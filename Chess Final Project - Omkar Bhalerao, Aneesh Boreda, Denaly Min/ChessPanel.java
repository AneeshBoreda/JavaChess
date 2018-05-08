/**********
 * A ChessPanel is the panel we used to create our main chessboard. The ChessPanel can
 * update itself, reset the colors of squares, and has a Listener for all the buttons
 * which performs a certain action upon being pressed.
 
 * @author Omkar Bhalerao, Aneesh Boreda, Denaly Min
 * @version 1.0
 **********/
 
//import necessary libraries
import javax.swing.*; //Swing Library
import java.awt.*; //Abstract Window Toolkit Library
import java.awt.event.*; //Abstract Window Toolkit Library
import java.io.*; //Input-Output Library
import java.util.*; //Utilitiy Library


public class ChessPanel extends JPanel
{
   //defines private variables
   private static Color col; //default color of square
   public static Color selectedCol; //color or selected square
   public static Color legalCol; //color of legal squares
   public static JButton[][] board; //the chessboard itself
   public static Piece[][] pieces; //array of pieces showing where everything is
   
   /************************************************************* 
   	* Constructs a ChessPanel.
   	**************************************************************/
   public ChessPanel()
   {  pieces=new Piece[8][8]; //defines matrix of pieces
      setLayout(new GridLayout(8, 8)); //makes the panel have a GridLayout, which we need for the chessboard
      board = new JButton[8][8]; //defines matrix of JButtons
      selectedCol=new Color(255,189,66); //defines the color of the selected square (orange)
      legalCol=new Color(0,255,0); //defines the color of a legal square (green)
      
      //This section creates the chessboard with all the chess pieces in the default position
      for(int i = 0; i < 8; i++){
         //Black pawns
         pieces[1][i]=new Pawn(1,i,1);
      }
      for(int i = 0; i < 8; i++){
         //White pawns
         pieces[6][i]=new Pawn(6,i,0);
      }
      //Other white pieces
      pieces[7][0]= new Rook(7,0,0);
      pieces[7][1]= new Knight(7,1,0);
      pieces[7][2]= new Bishop(7,2,0);
      pieces[7][3]= new Queen(7,3,0);
      pieces[7][4]= new King(7,4,0);
      pieces[7][5]= new Bishop(7,5,0);
      pieces[7][6]= new Knight(7,6,0);
      pieces[7][7]= new Rook(7,7,0);
      //Other black pieces
      pieces[0][0]= new Rook(0,0,1);
      pieces[0][1]= new Knight(0,1,1);
      pieces[0][2]= new Bishop(0,2,1);
      pieces[0][3]= new Queen(0,3,1);
      pieces[0][4]= new King(0,4,1);
      pieces[0][5]= new Bishop(0,5,1);
      pieces[0][6]= new Knight(0,6,1);
      pieces[0][7]= new Rook(0,7,1);
      
      //Updates Chessboard.board
      Chessboard.board=pieces;
      
      //This section creates the matrix of JButtons.
      for(int i = 0; i < 8; i++){
         for(int j = 0; j < 8; j++){
            board[i][j]=new JButton(); //adds JButton
            board[i][j].addActionListener(new Listener(i,j)); //adds ActionListener to JButton
            board[i][j].setFocusPainted(false); //No focus ring
            
            //Assigns squares a color based on their posiiton
            if((i+j)%2==0){
               col=Color.WHITE;
            }
            else{
               col=Color.BLACK;
            }
            
            //If the square contains a piece, assign an ImageIcon to the corresponding JButton.
            if (pieces[i][j]!=null)
            {
               ImageIcon icon= Chessboard.getPiece(i,j).getImage();
               board[i][j].setIcon(icon);
            }
            board[i][j].setBackground(col); //Sets background of JButtons.
            
            add(board[i][j]); //Adds the array of JButtons to the panel
         }      
      }
   }
   /************************************************************* 
   	* Updates the board.
   	**************************************************************/
   public static void updateBoard()
   {  
      Stack<int[]> k; //defines a stack for the legal squares
      
      //loops through the entire chessboard  
      for(int i = 0; i < 8; i++){
         for(int j = 0; j < 8; j++){
            //Sets icon for piece if it is not null
            if (pieces[i][j]!=null)
            {
               ImageIcon icon= Chessboard.getPiece(i,j).getImage();
               
               board[i][j].setIcon(icon);
              //Adds its attackedSquares to the master list of squares attacked by White
              if(pieces[i][j].col == 0 && !(pieces[i][j].name.equals("King"))){
                  k = pieces[i][j].getAttackedSquares(pieces);
                  int p = k.size();
                  for(int l = 0; l < p; l++){
                     Chessboard.whiteSquares.add(k.pop());
                  }
               //Adds its attackedSquares to the master list of squares attacked by Black
               }else if(pieces[i][j].col == 1 && !(pieces[i][j].name.equals("King"))){
                  k = pieces[i][j].getAttackedSquares(pieces);
                  int p = k.size();
                  for(int l = 0; l < p; l++){
                     Chessboard.blackSquares.add(k.pop());
                  }
               //Adds its attackedSquares to the master list of squares attacked by White
               }else if(pieces[i][j].col == 0 && pieces[i][j].name.equals("King")){
                  Chessboard.whiteSquares.add(new int[]{i-1,j-1});
                  Chessboard.whiteSquares.add(new int[]{i-1,j});
                  Chessboard.whiteSquares.add(new int[]{i-1,j+1});
                  Chessboard.whiteSquares.add(new int[]{i,j-1});
                  Chessboard.whiteSquares.add(new int[]{i,j+1});
                  Chessboard.whiteSquares.add(new int[]{i+1,j-1});
                  Chessboard.whiteSquares.add(new int[]{i+1,j});
                  Chessboard.whiteSquares.add(new int[]{i+1,j+1});
               }
               //Adds its attackedSquares to the master list of squares attacked by Black
               else if(pieces[i][j].col == 1 && pieces[i][j].name.equals("King")){
                  Chessboard.blackSquares.add(new int[]{i-1,j-1});
                  Chessboard.blackSquares.add(new int[]{i-1,j});
                  Chessboard.blackSquares.add(new int[]{i-1,j+1});
                  Chessboard.blackSquares.add(new int[]{i,j-1});
                  Chessboard.blackSquares.add(new int[]{i,j+1});
                  Chessboard.blackSquares.add(new int[]{i+1,j-1});
                  Chessboard.blackSquares.add(new int[]{i+1,j});
                  Chessboard.blackSquares.add(new int[]{i+1,j+1});
            }

               
            }
            //If nothing is on it
            else
            {
               board[i][j].setIcon(null); //remove the icon
            }
         }
      }
      
   }
   /************************************************************* 
   	* Resets the color of a square.
      * @param a       x-coordnate
      * @param b       y-coordinate
   	**************************************************************/
   public static void resetColor(int a, int b)
   {
      //Resets color of the square based on its position on the board
      if((a+b)%2==0){
         col=Color.WHITE;
      }
      else{
         col=Color.BLACK;
      }
      board[a][b].setBackground(col);
       
   
   }  
   /*****************************************************************
	* A Listener is a class which listens for an action performed on a
   * JButton, and performs an action when the button is pressed.
	****************************************************************/
   private class Listener implements ActionListener{
      private int[] pos;
      /************************************************************* 
   	* Constructs a Listener corresponding to the JButton at
      * coordinates (a,b).
      
   	* @param a    x-coordinate
      * @param b    y-coordinate
   	**************************************************************/
      public Listener(int a, int b){
         pos=new int[2];
         pos[0]=a;
         pos[1]=b; 
      }
      
      /************************************************************* 
   	* Defines the action to be performed when the JButton is
      * pressed.
      
      * @param e ActionEvent
   	**************************************************************/
      public void actionPerformed(ActionEvent e)
      {
         
         pieces=Chessboard.Input(pos[0],pos[1]); //Performs the 'Input' function from Chessboard.java
         
      }
   }
   
}
