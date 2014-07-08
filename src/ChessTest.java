import java.awt.Dimension;
import java.awt.Toolkit;

public class ChessTest {

	/**
	 * @testing
	 */
	public static void main(String[] args) {
		final int length = 600;
		
		ChessFrame chessFrame = new ChessFrame(length);// Get the size of the screen
		chessFrame.setDefaultCloseOperation(ChessFrame.EXIT_ON_CLOSE);
		chessFrame.setSize(length, length);
		chessFrame.setVisible(true);
		
		//dimension variable
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		 
		// Determine the new location of the window
		int w = chessFrame.getSize().width;
		int h = chessFrame.getSize().height;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;
		 
		// Move the window
		chessFrame.setLocation(x, y);
		

	}

}
