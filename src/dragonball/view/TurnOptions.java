package dragonball.view;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dragonball.controller.GameController;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

public class TurnOptions extends JPanel {
  final static int HEIGHT = Constants.HEIGHT / 4;
  final static int WIDTH = Constants.WIDTH;
  // making the game controller an instance variable
  private GameController controller;

  private JComboBox ultimateAttackMenu;
  private JComboBox superAttackMenu;

  public JButton getUltimateAttack() {
    return ultimateAttack;
  }

  public void setSuperAttackMenu(JComboBox superAttackMenu) {
    this.superAttackMenu = superAttackMenu;
  }

  private JButton superAttack;
  private JButton ultimateAttack;
  private JButton block;
  private JButton use;
  private JButton physicalAttack;
  private JButton attack;

  // original dimension of the button icon in paint.net
  static final int BUTTON_WIDTH = 248, BUTTON_HEIGHT = 89;
  static final double SCALING_FACTOR = 0.75;

  // coordinates and dimensions of buttons and menus
  static final int X_OFFSET = 20, Y_OFFSET = 20;
  static final int attack_width = (int) (BUTTON_WIDTH * SCALING_FACTOR),
      attack_height = (int) (BUTTON_HEIGHT * SCALING_FACTOR);
  static final int use_width = (int) (BUTTON_WIDTH * SCALING_FACTOR),
      use_height = (int) (BUTTON_HEIGHT * SCALING_FACTOR);
  static final int block_width = (int) (BUTTON_WIDTH * SCALING_FACTOR),
      block_height = (int) (BUTTON_HEIGHT * SCALING_FACTOR);
  
  static final int x_attack = WIDTH/2 - attack_width/2, y_attack = Y_OFFSET * 3;
  static final int x_block = x_attack + attack_width + X_OFFSET, y_block = y_attack;
  static final int x_use = x_attack - use_width - X_OFFSET, y_use = y_attack;
  
  static final int x_physical = x_attack, y_physical = y_block;
  static final int x_ultimate = x_use, y_ultimate = y_use;
  static final int x_super = x_block, y_super = y_block;
  
  static final int x_ultimate_menu = x_ultimate - attack_width - X_OFFSET,
      y_ultimate_menu = y_ultimate;
  static final int x_super_menu = x_super + attack_width + X_OFFSET, y_super_menu = y_super + attack_height + Y_OFFSET;

  public TurnOptions() {
    setSize(WIDTH, HEIGHT);
    setOpaque(false);
    setLayout(null);
    displayButton();

  }

  public void displayAttackMenus() {
    add(ultimateAttackMenu);
    ultimateAttackMenu.setBounds(x_ultimate_menu, y_ultimate_menu, 200, 20);
    ultimateAttackMenu.setVisible(false);
    ultimateAttackMenu.setEnabled(false);
    ultimateAttackMenu.setSelectedItem(null);

    add(superAttackMenu);
    superAttackMenu.setBounds(x_super_menu, y_super, 200, 20);
    superAttackMenu.setVisible(false);
    superAttackMenu.setEnabled(false);
    superAttackMenu.setSelectedItem(null);
  }

  public JComboBox getUltimateAttackMenu() {
    return ultimateAttackMenu;
  }

  public void setUltimateAttackMenu(ArrayList<UltimateAttack> ultimateAttacks) {
    String[] ultimateAttackNames = new String[ultimateAttacks.size()];
    for (int i = 0; i < ultimateAttackNames.length; i++) {
      ultimateAttackNames[i] = ultimateAttacks.get(i).getName();
    }

    ultimateAttackMenu = new JComboBox(ultimateAttackNames);
    ultimateAttackMenu.setActionCommand("ultimate-attack-menu");
    ultimateAttackMenu.addActionListener(controller);
    // System.out.println(Arrays.toString(ultimateAttackNames));
  }

  public JComboBox getSuperAttackMenu() {
    return superAttackMenu;
  }

  public void setSuperAttackMenu(ArrayList<SuperAttack> superAttacks) {
    String[] superAttackNames = new String[superAttacks.size()];
    for (int i = 0; i < superAttackNames.length; i++) {
      superAttackNames[i] = superAttacks.get(i).getName();
    }

    superAttackMenu = new JComboBox(superAttackNames);
    superAttackMenu.setActionCommand("super-attack-menu");
    superAttackMenu.addActionListener(controller);

    // System.out.println(Arrays.toString(superAttackNames));

  }

  private void displayButton() {
    Image scaled_attack = new ImageIcon("resources/world/buttons/attack.png").getImage()
        .getScaledInstance(attack_width, attack_height, Image.SCALE_DEFAULT);
    attack = new JButton(new ImageIcon(scaled_attack));
    attack.setSize(attack_width, attack_height);
    attack.setActionCommand("battle-attack");
    add(attack);
    attack.setBounds(x_attack, y_attack, attack_width, attack_height);
    //attack.setVisible(false);
    //attack.setEnabled(false);

    Image scaled_super = new ImageIcon("resources/world/buttons/super-attack.png").getImage()
        .getScaledInstance(attack_width, attack_height, Image.SCALE_SMOOTH);
    superAttack = new JButton(new ImageIcon(scaled_super));
    superAttack.setSize(attack_width, attack_height);
    superAttack.setActionCommand("battle-super-attack");
    superAttack
        .setToolTipText("Requires and consumes one ki bar. \n Damage = specific attack damage + fighter's blast damage");
    add(superAttack);
    superAttack.setBounds(x_super, y_super, attack_width, attack_height);
    superAttack.setVisible(false);
    superAttack.setEnabled(false);

    Image scaled_ultimate = new ImageIcon("resources/world/buttons/ultimate-attack.png")
        .getImage().getScaledInstance(attack_width, attack_height, Image.SCALE_SMOOTH);
    ultimateAttack = new JButton(new ImageIcon(scaled_ultimate));
    ultimateAttack.setSize(attack_width, attack_height);
    ultimateAttack.setActionCommand("battle-ultimate-attack");
    ultimateAttack
        .setToolTipText("Requires and consumes 3 ki bars. \n Damage = specific attack damage + fighter's blast damage");
    add(ultimateAttack);
    ultimateAttack.setBounds(x_ultimate, y_ultimate, attack_width, attack_height);
    ultimateAttack.setVisible(false);
    ultimateAttack.setEnabled(false);

    Image block_scaled = new ImageIcon("resources/world/buttons/block.png").getImage()
        .getScaledInstance(block_width, block_height, Image.SCALE_SMOOTH);
    block = new JButton(new ImageIcon(block_scaled));
    block.setSize(block_width, block_height);
    block.setActionCommand("battle-block");
    block.setToolTipText("Block foe's attack in the next turn");
    add(block);
    block.setBounds(x_block, y_block, block_width, block_height);
    //block.setVisible(false);
    //block.setEnabled(false);

    Image use_scaled = new ImageIcon("resources/world/buttons/use.png").getImage()
        .getScaledInstance(use_width, use_height, Image.SCALE_SMOOTH);
    use = new JButton(new ImageIcon(use_scaled)); 
    
    use.setSize(use_width, use_height);
    use.setActionCommand("battle-use");
    use.setToolTipText("Use senzu bean to restore health and stamina to maximum.");
    add(use);
    use.setBounds(x_use, y_use, use_width, use_height);
    //use.setVisible(false);
    //use.setEnabled(false);

    Image physical_scaled = new ImageIcon("resources/world/buttons/physical-attack.png")
        .getImage().getScaledInstance(attack_width, attack_height, Image.SCALE_SMOOTH);
    physicalAttack = new JButton(new ImageIcon(physical_scaled));
    physicalAttack.setSize(attack_width, attack_height);
    physicalAttack.setActionCommand("battle-physical-attack");
    physicalAttack.setToolTipText("Charges one ki bar. \n Damage = 50 + fighter's physical damage");
    add(physicalAttack);
    physicalAttack.setBounds(x_physical, y_physical, attack_width, attack_height);
    physicalAttack.setVisible(false);
    physicalAttack.setEnabled(false);

  }

  public void setListenersToGameController(ActionListener a) {

    ultimateAttack.addActionListener(a);
    superAttack.addActionListener(a);
    use.addActionListener(a);
    block.addActionListener(a);
    physicalAttack.addActionListener(a);
    attack.addActionListener(a);
  }

  public void setController(GameController controller) {
    this.controller = controller;
  }
  public void toggleAttackBlockUse (){
     if (attack.isVisible()) {
    attack.setVisible(false);
     } else {
       attack.setVisible(true);
     }
     if (attack.isEnabled()) {
       attack.setEnabled(false);   
     } else {
       attack.setEnabled(true);
     }
    
    if (block.isVisible()) {
      block.setVisible(false);  
    } else {
      block.setVisible(true);  
    }
    if (block.isEnabled()) {
      block.setEnabled(false);  
    } else {
      block.setEnabled(true);
    }
    
    if (use.isVisible()) {
      use.setVisible(false);
    } else {
      use.setVisible(true);
    }
    
    if (use.isEnabled()) {
      use.setEnabled(false); 
    } else{
      use.setEnabled(true);
    }
    
  } 
  
  public void toggleAttackOptions() {
    toggle(physicalAttack);
    toggle(ultimateAttack);
    toggle(superAttack); 
    
    toggle(superAttackMenu); 
    
    toggle(ultimateAttackMenu); 
  }
  
  public void toggle(JComponent j ) {
    if (j.isVisible()) {
      j.setVisible(false);
    } else {
      j.setVisible(true);
    } 
    
    if (j.isEnabled()) {
      j.setEnabled(false);
    } else {
      j.setEnabled(true); 
    }
  }
  public static void main(String[] args) {
    JFrame f = new JFrame();
    f.setSize(Constants.WIDTH, Constants.HEIGHT);
    TurnOptions t = new TurnOptions();
    f.setLayout(null);
    f.add(t);
    t.setBounds(0, Constants.HEIGHT * 2 / 3, Constants.WIDTH, Constants.HEIGHT / 4);
    f.setVisible(true);
  }
}
