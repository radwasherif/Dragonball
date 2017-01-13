package dragonball.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dragonball.controller.GameController;

public class WorldView extends JPanel implements KeyListener {
  static final int MAP_WIDTH = Constants.WIDTH;
  static final int MAP_HEIGHT = Constants.HEIGHT - 120;
  static final int TILE_WIDTH = MAP_WIDTH / 10;
  static final int TILE_HEIGHT = MAP_HEIGHT / 10;
  private int fighter_x = MAP_WIDTH - TILE_WIDTH, fighter_y = Constants.HEIGHT - TILE_HEIGHT;
  private String fighterImage;
  private String bossImage = "resources/world/boss.png";
  private GameController gameController;
  private JButton player;
  private WorldInfoPanel info;

  public static int getMapWidth() {
    return MAP_WIDTH;
  }

  public static int getMapHeight() {
    return MAP_HEIGHT;
  }

  public static int getTileWidth() {
    return TILE_WIDTH;
  }

  public static int getTileHeight() {
    return TILE_HEIGHT;
  }

  public int getFighter_x() {
    return fighter_x;
  }

  public int getFighter_y() {
    return fighter_y;
  }

  public String getFighterImage() {
    return fighterImage;
  }

  public JButton getPlayer() {
    return player;
  }

  public WorldInfoPanel getInfo() {
    return info;
  }

  public WorldView() {
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    setSize(Constants.WIDTH, Constants.HEIGHT);
    setLayout(null);
    setFocusable(true);
    info = new WorldInfoPanel();
    add(info);
    info.setBounds(0, 0, WorldInfoPanel.INFO_WIDTH, WorldInfoPanel.INFO_HEIGHT);
    player = new JButton();

    Image scaled_boss = new ImageIcon(bossImage).getImage().getScaledInstance(TILE_HEIGHT,
        TILE_HEIGHT, Image.SCALE_DEFAULT);
    JLabel boss = new JLabel(new ImageIcon(scaled_boss));
    add(boss);
    boss.setBounds(0, Constants.HEIGHT - MAP_HEIGHT, TILE_HEIGHT, TILE_HEIGHT);

  }

  public GameController getGameController() {
    return gameController;
  }

  public void setGameController(GameController gameController) {
    this.gameController = gameController;
  }

  public void setFighterImage(String fighterImage) {

    this.fighterImage = fighterImage;
  }

  public void removePreviousImage() {
    if (player != null) {
      player.setEnabled(false);
      player.setVisible(false);
      player.removeActionListener(player.getActionListeners()[0]);
      player = null;
    }
  }

  public void setPlayer() {

    ActionListener[] listeners = player.getActionListeners();
    ActionListener controller = listeners[0];
    removePreviousImage();
    ImageIcon playerIcon = new ImageIcon(fighterImage);
    Image playerScaledImage = (playerIcon.getImage()).getScaledInstance(TILE_HEIGHT, TILE_HEIGHT,
        Image.SCALE_DEFAULT);
    player = new JButton(new ImageIcon(playerScaledImage));
    player.setToolTipText("You can click on the player to view more options: save game, upgrade, add,and switch fighter, or assign attacks");
    player.setBorder(BorderFactory.createEmptyBorder());
    player.setContentAreaFilled(false);
    add(player);
    player.setBounds(fighter_x, fighter_y, TILE_WIDTH, TILE_HEIGHT);
    player.setActionCommand("player");

    player.addActionListener(controller);
    player.setVisible(true);

  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    Image img = new ImageIcon(fighterImage).getImage();
    Image map = new ImageIcon("resources/map2.png").getImage();
    g2.drawImage(map, 0, 0, MAP_WIDTH, Constants.HEIGHT, null);
  }

  public void up() {
    fighter_y -= TILE_HEIGHT;
  }

  public void down() {
    fighter_y += TILE_HEIGHT;
  }

  public void right() {
    fighter_x += TILE_WIDTH;
  }

  public void left() {
    fighter_x -= TILE_WIDTH;
  }

  public void keyPressed(KeyEvent e) {
  }

  public void keyTyped(KeyEvent e) {
  }

  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();
    if (code == KeyEvent.VK_UP) {
      try {
        gameController.getGame().getWorld().moveUp();
        player.setVisible(false);
        up();
      } catch (Exception e1) {
        System.out.println(e1.getMessage());
        // TODO make error sound
      }

    }
    if (code == KeyEvent.VK_DOWN) {
      try {
        gameController.getGame().getWorld().moveDown();
        player.setVisible(false);
        down();
      } catch (Exception e1) {
        System.out.println(e1.getMessage());
        // TODO make error sound
      }

    }
    if (code == KeyEvent.VK_RIGHT) {
      try {
        gameController.getGame().getWorld().moveRight();
        player.setVisible(false);
        right();
      } catch (Exception e1) {
        System.out.println(e1.getMessage());
        // TODO make error sound
      }

    }
    if (code == KeyEvent.VK_LEFT) {
      try {
        gameController.getGame().getWorld().moveLeft();
        player.setVisible(false);
        left();
      } catch (Exception e1) {
        System.out.println(e1.getMessage());
        // TODO make error sound
      }
    }
    add(player);
    player.setBounds(fighter_x, fighter_y, TILE_WIDTH, TILE_HEIGHT);
    player.setVisible(true);

  }

  public void setListenersToGameController(ActionListener a) {

    player.addActionListener(a);
  }

  public void resetPlayerPosition() {
    fighter_x = MAP_WIDTH - TILE_WIDTH;
    fighter_y = Constants.HEIGHT - TILE_HEIGHT;
    setPlayer();

  }

  public static void main(String[] args) {
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(Constants.WIDTH, Constants.HEIGHT);
    f.add(new WorldView());
    f.setVisible(true);
  }

}
