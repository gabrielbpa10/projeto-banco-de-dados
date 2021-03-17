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
import static javax.swing.SpringLayout.*;

public class NewMainWindow {

    public JFrame frame = new JFrame("Projeto de Tabela Hash");
    TableController tableController;

    public NewMainWindow() {
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

    JLabel pageSize = new JLabel("Page size");
    JTextField pageSizeTextField = new JTextField();

    JLabel pageQuantity = new JLabel("Page quantity");
    JTextField pageQuantityTextField = new JTextField();

    JButton loadButton = new JButton("Load");
    JProgressBar loadProgressBar = new JProgressBar();

    JLabel collisionLabel = new JLabel("Collisions:");
    JLabel collisionCounterLabel = new JLabel("0");

    JLabel overflowLabel = new JLabel("Overflows:");
    JLabel overflowCounterLabel = new JLabel("0");

    JTextField searchTextField = new JTextField();
    JButton searchButton = new JButton("Search");

    JLabel accessLabel = new JLabel("Disc accesses:");
    JLabel accessCounterLabel = new JLabel("0");
    DefaultListModel<String> bucketListModel = new DefaultListModel<>();

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

    void setEvents() {
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
                            Integer value = entry.getValue();
                            if (value > 1)
                                colissions += value - 1;
                            overflows += Math.floor(value / tableController.hashTable.bucketSize);
                        }
                        collisionCounterLabel.setText(colissions + "");
                        overflowCounterLabel.setText(overflows + "");
                        Bucket[] b = tableController.hashTable.buckets;
                        for (int i = 0; i < b.length; i++) {
                            //System.out.println("[" + i + "]" + b[i].count());
                            bucketListModel.addElement(i + "(" + b[i].count(true) + ") - " +b[i].print());
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        searchButton.addActionListener((e) -> {
            try {
                System.out.println(tableController.select(searchTextField.getText()));
                accessCounterLabel.setText(Integer.toString(tableController.accessCounter));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    public void gui() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadProgressBar.setStringPainted(true);
        Container c = frame.getContentPane();
        SpringLayout sl = new SpringLayout();
        c.setLayout(sl);
        c.add(pageSize);
        int horizontalMargin = 10, verticalSpacing = 10;
        sl.putConstraint(WEST, pageSize, horizontalMargin, WEST, c);
        sl.putConstraint(NORTH, pageSize, verticalSpacing, NORTH, c);
        c.add(pageSizeTextField);
        sl.putConstraint(EAST, pageSizeTextField, -horizontalMargin, EAST, c);
        sl.putConstraint(NORTH, pageSizeTextField, verticalSpacing, NORTH, c);
        sl.putConstraint(WEST, pageSizeTextField, horizontalMargin, EAST, pageQuantity);
        c.add(pageQuantity);
        sl.putConstraint(WEST, pageQuantity, horizontalMargin, WEST, c);
        sl.putConstraint(NORTH, pageQuantity, verticalSpacing, SOUTH, pageSizeTextField);
        c.add(pageQuantityTextField);
        sl.putConstraint(NORTH, pageQuantityTextField, verticalSpacing, SOUTH, pageSizeTextField);
        sl.putConstraint(EAST, pageQuantityTextField, -horizontalMargin, EAST, c);
        sl.putConstraint(WEST, pageQuantityTextField, horizontalMargin, EAST, pageQuantity);
        c.add(loadButton);
        sl.putConstraint(WEST, loadButton, horizontalMargin, WEST, c);
        sl.putConstraint(NORTH, loadButton, verticalSpacing, SOUTH, pageQuantityTextField);
        c.add(loadProgressBar);
        sl.putConstraint(NORTH, loadProgressBar, verticalSpacing, SOUTH, pageQuantityTextField);
        sl.putConstraint(EAST, loadProgressBar, -horizontalMargin, EAST, c);
        sl.putConstraint(WEST, loadProgressBar, horizontalMargin, EAST, loadButton);
        sl.putConstraint(SOUTH, loadProgressBar, 0, SOUTH, loadButton);
        c.add(collisionLabel);
        sl.putConstraint(WEST, collisionLabel, horizontalMargin, WEST, c);
        sl.putConstraint(NORTH, collisionLabel, verticalSpacing, SOUTH, loadButton);
        c.add(collisionCounterLabel);
        sl.putConstraint(NORTH, collisionCounterLabel, 0, NORTH, collisionLabel);
        sl.putConstraint(WEST, collisionCounterLabel, horizontalMargin, EAST, collisionLabel);
        c.add(overflowLabel);
        sl.putConstraint(NORTH, overflowLabel, 0, NORTH, collisionLabel);
        sl.putConstraint(EAST, overflowLabel, -horizontalMargin, WEST, overflowCounterLabel);
        c.add(overflowCounterLabel);
        sl.putConstraint(NORTH, overflowCounterLabel, 0, NORTH, collisionLabel);
        sl.putConstraint(EAST, overflowCounterLabel, -horizontalMargin, EAST, c);
        JLabel bucketsL = new JLabel("Buckets:");
        c.add(bucketsL);
        sl.putConstraint(NORTH, bucketsL, verticalSpacing, SOUTH, overflowCounterLabel);
        sl.putConstraint(WEST, bucketsL, horizontalMargin, WEST, c);
        // bucketListModel.addElement("USA");
        // bucketListModel.addElement("India");
        // bucketListModel.addElement("Vietnam");
        // bucketListModel.addElement("Canada");
        // bucketListModel.addElement("Denmark");
        // bucketListModel.addElement("France");
        // bucketListModel.addElement("Great Britain");
        // bucketListModel.addElement("Japan");
        // bucketListModel.addElement("Germany");
        JList<String> list = new JList<String>(bucketListModel);
        JScrollPane pane = new JScrollPane(list);
        c.add(pane);
        sl.putConstraint(NORTH, pane, verticalSpacing, SOUTH, bucketsL);
        sl.putConstraint(WEST, pane, horizontalMargin, WEST, c);
        sl.putConstraint(EAST, pane, -horizontalMargin, EAST, c);
        JButton firstL = new JButton("First");
        c.add(firstL);
        sl.putConstraint(NORTH, firstL, verticalSpacing, SOUTH, pane);
        sl.putConstraint(WEST, firstL, horizontalMargin, WEST, c);
        JButton previousL = new JButton("<");
        c.add(previousL);
        sl.putConstraint(NORTH, previousL, 0, NORTH, firstL);
        sl.putConstraint(WEST, previousL, horizontalMargin, EAST, firstL);
        JButton lastL = new JButton("Last");
        c.add(lastL);
        sl.putConstraint(NORTH, lastL, 0, NORTH, firstL);
        sl.putConstraint(EAST, lastL, -horizontalMargin, EAST, c);
        JButton nextL = new JButton(">");
        c.add(nextL);
        sl.putConstraint(NORTH, nextL, 0, NORTH, firstL);
        sl.putConstraint(EAST, nextL, -horizontalMargin, WEST, lastL);
        JLabel indexTotalL = new JLabel("0/0", JLabel.CENTER);
        c.add(indexTotalL);
        sl.putConstraint(NORTH, indexTotalL, 0, NORTH, firstL);
        sl.putConstraint(WEST, indexTotalL, horizontalMargin, EAST, previousL);
        sl.putConstraint(EAST, indexTotalL, horizontalMargin, WEST, nextL);
        sl.putConstraint(SOUTH, indexTotalL, 0, SOUTH, lastL);
        c.add(searchButton);
        sl.putConstraint(NORTH, searchButton, 0, NORTH, searchTextField);
        sl.putConstraint(EAST, searchButton, -horizontalMargin, EAST, c);
        c.add(searchTextField);
        sl.putConstraint(NORTH, searchTextField, verticalSpacing, SOUTH, lastL);
        sl.putConstraint(WEST, searchTextField, horizontalMargin, WEST, c);
        sl.putConstraint(EAST, searchTextField, -horizontalMargin, WEST, searchButton);
        sl.putConstraint(SOUTH, searchTextField, 0, SOUTH, searchButton);
        c.add(accessLabel);
        sl.putConstraint(NORTH, accessLabel, verticalSpacing, SOUTH, searchTextField);
        sl.putConstraint(WEST, accessLabel, horizontalMargin, WEST, c);
        c.add(accessCounterLabel);
        sl.putConstraint(NORTH, accessCounterLabel, 0, NORTH, accessLabel);
        sl.putConstraint(WEST, accessCounterLabel, horizontalMargin, EAST, accessLabel);
        // c.add(new JLabel("Buckets"));
        // button.addActionListener(this);
        setEvents();
        frame.setSize(300, 430);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = frame.getSize();
        frame.setLocation(dim.width / 2 - size.width / 2, dim.height / 2 - size.height / 2);
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(300, 430));
        //frame.setResizable(false);
    }

}