package org.example;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.CityUtils.parse;

public class Main {
    public static void main(String[] args) {
        List<City> cities = parse();
        sortByNameV1(cities);
        print(cities);

        sortByNameV2(cities);
        print(cities);

        sortByDistrictAndName(cities);
        print(cities);

        findBySimpleBruteForce(cities);
        findByInsertionSort(cities);
        findMaxPopulation(cities);

        findCountCityByRegionV1(cities);
        findCountCityByRegionV2(cities);
    }


    private static void sortByNameV1(List<City> cities) {
        cities.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
    }

    private static void sortByNameV2(List<City> cities) {
        cities.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
    }
    
    private static void sortByDistrictAndName(List<City> cities) {
        cities.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
    }

    public static void print(List<City> cities) {
        cities.forEach(System.out::println);
    }

    private static void findMaxPopulation(List<City> cities) {
        System.out.println(cities.stream().max(Comparator.comparing(City::getPopulation)));
    }
    private static void findByInsertionSort(List<City> cities) {
        City[] array = new City[cities.size()];
        cities.toArray(array);
        for (int i = 1; i < array.length; i++) {
            City other = array[i];
            int j = i - 1;
            while (j >= 0 && other.getPopulation() < array[j].getPopulation()) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = other;
        }
        System.out.println(MessageFormat.format("[0] = {1}", array.length - 1, array[array.length - 1]));
    }
    private static void findBySimpleBruteForce(List<City> cities) {
        City[] array = new City[cities.size()];
        cities.toArray(array);
        City other = array[0];
        int index = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[i].getPopulation() > other.getPopulation()) {
               other = array[i];
                index = i;
            }
        }
        System.out.println(MessageFormat.format("[0] = {1}", index, array[index]));
    }

    private static void findCountCityByRegionV1(List<City> cities) {
        Map<String, Integer> regions = new HashMap<>();
        for (City city : cities) {
            if (!regions.containsKey(city.getRegion())) {
                regions.put(city.getRegion(), 1);
            } else {
                regions.put(city.getRegion(), regions.get(city.getRegion()) + 1);
            }
        }
        for (String key : regions.keySet()) {
            System.out.println(MessageFormat.format(" [0] = {1}", key, regions.get(key)));
        }
    }

    private static void findCountCityByRegionV2(List<City> cities) {
        Map<String, Integer> regions = new HashMap<>();
        cities.forEach(city -> regions.merge(city.getRegion(), 1, Integer::sum));
        regions.forEach((k, v) -> System.out.println(MessageFormat.format(" [0] = {1}", k, v)));
    }
}