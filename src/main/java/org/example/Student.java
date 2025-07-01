package org.example;

public class Student {
    private final String name;
    private final int score;
    private final String letterGrade;

    public Student(String n, int s, String l){
        this.name = n;
        this.score = s;
        this.letterGrade = l;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}