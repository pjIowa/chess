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
	private boolean previousSelected = false;
	private boolean isWhiteTurn = true;


	public ChessFrame(int length){
		super("Chess Board");

		setSize(length, length);

		//set up grid
		grid = new GridLayout(8,8);
		setLayout( grid );

		//load image dimensions
		ChessPiece.loadSize(length/12);;

		for(i=0; i<8; i++){
			for(j=0; j<8; j++){
				mIndex=i*8+j;

				//store reference to square
				labelSet.add(new ChessSquare(mIndex));

				//initialize square with a piece and keep a reference
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
			//keep reference to piece
			pieceSet.put(index, piece);
			//set icon
			labelSet.get(index).setIcon(piece.getIcon());
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
			//System.out.println("Mouse pressed for row:" + index/8+", column:" + index%8);
			//Scenario 1: no pieces have been selected yet, no piece on square X
			//Scenario 2: no pieces have been selected yet, piece selected is on incorrect team X
			//Scenario 3: no pieces have been selected yet, piece selected is on correct team X
			//Scenario 4: piece has been selected, new position is occupied by opposite teamX
			//Scenario 5: piece has been selected, new position is occupied by same teamX
			//Scenario 6: piece has been selected, piece can't move to new position with its freedom of movementX
			//Scenario 7: piece has been selected, new position is the same as original positionX
			if(previousSelected){
				if(index == previousSelectedIndex){
					//deselect original position for no movement of piece
					labelSet.get(index).deselect();
					previousSelected = false;
					previousSelectedIndex = -1;
				}
				else{
					ChessPiece piece = pieceSet.get(previousSelectedIndex);
					ChessPiece destPiece = pieceSet.get(index);
					
					if(piece.canMove(previousSelectedIndex, index, pieceSet.keySet())){
						if(destPiece != null){
							//allow kill when destination is opponent's piece
							if(piece.isWhite()^destPiece.isWhite()){
								//move icons
								labelSet.get(previousSelectedIndex).setIcon(null);
								labelSet.get(index).setIcon(piece.getIcon());
								
								//remove piece references
								pieceSet.remove(index);
								pieceSet.remove(previousSelectedIndex);
								//add piece reference at new location
								pieceSet.put(index, piece);
								
								//deselect original position
								labelSet.get(previousSelectedIndex).deselect();
								previousSelected = false;
								previousSelectedIndex = -1;
								
								//switch turn
								isWhiteTurn = !isWhiteTurn;
							}
							//deselect original position for invalid kill
							else{
								labelSet.get(previousSelectedIndex).deselect();
								previousSelected = false;
								previousSelectedIndex = -1;
							}
						}
						else{
							//move icon to new vacant position
							labelSet.get(previousSelectedIndex).setIcon(null);
							labelSet.get(index).setIcon(piece.getIcon());
							
							//remove destination piece reference
							pieceSet.remove(index);
							//add reference at new location
							pieceSet.put(index, piece);
							
							//deselect original position
							labelSet.get(previousSelectedIndex).deselect();
							previousSelected = false;
							previousSelectedIndex = -1;
							
							//switch turn
							isWhiteTurn = !isWhiteTurn;
						}
					}
					//piece doesn't have freedom to move to new position
					else{
						//deselect original position
						labelSet.get(previousSelectedIndex).deselect();
						previousSelected = false;
						previousSelectedIndex = -1;
					}
				}
			}
			else{
				if(pieceSet.get(index) != null){
					ChessPiece piece = pieceSet.get(index);

					//if correct turn for piece selected, allow selection
					if(!(piece.isWhite()^isWhiteTurn)){
						labelSet.get(index).select();
						previousSelected = true;
						previousSelectedIndex = index;
					}
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {}
	}
}
