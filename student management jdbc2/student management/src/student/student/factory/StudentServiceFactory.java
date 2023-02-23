package student.student.factory;

import student.student.service.IStudentService;
import student.student.service.StudentServiceImpl;

public class StudentServiceFactory {

	private static IStudentService studentServiceImpl;

	private StudentServiceFactory() {

	}

	public static IStudentService getStudentService() {
		if (studentServiceImpl == null)
			studentServiceImpl = new StudentServiceImpl();
		return studentServiceImpl;
	}

}
