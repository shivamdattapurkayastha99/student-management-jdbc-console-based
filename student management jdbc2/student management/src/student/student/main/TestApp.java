package student.student.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import student.student.controller.IStudentController;
import student.student.dto.Student;
import student.student.factory.StudentControllerFactory;
import java.util.logging.Logger;
public class TestApp {

	public static void main(String[] args) {

		IStudentController studentController = null;
		String status = null, name = null, city = null, email = null, country = null;
		Integer id = null;
		Student studentRecord = null;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Logger logger = Logger.getLogger(TestApp.class.getName());
		try {
			while (true) {
				logger.info("# Select the operation to perform: ");
				logger.info(" 1. CREATE");
				logger.info(" 2. READ");
				logger.info(" 3. UPDATE");
				logger.info(" 4. DELETE");
				logger.info(" 5. EXIT");
				logger.info("Your options are: [1, 2, 3, 4, 5]: ");
				Integer option = Integer.parseInt(br.readLine());

				studentController = StudentControllerFactory.getStudentController();
				switch (option) {
				case 1:
					logger.info("Enter the name: ");
					name = br.readLine();

					logger.info("Enter the city: ");
					city = br.readLine();

					logger.info("Enter the email: ");
					email = br.readLine();

					logger.info("Enter the country: ");
					country = br.readLine();

					Student student = new Student();
					student.setName(name);
					student.setCity(city);
					student.setEmail(email);
					student.setCountry(country);

					status = studentController.save(student);
					if (status.equalsIgnoreCase("success")) {
						logger.info("Record Inserted Successfully :)");
					} else if (status.equalsIgnoreCase("failure")) {
						logger.info("Record Insertion failed :(");
					} else {
						logger.info("Some problem occured!");
					}

					break;
				case 2:
					logger.info("Enter the ID to fetch details: ");
					id = Integer.parseInt(br.readLine());
					studentRecord = studentController.findById(id);
					if (studentRecord != null)
						logger.info(studentRecord.toString());
					else
						logger.info("Record not available for the given id: " + id);
					break;

				case 3:
					logger.info("Enter the ID of the record to be updated: ");
					id = Integer.parseInt(br.readLine());
					studentRecord = studentController.findById(id);
					if (studentRecord != null) {
						Student newStudent = new Student();
						newStudent.setId(id);
						logger.info("Enter the new Student Name [Old Name is: " + studentRecord.getName() + "]: ");
						String newName = br.readLine();
						if (newName == null || newName.equals("")) {
							newStudent.setName(studentRecord.getName());
						} else {
							newStudent.setName(newName);
						}

						logger.info(
								"Enter the new Student email [Old email is: " + studentRecord.getEmail() + "]: ");
						String newEmail = br.readLine();
						if (newEmail == null || newEmail.equals("")) {
							newStudent.setEmail(studentRecord.getEmail());
						} else {
							newStudent.setEmail(newEmail);
						}

						logger.info("Enter the new Student City [Old City is: " + studentRecord.getCity() + "]: ");
						String newCity = br.readLine();
						if (newCity == null || newCity.equals("")) {
							newStudent.setCity(studentRecord.getCity());
						} else {
							newStudent.setCity(newCity);
						}

						logger.info(
								"Enter the new Student Country [Old country is: " + studentRecord.getCountry() + "]: ");
						String newCountry = br.readLine();
						if (newCountry == null || newCountry.equals("")) {
							newStudent.setCountry(studentRecord.getCountry());
						} else {
							newStudent.setCountry(newCountry);
						}

						status = studentController.updateById(newStudent);
						if (status.equalsIgnoreCase("success")) {
							logger.info("Record Updated Successfully :)");
						} else if (status.equalsIgnoreCase("failure")) {
							logger.info("Record Updation failed :(");
						} else {
							logger.info("Some problem occured!");
						}
					}

					else {
						logger.info("Record not available for the given id: " + id);
					}
					break;

				case 4:
					logger.info("Enter the ID to be deleted: ");
					id = Integer.parseInt(br.readLine());
					status = studentController.deleteById(id);
					if (status.equalsIgnoreCase("success")) {
						logger.info("Record Deleted Successfully :)");
					} else if (status.equalsIgnoreCase("failure")) {
						logger.info("Record Deletion failed :(");
					} else {
						logger.info("Record not available for the given id to delete!");
					}

					break;
				case 5:
					logger.info("Thank you for using the application :)");
					System.exit(0);

				default:
					logger.info("Please enter the given option for operation!");
					
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}