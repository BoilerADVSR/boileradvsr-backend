package com.boileradvsr.backend.scripts;

import com.boileradvsr.backend.models.*;
import com.boileradvsr.backend.models.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DegreeScript {
    public static void readFile(String filename) throws IOException {
        File file = new File(filename);
        FileReader fr = new FileReader(file);
        BufferedReader bfr = new BufferedReader(fr);
        ArrayList<String> lines = new ArrayList<>();
        String line;

        while ((line = bfr.readLine()) != null) {
            lines.add(line);
        }
        String degreeName = lines.get(0).substring(lines.get(0).indexOf(':') + 1);
        Degree.DEGREETYPE degreetype = Degree.DEGREETYPE.valueOf(lines.get(1).substring(lines.get(1).indexOf(':') + 1));
        String college = lines.get(2).substring(lines.get(2).indexOf(':') + 1);
        String department = lines.get(3).substring(lines.get(3).indexOf(':') + 1);
        Degree degree = new Degree(degreetype,degreeName, college, department);

        Requirement.Type reqType = Requirement.Type.CORE;

        for (int i = 4; i < lines.size(); i++) {
            line = lines.get(i);
            if (line.equals("CORE;")) {
                reqType = Requirement.Type.CORE;
                return;
            } else if (line.equals("ELECTIVE;")) {
                reqType = Requirement.Type.ELECTIVE;
                return;
            }
            degree.addRequirement(parseRequirement(line, reqType));
        }


    }

    public static Requirement parseRequirement(String line, Requirement.Type type) {
        int semi = line.indexOf(':');
        String classList = line.substring(semi + 1);
        String requirementName = line.substring(0, semi);
        ArrayList<String> classes = new ArrayList<>(Arrays.asList(classList.split(",")));
        //issue with the classes. Need the degree controller for this to work.
        Requirement requirement = new Requirement(requirementName, type);
        return requirement;
    }
}
