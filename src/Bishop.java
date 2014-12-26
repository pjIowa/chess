import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Bishop extends ChessPiece {
	private final String name;

	public Bishop(boolean isWhite) {
		super(isWhite);
		name= "B_"+(isWhite()?"White":"Black");
	}

	@Override
	public boolean canMove(int previousIndex, int index, Set<Integer> set) {
		//left diagonal as seen by user
		if((index - previousIndex)%7 == 0){
			//check for pieces in between
			if(index>previousIndex){
				do{
					previousIndex+=7;
					if(set.contains(Integer.valueOf(previousIndex))) return false;
				}while(previousIndex+7<index);
			}
			else{
				do{
					index+=7;
					if(set.contains(Integer.valueOf(index))) return false;
				}while(index+7<previousIndex);
			}
			return true;
		}
		//right diagonal as seen by user
		else if((index - previousIndex)%9 == 0){
			if(index>previousIndex){
				do{
					previousIndex+=9;
					if(set.contains(Integer.valueOf(previousIndex))) return false;
				}while(previousIndex+9<index);
			}
			else{
				do{
					index+=9;
					if(set.contains(Integer.valueOf(index))) return false;
				}while(index+9<previousIndex);
			}
			return true;
		}
		else return false;
		
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
