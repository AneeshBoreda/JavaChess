/*****************************************************************
* A Queen is a Piece that can move horizontally, vertically, and 
* diagonally. It captures the same way as it moves.
* @author Omkar Bhalerao, Aneesh Boreda, Denaly Min
* @version 1.0
****************************************************************/

//import necessary libraries
import java.util.*; //Utility Library
import javax.swing.*; // Swing Library

public class Queen extends Piece{
   //define necessary variables
   public Stack<int[]> availableSquares; //lists all available squares
   public ImageIcon piece; //stores the image of the piece
   
   /************************************************************* 
   	* Constructs a Queen at location (a, b) with color color.
      
   	* @param a         initial x-coordinate
      * @param b         initial y-coordinate
      * @param color     color
   	**************************************************************/
   public Queen(int a, int b, int color){
      super(a,b,color); //Constructs a Piece at location (a, b) with color color
      name = "Queen"; //Sets the name to "Queen"
      firstMove = true; //Sets the variable 'firstMove' to true.
      //Chooses the correct image based on the piece's color.
      if(color == 0){
         this.piece=new ImageIcon("ChessPieceImages/Blue2Q.png");
      }
      else{
         this.piece=new ImageIcon("ChessPieceImages/BlackQ.png");
      }
   }
   
   /************************************************************* 
   	* Clones a piece.
   	* @param x    initial piece
   	**************************************************************/
   public void clonePiece(Piece x)
   {
      //Sets all initial piece values to the clone's piece's values
      this.a = x.a;
      this.b = x.b;
      this.firstMove=x.firstMove;
      this.col=x.col;
      this.piece=x.piece;
      this.name="Queen";
      this.justMoved = x.justMoved;
   }
   
   /************************************************************* 
   	* Returns the image of the piece.
   	* @return piece    image of piece
   	**************************************************************/
   public ImageIcon getImage(){
      return piece; //Returns the image of the piece
   }
   
   /************************************************************* 
   	* Gets the squares a piece attacks.
   	* @param board              Chessboard which the piece is on
      * @return availableSquares  Stack of available squares
   	**************************************************************/
   public Stack<int[]> getAttackedSquares(Piece[][] board){
      availableSquares=new Stack<>(); //Stack of available squares
      int l;
      //checks one diagonal
      for(int k = 1; k < 8; k++){
         l = k;
         //if it goes off the board, break
         if(a+k > 7 || b+l > 7){
            break;
         }
         //if there is a piece on the square
         if(board[a+k][b+l] != null ){
            availableSquares.push(new int[]{a+k, b+l}); //initially pushes it into stack
            
            //if it is of same color and not a king, keep it in the stack and break
            if(!board[a+k][b+l].name.equals("King") || board[a+k][b+l].col==this.col)
            {
               break;
            }
            //if it isn't, pop it and keep going
            else{
               availableSquares.pop();
            }
         }
         //push the square into the stack
         availableSquares.push(new int[]{a+k, b+l});
      }
      //checks one diagonal
      for(int k = 1; k < 8; k++){
         l = k;
         //if it goes off the board, break
         if(a-k < 0 || b-l < 0){
            break;
         }
         //if there is a piece on the square
         if(board[a-k][b-l] != null){
            availableSquares.push(new int[]{a-k, b-l}); //initially pushes into stack
            //if it is of same color and not a king, keep it in the stack and break
            if(!board[a-k][b-l].name.equals("King") || board[a-k][b-l].col==this.col)
            {
               break;
            }
            //if it isn't, pop it and keep going
            else{
               availableSquares.pop();
            }
         }
         //push the square into the stack
         availableSquares.push(new int[]{a-k, b-l});
      }
      //Checks one diagonal
      for(int k = 1; k < 8; k++){
         l = -k;
         //if it goes off the board, break
         if(a+k > 7 || b+l < 0){
            break;
         }
         //if there is a piece on the square
         if(board[a+k][b+l] != null){
            availableSquares.push(new int[]{a+k, b+l}); //initially pushes into stack
            //if it is of same color and not a king, keep it in the stack and break
            if(!board[a+k][b+l].name.equals("King") || board[a+k][b+l].col==this.col)
            {
               break;
            }
            //if it isn't, pop it and keep going
            else{
               availableSquares.pop();
            }
            
         }
         //push the square into the stack
         availableSquares.push(new int[]{a+k, b+l});
      }
      //Checks one diagonal
      for(int k = 1; k < 8; k++){
         l = -k;
         //if it goes off the board, break
         if(a-k < 0 || b-l > 7){
            break;
         }
         //if there is a piece on the square
         if(board[a-k][b-l] != null){
            availableSquares.push(new int[]{a-k, b-l}); //initially pushes into stack
            //if it is of same color and not a king, keep it in the stack and break
            if(!board[a-k][b-l].name.equals("King") || board[a-k][b-l].col==this.col)
            {
               break;
            }
            //if it isn't, pop it and keep going
            else{
               availableSquares.pop();
            }
         }
         //push the square into the stack
         availableSquares.push(new int[]{a-k, b-l});
      }
      //Checks one column
      for(int k = 1; k < 8; k++){
         //if it goes off the board, break
         if(a+k > 7){
            break;
         }
         //if there is a piece on the square
         if(board[a+k][b] != null){
            availableSquares.push(new int[]{a+k, b}); //initially pushes into stack
            //if it is of same color and not a king, keep it in the stack and break
            if(!board[a+k][b].name.equals("King") || board[a+k][b].col==this.col)
            {
               break;
            }
            //if it isn't, pop it and keep going
            else{
               availableSquares.pop();
            }
         
         }
         //push the square into the stack
         availableSquares.push(new int[]{a+k, b});
      }
      //Checks one column
      for(int k = 1; k < 8; k++){
         //if it goes off the board, break
         if(a-k < 0){
            break;
         }
         //if there is a piece on the square
         if(board[a-k][b] != null){
            availableSquares.push(new int[]{a-k, b});//initially pushes into stack
            //if it is of same color and not a king, keep it in the stack and break

            if(!board[a-k][b].name.equals("King")|| board[a-k][b].col==this.col)
            {
               break;
            }
            //if it isn't, pop it and keep going
            else{
               availableSquares.pop();
            }
         }
         //push the square into the stack
         availableSquares.push(new int[]{a-k, b});
      }
      //Checks one row
      for(int k = 1; k < 8; k++){
         //if it goes off the board, break
         if(b+k > 7){
            break;
         }
         //if there is a piece on the square
         if(board[a][b+k] != null){
            availableSquares.push(new int[]{a, b+k}); //initially pushes into stack
            //if it is of same color and not a king, keep it in the stack and break
            if(!board[a][b+k].name.equals("King")|| board[a][b+k].col==this.col)
            {
               break;
            }
            //if it isn't, pop it and keep going
            else{
               availableSquares.pop();
            }
         }
         //push the square into the stack
         availableSquares.push(new int[]{a, b+k});
      }
      //Checks one row
      for(int k = 1; k < 8; k++){
         //if it goes off the board, break
         if(b-k < 0){
            break;
         }
         //if there is a piece on the square
         if(board[a][b-k] != null){
            availableSquares.push(new int[]{a, b-k}); //initially pushes into stack
            //if it is of same color and not a king, keep it in the stack and break
            if(!board[a][b-k].name.equals("King")|| board[a][b-k].col==this.col)
            {
               break;
            }
            //if it isn't, pop it and keep going
            else{
               availableSquares.pop();
            }
         }
         //push the square into the stack
         availableSquares.push(new int[]{a, b-k});
      }
      
   
      
      return availableSquares; //returns all available squares
   
   }
   /************************************************************* 
   	* Gets the squares a piece can move to.
   	* @param board              Chessboard which the piece is on
      * @return availableSquares  Stack of available squares
   	**************************************************************/

   public Stack<int[]> getLegalSquares(Piece[][] board){
      availableSquares = new Stack<int[]>(); //Creates stack of available squares
      int l;
      //Checks one diagonal
      for(int k = 1; k < 8; k++){
         l = k;
         //if it goes off the board, break
         if(a+k > 7 || b+l > 7){
            break;
         }
         //if there is a piece on the square and it is of the opposite color
         if(board[a+k][b+l] != null && board[a+k][b+l].col == -1*(col-1)){
            //if it is not a king
            if(!board[a+k][b+l].name.equals("King"))
            {
               //push the square into availableSquares
               availableSquares.push(new int[]{a+k, b+l});
            }
            //if it is a king
            else
            {
               //the king is in check
               InfoPanel.setInfoMessageText("The king is in check.");
            }
            break;
         }
         //if the piece is of the same color, break
         else if(board[a+k][b+l] != null){
            break;
         }
         //push the square into availableSquares
         availableSquares.push(new int[]{a+k, b+l});
      }
      //Checks one diagonal
      for(int k = 1; k < 8; k++){
         l = k;
         //if it goes off the board, break
         if(a-k < 0 || b-l < 0){
            break;
         }
         //if there is a piece on the square and it is of the opposite color
         if(board[a-k][b-l] != null && board[a-k][b-l].col == -1*(col-1)){
            //if it is not a king
            if(!board[a-k][b-l].name.equals("King"))
            {
               //push the square into availableSquares
               availableSquares.push(new int[]{a-k, b-l});
            }
            //if it is a king
            else
            {
               //the king is in check
               InfoPanel.setInfoMessageText("The king is in check.");
            }
            break;
         }
         //if the piece is of the same color, break
         else if(board[a-k][b-l] != null){
            break;
         }
         //push the square into availableSquares
         availableSquares.push(new int[]{a-k, b-l});
      }
      //Checks one diagonal
      for(int k = 1; k < 8; k++){
         l = -k;
         //if it goes off the board, break
         if(a+k > 7 || b+l < 0){
            break;
         }
         //if there is a piece on the square and it is of the opposite color
         if(board[a+k][b+l] != null && board[a+k][b+l].col == -1*(col-1)){
            //if it is not a king
            if(!board[a+k][b+l].name.equals("King"))
            {
               //push the square into availableSquares
               availableSquares.push(new int[]{a+k, b+l});
            }
            //if it is a king
            else
            {
               //the king is in check
               InfoPanel.setInfoMessageText("The king is in check.");
            }
            break;
         }
         //if the piece is of the same color, break
         else if(board[a+k][b+l] != null){
            break;
         }
         //push the square into availableSquares
         availableSquares.push(new int[]{a+k, b+l});
      }
      //Checks one diagonal
      for(int k = 1; k < 8; k++){
         l = -k;
         //if it goes off the board, break
         if(a-k < 0 || b-l > 7){
            break;
         }
         //if there is a piece on the square and it is of the opposite color
         if(board[a-k][b-l] != null && board[a-k][b-l].col == -1*(col-1)){
            //if it is not a king
            if(!board[a-k][b-l].name.equals("King"))
            {
               //push the square into availableSquares
               availableSquares.push(new int[]{a-k, b-l});
            }
            //if it is a king
            else
            {
               //the king is in check
               InfoPanel.setInfoMessageText("The king is in check.");
            }
            break;
         }
         //if the piece is of the same color, break
         else if(board[a-k][b-l] != null){
            break;
         }
         //push the square into availableSquares
         availableSquares.push(new int[]{a-k, b-l});
      }
      //Checks one column
      for(int k = 1; k < 8; k++){
         //if it goes off the board, break
         if(a+k > 7){
            break;
         }
         //if there is a piece on the square and it is of the opposite color
         if(board[a+k][b] != null && board[a+k][b].col == -1*(col-1)){
            //if it is not a king
            if(!board[a+k][b].name.equals("King"))
            {
               //push the square into availableSquares
               availableSquares.push(new int[]{a+k, b});
            }
            //if it is a king
            else
            {
               //the king is in check
               InfoPanel.setInfoMessageText("The king is in check.");
            }
            break;
         }
         //if the piece is of the same color, break
         else if(board[a+k][b] != null){
            break;
         }
         //push the square into availableeSquares
         availableSquares.push(new int[]{a+k, b});
      }
      //Checks one column
      for(int k = 1; k < 8; k++){
         //if it goes off the board, break
         if(a-k < 0){
            break;
         }
         //if there is a piece on the square and it is of the opposite color
         if(board[a-k][b] != null && board[a-k][b].col == -1*(col-1)){
            //if it is not a king
            if(!board[a-k][b].name.equals("King"))
            {
               //push the square into availableSquares
               availableSquares.push(new int[]{a-k, b});
            }
            //if it is a king
            else
            {
               //the king is in check
               InfoPanel.setInfoMessageText("The king is in check.");
            }
            break;
         }
         //if the piece is of the same color, break
         else if(board[a-k][b] != null){
            break;
         }
         //push the square into availableSquares
         availableSquares.push(new int[]{a-k, b});
      }
      //Checks one row
      for(int k = 1; k < 8; k++){
         //if it goes off the board, break
         if(b+k > 7){
            break;
         }
         //if there is a piece on the square and it is of the opposite color
         if(board[a][b+k] != null && board[a][b+k].col == -1*(col-1)){
            //if it is not a king
            if(!board[a][b+k].name.equals("King"))
            {
               //push the square into availableSquares
               availableSquares.push(new int[]{a, b+k});
            }
            //if it is a king
            else
            {
               //the king is in check
               InfoPanel.setInfoMessageText("The king is in check.");
            }
            break;
         }
         //if the piece is of the same color, break
         else if(board[a][b+k] != null){
            break;
         }
         //push the square into availableSquares
         availableSquares.push(new int[]{a, b+k});
      }
      //Checks one row
      for(int k = 1; k < 8; k++){
         //if it goes off the board, break
         if(b-k < 0){
            break;
         }
         //if there is a piece on the square and it is of the opposite color
         if(board[a][b-k] != null && board[a][b-k].col == -1*(col-1)){
            //if it is not a king
            if(!board[a][b-k].name.equals("King"))
            {
               //push the square into availableSquares
               availableSquares.push(new int[]{a, b-k});
            }
            //if it is a king
            else
            {
               //if it is a king
               InfoPanel.setInfoMessageText("The king is in check.");
            }
            break;
         }
         //if the piece is of the same color, break
         else if(board[a][b-k] != null){
            break;
         }
         //push the square into available squares
         availableSquares.push(new int[]{a, b-k});
      }
      
      return availableSquares; //returns availableSquares
   }
}