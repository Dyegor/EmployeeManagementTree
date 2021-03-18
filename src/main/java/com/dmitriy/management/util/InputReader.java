package com.dmitriy.management.util;

import com.dmitriy.management.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InputReader {
    /**
     * The method reads input from console and returns it as a list
     *
     * @return List of employees from the input
     * @throws IOException I/O exception of some sort has occurred
     */
    public static List<Employee> readInput(InputStream input) throws IOException {
        List<Employee> employees = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length > 2) {
                    employees.add(new Employee(Integer.parseInt(values[0]), values[1], Integer.parseInt(values[2])));
                }
            }
        }

        return employees;
    }
}
