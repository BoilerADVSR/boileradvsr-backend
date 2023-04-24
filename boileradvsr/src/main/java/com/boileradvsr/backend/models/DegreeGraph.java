package com.boileradvsr.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document(collection="degreeGraphs")
public class DegreeGraph {
    @Id
    public String name;
    private int vertices;
    private int edges;
    private ArrayList<ArrayList<Integer>> adj;
    private ArrayList<String> classes;
    private ArrayList<ArrayList<Integer>> revAdj;



    public DegreeGraph(String name, int vertices, ArrayList<String> classes) {
        this.name = name;
        this.vertices = vertices;
        this.classes = classes;
        adj = new ArrayList<ArrayList<Integer>>(vertices);
        revAdj = new ArrayList<>(vertices);

        for (int i = 0; i < vertices; ++i)
            adj.add(new ArrayList<Integer>());

        for (int i = 0; i < vertices; ++i)
            revAdj.add(new ArrayList<Integer>());
    }

    public DegreeGraph() {}

    public void addEdge(int v1, int v2) {
        adj.get(v1).add(v2);
        revAdj.get(v2).add(v1);

    }

    public ArrayList<String> getNextClasses(int source) {
        ArrayList<String> nextClasses = new ArrayList<>();
        for (int v : adj.get(source)) {
            nextClasses.add(classes.get(v));
        }
        return nextClasses;
    }

    public ArrayList<String> getNextClasses(String source) {
        int index = getIndexByName(source);
        if (index == -1) return null;
        return(getNextClasses(index));
    }

    public int getIndexByName(String className) {
        int index = -1;
        //System.out.println(className);
        for (int c = 0; c < classes.size(); c++) {
            if (classes.get(c).equals(className)) {
                index = c;
            }
        }
        return index;
    }

    public ArrayList<Integer> getAllClassesByName(ArrayList<String> classNames) {
        ArrayList<Integer> classes = new ArrayList<>();
        for (String name : classNames) {
            classes.add(getIndexByName(name));
        }
        return classes;
    }
    public ArrayList<String> getNextEligibleClassesController(ArrayList<Course> completed, ArrayList<Degree> concentrations) {
        ArrayList<String> completedNames = new ArrayList<>();
        for (Course course : completed) {
            completedNames.add(course.getCourseID());
        }
        ArrayList<String> eligible  = getNextEligibleClasses(completedNames);
        if (eligible.size() < 3 || concentrations.isEmpty()) {
            return eligible;
        } else {
            return trackAnalyzer(eligible, concentrations);
        }
    }

    public ArrayList<String> trackAnalyzer(ArrayList<String> eligible, ArrayList<Degree> concentrations) {
        //get all eligible classes for concentrations
        ArrayList<String> concentrationClasses = new ArrayList<>();
        for (Degree concentration : concentrations) {
            for (Requirement requirement: concentration.getRequirements()) {
                concentrationClasses.addAll(requirement.getCourseIDs());
            }
        }
        //Convert to list and sort by frequency (number of requirements fulfilled by a certain class)
        List<String> list = concentrationClasses;
        concentrationClasses.sort(Comparator.comparing(i -> Collections.frequency(list, i)).reversed());
        List<String> concentrationNoDup = concentrationClasses.stream().distinct().toList();
        //remove duplicates
        concentrationClasses = new ArrayList<>(concentrationNoDup);

        //remove classes that do not have a fulfilled prerequisite
        for (String course : concentrationClasses) {
            if (!eligible.contains(course)) concentrationClasses.remove(course);
        }

        return concentrationClasses;
    }


    public ArrayList<String> getNextEligibleClasses(ArrayList<String> completed) {
        //if no classes have been completed return starting classes
        if (completed.isEmpty()) return getStartingClasses();
        ArrayList<Integer> adjacentClasses = new ArrayList<>();
        ArrayList<Integer> completedClasses = getAllClassesByName(completed);
        //get all adjacent classes by running 1-step BFS, and remove duplicates.
        for (int i= 0; i < completed.size(); i++) {
            int index = getIndexByName(completed.get(i));
            if (index == -1) continue;
            adjacentClasses.addAll(adj.get(index));
        }
        List<Integer> adjacentClassesNoDup = adjacentClasses.stream().distinct().toList();
        adjacentClasses = new ArrayList<>(adjacentClassesNoDup);
        adjacentClasses.removeAll(completedClasses);
        ArrayList<Integer> preReqs;
        ArrayList<String> eligible = new ArrayList<>();
        //remove any class where all prerequisite requirements have not been completed
        for (int c = 0; c < adjacentClasses.size(); c++) {
            preReqs = revAdj.get(adjacentClasses.get(c));
            if (completedClasses.containsAll(preReqs)) eligible.add(classes.get(adjacentClasses.get(c)));
        }
        return eligible;
    }

    public ArrayList<String> getStartingClasses() {
        //get all nodes with an in-degree of 0 (No pre-requisites)
        ArrayList<String> starting = new ArrayList<>();
        for (int i = 0; i < revAdj.size(); i++) {
            if (revAdj.get(i).isEmpty()) starting.add(classes.get(i));
        }
        return starting;
    }

    public ArrayList<ArrayList<Integer>> getAdj() {
        return adj;
    }

    public ArrayList<ArrayList<Integer>> getRevAdj() {
        return revAdj;
    }

    public ArrayList<String> getClasses() {
        return classes;
    }

    public int getEdges() {
        return edges;
    }

    public int getVertices() {
        return vertices;
    }

    public String getName() {
        return name;
    }
}
