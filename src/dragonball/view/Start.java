package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Start extends JPanel {
  public Start() {
    
    ImageIcon icon = new ImageIcon("start wallpaper.jpg");
    Image scaledImage = (icon.getImage()).getScaledInstance(1370, 730, Image.SCALE_DEFAULT);
    ImageIcon img = new ImageIcon(scaledImage);
    JLabel background = new JLabel(img);
    setSize(1370, 730);
    background.setSize(1370, 730);
    //background.setOpaque(false);
    setLayout(new BorderLayout());
   
    
    background.setBounds(0,0,1370,730);
    
    JButton loadGame = new JButton(new ImageIcon("button-load.png"));
     //loadGame.setSize(60,20);
    loadGame.setBorder(BorderFactory.createEmptyBorder());
    loadGame.setContentAreaFilled(false);
    // loadGame.setOpaque(false);
    add(loadGame);
    loadGame.setBounds(60, 550, 350, 120);
    
   

    JButton newGame = new JButton(new ImageIcon("button-new-game.png"));
    //newGame.setSize(60, 20);
    newGame.setBorder(BorderFactory.createEmptyBorder());
    newGame.setContentAreaFilled(false);
    newGame.setOpaque(false);

    add(newGame);
    newGame.setBounds(60,400,350,120);
    
    add(background);

    // background.setVisible(true);
     newGame.setVisible(true);
     loadGame.setVisible(true);

  }
  
  
 
  public static void main(String[] args) {
    JFrame f = new JFrame();
    f.setSize(1370, 730);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     //f.setLayout(null);
    Start s = new Start();

    f.add(s);
    f.setVisible(true);
  }
}
