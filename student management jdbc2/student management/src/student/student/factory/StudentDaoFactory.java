package student.student.factory;

import student.student.dao.IStudentDao;
import student.student.dao.StudentDaoImpl;

public class StudentDaoFactory {

	private static IStudentDao studentDao = null;

	private StudentDaoFactory() {
	}

	public static IStudentDao getStudentDao() {
		if (studentDao == null)
			studentDao = new StudentDaoImpl();
		return studentDao;
	}
}
