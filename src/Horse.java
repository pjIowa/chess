import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class Horse extends ChessPiece {
	private final String name;

	public Horse(boolean isWhite) {
		super(isWhite);
		name= "H_"+(isWhite()?"White":"Black");
	}

	@Override
	public boolean canMove(int previousIndex, int index, Set<Integer> set) {
		int prevRow = previousIndex/8;
		int prevCol = previousIndex%8;
		int destRow = index/8;
		int destCol = index%8;

		//down 2, left 1
		if((destRow==prevRow+2)&&(destCol==prevCol-1)) return true;
		//down 2, right 1
		else if((destRow==prevRow+2)&&(destCol==prevCol+1)) return true;
		//down 1, left 2
		else if((destRow==prevRow+1)&&(destCol==prevCol-2)) return true;
		//down 1, right 2
		else if((destRow==prevRow+1)&&(destCol==prevCol+2)) return true;
		//up 2, left 1
		else if((destRow==prevRow-2)&&(destCol==prevCol-1)) return true;
		//up 2, right 1
		else if((destRow==prevRow-2)&&(destCol==prevCol+1)) return true;
		//up 1, left 2
		else if((destRow==prevRow-1)&&(destCol==prevCol-2)) return true;
		//up 1, right 2
		else if((destRow==prevRow-1)&&(destCol==prevCol+2)) return true;
		return false;
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
