import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Schedulizer {

	/**
	 * @param args
	 */
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

		Classes mathClass = null, laClass = null, readClass = null;
		List<Students> unluckyStd = new ArrayList<Students>();
		boolean foundCls;
		for (int i = 0; i < std.size(); i++) {

			foundCls = false;

			// schedulize math class
			// check if there is already a class compatible with current student
			for (Classes cls : ClassFactory.mathClsLst) {
				if (ClassFactory.compatible(std.get(i), cls)) {
					mathClass = cls;
					mathClass.addStd(std.get(i));
					foundCls = true;
					break;
				}
			}

			// no compatible cls, check if we can create a new class, remember
			// there is a limit on max#ofCls
			// if(mathClass == null &&
			// ClassFactory.getMaxCls()<=ClassFactory.getTotalMath()){
			// unluckyStd.add(std.get(i));
			//
			// continue;// those unlucky students...
			// }else if (mathClass == null) {
			// mathClass = ClassFactory.createClass("math", std.get(i)
			// .getMath());
			// ClassFactory.mathClsLst.add(mathClass);
			// mathClass.addStd(std.get(i));
			//
			// }

			if (!foundCls) {
				if (ClassFactory.getTotalMath() < ClassFactory.getMaxCls()) {
					mathClass = ClassFactory.createClass("math", std.get(i)
							.getMath());
					ClassFactory.mathClsLst.add(mathClass);
					mathClass.addStd(std.get(i));
					foundCls = true;
				} else {
					unluckyStd.add(std.get(i));
					continue;// those unlucky students...
				}
			}

			foundCls = false;
			// schedulize LA class
			for (Classes cls : ClassFactory.laClsLst) {
				if (ClassFactory.compatible(std.get(i), cls)) {
					laClass = cls;
					laClass.addStd(std.get(i));
					foundCls = true;
					break;
				}
			}
			// if(laClass == null &&
			// ClassFactory.getMaxCls()<=ClassFactory.getTotalLA()){
			// unluckyStd.add(std.get(i));
			// //roll back...
			// mathClass.removeStd(std.get(i).getName());
			// continue;// those unlucky students...
			// }else if (laClass == null) {
			// laClass = ClassFactory.createClass("la", std.get(i).getLA());
			// ClassFactory.laClsLst.add(laClass);
			// laClass.addStd(std.get(i));
			// }
			if (!foundCls) {
				if (ClassFactory.getTotalLA() < ClassFactory.getMaxCls()) {
					laClass = ClassFactory
							.createClass("la", std.get(i).getLA());
					ClassFactory.laClsLst.add(laClass);
					laClass.addStd(std.get(i));
					foundCls = true;
				} else {
					unluckyStd.add(std.get(i));
					// roll back...
					mathClass.removeStd(std.get(i).getId());
					continue;// those unlucky students...
				}
			}

			foundCls = false;
			// schedulize reading class
			for (Classes cls : ClassFactory.readClsLst) {
				if (ClassFactory.compatible(std.get(i), cls)) {
					readClass = cls;
					readClass.addStd(std.get(i));
					foundCls = true;
					break;
				}
			}
			// if(readClass == null &&
			// ClassFactory.getMaxCls()<=ClassFactory.getTotalRead()){
			// unluckyStd.add(std.get(i));
			// //roll back...
			// mathClass.removeStd(std.get(i).getName());
			// laClass.removeStd(std.get(i).getName());
			// continue;// those unlucky students...
			// }else if (readClass == null) {
			// readClass = ClassFactory.createClass("read", std.get(i)
			// .getRead());
			// ClassFactory.readClsLst.add(readClass);
			// readClass.addStd(std.get(i));
			// }

			if (!foundCls) {
				if (ClassFactory.getTotalRead() < ClassFactory.getMaxCls()) {
					readClass = ClassFactory.createClass("read", std.get(i)
							.getRead());
					ClassFactory.readClsLst.add(readClass);
					readClass.addStd(std.get(i));
					foundCls = true;
				} else {
					unluckyStd.add(std.get(i));
					// roll back...
					mathClass.removeStd(std.get(i).getId());
					laClass.removeStd(std.get(i).getId());
					continue;// those unlucky students...
				}
			}

			// //get ready for next round.
			// mathClass=null;
			// laClass=null;
			// readClass = null;
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

		System.out.println("*******Unlucky Students********");
		for (Students stdss : unluckyStd) {
			System.out.println(stdss.toString());
		}

	}

	public void runTest() {
		System.out
				.println("Running automatic testing for generated schedules:");

		// ==============================
		System.out
				.println("Testing on restriction on total number of classes:");
		System.out.println("	Max # of classes: " + ClassFactory.getMaxCls());
		System.out.println("	# of all math classes: "
				+ ClassFactory.getTotalMath());
		System.out.println("	# of all LArt classes: "
				+ ClassFactory.getTotalLA());
		System.out.println("	# of all reading classes: "
				+ ClassFactory.getTotalRead());
		boolean result = ClassFactory.getTotalMath() <= ClassFactory
				.getMaxCls()
				&& ClassFactory.getTotalLA() <= ClassFactory.getMaxCls()
				&& ClassFactory.getTotalRead() <= ClassFactory.getMaxCls();

		if (result) {
			System.out.println("	Result: Pass");
		} else {
			System.out.println("	Result: Fail");
			System.exit(0);
		}
		// ==============================
		System.out
				.println("Testing on restriction on max number of student per class:");
		System.out.println("	Max # of students per class: 5");
		result = true;
		for (Classes cls : ClassFactory.mathClsLst) {
			if (cls.getTotal() > 5) {
				result = false;
				break;
			}
		}
		if (result) {
			System.out.println("\tAll math classes passed.");
		} else {
			System.out.println("\tAt least one math class failed");
			System.exit(0);
		}
		for (Classes cls : ClassFactory.laClsLst) {
			if (cls.getTotal() > 5) {
				result = false;
				break;
			}
		}
		if (result) {
			System.out.println("\tAll LA classes passed.");
		} else {
			System.out.println("\tAt least one LA class failed");
			System.exit(0);
		}

		for (Classes cls : ClassFactory.readClsLst) {
			if (cls.getTotal() > 5) {
				result = false;
				break;
			}
		}
		if (result) {
			System.out.println("\tAll reading classes passed.");
		} else {
			System.out.println("\tAt least one reading class failed");
			System.exit(0);
		}
		//========================
		System.out.println("Testing on age restriction:");
		//========================
		System.out.println("Testing on behavior level restriction:");
		//========================
		System.out.println("Testing on whether each class has only one level of students:");
		//=======================
		System.out.println("Testing on unlucky students are truly unlucky, and cannot be assgined to any class:");
		//======================
		
	}

}
