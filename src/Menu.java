import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class Menu extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenuBar bar;
	JMenu file, edit, exit;
	JMenuItem new1, open, save, copy, paste, exit1;
	JTextArea textArea;
	JFileChooser chooser;
	FileOutputStream fos;
	BufferedWriter bwriter;
	StudentDB students;

	public Menu(StudentDB s) {
		students = s;
		bar = new JMenuBar();
		bar.setFont(new Font("Arial", Font.BOLD, 14));
		file = new JMenu(" File ");
		new1 = new JMenuItem(" New     ");
		new1.addActionListener(this);
		open = new JMenuItem(" Open... ");
		open.addActionListener(this);
		save = new JMenuItem(" Save... ");
		save.addActionListener(this);
		file.add(new1);
		file.add(open);
		file.add(save);
		bar.add(file);
		edit = new JMenu(" Edit ");
		copy = new JMenuItem(" Copy ");
		paste = new JMenuItem(" Paste ");
		edit.add(copy);
		edit.add(paste);
		bar.add(edit);
		exit = new JMenu(" Exit ");
		exit1 = new JMenuItem("Exit Your App");
		exit1.addActionListener(this);

		exit.add(exit1);
		bar.add(exit);

	}

	public void actionPerformed(ActionEvent event) {
		Object obj = event.getSource();
		chooser = new JFileChooser();
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			if (obj.equals(open)) {
				// use chooser.getSelectedFile() to get file
				// Some code here to parse or call a parser
				readFile(chooser.getSelectedFile());

			} else if (obj.equals(new1)) {

			} else if (obj.equals(save)) {
				// Some code here to write

			} else if (obj.equals(copy)) {

			} else if (obj.equals(paste)) {

			} else if (obj.equals(exit1)) {
				// Probably want to do checking here
				System.exit(0);
			}

	}

	public JMenuBar get_menu() {
		return bar;
	}

	private void readFile(File filename) {
		try {
			FileInputStream fis = new FileInputStream(filename);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			while (br.ready()) {
				String line = br.readLine();
				String[] params = line.split(",");
				if (params.length == 2) {
					Students s = new Students(params[0],
							Integer.parseInt(params[1]), 0, 0, 0);
					students.addStudent(s);
				} else if (params.length == 5) {
					Students s = new Students(params[0],
							Integer.parseInt(params[1]),
							Integer.parseInt(params[2]),
							Integer.parseInt(params[3]),
							Integer.parseInt(params[4]));
					students.addStudent(s);
				} else {
					// error
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
