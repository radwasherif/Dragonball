package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartView extends JPanel {
  private JButton newGame;
  private JButton loadGame;

  public StartView() {

    ImageIcon icon = new ImageIcon("resources/start-dragon.jpg");
    Image scaledImage = (icon.getImage()).getScaledInstance(1370, 730, Image.SCALE_DEFAULT);
    ImageIcon img = new ImageIcon(scaledImage);
    JLabel background = new JLabel(img);
    setSize(1370, 730);
    background.setSize(1370, 730);
    background.setOpaque(false);
    setLayout(new BorderLayout());
   
    
   background.setBounds(0,0,1370,730);
//    
     loadGame = new JButton(new ImageIcon("resources/button-load.png"));
     loadGame.setActionCommand("load");
     //loadGame.setSize(60,20);
    loadGame.setBorder(BorderFactory.createEmptyBorder());
    loadGame.setContentAreaFilled(false);
    // loadGame.setOpaque(false);
    add(loadGame);
    loadGame.setBounds(1000, 270, 350, 120);
    
   

    newGame = new JButton(new ImageIcon("resources/button-new-game.png"));
    newGame.setActionCommand("new"); 
    //newGame.setSize(60, 20);
    newGame.setBorder(BorderFactory.createEmptyBorder());
    newGame.setContentAreaFilled(false);
    newGame.setOpaque(false);

    add(newGame);
    newGame.setBounds(1000,120,350,120);
    
    add(background);

    // background.setVisible(true);
     newGame.setVisible(true);
     loadGame.setVisible(true);


  } 
  public void setListenersToGameController(ActionListener a) {
     newGame.addActionListener(a); 
     loadGame.addActionListener(a);
     
  }

 
  public JButton getNewGame() {
    return newGame;
  }

  public void setNewGame(JButton newGame) {
    this.newGame = newGame;
  }

  public JButton getLoadGame() {
    return loadGame;
  }

  public void setLoadGame(JButton loadGame) {
    this.loadGame = loadGame;
  }
   
}
