import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class King extends ChessPiece {
	private final String name;

	public King(boolean isWhite) {
		super(isWhite);
		name= "K_"+(isWhite()?"White":"Black");
	}

	@Override
	public boolean canMove(int previousIndex, int index, Set<Integer> set) {
		return true;
	}

	@Override
	public ImageIcon getIcon() {
		Image img;
		BufferedImage src;
		try {
			File file = new File(".");
			src = ImageIO.read(new File(file.getCanonicalPath()+"\\src\\" + name + ".png"));
			img = src;
			return new ImageIcon(img.getScaledInstance(ChessPiece.imageLength(), ChessPiece.imageLength(), Image.SCALE_SMOOTH));
		} 
		catch (IOException e) {return null;}
	}

}
