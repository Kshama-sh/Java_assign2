package org.example;

import java.io.InputStream;
import java.util.*;

class Directors {
    int directorid;
    String name;
    String nationality;

    public Directors(int directorid, String name, String nationality) {
        this.directorid = directorid;
        this.name = name;
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Director ID: " + directorid + ", Name: " + name + ", Nationality: " + nationality;
    }

    public static HashMap<Integer, Directors> readDirectorsCsv(String fileName) {
        HashMap<Integer, Directors> directorMap = new HashMap<>();
        InputStream inputStream = Directors.class.getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null) {
            System.out.println("Error: File not found!");
            return directorMap;
        }

        try (Scanner sc = new Scanner(inputStream)) {
            if (sc.hasNextLine()) sc.nextLine(); // Skip header

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (values.length < 3) continue;

                int directorid = Integer.parseInt(values[0].trim());
                String name = values[1].trim();
                String nationality = values[2].trim();

                directorMap.put(directorid, new Directors(directorid, name, nationality));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return directorMap;
    }
}
