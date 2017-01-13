package dragonball.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import dragonball.model.attack.Attack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

public class AssignAttackView extends JFrame
{
  private JComboBox<String> playerSuperAttacksBox;
  private JComboBox<String> fighterSuperAttacksBox;
  private JComboBox<String> playerUltimateAttacksBox;
  private JComboBox<String> fighterUltimateAttacksBox;
  
 private JButton addButton;
 private JButton switchButton;
 private JButton cancelButton;


  public AssignAttackView()
  {
  setSize(Constants.WIDTH ,Constants.HEIGHT + 20);
  getContentPane().setBackground(Color.BLACK);
  setUndecorated(true);
  setOpacity(0.85f);
  
  setLayout(null);
  
  ImageIcon playerAttacksIcon = new ImageIcon("resources/properties/your available attacks.png");
  Image playerAttacksImage = (playerAttacksIcon.getImage()).getScaledInstance(300, 100, Image.SCALE_DEFAULT);
  JLabel playerAttacksLabel = new JLabel(new ImageIcon(playerAttacksImage));
  getContentPane().add(playerAttacksLabel);
  playerAttacksLabel.setBounds(280, 100, 300, 90);
  playerAttacksLabel.setVisible(true);
  
  ImageIcon fighterAttacksIcon = new ImageIcon("resources/properties/fighter's attacks.png");
  Image fighterAttacksImage = (fighterAttacksIcon.getImage()).getScaledInstance(280, 100, Image.SCALE_DEFAULT);
  JLabel fighterAttacksLabel = new JLabel(new ImageIcon(fighterAttacksImage));
  getContentPane().add(fighterAttacksLabel);
  fighterAttacksLabel.setBounds(730, 100, 280, 90);
  fighterAttacksLabel.setVisible(true);
  
  ImageIcon addIcon = new ImageIcon("resources/properties/add button.png");
  Image addImage = (addIcon.getImage()).getScaledInstance(120, 80, Image.SCALE_DEFAULT);
  addButton = new JButton(new ImageIcon(addImage));
  addButton.setActionCommand("add_attack");
  getContentPane().add(addButton);
  addButton.setBounds(410,500,120,80);
  addButton.setVisible(true);
  
  ImageIcon switchIcon = new ImageIcon("resources/properties/switch button.png");
  Image switchImage = (switchIcon.getImage()).getScaledInstance(120, 80, Image.SCALE_DEFAULT);
  switchButton = new JButton(new ImageIcon(switchImage));
  switchButton.setActionCommand("switch_attacks");
  getContentPane().add(switchButton);
  switchButton.setBounds(605,500,120,80);
  switchButton.setVisible(true);
  
  ImageIcon cancelIcon = new ImageIcon("resources/properties/cancel button 2.png");
  Image cancelImage = (cancelIcon.getImage()).getScaledInstance(120, 80, Image.SCALE_DEFAULT);
  cancelButton = new JButton(new ImageIcon(cancelImage));
  cancelButton.setActionCommand("cancel_assign_attack");
  getContentPane().add(cancelButton);
  cancelButton.setBounds(800,500,120,80);
  cancelButton.setVisible(true);
  
  ImageIcon superAttacksIcon = new ImageIcon("resources/properties/super_attacks.png");
  Image superAttacksImage = (superAttacksIcon.getImage()).getScaledInstance(170, 80, Image.SCALE_DEFAULT);
  JLabel superAttacksLabel = new JLabel(new ImageIcon(superAttacksImage));
  getContentPane().add(superAttacksLabel);
  superAttacksLabel.setBounds(30,200 , 170, 80);
  superAttacksLabel.setVisible(true);
  
  ImageIcon ultimateAttacksIcon = new ImageIcon("resources/properties/ultimate_attacks.png");
  Image ultimateAttacksImage = (ultimateAttacksIcon.getImage()).getScaledInstance(190, 80, Image.SCALE_DEFAULT);
  JLabel ultimateAttacksLabel = new JLabel(new ImageIcon(ultimateAttacksImage));
  getContentPane().add(ultimateAttacksLabel);
  ultimateAttacksLabel.setBounds(20,280 , 190, 80);
  ultimateAttacksLabel.setVisible(true);
  
  }
  
  public JComboBox<String> getPlayerSuperAttacksBox() {
    return playerSuperAttacksBox;
  }

  public JComboBox<String> getPlayerUltimateAttacksBox() {
    return playerUltimateAttacksBox;
  }

  public JComboBox<String> getFighterSuperAttacksBox() {
    return fighterSuperAttacksBox;
  }
  public JComboBox<String> getFighterUltimateAttacksBox() {
    return fighterUltimateAttacksBox;
  }

public void setAllListeners(ActionListener a)
{
  addButton.addActionListener(a);
  switchButton.addActionListener(a);
  cancelButton.addActionListener(a);
 
}

  public void setFighterSuperAttacksBox(ArrayList<SuperAttack> arrayList)
      
  {
    String [] attacks = new String[arrayList.size()];
    int i = 0;
    for(i = 0; i < arrayList.size(); i++)
    {
      attacks[i] = arrayList.get(i).getName();
    }
    if(fighterSuperAttacksBox != null)
      remove(fighterSuperAttacksBox);
    fighterSuperAttacksBox = new JComboBox<String>(attacks);
    fighterSuperAttacksBox.setSelectedItem(null);
    fighterSuperAttacksBox.setEditable(false);
    getContentPane().add(fighterSuperAttacksBox);
    fighterSuperAttacksBox.setBounds(760,210,210,32);
    fighterSuperAttacksBox.setVisible(true);
     
  }
  
  public void setFighterUltimateAttacksBox(ArrayList<UltimateAttack> arrayList)
  
  {
    String [] attacks = new String[arrayList.size()];
    int i = 0;
    for(i = 0; i < arrayList.size(); i++)
    {
      attacks[i] = arrayList.get(i).getName();
    }
   
    if(fighterUltimateAttacksBox != null)
      remove(fighterUltimateAttacksBox);
    fighterUltimateAttacksBox = new JComboBox<String>(attacks);
    fighterUltimateAttacksBox.setSelectedItem(null);
    fighterUltimateAttacksBox.setEditable(false);
    getContentPane().add(fighterUltimateAttacksBox);
    fighterUltimateAttacksBox.setBounds(760,290,210,32);
    fighterUltimateAttacksBox.setVisible(true);
     
  }


  public void setPlayerSuperAttacksBox(ArrayList<SuperAttack> arrayList)
  
  {
    String [] attacks = new String[arrayList.size()];
    int i = 0;
    for(i = 0; i < arrayList.size(); i++)
    {
      attacks[i] = arrayList.get(i).getName();
    }
    if(playerSuperAttacksBox != null)
      remove(playerSuperAttacksBox);
    playerSuperAttacksBox = new JComboBox<String>(attacks);
    playerSuperAttacksBox.setSelectedItem(null);
    playerSuperAttacksBox.setEditable(false);
    getContentPane().add(playerSuperAttacksBox);
    playerSuperAttacksBox.setBounds(320,210,210,32);
    playerSuperAttacksBox.setVisible(true);
     
  }
  
  public void setPlayerUltimateAttacksBox(ArrayList<UltimateAttack> arrayList)
  
  {
    String [] attacks = new String[arrayList.size()];
    int i = 0;
    for(i = 0; i < arrayList.size(); i++)
    {
      attacks[i] = arrayList.get(i).getName();
    }
   
    if(playerUltimateAttacksBox != null)
      remove(playerUltimateAttacksBox);
    playerUltimateAttacksBox = new JComboBox<String>(attacks);
    playerUltimateAttacksBox.setSelectedItem(null);
    playerUltimateAttacksBox.setEditable(false);
    
    getContentPane().add(playerUltimateAttacksBox);
    playerUltimateAttacksBox.setBounds(320,290,210,32);
    playerUltimateAttacksBox.setVisible(true);
     
  }
  
  public static void main(String[] args) 
  {
   (new AssignAttackView()).setVisible(true); 
  }
  
  
  
  
  
}
