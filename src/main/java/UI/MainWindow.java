package UI;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import ReturnTypes.TableControllerInsertReturn;
import controller.TableController;
import controller.TextFileReader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MainWindow implements ActionListener {

	public JFrame frame = new JFrame("Projeto de Tabela Hash");
	TableController tableController;

	public MainWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		tableController = new TableController((a) -> a.length() % 10, 10, 4, 3);
	}

	public void run() {

	}

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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setSize(400, 400);
		frame.getContentPane().setLayout(new GridLayout(5, 2, 10, 10));
		loadProgressBar.setStringPainted(true);
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tableController.clear();
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int result = chooser.showOpenDialog(frame);
				int index = 0;
				HashMap<Integer, Integer> dict = new HashMap<Integer, Integer>();
				if (result == JFileChooser.APPROVE_OPTION) {
					File f = chooser.getSelectedFile();
					try {
						TextFileReader.ReadFile(f.getPath(), (line) -> {
							try {
								TableControllerInsertReturn r = tableController.insert(line);
								if (!dict.containsKey(r.bucketIndex)) dict.put(r.bucketIndex, 0);
								dict.put(r.bucketIndex, dict.get(r.bucketIndex));
							} catch (Exception e1) {
								e1.printStackTrace();
							} 
						});
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
						loadProgressBar.setValue(100);
					}
					int colissions = 0, overflows = 0;
					for (Map.Entry<Integer, Integer> entry : dict.entrySet()) {
						//Integer key = entry.getKey();
						Integer value = entry.getValue();
						if (value > 1) {
							colissions += value - 1;
						}
						overflows += Math.floor(value / tableController.hashTable.bucketSize);
						// ...
					}
					collisionCounterLabel.setText(colissions+"");
					overflowCounterLabel.setText(overflows+"");
				}
			}
		});
		frame.getContentPane().add(loadButton);
		frame.getContentPane().add(loadProgressBar);
		frame.getContentPane().add(collisionLabel);
		frame.getContentPane().add(collisionCounterLabel);
		frame.getContentPane().add(overflowLabel);
		frame.getContentPane().add(overflowCounterLabel);
		frame.getContentPane().add(searchTextField);
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					tableController.select(searchTextField.getText());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(searchButton);
		frame.getContentPane().add(acessLabel);
		frame.getContentPane().add(acessCounterLabel);

		// button.addActionListener(this);

		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}