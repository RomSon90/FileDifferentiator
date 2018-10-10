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
}
