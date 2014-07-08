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
	private int previousSelectedIndex = -1;
	private boolean pieceSelected = false;
	

	public ChessFrame(int length)
	{
		super("Chess Board");

		grid = new GridLayout(8,8);
		setLayout( grid );

		ChessSquare.loadSize(length/12, length/12);
		CompPiece.setupMatrix();

		for(i=0; i<8; i++)
		{
			for(j=0; j<8; j++)
			{
				mIndex=i*8+j;
				labelSet.add(new ChessSquare(mIndex));
				labelSet.get(mIndex).addMouseListener(new iMouseListener(mIndex));

				if((i%2)+(j%2)==1)
					labelSet.get(mIndex).setIcolor(Color.LIGHT_GRAY);
				else
					labelSet.get(mIndex).setIcolor(Color.WHITE);
				
				add( labelSet.get(mIndex));
			}
		}
	}

	private class iMouseListener extends MouseAdapter
	{
		private int index;

		public iMouseListener(int index)
		{
			this.index = index;
		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			System.out.println("Mouse pressed for label " + index);
			//TO DO:
			//	check if piece can be selected
			//	then pass information
			//	
			if(labelSet.get(mIndex).canSelect())
				System.out.println("aight");
			
		}

		@Override
		public void mouseReleased(MouseEvent e) 
		{
			//System.out.println("Mouse released for label " + index);
		}

		public boolean canMove(int nIndex, int oIndex)
		{
			return true;
		}

		public void move(int nIndex, int oIndex)
		{
			
		}
	}
}
