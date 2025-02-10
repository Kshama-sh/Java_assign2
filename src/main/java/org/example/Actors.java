package org.example;

import java.io.InputStream;
import java.util.*;

class Actors {
    int actorid;
    String name;
    String dateOfBirth;
    String nationality;

    public Actors(int actorid, String name, String dateOfBirth, String nationality) {
        this.actorid = actorid;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Actor ID: " + actorid + ", Name: " + name + ", DOB: " + dateOfBirth + ", Nationality: " + nationality;
    }

    public static HashMap<Integer, Actors> readActorsCsv(String fileName) {
        HashMap<Integer, Actors> actorMap = new HashMap<>();
        InputStream inputStream = Actors.class.getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null) {
            System.out.println("Error: File not found!");
            return actorMap;
        }

        try (Scanner sc = new Scanner(inputStream)) {
            if (sc.hasNextLine()) sc.nextLine(); // Skip header

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (values.length < 4) continue;

                int actorid = Integer.parseInt(values[0].trim());
                String name = values[1].trim();
                String dateOfBirth = values[2].trim();
                String nationality = values[3].trim();

                actorMap.put(actorid, new Actors(actorid, name, dateOfBirth, nationality));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return actorMap;
    }
}
