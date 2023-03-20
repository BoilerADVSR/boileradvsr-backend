package com.boileradvsr.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
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
        revAdj = new ArrayList<ArrayList<Integer>>(vertices);

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
    public ArrayList<String> getNextEligibleClassesController(ArrayList<Course> completed) {
        ArrayList<String> completedNames = new ArrayList<>();
        for (Course course : completed) {
            completedNames.add(course.getCourseID());
        }
        return (getNextEligibleClasses(completedNames));
    }

    public ArrayList<String> getNextEligibleClasses(ArrayList<String> completed) {
        if (completed.isEmpty()) return getStartingClasses();
        ArrayList<Integer> adjacentClasses = new ArrayList<>();
        ArrayList<Integer> completedClasses = getAllClassesByName(completed);
        for (int i= 0; i < completed.size(); i++) adjacentClasses.addAll(adj.get(getIndexByName(completed.get(i))));
        List<Integer> adjacentClassesNoDup = adjacentClasses.stream().distinct().toList();
        adjacentClasses = new ArrayList<>(adjacentClassesNoDup);
        adjacentClasses.removeAll(completedClasses);
        ArrayList<Integer> preReqs;
        ArrayList<String> eligible = new ArrayList<>();
        for (int c = 0; c < adjacentClasses.size(); c++) {
            preReqs = revAdj.get(adjacentClasses.get(c));
            if (completedClasses.containsAll(preReqs)) eligible.add(classes.get(adjacentClasses.get(c)));
        }
        return eligible;
    }

    public ArrayList<String> getStartingClasses() {
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
