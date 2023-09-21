package service.impl;

import exceptions.AgeControlExcep;
import exceptions.SalaryExcep;
import models.Person;
import models.enums.Position;
import service.DbHelper;

import java.util.*;
import java.util.stream.Collectors;

public class DbHelperImpl implements DbHelper {
    List<Person> people = new ArrayList<>();
    Long idGen = 1L;

    @Override
    public void addLocalPeople() {
        people.add(new Person(1L, "test1", 23, 111111.0, Position.CEO));
        people.add(new Person(2L, "test2", 24, 222222.0, Position.MANAGER));
        people.add(new Person(3L, "test3", 21, 555551.0, Position.DEVELOPER));
        people.add(new Person(4L, "test4", 29, 666660.0, Position.SELLER));
    }

    @Override
    public Person savedPerson(String name, int age, Double salary, Position position) throws AgeControlExcep, SalaryExcep {
        Person person = new Person();//create new person
        person.setId(idGen++);
        person.setName(name);
        person.setAge(age);
        person.setSalary(salary);
        person.setPosition(position);
        people.add(person);
        return person;
    }

    @Override
    public List<Person> getSortedPerson(int ans) {
        switch(ans){
            case 1:
                Collections.sort(people, Comparator.comparing(Person::getName));
                break;
            case 2:
                Collections.sort(people, Comparator.comparing(Person::getId));
                Collections.reverse(people);
                break;
            case 3:
                Collections.sort(people, Comparator.comparing(Person::getId));
                break;
        }
        for (Person person:
             people) {
            System.out.println(person);
        }
        return people;
    }

    public void getAverageAge(){
        double averageAge = people.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0.0);
        System.out.println("Средний возраст сотрудников: " + averageAge);
    }

    @Override
    public Map<String, Integer> getMaxMinAge() {
        Optional<Person> maxAgeEmployee = people.stream()
                .max(Comparator.comparingInt(Person::getAge));
        Optional<Person> minAgeEmployee = people.stream()
                .min(Comparator.comparingInt(Person::getAge));

        Map<String, Integer> result = new HashMap<>();

        maxAgeEmployee.ifPresent(employee -> {
            result.put("Самый старший сотрудник: " + employee.getName(), employee.getAge());
        });

        minAgeEmployee.ifPresent(employee -> {
            result.put("Самый младший сотрудник: " + employee.getName(), employee.getAge());
        });
        result.forEach((key, value) -> System.out.println(key + ": " + value + " лет"));

        return result;
    }

    public void getAverageSalary(){
        double averageSalary = people.stream()
                .mapToDouble(Person::getSalary)
                .average()
                .orElse(0.0);
        System.out.println("Средняя зарплата: " + averageSalary);
    }

    public void getMaxSalary(){
        Map<String, Double> maxSalary = new HashMap<>();
        Optional<Person> maxSalaryEmployee = people.stream()
                .max(Comparator.comparingDouble(Person::getSalary));

        maxSalaryEmployee.ifPresent(employee -> maxSalary.put("Сотрудник с максимальной зарплатой: " + employee.getName(),
                employee.getSalary()));

        maxSalary.forEach((key, value) -> System.out.println(key + ": " + value + " сом"));
    }
    public void getMinSalary(){
        Map<String, Double> minSalary = new HashMap<>();
        Optional<Person> minSalaryEmployee = people.stream()
                .min(Comparator.comparingDouble(Person::getSalary));
        minSalaryEmployee.ifPresent(employee -> minSalary.put("Сотрудник с минимальной зарплатой: " + employee.getName(),
                employee.getSalary()));
        minSalary.forEach((key, value) -> System.out.println(key + ": " + value + " лет"));
    }

    @Override
    public void sortByPosition() {
        Map<Position, List<Person>> employeesByPosition =
                people.stream().collect(Collectors.groupingBy(Person::getPosition));
        System.out.println("Сотрудники по должности:");
        employeesByPosition.forEach((position, employeeList) -> {
            System.out.println(position + ":");
            employeeList.forEach(employee -> System.out.println("  " + employee.getName()));
        });
    }

    @Override
    public void sortByAge() {
        Map<Integer, List<Person>> employeesByAge = people.stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println("Сотрудники по имени:");
        employeesByAge.forEach((age, employeeList) -> {
            System.out.println(age + " лет:");
            employeeList.forEach(employee -> System.out.println("  " + employee.getName()));
        });
    }

    public void getTotalSalaryCost(){
        double totalSalaryCost = people.stream()
                .mapToDouble(Person::getSalary)
                .sum();
        System.out.println("Общая стоимость затрат на зарплату за год: " + totalSalaryCost);
    }

    public void getSalaryUnder(){
        System.out.println("Сотрудники с зарплатой менее 10000:");
        people.stream()
                .filter(employee -> employee.getSalary() < 10000)
                .forEach(employee -> System.out.println(employee.getName()));
    }

    public void getAgeByPosition(Position chosenPosition){
        System.out.println("Список возрастов сотрудников в должности '" + chosenPosition + "':");
        people.stream()
                .filter(employee -> employee.getPosition().equals(chosenPosition))
                .map(Person::getAge)
                .forEach(System.out::println);
    }

}
