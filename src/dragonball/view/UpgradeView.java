package dragonball.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;



public class UpgradeView extends JFrame
{
  JButton HPbutton;
  JButton blastDamageButton;
  JButton physicalDamageButton;
  JButton kiButton;
  JButton staminaButton;
  JButton cancelButton;
  
  public UpgradeView()
  {
    setSize(Constants.WIDTH ,Constants.HEIGHT + 20);
    getContentPane().setBackground(Color.BLACK);
    setUndecorated(true);
    setOpacity(0.85f);
    
    setLayout(null);
    ImageIcon HPicon = new ImageIcon("resources/properties/HPbutton.png");
    Image HPimage = (HPicon.getImage()).getScaledInstance(320, 80, Image.SCALE_DEFAULT);
    HPbutton = new JButton(new ImageIcon(HPimage));
    getContentPane().add(HPbutton);
    HPbutton.setBounds(500,80,320,80);
    HPbutton.setActionCommand("increase_HP");
    HPbutton.setVisible(true);
    
    ImageIcon blastdamageicon = new ImageIcon("resources/properties/blast_damage_button.png");
    Image blastdamageimage = (blastdamageicon.getImage()).getScaledInstance(320, 80, Image.SCALE_DEFAULT);
    blastDamageButton = new JButton(new ImageIcon(blastdamageimage));
    getContentPane().add(blastDamageButton);
    blastDamageButton.setBounds(500,175,320,80);
    blastDamageButton.setActionCommand("increase_blast_damage");
    blastDamageButton.setVisible(true);
    
    ImageIcon physicaldamageicon = new ImageIcon("resources/properties/physical damage button.png");
    Image physicaldamageimage = (physicaldamageicon.getImage()).getScaledInstance(320, 80, Image.SCALE_DEFAULT);
    physicalDamageButton = new JButton(new ImageIcon(physicaldamageimage));
    getContentPane().add(physicalDamageButton);
    physicalDamageButton.setBounds(500,270,320,80);
    physicalDamageButton.setActionCommand("increase_physical_damage");
    physicalDamageButton.setVisible(true);
    
    ImageIcon kiicon = new ImageIcon("resources/properties/maxKi button.png");
    Image kiimage = (kiicon.getImage()).getScaledInstance(320, 80, Image.SCALE_DEFAULT);
    kiButton = new JButton(new ImageIcon(kiimage));
    getContentPane().add(kiButton);
    kiButton.setBounds(500,365,320,80);
    kiButton.setActionCommand("increase_max_ki");
    kiButton.setVisible(true);
    
    ImageIcon staminaicon = new ImageIcon("resources/properties/maxStamina button.png");
    Image staminaimage = (staminaicon.getImage()).getScaledInstance(320, 80, Image.SCALE_DEFAULT);
    staminaButton = new JButton(new ImageIcon(staminaimage));
    getContentPane().add(staminaButton);
    staminaButton.setBounds(500,460,320,80);
    staminaButton.setActionCommand("increase_max_stamina");
    staminaButton.setVisible(true);
    
    ImageIcon cancelicon = new ImageIcon("resources/properties/cancel button.png");
    Image cancelimage = (cancelicon.getImage()).getScaledInstance(150, 80, Image.SCALE_DEFAULT);
    cancelButton = new JButton(new ImageIcon(cancelimage));
    getContentPane().add(cancelButton);
    cancelButton.setBounds(600,600,150,80);
    cancelButton.setActionCommand("cancel_upgrading");
    cancelButton.setVisible(true);
    
    
  }
  
  public void setAllListeners(ActionListener a)
  {
    HPbutton.addActionListener(a);
    blastDamageButton.addActionListener(a);
    physicalDamageButton.addActionListener(a);
    kiButton.addActionListener(a);
    staminaButton.addActionListener(a);
    cancelButton.addActionListener(a);
    
    
  }
}
