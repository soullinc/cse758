import java.util.ArrayList;

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
		for (int i = 0; i < students.size(); i ++) {
			Students std = students.get(i);
			if (std.getId() == id) {
				return std;
			}
		}
		return null;
	}
	
	public boolean hasStudent (int id) {
		for (int i = 0; i < students.size(); i ++) {
			Students std = students.get(i);
			if (std.getId() == id) {
				return true;
			}
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
	
	public void removeStudent (int id) {
		System.out.println(id);
		for (int i = 0; i < students.size(); i ++) {
			Students std = students.get(i);
			if (std.getId() == id) {
				System.out.println("got to remove");
				students.remove(i);
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
