import java.util.ArrayList;
import java.util.List;

public class ClassFactory {

	private static int totalClasses = 0;
	private static int totalMath = 0;
	private static int totalLA = 0;
	private static int totalRead = 0;
	private static int maxCls=4;

	private ClassFactory() {
		// empty
	}

	static List<Classes> mathClsLst = new ArrayList<Classes>();
	static List<Classes> laClsLst = new ArrayList<Classes>();
	static List<Classes> readClsLst = new ArrayList<Classes>();

	public static Classes createClass(String name, int lvl) {
		totalClasses++;
		if (name.equals("math")) {
			totalMath++;
		} else if (name.equals("la")) {
			totalLA++;
		} else if (name.equals("read")) {
			totalRead++;
		} else {
			System.err.println("Wrong class name passed into class factory:"
					+ name);
		}
		return new Classes(name, lvl);
	}

	public static int getTotalClasses() {
		return totalClasses;
	}

	public static int getTotalMath() {
		return totalMath;
	}

	public static int getTotalLA() {
		return totalLA;
	}

	public static int getTotalRead() {
		return totalRead;
	}

	/**
	 * Check if a student can be assigned to a particular class
	 * 
	 * @param std
	 * @param cls
	 * @return
	 * 			True if yes, false otherwise.
	 */
	public static boolean compatible(Students std, Classes cls) {
		if(cls.getBL3()==1 && std.getBL()==3){
			return false;
		}
		if(cls.getBL2()==3 && (std.getBL()==2 || std.getBL()==3)){
			return false;
		}
		
		if (cls.getClsName().equals("math")) {
			if(cls.getLvl()==std.getMath() && Math.abs(cls.getLowestAge()-std.getAge())<3 && cls.getTotal()<5){
				return true;
			}else{
				return false;
			}
		} else if (cls.getClsName().equals("la")) {
			if(cls.getLvl()==std.getLA() && Math.abs(cls.getLowestAge()-std.getAge())<3 && cls.getTotal()<5){
				return true;
			}else{
					return false;
				}
		} else if (cls.getClsName().equals("read")) {
			if(cls.getLvl()==std.getRead() && Math.abs(cls.getLowestAge()-std.getAge())<3 && cls.getTotal()<5){
				return true;
			}else{
				return false;
			}
		} else {
			System.err
					.println("A Classes object passed into ClassFactory.compatible method is labeled wrong class name");
			return false;
		}
	}
	
	/**
	 * Set max number of classes allowed. default is 15.
	 * @param i
	 * 			max number of cls allowed.
	 */
	public static void setMaxCls(int i){
		maxCls=i;
	}
	
	/**
	 * Get max number of classes allowed.
	 * @return
	 * 		number of max classes.
	 */
	public static int getMaxCls(){
		return maxCls;
	}
}
