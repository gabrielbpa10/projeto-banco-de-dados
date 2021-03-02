package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow implements ActionListener{
		
	private JButton loadButton = new JButton("Carregar");
	private JProgressBar loadProgressBar = new JProgressBar();

	private JLabel collisionLabel = new JLabel("Colisões:", JLabel.CENTER);
	private JLabel collisionCounterLabel = new JLabel("0", JLabel.CENTER);

	private JLabel overflowLabel = new JLabel("Overflows:", JLabel.CENTER);
	private JLabel overflowCounterLabel = new JLabel("0", JLabel.CENTER);
	
	private JTextField searchTextField = new JTextField();
	private JButton searchButton = new JButton("Buscar");	

	private JLabel acessLabel = new JLabel("Acessos:", JLabel.CENTER);
	private JLabel acessCounterLabel = new JLabel("0", JLabel.CENTER);
	
	public void gui() {
		
		 JFrame frame = new JFrame("Projeto de Tabela Hash");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 //frame.setSize(400, 400);
		 frame.getContentPane().setLayout(new GridLayout(5,2,10,10));

		loadProgressBar.setStringPainted(true);

		 frame.getContentPane().add(loadButton);
		 frame.getContentPane().add(loadProgressBar);
		 frame.getContentPane().add(collisionLabel);
		 frame.getContentPane().add(collisionCounterLabel);
		 frame.getContentPane().add(overflowLabel);
		 frame.getContentPane().add(overflowCounterLabel);
		 frame.getContentPane().add(searchTextField);
		 frame.getContentPane().add(searchButton);
		 frame.getContentPane().add(acessLabel);
		 frame.getContentPane().add(acessCounterLabel);
		 
		 //button.addActionListener(this);
		 
		 frame.pack();
	     frame.setVisible(true);
	     frame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
