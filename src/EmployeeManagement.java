import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManagement {
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private final String dbURL = "jdbc:sqlserver://QUANGNGUYEN;databaseName=QLNV;useUnicode=true&characterEncoding=UTF-8;user=sa;password=12345;encrypt=true;trustServerCertificate=true";

    public static void main(String[] args) {
        while (true) {
            System.out.println("Employee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Delete Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Show All Employees");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    deleteEmployee();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    showAllEmployees();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void addEmployee() {
        System.out.println("Choose type of employee:");
        System.out.println("1. Experience");
        System.out.println("2. Fresher");
        System.out.println("3. Intern");
        int typeChoice = scanner.nextInt();
        scanner.nextLine();

        Employee employee = null;

        switch (typeChoice) {
            case 1:
                employee = new Experience();
                break;
            case 2:
                employee = new Fresher();
                break;
            case 3:
                employee = new Intern();
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.print("Enter Full Name: ");
        employee.setFullName(scanner.nextLine());
        System.out.print("Enter Birthday (yyyy-mm-dd): ");
        employee.setBirthday(scanner.nextLine());
        System.out.print("Enter Phone: ");
        employee.setPhone(scanner.nextLine());
        System.out.print("Enter Email: ");
        employee.setEmail(scanner.nextLine());
        System.out.print("Enter Employee Type: ");
        employee.setEmployee_type(scanner.nextLine());

        if (employee instanceof Experience) {
            System.out.print("Enter Experience in Year: ");
            ((Experience) employee).setExpInYear(scanner.nextInt());
            scanner.nextLine();
            System.out.print("Enter Professional Skill: ");
            ((Experience) employee).setProSkill(scanner.nextLine());
        } else if (employee instanceof Fresher) {
            System.out.print("Enter Graduation Date (yyyy-mm-dd): ");
            ((Fresher) employee).setGraduation_date(scanner.nextLine());
            System.out.print("Enter Graduation Rank: ");
            ((Fresher) employee).setGraduation_rank(scanner.nextLine());
            System.out.print("Enter Education: ");
            ((Fresher) employee).setEducation(scanner.nextLine());
        } else if (employee instanceof Intern) {
            System.out.print("Enter Majors: ");
            ((Intern) employee).setMajors(scanner.nextLine());
            System.out.print("Enter Semester: ");
            ((Intern) employee).setSemester(scanner.nextLine());
            System.out.print("Enter University Name: ");
            ((Intern) employee).setUniversity_name(scanner.nextLine());
        }

        employees.add(employee);

        EmployeeManagement manager = new EmployeeManagement();
        manager.saveEmployeeToDB(employee);

        System.out.println("Employee added successfully.");
        System.out.println("Employee added successfully.");
    }

    public static void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Employee employee : employees) {
            if (employee.getID().equals(id)) {
                employees.remove(employee);
                System.out.println("Employee deleted successfully.");
                return;
            }
        }

        System.out.println("Employee not found.");
    }

    public static void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Employee employee : employees) {
            if (employee.getID().equals(id)) {
                System.out.println("Choose field to update:");
                System.out.println("1. Full Name");
                System.out.println("2. Birthday");
                System.out.println("3. Phone");
                System.out.println("4. Email");
                System.out.println("5. Employee Type");
                int fieldChoice = scanner.nextInt();
                scanner.nextLine();

                switch (fieldChoice) {
                    case 1:
                        System.out.print("Enter new Full Name: ");
                        employee.setFullName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.print("Enter new Birthday (yyyy-mm-dd): ");
                        employee.setBirthday(scanner.nextLine());
                        break;
                    case 3:
                        System.out.print("Enter new Phone: ");
                        employee.setPhone(scanner.nextLine());
                        break;
                    case 4:
                        System.out.print("Enter new Email: ");
                        employee.setEmail(scanner.nextLine());
                        break;
                    case 5:
                        System.out.print("Enter new Employee Type: ");
                        employee.setEmployee_type(scanner.nextLine());
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        return;
                }

                System.out.println("Employee updated successfully.");
                return;
            }
        }

        System.out.println("Employee not found.");
    }

    public static void showAllEmployees() {
        for (Employee employee : employees) {
            employee.ShowInfo();
            System.out.println("------------------------------");
        }
    }

    public void saveEmployeeToDB(Employee employee) {
        final String dbURL = "jdbc:sqlserver://QUANGNGUYEN;databaseName=QLNV;useUnicode=true&characterEncoding=UTF-8;user=sa;password=12345;encrypt=true;trustServerCertificate=true";

        String query = "INSERT INTO employees (fullName, birthday, phone, email, employee_type, expInYear, proSkill, graduation_date, graduation_rank, education, majors, semester, university_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(dbURL);
             PreparedStatement pstmt = conn.prepareStatement(query)
        ) {
            pstmt.setString(1, employee.getFullName());
            pstmt.setString(2, employee.getBirthday());
            pstmt.setString(3, employee.getPhone());
            pstmt.setString(4, employee.getEmail());
            pstmt.setString(5, employee.getEmployee_type());

            if (employee instanceof Experience) {
                pstmt.setInt(6, ((Experience) employee).getExpInYear());
                pstmt.setString(7, ((Experience) employee).getProSkill());
            } else {
                pstmt.setNull(6, java.sql.Types.INTEGER);
                pstmt.setNull(7, java.sql.Types.NVARCHAR);
            }

            if (employee instanceof Fresher) {
                pstmt.setString(8, ((Fresher) employee).getGraduation_date());
                pstmt.setString(9, ((Fresher) employee).getGraduation_rank());
                pstmt.setString(10, ((Fresher) employee).getEducation());
            } else {
                pstmt.setNull(8, java.sql.Types.NVARCHAR);
                pstmt.setNull(9, java.sql.Types.NVARCHAR);
                pstmt.setNull(10, java.sql.Types.NVARCHAR);
            }

            if (employee instanceof Intern) {
                pstmt.setString(11, ((Intern) employee).getMajors());
                pstmt.setString(12, ((Intern) employee).getSemester());
                pstmt.setString(13, ((Intern) employee).getUniversity_name());
            } else {
                pstmt.setNull(11, java.sql.Types.NVARCHAR);
                pstmt.setNull(12, java.sql.Types.NVARCHAR);
                pstmt.setNull(13, java.sql.Types.NVARCHAR);
            }

            pstmt.executeUpdate();
            System.out.println("Employee saved to database successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to save employee to database.");
        }
    }
}

