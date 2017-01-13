package dragonball.view;

import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;

public class GameView extends JFrame {
  private static final int WIDTH = 1370, HEIGHT = 730;
  private StartView start;
  private ChooseFighter chooseFighter;
  private WorldView worldView;
  private PropertiesView propertiesView;
  private BattleView battleView; 
  private String lastSavedGameView;
  private DragonView dragonView;
  


  public StartView getStart() {
    return start;
  }



  public void setStart(StartView start) {
    this.start = start;
  }



  public ChooseFighter getChooseFighter() {
    return chooseFighter;
  }



  public void setChooseFighter(ChooseFighter chooseFighter) {
    this.chooseFighter = chooseFighter;
  }

  public WorldView getWorldView () {
    return worldView;  
  }

  public PropertiesView getPropertiesView() {
    return propertiesView;
  }



  public void setPropertiesView(PropertiesView propertiesView) {
    this.propertiesView = propertiesView;
  }



  public void setWorldView(WorldView worldView) {
    this.worldView = worldView;
  }



  public GameView() {
    setSize(WIDTH, HEIGHT);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    start = new StartView();
    add(start);
    chooseFighter = new ChooseFighter();
    add(chooseFighter);
    worldView = new WorldView(); 
    add(worldView); 
    worldView.setVisible(false);
    chooseFighter.setVisible(false);
    propertiesView = new PropertiesView();
    add(propertiesView);
    propertiesView.setVisible(false);
    dragonView = new DragonView();
    add(dragonView);
    dragonView.setVisible(false);
    
    setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);

    setVisible(true);



  }





  public DragonView getDragonView() {
    return dragonView;
  }



  public void setDragonView(DragonView dragonView) {
    this.dragonView = dragonView;
  }



  public BattleView getBattleView() {
    return battleView;
  }



  public void showBattle() {
    worldView.setVisible(false);

    battleView = new BattleView(); 
    add(battleView); 
    battleView.setVisible(true); 

  }


  public void setListenersToGameController(ActionListener a) {

    start.setListenersToGameController(a);
    chooseFighter.setListenersToGameController(a);
    worldView.setListenersToGameController(a);
    propertiesView.setListenersToGameController(a);
    dragonView.setListenersToGameController(a);
  }

  public void save(String fileName)
  {
    lastSavedGameView = fileName+"-gameView";
    try {
      FileOutputStream fileOut = new FileOutputStream(fileName+"-gameView");
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(this);
      out.close();
      fileOut.close();
    }catch (FileNotFoundException e) {
      // TODO Auto-generated catch block`
      System.out.println(e.getMessage());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  public void load(String fileName)
  {
    
    try {
      FileInputStream fileIn = new FileInputStream(fileName);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      GameView gv = (GameView) in.readObject();
      this.start = gv.start;
      this.chooseFighter = gv.chooseFighter;
      this.worldView = gv.worldView;
      this.propertiesView = gv.propertiesView;
      this.battleView = gv.battleView;
      
      
      in.close();
      fileIn.close();
      
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }


}
