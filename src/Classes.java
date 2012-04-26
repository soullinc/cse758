import java.util.ArrayList;
import java.util.List;


public class Classes {
	private String classname;
	private int lvl;
	private int totalStd;
	private List<Students> students;
	private int lowestAge=999;
	private int bl3=0;
	private int bl2=0;
	
	public Classes(String name, int lvl){
		students = new ArrayList<Students>();
		this.classname=name;
		this.lvl=lvl;
		totalStd=0;
	}
	
	public void addStd(Students std){
		students.add(std);
		if (std.getAge()<lowestAge){
			this.lowestAge= (int) Math.floor(std.getAge());
		}
		if(std.getBL()==3){
			this.bl3++;
		}else if(std.getBL()==2){
			this.bl2++;
		}
		totalStd++;
	}
	
	public Students removeStd(int id){
		for(Students std: this.students){
			if (std.getId()==id){
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
	
	/**
	 * Get number of behavior lvl3 students
	 * @return	Number of BL3 students
	 */
	public int getBL3(){
		return this.bl3;
	}
	
	/**
	 * Get number of behavior lvl2 students
	 * @return	Number of BL2 students
	 */
	public int getBL2(){
		return this.bl2;
	}
	
	
}
