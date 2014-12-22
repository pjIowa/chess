import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class ChessSquare extends JLabel {

	//location on frame
	private int index;
	//initial label color
	private Color iColor;
	
	//constant label size variables
	private static int imageW, imageH;

	public ChessSquare(int index)
	{
		this.index=index;
		
		setOpaque(true);
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
		
		String pieceName; 
		switch (index) {
		case 0: case 7: pieceName = "R_White";
		break;
		case 1: case 6: pieceName = "H_White";
		break;
		case 2: case 5: pieceName = "B_White";
		break;
		case 3: pieceName = "K_White";
		break;
		case 4: pieceName = "Q_White";
		break;
		case 56: case 63: pieceName = "R_Black";
		break;
		case 57: case 62: pieceName = "H_Black";
		break;
		case 58: case 61: pieceName = "B_Black";
		break;
		case 60: pieceName = "Q_Black";
		break;
		case 59: pieceName = "K_Black";
		break;
		case 8: case 9: case 10: case 11:
		case 12: case 13: case 14: case 15: pieceName = "P_White";
		break;
		case 48: case 49: case 50: case 51:
		case 52: case 53: case 54: case 55: pieceName = "P_Black";
		break;
		default: pieceName = "";
		break;
		}
		if(pieceName.length() > 0){
			setIcon(new ImageIcon(getImageFor(pieceName)));
		}
		else{
			setIcon(null);
		}
	}

	//set size for each label
	public static void loadSize(int imageW, int imageH){
		ChessSquare.imageW=imageW;
		ChessSquare.imageH=imageH;
	}

	public void setIcolor(Color iColor){
		this.setBackground(iColor);
		this.iColor=iColor;
	}

	public Color getIcolor() {return iColor;}

	//get function to find local png file
	public Image getImageFor(String name){
		Image img;
		BufferedImage src;
		try {
			File file = new File(".");
			src = ImageIO.read(new File(file.getCanonicalPath()+"\\src\\" + name + ".png"));
			img = src;
			return img.getScaledInstance(imageW, imageH, Image.SCALE_SMOOTH);
		} 
		catch (IOException e) {return null;}
	}

	public void select() {
		setBackground(Color.YELLOW);//changes current spot to yellow
	}

	public void revertSelection(){
		setBackground(getIcolor());//reset color of label
	}
}
