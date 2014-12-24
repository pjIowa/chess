import javax.swing.ImageIcon;

public abstract class ChessPiece {
	private final boolean isWhite;
	private static int imageL;
	private int index;

	public ChessPiece(boolean isWhite) {
		this.isWhite = isWhite;
	}
	
	public boolean isWhite(){
		return isWhite;
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public static void loadSize(int imageL){
		ChessPiece.imageL = imageL;
	}
	
	public static int imageLength(){
		return ChessPiece.imageL;
	}
	
	public abstract boolean canMove(int previousIndex, int index);
	public abstract ImageIcon getIcon();
}
