package com.boileradvsr.backend.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection="degrees")
public class Degree {

    public enum DEGREETYPE {
        MAJOR,
        MINOR,
        CONCENTRATION,
        CERTIFICATE
    }

    DEGREETYPE degreeType;
    @Id
    String degreeTitle;
    String college;
    String department;
    ArrayList<Requirement> requirements;
    ArrayList<Degree> specializationsAvailable;
    ArrayList<Degree> specializationsSelected;
    
    public Degree(DEGREETYPE degreeType, String degreeTitle, String college, String department) {
        this.degreeType = degreeType;
        this.degreeTitle = degreeTitle;
        this.college = college;
        this.department = department;
        if (degreeType == DEGREETYPE.MAJOR) {
            specializationsSelected = new ArrayList<Degree>();
            specializationsAvailable = new ArrayList<Degree>();
        }
        requirements = new ArrayList<Requirement>();

    }

    public void addRequirement(Requirement requirement) {
        requirements.add(requirement);
    }
    public DEGREETYPE getDegreeType() {
        return degreeType;
    }

    public String getDegreeTitle() {
        return degreeTitle;
    }

    public String getCollege() {
        return college;
    }

    public String getDepartment() {
        return department;
    }

    public ArrayList<Requirement> getRequirements() {
        return requirements;
    }

    public ArrayList<Degree> getSpecializationsAvailable() {
        return specializationsAvailable;
    }

    public ArrayList<Degree> getSpecializationsSelected() {
        return specializationsSelected;
    }

    public void addSpecialization(Degree specialization) {
        specializationsSelected.add(specialization);
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setDegreeTitle(String degreeTitle) {
        this.degreeTitle = degreeTitle;
    }

    public void setDegreeType(DEGREETYPE degreeType) {
        this.degreeType = degreeType;
    }

    public ArrayList<Degree> getAvailableSpecializations() {
        return(specializationsAvailable);
    }

    @Override
    public String toString() {
        return "Degree{" +
                "degreeType=" + degreeType +
                ", degreeTitle='" + degreeTitle + '\'' +
                ", college='" + college + '\'' +
                ", department='" + department + '\'' +
                ", requirements=" + requirements +
                ", specializationsAvailable=" + specializationsAvailable +
                ", specializationsSelected=" + specializationsSelected +
                '}';
    }

    public void setRequirements(ArrayList<Requirement> requirements) {
        this.requirements = requirements;
    }

    public void setSpecializationsAvailable(ArrayList<Degree> specializationsAvailable) {
        this.specializationsAvailable = specializationsAvailable;
    }

    public void setSpecializationsSelected(ArrayList<Degree> specializationsSelected) {
        this.specializationsSelected = specializationsSelected;
    }
}