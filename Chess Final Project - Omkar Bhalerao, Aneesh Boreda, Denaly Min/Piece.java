import java.util.*;//Stack
import javax.swing.*;//ImageIcon
import java.lang.Math;

/**
   Parent class from which all concrete pieces inherit
      
   @author  Omkar Bhalerao
   @author  Aneesh Boreda
   @author  Denaly Min
   @version 17 May 17
*/
public abstract class Piece{
   protected int a;//column
   protected int b;//row
   protected int col;//color, 0 is white, 1 is black
   public String name;//name corresponding to piece type
   public boolean justMoved;
   public static int[] lastMoved = new int[]{1,1};
   public boolean enPassant;
   protected ImageIcon piece;//the graphical representation of the piece on our GUI
   public boolean firstMove;
   public boolean castling;
   /**
      Initializes position and color fields
      
      @param a    row of piece
      @param b    column of piece
      @param col  color of piece 
   */
   public Piece(int a, int b, int col)
   {
      firstMove=true;
      this.a=a;//position
      this.b=b;
      this.col=col;//color
   }
   /**
      Creates a copy of the piece for move testing, not implemented
      
      @param x the Piece to be cloned
   */
   public abstract void clonePiece(Piece x);
   /**
      Finds legal moves, defined separately in each class
      @param board   the board
      @return Stack  a Stack of int[] which stores legal moves
   */   
   public abstract Stack<int[]> getLegalSquares(Piece[][] board);
   /**
      Finds the image representing the piece from the folder ChessPieceImages
      
      @return ImageIcon the ImageIcon which the image is stored as
   */
   public abstract ImageIcon getImage();
   /**
      Finds all attacked squares
      Used for calculating check, but not yet implemented 
      
      @param board               the board
      @return availableSquares   a Stack showing all squares attacked by this piece
   */
   public abstract Stack<int[]> getAttackedSquares(Piece[][] board);
   /**
      Moves the piece 
      
      @param a                   column of moving piece
      @param b                   row of moving piece
      @param board               board on which the pieces are
   */
   public void move(int a, int b, Piece[][] board)   
   {
      try
      {
         board[lastMoved[0]][lastMoved[1]].justMoved=false;//the piece that moved before this piece has no longer "justMoved"
      }
      catch(Exception e){}
      Piece x = board[this.a][this.b];//transfers piece to temporary placeholder variable
      board[this.a][this.b]=null;//clears space
      board[a][b]=x;//moves piece to new space
      int oldA=this.a;//updates position variables
      int oldB=this.b;
      this.a=a;
      this.b=b;
      if(this.firstMove)//if it was the first move
      {
         justMoved=true;//it has now just moved
         this.firstMove=false;//and the next move will not be its first
      }
      lastMoved[0]=a;
      lastMoved[1]=b;
   }
}