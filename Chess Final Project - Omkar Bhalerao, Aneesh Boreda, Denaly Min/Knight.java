import java.util.*;//Stack
import javax.swing.*;//ImageIcon

/**
   Knight class for instantiating Knights
      
   @author  Omkar Bhalerao
   @author  Aneesh Boreda
   @author  Denaly Min
   @version 7 June 17
*/
public class Knight extends Piece{
   public Stack<int[]> availableSquares;//available squares
   public ImageIcon piece;//the graphical representation
   /**
      Initializes position and color, as well as other fields.
      
      @param a    row of piece
      @param b    column of piece
      @param col  color of piece 
   */
   public Knight(int a, int b, int color)
   {
      super(a,b,color);
      name = "Knight";
      firstMove = true;
      if(color == 0){
         this.piece=new ImageIcon("ChessPieceImages/Blue2N.png");
      }
      else{
         this.piece=new ImageIcon("ChessPieceImages/BlackN.png");
      }
   }
   /**
      Creates a copy of the piece for move testing, not implemented
      
      @param x the Piece to be cloned
   */
   public void clonePiece(Piece x)
   {
      this.a = x.a;
      this.b = x.b;
      this.firstMove=x.firstMove;
      this.col=x.col;
      this.piece=x.piece;
      this.name="Knight";
      this.justMoved = x.justMoved;
   }
   /**
      Returns the image representing this piece.
      Called in method updateBoard of class ChessPanel.
      
      @return piece  ImageIcon of the corresponding chess piece
   */
   public ImageIcon getImage(){
      return piece;
   }
   /**
      Finds all attacked squares
      Used for calculating check, but not yet implemented 
      
      @param board               the board
      @return availableSquares   a Stack showing all squares attacked by this piece
   */
   public Stack<int[]> getAttackedSquares(Piece[][] board){
      availableSquares=new Stack<int[]>();
      for(int k = -2; k < 3; k+=4){
         for(int l = -1; l < 2; l+=2){
            if((a-l > -1 && b-k > -1) && (a-l < 8 && b-k < 8)){
               
               availableSquares.push(new int[]{a-l,b-k});
               
            }
            if((a-k > -1 && b-l > -1) && (a-k < 8 && b-l < 8)){
               availableSquares.push(new int[]{a-k,b-l});
               
            }
         }
      }
      return availableSquares;
   }
   /**
      Finds all legal moves for the piece.
      Called in method Input of class Chessboard.
      
      @param board               the board which the pieces are on
      @return availableSquares   a Stack of all the squares it can move to expressed as int[]
   */
   public Stack<int[]> getLegalSquares(Piece[][] board){
      availableSquares=new Stack<int[]>();
      for(int k = -2; k < 3; k+=4){
         for(int l = -1; l < 2; l+=2){
            if((a-l > -1 && b-k > -1) && (a-l < 8 && b-k < 8)){
               if(board[a-l][b-k] == null)
               {
                  availableSquares.push(new int[]{a-l, b-k});
               }
               else if(board[a-l][b-k].col == -1*(col-1))
               {
                  if(!board[a-l][b-k].name.equals("King"))
                  {
                     availableSquares.push(new int[]{a-l, b-k});
                  }
                  else
                  {
                     InfoPanel.setInfoMessageText("The king is in check.");
                  }
               }
            }
            if((a-k > -1 && b-l > -1) && (a-k < 8 && b-l < 8)){
               if(board[a-k][b-l] == null)
               {
                  availableSquares.push(new int[]{a-k, b-l});
               }
               else if(board[a-k][b-l].col == -1*(col-1))
               {
                  if(!board[a-k][b-l].name.equals("King"))
                  {
                     availableSquares.push(new int[]{a-k, b-l});
                  }
                  else
                  {
                     InfoPanel.setInfoMessageText("The king is in check.");
                  }
               }
            }
         }
      }
      return availableSquares;
   }
}