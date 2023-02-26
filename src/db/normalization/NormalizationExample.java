package db.normalization;

import java.util.ArrayList;
import java.util.List;

public class NormalizationExample {

    public static void main(String[] args) {

    }

    /*
        1) Each table cell must contain only one value
        2) There should be no duplicate lines
     */
    public static void firstForm() {
        // before
        List<Employee> employeesRaw = new ArrayList<>();
        employeesRaw.add(new Employee("Suvorov Alex", "skates, motorcycle"));
        employeesRaw.add(new Employee("Ivanov Illya", "drawing, playstation"));
        employeesRaw.add(new Employee("Vlasov Anton", "toys, cars"));
        employeesRaw.add(new Employee("Gridin Kuzma", "games, photo"));

        // after
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Suvorov Alex", "skates"));
        employees.add(new Employee("Suvorov Alex", "motorcycle"));
        employees.add(new Employee("Ivanov Illya", "drawing"));
        employees.add(new Employee("Ivanov Illya", "playstation"));
        employees.add(new Employee("Vlasov Anton", "toys"));
        employees.add(new Employee("Vlasov Anton", "cars"));
        employees.add(new Employee("Gridin Kuzma", "games"));
        employees.add(new Employee("Gridin Kuzma", "photo"));
    }

    /*
       1) There is a primary key in the table
       2) All attributes depend on the primary key as a whole and not on any part of it
    */
    public static void secondForm() {
        // before
        List<EmployeeProjects> employeeProjectsList = new ArrayList<>();
        employeeProjectsList.add(new EmployeeProjects(1, 1, "Vlasov Anton", "Reshala", "Coca Cola"));
        employeeProjectsList.add(new EmployeeProjects(1, 2, "Vlasov Anton", "Izmeny", "Samsung"));
        employeeProjectsList.add(new EmployeeProjects(2, 1, "Khovansky Yura", "Reshala", "Coca Cola"));
        employeeProjectsList.add(new EmployeeProjects(3, 2, "Oneshko Yulik", "Izmeny", "Samsung"));

        // after
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Vlasov Anton"));
        employees.add(new Employee(2, "Khovansky Yura"));
        employees.add(new Employee(3, "Oneshko Yulik"));

        List<Project> projects = new ArrayList<>();
        projects.add(new Project(1, "Reshala", "Coca Cola"));
        projects.add(new Project(2, "Izmeny", "Samsung"));

        List<EmployeeProjects> employeeProjects = new ArrayList<>();
        employeeProjects.add(new EmployeeProjects(1, 1));
        employeeProjects.add(new EmployeeProjects(1, 2));
        employeeProjects.add(new EmployeeProjects(2, 1));
        employeeProjects.add(new EmployeeProjects(3, 2));
    }

    /*
        All attributes depend on the primary key and not on any other attributes
     */
    public static void thirdForm() {
        // before
        List<EmployeeAddresses> employeeAddressesList = new ArrayList<>();
        employeeAddressesList.add(new EmployeeAddresses(1, "Vlasov Anton", "Shushary", "+11111"));
        employeeAddressesList.add(new EmployeeAddresses(2, "Khovansky Yura", "Vykhino", "+22222"));
        employeeAddressesList.add(new EmployeeAddresses(3, "Oneshko Yulik", "Devyatkino", "+33333"));
        employeeAddressesList.add(new EmployeeAddresses(4, "Gridin Kuzma", "Shushary", "+11111"));

        // after
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Vlasov Anton", 1));
        employees.add(new Employee(2, "Khovansky Yura", 2));
        employees.add(new Employee(3, "Oneshko Yulik", 3));
        employees.add(new Employee(4, "Gridin Kuzma", 1));

        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address(1, "Shushary", "+11111"));
        addresses.add(new Address(2, "Vykhino", "+22222"));
        addresses.add(new Address(3, "Devyatkino", "+33333"));
    }

    /*
        Boyce-Codd normal form
        Key attributes must not depend on non-key attributes
     */
    public static void thirdFormEnhanced() {
        // before
        List<ProjectTasks> projectTasksList = new ArrayList<>();
        projectTasksList.add(new ProjectTasks(1, "video filming", "Vlasov Anton"));
        projectTasksList.add(new ProjectTasks(1, "video editing", "Petr Korneev"));
        projectTasksList.add(new ProjectTasks(2, "video filming", "Vlasov Anton"));
        projectTasksList.add(new ProjectTasks(2, "video filming", "Oneshko Yulik"));
        projectTasksList.add(new ProjectTasks(2, "video editing", "Egor FromGor"));

        // after
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Vlasov Anton", "video filming"));
        employees.add(new Employee(2, "Petr Korneev", "video editing"));
        employees.add(new Employee(3, "Oneshko Yulik", "video filming"));
        employees.add(new Employee(4, "Egor FromGor", "video editing"));

        List<EmployeeProjects> employeeProjects = new ArrayList<>();
        employeeProjects.add(new EmployeeProjects(1, 1));
        employeeProjects.add(new EmployeeProjects(2, 1));
        employeeProjects.add(new EmployeeProjects(1, 2));
        employeeProjects.add(new EmployeeProjects(3, 2));
        employeeProjects.add(new EmployeeProjects(4, 2));
    }

}

class Employee {

    private int employeeId;
    private String name;
    private String hobbies;
    private int addressId;
    private String skill;

    public Employee(String name, String hobbies) {
        this.name = name;
        this.hobbies = hobbies;
    }

    public Employee(int employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
    }

    public Employee(int employeeId, String name, int addressId) {
        this.employeeId = employeeId;
        this.name = name;
        this.addressId = addressId;
    }

    public Employee(int employeeId, String name, String skill) {
        this.employeeId = employeeId;
        this.name = name;
        this.skill = skill;
    }

}

class EmployeeProjects {

    private int employeeId;
    private int projectId;
    private String name;
    private String project;
    private String projectClient;

    public EmployeeProjects(int employeeId, int projectId, String name, String project, String projectClient) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.name = name;
        this.project = project;
        this.projectClient = projectClient;
    }

    public EmployeeProjects(int employeeId, int projectId) {
        this.employeeId = employeeId;
        this.projectId = projectId;
    }

}

class Project {

    private int projectId;
    private String project;
    private String projectClient;

    public Project(int projectId, String project, String projectClient) {
        this.projectId = projectId;
        this.project = project;
        this.projectClient = projectClient;
    }

}

class EmployeeAddresses {

    private int employeeId;
    private String name;
    private int addressId;
    private String address;
    private String phone;

    public EmployeeAddresses(int employeeId, String name, String address, String phone) {
        this.employeeId = employeeId;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

}

class Address {

    private int addressId;
    private String address;
    private String phone;

    public Address(int addressId, String address, String phone) {
        this.addressId = addressId;
        this.address = address;
        this.phone = phone;
    }

}

class ProjectTasks {

    private int projectId;
    private String task;
    private String responsibleEmployee;

    public ProjectTasks(int projectId, String task, String responsibleEmployee) {
        this.projectId = projectId;
        this.task = task;
        this.responsibleEmployee = responsibleEmployee;
    }

}
