
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.html.HTMLEditorKit;

public class Test extends JFrame
{
  public Test()
  {
    
    
   setSize(400, 400);
 setLayout(null);
   
    
  }
  
  public static void main(String[] args) {
    JPanel panel = new JPanel();
    //panel .setSize(300,300);
    panel.setBackground(Color.BLACK);
    
    JTextPane text = new JTextPane();
    text.setEditable(false);
    //text.setFont(new Font("Vivaldi",Font.BOLD,20));
    text.setBackground(Color.BLACK);
    //text.setSize(200,100);
    text.setEditorKit(new HTMLEditorKit());
    text.setText("<html><font size=\"8\" face=\"Vivaldi\"color=\"white\">WORLD</font></html>");
    
    Test t = new Test();
    //t.setBackground(Color.RED);
    panel.add(text);
    t.add(panel);
    panel.setBounds(0, 0,300, 300);
    text.setBounds(100,100,100,100);
    panel.setVisible(true);
    text.setVisible(true);
    t.setVisible(true);
    
  }
}
