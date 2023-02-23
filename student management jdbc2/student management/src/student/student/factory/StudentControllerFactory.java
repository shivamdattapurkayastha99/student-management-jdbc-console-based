package student.student.factory;

import student.student.controller.IStudentController;
import student.student.controller.StudentControllerImpl;

public class StudentControllerFactory {

	private static IStudentController studentController;

	private StudentControllerFactory() {

	}

	public static IStudentController getStudentController() {
		if (studentController == null)
			studentController = new StudentControllerImpl();

		return studentController;
	}
}
