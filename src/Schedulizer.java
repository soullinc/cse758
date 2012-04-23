import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Schedulizer {

	/**
	 * @param args
	 */
	@SuppressWarnings("null")
	public static void genSchedule(StudentDB stds) {

		List<Students> std = stds.getStudents();

		/*
		 * Scanner in = new Scanner(System.in);
		 * 
		 * System.out.print("Input student info: name:"); String name =
		 * in.nextLine();
		 * 
		 * while (!name.equals("-1")) {
		 * 
		 * System.out.print("                    age: "); int age =
		 * in.nextInt(); System.out.print("                    Math level: ");
		 * int math = in.nextInt();
		 * System.out.print("                    LA level: "); int la =
		 * in.nextInt();
		 * System.out.print("                    Reading level: "); int read =
		 * in.nextInt(); std.add(new Students(name, age, math, la, read));
		 * in.nextLine(); System.out.print("Input student info: name:"); name =
		 * in.nextLine(); }
		 * 
		 * System.out.println("\nTotal students: " + std.size());
		 */

		// sort by age.
		Collections.sort(std);

		// print sorted list.can be deleted for final product
		for (int i = 0; i < std.size(); i++) {
			System.out.println(std.get(i).toString());
		}

		// List<Classes> math = new ArrayList<Classes>();
		// List<Classes> la = new ArrayList<Classes>();
		// List<Classes> read = new ArrayList<Classes>();

		Classes mathClass = null, laClass = null, readClass = null;
		for (int i = 0; i < std.size(); i++) {

			// schedulize math class
			for (Classes cls : ClassFactory.mathClsLst) {
				if (ClassFactory.compatible(std.get(i), cls)) {
					mathClass = cls;
					break;
				}
			}
			if (mathClass == null) {
				mathClass = ClassFactory.createClass("math", std.get(i)
						.getMath());
				ClassFactory.mathClsLst.add(mathClass);
			}
			mathClass.addStd(std.get(i));
			mathClass = null;

			// schedulize LA class
			for (Classes cls : ClassFactory.laClsLst) {
				if (ClassFactory.compatible(std.get(i), cls)) {
					laClass = cls;
				}
			}
			if (laClass == null) {
				laClass = ClassFactory.createClass("la", std.get(i).getLA());
				ClassFactory.laClsLst.add(laClass);
			}
			laClass.addStd(std.get(i));
			laClass = null;

			// schedulize reading class
			for (Classes cls : ClassFactory.readClsLst) {
				if (ClassFactory.compatible(std.get(i), cls)) {
					readClass = cls;
				}
			}
			if (readClass == null) {
				readClass = ClassFactory.createClass("read", std.get(i)
						.getRead());
				ClassFactory.readClsLst.add(readClass);
			}
			readClass.addStd(std.get(i));
			readClass = null;

		}

		System.out.println("*********Results************");
		System.out.println("There are " + ClassFactory.mathClsLst.size()
				+ " classes for math:");
		for (Classes cls : ClassFactory.mathClsLst) {
			System.out.println(cls.toString());
		}

		System.out.println("\nThere are " + ClassFactory.laClsLst.size()
				+ " classes for LA:");
		for (Classes cls : ClassFactory.laClsLst) {
			System.out.println(cls.toString());
		}

		System.out.println("\nThere are " + ClassFactory.readClsLst.size()
				+ " classes for reading:");
		for (Classes cls : ClassFactory.readClsLst) {
			System.out.println(cls.toString());
		}

	}

}
