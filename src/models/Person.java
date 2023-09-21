package models;

import exceptions.AgeControlExcep;
import exceptions.SalaryExcep;
import models.enums.Position;

public class Person {
    private Long id;
    private String name;
    private int age;
    private Double salary;
    private Position position;
    //примитивные по умолчанию 0 (что-то есть), ссылочные - null (ничего нет)

    public Person(Long id, String name, int age, Double salary, Position position) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.position = position;
    }

    public Person(){}

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", position=" + position +
                '}';
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws AgeControlExcep {
        if(age >= 18 && age <= 60)
            this.age = age;
        else
            throw new AgeControlExcep("Возраст не соответствует");
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) throws SalaryExcep{
        if(salary < 0)
            throw new SalaryExcep("Неккоректный ввод");
        else
            this.salary = salary;
    }
}
