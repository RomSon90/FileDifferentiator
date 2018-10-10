package differentiator;

public class FileDifferentiator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
	}
}
