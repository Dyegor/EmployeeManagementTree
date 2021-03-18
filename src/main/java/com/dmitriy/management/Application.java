package com.dmitriy.management;

import com.dmitriy.management.util.InputReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * The main class for the employment management tree exercise
 */
public class Application {
    private final static String INDENTATION_CHARACTER = "->";
    private final static String INPUT_MESSAGE = "Please, enter your data in the following format:" +
            System.lineSeparator() +
            "EmployeeID,EmployeeName,EmployeeManagerId" +
            System.lineSeparator() +
            "Start from next line:";

    /**
     * The main method
     */
    public static void main(String[] args) throws IOException {
        System.out.println(INPUT_MESSAGE);

        InputStream input = System.in;
        List<Employee> employees = InputReader.readInput(input);

        Application application = new Application();
        application.displayManagementTree(employees);
    }

    /**
     * Displays employees in a logical tree.
     */
    public void displayManagementTree(List<Employee> employees) {
        Employee rootManager = findRootManger(employees);
        Map<Integer, List<Employee>> subordinatesMap = setSubordinatesMap(employees);
        populateSubordinatesLists(subordinatesMap, employees);
        printManagementTree(subordinatesMap, rootManager, 0);
    }

    /**
     * The method finds rootManager of the Tree
     *
     * @param employees List of employees
     * @return rootManager
     */
    private static Employee findRootManger(List<Employee> employees) {
        Employee rootManager = new Employee();
        List<Employee> rootManagersList = new ArrayList<>();

        for (Employee employee : employees) {
            if (employee.getManagerId() == 0) {
                rootManager = employee;
                rootManagersList.add(rootManager);
            }
        }

        checkRootIsUnique(rootManagersList);

        return rootManager;
    }

    /**
     * The method checks if only one root manager exists
     */
    private static void checkRootIsUnique(List<Employee> rootManagersList) {
        if (rootManagersList.size() > 1) {
            throw new IllegalArgumentException("Tree must contain only one root manager.");
        }
    }

    /**
     * The method sets Map with Lists of Employees and their manager Ids
     *
     * @param employees List of employees
     * @return Map of Employees Lists
     */
    private static Map<Integer, List<Employee>> setSubordinatesMap(List<Employee> employees) {
        Map<Integer, List<Employee>> subordinatesMap = new HashMap<>();

        for (Employee employee : employees) {
            subordinatesMap.put(employee.getId(), new ArrayList<>());
        }

        return subordinatesMap;
    }

    /**
     * The method populates subordinate lists according to their manager IDs
     *
     * @param employees List of employees
     */
    private static void populateSubordinatesLists(Map<Integer, List<Employee>> subordinatesMap, List<Employee> employees) {
        for (Employee employee : employees) {
            if (subordinatesMap.containsKey(employee.getManagerId())) {
                subordinatesMap.get(employee.getManagerId()).add(employee);
            }
        }
    }

    /**
     * The method recursively prints out entire Management tree
     *
     * @param root  The employee for whom the management tree is being printed
     * @param level represents one level of management
     */
    private static void printManagementTree(Map<Integer, List<Employee>> subordinatesMap, Employee root, int level) {
        System.out.print(prefix(level));
        System.out.println(root.getName());

        List<Employee> subs = subordinatesMap.get(root.getId());

        for (Employee em : subs) {
            printManagementTree(subordinatesMap, em, level + 1);
        }
    }

    /**
     * The method adds a prefix indicating the level of management
     *
     * @param level represents one level of management
     * @return prefix which shows Hierarchy leve
     */
    private static String prefix(int level) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i <= level; i++) {
            s.append(INDENTATION_CHARACTER);
        }

        return s.toString();
    }
}