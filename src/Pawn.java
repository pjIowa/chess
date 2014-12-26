import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Pawn extends ChessPiece {
	private final String name;

	public Pawn(boolean isWhite) {
		super(isWhite);
		name= "P_"+(isWhite()?"White":"Black");
	}

	@Override
	public boolean canMove(int previousIndex, int index, Set<Integer> set) {
		boolean firstMove = false;
		//viewpoint from piece to middle of board
		int moveOffset;
		int rightKillOffset, leftKillOffset;
		
		if(isWhite()){
			//checks for first move
			if(previousIndex/8 == 1) firstMove = true;
			moveOffset = 8;
			rightKillOffset = 7;
			leftKillOffset = 9;
		}
		else{
			if(previousIndex/8 == 6) firstMove = true;
			moveOffset= -8;
			rightKillOffset = -7;
			leftKillOffset = -9;
		}
		
		if(firstMove){
			//move forward 2 allowed to empty spot
			if(index == previousIndex+moveOffset*2){
				if(set.contains(Integer.valueOf(index)))return false;
				else return true;
			}
		}
		//move forward 1 allowed to empty spot
		if(index == previousIndex+moveOffset) {
			if(set.contains(Integer.valueOf(index)))return false;
			else return true;
		}
		//diagonal kills
		//Note: ChessFrame checks if the kill is an opposing piece
		else if(index == previousIndex+leftKillOffset){
			if(set.contains(Integer.valueOf(index)))return true;
			else return false;
		}
		else if(index == previousIndex+rightKillOffset){
			if(set.contains(Integer.valueOf(index)))return true;
			else return false;
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
