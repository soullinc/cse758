
public class Students implements Comparable<Students>{
	
	private String name;
	private int age;
	private int math;
	private int la;
	private int read;
	private static int totalStudents=0;
	
	//TODO: Replace Age with BDate and calculate Age.
	//Largest age gap 3yrs 11 months
	
	public Students() {
		totalStudents++;
	}
	public Students(String name, int age,int math,int la, int read){
		totalStudents+=1;
		this.name=name;
		this.age=age;
		this.math=math;
		this.la=la;
		this.read=read;
				
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getAge(){
		return this.age;
	}
	
	public int getMath(){
		return this.math;
	}
	
	public int getLA(){
		return this.la;
	}
	
	public int getRead(){
		return this.read;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public void setAge(int age){
		this.age=age;
	}
	
	public void setMath(int lvl){
		this.math=lvl;
	}
	
	public void setLA(int lvl){
		this.la=lvl;
	}
	
	public void setRead(int lvl){
		this.read=lvl;
	}
	
	public static int getTotal(){
		return totalStudents;
	}

	@Override
	public int compareTo(Students std) {
		return this.age-std.age;
	}

	@Override
	public String toString(){
		return "Student name:"+name+"; age: "+age+"; Math level: "+math+"; LA level: "+la+"; Reading level: "+read+".\n";
		
	}
}
