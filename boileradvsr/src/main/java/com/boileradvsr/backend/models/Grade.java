package com.boileradvsr.backend.models;
import java.util.HashMap;
public class Grade {
    String letterGrade;
    double gpaValue;
    boolean passFail;
    HashMap<String, Double> gradesAndValues;

    public Grade(String letterGrade, double gpaValue, boolean passFail, HashMap<String, Double> gradesAndValues) {
        this.letterGrade = letterGrade;
        this.gpaValue = gpaValue;
        this.passFail = passFail;
        this.gradesAndValues = gradesAndValues;
    }

    public Grade(double gpaValue) {
        this.gpaValue = gpaValue;
    }

    public HashMap<String, Double> getGradesAndValues() {
        return(gradesAndValues);
    }

    public double getGpaValue() {
        return gpaValue;
    }

    public String getLetterGrade() {
        return letterGrade;
    }
}