package differentiator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class FileDifferentiator {

	public static void main(String[] args) {
		FileDifferentiator differentiator = new FileDifferentiator();
		
		//Get the file path
		if (args.length==0) {
			throw new IllegalArgumentException("No file path specified");
		}
		Path filePath = Paths.get(args[0]);

		//Get supported file types
		SupportedFileTypes supportedFileTypes = differentiator.new SupportedFileTypes();
		ArrayList<FileType> supportedTypesArray = supportedFileTypes.getSupportedFileTypes();
		
		// Create the FileType out of target file
		String fileExtension = pathToExtension(filePath);
		byte[] magicBytes = getMagicBytes(filePath);
		int offset = 0;
		
		FileType checkedFile = differentiator.new FileType(fileExtension, magicBytes, offset);
		for (FileType fType : supportedTypesArray) {
			if (fType.equals(checkedFile)) {
				if (checkMagicBytesMatch(checkedFile.magicNumber, fType.magicNumber, checkedFile.extension)){
					System.out.println("File extension: " + fileExtension + " File type: " + fType.extension);
				}
			}
		}
	}
	
	private static boolean checkMagicBytesMatch(byte[] fileBytes, byte[] extensionBytes, String extension) {
		switch(extension) {
		case "JPG":
		case "JPEG":
			byte[] threeFileBytes = new byte[] {fileBytes[0], fileBytes[1], fileBytes[2]};
			return Arrays.equals(threeFileBytes, extensionBytes);
		}
		return Arrays.equals(fileBytes, extensionBytes);
	}
	
	private static byte[] getMagicBytes(Path filePath) {
		byte[] fileMagicNumber = new byte[4] ;
		
		try {
			byte[] fileAllBytes = Files.readAllBytes(filePath);
			for (int i=0; i < 4; i++) {
				fileMagicNumber[i] = fileAllBytes[i];
			}
		} catch (IOException ex) {
			System.out.println(ex.toString());
		}
		
		return fileMagicNumber;
		
	}
	
	private static String pathToExtension(Path path) {
		String fileName = path.toString();
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i+1);
		}
		
		return extension;
	}
	
	public class FileType {
		// This class describes a type of file, it is used for comparison with an existing file
		private String extension;
		private byte[] magicNumber;
		int offset;
		
		public FileType(String extension, byte[] magicNumber, int offset) {
			this.extension=extension.toUpperCase();
			this.magicNumber=magicNumber;
			this.offset=offset;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this==obj) {
				return true;
			}
			if (obj==null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			FileType otherFileType = (FileType) obj;
			if (extension==null) {
				if (otherFileType.extension != null) {
					return false;
				}
			} else if (!extension.equals(otherFileType.extension)) {
				return false;
			}
			if (!Arrays.equals(magicNumber, otherFileType.magicNumber)) {
				return false;
			}
			if (offset != otherFileType.offset) {
				return false;
			}
			return true;
		}
		
		
	}
	
	private class SupportedFileTypes {
		// This class fetches supported file types and stores them in an array
	
		public ArrayList<FileType> getSupportedFileTypes() {
			ArrayList<FileType> supportedTypes = new ArrayList<FileType>();
			FileType gif87a = new FileType("gif", new byte[] {0x47, 0x49, 0x46, 0x38, 0x37, 0x61}, 0);
			FileType gif89a = new FileType("gif", new byte[] {0x47, 0x49, 0x46, 0x38, 0x39, 0x61}, 0);
			FileType jpg = new FileType("jpg", new byte[] {(byte)0xff, (byte)0xd8, (byte)0xff}, 0); 
			//FileType txt = new FileType
			
			supportedTypes.add(gif87a);
			supportedTypes.add(gif89a);
			supportedTypes.add(jpg);
			
			return supportedTypes;
			
		}
	}
}

