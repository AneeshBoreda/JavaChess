import java.util.*;
import javax.swing.*;
/**
   Pawn class for instantiating Pawns
      
   @author  Omkar Bhalerao
   @author  Aneesh Boreda
   @author  Denaly Min
   @version 7 June 17
   */
public class Pawn extends Piece{
   
   public Stack<int[]> availableSquares;
   public ImageIcon piece;
   
   /**
      Initializes position and color, as well as other fields.
      
      @param a       row of piece
      @param b       column of piece
      @param color   color of piece 
   */
   public Pawn(int a, int b, int color){
      super(a,b,color);
      firstMove = true;
      justMoved = false;
      enPassant = false;
      name = "Pawn";
      if(color == 0){
         this.piece=new ImageIcon("ChessPieceImages/Blue2P.png");
      }
      else{
         this.piece=new ImageIcon("ChessPieceImages/BlackP.png");
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
      this.name="Pawn";
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
      availableSquares = new Stack<>();
      if(col==0)
      {
         if(b<7 )
         {
            availableSquares.push(new int[]{a-1,b+1});
         }
         if(b>0 )
         {
            availableSquares.push(new int[]{a-1,b-1});
         }
      } 
      else if(col==1)
      {
         if(b<7 )
         {
            availableSquares.push(new int[]{a+1,b+1});
         }
         if(b>0 )
         {
            availableSquares.push(new int[]{a+1,b-1});
         }
      } 
      return availableSquares;
   }
   public void move(int a, int b, Piece[][] board)   {  
      if(lastMoved[0]!=-1)
      {
         board[lastMoved[0]][lastMoved[1]].justMoved=false;
      }
      Piece x = board[this.a][this.b];
      board[this.a][this.b]=null;
      board[a][b]=x;
      int oldA=this.a;  
      int oldB=this.b;    
      this.a=a;
      this.b=b;
      if(board[a][b].enPassant){
         board[oldA][b] = null;
      }
      if(this.col==0 && this.a==0)
      {
         InfoPanel.Promotion(0, this.a, this.b);
         InfoPanel.setInfoMessageText("Please select a piece to promote to.");
         if(board==Chessboard.board)
         {
            Chessboard.promotion=true;
         }
      }
      else if(this.col==1 && this.a==7)
      {
         InfoPanel.Promotion(1, this.a, this.b);
         InfoPanel.setInfoMessageText("Please select a piece to promote to.");
         if(board==Chessboard.board)
         {
            Chessboard.promotion=true;
         }
      }
      else if(firstMove)
      {
         justMoved=true;
         firstMove=false;
      }
      lastMoved[0]=a;
      lastMoved[1]=b;
   }
   /**
      Finds all legal moves for the piece.
      Called in method Input of class Chessboard.
      
      @param board               the board which the pieces are on
      @return availableSquares   a Stack of all the squares it can move to expressed as int[]
   */
   public Stack<int[]> getLegalSquares(Piece[][] board){
      enPassant = false;
      availableSquares=new Stack<>();
      if (col==0){
         if(board[a-1][b]==null)
         {
            availableSquares.push(new int[]{a-1,b});
            if (firstMove&&board[a-2][b]==null){
               availableSquares.push(new int[]{a-2,b});
            }
         }
         if(b<7 && board[a-1][b+1]!=null)
         {
            if(board[a-1][b+1].col==1)
            {
               if(!board[a-1][b+1].name.equals("King"))
               {
                  availableSquares.push(new int[]{a-1, b+1});
               }
               else
               {
                  InfoPanel.setInfoMessageText("The king is in check.");
               }
            }
         }
         if(b>0 && board[a-1][b-1]!=null)
         {
            if(board[a-1][b-1].col==1)
            {
               if(!board[a-1][b-1].name.equals("King"))
               {
                  availableSquares.push(new int[]{a-1, b-1});
               }
               else
               {
                  InfoPanel.setInfoMessageText("The king is in check.");
               }
            }
         }
         if(a==3  && b < 7){
            if(board[a][b+1] != null){
               if(board[a][b+1].col == 1 && board[a][b+1].name.equals("Pawn") && 
               board[a][b+1].justMoved == true){
                  availableSquares.push(new int[] {a-1,b+1});
                  enPassant = true;
               }
            }
         }
         if(a==3 && b > 0){
            if(board[a][b-1] != null){
            
               if(board[a][b-1].col == 1 && board[a][b-1].name.equals("Pawn") && 
               board[a][b-1].justMoved == true){
                  availableSquares.push(new int[] {a-1,b-1});
                  enPassant = true;
               }
            }
         }
         
      }
      else{
         if(board[a+1][b]==null)
         {
            availableSquares.push(new int[]{a+1,b});
            if (firstMove&&board[a+2][b]==null)
            {
               availableSquares.push(new int[]{a+2,b});
            }
         }
         if(b<7 && board[a+1][b+1]!=null)
         {
            if(board[a+1][b+1].col==0)
            {
               if(!board[a+1][b+1].name.equals("King"))
               {
                  availableSquares.push(new int[]{a+1, b+1});
               }
               else
               {
                  InfoPanel.setInfoMessageText("The king is in check.");
               }
            }            
         }
         if(b>0 && board[a+1][b-1]!=null)
         { 
            if(board[a+1][b-1].col==0)
            {
               if(!board[a+1][b-1].name.equals("King"))
               {
                  availableSquares.push(new int[]{a+1, b-1});
               }
               else
               {
                  InfoPanel.setInfoMessageText("The king is in check.");
               }
            }
         }
         if(a==4  && b < 7){
            if(board[a][b+1] != null)
            {
               if(board[a][b+1].col == 0 && board[a][b+1].name.equals("Pawn") && 
               board[a][b+1].justMoved == true){
                  availableSquares.push(new int[] {a+1,b+1});
                  enPassant = true;
               }
            }
            
         }
         if(a==4 && b > 0){
            if(board[a][b-1] != null)
            {
               if(board[a][b-1].col == 0 && board[a][b-1].name.equals("Pawn") && 
               board[a][b-1].justMoved == true){
                  availableSquares.push(new int[] {a+1,b-1});
                  enPassant = true;
               }
            }
         }
      }
      
      return availableSquares;
   }
}
      
