import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Rook extends ChessPiece {
	private final String name;

	public Rook(boolean isWhite) {
		super(isWhite);
		name= "R_"+(isWhite()?"White":"Black");
	}

	@Override
	public boolean canMove(int previousIndex, int index, Set<Integer> set) {
		//vertical move
		if(((index%8)==(previousIndex%8))&&((index/8)!=(previousIndex/8))){
			if(index>previousIndex){
				//check for pieces in between
				while(index>previousIndex+8){
					previousIndex+=8;
					if(set.contains(Integer.valueOf(previousIndex))) return false;
				}
			}
			else{
				while(previousIndex>index+8){
					index+=8;
					if(set.contains(Integer.valueOf(index))) return false;
				}
			}
			return true;
		}
		//horizontal move
		else if(((index%8)!=(previousIndex%8))&&((index/8)==(previousIndex/8))){
			if(index>previousIndex){
				while(index>previousIndex+1){
					previousIndex+=1;
					if(set.contains(Integer.valueOf(previousIndex))) return false;
				}
			}
			else{
				while(previousIndex>index+1){
					index+=1;
					if(set.contains(Integer.valueOf(index))) return false;
				}
			}
			return true;
		}
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
