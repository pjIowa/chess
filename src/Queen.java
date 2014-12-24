import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Queen extends ChessPiece {
	private final String name;

	public Queen(boolean isWhite) {
		super(isWhite);
		name= "Q_"+(isWhite()?"White":"Black");
	}

	@Override
	public boolean canMove(int previousIndex, int index) {
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
