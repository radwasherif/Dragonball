package dragonball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleEventType;
import dragonball.model.battle.BattleOpponent;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.game.Game;
import dragonball.model.game.GameListener;
import dragonball.view.AssignAttackView;
import dragonball.view.GameView;
import dragonball.view.UpgradeView;


public class GameController implements GameListener, ActionListener, Serializable {
  private Game game;
  private GameView gameView;
  private Battle battle;
  private UpgradeView upgradeView;
  private AssignAttackView assignAttackView;
  private String lastSavedController;
  private Dragon dragon;
  // we need a global variable to hold the selected attack names from the combo box, to be able to
  // pass them in the handling of the button event
  private String selectedUltimateAttack;
  private String selectedSuperAttack;

  // private String playerName;
  // private String fighterName;

  public GameController() {
    
    try {
      game = new Game();
    } catch (IOException e) {
      System.out.println("Problem with loading CSV files");
      e.printStackTrace();
    }
    System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
    game.setListener(this);

    gameView = new GameView();
    gameView.getStart().setVisible(true);

    upgradeView = new UpgradeView();

    assignAttackView = new AssignAttackView();

    setAllListeners();
    
    
    gameView.addWindowListener(new java.awt.event.WindowAdapter(){
      public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        if(JOptionPane.showConfirmDialog(gameView , "Are you sure you want to exit ?" , "Really Closing ?" , JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
          System.exit(0);
         
        }
          
      }
    });
    
    
    
  }

  public void setAllListeners() {
    gameView.setListenersToGameController(this);
    gameView.getWorldView().setGameController(this);
    upgradeView.setAllListeners(this);
    assignAttackView.setAllListeners(this);

    // System.out.println("Hi");
  }

  @SuppressWarnings("deprecation")
  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getActionCommand().equals("new")) {

      String playerName = JOptionPane.showInputDialog(null, "Enter your name", "");
      while (true) {
        if (playerName != null && playerName.length() == 0)
          playerName = JOptionPane.showInputDialog(null,
              "You haven't entered a name. \n Please enter your name to start playing.", "");
        else
          break;
      }
      if (playerName != null) {
        try {
          game = new Game();
          game.setListener(this);
        } catch (UnknownAttackTypeException e1) {

          e1.printStackTrace();
        } catch (MissingFieldException e1) {
          e1.printStackTrace();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
        game.getPlayer().setName(playerName);



        gameView.getStart().setVisible(false);
        gameView.getWorldView().getInfo().setPlayerName(playerName);
        gameView.getChooseFighter().setVisible(true);

      }
    } else if (e.getActionCommand().equals("load")) {
      try {

        BufferedReader reader = new BufferedReader(new FileReader("all_saved_games.csv"));
        String line;
        ArrayList<String> saved_games = new ArrayList<String>();
        while (( line = reader.readLine()) != null)
        {
          saved_games.add(line);
        }
        String fileChoosed = (String) JOptionPane.showInputDialog(null,"Choose the game you want to load","choose saved game",JOptionPane.OK_CANCEL_OPTION
            , null,saved_games.toArray(),"");
        if(fileChoosed != null && fileChoosed.length() != 0)
        {
          gameView.setEnabled(false);
          gameView.setVisible(false);
          gameView.disable();
          load(fileChoosed);
          gameView.setVisible(true);
        }
      }catch (FileNotFoundException e1) {
        JOptionPane.showMessageDialog(null, "You haven't saved any game!");
        // e1.printStackTrace();
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }

    }

    if (e.getActionCommand().equals("saiyan")) {
      try {

        String Name = JOptionPane.showInputDialog(null, "Enter fighter's name", "");
        while (true) {
          if (Name != null && Name.length() == 0)
            Name = JOptionPane.showInputDialog(null,
                "You haven't entered a name. \n Please enter your fighter's to start playing.", "");
          else
            break;
        }
        if (Name != null) {
          Saiyan saiyan = new Saiyan(Name);
          game.getPlayer().getFighters().add(saiyan);
          game.getPlayer().setActiveFighter(saiyan);
          gameView.getChooseFighter().setVisible(false);
          gameView.getWorldView().setFighterImage("resources/world/saiyan.png");
          gameView.getWorldView().getInfo().updateFighterInfo(saiyan);
          gameView.getWorldView().setPlayer();
          gameView.getWorldView().setVisible(true);
          gameView.getWorldView().requestFocusInWindow();
        }
      } catch (NotEnoughKiException e1) {

        e1.printStackTrace();
      }

    }
    if (e.getActionCommand().equals("earthling")) {
      try {
        String Name = JOptionPane.showInputDialog(null, "Enter fighter's name", "");
        while (true) {
          if (Name != null && Name.length() == 0)
            Name = JOptionPane.showInputDialog(null,
                "You haven't entered a name. \n Please enter fighter's name to start playing.", "");
          else
            break;
        }
        if (Name != null) {
          Earthling earthling = new Earthling(Name);
          game.getPlayer().getFighters().add(earthling);
          game.getPlayer().setActiveFighter(earthling);
          gameView.getChooseFighter().setVisible(false);
          gameView.getWorldView().setFighterImage("resources/world/earthling.png");
          gameView.getWorldView().getInfo().updateFighterInfo(earthling);
          gameView.getWorldView().setPlayer();
          gameView.getWorldView().setVisible(true);
          gameView.getWorldView().requestFocusInWindow();

        }
      } catch (NotEnoughKiException e1) {

        e1.printStackTrace();
      }

    }

    if (e.getActionCommand().equals("majin")) {
      try {
        String Name = JOptionPane.showInputDialog(null, "Enter fighter's name", "");
        while (true) {
          if (Name != null && Name.length() == 0)
            Name = JOptionPane.showInputDialog(null,
                "You haven't entered a name. \n Please enter fighter's name to start playing.", "");
          else
            break;
        }
        if (Name != null) {
          Majin majin = new Majin(Name);
          game.getPlayer().getFighters().add(majin);
          game.getPlayer().setActiveFighter(majin);
          gameView.getChooseFighter().setVisible(false);
          gameView.getWorldView().setFighterImage("resources/world/majin.png");
          gameView.getWorldView().getInfo().updateFighterInfo(majin);
          gameView.getWorldView().setPlayer();
          gameView.getWorldView().setVisible(true);
          gameView.getWorldView().requestFocusInWindow();

        }
      } catch (NotEnoughKiException e1) {

        e1.printStackTrace();
      }

    }

    if (e.getActionCommand().equals("frieza")) {
      try {
        String Name = JOptionPane.showInputDialog(null, "Enter fighter's name", "");
        while (true) {
          if (Name != null && Name.length() == 0)
            Name = JOptionPane.showInputDialog(null,
                "You haven't entered a name. \n Please enter fighter's name to start playing.", "");
          else
            break;
        }
        if (Name != null) {
          Frieza frieza = new Frieza(Name);
          game.getPlayer().getFighters().add(frieza);
          game.getPlayer().setActiveFighter(frieza);
          gameView.getChooseFighter().setVisible(false);
          gameView.getWorldView().getInfo().updateFighterInfo(frieza);
          gameView.getWorldView().setFighterImage("resources/world/frieza.png");
          gameView.getWorldView().setPlayer();
          gameView.getWorldView().setVisible(true);
          gameView.getWorldView().requestFocusInWindow();

        }
      } catch (NotEnoughKiException e1) {

        e1.printStackTrace();
      }

    }

    if (e.getActionCommand().equals("namekian")) {
      try {
        String Name = JOptionPane.showInputDialog(null, "Enter fighter's name", "");
        while (true) {
          if (Name != null && Name.length() == 0)
            Name = JOptionPane.showInputDialog(null,
                "You haven't entered a name. \n Please enter fighter's name to start playing.", "");
          else
            break;
        }
        if (Name != null) {
          Namekian namekian = new Namekian(Name);
          game.getPlayer().getFighters().add(namekian);
          game.getPlayer().setActiveFighter(namekian);
          gameView.getChooseFighter().setVisible(false);
          gameView.getWorldView().setFighterImage("resources/world/namekian.png");
          gameView.getWorldView().getInfo().updateFighterInfo(namekian);
          gameView.getWorldView().setPlayer();
          gameView.getWorldView().setVisible(true);
          gameView.getWorldView().requestFocusInWindow();

        }
      } catch (NotEnoughKiException e1) {

        e1.printStackTrace();
      }

    }

    if (e.getActionCommand().equals("player")) {
      gameView.getPropertiesView().setGameController(this);
      gameView.getPropertiesView().requestFocusInWindow();
      gameView.getPropertiesView().setPlayer(game.getPlayer());
      gameView.getPropertiesView().setActiveFighter(game.getPlayer().getActiveFighter());
      gameView.getPropertiesView().setFighters(game.getPlayer().getFighters());
      gameView.getPropertiesView().setPlayerInfoPanel();
      gameView.getWorldView().setVisible(false);
      gameView.getPropertiesView().setVisible(true);
    }

    if (e.getActionCommand().equals("back")) {
      gameView.getPropertiesView().setVisible(false);
      gameView.getWorldView().setVisible(true);
      gameView.getWorldView().requestFocusInWindow();
    }

    if (e.getActionCommand().equals("Saiyan_added")) {
      String Name = JOptionPane.showInputDialog(null, "Enter fighter's name", "");
      while (true) {
        if (Name != null && Name.length() == 0)
          Name = JOptionPane.showInputDialog(null,
              "You haven't entered a name. \n Please enter fighter's name to start playing.", "");
        else
          break;
      }
      if (Name != null) {
        try {
          game.getPlayer().createFighter('S', Name);
        } catch (NotEnoughKiException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        ((JComponent) e.getSource()).setEnabled(false);
        ((JComponent) e.getSource()).setVisible(false);
        gameView.getPropertiesView().setFighters(game.getPlayer().getFighters());
      }
    }
    if (e.getActionCommand().equals("Frieza_added")) {
      String Name = JOptionPane.showInputDialog(null, "Enter fighter's name", "");
      while (true) {
        if (Name != null && Name.length() == 0)
          Name = JOptionPane.showInputDialog(null,
              "You haven't entered a name. \n Please enter fighter's name to start playing.", "");
        else
          break;
      }
      if (Name != null) {
        try {
          game.getPlayer().createFighter('F', Name);
        } catch (NotEnoughKiException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        ((JComponent) e.getSource()).setEnabled(false);
        ((JComponent) e.getSource()).setVisible(false);
        gameView.getPropertiesView().setFighters(game.getPlayer().getFighters());
      }
    }
    if (e.getActionCommand().equals("Namekian_added")) {

      String Name = JOptionPane.showInputDialog(null, "Enter fighter's name", "");
      while (true) {
        if (Name != null && Name.length() == 0)
          Name = JOptionPane.showInputDialog(null,
              "You haven't entered a name. \n Please enter fighter's name to start playing.", "");
        else
          break;
      }
      if (Name != null) {
        try {
          game.getPlayer().createFighter('N', Name);
        } catch (NotEnoughKiException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        ((JComponent) e.getSource()).setEnabled(false);
        ((JComponent) e.getSource()).setVisible(false);
        gameView.getPropertiesView().setFighters(game.getPlayer().getFighters());
      }
    }
    if (e.getActionCommand().equals("Earthling_added")) {

      String Name = JOptionPane.showInputDialog(null, "Enter fighter's name", "");
      while (true) {
        if (Name != null && Name.length() == 0)
          Name = JOptionPane.showInputDialog(null,
              "You haven't entered a name. \n Please enter fighter's name to start playing.", "");
        else
          break;
      }
      if (Name != null) {
        try {
          game.getPlayer().createFighter('E', Name);
        } catch (NotEnoughKiException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        ((JComponent) e.getSource()).setEnabled(false);
        ((JComponent) e.getSource()).setVisible(false);
        gameView.getPropertiesView().setFighters(game.getPlayer().getFighters());
      }
    }
    if (e.getActionCommand().equals("Majin_added")) {

      String Name = JOptionPane.showInputDialog(null, "Enter fighter's name", "");
      while (true) {
        if (Name != null && Name.length() == 0)
          Name = JOptionPane.showInputDialog(null,
              "You haven't entered a name. \n Please enter fighter's name to start playing.", "");
        else
          break;
      }
      if (Name != null) {
        try {
          game.getPlayer().createFighter('M', Name);
        } catch (NotEnoughKiException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        ((JComponent) e.getSource()).setEnabled(false);
        ((JComponent) e.getSource()).setVisible(false);
        gameView.getPropertiesView().setFighters(game.getPlayer().getFighters());
      }
    }

    if (e.getActionCommand().equals("Saiyan_chosen")) {
      ArrayList<PlayableFighter> fighters = game.getPlayer().getFighters();
      for (PlayableFighter f : fighters) {
        if (f instanceof Saiyan) {
          game.getPlayer().setActiveFighter(f);
          gameView.getWorldView().getInfo().updateFighterInfo(f);
          gameView.getPropertiesView().setActiveFighter(f);
          break;
        }
      }
      ((JComponent) e.getSource()).setEnabled(false);
      ((JComponent) e.getSource()).setVisible(false);
      gameView.getPropertiesView().setFighters(game.getPlayer().getFighters());
      gameView.getWorldView().setFighterImage("resources/world/saiyan.png");
      gameView.getWorldView().setPlayer();
    }

    if (e.getActionCommand().equals("Frieza_chosen")) {
      ArrayList<PlayableFighter> fighters = game.getPlayer().getFighters();
      for (PlayableFighter f : fighters) {
        if (f instanceof Frieza) {
          game.getPlayer().setActiveFighter(f);
          gameView.getPropertiesView().setActiveFighter(f);
          gameView.getWorldView().getInfo().updateFighterInfo(f);
          break;
        }
      }
      ((JComponent) e.getSource()).setEnabled(false);
      ((JComponent) e.getSource()).setVisible(false);
      gameView.getPropertiesView().setFighters(game.getPlayer().getFighters());
      gameView.getWorldView().setFighterImage("resources/world/frieza.png");
      gameView.getWorldView().setPlayer();
    }

    if (e.getActionCommand().equals("Earthling_chosen")) {
      ArrayList<PlayableFighter> fighters = game.getPlayer().getFighters();
      for (PlayableFighter f : fighters) {
        if (f instanceof Earthling) {
          game.getPlayer().setActiveFighter(f);
          gameView.getPropertiesView().setActiveFighter(f);
          gameView.getWorldView().getInfo().updateFighterInfo(f);
          break;
        }
      }
      ((JComponent) e.getSource()).setEnabled(false);
      ((JComponent) e.getSource()).setVisible(false);
      gameView.getPropertiesView().setFighters(game.getPlayer().getFighters());
      gameView.getWorldView().setFighterImage("resources/world/earthling.png");
      gameView.getWorldView().setPlayer();
    }

    if (e.getActionCommand().equals("Majin_chosen")) {
      ArrayList<PlayableFighter> fighters = game.getPlayer().getFighters();
      for (PlayableFighter f : fighters) {
        if (f instanceof Majin) {
          game.getPlayer().setActiveFighter(f);
          gameView.getPropertiesView().setActiveFighter(f);
          gameView.getWorldView().getInfo().updateFighterInfo(f);
          break;
        }
      }
      ((JComponent) e.getSource()).setEnabled(false);
      ((JComponent) e.getSource()).setVisible(false);
      gameView.getPropertiesView().setFighters(game.getPlayer().getFighters());
      gameView.getWorldView().setFighterImage("resources/world/majin.png");
      gameView.getWorldView().setPlayer();
    }

    if (e.getActionCommand().equals("Namekian_chosen")) {
      ArrayList<PlayableFighter> fighters = game.getPlayer().getFighters();
      for (PlayableFighter f : fighters) {
        if (f instanceof Namekian) {
          game.getPlayer().setActiveFighter(f);
          gameView.getPropertiesView().setActiveFighter(f);
          gameView.getWorldView().getInfo().updateFighterInfo(f);
          break;
        }
      }
      ((JComponent) e.getSource()).setEnabled(false);
      ((JComponent) e.getSource()).setVisible(false);
      gameView.getPropertiesView().setFighters(game.getPlayer().getFighters());
      gameView.getWorldView().setFighterImage("resources/namekian.jpg");
      gameView.getWorldView().setPlayer();
    }

    if (e.getActionCommand().equals("upgrade")) {
      //game.getPlayer().getActiveFighter().setAbilityPoints(1);
      if (game.getPlayer().getActiveFighter().getAbilityPoints() == 0) {
        JOptionPane.showMessageDialog(null, "You don't have ability points to upgrade your fighter!");

        // exception message
      } else {

        upgradeView.setVisible(true);
        upgradeView.requestFocusInWindow();
      }

    }

    if (e.getActionCommand().equals("increase_HP")) {

      try {
        game.getPlayer().upgradeFighter(game.getPlayer().getActiveFighter(), 'H');
        gameView.getPropertiesView().setFighters(game.getPlayer().getFighters());
      } catch (NotEnoughAbilityPointsException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }

      upgradeView.setVisible(false);
      gameView.getPropertiesView().requestFocusInWindow();
    }

    if (e.getActionCommand().equals("increase_blast_damage")) {
      try {
        game.getPlayer().upgradeFighter(game.getPlayer().getActiveFighter(), 'B');
        gameView.getPropertiesView().setFighters(game.getPlayer().getFighters());
      } catch (NotEnoughAbilityPointsException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }

      upgradeView.setVisible(false);
      gameView.getPropertiesView().requestFocusInWindow();
    }

    if (e.getActionCommand().equals("increase_physical_damage")) {
      try {
        game.getPlayer().upgradeFighter(game.getPlayer().getActiveFighter(), 'P');
        gameView.getPropertiesView().setFighters(game.getPlayer().getFighters());
      } catch (NotEnoughAbilityPointsException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }

      upgradeView.setVisible(false);
      gameView.getPropertiesView().requestFocusInWindow();
    }

    if (e.getActionCommand().equals("increase_max_ki")) {
      try {
        game.getPlayer().upgradeFighter(game.getPlayer().getActiveFighter(), 'K');
        gameView.getPropertiesView().setFighters(game.getPlayer().getFighters());
      } catch (NotEnoughAbilityPointsException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }

      upgradeView.setVisible(false);
      gameView.getPropertiesView().requestFocusInWindow();
    }

    if (e.getActionCommand().equals("increase_max_stamina")) {
      try {
        game.getPlayer().upgradeFighter(game.getPlayer().getActiveFighter(), 'S');
        gameView.getPropertiesView().setFighters(game.getPlayer().getFighters());
      } catch (NotEnoughAbilityPointsException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }

      upgradeView.setVisible(false);
      gameView.getPropertiesView().requestFocusInWindow();
    }

    if (e.getActionCommand().equals("cancel_upgrading")) {
      upgradeView.setVisible(false);
      gameView.getPropertiesView().requestFocusInWindow();
    }

    if (e.getActionCommand().equals("assign_attack")) {

      // Just to test
      //      ArrayList<SuperAttack> SAttacks = new ArrayList<SuperAttack>();
      //      SAttacks.add(new SuperAttack("el sahm el montaleq", 500));
      //      SAttacks.add(new SuperAttack("Attack gamed moot", 1000));
      //      SAttacks.add(new SuperAttack("Saroo5 ard gaw", 800));
      //      game.getPlayer().setSuperAttacks(SAttacks);
      //      ArrayList<UltimateAttack> UAttacks = new ArrayList<UltimateAttack>();
      //      UAttacks.add(new UltimateAttack("7anafy", 1100));
      //      UAttacks.add(new UltimateAttack("7amada", 2200));
      //      UAttacks.add(new UltimateAttack("7zla2oom", 3300));
      //      game.getPlayer().setUltimateAttacks(UAttacks);

      assignAttackView.setPlayerSuperAttacksBox(game.getPlayer().getSuperAttacks());
      assignAttackView.setPlayerUltimateAttacksBox(game.getPlayer().getUltimateAttacks());
      assignAttackView.setFighterSuperAttacksBox(game.getPlayer().getActiveFighter()
          .getSuperAttacks());
      assignAttackView.setFighterUltimateAttacksBox(game.getPlayer().getActiveFighter()
          .getUltimateAttacks());
      assignAttackView.setVisible(true);
      assignAttackView.requestFocusInWindow();
    }

    if (e.getActionCommand().equals("add_attack")) {
      String playerSuperAttack = (String) assignAttackView.getPlayerSuperAttacksBox()
          .getSelectedItem();
      String playerUltimateAttack = (String) assignAttackView.getPlayerUltimateAttacksBox()
          .getSelectedItem();
      if (playerSuperAttack != null) {
        SuperAttack superAttack = null;
        for (SuperAttack SA : game.getPlayer().getSuperAttacks()) {
          if (SA.getName().equals(playerSuperAttack)) {
            superAttack = SA;
            break;
          }
        }
        try {
          game.getPlayer().assignAttack(game.getPlayer().getActiveFighter(), superAttack, null);
        } catch (DuplicateAttackException e1) {
          // print message
          showErrorMessage("This attack already exists in the fighter's super attacks' list.");
        } catch (MaximumAttacksLearnedException e1) {
          // print message
          showErrorMessage("You can not add new super attack to this fighter!");
        }
      }

      if (playerUltimateAttack != null) {
        UltimateAttack ultimateAttack = null;
        for (UltimateAttack UA : game.getPlayer().getUltimateAttacks()) {
          if (UA.getName().equals(playerUltimateAttack)) {
            ultimateAttack = UA;
            break;
          }
        }
        try {
          game.getPlayer().assignAttack(game.getPlayer().getActiveFighter(), ultimateAttack, null);
        } catch (DuplicateAttackException e1) {
          // TODO print message
          showErrorMessage("This attack already exists in the fighter's ultimate attacks' list.");
        } catch (MaximumAttacksLearnedException e1) {
          // TODO print message
          showErrorMessage("You can not add new ultimate attack to this fighter!");
        } catch (NotASaiyanException e1) {
          // TODO print message
          showErrorMessage("This fighter is not of type Saiyan, You can not assign SuperSiyan attack!");
        }

      }

      if (playerSuperAttack == null && playerUltimateAttack == null) {
        showErrorMessage("Choose the attack you want to add!");
      } else {
        assignAttackView.setVisible(false);
        gameView.getPropertiesView().requestFocusInWindow();
      }
    }

    if (e.getActionCommand().equals("switch_attacks")) {
      String playerSuperAttack = (String) assignAttackView.getPlayerSuperAttacksBox()
          .getSelectedItem();
      String playerUltimateAttack = (String) assignAttackView.getPlayerUltimateAttacksBox()
          .getSelectedItem();
      String fighterSuperAttack = (String) assignAttackView.getFighterSuperAttacksBox()
          .getSelectedItem();
      String fighterUltimateAttack = (String) assignAttackView.getFighterUltimateAttacksBox()
          .getSelectedItem();
      if (fighterSuperAttack != null && playerSuperAttack != null) {
        SuperAttack superAttackPlayer = null;
        SuperAttack superAttackFighter = null;
        for (SuperAttack SA : game.getPlayer().getSuperAttacks()) {
          if (SA.getName().equals(playerSuperAttack)) {
            superAttackPlayer = SA;
            break;
          }
        }
        for (SuperAttack SA : game.getPlayer().getActiveFighter().getSuperAttacks()) {
          if (SA.getName().equals(fighterSuperAttack)) {
            superAttackFighter = SA;
            break;
          }
        }
        try {
          game.getPlayer().assignAttack(game.getPlayer().getActiveFighter(), superAttackPlayer,
              superAttackFighter);
        } catch (DuplicateAttackException e1) {
          // print message
          showErrorMessage("This attack already exists in the fighter's super attacks' list.");
        } catch (MaximumAttacksLearnedException e1) {

          showErrorMessage("You can not add new super attack to this fighter!");
        }
      }

      if (playerUltimateAttack != null && fighterUltimateAttack != null) {
        UltimateAttack ultimateAttackPlayer = null;
        UltimateAttack ultimateAttackFighter = null;
        for (UltimateAttack UA : game.getPlayer().getUltimateAttacks()) {
          if (UA.getName().equals(playerUltimateAttack)) {
            ultimateAttackPlayer = UA;
            break;
          }
        }
        for (UltimateAttack UA : game.getPlayer().getActiveFighter().getUltimateAttacks()) {
          if (UA.getName().equals(playerUltimateAttack)) {
            ultimateAttackFighter = UA;
            break;
          }
        }
        try {
          game.getPlayer().assignAttack(game.getPlayer().getActiveFighter(), ultimateAttackPlayer,
              ultimateAttackFighter);
        } catch (DuplicateAttackException e1) {

          showErrorMessage("This attack already exists in the fighter's ultimate attacks' list.");
        } catch (MaximumAttacksLearnedException e1) {

          showErrorMessage("You can not add new ultimate attack to this fighter!");
        } catch (NotASaiyanException e1) {

          showErrorMessage("This fighter is not of type Saiyan, You can not assign SuperSiyan attack!");
        }
      }

      if ((playerSuperAttack != null && fighterSuperAttack == null)
          || (playerSuperAttack == null && fighterSuperAttack != null)
          || (playerUltimateAttack != null && fighterUltimateAttack == null)
          || (playerUltimateAttack == null && fighterUltimateAttack != null)) {
        showErrorMessage("Choose the attack you want to switch with!");
      } else if (playerSuperAttack == null && fighterSuperAttack == null
          && playerUltimateAttack == null && fighterUltimateAttack == null) {
        showErrorMessage("Choose the attacks you want to switch!");
      } else {
        assignAttackView.setVisible(false);
        gameView.getPropertiesView().requestFocusInWindow();
      }
    }

    if (e.getActionCommand().equals("cancel_assign_attack")) {
      assignAttackView.setVisible(false);
      gameView.getPropertiesView().requestFocusInWindow();
    }

    if (e.getActionCommand().equals("save_game")) {
      String fileName = JOptionPane.showInputDialog(null, "Enter a name for your game", "");
      while (true) {
        if (fileName != null && fileName.length() == 0) {
          fileName = JOptionPane.showInputDialog(null,
              "You haven't entered a name. \n Please enter file name to save your game", "");
        } else
          break;
      }
      if (fileName != null)
        save(fileName);
    }


    if(e.getActionCommand().contains("wish"))
    {
      handleDragonWishButtons(e.getActionCommand(),dragon);
    }

    // TODO radwa
    String actionCommand = e.getActionCommand();

    if (actionCommand.equals("super-attack-menu")) {
      JComboBox cb = (JComboBox) e.getSource();
      selectedSuperAttack = (String) cb.getSelectedItem();
    }
    if (actionCommand.equals("ultimate-attack-menu")) {
      JComboBox cb = (JComboBox) e.getSource();
      selectedUltimateAttack = (String) cb.getSelectedItem();
    }

    if (actionCommand.contains("battle")) {
      handleAttackButtons(actionCommand, battle);
    }

  }

  // TODO radwa
  private void handleAttackButtons(String actionCommand, Battle battle) {
    if (!battle.isMyTurn()) {
      showErrorMessage("This is not your turn");
      return;
    }
    if (actionCommand.equals("battle-attack")){
      gameView.getBattleView().getTurnOptions().toggleAttackBlockUse();
      gameView.getBattleView().getTurnOptions().toggleAttackOptions();
      return; 
    }

    if (actionCommand.equals("battle-physical-attack")) {
      try {
        //System.out.println("physical attack");
        battle.attack(new PhysicalAttack());
        gameView.getBattleView().getTurnOptions().toggleAttackOptions();
      } catch (NotASaiyanException | NotEnoughKiException e) {
        showErrorMessage(e.getMessage());

      } catch (ClassNotFoundException | IOException e) {
        e.printStackTrace();
      }
    } else if (actionCommand.equals("battle-super-attack")) {
      if (selectedSuperAttack == null) {
        showErrorMessage("You must choose an attack first");
        return;
      }
      SuperAttack attack = gameView.getBattleView().findSuperAttack(selectedSuperAttack);

      try {
        battle.attack(attack);
        gameView.getBattleView().getTurnOptions().toggleAttackOptions();
      } catch (NotASaiyanException | NotEnoughKiException e1) {
        showErrorMessage(e1.getMessage());
        e1.printStackTrace();
      } catch (ClassNotFoundException | IOException e1) {
        e1.printStackTrace();
        System.out.println(e1.getMessage());
      }
    } else if (actionCommand.equals("battle-ultimate-attack")) {
      if (selectedUltimateAttack == null) {
        showErrorMessage("You must choose an attack first");
        return;
      }
      UltimateAttack attack = gameView.getBattleView().findUltimateAttack(selectedUltimateAttack);
      try {
        battle.attack(attack);
        gameView.getBattleView().getTurnOptions().toggleAttackOptions();
      } catch (NotASaiyanException | NotEnoughKiException e) {
        showErrorMessage(e.getMessage());

      } catch (ClassNotFoundException | IOException e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
      }
    } else if (actionCommand.equals("battle-block")) {
      try {
        battle.block();
        gameView.getBattleView().getTurnOptions().toggleAttackBlockUse();
      } catch (NotEnoughKiException e) {
        showErrorMessage(e.getMessage());

      } catch (ClassNotFoundException | IOException e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
      }
    } else if (actionCommand.equals("battle-use")) {
      try {
        battle.use(game.getPlayer(), Collectible.SENZU_BEAN);
        gameView.getBattleView().getTurnOptions().toggleAttackBlockUse();
      } catch (NotEnoughSenzuBeansException | NotEnoughKiException e) {
        showErrorMessage(e.getMessage());

      } catch (ClassNotFoundException | IOException e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
      }
    }
  }

  public GameView getGameView() {
    return gameView;
  }

  @Override
  public void onDragonCalled(Dragon dragon) {
    this.dragon = dragon;
    gameView.getWorldView().setVisible(false);
    gameView.getDragonView().setWishes(dragon.getWishes());
    gameView.getDragonView().setVisible(true);
    gameView.getDragonView().requestFocusInWindow();


  }

  public void handleDragonWishButtons(String actionCommmand,Dragon dragon)
  {
    //TODO alaa 
    DragonWish [] wishes = dragon.getWishes();
    if(actionCommmand.equals("senzu-bean-wish"))
    {
      game.onWishChosen(wishes[0]);
      JOptionPane.showMessageDialog(null, "Congratulations!\n" + wishes[0].getSenzuBeans()+ " Senzu Beans have been added to you :D");
    }
    if(actionCommmand.equals("ability-points-wish"))
    {
      game.onWishChosen(wishes[1]);
      JOptionPane.showMessageDialog(null, "Congratulations!\n" + wishes[1].getAbilityPoints()+ " Ability Points have been added to you :D");
    }
    if(actionCommmand.equals("super-attack-wish"))
    {
      game.onWishChosen(wishes[2]);
      JOptionPane.showMessageDialog(null, "Congratulations!\n" + wishes[2].getSuperAttack()+ " Super Attack has been added to you :D");
    }
    if(actionCommmand.equals("ultimate-attack-wish"))
    {
      game.onWishChosen(wishes[3]);
      JOptionPane.showMessageDialog(null, "Congratulations!\n" + wishes[3].getUltimateAttack()+ " Ultimate Attack has been added to you :D");
    }
    for(int i = 0;i<7;i++)
    {
      gameView.getWorldView().getInfo().removeDragonBall();
    }
    gameView.getDragonView().setVisible(false);
    gameView.getWorldView().setVisible(true);
    gameView.getWorldView().requestFocusInWindow();

  }
  public void onCollectibleFound(Collectible collectible) {
    if (collectible == Collectible.DRAGON_BALL) {
      gameView.getWorldView().getInfo().addDragonBall();
     showErrorMessage("You have collected a dragon ball.");
    } else if (collectible == Collectible.SENZU_BEAN) {
      gameView.getWorldView().getInfo().addSenzuBean();
      showErrorMessage("You have collected a senzu bean");
    }

  }

  @Override
  public void onBattleEvent(BattleEvent e) {

    if (e.getType() == BattleEventType.STARTED)

    { //System.out.println("fighter's ability points just before the battle" + game.getPlayer().getActiveFighter().getAbilityPoints());
      // initializing the battle instance variable to use it in handling later events
      battle = (Battle) e.getSource();
      // initializes a new battle and makes it visible
      gameView.showBattle();

      // sets the game controller as the listener to all objects in the battle view class
      gameView.getBattleView().setListenersToGameController(this);
      // game.getPlayer().setSenzuBeans(7); //TODO remove this 

      // passing the me and foe from the battle to the battle view
      gameView.getBattleView().setMe(game.getPlayer().getActiveFighter());
      gameView.getBattleView().setPlayer(game.getPlayer());
      NonPlayableFighter foe = (NonPlayableFighter) ((Battle) e.getSource()).getFoe();
      gameView.getBattleView().setFoe(foe);
      gameView.getBattleView().createInfoPanel();
      updateBattleInfoBar();


      gameView.getBattleView().getInfo().getTurn().setVisible(true);
      // displaying the two fighters in their initial standby postions
      gameView.getBattleView().displayStandby();

      // initializing the controller instance in the Turn Options class
      gameView.getBattleView().getTurnOptions().setController(this);
      gameView.getBattleView().setUpTurnOptions();

    }

    if (e.getType() == BattleEventType.ATTACK_ME) {
      //Thread.dumpStack();

      gameView.getBattleView().meAttack(e.getAttack());
      gameView.getBattleView().getInfo().updateBattleFighterInfo(game.getPlayer());
      gameView.getBattleView().getInfo().updateBattleFoeInfo((NonPlayableFighter)battle.getFoe());
      //System.out.println("attack - is my turn");

    } 
    if(e.getType() == BattleEventType.ATTACK_FOE) {
      // System.out.println("5 - foe attack event delivered to controller");
      gameView.getBattleView().getInfo().updateBattleFighterInfo(game.getPlayer());
      gameView.getBattleView().getInfo().updateBattleFoeInfo((NonPlayableFighter)battle.getFoe());
      gameView.getBattleView().foeAttack(e.getAttack());

    }

    if (e.getType() == BattleEventType.ENDED) {
      BattleOpponent winner = e.getWinner();
      if (winner == battle.getFoe())
      {
        showErrorMessage("The battle is over. \n You lost :(");
        
        ArrayList<String> old_saved_games = new ArrayList<String>();
        try
        {
          BufferedReader reader = new BufferedReader(new FileReader("all_saved_games.csv"));

          String line;
          while(( line = reader.readLine()) != null)
          {
            old_saved_games.add(line);
          }
        }catch(FileNotFoundException e1)
        {

        } catch (IOException e1) {

          e1.printStackTrace();
        }
        
        if(!old_saved_games.isEmpty())
        {
          System.out.println("load");
          gameView.setEnabled(false);
          gameView.setVisible(false);
          
          load(old_saved_games.get(old_saved_games.size()-1));
          gameView.setVisible(true);
        }
          
        else
        {
          gameView.getBattleView().setVisible(false);
          gameView.getWorldView().setVisible(true);
          gameView.getWorldView().requestFocusInWindow();
          //System.out.println("update called -controller");
          gameView.getWorldView().getInfo().updateFighterInfo(game.getPlayer().getActiveFighter());
          updateBattleInfoBar();   
        }
      }

      else {
        showErrorMessage("The battle is over. You won! /o/");
        gameView.getBattleView().setVisible(false);
        gameView.getWorldView().setVisible(true);
        gameView.getWorldView().requestFocusInWindow();
        //System.out.println("update called -controller");
        gameView.getWorldView().getInfo().updateFighterInfo(game.getPlayer().getActiveFighter());
        updateBattleInfoBar();   
      }
      //System.out.println("fighter's ability points just after the battle" + game.getPlayer().getActiveFighter().getAbilityPoints());
      gameView.getBattleView().setVisible(false);
      gameView.getWorldView().setVisible(true);
      gameView.getWorldView().requestFocusInWindow();
      //System.out.println("update called -controller");
      gameView.getWorldView().getInfo().updateFighterInfo(game.getPlayer().getActiveFighter());
      updateBattleInfoBar();   
    }
    if (e.getType() == BattleEventType.USE) {
      int response = JOptionPane.showConfirmDialog(null,"You have used one senzu bean. \n Your stamina and health are now maximum.", 
          "Use."  ,JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("resources/world/senzu-bean.png"));


      //System.out.println("Hiiiii");
      updateBattleInfoBar();
      callPlay();
      System.out.println("Play called  -use controller");

    }
    if (e.getType() == BattleEventType.BLOCK_ME) {
      //System.out.println("1-block event delivered to controller");
      gameView.getBattleView().meBlock();  
    }
    if (e.getType() == BattleEventType.BLOCK_FOE) {
      JOptionPane.showMessageDialog(null, "Foe has blocked your attack");
      gameView.getBattleView().getTurnOptions().toggleAttackBlockUse();
      updateBattleInfoBar();
    }
    if(e.getType() == BattleEventType.NEW_TURN)
    {
      //      if(battle.getAttacker() == battle.getMe())
      //      {
      //        gameView.getBattleView().getInfo().getFoeTurn().setVisible(true);
      //        gameView.getBattleView().getInfo().getTurn().setVisible(false);
      //      }
      //      else
      //      {
      //        gameView.getBattleView().getInfo().getTurn().setVisible(true);
      //        gameView.getBattleView().getInfo().getFoeTurn().setVisible(false);
      //      }
    }

  }

  public void callPlay () {
    try {
      if (!battle.isMyTurn()) {
        System.out.println("play called");
        battle.play();
      } else {
        System.out.println("not foe's turn");
        //Thread.dumpStack();
      }
    } catch (ClassNotFoundException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  
  public void setAddedFeateaures(int XP,int ability,ArrayList<UltimateAttack> UA,ArrayList<SuperAttack>SA)
  {
    String UAS = "";
    for (int i = 0; i < UA.size(); i++) 
    {
      UAS+= UA.get(i).getName()+", ";
      
    }
    String SAS = "";
    for (int i = 0; i < SA.size(); i++) 
    {
      SAS+= SA.get(i).getName()+", ";
      
    }
    
    if(UAS.length()!=0 && SAS.length()!=0)
    JOptionPane.showMessageDialog(null, "You gained\n"+ XP+"XP\n" +ability+"ability points\n"+UAS+" ultimate attacks\n" + SAS +"super attacks\n");
    else if(UAS.length() != 0 && SAS.length()==0)
      JOptionPane.showMessageDialog(null, "You gained\n"+ XP+"XP\n" +ability+"ability points\n"+UAS+" ultimate attacks\n");
    else
      if(SAS.length() != 0 &&UAS.length() == 0)
        JOptionPane.showMessageDialog(null, "You gained\n"+ XP+"XP\n" +ability+"ability points\n"+ SAS +"super attacks\n");
      else
        JOptionPane.showMessageDialog(null, "You gained\n"+ XP+"XP\n" +ability+"ability points\n");
    
  }
  {
    
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public void showErrorMessage(String message) {
    JOptionPane.showMessageDialog(null, message);
  }

  public void updateBattleInfoBar () {
    gameView.getBattleView().getInfo().updateBattleFighterInfo(game.getPlayer());
    gameView.getBattleView().getInfo().updateBattleFoeInfo((NonPlayableFighter)battle.getFoe());

  }
  public static void main(String[] args) {
    new GameController();
  }

  public void save(String fileName)
  {
    FileOutputStream fileOut;
    try {
      fileOut = new FileOutputStream(fileName);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(this); 
      this.lastSavedController = fileName;
      out.close();
      fileOut.close();
    } catch (FileNotFoundException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    ArrayList<String> old_saved_games = new ArrayList<String>();
    try
    {
      BufferedReader reader = new BufferedReader(new FileReader("all_saved_games.csv"));

      String line;
      while(( line = reader.readLine()) != null)
      {
        old_saved_games.add(line);
      }
    }catch(FileNotFoundException e)
    {

    } catch (IOException e) {

      e.printStackTrace();
    }
    old_saved_games.add(fileName);
    writeCsvFile("all_saved_games.csv", old_saved_games);
  }


  public void load(String fileName)
  {
    try {
      FileInputStream fileIn = new FileInputStream(fileName);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      GameController controller = (GameController) in.readObject();
      this.game = controller.game;
      this.gameView = controller.gameView;
      this.upgradeView = controller.upgradeView;
      this.assignAttackView = controller.assignAttackView;
      this.lastSavedController = controller.lastSavedController;
      game.setListener(this);
      gameView.setListenersToGameController(this);

      in.close();
      fileIn.close();

    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block   
      JOptionPane.showMessageDialog(null, "There is an error with loading this file");
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } 
  }

  public static void writeCsvFile(String fileName, ArrayList<String> lines) 
  {
    FileWriter fileWriter = null;
    try {
      fileWriter = new FileWriter(fileName);
      if(lines != null)
      {
        for(String line:lines)
        {
          fileWriter.append(line);
          fileWriter.append("\n");
        }
      }

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      fileWriter.flush();
      fileWriter.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } 
  }

  @Override
  public void onPlayerPositionReset() {
    gameView.getWorldView().resetPlayerPosition(); 

  }
}
