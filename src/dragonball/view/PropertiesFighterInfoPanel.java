package dragonball.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PropertiesFighterInfoPanel extends JPanel
{
   static final int WIDTH_FIGHTER_INFO = 1000;
   static final int HEIGHT_FIGHTER_INFO = 340;
   private JLabel active_fighter_label;
    public PropertiesFighterInfoPanel()
    {
      setSize(WIDTH_FIGHTER_INFO,HEIGHT_FIGHTER_INFO);
      setLayout(null);
      setBackground(Color.BLACK);
 
      
      ImageIcon active_fighter_icon = new ImageIcon("resources/properties/active fighter.png");
      Image active_fighter_word_image = (active_fighter_icon.getImage()).getScaledInstance(230, 100, Image.SCALE_DEFAULT);
      active_fighter_label = new JLabel(new ImageIcon(active_fighter_word_image));
      add(active_fighter_label);
      active_fighter_label.setBounds(0, 0, 230, 100);
     
      
      setVisible(true);
    }
    
    public void displayFighterInfo(String fighterName, int fighterLevel, int fighterMaxKi , int fighterMaxStamina, int fighterMaxHP, int fighterXP, int fighterTargetXP, int fighterAbilityPoints,int fighterPhysicalDamage, int fighterBlastDamage)
    {
      active_fighter_label.setVisible(true);
      JTextArea name = new JTextArea(fighterName);
      name.setFont(new Font("Yu Gothic Light",Font.PLAIN,30));
      name.setBackground(Color.BLACK);
      name.setForeground(Color.WHITE);
      name.setEditable(false);
      name.setOpaque(false);
      
      add(name);
      name.setBounds(235, 20, 250, 60);
      name.setVisible(true);
      
      JTextArea level = new JTextArea("Level: "+fighterLevel);
      level.setFont(new Font("Yu Gothic Light",Font.PLAIN,30));
      level.setBackground(Color.BLACK);
      level.setForeground(Color.WHITE);
      level.setEditable(false);
      level.setOpaque(false);
      
      add(level);
      level.setBounds(550, 20, 220, 60);
      level.setVisible(true);
      
      JTextArea maxKi = new JTextArea("Maximum Ki: "+fighterMaxKi);
      maxKi.setFont(new Font("Yu Gothic Light",Font.PLAIN,30));
      maxKi.setBackground(Color.BLACK);
      maxKi.setForeground(Color.WHITE);
      maxKi.setEditable(false);
      maxKi.setOpaque(false);
      
      add(maxKi);
      maxKi.setBounds(0,90, 250, 60);
      maxKi.setVisible(true);
      
      
      JTextArea maxStamina = new JTextArea("Maximum Stamina: "+fighterMaxStamina);
      maxStamina.setFont(new Font("Yu Gothic Light",Font.PLAIN,30));
      maxStamina.setBackground(Color.BLACK);
      maxStamina.setForeground(Color.WHITE);
      maxStamina.setEditable(false);
      maxStamina.setOpaque(false);
      
      add(maxStamina);
      maxStamina.setBounds(520,90, 300, 60);
      maxStamina.setVisible(true);
      
      JTextArea maxHP = new JTextArea("Maximum Health Points: "+fighterMaxHP);
      maxHP.setFont(new Font("Yu Gothic Light",Font.PLAIN,30));
      maxHP.setBackground(Color.BLACK);
      maxHP.setForeground(Color.WHITE);
      maxHP.setEditable(false);
      maxHP.setOpaque(false);
      
      add(maxHP);
      maxHP.setBounds(0,160, 410, 60);
      maxHP.setVisible(true);
      
      JTextArea XP = new JTextArea("Current XP: "+fighterXP);
      XP.setFont(new Font("Yu Gothic Light",Font.PLAIN,30));
      XP.setBackground(Color.BLACK);
      XP.setForeground(Color.WHITE);
      XP.setEditable(false);
      XP.setOpaque(false);
      
      add(XP);
      XP.setBounds(0,220, 300, 60);
      XP.setVisible(true);
      
      JTextArea targetXP = new JTextArea("Target XP: "+fighterTargetXP);
      targetXP.setFont(new Font("Yu Gothic Light",Font.PLAIN,30));
      targetXP.setBackground(Color.BLACK);
      targetXP.setForeground(Color.WHITE);
      targetXP.setEditable(false);
      targetXP.setOpaque(false);
      
      add(targetXP);
      targetXP.setBounds(520,220, 250, 60);
      targetXP.setVisible(true);
      
      JTextArea abilityPoints = new JTextArea("Ability Points: "+fighterAbilityPoints);
      abilityPoints.setFont(new Font("Yu Gothic Light",Font.PLAIN,30));
      abilityPoints.setBackground(Color.BLACK);
      abilityPoints.setForeground(Color.WHITE);
      abilityPoints.setEditable(false);
      abilityPoints.setOpaque(false);
      
      add(abilityPoints);
      abilityPoints.setBounds(520,160, 250, 60);
      abilityPoints.setVisible(true);
      
      JTextArea physicalDamage = new JTextArea("Physical Damage: "+fighterPhysicalDamage);
      physicalDamage.setFont(new Font("Yu Gothic Light",Font.PLAIN,30));
      physicalDamage.setBackground(Color.BLACK);
      physicalDamage.setForeground(Color.WHITE);
      physicalDamage.setEditable(false);
      physicalDamage.setOpaque(false);
      
      add(physicalDamage);
      physicalDamage.setBounds(0,280, 300, 60);
      physicalDamage.setVisible(true);
      
      JTextArea blastDamage = new JTextArea("Blast Damage: "+fighterBlastDamage);
      blastDamage.setFont(new Font("Yu Gothic Light",Font.PLAIN,30));
      blastDamage.setBackground(Color.BLACK);
      blastDamage.setForeground(Color.WHITE);
      blastDamage.setEditable(false);
      blastDamage.setOpaque(false);
      
      add(blastDamage);
      blastDamage.setBounds(520,280, 300, 60);
      blastDamage.setVisible(true);
    }
    
    
    public static void main(String[] args) {
      JFrame f = new JFrame();
      f.setSize(Constants.WIDTH, Constants.HEIGHT);
      f.setLayout(null);
      f.getContentPane().setBackground(Color.BLACK);
      PropertiesFighterInfoPanel p = new PropertiesFighterInfoPanel();
      
      f.add(p);
      p.displayFighterInfo("goku", 1, 5, 4,100, 80, 10, 2,50,100);
      p.setBounds(220, 0, 900, 400);
      p.setVisible(true);
      f.setVisible(true);
    }

   
}
