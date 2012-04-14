import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class NewFrame {

	JTable table;
	StudentDB students;
	static Object[][] data;
	String[] columnNames = { "Name", "Age", "Math Level", "Reading Level",
			"Language Arts Level" };
	String[] validStates = { "", "K", "1", "2", "3", "4", "5", "6", "7", "8" };

	public NewFrame(JFrame frame, StudentDB s) {
		ColumnRenderer cr = new ColumnRenderer();

		students = s;
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception evt) {
		}

		// Create and set up the window.
		frame = new JFrame("Scheduling");
		frame.setState(Frame.NORMAL);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		frame.setSize(dimension);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Menu menu = new Menu(students);
		JMenuBar menuBar = menu.get_menu();

		frame.setJMenuBar(menuBar);

		menuBar.setOpaque(true);
		menuBar.setBackground(new Color(128, 128, 128));
		menuBar.setPreferredSize(new Dimension(200, 20));

		// Set the menu bar and add the label to the content pane.
		frame.setJMenuBar(menuBar);

		populateTable();

		table = new JTable(data, columnNames);

		// Create the combo box editor
		JComboBox comboBox = new JComboBox(validStates);
		comboBox.setEditable(true);
		DefaultCellEditor editor = new DefaultCellEditor(comboBox);
		

		// Assign the editor to the second column
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(2).setCellEditor(editor);
		tcm.getColumn(2).setCellRenderer(cr);
		tcm.getColumn(3).setCellEditor(editor);
		tcm.getColumn(3).setCellRenderer(cr);
		tcm.getColumn(4).setCellEditor(editor);
		tcm.getColumn(4).setCellRenderer(cr);

		frame.add(table.getTableHeader(), BorderLayout.PAGE_START);
		frame.add(table, BorderLayout.CENTER);

		// Display the window.
		frame.setVisible(true);
	}

	// Make the object [][] representation of students to be added into the
	// JTable
	private void populateTable() {
		int i = 0;
		data = new Object[students.getSize() + 1][5];
		if (students.getSize() > 0) {
			ArrayList<Students> stdList = students.getStudents();
			Iterator<Students> it = stdList.iterator();
			while (it.hasNext()) {
				

				Students s = it.next();
				data[i][0] = s.getName();
				data[i][1] = s.getAge();
				int mLevel = s.getMath();
				String math = (mLevel == 0) ? "K" : Integer.toString(mLevel);
				data[i][2] = math;

				int rLevel = s.getRead();
				String reading = (rLevel == 0) ? "K" : Integer.toString(rLevel);
				data[i][3] = reading;

				int lLevel = s.getLA();
				String la = (lLevel == 0) ? "K" : Integer.toString(lLevel);
				data[i][4] = la;
				i++;
			}
		}

		// Add an empty row

		data[i][0] = "";
		data[i][1] = "";
		data[i][2] = "";
		data[i][3] = "";
		data[i][4] = "";

	}

	public boolean isValidValue(Object value) {
		if (value instanceof String) {
			String sValue = (String) value;

			for (int i = 0; i < validStates.length; i++) {
				if (sValue.equals(validStates[i])) {
					return true;
				}
			}
		}

		return false;
	}

}
