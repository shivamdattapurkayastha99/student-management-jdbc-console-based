package student.student.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import student.student.controller.IStudentController;
import student.student.dto.Student;
import student.student.factory.StudentControllerFactory;

public class TestApp {

	public static void main(String[] args) {

		IStudentController studentController = null;
		String status = null, name = null, city = null, email = null, country = null;
		Integer id = null;
		Student studentRecord = null;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			while (true) {
				System.out.println("# Select the operation to perform: ");
				System.out.println(" 1. CREATE");
				System.out.println(" 2. READ");
				System.out.println(" 3. UPDATE");
				System.out.println(" 4. DELETE");
				System.out.println(" 5. EXIT");
				System.out.print("Your options are: [1, 2, 3, 4, 5]: ");
				Integer option = Integer.parseInt(br.readLine());

				studentController = StudentControllerFactory.getStudentController();
				switch (option) {
				case 1:
					System.out.print("Enter the name: ");
					name = br.readLine();

					System.out.print("Enter the city: ");
					city = br.readLine();

					System.out.print("Enter the email: ");
					email = br.readLine();

					System.out.print("Enter the country: ");
					country = br.readLine();

					Student student = new Student();
					student.setName(name);
					student.setCity(city);
					student.setEmail(email);
					student.setCountry(country);

					status = studentController.save(student);
					if (status.equalsIgnoreCase("success")) {
						System.out.println("Record Inserted Successfully :)");
					} else if (status.equalsIgnoreCase("failure")) {
						System.out.println("Record Insertion failed :(");
					} else {
						System.out.println("Some problem occured!");
					}

					break;
				case 2:
					System.out.print("Enter the ID to fetch details: ");
					id = Integer.parseInt(br.readLine());
					studentRecord = studentController.findById(id);
					if (studentRecord != null)
						System.out.println(studentRecord);
					else
						System.out.println("Record not available for the given id: " + id);
					break;

				case 3:
					System.out.print("Enter the ID of the record to be updated: ");
					id = Integer.parseInt(br.readLine());
					studentRecord = studentController.findById(id);
					if (studentRecord != null) {
						Student newStudent = new Student();
						newStudent.setId(id);
						System.out.print("Enter the new Student Name [Old Name is: " + studentRecord.getName() + "]: ");
						String newName = br.readLine();
						if (newName == null || newName.equals("")) {
							newStudent.setName(studentRecord.getName());
						} else {
							newStudent.setName(newName);
						}

						System.out.print(
								"Enter the new Student email [Old email is: " + studentRecord.getEmail() + "]: ");
						String newEmail = br.readLine();
						if (newEmail == null || newEmail.equals("")) {
							newStudent.setEmail(studentRecord.getEmail());
						} else {
							newStudent.setEmail(newEmail);
						}

						System.out.print("Enter the new Student City [Old City is: " + studentRecord.getCity() + "]: ");
						String newCity = br.readLine();
						if (newCity == null || newCity.equals("")) {
							newStudent.setCity(studentRecord.getCity());
						} else {
							newStudent.setCity(newCity);
						}

						System.out.print(
								"Enter the new Student Country [Old country is: " + studentRecord.getCountry() + "]: ");
						String newCountry = br.readLine();
						if (newCountry == null || newCountry.equals("")) {
							newStudent.setCountry(studentRecord.getCountry());
						} else {
							newStudent.setCountry(newCountry);
						}

						status = studentController.updateById(newStudent);
						if (status.equalsIgnoreCase("success")) {
							System.out.println("Record Updated Successfully :)");
						} else if (status.equalsIgnoreCase("failure")) {
							System.out.println("Record Updation failed :(");
						} else {
							System.out.println("Some problem occured!");
						}
					}

					else {
						System.out.println("Record not available for the given id: " + id);
					}
					break;

				case 4:
					System.out.print("Enter the ID to be deleted: ");
					id = Integer.parseInt(br.readLine());
					status = studentController.deleteById(id);
					if (status.equalsIgnoreCase("success")) {
						System.out.println("Record Deleted Successfully :)");
					} else if (status.equalsIgnoreCase("failure")) {
						System.out.println("Record Deletion failed :(");
					} else {
						System.out.println("Record not available for the given id to delete!");
					}

					break;
				case 5:
					System.out.println("Thank you for using the application :)");
					System.exit(0);

				default:
					System.out.println("Please enter the given option for operation!");
					
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}