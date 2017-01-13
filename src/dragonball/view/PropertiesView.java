package dragonball.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dragonball.controller.GameController;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.player.Player;

public class PropertiesView extends JPanel
{
  private JButton back;
  private String activeFighter;
  private ArrayList<String> fighters;
  private ArrayList<String> remainingFighters;
  private Player player;
  private GameController gameController;
  private JButton ActiveFighterButton;
  private ArrayList<JButton> fightersButtons;
  private JLabel add_fighter_label ;
  private JLabel change_fighter_label ;
  private JButton upgrade_button ;
  private JButton assign_attack_button;
  private JButton save_button;
  
  private PropertiesPlayerInfoPanel playerInfoPanel;
  private PropertiesFighterInfoPanel fighterInfoPanel;

  static final int BACK_WIDTH = 200, BACK_HEIGHT = 100,
      SMALL_FIGHTER_WIDTH = 100, SMALL_FIGHTER_HEIGHT = 200,
      BIG_FIGHTER_WIDTH = 200, BIG_FIGHTER_HEIGHT = 400,
      INIT_REMAINING_SPACE = Constants.WIDTH - 600, INIT_FIGHTRS_SPACE = 220,
      REMAINING_FIGHTERS_Y = 490;

  public PropertiesView()
  {
    setSize(Constants.WIDTH,Constants.HEIGHT);
    setBackground(Color.BLACK);
    setLayout(null);

    ImageIcon backIcon = new ImageIcon("resources/back-button.png");
    Image backImage = (backIcon.getImage()).getScaledInstance(BACK_WIDTH, BACK_HEIGHT, Image.SCALE_DEFAULT);
    back = new JButton(new ImageIcon(backImage));
    back.setBorder(BorderFactory.createEmptyBorder());
    back.setContentAreaFilled(false);
    back.setActionCommand("back");
    add(back);
    back.setBounds(20,580, BACK_WIDTH, BACK_HEIGHT);
    back.setVisible(true);

    
    ImageIcon add_fighter_icon = new ImageIcon("resources/properties/add fighter.png");
    //mage add_fighter_image = (add_fighter_icon.getImage()).getScaledInstance(200,90 , Image.SCALE_DEFAULT);
    add_fighter_label = new JLabel(add_fighter_icon);
    add(add_fighter_label);
    add_fighter_label.setBounds(INIT_REMAINING_SPACE + 150 ,REMAINING_FIGHTERS_Y - 90, add_fighter_icon.getIconWidth(),add_fighter_icon.getIconHeight());
    
    
    ImageIcon change_fighter_icon = new ImageIcon("resources/properties/change active fighter.png");
    Image change_fighter_image = (change_fighter_icon.getImage()).getScaledInstance(350,90 , Image.SCALE_DEFAULT);
    change_fighter_label = new JLabel(new ImageIcon(change_fighter_image));
    add(change_fighter_label);
    change_fighter_label.setBounds(INIT_FIGHTRS_SPACE + 80 ,REMAINING_FIGHTERS_Y - 90, 350, 90);
    
    ImageIcon upgrade_icon = new ImageIcon("resources/properties/upgrade.png");
    Image upgrade_image = (upgrade_icon.getImage()).getScaledInstance(170, 100, Image.SCALE_DEFAULT);
    upgrade_button = new JButton(new ImageIcon(upgrade_image));
    upgrade_button.setContentAreaFilled(false);
    upgrade_button.setActionCommand("upgrade");
    add(upgrade_button);
    upgrade_button.setBounds(Constants.WIDTH - 215, 190, 170, 100);
    upgrade_button.setVisible(true);
    
    ImageIcon assign_attack_icon = new ImageIcon("resources/properties/assign attack button.png");
    Image assign_attack_image = (assign_attack_icon.getImage()).getScaledInstance(210, 100, Image.SCALE_DEFAULT);
    assign_attack_button = new JButton(new ImageIcon(assign_attack_image));
    assign_attack_button.setContentAreaFilled(false);
    assign_attack_button.setActionCommand("assign_attack");
    add(assign_attack_button);
    assign_attack_button.setBounds(Constants.WIDTH - 235, 300, 210, 100);
    assign_attack_button.setVisible(true);
    
    ImageIcon save_icon = new ImageIcon("resources/properties/save button.png");
    Image save_image = (save_icon.getImage()).getScaledInstance(210, 160, Image.SCALE_DEFAULT);
    save_button = new JButton(new ImageIcon(save_image));
    save_button.setContentAreaFilled(false);
    save_button.setActionCommand("save_game");
    add(save_button);
    save_button.setBounds(Constants.WIDTH - 235, 10, 210, 160);
    save_button.setVisible(true);
    
  }
  
  
  
  
  public PropertiesPlayerInfoPanel getPlayerInfoPanel() {
    return playerInfoPanel;
  }




  public void setPlayerInfoPanel()
  {
    playerInfoPanel = new PropertiesPlayerInfoPanel();
    playerInfoPanel.displayPlayerInfo(player.getName(),player.getExploredMaps());
    add(playerInfoPanel);
   playerInfoPanel.setBounds(220,0,900,50);
    playerInfoPanel.setVisible(true);
  }

  public void setFighterInfoPanel()
  {
   if(fighterInfoPanel != null)
     remove(fighterInfoPanel);
    fighterInfoPanel = new PropertiesFighterInfoPanel();
    PlayableFighter fighter = player.getActiveFighter();
    fighterInfoPanel.displayFighterInfo(fighter.getName(), fighter.getLevel(), fighter.getMaxKi(), fighter.getMaxStamina(),
        fighter.getMaxHealthPoints(), fighter.getXp(), fighter.getTargetXp(), fighter.getAbilityPoints(),fighter.getPhysicalDamage(),fighter.getBlastDamage());
    add(fighterInfoPanel);
    fighterInfoPanel.setBounds(220, 60, 900, 340);
    fighterInfoPanel.setVisible(true);
    
    
  }



  public JButton getActiveFighterButton() {
    return ActiveFighterButton;
  }


  public void setActiveFighterButton(JButton activeFighterButton) {
    ActiveFighterButton = activeFighterButton;
  }


  public void setActiveFighter(String activeFighter) {
    this.activeFighter = activeFighter;
  }




  public void setListenersToGameController(ActionListener a)
  {
    back.addActionListener(a);
    upgrade_button.addActionListener(a);
    assign_attack_button.addActionListener(a);
    save_button.addActionListener(a);
  }

  public void setGameController(GameController gameController)
  {
    this.gameController = gameController;
  }

  public void setActiveFighter(PlayableFighter activeFighter) {

    this.activeFighter = activeFighter.getClass().getName();
  }


  public void removeActiveFighterButton()
  {
    if(ActiveFighterButton != null)
    this.ActiveFighterButton.setVisible(false);
  }
  
  public void removeFightersButtons()
  {
    if(fightersButtons != null)
    {
      for(JButton button : fightersButtons)
      {
        button.setEnabled(false);
        button.setVisible(false);
      }
    }
  }
  
  public void setFighters(ArrayList<PlayableFighter> ActiveFighters) {
    boolean activeFighterPassed = false;
    if(ActiveFighters.size() < 5)
    {
      add_fighter_label.setVisible(true);
    }
    else
    {
      add_fighter_label.setVisible(false);
    }
    if(ActiveFighters.size() > 1)
    {
      change_fighter_label.setVisible(true);
    }
    else
    {
      change_fighter_label.setVisible(false);
    }
    fighters = new ArrayList<String>();
    for(PlayableFighter fighter: ActiveFighters)
    {
      this.fighters.add(fighter.getClass().getName());
    }

    removeFightersButtons();
    fightersButtons = new ArrayList<JButton>();
    for(String ActiveFighter: fighters)
    {
      StringTokenizer st = new StringTokenizer(ActiveFighter,".");
      for (int i = 0; i < 4; i++) {
        st.nextToken();
      }
      String ActiveFighterName = st.nextToken();
      
      if(ActiveFighter.equals(activeFighter))
      {
        removeActiveFighterButton();
        ActiveFighterButton = null;
        ActiveFighterButton = generateBigButton(activeFighter);
        add(ActiveFighterButton);
        ActiveFighterButton.setBounds(10, 20, BIG_FIGHTER_WIDTH, BIG_FIGHTER_HEIGHT);
        ActiveFighterButton.setVisible(true);
        activeFighterPassed = true;
      }
      else
      {
        int i = fighters.indexOf(ActiveFighter);
        JButton button = generateSmallButton(ActiveFighter);
        add(button);
        button.setActionCommand(ActiveFighterName+"_chosen");
        if(!activeFighterPassed)
          button.setBounds(INIT_FIGHTRS_SPACE + i*SMALL_FIGHTER_WIDTH+i*40, REMAINING_FIGHTERS_Y, SMALL_FIGHTER_WIDTH,SMALL_FIGHTER_HEIGHT);
        else
          button.setBounds(INIT_FIGHTRS_SPACE + (i-1)*SMALL_FIGHTER_WIDTH+(i-1)*40, REMAINING_FIGHTERS_Y, SMALL_FIGHTER_WIDTH,SMALL_FIGHTER_HEIGHT);
        button.addActionListener(gameController);
        fightersButtons.add(button);
        button.setVisible(true);
      }
    }
    
   setFighterInfoPanel();

    if(remainingFighters == null)
    setRemainingFighters();

  }


  public ArrayList<String> getRemainingFighters() {
    return remainingFighters;
  }


  public void setRemainingFighters()
  {
    remainingFighters = new ArrayList<String>();
    ArrayList<String> allFighters = new ArrayList<String>();
    allFighters.add("dragonball.model.character.fighter.Saiyan");
    allFighters.add("dragonball.model.character.fighter.Earthling");
    allFighters.add("dragonball.model.character.fighter.Frieza");
    allFighters.add("dragonball.model.character.fighter.Namekian");
    allFighters.add("dragonball.model.character.fighter.Majin");
    for(String fighter: allFighters)
    {
      if(!fighters.contains(fighter) && !fighter.equals(activeFighter))
        remainingFighters.add(fighter);
    }

    for (String remainingFighter: remainingFighters)
    {
      StringTokenizer st = new StringTokenizer(remainingFighter,".");
      for (int i = 0; i < 4; i++) {
        st.nextToken();
      }
      String remainingFighterName = st.nextToken();
      int i = remainingFighters.indexOf(remainingFighter);

      JButton button =generateSmallButton(remainingFighter);
      add(button);
      button.setActionCommand(remainingFighterName+"_added");
      button.setBounds(INIT_REMAINING_SPACE + i*SMALL_FIGHTER_WIDTH + i*40, REMAINING_FIGHTERS_Y, SMALL_FIGHTER_WIDTH,SMALL_FIGHTER_HEIGHT);
      button.addActionListener(gameController);
      button.setVisible(true);
    }


  }


  public Player getPlayer() {
    return player;
  }


  public void setPlayer(Player player) {
    this.player = player;
  }


  public String getActiveFighter() {
    return activeFighter;
  }


  public ArrayList<String> getFighters() {
    return fighters;
  }


  public JButton generateSmallButton(String FullName)
  {
    StringTokenizer st = new StringTokenizer(FullName,".");
    for (int i = 0; i < 4; i++) {
      st.nextToken();
    }
    String name = st.nextToken();
    ImageIcon icon = new ImageIcon("resources/"+name+"-edit.png"); 
    Image image = icon.getImage().getScaledInstance(SMALL_FIGHTER_WIDTH, SMALL_FIGHTER_HEIGHT, Image.SCALE_DEFAULT); 
    JButton button = new JButton(new ImageIcon(image));
    return button;
  }

  public JButton generateBigButton(String FullName)
  {
    StringTokenizer st = new StringTokenizer(FullName,".");
    for (int i = 0; i < 4; i++) {
      st.nextToken();
    }
    String name = st.nextToken();
    ImageIcon icon = new ImageIcon("resources/"+name+"-edit.png"); 
    Image image = icon.getImage().getScaledInstance(BIG_FIGHTER_WIDTH, BIG_FIGHTER_HEIGHT, Image.SCALE_DEFAULT); 
    JButton button = new JButton(new ImageIcon(image));
    return button;
  }
  
  
  
  
  public static void main(String[] args)
  {
    JFrame f = new JFrame();
    f.setSize(Constants.WIDTH,Constants.HEIGHT);
    PropertiesView p = new PropertiesView();
    f.add(p);
    f.setVisible(true);
  }
}
