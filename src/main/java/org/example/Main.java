package org.example;

import java.lang.reflect.Array;
import java.util.*;

public class Main{
    public static String calculateLetter(int grade) {
        if (grade >= 90) { // A = 90-100
            return "A";
        } else if (grade >= 80) { // B = 80-89
            return "B";
        } else if (grade >= 70) { // C = 70-79
            return "C";
        } else if (grade >= 60) { // D = 60-69
            return "D";
        } else { // F = below 60
            return "F";
        }
    }

    public static double getAverageScore(ArrayList<Student> studentsList) {
        int totalScore = 0;

        for (Student student: studentsList) {
            totalScore += student.getScore();
        }

        return (double) totalScore / studentsList.toArray().length;
    }

    public static ArrayList<Student> getTopStudents(ArrayList<Student> studentsList) {
        int maxScore = 0;
        ArrayList<Student> topStudents = new ArrayList<>();

        for (Student student: studentsList) {
            int currScore = student.getScore();

            if (currScore > maxScore) {
                topStudents.clear();;
                topStudents.add(student);
                maxScore = currScore;
            } else if (currScore == maxScore) {
                topStudents.add(student);
            }
        }

        return topStudents;
    }

    public static void getClassSummary(ArrayList<Student> studentsList, Map<String, Integer> gradeCounts) {
        System.out.println("----- Class Summary -----");

        // Get average score
        double averageScore = getAverageScore(studentsList);
        System.out.printf("Average Score: %.2f\n", averageScore);

        // Print grade counts
        System.out.print("Grade Counts: ");
        gradeCounts.forEach((key, value) -> System.out.print(key + ":" + value + " "));
        System.out.print("\n");

        // Show top students
        ArrayList<Student> topStudents = getTopStudents(studentsList);
        System.out.print("Top Student(s): ");

        for (Student student: topStudents) {
            System.out.printf("%s (%d) ", student.getName(), student.getScore());
        }

    }

    public static void main(String[] args) {
        ArrayList<Student> studentsList = new ArrayList<>(); // Initialize ArrayList for students
        Scanner userInput = new Scanner(System.in); // Initialize scanner for user input

        // Initialize map for grade counts
        Map<String, Integer> gradeCounts = new HashMap<>();
        gradeCounts.put("A", 0);
        gradeCounts.put("B", 0);
        gradeCounts.put("C", 0);
        gradeCounts.put("D", 0);
        gradeCounts.put("F", 0);

        System.out.print("Enter number of students: ");
        int numOfStudents = userInput.nextInt();
        userInput.nextLine();
        System.out.print("\n");

        for (int i = 0; i < numOfStudents; i++) {
            System.out.print("Enter name of student " + (i+1) + ": ");
            String currName = userInput.nextLine();

            System.out.print("Enter score for " + currName + ": ");
            int currScore = userInput.nextInt();
            userInput.nextLine();

            String currGrade = calculateLetter(currScore);
            System.out.println(currName + " got grade: " + currGrade);

            System.out.print("\n");

            // Increment grade counts
            int count = gradeCounts.getOrDefault(currGrade, 0);
            gradeCounts.put(currGrade, count + 1);

            // Add Student object to studentsList array
            Student student = new Student(currName, currScore, currGrade);
            studentsList.add(student);
        }

        getClassSummary(studentsList, gradeCounts); // Print class summary
    }
}
