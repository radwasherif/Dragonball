package dragonball.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.sun.corba.se.impl.orbutil.closure.Constant;

import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.player.Player;

public class BattleInfoPanel extends JPanel{
  static final int info_x = 10;
  static final int info_y = 10;
 static final int y_offset = 33;
 static final int x_offset = 20 ; 
 
 static final int info_x_foe = Constants.WIDTH /2 ;
 private Player player;
  private PlayableFighter fighter;
  private NonPlayableFighter foe;
  private ArrayList<JLabel> senzuListFighter ;
  private ArrayList<JLabel> senzuListFoe ;
  private ArrayList<JLabel> emptyStaminaListFighter;
  private ArrayList<JLabel> fullStaminaListFighter;
  private ArrayList<JLabel> kiListFullFighter;
  private ArrayList<JLabel> emptyStaminaListFoe;
  private ArrayList<JLabel> fullStaminaListFoe;
  private ArrayList<JLabel> kiListFullFoe;
  private JTextArea xp;
  private JTextArea fighterKi;
 private JProgressBar fighterHealth;
 private JTextArea foeKi;
 JProgressBar foeHealth;
 private JLabel turn ;
 private JLabel foeTurn;
  
  public JLabel getTurn() {
  return turn;
}


public void setTurn(JLabel turn) {
  this.turn = turn;
}


public JLabel getFoeTurn() {
  return foeTurn;
}


public void setFoeTurn(JLabel foeTurn) {
  this.foeTurn = foeTurn;
}


  public BattleInfoPanel (Player player, NonPlayableFighter foe ) {
    
    setSize(Constants.WIDTH , Constants.HEIGHT);
    setLayout(null);
    setOpaque(false);
    
    ImageIcon turnIcon = new ImageIcon("resources/battleInfoPanel/turn.png");
    Image turnI = (turnIcon.getImage()).getScaledInstance(200, 100, Image.SCALE_DEFAULT);
    turn = new JLabel (new ImageIcon (turnI));
    add(turn);
    turn.setOpaque(false);
    turn.setBounds((Constants.WIDTH /2 - turn.getWidth() / 2) - 110  , info_y+ 40 , 200 , 100 );
   turn.setVisible(false);
    
    ImageIcon foeTurnIcon = new ImageIcon("resources/battleInfoPanel/foeTurn.png");
    Image foeTurnI = (foeTurnIcon.getImage()).getScaledInstance(200, 100, Image.SCALE_DEFAULT);
    foeTurn = new JLabel (new ImageIcon (foeTurnI));
    add(foeTurn);
    foeTurn.setOpaque(false);
    foeTurn.setBounds((Constants.WIDTH /2 - turn.getWidth() / 2) - 40  , info_y+ 40 , 200 , 100 );
    foeTurn.setVisible(false);
    
    
    this.player = player;
    fighter = player.getActiveFighter();
    
    ImageIcon activeFighterIcon = new ImageIcon ("resources/world/active-fighter.png");
    Image act = activeFighterIcon.getImage().getScaledInstance(160, 50, Image.SCALE_DEFAULT);
    JLabel activeFighterLabel = new JLabel (new ImageIcon(act));
    add(activeFighterLabel);
   
    activeFighterLabel.setBounds(info_x, info_y, 160, 50);
    activeFighterLabel.setVisible(true);
    
    JTextArea activeFighterName = new JTextArea(fighter.getName());
    activeFighterName.setEditable(false);
    activeFighterName.setFont(new Font("Forte" ,Font.PLAIN  , 30));
    activeFighterName.setOpaque(false);
    add(activeFighterName);
    activeFighterName.setBounds(info_x+165,info_y,200,50);
    activeFighterName.setVisible(true);
    
    ImageIcon levelIcon = new ImageIcon("resources/world/level.png");
    Image lev = levelIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
    JLabel level = new JLabel (new ImageIcon(lev));
    add(level);
    level.setBounds(info_x, info_y+ y_offset, 50, 50);
    level.setVisible(true);
    
    Font l = new Font("Forte" ,Font.PLAIN  , 30 );
    JTextArea fighterLevel = new JTextArea(" "+fighter.getLevel());
    fighterLevel.setFont(l);
    add(fighterLevel);
    fighterLevel.setForeground(Color.orange);
   fighterLevel.setBounds( info_x +70, info_y + y_offset+5, 200 , 100);
   fighterLevel.setEditable(false);
   fighterLevel.setOpaque(false);
    fighterLevel.setVisible(true);
    
//    ArrayList abilityList = new ArrayList<JLabel>();
//    ImageIcon abilityIcon = new ImageIcon ("resources/world/ability-point-full.png");
//    Image abil = abilityIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
//    for (int i = 0; i < fighter.getAbilityPoints() ; i++) {
//      JLabel ability = new JLabel(new ImageIcon (abil));
//      abilityList.add(ability);
//      add(ability);
//      ability.setBounds(info_x +300 + (i*x_offset) ,info_y , 30, 30);
//      ability.setVisible(true);
//      }
//    

    if(xp!= null){
     xp.setVisible(false);
      remove(xp);
    }
    
    
    Font xpF = new Font("Forte" , Font.PLAIN , 30);
     xp = new JTextArea(fighter.getXp()+" / " + fighter.getTargetXp());
    xp.setFont(xpF);
    add(xp);
    xp.setBounds(info_x +300 , info_y + 4*y_offset -80  , 200, 60);
    xp.setEditable(false);
    xp.setOpaque(false);
    xp.setVisible(true);
     
     ImageIcon xpIcon = new ImageIcon("resources/battleInfoPanel/xp.png");
     Image xpI = xpIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
     JLabel xpp = new JLabel(new ImageIcon(xpI));
     xpp.setToolTipText("your XP / target XP");
     add(xpp);
     xpp.setBounds(info_x +360 + (x_offset) , info_y + 4*y_offset -80  , 40, 40);
     xpp.setVisible(true);
    
    
    
    

    
    JTextArea physicalDamage = new JTextArea("Physical Damage: "+fighter.getPhysicalDamage() );
    physicalDamage.setForeground(Color.BLACK);
    //physicalDamage.setBackground(Color.OPAQUE);
    physicalDamage.setOpaque(false);
    add(physicalDamage);
    Font fo = new Font("Forte", Font.BOLD, 20);
    physicalDamage.setFont(fo);
    physicalDamage.setBounds(info_x  , info_y+2*y_offset +10 , 300, 100);
    physicalDamage.setEditable(false);
    physicalDamage.setVisible(true);
  
    
    JTextArea blastDamage = new JTextArea("Blast Damage: "+fighter.getBlastDamage() );
    blastDamage.setForeground(Color.BLACK);
    
    blastDamage.setOpaque(false);
    add(blastDamage);
    Font f = new Font("Forte", Font.BOLD, 20);
    blastDamage.setFont(f);
    blastDamage.setBounds(    info_x  , info_y + 3*y_offset , 300 , 100);
    blastDamage.setEditable(false);
    blastDamage.setVisible(true);
    
    fighterHealth = new JProgressBar(SwingConstants. VERTICAL,0,100);
    add(fighterHealth);  
    fighterHealth.setBounds(10 , 300 , 30 , 300);
    
    
    
    this.foe = foe;
    
    ImageIcon foeNameIcon = new ImageIcon("resources/battleInfoPanel/foeName.png");
    Image foeI = foeNameIcon.getImage().getScaledInstance(140, 50, Image.SCALE_DEFAULT);
    JLabel foeName = new JLabel(new ImageIcon(foeI));
    add(foeName);
    foeName.setBounds(info_x_foe+230, info_y + y_offset -45, 140, 50);
    foeName.setVisible(true);
    
    Font foeN = new Font("Forte" ,Font.PLAIN  , 30 );
    JTextArea foeNameText = new JTextArea(" "+foe.getName());
    foeNameText.setFont(foeN);
    add(foeNameText);
    foeNameText.setBounds(info_x_foe +370 , info_y + y_offset -40 , 200 , 100);
    foeNameText.setOpaque(false);
    foeNameText.setEditable(false);
    foeNameText.setVisible(true);
    
    
    
    ImageIcon levelIcon2 = new ImageIcon("resources/world/level.png");
    Image lev2 = levelIcon2.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
    JLabel level2 = new JLabel (new ImageIcon(lev2));
    add(level2);
    level2.setBounds(info_x_foe+245, info_y +25, 50, 50);
    
    level2.setVisible(true);
   
    Font l2 = new Font("Forte" ,Font.PLAIN  , 30 );
    JTextArea foeLevel = new JTextArea(" "+foe.getLevel());
    foeLevel.setFont(l);
    add(foeLevel);
    foeLevel.setBounds(info_x_foe + 325, info_y+5 + y_offset -10 , 200 , 100);
    foeLevel.setOpaque(false);
    foeLevel.setEditable(false);
    foeLevel.setForeground(Color.orange);
    foeLevel.setVisible(true);
    
    //info_x +1130 , info_y , 250 , 100
    JTextArea physicalDamageF = new JTextArea("Physical Damage: "+foe.getPhysicalDamage() );
    physicalDamageF.setForeground(Color.BLACK);
    //physicalDamage.setBackground(Color.OPAQUE);
    physicalDamageF.setOpaque(false);
    add(physicalDamageF);
    Font font = new Font("Forte", Font.BOLD, 20);
    physicalDamageF.setFont(font);
    physicalDamageF.setBounds(Constants.WIDTH-info_x -440  , info_y + y_offset +38 , 300 , 100);
    physicalDamageF.setEditable(false);
    physicalDamageF.setVisible(true);
    
    
    JTextArea blastDamageF = new JTextArea("Blast Damage: "+foe.getBlastDamage() );
    blastDamageF.setForeground(Color.BLACK);
    //physicalDamage.setBackground(Color.OPAQUE);
    blastDamageF.setOpaque(false);
    add(blastDamageF);
    Font fontF = new Font("Forte", Font.BOLD, 20);
    blastDamageF.setFont(fontF);
    blastDamageF.setBounds(Constants.WIDTH-info_x -440, info_y + y_offset + 68, 300 , 100);
    blastDamageF.setVisible(true);
    
    foeHealth = new JProgressBar(SwingConstants. VERTICAL,0,100);
    add(foeHealth);  
    foeHealth.setBounds(Constants.WIDTH - 60 , 300 , 30 , 300);
    
    
    setVisible(true);
   
  }
  
  
  public void updateBattleFighterInfo(Player player){
    
    
   if(senzuListFighter != null){
    for(JLabel label : senzuListFighter){
      label.setVisible(false);
      remove(label);
    }
   }
    
    
    senzuListFighter = new ArrayList<JLabel>();
    ImageIcon senzuIcon = new ImageIcon("resources/world/senzu-bean.png");
    Image senz = senzuIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    for (int i = 0; i < player.getSenzuBeans() ; i++) {
      JLabel senzuBeans = new JLabel(senzuIcon);
      senzuListFighter.add(senzuBeans);
      if(i == player.getSenzuBeans()-1)
        senzuBeans.setToolTipText("your Senzu Beans");
      add(senzuBeans);
      senzuBeans.setBounds(info_x +300 + (i*x_offset) , info_y, 20 , 20);
      senzuBeans.setVisible(true);
    }
    
    
    
    
    
    if(emptyStaminaListFighter != null){
      for(JLabel label : emptyStaminaListFighter){
        remove(label);
      }
     }
    
    emptyStaminaListFighter = new ArrayList <JLabel>();
    ImageIcon emptyStaminaIcon = new ImageIcon("resources/world/stamina-empty.png");
    Image emptyS = emptyStaminaIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    for (int i = 0; i < fighter.getMaxStamina(); i++) {
      JLabel emptyStamina = new JLabel (new ImageIcon(emptyS));
      emptyStaminaListFighter.add(emptyStamina);
      if(i == fighter.getMaxStamina()-1)
        emptyStamina.setToolTipText("your stamina");
      add(emptyStamina);
      emptyStamina.setBounds(info_x +300 + (i*x_offset) , info_y + y_offset, 20 , 20);
      emptyStamina.setVisible(true);
    }
    
    
    
    if(fullStaminaListFighter != null){
      for(JLabel label : fullStaminaListFighter){
        label.setVisible(false);
        remove(label);
        
         }
     }
     fullStaminaListFighter = new ArrayList<JLabel>();
    ImageIcon fullStaminaIcon = new ImageIcon("resources/world/stamina-full.png");
    Image fullS = fullStaminaIcon.getImage().getScaledInstance( 20 , 20, Image.SCALE_DEFAULT);
    System.out.println("update stamina: " + fighter.getStamina());
    for (int i = 0; i < fighter.getStamina(); i++) {
      JLabel fullStamina = new JLabel (new ImageIcon(fullS));
      fullStaminaListFighter.add(fullStamina);
      add(fullStamina);
      fullStamina.setBounds(info_x +300 + (i*x_offset) , info_y + y_offset, 20 , 20);
      fullStamina.setVisible(true);
    }
    
    
    
    
    
 
    
//    ArrayList kiList = new ArrayList <JLabel>();
//    ImageIcon kiIcon = new ImageIcon("resources/world/ki-empty.png");
//    Image kiI = kiIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
//    for (int i = 0; i < fighter.getMaxKi(); i++) {
//      JLabel kiGrey = new JLabel(new ImageIcon(kiI));
//      kiList.add(kiGrey);
//      add(kiGrey);
//      kiGrey.setBounds(info_x +300 + (i*x_offset) , info_y + 6*y_offset - 80  , 20, 20);
//      kiGrey.setVisible(true);  
//    }
    
    
//    if(kiListFullFighter != null){
//      for(JLabel label : kiListFullFighter){
//        remove(label);
//      }
//     }
//    
//    
//    System.out.println("in view ki fighter: "+fighter.getKi());
//    kiListFullFighter = new ArrayList <JLabel>();
//    ImageIcon kiIconfull = new ImageIcon("resources/world/ki-full.png");
//    Image kiIfull = kiIconfull.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
//    for (int i = 0; i < fighter.getKi(); i++) {
//      JLabel ki = new JLabel(new ImageIcon(kiIfull));
//      kiList.add(ki);
//      add(ki);
//      ki.setBounds(info_x +300 + (i*x_offset) , info_y + 6*y_offset -80  , 20, 20);
//      ki.setVisible(true);      
//    }
    
    
    if(fighterKi !=null){
     fighterKi.setVisible(false);
      remove(fighterKi);
    }
    Font l = new Font("Forte" ,Font.PLAIN  , 30 );
     fighterKi = new JTextArea(fighter.getKi() + " / " + fighter.getMaxKi());
    fighterKi.setFont(l);
    add(fighterKi);
    fighterKi.setBounds( info_x +300  , info_y + 5*y_offset -80  , 100, 60);
    fighterKi.setEditable(false);
    fighterKi.setOpaque(false);
   fighterKi.setVisible(true);
   
 ImageIcon kiIconfull = new ImageIcon("resources/world/ki-full.png");
 JLabel ki = new JLabel(kiIconfull);
 ki.setToolTipText("your Ki Bars");
    add(ki);
   ki.setBounds(info_x +385 , info_y + 5*y_offset -65  , kiIconfull.getIconWidth(), kiIconfull.getIconHeight());
   ki.setVisible(true); 
    
    
   
    int fighterHP = (fighter.getHealthPoints()*100/fighter.getMaxHealthPoints());
    fighterHealth.setValue (  fighterHP );
    fighterHealth.setStringPainted(true);
    
   
    if(fighterHP > 75)
    {
      fighterHealth.setForeground(Color.GREEN);
    }
    else
      if(fighterHP > 50)
      {
        fighterHealth.setForeground(Color.MAGENTA);
      }
      else
        if(fighterHP > 25)
        {
          fighterHealth.setForeground(Color.ORANGE);
        }
        else
        {
          fighterHealth.setForeground(Color.RED);
        }

    
  }
  
  
  
public void updateBattleFoeInfo(NonPlayableFighter nonPlayebleFighter ){
    
 
  if(emptyStaminaListFoe != null){
    for(JLabel label : emptyStaminaListFoe){
      remove(label);
    }
   }
  
  
     emptyStaminaListFoe = new ArrayList <JLabel>();
    ImageIcon emptyStaminaIcon = new ImageIcon("resources/world/stamina-empty.png");
    Image emptyS = emptyStaminaIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    for (int i = 0; i < nonPlayebleFighter.getMaxStamina(); i++) {
      JLabel emptyStamina = new JLabel (new ImageIcon(emptyS));
      emptyStaminaListFoe.add(emptyStamina);
      if(i == nonPlayebleFighter.getMaxStamina() -1)
        emptyStamina.setToolTipText("Foe's Stamina");
      add(emptyStamina);
      emptyStamina.setBounds( info_x +1150 + (i*x_offset) , info_y + 30 , 20 , 20);
      emptyStamina.setVisible(true);
    }
    
    
    
    if(fullStaminaListFoe != null){
      for(JLabel label : fullStaminaListFoe){
        remove(label);
      }
     }
    
    fullStaminaListFoe = new ArrayList<JLabel>();
    ImageIcon fullStaminaIcon = new ImageIcon("resources/world/stamina-full.png");
    Image fullS = fullStaminaIcon.getImage().getScaledInstance( 20 , 20, Image.SCALE_DEFAULT);
    for (int i = 0; i < fighter.getStamina(); i++) {
      JLabel fullStamina = new JLabel (new ImageIcon(fullS));
      fullStaminaListFoe.add(fullStamina);
      add(fullStamina);
      fullStamina.setBounds(info_x +1150 + (i*x_offset) , info_y + 30 , 20 , 20);
      fullStamina.setVisible(true);
    }
    
 
//    ArrayList kiList = new ArrayList <JLabel>();
//    ImageIcon kiIcon = new ImageIcon("resources/world/ki-empty.png");
//    Image kiI = kiIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
//    for (int i = 0; i < nonPlayebleFighter.getMaxKi(); i++) {
//      JLabel kiGrey = new JLabel(new ImageIcon(kiI));
//      kiList.add(kiGrey);
//      add(kiGrey);
//      kiGrey.setBounds(info_x +1130+ (i*x_offset) , info_y + 2*y_offset+20  , 20, 20);
//      kiGrey.setVisible(true);
//      
//    }
//    
//    
//    
//    if(kiListFullFoe != null){
//      for(JLabel label : kiListFullFoe){
//        remove(label);
//      }
//     }
//    
//    
//    
//     kiListFullFoe = new ArrayList <JLabel>();
//    ImageIcon kiIconfull = new ImageIcon("resources/world/ki-full.png");
//    Image kiIfull = kiIconfull.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
//    for (int i = 0; i < nonPlayebleFighter.getKi(); i++) {
//      JLabel ki = new JLabel(new ImageIcon(kiIfull));
//      kiListFullFoe.add(ki);
//      add(ki);
//      ki.setBounds(info_x +1130+ (i*x_offset) , info_y + 2*y_offset +20 , 20, 20);
//      ki.setVisible(true);
//      
//    }
    
    
    if(foeKi!= null){
    foeKi.setVisible(false);
      remove(foeKi);
    }
    Font l = new Font("Forte" ,Font.PLAIN  , 30 );
    foeKi = new JTextArea(nonPlayebleFighter.getKi() + " / " + nonPlayebleFighter.getMaxKi());
    foeKi.setFont(l);
    
    add(foeKi);
    foeKi.setBounds( info_x +1150  , info_y + 60    , 100, 60);
    foeKi.setEditable(false);
    foeKi.setOpaque(false);
    foeKi.setVisible(true);
   
 ImageIcon kiIconfull = new ImageIcon("resources/world/ki-full.png");
 JLabel ki = new JLabel(kiIconfull);
 ki.setToolTipText("Foe's Ki Bars");
    add(ki);
   ki.setBounds(info_x +1230 , info_y + y_offset +40   , kiIconfull.getIconWidth(), kiIconfull.getIconHeight());
   ki.setVisible(true); 
    
   
   int foeHP = (nonPlayebleFighter.getHealthPoints()*100/nonPlayebleFighter.getMaxHealthPoints());
    
   foeHealth.setValue (  foeHP );
   foeHealth.setStringPainted(true);
   if(foeHP > 75)
   {
   foeHealth.setForeground(Color.GREEN);
   }
   else
     if(foeHP > 50)
     {
       foeHealth.setForeground(Color.MAGENTA);
     }
     else
       if(foeHP > 25)
       {
         foeHealth.setForeground(Color.ORANGE);
       }
       else
       {
         foeHealth.setForeground(Color.RED);
       }

    
  }
  
  
  
//  public static void main(String[]args) throws NotEnoughKiException{
//  
//    
//    ArrayList superAttack = new ArrayList<Integer>();
//    superAttack.add("shankel 7arame ya radwa, ha :P");
//    ArrayList ultimateAttack = new ArrayList<Integer>();
//    ultimateAttack.add("saro5 ard gaw");
//    
////    PlayableFighter fighter = new Saiyan("mariem" , 3, 12, 50,
////      100 , 5 , 2, 3 , 6, 6, superAttack, ultimateAttack);
//    
//   PlayableFighter fighter = new Frieza("mariemOther");
//   Player player = new Player("mariem"); 
//    fighter.setAbilityPoints(5);
//    player.setSenzuBeans(10);
//    fighter.setXp(5);
//    player.setActiveFighter(fighter);
//    fighter.setLevel(5);
//    fighter.setKi(2);
//    fighter.setPhysicalDamage(40);
//    
//    JFrame j = new JFrame (); 
//    j.setLayout(null);
//   // j.setSize(Constants.WIDTH, Constants.HEIGHT);
//    j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    BattleInfoPanel p = new BattleInfoPanel();
//    
//    j.add(p);
//    p.setBounds(0,0,Constants.WIDTH , Constants.HEIGHT);
//    j.setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT );
//    p.updateBattleFighterInfo(player);
//    j.setVisible(true);
//    p.setVisible(true);
//    
//    
//    NonPlayableFighter foe = new NonPlayableFighter("mariem", 55, 100, 40, 30, 7, 3, false, superAttack, ultimateAttack);
//    p.updateBattleFoeInfo(foe);
//    
//    
//    
//    
//  }

  
  
}