package dragonball.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dragonball.model.dragon.DragonWish;

public class DragonView extends JPanel {

  JButton senzuBeans ,abilityPoints , superAttack , ultimateAttack ; 
  
  public DragonView() {
    
    
   
    //setSize(1370, 730);
    
    setLayout(null);

    //background.setBounds(0,0,1370,730);
  // setVisible(true);
   ImageIcon i = new ImageIcon("resources/dragonView/senzuBeans.jpg");
   Image ii = i.getImage().getScaledInstance(120, 120 , Image.SCALE_DEFAULT);
    senzuBeans = new JButton (new ImageIcon (ii));
    senzuBeans.setActionCommand("senzu-bean-wish");
    //senzuBeans.setSize(10,10);
    add(senzuBeans);
    senzuBeans.setBounds(1190, 250, 120, 120);
    //senzuBeans.setBorder(BorderFactory.createEmptyBorder());
   // senzuBeans.setContentAreaFilled(false);
    //senzuBeans.setOpaque(false);
   
    senzuBeans.setVisible(true);
    
    ImageIcon ab = new ImageIcon("resources/dragonView/ability.jpg");
    Image abb = ab.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
    abilityPoints = new JButton (new ImageIcon(abb));
    abilityPoints.setActionCommand("ability-points-wish");
    add(abilityPoints);
    abilityPoints.setBounds(60 , 250 , 120 , 120);
    abilityPoints.setVisible(true);
    
        
    ImageIcon sup = new ImageIcon ("resources/dragonView/superAttack1.jpg");
    Image supp = sup.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
    superAttack = new JButton (new ImageIcon(supp));
    superAttack.setActionCommand("super-attack-wish");
    add(superAttack);
    superAttack.setBounds(60 , 100 , 120 , 120);
    superAttack.setVisible(true);
    
//    ImageIcon supT = new ImageIcon ("resources/dragonView/coollogo_com-22752391.png");
//    Image suppT = supT.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
//    JLabel superAttackT = new JLabel (new ImageIcon(suppT));
//    add(superAttackT);
//    superAttackT.setBounds(200 , 250 , 120 , 120);
//    superAttackT.setVisible(true);    
    
    
    ImageIcon ult = new ImageIcon("resources/dragonView/ultimateAttack.jpg");
    Image ultt = ult.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
    ultimateAttack = new JButton (new ImageIcon(ultt));
    ultimateAttack.setActionCommand("ultimate-attack-wish");
    add(ultimateAttack);
    ultimateAttack.setBounds(1190 , 100 , 120 , 120 );
    ultimateAttack.setVisible(true);
    

    ImageIcon chooseIcon = new ImageIcon("resources/dragonView/choose-your-wish.png");
    JLabel chooseLabel = new JLabel(chooseIcon);
    add(chooseLabel);
    chooseLabel.setBounds(160,400, chooseIcon.getIconWidth(), chooseIcon.getIconHeight());
    chooseLabel.setVisible(true);
    
  }
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    ImageIcon icon = new ImageIcon("resources/dragonView/start.jpg");
    Image scaledImage = (icon.getImage()).getScaledInstance(1370, 730, Image.SCALE_DEFAULT);
    ImageIcon img = new ImageIcon(scaledImage);
    Image m =  img.getImage();
   
    g2.drawImage(m, 0, 0 , Constants.WIDTH, Constants.HEIGHT, null);

  }
  
  
  

 public void setListenersToGameController(ActionListener a)
 {
  superAttack.addActionListener(a);
  ultimateAttack.addActionListener(a);
  senzuBeans.addActionListener(a);
  abilityPoints.addActionListener(a);
  
 }
 
 public void setWishes(DragonWish[] wishes)
 {
  senzuBeans.setToolTipText("Add "+wishes[0].getSenzuBeans()+" Senzu Beans");
  abilityPoints.setToolTipText("Add "+ wishes[1].getAbilityPoints()+" Ability Points");
  superAttack.setToolTipText("Add "+ wishes[2].getSuperAttack().getName() + " to your Super Attacks");
  ultimateAttack.setToolTipText("Add " + wishes[3].getUltimateAttack().getName() + " to your Ultimate Attacks");
 }

  
  
  public static void main(String []args) {
    JFrame f = new JFrame();
   f.setSize(1370, 730);
   f.setResizable(false);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    DragonView v = new DragonView();
    
    f.add(v);
    f.setVisible(true);
    
  }
  

}
