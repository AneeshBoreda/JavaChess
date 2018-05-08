import javax.swing.*;//JFrame
import java.io.*;//IOException

/**
   The ChessDriver class creates the user interface and puts everything together.

   @author  Omkar Bhalerao
   @author  Aneesh Boreda
   @author  Denaly Min
   @version 7 June 2017
*/
public class ChessDriver
{
   /**
      Initializes two JFrames, one for rules and one for the game itself.
      Upon each JFrame a panel is initialized, these can be found in the ChessPanel and RulesPanel classes.
      
      @param args
      @throws IOException  If an input or output exception occurred
   */
   public static void main(String[] args) throws IOException
   {
      JFrame frame = new JFrame("Chess");//chess panel
      frame.setSize(496, 496);
      frame.setLocation(0, 0);//top left corner
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closing this window ends the program
      frame.setContentPane(new ChessPanel());
      frame.setVisible(true);
      JFrame rules = new JFrame("Rules");//rules panel
      rules.setSize(600, 700);
      rules.setLocation(525, 0);//to the right of chess game
      rules.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//non-essential part so does not end program when closed
      rules.setContentPane(new RulesPanel());
      rules.setVisible(true);
      JFrame info = new JFrame("Info");//info panel
      info.setSize(520, 200);
      info.setLocation(0, 500);//under chess game
      info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closing this window ends the program
      info.setContentPane(new InfoPanel());
      info.setVisible(true);
   }
}