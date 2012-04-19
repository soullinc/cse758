import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class NewFrame implements TableModelListener {

	JTable table;
	JFrame frame;
	StudentDB students;
	static Object[][] data;
	Object[][] backup;
	String[] columnNames = { "Name", "Age", "Math Level", "Reading Level",
			"Language Arts Level" };
	String[] validStates = { "", "K", "1", "2", "3", "4", "5", "6", "7", "8" };

	public NewFrame(JFrame f, StudentDB s) {
		ComboRenderer cr = new ComboRenderer();
		frame = f;
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

		Menu menu = new Menu(students, frame);
		JMenuBar menuBar = menu.getMenu();
		frame.setJMenuBar(menuBar);
		menuBar.setPreferredSize(new Dimension(200, 20));
		// Set the menu bar and add the label to the content pane.
		frame.setJMenuBar(menuBar);

		JToolBar tb = menu.getToolBar();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(tb);
		frame.add(panel, BorderLayout.NORTH);

		populateTable();

		table = new JTable(data, columnNames);
		table.getModel().addTableModelListener(this);
		table.setShowGrid(true);
		table.setGridColor(Color.BLACK);
		table.setRowHeight(20);
		table.setCellSelectionEnabled(true);

		// Create the combo box editor
		JComboBox comboBox = new JComboBox(validStates);
		comboBox.setEditable(true);
		DefaultCellEditor editor = new DefaultCellEditor(comboBox);

		// Create the textfield editor
		JTextField text = new JTextField();
		text.setEditable(true);
		DefaultCellEditor teditor = new DefaultCellEditor(text);

		// Render comboboxes properly
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setCellEditor(teditor);
		tcm.getColumn(1).setCellEditor(teditor);
		tcm.getColumn(2).setCellEditor(editor);
		tcm.getColumn(2).setCellRenderer(cr);
		tcm.getColumn(3).setCellEditor(editor);
		tcm.getColumn(3).setCellRenderer(cr);
		tcm.getColumn(4).setCellEditor(editor);
		tcm.getColumn(4).setCellRenderer(cr);

		// Make Table Scrollable
		JScrollPane pane = new JScrollPane(table);
		pane.setSize(dimension);

		frame.add(pane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// Display the window.
		frame.setVisible(true);
	}

	// Make the object [][] representation of students to be added into the
	// JTable
	private void populateTable() {
		int i = 0;
		data = new Object[300][5];
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

		while (i < 300) {
			data[i][0] = "";
			data[i][1] = "";
			data[i][2] = "";
			data[i][3] = "";
			data[i][4] = "";
			i++;
		}

		backup = data;

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

	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
		int column = e.getColumn();
		TableModel model = (TableModel) e.getSource();
		Object d = model.getValueAt(row, column);
		System.out.println(d.toString());

		Object oldName = backup[row][0];
		Students s;
		boolean newStudent = false;
		if (students.hasStudent(oldName.toString())) {
			s = students.getStudent(oldName.toString());
		} else {
			s = new Students();
			newStudent = true;
		}

		int x;
		switch (column) {
		case 0:
			if (students.hasStudent(d.toString())) {
				JOptionPane
						.showMessageDialog(
								frame,
								"Temporarily not accepting multiple students with same name.\n"
										+ " Once we establish whether or not students already have student ID numbers,\n"
										+ " we will change our data structure!");
			} else {
				s.setName(d.toString());
			}
			break;
		case 1:
			try {
				x = Integer.parseInt(d.toString());
				s.setAge(x);
			} catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(frame,
						"Age must be a numerical value.");
			}
			break;
		case 2:
			x = (d.toString().equals("K")) ? 0 : Integer.parseInt(d.toString());
			s.setMath(x);
			break;
		case 3:
			x = (d.toString().equals("K")) ? 0 : Integer.parseInt(d.toString());
			s.setRead(x);
			break;
		case 4:
			x = (d.toString().equals("K")) ? 0 : Integer.parseInt(d.toString());
			s.setLA(x);
			break;
		}

		if (newStudent) {
			students.addStudent(s);
		} else {
			students.modifyStudent(oldName.toString(), s);
		}

	}

}
