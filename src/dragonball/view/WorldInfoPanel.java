package dragonball.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;

public class WorldInfoPanel extends JPanel {

  static final int INFO_HEIGHT = Constants.HEIGHT - WorldView.MAP_HEIGHT;
  static final int INFO_WIDTH = Constants.WIDTH;
  static final int X_SPACE = 20;
  static final int Y_SPACE = 10;
  static final int X_OFFSET = INFO_WIDTH / 3;
  // The icons that say "Player name" and "Active fighter" and "Level"
  ImageIcon playerNameIcon = new ImageIcon("resources/world/player-name.png");
  ImageIcon activeFighterIcon = new ImageIcon("resources/world/active-fighter.png");
  ImageIcon levelIcon = new ImageIcon("resources/world/level.png");

  JLabel activeFighterLabel;
  JLabel playerNameLabel;
  JLabel levelLabel;

  // Text areas to hold names of player and active fighter
  JTextArea player;
  JTextArea fighter;
  JTextArea levelText;

  // Strings to hold names of player and active fighter
  //String playerName = "Radwa";
  //String fighterName = "Goku";

  // Image Icons to store the pictures of senzu beans and dragon balls
  ImageIcon senzuBeanImage = new ImageIcon("resources/world/senzu-bean.png");
  ImageIcon dragonBallImage = new ImageIcon("resources/world/dragonball.png");

  // variables to keep track of senzu beans and DBs TODO --> make methods to link them with
  // controller
  int senzuBeans = 0;
  int dragonBalls = 0;
  
  JTextArea noSenzu; 
  
  JTextArea noDragonball; 

  // the level of the active fighter
  int level = 0;

  // ArrayLists to keep the senzu beans owned by the player
  // to be able to easily access them for deletion
  ArrayList<JLabel> dragonBallsList;
  ArrayList<JLabel> senzuBeansList;

  // coordinates of the senzu beans bar
  int x_senzu = X_SPACE;
  final int y_senzu = Y_SPACE / 2 + playerNameIcon.getIconHeight();
  final int senzu_size = 30;

  // coordinates of dragon balls bar
  int x_dragonball = X_SPACE;
  final int y_dragonball = y_senzu + senzu_size + Y_SPACE;
  final int dragonball_size = 30;

  // ImageIcons holding the stamina and ki shapes
  ImageIcon emptyKiIcon = new ImageIcon("resources/world/ki-empty.png"); // empty ki bar that is
                                                                           // not gained yet
  ImageIcon fullKiIcon = new ImageIcon("resources/world/ki-full.png"); // full ki bar that is
                                                                         // gained by the player
  ImageIcon emptyStaminaIcon = new ImageIcon("resources/world/stamina-empty.png");
  ImageIcon fullStaminaIcon = new ImageIcon("resources/world/stamina-full.png");
  ImageIcon fullAbilityIcon = new ImageIcon("resources/world/ability-point-full.png");
  //ImageIcon emptyAbilityIcon = new ImageIcon("resources/world/ability-point-empty.png");

  // coordinates of ki bars
  int x_ki = X_OFFSET;
  final int y_ki = Y_SPACE + activeFighterIcon.getIconHeight();
  // coordinate of stamina bar
  int x_stamina = X_OFFSET;
  int y_stamina = y_ki + 2 * Y_SPACE + fullKiIcon.getIconHeight();

  // coordinates of ablity points bar
  int x_ability = X_OFFSET * 2;
  final int y_ability = Y_SPACE + levelIcon.getIconHeight();

  // variables to indicates amounts of ki, stamin, and ability points
  int maxStamina = 4;
  int stamina = 0;
  int maxKi = 4;
  int ki = 0;
  //int maxAbility = 5;
  int ability = 0;

  // sizes of ki and stamina and ability bars
  final int ki_height = fullKiIcon.getIconHeight();
  final int ki_width = fullKiIcon.getIconWidth();
  final int stamina_size = fullStaminaIcon.getIconHeight();
  final int ability_size = fullAbilityIcon.getIconHeight();

  ArrayList<JLabel> emptyStaminaList;
  ArrayList<JLabel> fullStaminaList;
  ArrayList<JLabel> emptyKiList;
  ArrayList<JLabel> fullKiList;
  //ArrayList<JLabel> emptyAbilityList;
  ArrayList<JLabel> fullAbilityList;

  // variables to store current xp and maximum xp
  int maxXp = 1000;
  int xp = 500;

  // coordinates of Xp bar
  int x_xp = X_OFFSET * 2;
  final int y_xp = Y_SPACE + levelIcon.getIconHeight() + fullAbilityIcon.getIconHeight() + Y_SPACE;

  // dimensions of the Xp bar
  final int max_xp_width = 400;
  final int xp_height = 20;

  public WorldInfoPanel() {
    setSize(INFO_WIDTH, INFO_HEIGHT);
    setLayout(null);
    setOpaque(false);
    //to indicate that there are no senzu beans yet 
    addNoSenzu();
    addNoDragonball();
    
    
    
    // initializing and styling text areas holding the names of player and fighter
    player = new JTextArea();
    player.setFont(new Font("Yu Gothic Light", Font.BOLD, 20));
    player.setEditable(false);
    player.setOpaque(false);
    player.setWrapStyleWord(true);

    fighter = new JTextArea();
    fighter.setFont(new Font("Yu Gothic Light", Font.BOLD, 20));
    fighter.setEditable(false);
    fighter.setOpaque(false);
    fighter.setWrapStyleWord(true);

    levelText = new JTextArea(level + "");
    levelText.setFont(new Font("Yu Gothic Light", Font.BOLD, 20));
    levelText.setEditable(false);
    levelText.setOpaque(false);
    levelText.setWrapStyleWord(true);

    // adding the text are that hold the number of the level
    add(levelText);
    levelText.setBounds(X_OFFSET * 2 + levelIcon.getIconWidth() + X_SPACE, Y_SPACE + 5, 50, 50);

    // making labels to hold the "Player Name" and "active fighter" icons
    playerNameLabel = new JLabel(playerNameIcon);
    activeFighterLabel = new JLabel(activeFighterIcon);
    levelLabel = new JLabel(levelIcon);

    // add player name label in the top left
    add(playerNameLabel);
    playerNameLabel.setBounds(X_SPACE, Y_SPACE, playerNameIcon.getIconWidth(),
        playerNameIcon.getIconHeight());
    // add the actual name of the player
    add(player);
    player.setVisible(true);
    player.setBounds(playerNameIcon.getIconWidth() + X_SPACE, Y_SPACE,
        X_OFFSET - playerNameIcon.getIconWidth(), playerNameIcon.getIconHeight());

    // add active fighter label in the top right half
    add(activeFighterLabel);
    activeFighterLabel.setBounds(X_OFFSET, Y_SPACE, activeFighterIcon.getIconWidth(),
        activeFighterIcon.getIconHeight());
    // adding text field containing fighter name
    add(fighter);
    fighter.setVisible(true);
    fighter.setBounds(X_OFFSET + activeFighterIcon.getIconWidth(), Y_SPACE, X_OFFSET
        - activeFighterIcon.getIconWidth(), activeFighterIcon.getIconHeight());

    // adding the level label
    add(levelLabel);
    levelLabel
        .setBounds(2 * X_OFFSET, Y_SPACE, levelIcon.getIconWidth(), levelIcon.getIconHeight());

    senzuBeansList = new ArrayList<JLabel>();
    dragonBallsList = new ArrayList<JLabel>();
    fullStaminaList = new ArrayList<JLabel>();
    emptyStaminaList = new ArrayList<JLabel>();
    emptyKiList = new ArrayList<JLabel>();
    fullKiList = new ArrayList<JLabel>();
    fullAbilityList = new ArrayList<JLabel>();
    //emptyAbilityList = new ArrayList<JLabel>();


    addAllKi();
    addAllStamina();
    addAllAbility();
    

  }

  public void addSenzuBean() {
     
     
    JLabel senzuBean = new JLabel(senzuBeanImage);
    senzuBeansList.add(senzuBean);
    add(senzuBean);
    senzuBean.setBounds(x_senzu, y_senzu, senzu_size, senzu_size);
    x_senzu += senzuBeanImage.getIconWidth();
    senzuBean.setVisible(true);
    senzuBeans++; 
    if (senzuBeans > 0) {
      if (noSenzu != null)
        remove(noSenzu); 
      
    }
    
  } 

  public void removeSenzuBean() {
    if (senzuBeans > 0) {
    JLabel senzuBean = senzuBeansList.remove(senzuBeans - 1);
    senzuBean.setVisible(false);
    x_senzu -= senzuBeanImage.getIconWidth();
    senzuBeans--; 
    }
    
    if (senzuBeans <= 0) {
      addNoSenzu();
    }
  }

  public void addDragonBall() {
    JLabel dragonBall = new JLabel(dragonBallImage);
    dragonBallsList.add(dragonBall);
    add(dragonBall);
    dragonBall.setBounds(x_dragonball, y_dragonball, dragonball_size, dragonball_size);
    x_dragonball += dragonBallImage.getIconWidth() + X_SPACE / 2;
    dragonBall.setVisible(true);
    dragonBalls++;
    if (dragonBalls > 0) {
      if (noDragonball != null) {
        remove(noDragonball); 
      }
      
    }
  }

  public void removeDragonBall() {
    if (dragonBalls > 0) {
    JLabel dragonBall = dragonBallsList.remove(dragonBalls - 1);
    dragonBall.setVisible(false);
    x_dragonball -= dragonBallImage.getIconWidth() + X_SPACE / 2; 
    dragonBalls--; 
  } 
    if (dragonBalls <= 0) {
      addNoDragonball();
    }
  }

  public void addNoSenzu () {
    noSenzu = new JTextArea("No senzu beans yet."); 
    add(noSenzu);
    noSenzu.setFont(new Font("Forte", Font.PLAIN, 20));
    noSenzu.setOpaque(false);
    noSenzu.setForeground(Color.gray);
    
    
    noSenzu.setBounds(x_senzu, y_senzu, 200, 30);
    if (senzuBeans > 0) {
      noSenzu.setVisible(false);
    }
    
  } 
  
  public void addNoDragonball () {
    noDragonball = new JTextArea("No dragon balls yet."); 
    add(noDragonball); 
    noDragonball.setFont(new Font("Forte", Font.PLAIN, 20));
    noDragonball.setForeground(Color.gray);
    noDragonball.setOpaque(false);
    noDragonball.setBounds(x_dragonball, y_dragonball, 200, 30);
     if (dragonBalls > 0) {
       noDragonball.setVisible(false);
     }

  }
  
  public void addAllKi() {
    for (int i = 0; i < maxKi; i++) {
      //JLabel emptyKi = new JLabel(emptyKiIcon);
      //emptyKi.setToolTipText("The maximum ki bars of the active fighter");
      JLabel fullKi = new JLabel(fullKiIcon);
      fullKi.setToolTipText("The fighter's maximum ki");
      fullKiList.add(fullKi);
      //emptyKiList.add(emptyKi);

      //add(emptyKi);
      add(fullKi);
      //emptyKi.setBounds(x_ki, y_ki, ki_width, ki_height);
      fullKi.setBounds(x_ki, y_ki, ki_width, ki_height);
      x_ki += ki_width + X_SPACE;
      fullKi.setVisible(true);
      //System.out.println("ki");
      //emptyKi.setVisible(true);
//      if (i < ki) {
//      fullKi.setVisible(true);
//      } else {
//        fullKi.setVisible(false);
//      }

    }
  }

  public void addMaxKi() {
    maxKi++;
    //JLabel emptyKi = new JLabel(emptyKiIcon);
    JLabel fullKi = new JLabel(fullKiIcon);
    fullKiList.add(fullKi);
    //emptyKiList.add(emptyKi);

    //add(emptyKi);
    add(fullKi);
    //emptyKi.setBounds(x_ki, y_ki, ki_width, ki_height);
    fullKi.setBounds(x_ki, y_ki, ki_width, ki_height);
    x_ki += ki_width + X_SPACE;
    
    
    //emptyKi.setVisible(false);
    fullKi.setVisible(true);
  }

  public void addAllStamina() {

    for (int i = 0; i < maxStamina; i++) {
//      JLabel emptyStamina = new JLabel(emptyStaminaIcon);
//      emptyStamina.setToolTipText("Maximum stamina of the active fighter");
      JLabel fullStamina = new JLabel(fullStaminaIcon);
      fullStaminaList.add(fullStamina);
      fullStamina.setToolTipText("The active fighter's maximum stamina.");
      //emptyStaminaList.add(emptyStamina);

      //add(emptyStamina);
      add(fullStamina);
      //emptyStamina.setBounds(x_stamina, y_stamina, stamina_size, stamina_size);
      fullStamina.setBounds(x_stamina, y_stamina, stamina_size, stamina_size);
      x_stamina += stamina_size + X_SPACE;

      //emptyStamina.setVisible(false);
      fullStamina.setVisible(true);
      //System.out.println("stamina");
//      if (i < stamina) {
//      fullStamina.setVisible(true);
//      }
//      else {
//        fullStamina.setVisible(false);
//      }

    }
  }

  public void addMaxStamina() {
    maxStamina++;
    //JLabel emptyStamina = new JLabel(emptyStaminaIcon);
    JLabel fullStamina = new JLabel(fullStaminaIcon);
    fullStaminaList.add(fullStamina);
    //emptyStaminaList.add(emptyStamina);

    //add(emptyStamina);
    add(fullStamina);
    //emptyStamina.setBounds(x_stamina, y_stamina, stamina_size, stamina_size);
    fullStamina.setBounds(x_stamina, y_stamina, stamina_size, stamina_size);
    x_stamina += stamina_size + X_SPACE;

    //emptyStamina.setVisible(true);
    fullStamina.setVisible(true);

  }

  public void addAllAbility() {
    for (int i = 0; i < ability; i++) {
      //JLabel emptyAbility = new JLabel(emptyAbilityIcon);
      JLabel fullAbility = new JLabel(fullAbilityIcon);

      fullAbilityList.add(fullAbility);
      //emptyAbilityList.add(emptyAbility);

      //add(emptyAbility);
      add(fullAbility);

      //emptyAbility.setBounds(x_ability, y_ability, ability_size, ability_size);
      fullAbility.setBounds(x_ability, y_ability, ability_size, ability_size);
      x_ability += ability_size + X_SPACE;

      //emptyAbility.setVisible(true);
      fullAbility.setVisible(true);
    }
  }

  public void addKi() {
    if (ki < maxKi) {
      ki++;
      emptyKiList.get(ki - 1).setVisible(false);
      fullKiList.get(ki - 1).setVisible(true);
    }
  }

  public void removeKi() {
    if (ki > 0) {
    emptyKiList.get(ki - 1).setVisible(true);
    fullKiList.get(ki - 1).setVisible(false);
    ki--;
    }
  }

  public void addStamina() {
    if (stamina < maxStamina) {
      stamina++;
      emptyStaminaList.get(stamina - 1).setVisible(false);
      fullStaminaList.get(stamina - 1).setVisible(true);

    }
  }

  public void removeStamina() {
    if (stamina > 0) {
      emptyStaminaList.get(stamina - 1).setVisible(true);
      fullStaminaList.get(stamina - 1).setVisible(false);
      stamina--;
    }
  }

  public void addAbilityPoint() {
      JLabel abilityPoint = new JLabel(fullAbilityIcon);     
      ability++;
      //emptyAbilityList.get(ability - 1).setVisible(false);
      fullAbilityList.add(abilityPoint); 
      add(abilityPoint); 
      abilityPoint.setBounds(x_ability, y_ability, fullAbilityIcon.getIconWidth(), fullAbilityIcon.getIconHeight());
      x_ability += fullAbilityIcon.getIconWidth() + X_SPACE; 
    
  }

  public void removeAbilityPoint() {
    if (ability > 0) {
      JLabel abilityPoint = fullAbilityList.get(ability-1); 
      abilityPoint.setVisible(false); 
      x_ability -= fullAbilityIcon.getIconWidth() + X_SPACE; 
    }
  }
  public void setPlayerName(String playerName) {
    player.setText(playerName); 
    
  } 
  public void setFighterName(String fighterName) {
    fighter.setText(fighterName); 
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.GRAY);
    g.fillRect(x_xp, y_xp, max_xp_width, xp_height);

    g.setColor(Color.CYAN);
    int xp_width = (xp * max_xp_width) / maxXp;
    g.fillRect(x_xp, y_xp, xp_width, xp_height);

  }

  public void updateXp(int xp) {
    System.out.println("old xp: " + this.xp + " new xp: " + xp);
    this.xp = xp;
    repaint();
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
    levelText.setText(this.level + "");
  }
  
  public void updateFighterInfo (PlayableFighter fighter) {
    removeAll(); 
    setLevel(fighter.getLevel()); 
    setFighterName(fighter.getName());
    maxXp = fighter.getTargetXp(); 
    updateXp(fighter.getXp()); 
    maxStamina = fighter.getMaxStamina(); 
    //stamina = fighter.getStamina(); 
    maxKi = fighter.getMaxKi(); 
    //ki = fighter.getKi(); 
    
    ability = fighter.getAbilityPoints(); 
    
    addAllKi(); 
    addAllStamina(); 
    addAllAbility(); 
  } 
  
  public void removeAll () {
    for (JLabel emptyStamina: emptyStaminaList) {
      remove(emptyStamina); 
      //emptyStamina.setVisible(false); 
    } 
    emptyStaminaList = new ArrayList<JLabel>(); 
    for (JLabel fullStamina: fullStaminaList) {
      remove(fullStamina); 
      //fullStamina.setVisible(false);
    }
    fullStaminaList = new ArrayList<JLabel>(); 
    for (JLabel fullKi: fullKiList) {
      remove(fullKi); 
      
    } 
    fullKiList = new ArrayList<JLabel>(); 
    
    for(JLabel emptyKi: emptyKiList) {
      remove(emptyKi); 
    }
    emptyKiList = new ArrayList<JLabel>(); 
    
    for (JLabel abilityPoint: fullAbilityList) {
      remove(abilityPoint); 
     }
    
    fullAbilityList = new ArrayList<JLabel>(); 
    x_stamina = X_OFFSET; 
    x_ki = X_OFFSET; 
    x_ability = 2*X_OFFSET; 
  }

  public static void main(String[] args) {
    JFrame f = new JFrame();
    f.setSize(Constants.WIDTH, Constants.HEIGHT);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    WorldInfoPanel info = new WorldInfoPanel();
    f.setLayout(null);
    f.add(info);
    info.setBounds(0, 0, INFO_WIDTH, INFO_WIDTH);
    info.addDragonBall();
    info.addDragonBall();
    info.removeDragonBall();
    info.removeDragonBall();
    
    info.addSenzuBean();
    info.removeSenzuBean();
    info.removeSenzuBean();
    info.addMaxKi();
    info.addMaxKi();
    

    f.setVisible(true);
  }

  public int getMaxXp() {
    return maxXp;
  }

  public void setMaxXp(int maxXp) {
    this.maxXp = maxXp;
  }
}
