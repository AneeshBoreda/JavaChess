import java.util.*;
import javax.swing.*;

/**
   Bishop class to instantiate bishops
      
   @author  Omkar Bhalerao
   @author  Aneesh Boreda
   @author  Denaly Min
   @version 7 June 17
*/
public class Bishop extends Piece{ 
   public Stack<int[]> availableSquares;//squares it can move to
   public ImageIcon piece;//bishop image
   /**
      Initializes position and color, as well as other fields.
      
      @param a    row of piece
      @param b    column of piece
      @param col  color of piece 
   */
   public Bishop(int a, int b, int color){
      super(a,b,color);//calls Piece class constructor
      name = "Bishop";//sets name to bishop
      firstMove = true;//it is the first move
      if(color == 0){//sets image according to color
         this.piece=new ImageIcon("ChessPieceImages/Blue2B.png");//white bishop
      }
      else{
         this.piece=new ImageIcon("ChessPieceImages/BlackB.png");//black bishop
      }
   }
   /**
      Creates a copy of the piece for move testing, not implemented
      
      @param x the Piece to be cloned
   */
   public void clonePiece(Piece x)
   {
      this.a = x.a;//sets all variables to the same values
      this.b = x.b;
      this.firstMove=x.firstMove;
      this.col=x.col;
      this.piece=x.piece;
      this.name="Bishop";
      this.justMoved = x.justMoved;
   }
   /**
      Returns the image representing this piece.
      Called in method updateBoard of class ChessPanel.
      
      @return piece  the ImageIcon that represents this piece
   */
   public ImageIcon getImage()
   {
      return piece;
   }
   /**
      Finds all attacked squares
      Used for calculating check, but not yet implemented 
      
      @param board               the board
      @return availableSquares   a Stack showing all squares attacked by this piece
   */
   public Stack<int[]> getAttackedSquares(Piece[][] board)
   {
      availableSquares=new Stack<>();
      int l;
      for(int k = 1; k < 8; k++){
         l = k;
         if(a+k > 7 || b+l > 7){
            break;
         }
         if(board[a+k][b+l] != null ){
            availableSquares.push(new int[]{a+k, b+l});
            
            if(!board[a+k][b+l].name.equals("King") || board[a+k][b+l].col==this.col)
            {
            break;
            }
            else{
               availableSquares.pop();
            }
         }
         availableSquares.push(new int[]{a+k, b+l});
      }
      for(int k = 1; k < 8; k++){
         l = k;
         if(a-k < 0 || b-l < 0){
            break;
         }
         if(board[a-k][b-l] != null){
            availableSquares.push(new int[]{a-k, b-l});
            if(!board[a-k][b-l].name.equals("King") || board[a-k][b-l].col==this.col)
            {
            break;
            }
            else{
               availableSquares.pop();
            }
         }

         availableSquares.push(new int[]{a-k, b-l});
      }
      for(int k = 1; k < 8; k++){
         l = -k;
         if(a+k > 7 || b+l < 0){
            break;
         }
         if(board[a+k][b+l] != null){
            availableSquares.push(new int[]{a+k, b+l});
            if(!board[a+k][b+l].name.equals("King") || board[a+k][b+l].col==this.col)
            {
            break;
            }
            else{
               availableSquares.pop();
            }
            
         }
        
         availableSquares.push(new int[]{a+k, b+l});
      }
      for(int k = 1; k < 8; k++){
         l = -k;
         if(a-k < 0 || b-l > 7){
            break;
         }
         if(board[a-k][b-l] != null){
            availableSquares.push(new int[]{a-k, b-l});
            if(!board[a-k][b-l].name.equals("King") || board[a-k][b-l].col==this.col)
            {
            break;
            }
            else{
               availableSquares.pop();
            }
         }
         availableSquares.push(new int[]{a-k, b-l});
      }
      return availableSquares;
   }
   /**
      Finds all legal moves for the piece.
      Called in method Input of class Chessboard.
      
      @param Piece[][] board     Piece array from which the legal squares are found
      @return availableSquares   Stack of the legal moves for the piece
   */
   public Stack<int[]> getLegalSquares(Piece[][] board)
   {
      availableSquares = new Stack<int[]>();
      int l;
      for(int k = 1; k < 8; k++){
         l = k;
         if(a+k > 7 || b+l > 7){//checks diagonal
            break;
         }
         if(board[a+k][b+l] != null && board[a+k][b+l].col == -1*(col-1))//if there is a piece of opposite color
         {
            if(!board[a+k][b+l].name.equals("King"))//and it is not a king
            {
               availableSquares.push(new int[]{a+k, b+l});//add square
            }
            else
            {
               InfoPanel.setInfoMessageText("The king is in check.");
            }
            break;
         }
         else if(board[a+k][b+l] != null){//nothing there
            break;
         }
         availableSquares.push(new int[]{a+k, b+l});//add square
      }
      for(int k = 1; k < 8; k++){
         l = k;
         if(a-k < 0 || b-l < 0){//checks diagonal
            break;
         }
         if(board[a-k][b-l] != null && board[a-k][b-l].col == -1*(col-1))//if there is a piece of opposite color
         {
            if(!board[a-k][b-l].name.equals("King"))//and it is not a king
            {
               availableSquares.push(new int[]{a-k, b-l});//add square
            }
            else
            {
               InfoPanel.setInfoMessageText("The king is in check.");
            }
            break;
         }
         else if(board[a-k][b-l] != null){//nothing there
            break;
         }
         availableSquares.push(new int[]{a-k, b-l});//add square
      }
      for(int k = 1; k < 8; k++){
         l = -k;
         if(a+k > 7 || b+l < 0){//checks diagonal
            break;
         }
         if(board[a+k][b+l] != null && board[a+k][b+l].col == -1*(col-1))//if there is a piece of opposite color
         {
            if(!board[a+k][b+l].name.equals("King"))//and it is not a king
            {
               availableSquares.push(new int[]{a+k, b+l});//add square
            }
            else
            {
               InfoPanel.setInfoMessageText("The king is in check.");
            }
            break;
         }
         else if(board[a+k][b+l] != null){//nothing there
            break;
         }
         availableSquares.push(new int[]{a+k, b+l});//add square
      }
      for(int k = 1; k < 8; k++){
         l = -k;
         if(a-k < 0 || b-l > 7)//checks diagonal
         {
            break;
         }
         if(board[a-k][b-l] != null && board[a-k][b-l].col == -1*(col-1))//if there is a piece of opposite color
         {
            if(!board[a-k][b-l].name.equals("King"))//and it is not a king
            {
               availableSquares.push(new int[]{a-k, b-l});//add square
            }
            else
            {
               InfoPanel.setInfoMessageText("The king is in check.");
            }
            break;
         }
         else if(board[a-k][b-l] != null)//nothing there
         {
            break;
         }
         availableSquares.push(new int[]{a-k, b-l});//add square
      }
      return availableSquares;
   }  
}