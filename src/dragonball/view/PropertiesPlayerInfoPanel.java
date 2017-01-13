package dragonball.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.html.HTMLEditorKit;

public class PropertiesPlayerInfoPanel extends JPanel
{
  static final int WIDTH = 900;
  static final int HEIGHT = 50;
 
  
  public PropertiesPlayerInfoPanel()
  {
    setSize(WIDTH,HEIGHT);
    setLayout(null);
    setBackground(Color.BLACK);
    
    
    setVisible(true);
  }


 
  public void displayPlayerInfo(String playerName, int playerExplordMaps)
  {
    JTextArea player = new JTextArea(playerName);
    player.setFont(new Font("Blackadder ITC", Font.BOLD,46));
    player.setBackground(Color.BLACK);
    player.setForeground(Color.WHITE);
    player.setEditable(false);
    player.setOpaque(false);
    
    add(player);
    player.setBounds(0, 0, 440, 50);
    player.setVisible(true);
    
    
    JTextArea exploredMaps = new JTextArea("explored maps: "+playerExplordMaps);
    exploredMaps.setFont(new Font("Yu Gothic Light",Font.PLAIN,34));
    exploredMaps.setBackground(Color.BLACK);
    exploredMaps.setForeground(Color.WHITE);
    exploredMaps.setEditable(false);
    exploredMaps.setOpaque(false);
    
    add(exploredMaps);
    exploredMaps.setBounds(485, 0, 250, 50);
    exploredMaps.setVisible(true);
    
    
  }
  
}
