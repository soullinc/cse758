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
		//new NewFrame(frame,this);
		
	}
	
	public Students getStudent (int id) {
		Iterator<Students> it = students.iterator();
		while(it.hasNext()) {
			Students s = it.next();
			if (s.getId() == id)
				return s;
		}
		return null;
	}
	
	public boolean hasStudent (int id) {
		Iterator<Students> it = students.iterator();
		while(it.hasNext()) {
			Students s = it.next();
			if (s.getId() == id)
				return true;
		}
		return false;
	}
	
	public void modifyStudent (int id, Students s) {
		for (int i = 0; i < students.size(); i ++) {
			Students std = students.get(i);
			if (std.getId() == id) {
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
