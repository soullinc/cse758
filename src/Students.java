import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Students implements Comparable<Students> {
	
	private int id;
	private String fName;
	private String lName;
	private Date birthDate;
	private double numBDate;
	private int math;
	private int la;
	private int read;
	private static int totalStudents=0;
	private int bl;
	
	//TODO: Replace Age with BDate and calculate Age.
	//Largest age gap 3yrs 11 months
	
	public Students() {
		totalStudents++;
	}
	public Students(Integer id, String fName, String lName, Date bDate,int math,int la, int read, int bl){
		totalStudents+=1;
		this.id = id;
		this.fName=fName;
		this.lName = lName;
		this.birthDate = bDate;
		this.math=math;
		this.la=la;
		this.read=read;
		this.bl = bl;		
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getFirstName(){
		return this.fName;
	}
	
	public String getLastName(){
		return this.lName;
	}
	
	public Date getBirthDate() {
		return this.birthDate;
	}
	
	public double getAge(){
		Calendar c = new GregorianCalendar();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		double numDate = year + (month/12.0);
		return numDate - numBDate;
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
	
	
	public void setFirstName(String name){
		this.fName=name;
	}
	
	public void setLastName(String name){
		this.lName=name;
	}
	
	public void setBirthDate(Date bDate){
		this.birthDate = bDate;
		
		Calendar c = new GregorianCalendar();
		c.setTime(bDate);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		
		this.numBDate = year + (month/12.0);
		
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
	
	public void setId(int id) {
		this.id = id;
	}
	
	public static int getTotal(){
		return totalStudents;
	}

	public double compareAge(Students std) {
		return this.getAge()-std.getAge();
	}
	
	@Override
	public int compareTo(Students std) {
		return (int) Math.floor(this.getAge()-std.getAge());
	}

	@Override
	public String toString(){
		return "Student name:"+fName+" "+lName+"; age: "+Math.floor(getAge())+"; Math level: "+math+"; LA level: "+la+"; Reading level: "+read+".\n";
		
	}
	
	/**
	 * Set behavior lvl
	 * @param i
	 * 		Std's behavior lvl
	 */
	public void setBL(int i){
		this.bl=i;
	}
	
	/**
	 * Get behavior lvl
	 * @return
	 * 		Std's behavior lvl
	 */
	public int getBL(){
		return this.bl;
	}
}
