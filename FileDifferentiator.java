package differentiator;

import java.util.ArrayList;
import java.util.Arrays;

public class FileDifferentiator {

	public static void main(String[] args) {
		
		FileDifferentiator differentiator = new FileDifferentiator();
		SupportedFileTypes supportedFileTypes = differentiator.new SupportedFileTypes();
		ArrayList<FileType> supportedTypes = supportedFileTypes.getSupportedFileTypes();

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

