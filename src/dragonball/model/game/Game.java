package dragonball.model.game;

import java.io.BufferedReader;
import java.io.File;
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
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeMap;

import com.sun.corba.se.spi.orbutil.fsm.State;

import dragonball.model.attack.Attack;
import dragonball.model.attack.MaximumCharge;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleEventType;
import dragonball.model.battle.BattleListener;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;
import dragonball.model.dragon.DragonWishType;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.player.Player;
import dragonball.model.player.PlayerListener;
import dragonball.model.world.World;
import dragonball.model.world.WorldListener;

public class Game implements PlayerListener, BattleListener, WorldListener, Serializable {
  private Player player;
  private World world;
  private ArrayList<NonPlayableFighter> weakFoes;
  private ArrayList<NonPlayableFighter> strongFoes;
  private ArrayList<Attack> attacks;
  private ArrayList<Dragon> dragons;
  private TreeMap<String, Integer> attackTree;
  private GameState state;
  private transient GameListener listener;
  private String lastSavedGame;

  public Game() throws UnknownAttackTypeException, MissingFieldException, IOException {
    player = new Player("");
    player.setListener(this);
    world = new World();
    weakFoes = new ArrayList<NonPlayableFighter>();
    strongFoes = new ArrayList<NonPlayableFighter>();
    attacks = new ArrayList<Attack>();
    dragons = new ArrayList<Dragon>();
    state = GameState.WORLD;
    world.setListener(this);

    try {
      this.loadAttacks("Database-Attacks.csv");
    } catch (MissingFieldException e) {
      System.out.println(e.getMessage());
      attacks = new ArrayList<Attack>();
      attackTree = new TreeMap<String, Integer>();
      this.loadAttacks("Database-Attacks-aux.csv");
    } catch (UnknownAttackTypeException e) {
      System.out.println(e.getMessage());
      attacks = new ArrayList<Attack>();
      attackTree = new TreeMap<String, Integer>();
      this.loadAttacks("Database-Attacks-aux.csv");
    } catch (IOException e) { // changed order of IOException catch, to make most general at the end
                           // S
      this.loadAttacks("Database-Attacks-aux.csv");
      e.printStackTrace();
    }

    try {
      loadFoes("Database-Foes-Range1.csv");
    } catch (MissingFieldException e) {
      System.out.println(e.getMessage());
      weakFoes = new ArrayList<NonPlayableFighter>();
      strongFoes = new ArrayList<NonPlayableFighter>();
      this.loadFoes("Database-Foes-aux.csv");
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      loadDragons("Database-Dragons.csv");
    } catch (MissingFieldException e) {
      dragons = new ArrayList<Dragon>();
      System.out.println(e.getMessage());
      this.loadDragons("Database-Dragons-aux.csv");
    } catch (IOException e) {
      e.printStackTrace();
    }

    world.generateMap(weakFoes, strongFoes);
  }

  public Player getPlayer() {
    return player;
  }

  public World getWorld() {
    return world;
  }
  public String getLastSavedGame()
  {
    return lastSavedGame; 
  }
  public ArrayList<NonPlayableFighter> getWeakFoes() {
    return weakFoes;
  }

  public ArrayList<NonPlayableFighter> getStrongFoes() {
    return strongFoes;
  }

  public ArrayList<Attack> getAttacks() {
    return attacks;
  }

  public ArrayList<Dragon> getDragons() {
    return dragons;
  }

  public GameState getState() {
    return state;
  }

  public GameListener getListener() {
    return listener;
  }

  public void setListener(GameListener listener) {
    this.listener = listener;
  }

  private ArrayList<String> loadCSV(String filePath) throws IOException {
    FileReader fr = new FileReader(filePath);
    BufferedReader bf = new BufferedReader(fr);
    ArrayList<String> output = new ArrayList<String>();
    String line = "";
    while ((line = bf.readLine()) != null) {
      output.add(line);
    }
    return output;
  }

  public void loadAttacks(String filePath) throws MissingFieldException,
      UnknownAttackTypeException, IOException {
    ArrayList<String> file = loadCSV(filePath);
    StringTokenizer st;
    attackTree = new TreeMap<String, Integer>();
    for (String s : file) {
      st = new StringTokenizer(s, ",");
      if (st.countTokens() != 3) {
        throw new MissingFieldException("There are " + (3 - st.countTokens())
            + " missing fields in the file " + filePath + "on line " + (file.indexOf(s) + 1),
            filePath, file.indexOf(s) + 1, 3 - st.countTokens());
      }

      String name, type;
      int damage;
      type = st.nextToken();
      name = st.nextToken();
      damage = Integer.parseInt(st.nextToken());
      attackTree.put(name, damage);

      if (type.equals("SA")) {
        // if (name.equals("Maximum Charge"))
        // attacks.add(new MaximumCharge());
        // else
        attacks.add(new SuperAttack(name, damage));
      } else if (type.equals("UA")) {
        attacks.add(new UltimateAttack(name, damage));
      } else if (type.equals("MC")) {
        attacks.add(new MaximumCharge());
      } else if (type.equals("SS")) {
        attacks.add(new SuperSaiyan());
      } else {
        throw new UnknownAttackTypeException("There is no attack of type " + type, filePath,
            file.indexOf(s) + 1, type);
      }
    }
  }

  public void loadFoes(String filePath) throws IOException, MissingFieldException {
    ArrayList<String> file = loadCSV(filePath);
    StringTokenizer st;
    for (int i = 0; i < file.size(); i += 3) {
      st = new StringTokenizer(file.get(i), ",");
      if (st.countTokens() != 8) {
        throw new MissingFieldException("There are " + (8 - st.countTokens())
            + " missing fields in the file " + filePath + "on line " + (i + 1), filePath, i + 1,
            8 - st.countTokens());
      }
      String name = st.nextToken();
      int level = Integer.parseInt(st.nextToken());
      int maxHealthPoints = Integer.parseInt(st.nextToken());
      int blastDamage = Integer.parseInt(st.nextToken());
      int physicalDamage = Integer.parseInt(st.nextToken());
      int maxKi = Integer.parseInt(st.nextToken());
      int maxStamina = Integer.parseInt(st.nextToken());
      boolean strong = (st.nextToken().equals("TRUE")) ? true : false;
      
      ArrayList<SuperAttack> superAttacks = new ArrayList<SuperAttack>(); 
      ArrayList<UltimateAttack> ultimateAttacks = new ArrayList <UltimateAttack> ();  
      if ((i+1) < file.size()) {
      String superAttackLine = file.get(i + 1);
      superAttacks = loadSuperAttacks(superAttackLine);
      }
      
      if ((i+2) < file.size()) {
      String ultimateAttackLine = file.get(i + 2);
      ultimateAttacks = loadUltimateAttacks(ultimateAttackLine);
      }
      
      if (strong) {
        strongFoes.add(new NonPlayableFighter(name, level, maxHealthPoints, blastDamage,
            physicalDamage, maxKi, maxStamina, strong, superAttacks, ultimateAttacks));
      } else {
        weakFoes.add(new NonPlayableFighter(name, level, maxHealthPoints, blastDamage,
            physicalDamage, maxKi, maxStamina, strong, superAttacks, ultimateAttacks));
      }
    }
  }

  public void loadDragons(String filePath) throws IOException, MissingFieldException {
    ArrayList<String> file = loadCSV(filePath);
    StringTokenizer st;
    for (int i = 0; i < file.size(); i += 3) {
      st = new StringTokenizer(file.get(i), ",");
      if (st.countTokens() != 3) {
        throw new MissingFieldException("there are " + (3 - st.countTokens())
            + " fields are missing in the file " + filePath + " on line " + (i + 1), filePath,
            i + 1, 3 - st.countTokens());
      }
      String name = st.nextToken();
      int senzuBeans = Integer.parseInt(st.nextToken());
      int abilityPoints = Integer.parseInt(st.nextToken());
      ArrayList<SuperAttack> superAttacks = new ArrayList<SuperAttack>(); 
      ArrayList<UltimateAttack> ultimateAttacks = new ArrayList<UltimateAttack>(); 
      if ((i+1) < file.size()) {
      String superAttackLine = file.get(i + 1);
      superAttacks = loadSuperAttacks(superAttackLine);
      } 
      
      if ((i+2) < file.size()) {
      String ultimateAttackLine = file.get(i + 2);
      ultimateAttacks = loadUltimateAttacks(ultimateAttackLine);
      }
      dragons.add(new Dragon(name, superAttacks, ultimateAttacks, senzuBeans, abilityPoints));
    }

  }

  private ArrayList<SuperAttack> loadSuperAttacks(String superAttackLine) {
    ArrayList<SuperAttack> superAttacks = new ArrayList<SuperAttack>();
    StringTokenizer st = new StringTokenizer(superAttackLine);
    if (superAttackLine != null && !superAttackLine.equals("\n")) {
      st = new StringTokenizer(superAttackLine, ",");

      while (st.hasMoreTokens()) {
        String superAttackName = st.nextToken();
        if (attackTree.containsKey(superAttackName)) {
          int damage = (int) attackTree.get(superAttackName);
          if (superAttackName.equals("Maximum Charge"))
            superAttacks.add(new MaximumCharge());
          else
            superAttacks.add(new SuperAttack(superAttackName, damage));
        }
      }
    }
    return superAttacks;
  }

  private ArrayList<UltimateAttack> loadUltimateAttacks(String ultimateAttackLine) {
    ArrayList<UltimateAttack> ultimateAttacks = new ArrayList<UltimateAttack>();
    StringTokenizer st = new StringTokenizer(ultimateAttackLine);
    if (ultimateAttackLine != null) {
      st = new StringTokenizer(ultimateAttackLine, ",");

      while (st.hasMoreTokens()) {

        String ultimateAttackName = st.nextToken();
        if (attackTree.containsKey(ultimateAttackName)) {
          int damage = attackTree.get(ultimateAttackName);
          ultimateAttacks.add(new UltimateAttack(ultimateAttackName, damage));
        }
      }
    }
    return ultimateAttacks;
  }

  @Override
  public void onFoeEncountered(NonPlayableFighter foe) throws NotEnoughKiException,
      ClassNotFoundException, MissingFieldException, IOException {
    state = GameState.BATTLE;
    Battle battle = new Battle(player.getActiveFighter(), foe);
    onBattleEvent(new BattleEvent(battle, BattleEventType.STARTED));
    battle.setListener(this);
    battle.start();

  }

  @Override
  public void onCollectibleFound(Collectible collectible) {
    
    if (collectible == Collectible.DRAGON_BALL) {
      player.setDragonBalls(player.getDragonBalls() + 1);
      if (player.getDragonBalls() >= 7)
        player.callDragon();
    } else {
      player.setSenzuBeans(player.getSenzuBeans() + 1);
    }
    if (listener != null) {
      listener.onCollectibleFound(collectible);
    }

  }

  @Override
  public void onBattleEvent(BattleEvent e) throws ClassNotFoundException, MissingFieldException,
      IOException {
    
    if (e.getType() == BattleEventType.STARTED)
      state = GameState.BATTLE;

    else if (e.getType() == BattleEventType.ENDED) {
      state = GameState.WORLD;
      PlayableFighter activeFighter = player.getActiveFighter();
      if (e.getWinner().equals(activeFighter)) {
        
        NonPlayableFighter loser = (NonPlayableFighter) e.getLoser();
        
        ArrayList<UltimateAttack> added = new ArrayList<UltimateAttack>();
        
        for(UltimateAttack UA:loser.getUltimateAttacks())
        {
          if(!player.getUltimateAttacks().contains(UA))
          {
            player.getUltimateAttacks().add(UA);
            added.add(UA);
          }
        }
        ArrayList<SuperAttack> addedS = new ArrayList<SuperAttack>();
        for(SuperAttack SA:loser.getSuperAttacks())
        {
          if(!player.getSuperAttacks().contains(SA))
          {
            player.getSuperAttacks().add(SA);
            addedS.add(SA);
          }
        }
        
        int gainedXP = 5 * loser.getLevel();
        activeFighter.setXp(activeFighter.getXp() + 5 * loser.getLevel());
        activeFighter.setAbilityPoints(activeFighter.getAbilityPoints()+2);
        if (loser.isStrong()) {
          // strongFoes.remove(loser);
          // world = new World();
          // world.generateMap(weakFoes, strongFoes);
          player.setExploredMaps(player.getExploredMaps() + 1);
          int foesRange = (player.getMaxFighterLevel() - 1) / 10 + 1;
          try {
            loadFoes("." + File.separator + "Database-Foes-Range" + foesRange + ".csv");
          } catch (MissingFieldException o) {
            System.out.println(o.getMessage());
            weakFoes.clear();
            strongFoes.clear();
            loadFoes("Database-Foes-Range1.csv");
          }
          
          
          
          world.generateMap(weakFoes, strongFoes);
          world.resetPlayerPosition();
        }
        
        if(listener != null)
          listener.setAddedFeateaures(gainedXP,2,added,addedS);
        // else
        // {
        
        // weakFoes.remove(loser);
        // }
      } else {
        // in M3 the scenario changed :")
        // world.returnFoe((NonPlayableFighter) e.getWinner());
        // world.resetPlayerPosition();
        if (lastSavedGame != null) {
          load(lastSavedGame);
        } else {
          // this.world = new World();
          world.generateMap(weakFoes, strongFoes);
          world.resetPlayerPosition();
          state = GameState.WORLD;
          // this.world.setListener(this);
        }

      }
    }
    if (listener != null)
      listener.onBattleEvent(e);
    
    
  }

  public void onDragonCalled() {
    Random r = new Random();
    int index = r.nextInt(dragons.size());
    Dragon dragon = dragons.get(index);
    state = GameState.DRAGON;
    player.setDragonBalls(player.getDragonBalls() - 7);
    if (listener != null)
      listener.onDragonCalled(dragon);
  }

  public void onWishChosen(DragonWish wish) 
  {
    if(wish.getType() == DragonWishType.ABILITY_POINTS)
    {
      player.getActiveFighter().setAbilityPoints(player.getActiveFighter().getAbilityPoints()+wish.getAbilityPoints());
    }
    if(wish.getType() == DragonWishType.SENZU_BEANS)
    {
      player.setSenzuBeans(player.getSenzuBeans()+wish.getSenzuBeans());
    }
    if(wish.getType() == DragonWishType.SUPER_ATTACK)
    {
      if(!player.getSuperAttacks().contains(wish.getSuperAttack()))
      {
        player.getSuperAttacks().add(wish.getSuperAttack());
      }
    }
    if(wish.getType() == DragonWishType.ULTIMATE_ATTACK)
    {
      if(!player.getUltimateAttacks().contains(wish.getUltimateAttack()))
      {
        player.getUltimateAttacks().add(wish.getUltimateAttack());
      }
    }
      state = GameState.WORLD;
  }

  public void save(String fileName) {
    lastSavedGame = fileName+"-game";
    try {
      FileOutputStream fileOut = new FileOutputStream(fileName+"-game");
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(this);
      
     
      
        out.close();
      fileOut.close();

    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block`
      System.out.println(e.getMessage());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void load(String fileName) throws ClassNotFoundException {
    try {
      FileInputStream fileIn = new FileInputStream(fileName);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      Game g = (Game) in.readObject();
      this.attacks = g.attacks;
      this.dragons = g.dragons;
      this.listener = g.listener;
      this.player = g.player;
      this.state = g.state;
      this.strongFoes = g.strongFoes;
      this.weakFoes = new ArrayList<NonPlayableFighter>();
      for (NonPlayableFighter weakFoe : g.weakFoes) {
        this.weakFoes.add(weakFoe);
      }

      this.world = g.world;
      this.listener = g.listener;
      player.setListener(this);
      world.setListener(this);
      in.close();
      fileIn.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      System.out.println(e.getMessage());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  @Override
  public void onPlayerPositionReset() {
    listener.onPlayerPositionReset();
    
  }
  
  
}
