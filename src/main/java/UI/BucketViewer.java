package UI;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.table.*;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;

import controller.Bucket;

public class BucketViewer extends JFrame {

    Bucket[] buckets;
    TableModel model = new TableModel();
    JTable table;
    
    public BucketViewer (Bucket[] buckets) {
        this.buckets = buckets;
        model.addColumn("#");
        model.addColumn("Content - 0");
        model.addColumn("Content - 1");
        model.addColumn("Content - 2");
        model.addColumn("Content - 3");
    }

    public void show () {
        JFrame f = new JFrame();
        Container cp = f.getContentPane();
        SpringLayout sl = new SpringLayout();
        cp.setLayout(sl);
        int margin = 10;
        table = new JTable();
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void updateTable () {
        table.revalidate();
        table.repaint();
    }

    class TableModel extends DefaultTableModel {

        private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int row, int column) {
			if (column == 1) {
				return false;
			} else {
				return true;
			}
		}
    }

}
