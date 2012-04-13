
public class ClassFactory {

	private static int totalClasses=0;
	private static int totalMath=0;
	private static int totalLA=0;
	private static int totalRead=0;
	
	private ClassFactory(){
		//empty
	}
	
	public static Classes createClass(String name,int lvl){
		totalClasses++;
		if(name.equals("math")){
			totalMath++;
		}else if(name.equals("la")){
			totalLA++;
		}else if(name.equals("read")){
			totalRead++;
		}else{
			System.err.println("Wrong class name passed into class factory:"+name);
		}
		return new Classes(name,lvl);
	}
	
	public static int getTotalClasses(){
		return totalClasses;
	}
	
	public static int getTotalMath(){
		return totalMath;
	}
	
	public static int getTotalLA(){
		return totalLA;
	}
	
	public static int getTotalRead(){
		return totalRead;
	}
}
