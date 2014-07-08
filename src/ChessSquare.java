import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	//computation piece
	private CompPiece piece;

	//constant label size variables
	private static int imageW, imageH;

	public ChessSquare(int index)
	{
		this.index=index;
		//setSpecs(name);
		//label adjustments
		setOpaque(true);
		
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);

		switch (index) {
		case 0: case 7: piece = CompPiece.W_ROOK;
		break;
		case 1: case 6: piece = CompPiece.W_HORSE;
		break;
		case 2: case 5: piece = CompPiece.W_BISHOP;
		break;
		case 3: piece = CompPiece.W_KING;
		break;
		case 4: piece = CompPiece.W_QUEEN;
		break;
		case 56: case 63: piece = CompPiece.B_ROOK;
		break;
		case 57: case 62: piece = CompPiece.B_HORSE;
		break;
		case 58: case 61: piece = CompPiece.B_BISHOP;
		break;
		case 60: piece = CompPiece.B_QUEEN;
		break;
		case 59: piece = CompPiece.B_KING;
		break;
		case 8: case 9: case 10: case 11:
		case 12: case 13: case 14: case 15: piece = CompPiece.W_PAWN;
		break;
		case 48: case 49: case 50: case 51:
		case 52: case 53: case 54: case 55: piece = CompPiece.B_PAWN;
		break;
		default: piece = null;
		break;
		}
		if(piece != null)
		{
			setIcon(new ImageIcon(getImageFor(piece.getImageName())));
			System.out.println(CompPiece.isWhiteAt(index) + " "+ index);
		}
		else
			setIcon(null);
	}

	//set size for each label
	public static void loadSize(int imageW, int imageH)
	{
		ChessSquare.imageW=imageW;
		ChessSquare.imageH=imageH;
	}

	public void setIcolor(Color iColor)
	{
		this.setBackground(iColor);
		this.iColor=iColor;
	}

	public Color getIcolor() {return iColor;}

	//get function to find local png file
	public Image getImageFor(String name)
	{
		Image img;
		BufferedImage src;
		try 
		{
			src = ImageIO.read(new File("C:\\Users\\Prajwal Kedilaya\\workspace\\ChessBoard\\src\\" + name + ".png"));
			img = src;
			return img.getScaledInstance(imageW, imageH, Image.SCALE_SMOOTH);
		} 
		catch (IOException e) {return null;}
	}
	
	public boolean canSelect()
	{
		if(piece == null)
			return false;
		else 
			return piece.canSelect();
	}

	public void select() 
	{
		setBackground(Color.YELLOW);//changes current spot to yellow
	}

	public void revertSelection()
	{
		setBackground(getIcolor());//reset color of label
	}

}
