import java.awt.Color;

import javax.swing.JLabel;


@SuppressWarnings("serial")
public class ChessSquare extends JLabel {

	//initial label color
	private Color iColor;

	public ChessSquare()
	{	
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
