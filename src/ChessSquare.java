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

	public ChessSquare(int index)
	{
		this.index=index;
		
		setOpaque(true);
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
	}

	public void setIcolor(Color iColor){
		this.setBackground(iColor);
		this.iColor=iColor;
	}

	public Color getIcolor() {return iColor;}

	public void select() {
		setBackground(Color.YELLOW);//changes current spot to yellow
	}

	public void deselect(){
		setBackground(getIcolor());//reset color of label
	}
}
