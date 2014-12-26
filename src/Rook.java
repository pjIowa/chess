import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
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
				do{
					previousIndex+=8;
					if(set.contains(Integer.valueOf(previousIndex))) return false;
				}while(previousIndex+8<index);
			}
			else{
				do{
					index+=8;
					if(set.contains(Integer.valueOf(index))) return false;
				}while(index+8<previousIndex);
			}
			return true;
		}
		//horizontal move
		else if(((index%8)!=(previousIndex%8))&&((index/8)==(previousIndex/8))){
			if(index>previousIndex){
				do{
					previousIndex+=1;
					if(set.contains(Integer.valueOf(previousIndex))) return false;
				}while(previousIndex+1<index);
			}
			else{
				do{
					index+=1;
					if(set.contains(Integer.valueOf(index))) return false;
				}while(index+1<previousIndex);
			}
			return true;
		}
		else
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
