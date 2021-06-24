package com.vaibhav.example;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Collectors.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class Main {
    private static DecimalFormat df = new DecimalFormat("#.##");

    public static List<Employee> initializeCollections() {
        List<Employee> employeeList = new ArrayList<Employee>();

        employeeList.add(new Employee(111, "Jiya Brein", 32, "Female",
                "HR", 2011, 25000.0));
        employeeList.add(new Employee(122, "Paul Niksui", 25, "Male",
                "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Martin Theron", 29, "Male",
                "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(144, "Murali Gowda", 28, "Male",
                "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(155, "Nima Roy", 27, "Female",
                "HR", 2013, 22700.0));
        employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male",
                "Security And Transport", 2016, 10500.0));
        employeeList.add(new Employee(177, "Manu Sharma", 35, "Male",
                "Account And Finance", 2010, 27000.0));
        employeeList.add(new Employee(188, "Wang Liu", 31, "Male",
                "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female",
                "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Employee(200, "Jaden Dough", 38, "Male",
                "Security And Transport", 2015, 11000.5));
        employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female",
                "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male",
                "Product Development", 2016, 28200.0));
        employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female",
                "Account And Finance", 2013, 21300.0));
        employeeList.add(new Employee(244, "Nicolus Den", 24, "Male",
                "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Employee(255, "Ali Baig", 23, "Male",
                "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female",
                "Product Development", 2015, 28900.0));
        employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male",
                "Product Development", 2012, 35700.0));

        return employeeList;
    }

    // How many male and female employees are there in the organization?

    public static Map<String, List<Employee>> groupEmployeesByGender() {
        List<Employee> employees = initializeCollections();

        Map<String, List<Employee>> result = employees.stream()
                .collect(groupingBy(Employee::getGender, toList()));
        Map<Boolean, List<Employee>> booleanListMap = employees.stream().collect(Collectors.partitioningBy( e -> e.getGender().equals("Male")));
        return result;
    }
//    public static Map<Character, Long> characterIntegerMap(String name){
//
//    }


    //Print the name of all departments in the organization?
    public static List<String> getDepartmentList() {
        List<Employee> employees = initializeCollections();

        return employees.stream().map(e -> e.getDepartment())
                .distinct()
                .collect(toList());
    }

    //What is the average age of male and female employees?
    public static Map<String, Double> avgAgeByGender() {
        List<Employee> employees = initializeCollections();

        return employees.stream().collect(Collectors.groupingBy(Employee::getGender,
                Collectors.averagingDouble(Employee::getAge)));
    }


    //Get the details of highest paid employee in the organization?
    public static Double getHighestPaidSalary() {
        List<Employee> employees = initializeCollections();

        Double highestPaidSalary = employees.stream()
                .map(Employee::getSalary)
                .max(Double::compareTo)
                .get();
        return highestPaidSalary;
    }


    //Get the names of all employees who have joined after 2015?
    public static List<Employee> employeesJoinedAfter2015() {
        List<Employee> employees = initializeCollections();

        return employees.stream()
                .filter(e -> e.getYearOfJoining() > 2015)
                .collect(toList());
    }

    //Count the number of employees in each department?
    public static Map<String, Long> getEmployeesCountByDepartment() {
        List<Employee> employees = initializeCollections();

        return employees.stream()
                .collect(groupingBy(Employee::getDepartment,
                        counting()));
    }

    //What is the average salary of each department?
    public static Map<String, Double> avgSalaryOfDepartment() {
        List<Employee> employees = initializeCollections();
        return employees.stream().collect(groupingBy(Employee::getDepartment,
                averagingDouble(Employee::getSalary)));
    }


    // Get the details of youngest male employee in the product development department?
    public static Employee getYoungestMaleEmployeeInPD() {
        List<Employee> employees = initializeCollections();
        return employees.stream()
                .filter(e -> e.getDepartment().equals("Product Development"))
                .min(comparingInt(Employee::getAge))
                .get();
    }

    //How many male and female employees are there in the sales and marketing team?
    public static Map<String, Long> getMaleAndFemaleEmployeesCountInSales() {
        List<Employee> employees = initializeCollections();
        return employees.stream()
                .filter(e -> e.getDepartment().equals("Product Development"))
                .collect(groupingBy(Employee::getGender, counting()));
    }

    //What is the average salary of male and female employees?
    public static Map<String, Double> averageSalaryByGender() {
        List<Employee> employees = initializeCollections();

        return employees.stream().collect(groupingBy(Employee::getGender,
                averagingDouble(Employee::getSalary)));

    }

    // List down the names of all employees in each department?
    public static Map<String, List<String>> getNamesByDepartment() {
        List<Employee> employees = initializeCollections();

        return employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                mapping(Employee::getName, toList())));
    }

    public static Map<Character, Long> charFrequencyMap(String word){
        return  word.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

    public static Map<Character, Long> charFrequencyMapSortedAlphabetically(String word){
        Function<Character, Character> func = c -> c;
        return  word.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(func, TreeMap::new ,Collectors.counting()));
    }

    public static Map<String, List<String>> groupAnagrams(List<String> words){

        Function<String, String> anagram = word -> word.chars()
                .mapToObj( c -> (char) c)
                .sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return words.stream().collect(Collectors.groupingBy(anagram));
    }


    public static void main(String[] args) {

        System.out.println(groupEmployeesByGender());
        System.out.println(getDepartmentList());
        System.out.println(avgAgeByGender());
        System.out.println(getHighestPaidSalary());
        System.out.println(employeesJoinedAfter2015());
        System.out.println(getEmployeesCountByDepartment());
        System.out.println(avgSalaryOfDepartment());
        System.out.println(getYoungestMaleEmployeeInPD());
        System.out.println(getMaleAndFemaleEmployeesCountInSales());
        System.out.println(averageSalaryByGender());
        System.out.println(getNamesByDepartment());

        System.out.println(charFrequencyMap("mississippi"));

        System.out.println(charFrequencyMapSortedAlphabetically("mississippi"));

        System.out.println(groupAnagrams(Arrays.asList("tea", "ate", "abc", "cba", "aa")));



    }
}
