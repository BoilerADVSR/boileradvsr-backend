package com.boileradvsr.backend.models;
import java.util.ArrayList;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planofstudy")
public class PlanOfStudy {
    double gpa;
    ArrayList<Degree> degrees;
    ArrayList<Semester> semesters;

    public PlanOfStudy(ArrayList<Degree> degrees, ArrayList<Semester> semesters) {

        this.degrees = degrees;
        this.semesters = semesters;
        gpa = 0.0;
    }

    public PlanOfStudy() {
        degrees = new ArrayList<>();
        semesters = new ArrayList<>();
        gpa = 0.0;
    }

    public void calculateGPA() {
        int creditHours = 0;
        int qualityPoints = 0;
        for (Semester semester : semesters) {
            qualityPoints += semester.getQualityPoints();
            creditHours += semester.getCreditHours();
        }
        if (creditHours == 0) {
            gpa = 0;
        } else {
            gpa = (double)qualityPoints / creditHours;
        }
    }

    public Semester getSemesterByDate(Semester.Season season, int year) {
        for (Semester semester : semesters) {
            if (semester.year == year && semester.season == season) {
                return semester;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "PlanOfStudy{" +
                "degrees=" + degrees +
                ", semesters=" + semesters +
                '}';
    }

    public ArrayList<Course> getCoursesTaken() {
        ArrayList<Course> courses = new ArrayList<>();
        for (Semester semester : semesters) {
            courses.addAll(semester.getCourses());
        }
        return courses;
    }
    //TODO refactor names so it is not included in the API REQUEST
    public ArrayList<String> getCourseIDsTaken() {
        ArrayList<String> courses = new ArrayList<>();
        for (Semester semester : semesters) {
            for (Course course : semester.getCourses()) {
                courses.add(course.getCourseID());
            }
        }
        return courses;
    }

    public ArrayList<Requirement> getAllRequirements() {
        ArrayList<Requirement> allRequirements = new ArrayList<>();
        for (Degree degree : degrees) {
            allRequirements.addAll(degree.getRequirements());
        }
        return allRequirements;
    }

    public ArrayList<Requirement> requirementsLeft() {
        ArrayList<Requirement> requirements = getAllRequirements();
        ArrayList<String> coursesTaken = getCourseIDsTaken();
        ArrayList<Requirement> requirementsLeft = new ArrayList<>();

        ArrayList<Course> requirementCourses;
        boolean complete = false;

        for (Requirement requirement : requirements) {
            requirementCourses = requirement.getCourses();
            for (Course course : requirementCourses) {
                if (coursesTaken.contains(course.getCourseID())) {
                    complete = true;
                    break;
                }
            }
            if (!complete) requirementsLeft.add(requirement);
            complete = false;
        }
        return requirementsLeft;
    }

    public void addDegree(Degree degree) {
        degrees.add(degree);
    }

    public void removeDegree(String degreetitle) {
        for (int i = 0; i < degrees.size(); i++) {
            if (degrees.get(i).getDegreeTitle().equals(degreetitle)) {
                degrees.remove(i);
                return;
            }
        }
    }


    public void addSemester(Semester semester) {
        semesters.add(semester);
        calculateGPA();
    }

    public ArrayList<Degree> getDegrees() {
        return degrees;
    }

    public ArrayList<Semester> getSemesters() {
        return semesters;
    }

    public void setDegrees(ArrayList<Degree> degrees) {
        this.degrees = degrees;
    }

    public void setSemesters(ArrayList<Semester> semesters) {
        this.semesters = semesters;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}