package UI;

import javax.swing.*;

import Enums.TableControllerMode;
import ReturnTypes.TableControllerInsertReturn;
import controller.Bucket;
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
		// tableController = new TableController((a) -> a.length() % 10, 10, 10, 100);
	}

	public void run() {

	}

	private JLabel pageSize = new JLabel("Tamanho da Página", JLabel.CENTER);
	private JTextField pageSizeTextField = new JTextField();

	private JLabel pageQuantity = new JLabel("Quantidade da página", JLabel.CENTER);
	private JTextField pageQuantityTextField = new JTextField();

	private JButton loadButton;
	private JProgressBar loadProgressBar = new JProgressBar();

	private JLabel collisionLabel = new JLabel("Colisões:", JLabel.CENTER);
	private JLabel collisionCounterLabel = new JLabel("0", JLabel.CENTER);

	private JLabel overflowLabel = new JLabel("Overflows:", JLabel.CENTER);
	private JLabel overflowCounterLabel = new JLabel("0", JLabel.CENTER);

	private JTextField searchTextField = new JTextField();
	private JButton searchButton = new JButton("Buscar");

	private JLabel acessLabel = new JLabel("Acessos:", JLabel.CENTER);
	private JLabel acessCounterLabel = new JLabel("0", JLabel.CENTER);

	Popup p;

	void reset() {
		pageSizeTextField.setText("");
		pageQuantityTextField.setText("");
		overflowCounterLabel.setText("");
		collisionCounterLabel.setText("");
		loadProgressBar.setValue(0);
		// loadButton.removeAll();
	}

	TableControllerMode getTableControllerMode() {
		if (pageSizeTextField.getText().equals("")) {
			return TableControllerMode.FixedPageAmount;
		} else {
			return TableControllerMode.FixedPageSize;
		}
	}

	int getPageValue() {
		if (pageSizeTextField.getText().equals("")) {
			return Integer.parseInt(pageQuantityTextField.getText());
		} else {
			return Integer.parseInt(pageSizeTextField.getText());
		}
	}

	public void gui() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setSize(400, 400);
		Container c = frame.getContentPane();
		c.setLayout(new GridLayout(7, 2, 10, 10));
		loadButton = new JButton("Carregar");
		loadProgressBar.setStringPainted(true);
		loadButton.addActionListener((e) -> {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int result = chooser.showOpenDialog(frame);
			HashMap<Integer, Integer> dict = new HashMap<Integer, Integer>();
			if (pageSizeTextField.getText().equals("") && pageQuantityTextField.getText().equals("")) {
				JFrame frame = new JFrame("Atenção!!!");
				JOptionPane.showMessageDialog(frame, "Informe apenas quantidade de páginas ou tamanho de páginas");
				reset();
			} else if (!pageSizeTextField.getText().equals(pageQuantityTextField.getText())) {
				// Aqui cria a table controller somente pela "tamanho de pages" digitado pelo
				// usuário
				int bucketCount = 10;
				int bucketSize = 20;
				try {
					tableController = new TableController((a) -> a.length() % 10, bucketCount, bucketSize,
							getTableControllerMode(), getPageValue());
					if (result == JFileChooser.APPROVE_OPTION) {
						File f = chooser.getSelectedFile();
						// try {
						TextFileReader.ReadFile(f.getPath(), (line) -> {
							try {
								TableControllerInsertReturn r = tableController.insert(line);
								if (!dict.containsKey(r.bucketIndex))
									dict.put(r.bucketIndex, 0);
								dict.put(r.bucketIndex, dict.get(r.bucketIndex) + 1);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}, () -> {
						});
						loadProgressBar.setValue(100);
						int colissions = 0, overflows = 0;
						for (Map.Entry<Integer, Integer> entry : dict.entrySet()) {
							Integer key = entry.getKey();
							System.out.println(key);
							Integer value = entry.getValue();
							System.out.println(value);
							if (value > 1)
								colissions += value - 1;
							overflows += Math.floor(value / tableController.hashTable.bucketSize);
						}
						collisionCounterLabel.setText(colissions + "");
						overflowCounterLabel.setText(overflows + "");
						Bucket[] b = tableController.hashTable.buckets;
						for (int i = 0; i < b.length; i++) {
							System.out.println("[" + i + "]" + b[i].count());
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		c.add(pageSize);
		c.add(pageSizeTextField);
		c.add(pageQuantity);
		c.add(pageQuantityTextField);
		c.add(loadButton);
		c.add(loadProgressBar);
		c.add(collisionLabel);
		c.add(collisionCounterLabel);
		c.add(overflowLabel);
		c.add(overflowCounterLabel);
		c.add(searchTextField);
		searchButton.addActionListener((e) -> {
			try {
				System.out.println(tableController.select(searchTextField.getText()));
				acessCounterLabel.setText(Integer.toString(tableController.accessCounter));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		c.add(searchButton);
		c.add(acessLabel);
		c.add(acessCounterLabel);
		// c.add(new JLabel("Buckets"));
		// JList<JLabel> list = new JList<JLabel>();
		// list.add(new JLabel("111"));
		// JScrollPane pane = new JScrollPane(list);
		// c.add(pane);
		// button.addActionListener(this);

		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}
}