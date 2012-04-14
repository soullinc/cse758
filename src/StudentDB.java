import java.util.ArrayList;
import java.util.List;

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

	public int getSize() {
		return students.size();
	}
	
	public ArrayList<Students> getStudents() {
		return students;
	}
}
