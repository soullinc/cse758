import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;


public class StudentDB {
	ArrayList<Students> students;
	JFrame frame;
	public StudentDB (JFrame f){
		frame = f;
		students = new ArrayList<Students>();
	}
	
	public void addStudent (Students s) {
		students.add(s);
		new NewFrame(frame,this);
		
	}
	
	public Students getStudent (String name) {
		Iterator<Students> it = students.iterator();
		while(it.hasNext()) {
			Students s = it.next();
			if (s.getName().equals(name))
				return s;
		}
		return null;
	}
	
	public boolean hasStudent (String name) {
		Iterator<Students> it = students.iterator();
		while(it.hasNext()) {
			Students s = it.next();
			if (s.getName().equals(name))
				return true;
		}
		return false;
	}
	
	public void modifyStudent (String name, Students s) {
		for (int i = 0; i < students.size(); i ++) {
			Students std = students.get(i);
			if (std.getName().equals(name)) {
				students.remove(i);
				students.add(i, s);
			}
		}
	}

	public int getSize() {
		return students.size();
	}
	
	public ArrayList<Students> getStudents() {
		return students;
	}
}
