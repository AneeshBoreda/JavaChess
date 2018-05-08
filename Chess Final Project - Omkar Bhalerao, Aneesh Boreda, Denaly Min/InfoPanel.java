import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
/**
   InfoPanel class to instantiate InfoPanels
      
   @author  Omkar Bhalerao
   @author  Aneesh Boreda
   @author  Denaly Min
   @version 7 June 17
*/

public class InfoPanel extends JPanel {
   private static JButton[] buttons; //buttons for promotion, are hidden at first
   private static JLabel label;//label that shows messages
   private GridBagLayout g;  
   private static volatile int pieceNum;
   private static int a;//row of pawn if there is a promotion
   private static int b;//column of pawn if there is a promotion
   private static int turn;//whether it is white or black's turn
   private static JButton resign;//button to resign, works for either side
   /**
      Initializes InfoPanel, there are no parameters to change
   */
   public InfoPanel() 
   {
      
      buttons=new JButton[4];//four possible pieces to promote, so four buttons
      g=new GridBagLayout();
      setLayout(g);
      GridBagConstraints c = new GridBagConstraints();
      label = new JLabel("No Messages to Show");
      label.setFont(new Font("Arial", 1, 18));
      c.insets=new Insets(0,10,0,10);
      c.gridx=0;
      c.gridy=0;
      c.gridwidth = 4;
      c.weighty=0.5;
      c.weightx=1.0;
      add(label, c);//create label for messages
      c.gridwidth=1;
      c.gridy=1;
      c.weighty=0.5;
      c.weightx=1.0;
      c.fill=GridBagConstraints.BOTH;
      for(int i=0; i<4; i++)//create buttons, keep them hidden for now
      {
         c.gridx=i;
         buttons[i]=new JButton();
         buttons[i].addActionListener(new Listener(i+1));
         buttons[i].setFocusPainted(false);
         buttons[i].setVisible(false);
         add(buttons[i],c);
      }
      //button for resigning, always visible
      resign = new JButton("Resign");
      resign.setFont(new Font("Arial", Font.BOLD, 20));
      resign.addActionListener(new ResignListener());
      resign.setVisible(true);
      add(resign, c);
   }
   /**
      Shows the user an information message
      
      @param st   Message to show
     
   */
   public static void setInfoMessageText(String st)
   {
      label.setForeground(Color.BLUE.brighter());
      label.setText(st);
      
   }
   /**
      Shows the user an error message
      
      @param st   Message to show
     
   */
   public static void setErrorMessageText(String st)
   {
      label.setForeground(Color.RED);
      label.setText(st);
      
   }
   /**
      Handles pawn promotion
      
      @param currentTurn   Which side's turn it is
      @param posA          Row of pawn to be promoted
      @param posB          Column of pawn to be promoted
     
   */
   public static void Promotion(int currentTurn, int posA, int posB) 
   {
      a=posA;
      b=posB;
      turn=currentTurn;
      ImageIcon[] pieceImgs;
      if(turn==0)//if white's turn, show white pieces
      {
         ImageIcon Queen= new Queen(0,0,0).getImage();
         ImageIcon Rook= new Rook(0,0,0).getImage();
         ImageIcon Bishop= new Bishop(0,0,0).getImage();
         ImageIcon Knight= new Knight(0,0,0).getImage();
         pieceImgs= new ImageIcon[]{Queen, Rook, Bishop, Knight};
      }
      else//if black's turn, show black pieces
      {
         ImageIcon Queen= new Queen(0,0,1).getImage();
         ImageIcon Rook= new Rook(0,0,1).getImage();
         ImageIcon Bishop= new Bishop(0,0,1).getImage();
         ImageIcon Knight= new Knight(0,0,1).getImage();
         pieceImgs= new ImageIcon[]{Queen, Rook, Bishop, Knight};
      }
   
      for(int i=0; i<4; i++)//set buttons visible
      {  
         
         buttons[i].setIcon(pieceImgs[i]);
         buttons[i].setVisible(true);
         
      }
      
      
   
   }
   /**
      Resets label when there are no messages to show
     
   */
   public static void reset()
   {
      label.setForeground(Color.BLACK);
      label.setText("No Messages to Show");
      for(int i=0; i<4; i++)
      {
         buttons[i].setVisible(false);
      }
      
   }
   /**
      Promotes pawn to the selected piece
   */
   private class Listener implements ActionListener{
      private int pos;
      public Listener(int a){
         pos=a;
      }
      
      public void actionPerformed(ActionEvent e)
      {
         if (pos==1)
         {
         Chessboard.board[a][b]=new Queen(a,b,turn);
         }
         else if (pos==2)
         {
         Chessboard.board[a][b]=new Rook(a,b,turn);
         }
         else if (pos==3)
         {
         Chessboard.board[a][b]=new Bishop(a,b,turn);
         }
         else if (pos==4)
         {
         Chessboard.board[a][b]=new Knight(a,b,turn);
         }
         
         Chessboard.promotion=false; 
         ChessPanel.updateBoard(); 
         reset();     
      }
   }
   /**
      Used for one side to resign
   */
   private class ResignListener implements ActionListener
   {
      public ResignListener()
      {
         
      }
      //ends program after showing message for resignation
      public void actionPerformed(ActionEvent e)
      {
         Object[] options = {"RESIGN", "CANCEL"};
         int choice;
         ImageIcon winner;
         if(Chessboard.turn == 0)
         {
            choice = JOptionPane.showOptionDialog(null, "Are you sure you want to resign?", "White:", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if(choice == 0)
            {
               winner = new ImageIcon("ChessPieceImages/BlackK.png");
               JOptionPane.showMessageDialog(null, "Black wins!", "Game Over", JOptionPane.WARNING_MESSAGE, winner);
               System.exit(0);
            }
         }
         else
         {
            choice = JOptionPane.showOptionDialog(null, "Are you sure you want to resign?", "Black:", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if(choice == 0)
            {
               winner = new ImageIcon("ChessPieceImages/Blue2K.png");
               JOptionPane.showMessageDialog(null, "White wins!", "Game Over", JOptionPane.WARNING_MESSAGE, winner);
               System.exit(0);
            }
         }
      }
   }
}