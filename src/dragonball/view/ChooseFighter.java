package dragonball.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChooseFighter extends JPanel {
  static final int FIGHTER_WIDTH = 200; 
  static final int FIGHTER_HEIGHT = 400; 
  static final int INTER_SPACE = 50; 
  static final int INIT_SPACE = 75; 
  JButton saiyan_button , frieza_button , namekian_button , earthling_button , majin_button ; 
  
  public ChooseFighter() {
    setSize(Constants.WIDTH, Constants.HEIGHT);
    
    setLayout(null); 
    ImageIcon choose = new ImageIcon("resources/choose-your-fighter.png"); 
    JLabel chooseFighter = new JLabel(choose); chooseFighter.setVisible(true);
    add(chooseFighter); 
    chooseFighter.setBounds((Constants.WIDTH - choose.getIconWidth())/2, 0, choose.getIconWidth(), choose.getIconHeight());
    //setVisible(true); 
    setBackground(Color.BLACK);
    
    ImageIcon i1 = new ImageIcon("resources/earthling-edit.png"); 
    Image earthling = i1.getImage().getScaledInstance(FIGHTER_WIDTH, FIGHTER_HEIGHT, Image.SCALE_DEFAULT); 
     earthling_button = new JButton(new ImageIcon(earthling)); 
    earthling_button.setActionCommand("earthling"); 
    earthling_button.setBorder(BorderFactory.createEmptyBorder());
    earthling_button.setContentAreaFilled(false);
    add(earthling_button); 
    earthling_button.setBounds(INIT_SPACE, choose.getIconHeight()+10, FIGHTER_WIDTH, FIGHTER_HEIGHT);
    ImageIcon j1 = new ImageIcon("resources/earthling-name.gif");
    Image scaledName1 = (j1.getImage()).getScaledInstance(FIGHTER_WIDTH, 60 , Image.SCALE_DEFAULT);
    JLabel earthlingName = new JLabel (new ImageIcon (scaledName1));
    add(earthlingName);
    earthlingName.setBounds(INIT_SPACE - 10, choose.getIconHeight()+10 + FIGHTER_HEIGHT + 10, FIGHTER_WIDTH
        , j1.getIconHeight());
    
    ImageIcon i2 = new ImageIcon("resources/namekian-edit.png"); 
    Image namekian = i2.getImage().getScaledInstance(FIGHTER_WIDTH, FIGHTER_HEIGHT, Image.SCALE_DEFAULT); 
     namekian_button = new JButton(new ImageIcon(namekian)); 
    namekian_button.setActionCommand("namekian"); 
    namekian_button.setBorder(BorderFactory.createEmptyBorder());
    namekian_button.setContentAreaFilled(false);
    add(namekian_button); 
    namekian_button.setBounds(INIT_SPACE + FIGHTER_WIDTH + INTER_SPACE, choose.getIconHeight()+10, FIGHTER_WIDTH, FIGHTER_HEIGHT);
    ImageIcon j2 = new ImageIcon("resources/namekian-name.gif");
    Image scaledName2 = (j2.getImage()).getScaledInstance(FIGHTER_WIDTH, 60 , Image.SCALE_DEFAULT);
    JLabel namekianName = new JLabel (new ImageIcon (scaledName2));
    add(namekianName);
    namekianName.setBounds(INIT_SPACE + FIGHTER_WIDTH - 10 + INTER_SPACE, choose.getIconHeight()+10 + FIGHTER_HEIGHT + 10, FIGHTER_WIDTH
        , j2.getIconHeight());
    
    ImageIcon i3 = new ImageIcon("resources/frieza-edit.PNG"); 
    Image frieza = i3.getImage().getScaledInstance(FIGHTER_WIDTH, FIGHTER_HEIGHT, Image.SCALE_DEFAULT); 
     frieza_button = new JButton(new ImageIcon(frieza)); 
    frieza_button.setActionCommand("frieza"); 
    frieza_button.setBorder(BorderFactory.createEmptyBorder());
    frieza_button.setContentAreaFilled(false);
    add(frieza_button); 
    frieza_button.setBounds(INIT_SPACE + 2*FIGHTER_WIDTH + 2*INTER_SPACE, choose.getIconHeight()+10, FIGHTER_WIDTH, FIGHTER_HEIGHT);
    ImageIcon j3 = new ImageIcon("resources/frieza-name.gif");
    Image scaledName3 = (j3.getImage()).getScaledInstance(FIGHTER_WIDTH, 60 , Image.SCALE_DEFAULT);
    JLabel friezaName = new JLabel (new ImageIcon (scaledName3));
    add(friezaName);
    friezaName.setBounds(INIT_SPACE - 10 + 2*FIGHTER_WIDTH + 2*INTER_SPACE, choose.getIconHeight()+10 + FIGHTER_HEIGHT + 10, FIGHTER_WIDTH
        , j3.getIconHeight());
    
    ImageIcon i4 = new ImageIcon("resources/majin-edit.png"); 
    Image majin = i4.getImage().getScaledInstance(FIGHTER_WIDTH+30, FIGHTER_HEIGHT, Image.SCALE_DEFAULT); 
     majin_button = new JButton(new ImageIcon(majin)); 
    majin_button.setActionCommand("majin"); 
    majin_button.setBorder(BorderFactory.createEmptyBorder());
    majin_button.setContentAreaFilled(false);
    add(majin_button); 
    majin_button.setBounds(INIT_SPACE  + 3*FIGHTER_WIDTH + 3*INTER_SPACE, choose.getIconHeight()+10, FIGHTER_WIDTH, FIGHTER_HEIGHT);
    ImageIcon j4 = new ImageIcon("resources/majin-name.gif");
    Image scaledName4 = (j4.getImage()).getScaledInstance(FIGHTER_WIDTH, 60 , Image.SCALE_DEFAULT);
    JLabel majinName = new JLabel (new ImageIcon (scaledName4));
    add(majinName);
    majinName.setBounds(INIT_SPACE - 10 + 3*FIGHTER_WIDTH + 3*INTER_SPACE, choose.getIconHeight()+10 + FIGHTER_HEIGHT + 10, FIGHTER_WIDTH
        , j4.getIconHeight());
    
    ImageIcon i5 = new ImageIcon("resources/saiyan-edit.png"); 
    Image saiyan = i5.getImage().getScaledInstance(FIGHTER_WIDTH, FIGHTER_HEIGHT, Image.SCALE_DEFAULT); 
     saiyan_button = new JButton(new ImageIcon(saiyan)); 
    saiyan_button.setActionCommand("saiyan"); 
    saiyan_button.setBorder(BorderFactory.createEmptyBorder());
    saiyan_button.setContentAreaFilled(false);
    add(saiyan_button); 
    saiyan_button.setBounds(INIT_SPACE  + 4*FIGHTER_WIDTH + 4*INTER_SPACE, choose.getIconHeight()+10, FIGHTER_WIDTH, FIGHTER_HEIGHT);
    ImageIcon j5 = new ImageIcon("resources/saiyan-name.gif");
    Image scaledName5 = (j5.getImage()).getScaledInstance(FIGHTER_WIDTH, 60 , Image.SCALE_DEFAULT);
    JLabel saiyanName = new JLabel (new ImageIcon (scaledName5));
    add(saiyanName);
    saiyanName.setBounds(INIT_SPACE - 10 + 4*FIGHTER_WIDTH + 4*INTER_SPACE , choose.getIconHeight()+10 + FIGHTER_HEIGHT + 10, FIGHTER_WIDTH
        , j5.getIconHeight());    
    
  }
  
  public void setListenersToGameController (ActionListener a) {
      saiyan_button.addActionListener(a);
      frieza_button.addActionListener(a);
      earthling_button.addActionListener(a);
      majin_button.addActionListener(a);
      namekian_button.addActionListener(a);
  }
  
 public static void main(String[] args) {
  JFrame f = new JFrame(); 
  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
  f.setSize(Constants.WIDTH, Constants.HEIGHT);
  f.add(new ChooseFighter()); 
  f.setVisible(true);
}
}
