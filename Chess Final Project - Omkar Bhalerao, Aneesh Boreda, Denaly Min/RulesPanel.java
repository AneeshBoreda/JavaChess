import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
   RulesPanel class to instantiate RulesPanels
      
   @author  Omkar Bhalerao
   @author  Aneesh Boreda
   @author  Denaly Min
   @version 7 June 17
*/
public class RulesPanel extends JPanel
{
   private JTextArea label;
   private JButton nextButton, prevButton;
   private JLabel textArea;
   private ImageIcon image;
   private String x;
   private String[] text;
   private int page = 0;
   /**
      Create rules panel
           
   */
   public RulesPanel() throws IOException
   {   
      setLayout(new FlowLayout());
      text = new String[25];
      //RulesPanelText.txt contains all text for the rules panel
      BufferedReader file = new BufferedReader(new FileReader(new File("RulesPanelText.txt")));
      int count = 0;
      x = "placeholder :)";
      while(x != null){
         x = file.readLine();
         text[count] = x;
         count++;
      }
   
      label = new JTextArea(text[0], 25, 31);//text area that will show all text
      label.setEditable(false);
      label.setLineWrap(true);
      label.setWrapStyleWord(true);
      label.setFont(new Font("Arial", 1, 20));
      label.setBackground(getBackground());
      add(label);
      
      textArea = new JLabel();
      add(textArea);
      //button that moves back one page
      prevButton = new JButton();
      prevButton.setText("Previous Page");
      prevButton.addActionListener(new PrevListener());
      add(prevButton);
      prevButton.setVisible(false);
      //button that moves forward one page
      nextButton = new JButton();
      nextButton.setText("Next Page");
      nextButton.addActionListener(new NextListener());
      add(nextButton);
      
      image = new ImageIcon("ChessPieceImages/ChessStart.png");
   }
   /**
      Handles previous button
     
   */
   private class PrevListener implements ActionListener{
      public PrevListener(){
      
      }
      public void actionPerformed(ActionEvent e){
         page -= 1;
         doStuff(label, page);
         nextButton.setVisible(true);
         if(page <= 0){
            prevButton.setVisible(false);
         }
         if(page == 3){
            label.setRows(6);
            textArea.setIcon(image);
         }
         else{
            label.setRows(25);
            textArea.setIcon(null);
         }
      }
   }
   /**
      Handles next button     
   */
   private class NextListener implements ActionListener{
      public NextListener(){
      
      }
      public void actionPerformed(ActionEvent e){
         page += 1;
         doStuff(label, page);
         prevButton.setVisible(true);
         if(page >= 15){
            nextButton.setVisible(false);
         }
         if(page == 3){
            label.setRows(6);
            textArea.setIcon(image);
         }
         else{
            label.setRows(25);
            textArea.setIcon(null);
         }
      }
   }
   /**
      Makes text split properly     
   */
   public void doStuff(JTextArea JTA, int line) {
      JTA.setText("");
      String[] arr = text[page].split("POTATO");
      for(int loop = 0; loop < arr.length; loop++) {
         JTA.append(arr[loop]);
         JTA.append("\n");
      }
   }
}