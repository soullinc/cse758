import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Schedulizer {

	/**
	 * @param args
	 */
	@SuppressWarnings("null")
	public static void main(String[] args) {

		List<Students> std = new ArrayList<Students>();

		Scanner in = new Scanner(System.in);

		System.out.print("Input student info: name:");
		String name = in.nextLine();

		while (!name.equals("-1")) {

			System.out.print("                    age: ");
			int age = in.nextInt();
			System.out.print("                    Math level: ");
			int math = in.nextInt();
			System.out.print("                    LA level: ");
			int la = in.nextInt();
			System.out.print("                    Reading level: ");
			int read = in.nextInt();
			std.add(new Students(name, age, math, la, read));
			in.nextLine();
			System.out.print("Input student info: name:");
			name = in.nextLine();
		}

		System.out.println("\nTotal students: " + std.size());

		//sort by age.
		Collections.sort(std);

		//print sorted list.
		for(int i=0;i<std.size();i++){
		 System.out.println(std.get(i).toString());
		 }

		List<Classes> math = new ArrayList<Classes>();
		List<Classes> la = new ArrayList<Classes>();
		List<Classes> read = new ArrayList<Classes>();

		Classes mathClass=null,laClass=null,readClass=null;
		for (int i = 0; i < std.size(); i++) {
	
			int age = std.get(i).getAge();
			int mathLvl = std.get(i).getMath();
			int laLvl=std.get(i).getLA();
			int readLvl=std.get(i).getRead();
			
			//schedulize math class
			for(Classes cls:math){
				if(cls.getLvl()==mathLvl && Math.abs(cls.getLowestAge()-age)<3 && cls.getTotal()<5){
					mathClass=cls;
				}
			}
			if(mathClass==null){
				mathClass=ClassFactory.createClass("math", mathLvl);
				math.add(mathClass);
			}
			mathClass.addStd(std.get(i));
			mathClass=null;
			
			
			//schedulize LA class
			for(Classes cls:la){
				if(cls.getLvl()==laLvl && Math.abs(cls.getLowestAge()-age)<3 && cls.getTotal()<5){
					laClass=cls;
				}
			}
			if(laClass==null){
				laClass=ClassFactory.createClass("la", laLvl);
				la.add(laClass);
			}
			laClass.addStd(std.get(i));
			laClass=null;
			
			//schedulize reading class
			for(Classes cls:read){
				if(cls.getLvl()==readLvl && Math.abs(cls.getLowestAge()-age)<3 && cls.getTotal()<5){
					readClass=cls;
				}
			}
			if(readClass==null){
				readClass=ClassFactory.createClass("read", readLvl);
				read.add(readClass);
			}
			readClass.addStd(std.get(i));
			readClass=null;
			
			
			
			
			
		}
		
		System.out.println("*********Results************");
		System.out.println("There are "+math.size()+" classes for math:");
		for(Classes cls: math){
			System.out.println(cls.toString());
		}
		
		System.out.println("\nThere are "+la.size()+" classes for LA:");
		for(Classes cls: la){
			System.out.println(cls.toString());
		}
		
		System.out.println("\nThere are "+read.size()+" classes for reading:");
		for(Classes cls: read){
			System.out.println(cls.toString());
		}

	}

}
