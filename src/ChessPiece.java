import java.util.Set;
import javax.swing.ImageIcon;

public abstract class ChessPiece {
	private final boolean isWhite;
	private static int imageL;

	public ChessPiece(boolean isWhite) {
		this.isWhite = isWhite;
	}

	public boolean isWhite(){
		return isWhite;
	}

	public static void loadSize(int imageL){
		ChessPiece.imageL = imageL;
	}

	public static int imageLength(){
		return ChessPiece.imageL;
	}

	public abstract boolean canMove(int previousIndex, int index, Set<Integer> set);
	public abstract ImageIcon getIcon();
}
