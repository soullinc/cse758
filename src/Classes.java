import java.util.ArrayList;
import java.util.List;


public class Classes {
	private String classname;
	private int lvl;
	private int totalStd;
	private List<Students> students;
	private int lowestAge=999;
	
	public Classes(String name, int lvl){
		students = new ArrayList<Students>();
		this.classname=name;
		this.lvl=lvl;
		totalStd=0;
	}
	
	public void addStd(Students std){
		students.add(std);
		if (std.getAge()<lowestAge){
			this.lowestAge=std.getAge();
		}
		totalStd++;
	}
	
	public Students removeStd(String name){
		for(Students std: this.students){
			if (std.getName()==name){
				this.students.remove(std);
				return std;
			}
		}
		return null;
	}
	
	public String getClsName(){
		return this.classname;
	}
	
	public int getLvl(){
		return this.lvl;
	}
	
	public int getTotal(){
		return this.totalStd;
	}
	
	public int getLowestAge(){
		return this.lowestAge;
	}
	
	@Override
	public String toString(){
		
		StringBuilder sb =  new StringBuilder();
		if(students.size()!=0){
			for(Students std:students){
				 sb.append(std.toString());
			}
			return sb.toString();
		}else{
			return "No students in this class.\n";
		}
	}
}
