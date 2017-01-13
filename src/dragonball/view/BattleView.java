package dragonball.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import dragonball.controller.GameController;
import dragonball.model.attack.Attack;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.player.Player;

public class BattleView extends JPanel {
  static final int BATTLE_WIDTH = Constants.WIDTH;
  static final int BATTLE_HEIGHT = Constants.HEIGHT;
  static final String ME_SPRITE_PATH = "resources/battle/"; 
  static final String FOE_SPRITE_PATH = "resources/battle/foes/"; 
  GameController controller; 
  private TurnOptions turnOptions; 
 
  
  
  public TurnOptions getTurnOptions() {
    return turnOptions;
   
  }

  //I will store the me and foe of the battle in local variables here to make accessing them easier 
  private PlayableFighter me;
  private NonPlayableFighter foe; 
  
  private ArrayList<SuperAttack> testSuper = new ArrayList<SuperAttack>(); 
  private ArrayList<UltimateAttack> testUltimate = new ArrayList<UltimateAttack>(); 

  private String meSpriteSource = "resources/battle/goku-standby.gif";
  private String foeSpriteSource = "C:/Users/R/Desktop/Dragonball art/battle/foes/dabura-standby.gif";
  // hold the images to be displayed in the JLabels, changes according to battle state
  private ImageIcon meSprite;
  private ImageIcon foeSprite;

  // hold the current fighter and foe being displayed on the screen
  private JLabel meLabel;
  private JLabel foeLabel;

  // coordinates of the fighter on the map
  static final int me_foe_offset = 100; 
  private int x_me = BATTLE_WIDTH/2 - me_foe_offset;
  private int y_sprite = (BATTLE_HEIGHT*4)/5;

  private int x_foe = x_me + me_foe_offset;
 

  private Timer timer, timer2, timer3;  
  private boolean showingMeAttack = false, showingFoeAttack = false, standbyDisplayed = false, showingMeBlocking = false; 
   private int foeNumber; 
   private static final int totalFoes = 4; 
   //private int counter =  0; 
   private BattleInfoPanel info; 
   private Player player;
   
   public BattleView() {
     
    
    setLayout(null);
    
   
    turnOptions = new TurnOptions();
    timer = new Timer(4000, new ActionListener() {
 
      

      @Override
      public void actionPerformed(ActionEvent e) {
        
        if (showingMeAttack) {
          
          displayMe(); 
          showingMeAttack = false; 
          
        } else {
         if (!standbyDisplayed) {
          displayStandby(); 
         }
         controller.updateBattleInfoBar();
          
          
          timer.stop(); 
          controller.callPlay();
        }
         
      }
    }); 
    
    timer2 = new Timer(4000, new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        if (showingFoeAttack) {

          //TODO to test Alaa w mariem 
          getInfo().getFoeTurn().setVisible(true);
          getInfo().getTurn().setVisible(false);
          
          
          displayFoe(); //will display foe in attack mode 
          showingFoeAttack = false;
        } else { 
          if (!standbyDisplayed) {
          displayStandby(); //will display both fighter in standby mode 
          }
          
        //TODO to test Alaa w Mariem
          getInfo().getTurn().setVisible(true);
          getInfo().getFoeTurn().setVisible(false);
          
          controller.getGameView().getBattleView().getTurnOptions().toggleAttackBlockUse();
          controller.updateBattleInfoBar();
          timer2.stop();
          
        }   
       
      }
      }); 
    
    timer3 = new Timer(2000, new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        if (showingMeBlocking) {
          displayMe();
          showingMeBlocking = false; 
        } else {
          if (!standbyDisplayed) {
            displayStandby();
          }
          controller.callPlay();
          controller.updateBattleInfoBar();
          timer3.stop();
        }
        
      }
    }); 
   
    
  }
   
   public void createInfoPanel()
   {
     info = new BattleInfoPanel(player,foe); 
     add(info); 
     
     info.setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT);
     info.setVisible(true);
   }
  
  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public BattleInfoPanel getInfo() {
    return info;
  }

  public void setInfo(BattleInfoPanel info) {
    this.info = info;
  }

  public void setUpTurnOptions () {
    if (me != null && foe != null) {
    turnOptions.setUltimateAttackMenu(me.getUltimateAttacks());
    turnOptions.setSuperAttackMenu(me.getSuperAttacks());
    turnOptions.displayAttackMenus();
    add(turnOptions);
    
    turnOptions.setBounds(0, BATTLE_HEIGHT*4/5, BATTLE_WIDTH, BATTLE_HEIGHT/4);
    } else {
      System.out.println("Me or Foe is Null on battle start -battle view ");
      System.out.println("Me: " + me + " Foe: " + foe);
    }
  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
     Graphics2D g2 = (Graphics2D) g;
     ImageIcon icon = new ImageIcon("resources/battle/battle-background.jpeg");
      Image img = icon.getImage();
    g2.drawImage(img, 0, 0, BATTLE_WIDTH, BATTLE_HEIGHT,null); 
  }

  public void displayMe() {
    meSprite = new ImageIcon(meSpriteSource); 
     if (meLabel==null)
     {
       meLabel = new JLabel(meSprite);
       add(meLabel);
       meLabel.setBounds(x_me, y_sprite - meSprite.getIconHeight(), meSprite.getIconWidth(),
           meSprite.getIconHeight());
       
       meLabel.setVisible(true);
      
     } else {
       meLabel.setIcon(meSprite);
       meLabel.setBounds(x_me, y_sprite - meSprite.getIconHeight(), meSprite.getIconWidth(),
           meSprite.getIconHeight());
     }
    
  }

  public void displayFoe() {
    foeSprite = new ImageIcon(foeSpriteSource); 
      if (foeLabel == null) 
         
      
      {foeLabel = new JLabel(foeSprite);
      add(foeLabel);
      foeLabel.setBounds(x_foe, y_sprite - foeSprite.getIconHeight(), foeSprite.getIconWidth(), foeSprite.getIconHeight());
      //System.out.println(foeLabel.getLocation());
      foeLabel.setVisible(true);
   
      } else {
        foeLabel.setIcon(foeSprite);
        foeLabel.setBounds(x_foe, y_sprite - foeSprite.getIconHeight(), foeSprite.getIconWidth(), foeSprite.getIconHeight());
      }
    
     
     }
  
  
  
  public PlayableFighter getMe() {
    return me;
  }

  public void setMe(PlayableFighter me) {
    this.me = me;
  }

  public NonPlayableFighter getFoe() {
    return foe;
  }

  public void setFoe(NonPlayableFighter foe) {
    this.foe = foe;
  }



  public void setListenersToGameController(ActionListener a) {
    turnOptions.setListenersToGameController(a); 
    controller = (GameController) a; 
    
  }

  public void displayStandby() {
    meSpriteSource = ME_SPRITE_PATH + getFighterRace(me) + "-standby.gif"; 
    displayMe(); 
    
    if (foeNumber == 0)
      foeNumber = getFoeNumber(); 
    foeSpriteSource = FOE_SPRITE_PATH + "foe" + foeNumber + "-standby.gif"; //TODO change foe name
    displayFoe(); 
    standbyDisplayed = true; 
    
    
  }
  
  public String getFighterRace(PlayableFighter fighter) {
    if (fighter instanceof Saiyan ) {
      if (((Saiyan)fighter).isTransformed())
        return "supersaiyan"; 
      return "saiyan"; 
    } else if (fighter instanceof Majin) 
      return "majin"; 
    else if (fighter instanceof Namekian) 
      return "namekian"; 
    else if (fighter instanceof Frieza) 
      return "frieza"; 
    else 
     return "earthling"; 
    
  }
  
  public String getAttackType (Attack attack) {
    if (attack instanceof SuperAttack) {
      return "super"; 
    } 
    if (attack instanceof UltimateAttack) {
      return "ultimate"; 
    } 
    if (attack instanceof PhysicalAttack) {
      return "physical"; 
    } 
    if (attack instanceof SuperSaiyan) {
      return "super"; 
    }
    return null; 
  } 

  public SuperAttack findSuperAttack(String selectedSuperAttack) {
    for (SuperAttack attack : me.getSuperAttacks()) {
      if (attack.getName().equals(selectedSuperAttack)) 
        return attack; 
    }
    System.out.println("Super Attack not found. I'm in the BattleView btw");
    return null; 
  }

  public UltimateAttack findUltimateAttack(String selectedUltimateAttack) {
   for (UltimateAttack attack : me.getUltimateAttacks()) {
     if (attack.getName().equals(selectedUltimateAttack)) 
       return attack; 
   }
    System.out.println("Super attack not found. I'm in the BattleView btw");
    return null;
  }

  public void meAttack(Attack attack) {
    //System.out.println("me attack");
    standbyDisplayed = false; 
    String race = getFighterRace(me); 
    String attackType = getAttackType(attack); 
    meSpriteSource = ME_SPRITE_PATH + race + "-"+ attackType + ".gif";
    //foeSpriteSource = FOE_SPRITE_PATH + "foe" + foeNumber + "-standby.gif";
    //displayFoe();
    
    showingMeAttack = true; 
    timer.setInitialDelay(100);
    timer.start();
    
    
  }
  
  public void foeAttack (Attack attack) {
    //System.out.println("6 - foe attack called in view");
    standbyDisplayed = false; 
    String attackType = getAttackType(attack); 
    foeSpriteSource = FOE_SPRITE_PATH + "foe" + foeNumber + "-" + attackType + ".gif"; 
    showingFoeAttack = true; 
    
    timer2.setInitialDelay(2000); 
      timer2.start();   
      
    
  }
  public void meBlock() {
    //System.out.println("2- me block in view");
    standbyDisplayed = false; 
    meSpriteSource = ME_SPRITE_PATH + getFighterRace(me) + "-blocking.gif"; 
    showingMeBlocking = true; 
    
    timer3.setInitialDelay(100);
    timer3.start();
  }
  
  public int getFoeNumber () {
    Random r = new Random(); 
    return r.nextInt(totalFoes) + 1; 
  }
}
