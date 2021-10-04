package org.example;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CityUtils {
    public static List<City> parse() {
        List<City> cities = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File("resources/city_ru.csv")); ) {
            while (scanner.hasNextLine()) {
                cities.add(parse(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return cities;
    }

    private static City parse(String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(";");
        scanner.skip("\\d*");
        String name = scanner.next();
        String region = scanner.next();
        String district = scanner.next();
        int population = scanner.nextInt();
        String foundation = null;
        if (scanner.hasNext()) {
            foundation = scanner.next();
        }
        scanner.close();
        return new City(name, region, district, population, foundation);
    }

    public static void print(List<City> cities) {
        cities.forEach(System.out::println);
    }


}
