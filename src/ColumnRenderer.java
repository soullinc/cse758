import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class ColumnRenderer extends JComboBox implements TableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void updateUI() {
		super.updateUI();
	}

	public void revalidate() {
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (value != null) {
			// System.out.println(value.toString());
			removeAllItems();
			addItem(value);
		}
		return this;
	}
}