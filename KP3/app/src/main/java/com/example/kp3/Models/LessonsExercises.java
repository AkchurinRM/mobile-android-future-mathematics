package com.example.kp3.Models;

public class LessonsExercises {
    public String[] array_of_exercises;
    public int[] points;
    public int[] mistakes;

    public void setPoints(int[] points) {
        this.points = points;
    }
    public int[] getPoints() {
        return points;
    }


    public int[] getMistakes() {
        return mistakes;
    }
    public void setMistakes(int[] mistakes) {
        this.mistakes = mistakes;
    }

    public String[] getArray_of_exercises() {
        return array_of_exercises;
    }
    public void setArray_of_exercises(String[] array_of_exercises) {
        this.array_of_exercises = array_of_exercises;
    }
}
