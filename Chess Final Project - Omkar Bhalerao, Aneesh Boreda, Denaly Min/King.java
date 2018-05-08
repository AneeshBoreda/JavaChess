import java.util.*;
import javax.swing.*;
import java.lang.*;
/**
   The King class is for instantiating kings

   @author  Omkar Bhalerao
   @author  Aneesh Boreda
   @author  Denaly Min
   @version 7 June 2017
*/
public class King extends Piece{
  
   public Stack<int[]> availableSquares;//sqaures it can move to
   public ImageIcon piece;//its image
   /**
      Initializes position and color, as well as other fields.
      
      @param a    row of piece
      @param b    column of piece
      @param col  color of piece 
   */
   public King(int a, int b, int color){
      super(a,b,color);
      name="King";
      firstMove = true;
      if(color == 0){
         this.piece=new ImageIcon("ChessPieceImages/Blue2K.png");
      }
      else{
         this.piece=new ImageIcon("ChessPieceImages/BlackK.png");
      }
   }
   /**
      Creates a copy of the piece given as an argument, not used in the program
      
      @param x    piece to clone
     
   */
   public void clonePiece(Piece x)
   {
      this.a=x.a;
      this.b=x.b;
      this.firstMove=x.firstMove;
      this.col=x.col;
      this.piece=x.piece;
      this.name="King";
      this.justMoved = x.justMoved;
      this.castling = x.castling;
      
   }
   
   /**
      Returns the image of the piece      
      @return piece  The ImageIcon of the piece
     
   */
   public ImageIcon getImage(){
      return piece;
   }
   /**
      Moves the king
      
      @param a       row of piece
      @param b       column of piece
      @param board   The array which the piece is in 
   */
   public void move(int a, int b, Piece[][] board)
   {
      Piece x = board[this.a][this.b];
      board[this.a][this.b]=null;
      board[a][b]=x;
      int oldA=this.a;  
      int oldB=this.b;    
      this.a=a;
      this.b=b;
      //handles castling
      if(castling){
         if(Math.abs(oldB-this.b)==2)
         {
            
            if(this.b>3)
            {
               board[this.a][this.b-1]=board[this.a][this.b+1];
               board[this.a][this.b-1].b=this.b-1;
               board[this.a][this.b+1]=null;
            }
            else
            {
               board[this.a][this.b+1]=board[this.a][this.b-2];
               board[this.a][this.b+1].b=this.b+1;
               board[this.a][this.b-2]=null;
            }
         }
      }
      if(this.firstMove)
      {
         justMoved=true;
         this.firstMove=false;
      }
      lastMoved[0]=a;
      lastMoved[1]=b;
   }  
   /**
      Finds all attacked squares
      Used for calculating check, but not yet implemented 
      
      @param board               The array to get attacked squares on
      @return availableSquares   a Stack showing all squares attacked by this piece
   */

   public Stack<int[]> getAttackedSquares(Piece[][] board){
      availableSquares=new Stack<>();
      int k = Math.max(0,a-1);
      int l=  Math.min(7,a+1);
      int m=  Math.max(0,b-1);
      int n=  Math.min(7,b+1);
      for(int i = k; i<=l; i++)
      {  
         for(int j = m; j<=n; j++)
         {
            if(i==a && j==b){
               continue;
            }
            availableSquares.push(new int[]{i,j});
         }
      }
      return availableSquares;
   }
    /**
      Finds all legal squares to move to
      Used for calculating check, but not yet implemented 
      
      @param board               the array to get legal squares on
      @return availableSquares   a stack of all potential squares the king can move to
   */

    public Stack<int[]> getLegalSquares(Piece[][] board){
      availableSquares=new Stack<>();
      int k = Math.max(0,a-1);
      int l=  Math.min(7,a+1);
      int m=  Math.max(0,b-1);
      int n=  Math.min(7,b+1);
      //loops through all squares surrounding the king
      for(int i = k; i<=l; i++)
      {  
         for(int j = m; j<=n; j++)
         {
            if(i==a && j==b){
               continue;
            }
            boolean containsB = false;
            boolean containsW = false;
           
            if(board[i][j]==null)
            {
               if((col == 0 && !(containsB)) || (col == 1 && !(containsW))){
                  availableSquares.push(new int[]{i,j});
               }
            }
            //tells you if the king is in check
            else if(board[i][j].col != this.col)
            {
               if((col == 0 && !(containsB)) || col == 1 && !(containsW))
               {
                  if(!board[i][j].name.equals("King"))
                  {
                     availableSquares.push(new int[]{i, j});
                  }
                  else
                  {
                     InfoPanel.setInfoMessageText("The king is in check.");
                  }
               }
            }
         }
      
      }
      if(this.firstMove)
      {
         if(b < 5){
            if(board[this.a][this.b+3] != null && board[this.a][this.b+2]==null
            && board[this.a][this.b+1]==null )
            {
               if(board[this.a][this.b+3].firstMove)
               {
                  availableSquares.push(new int[]{this.a, this.b+2});
                  castling=true;
               }
            }
         }
         if(b > 3){
            if(board[this.a][this.b-4] != null && board[this.a][this.b-2]==null
            && board[this.a][this.b-1]==null && board[this.a][this.b-3]==null
            )
            {
               if(board[this.a][this.b-4].firstMove)
               {
                  availableSquares.push(new int[]{this.a, this.b-2});
                  castling=true;
               }
            }
         }
      }
      
      return availableSquares;
   }
}