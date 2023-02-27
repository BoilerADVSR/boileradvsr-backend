package com.boileradvsr.backend.models;
import java.util.ArrayList;

import org.springframework.beans.factory.xml.DelegatingEntityResolver;

public class PlanOfStudy {
    ArrayList<Degree> degrees;
    ArrayList<Semester> semesters;

    public PlanOfStudy(ArrayList<Degree> degrees, ArrayList<Semester> semesters) {

        this.degrees = degrees;
        this.semesters = semesters;
    }

    public void addDegree(Degree degree) {
        degrees.add(degree);
    }

}