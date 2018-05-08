import java.util.*;//Stack<>
 
/**
   Stores the chessboard status and handles piece moving and interaction
   
   @author  Omkar Bhalerao
   @author  Aneesh Boreda
   @author  Denaly Min
   @version 7 June 2017
*/
public class Chessboard{
   public static Piece[][] board;//array where all pieces are stored
   public static int[] clickLoc;//stores location of last click
   public static boolean pieceSelected;//whether or not a piece is already selected
   public static Stack<int[]> legalSquares;//the legal moves of a piece
   public static int[][]legalSquaresArr;//legal squares in array form
   public static int turn = 0;//turn counter, 0 -> white's turn, 1 -> black's turn
   public static boolean promotion = false;//whether or not a piece is currently in need of promotion
   public static boolean check = false;//if the king is in check currently
   public static ArrayList<int[]> whiteSquares = new ArrayList<int[]>(321);//squares that white controls
   public static ArrayList<int[]> blackSquares = new ArrayList<int[]>(321);//squares that black controls
   /**
      default constructor
   */
   public Chessboard(){}
   /**
      When a piece is selected on the board, this method highlights the legal moves of that piece, provided it is your turn.
      Method called from ChessPanel when a button is clicked.
      
      @param a       the row of the selected piece
      @param b       the column of the selected piece
      @return board  Piece[][] representing the current board state
   */
   public static Piece[][] Input(int a, int b)
   {
      
      if(clickLoc==null && getPiece(a,b)!=null && !promotion)//when a square is clicked
      {
         InfoPanel.reset();//clears old messages from info panel
         if(getPiece(a,b).col==turn)//if the piece selected can move
         {
            pieceSelected=true;//piece now becomes selected
            clickLoc = new int[] {a,b};//
            ChessPanel.board[a][b].setBackground(ChessPanel.selectedCol);//turns selected piece orange
            legalSquares=board[a][b].getLegalSquares(board);//gets the selected piece's legal moves
            legalSquaresArr=new int[legalSquares.size()][2];//the following lines turn the legalSquares ArrayList into an Array
            int count = 0;
            while(!legalSquares.empty())
            {
               legalSquaresArr[count]=legalSquares.pop();
               count++;
            }
            highlightLegalSquares(legalSquaresArr);//turns the legal moves green
         }
         else//so if it's not your turn
         {
            if(turn==0)//if black piece is selected during white's turn
            {
               InfoPanel.setErrorMessageText("It is white's turn. Please move a white piece");
            }
            else//if white piece is selected during black's turn
            {
               InfoPanel.setErrorMessageText("It is black's turn. Please move a black piece");
            }
         }
      }
      else if(pieceSelected)//if a piece is already selected
      {
         InfoPanel.reset();//clears old info messages
         if(a==clickLoc[0] && b==clickLoc[1])//if you click on same square again, it deselects the piece
         {
            ChessPanel.resetColor(clickLoc[0],clickLoc[1]);//resets color to normal from orange
            pieceSelected=false;//following variables are reset 
            clickLoc=null;
            resetLegalSquares(legalSquaresArr);
         }
         else
         {
            boolean contains = false;
            for(int[] i: legalSquaresArr)//checks to see if destination is a legal move
            {
               if(a == i[0] && b == i[1])
               {
                  contains = true;
                  break;
               }
            }
            if(contains == true)//if so, we move the piece
            {
               board[clickLoc[0]][clickLoc[1]].move(a,b, board);
               ChessPanel.resetColor(clickLoc[0],clickLoc[1]);
               whiteSquares.clear();
               blackSquares.clear();
               ChessPanel.updateBoard();//updates the GUI with our new board
               clickLoc=null;
               pieceSelected=false;
               resetLegalSquares(legalSquaresArr);
               if(turn==0)//and change the turn
               {
                  turn=1;
               }
               else
               {
                  turn=0;  
               }
            }
            else//if it is not a legal move, display an error message on the info panel
            {
               InfoPanel.setErrorMessageText("Please choose one of the squares highlighted green.");
            }
         }
      }
      return board;//finally, returns the new state of the board if needed
   }
   /**
      Returns the piece found at location (a, b) on the chessboard.
      Called in the updateBoard method from class ChessPanel.
      
      @param a             row of piece
      @param b             column of piece
      @return board[a][b]  piece present at given board location
   */
   public static Piece getPiece(int a, int b)
   {
      return board[a][b];
   }
   /**
      When a piece is selected, highlights the legal moves in green.
      Called in Input.
      
      @param legalSquares  an int[][] containing the legal moves for the piece
   */
   public static void highlightLegalSquares(int[][]legalSquares)
   {
      for(int[]x:legalSquares)//changes the color of every square which a selected piece can move to to green
      {  
         ChessPanel.board[x[0]][x[1]].setBackground(ChessPanel.legalCol);
      }  
   }
   /**
      When a piece is moved or deselected, resets all squares to default grey.
      Called in Input.
      
      @param legalSquares  an int[][] containing the legal moves for the piece
   */
   public static void resetLegalSquares(int[][]legalSquares)
   {
      for(int[]x:legalSquares)//turns highlighted squares back to white or black depending on their position
      {
         ChessPanel.resetColor(x[0],x[1]);
      }  
   }
}