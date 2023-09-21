package service;

import exceptions.AgeControlExcep;
import exceptions.SalaryExcep;
import models.Person;
import models.enums.Position;

import java.util.List;
import java.util.Map;

public interface DbHelper {
    //данные сотрудников
    void addLocalPeople();
    Person savedPerson(String name, int age, Double salary, Position position) throws AgeControlExcep, SalaryExcep;
    List<Person> getSortedPerson(int answer);
    void getAverageAge();
    Map<String, Integer> getMaxMinAge();
    void getAverageSalary();
    void getMaxSalary();
    void getMinSalary();
    void sortByPosition();
    void sortByAge();
    void getTotalSalaryCost();
    void getSalaryUnder();
    void getAgeByPosition(Position pos);
}
