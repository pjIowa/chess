import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.imageio.ImageIO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class ChessFrame extends JFrame{

	private GridLayout grid;
	private int i, j, mIndex;//index variables
	private ArrayList<ChessSquare> labelSet = new ArrayList<ChessSquare>();
	private HashMap<Integer,ChessPiece> pieceSet = new HashMap<Integer,ChessPiece>();
	private int previousSelectedIndex = -1;
	private boolean pieceSelected = false;
	

	public ChessFrame(int length){
		super("Chess Board");
		
		setSize(length, length);

		grid = new GridLayout(8,8);
		setLayout( grid );

		ChessSquare.loadSize(length/12, length/12);
		
		for(i=0; i<8; i++){
			for(j=0; j<8; j++){
				mIndex=i*8+j;
				
				//store reference to square
				labelSet.add(new ChessSquare(mIndex));
				
				//add piece and keep a reference
				addPiece(mIndex);
				
				//add a separate mouse listener to each square
				labelSet.get(mIndex).addMouseListener(new iMouseListener(mIndex));
				
				//color white and gray alternating
				if((i%2)+(j%2)==1)
					labelSet.get(mIndex).setIcolor(Color.LIGHT_GRAY);
				else
					labelSet.get(mIndex).setIcolor(Color.WHITE);
				
				//adds chess square to frame
				add( labelSet.get(mIndex));
			}
		}
	}
	
	public void addPiece(int index){
		ChessPiece piece;
		switch (index) {
		case 0: case 7: piece = new Rook(true);
		break;
		case 1: case 6: piece = new Horse(true);
		break;
		case 2: case 5: piece = new Bishop(true);
		break;
		case 3: piece = new King(true);
		break;
		case 4: piece = new Queen(true);
		break;
		case 56: case 63: piece = new Rook(false);
		break;
		case 57: case 62: piece = new Horse(false);
		break;
		case 58: case 61: piece = new Bishop(false);
		break;
		case 60: piece = new Queen(false);
		break;
		case 59: piece = new King(false);
		break;
		case 8: case 9: case 10: case 11:
		case 12: case 13: case 14: case 15: piece = new Pawn(true);
		break;
		case 48: case 49: case 50: case 51:
		case 52: case 53: case 54: case 55: piece = new Pawn(false);
		break;
		default: piece = null;
		break;
		}
		if(piece != null){
			pieceSet.put(index, piece);
		}
	}
	
	private class iMouseListener extends MouseAdapter
	{
		private int index;

		public iMouseListener(int index){
			this.index = index;
		}

		@Override
		public void mousePressed(MouseEvent e){
			System.out.println("Mouse pressed for row:" + index/8+", column:" + index%8);
		}

		@Override
		public void mouseReleased(MouseEvent e) {}
	}
}
