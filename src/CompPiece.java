import java.awt.Color;


public enum CompPiece {
	B_ROOK(1, false),
	B_HORSE(2, false),
	B_BISHOP(3, false),
	B_QUEEN(4, false),
	B_KING(5, false),
	B_PAWN(6, false),
	W_ROOK(1, true),
	W_HORSE(2, true),
	W_BISHOP(3, true),
	W_QUEEN(4, true),
	W_KING(5, true),
	W_PAWN(6, true);
	
	private int p_type;
	private boolean isWhite;
	private boolean firstMove = true;
	
	//0 is unoccupied, 1 is white, 2 is black
	private static int [][] occupationMatrix = new int[8][8];
	
	//starts on white side first
	private static boolean whiteTurn = true;
	
	
	/*
	 * STATIC FUNCTIONS
	 */
	//sets up colors occupying the board at each location
	public static void setupMatrix()
	{
		int mIndex;
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				mIndex=i*8+j;
				
				if((mIndex >= 0) && (mIndex<= 15))
					occupationMatrix[i][j] = 1;
				else if((mIndex >= 48) && (mIndex<= 63))
					occupationMatrix[i][j] = 2;
				else
					occupationMatrix[i][j] = 0;
			}
		}
	}
	//gets if the board is occupied at the position
	private static boolean isOpenAt(int position)
	{
		int row = position/8;
		int col = position%8;
		return (occupationMatrix[row][col] == 0) ? true : false;
	}
	//gets if the board is occupied by a white piece at the position
	public static boolean isWhiteAt(int position)
	{
		int row = position/8;
		int col = position%8;
		return (occupationMatrix[row][col] == 1) ? true : false;
	}
	
	/*
	 * CLASS FUNCTIONS
	 */
	//constructor
	CompPiece(int initType, boolean initColor)
	{
		p_type = initType;
		isWhite = initColor;
		
		//System.out.println(isWhite + " "+getImageName());
	}
	
	//checks if the piece is a certain type
	private boolean isPawn() {return (p_type == 6)? true: false;}
	private boolean isKing() {return (p_type == 5)? true: false;}
	private boolean isQueen() {return (p_type == 4)? true: false;}
	private boolean isBishop() {return (p_type == 3)? true: false;}
	private boolean isHorse() {return (p_type == 2)? true: false;}
	private boolean isRook() {return (p_type == 1)? true: false;}
	private boolean isFirstMove(){return firstMove;}
	
	
	//checks if the piece can move
	public boolean canMoveTo(int newPosition)
	{
		if(whiteTurn && isWhite)
		{
			if(isPawn())
			{
				
			}
		}
		else if(!whiteTurn && !isWhite)
		{
			
		}
		else
		{
			return false;
		}
		return true;
		//returns false if either:
			//new position results in check for current side
			//current side is currently in check
		//returns true if the new position is valid
	}
	
	//moves pieces
	public void moveTo(int startPosition, int endPosition)
	{
		//only do boundary checks for positions
		if((startPosition >= 0) && (startPosition <= 63))
		{
			if((endPosition >= 0) && (endPosition <= 63))
			{
				if(startPosition != endPosition)
				{
					//adjust matrix for kill or standard move
				}
			}
		}
		
		//switches turn
		firstMove = false;
		whiteTurn = !whiteTurn;
	}
	
	//checks if piece can be selected
	public boolean canSelect()
	{
		System.out.println(whiteTurn + " "+ isWhite);
		if(whiteTurn && isWhite)
			return true;
		else if(!whiteTurn && !isWhite)
			return true;
		else
			return false;
	}
	
	//creates image name
	public String getImageName()
	{
		String rName = "";
		if(isPawn()) rName += "P_";
		else if(isQueen()) rName +="Q_";
		else if(isKing()) rName += "K_";
		else if(isBishop()) rName +="B_";
		else if(isRook()) rName += "R_";
		else if(isHorse()) rName += "H_";
		
		if(isWhite) rName+="White";
		else rName+= "Black";
		return rName;
	}
}
