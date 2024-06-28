package com.manishjangra.eps;

import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Employee{
    private String name;
    private int id;

    public Employee(String name, int id){
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }

    public abstract double calculateSalary();

    @Override
    public String toString(){
        return "Name: " + name + " ID: " + id + " Salary: " + calculateSalary();
    }
}

class FullTimeEmployee extends Employee{



    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlysalary){
        super(name, id);
        this.monthlySalary = monthlysalary;
    }


    @Override
    public double calculateSalary(){
        return monthlySalary;
    }

}

class PartTimeEmployee extends Employee{

    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate){
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }


    @Override
    public double calculateSalary(){
        return hoursWorked*hourlyRate;
    }

}

class PayrollSystem{

    private ArrayList<Employee> employeeList;

    public PayrollSystem(){
        employeeList = new ArrayList<>();
    }


    public void addEmployee(Employee employee){
        employeeList.add(employee);
    }

    public void removeEmployee(int id){
        Employee employeeToRemove = null;
        for(Employee employee : employeeList){
            if(employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }

        }
        if (employeeToRemove != null) {
            employeeList.remove(employeeToRemove);
        }
        else {
            System.out.println("Employee not found");
        }

    }

    public void displayEmployees(){
        for(Employee employee : employeeList){
            if (employee != null) {
                System.out.println(employee);
            }
            else {
                System.out.println("Employee not found");
            }

        }
    }
    
}


public class Main{
    public static void main(String[] args) {

        PayrollSystem payrollSystem = new PayrollSystem();


        System.out.println("WELCOME TO EMPLOYEE PAYROLL SYSTEM\n");

        System.out.println("Select below:" +
                "\n1 - Employee Details" +
                "\n2 - Add Employee" +
                "\n3 - Remove Employee\n");

        Scanner input = new Scanner(System.in);

        switch (input.nextInt()){
            case 1: {
                System.out.println("Employee Details: ");
                payrollSystem.displayEmployees();
                break;
            }
            case 2: {
                System.out.println("Add Employee Details: ");
                System.out.println("Employee is Full Time or Part Time?\n1 - Full Time\n2 - Part Time");
                if(input.nextInt() == 1){
                    System.out.println("Enter No. of Employees to add: ");
                    int noOfEmployees = input.nextInt();
                    for(int i=1 ; i<=noOfEmployees ; i++){
                        System.out.println("Enter Employee"+i+" Details: ");
                        System.out.print("Enter Name: ");
                        String name = input.nextLine();
                        System.out.print("Enter ID: ");
                        int id = input.nextInt();
                        System.out.println("Enter Monthly Salary: ");
                        double monthlySalary = input.nextDouble();

                        FullTimeEmployee emp = new FullTimeEmployee(name, id, monthlySalary);

                        payrollSystem.addEmployee(emp);

                    }

                } else if(input.nextInt() == 2){
                    System.out.println("Enter No. of Employees to add: ");
                    for(int i=1 ; i<=input.nextInt() ; i++){
                        System.out.println("Enter Employee"+i+"Details: ");
                        System.out.print("Enter Name: ");
                        String name = input.nextLine();
                        System.out.print("Enter ID: ");
                        int id = input.nextInt();
                        System.out.print("Enter Hour Working: ");
                        int hoursWorked = input.nextInt();
                        System.out.print("Enter Hour Rate: ");
                        double hourlyRate = input.nextDouble();

                        PartTimeEmployee pemp = new PartTimeEmployee(name, id, hoursWorked, hourlyRate);

                        payrollSystem.addEmployee(pemp);

                    }

                }

            }
        }


    }
}