package com.boileradvsr.backend;

import com.boileradvsr.backend.models.DegreeGraph;

import java.util.ArrayList;

public class DegreeMain {
    public static void main(String[] args) {
        ArrayList<String> classes = new ArrayList<>();
        classes.add("CS180");
        classes.add("CS182");
        classes.add("CS240");
        classes.add("CS250");
        classes.add("CS251");
        classes.add("CS252");
        classes.add("CS307");
        classes.add("CS314");
        classes.add("CS373");

        ArrayList<String> classesCompleted = new ArrayList<>();
        classesCompleted.add("CS180");
//        classesCompleted.add("CS182");
//        classesCompleted.add("CS240");


        DegreeGraph graph = new DegreeGraph("CS",9, classes);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,3);
        graph.addEdge(1,4);
        graph.addEdge(2,3);
        graph.addEdge(2,4);
        graph.addEdge(3,5);
        graph.addEdge(4,5);
        graph.addEdge(4,6);
        graph.addEdge(4,8);


        ArrayList<String> topsort = graph.getNextEligibleClasses(classesCompleted);
        for (String s : topsort) {
            System.out.println(s);
        }







    }
}
