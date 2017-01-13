package dragonball.view; 

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Radwa extends JFrame{
	int minValue =1;
	int maxValue = 100;
	int counter = minValue;
	private JLabel l;
	private JLabel n;
	
	Radwa(){
		super("The Number Incrementer");
	    setSize(320, 100);
	    setLocationRelativeTo(null);
		l = new JLabel("Look at the number on my right :D");
		n = new JLabel();
		
		JButton b = new JButton("Start");
		b.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		          Thread runner = new Thread() {
		            public void run() {
		              counter = minValue;
		              while (counter <= maxValue) {
		                Runnable runme = new Runnable() {
		                  public void run() {
		                    n.setText(counter+"");
		                  }
		                };
		                SwingUtilities.invokeLater(runme);
		                counter++;
		                try {
		                  Thread.sleep(100);
		                } catch (Exception ex) {
		                }
		              }
		            }
		          };
		          runner.start();
		        }
		      });

		
		 getContentPane().add(l, BorderLayout.CENTER);
		    getContentPane().add(b, BorderLayout.WEST);
		    getContentPane().add(n, BorderLayout.EAST);
		setVisible(true);
		
}
	public static void main(String[] args) {
		new Radwa();
	}
}