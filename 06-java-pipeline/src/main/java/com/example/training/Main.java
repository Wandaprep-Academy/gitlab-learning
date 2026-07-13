package com.example.training;

/**
 * Main entry point for the simple Java training application.
 * Used by the GitLab CI/CD pipeline as a real (if minimal) Java codebase.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello from the GitLab CI/CD training app!");
        Calculator calc = new Calculator();
        System.out.println("2 + 3 = " + calc.add(2, 3));
        System.out.println("10 / 2 = " + calc.divide(10, 2));
    }
}
