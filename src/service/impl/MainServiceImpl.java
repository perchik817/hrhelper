package service.impl;

import exceptions.AgeControlExcep;
import exceptions.SalaryExcep;
import models.Person;
import models.enums.Position;
import service.DbHelper;
import service.MainService;
import java.util.Scanner;

public class MainServiceImpl implements MainService {

    Scanner scan = new Scanner(System.in);
    DbHelper dbHelper = new DbHelperImpl();
    @Override
    public void start() {
        dbHelper.addLocalPeople();
        int continueProg = 1;
        System.out.println("~~~~~Добро пожаловать~~~~~");
        while(continueProg != 0){
            System.out.print("1.Заведение нового сотрудника" +
                    "\n2.Просмотр списка сотрудников" +
                    "\n3.Анализ" +
                    "\nВыберите действие (1 - 3): ");
            int answer = scan.nextInt();

            switch (answer){
                case 1:
                    addNewPerson();
                    break;
                case 2:
                    viewPersonList();
                    break;
                case 3:
                    analyze();
                    break;
                default:
                    throw new RuntimeException("Неверный ввод");

            }

            System.out.println("Хотите продолжить работу? (1/0)");
            continueProg = scan.nextInt();
        }

    }

    private void analyze() {
        int localAns = 1;
        while (localAns == 1){
            System.out.println("Выберите вид анализа");
            System.out.println("1. Вывести средний возраст сотрудников" +
                    "\n2. Вывести мах и мин возраст" +
                    "\n3. Вывести среднюю зп" +
                    "\n4. Вывести сотрудника с самой большой зп" +
                    "\n5. Вывести сотрудника с самой низкой зп" +
                    "\n6. Сгруппировать всех по должности" +
                    "\n7. Сгруппировать всех по возрасту" +
                    "\n8. Вывести общую стоимость затрат на зп за год" +
                    "\n9. Вывести только тех у кого зп меньше 10000" +
                    "\n10. Вывести список возрастов сотрудников в определенной должности на ваш выбор");
            int antwort = scan.nextInt();
            switch (antwort){
                case 1:
                    dbHelper.getAverageAge();
                    break;
                case 2:
                    dbHelper.getMaxMinAge();
                    break;
                case 3:
                    dbHelper.getAverageSalary();
                    break;
                case 4:
                    dbHelper.getMaxSalary();
                    break;
                case 5:
                    dbHelper.getMinSalary();
                    break;
                case 6:
                    dbHelper.sortByPosition();
                    break;
                case 7:
                    dbHelper.sortByAge();
                    break;
                case 8:
                    dbHelper.getTotalSalaryCost();
                    break;
                case 9:
                    dbHelper.getSalaryUnder();
                    break;
                case 10:
                    System.out.println("Выберите должность: ");
                    for (Position position : Position.values()) {
                        System.out.println((position.getVal() + 1) + ". " + position);
                    }
                    int positionVal = scan.nextInt();

                    Position chosenPosition = Position.values()[positionVal - 1];
                    dbHelper.getAgeByPosition(chosenPosition);
                    break;
                default:
                    throw new RuntimeException("Неверный ввод");

            }
            System.out.println("Хотите продолжить анализ списка сотрудников? (1/0)");

            if(scan.nextInt() == 0)
                localAns = 0;
        }
    }

    private void viewPersonList() {
        int localAns = 1;
        while (localAns == 1) {
            System.out.println("Выберите метод сортировки");
            System.out.println("1. По имени");
            System.out.println("2. От новых сотрудников к старым");
            System.out.println("3. От старых сотрудников к новым");
            int ans = scan.nextInt();
            dbHelper.getSortedPerson(ans);
            System.out.println("Хотите продолжить просмотр списка сотрудников? (1/0)");

            if(scan.nextInt() == 0)
                localAns = 0;
        }
    }

    private void addNewPerson(){
        int localAns = 1;
        while (localAns == 1) {
            System.out.println("Введите имя сотрудника: ");
            String name = scan.next().trim();
            System.out.println("Введите возраст сотрудника: ");
            int age = scan.nextInt();
            System.out.println("Введите зарплату сотрудника: ");
            Double salary = scan.nextDouble();
            System.out.println("Выберите должность сотрудника: ");
            for (Position position : Position.values()) {
                System.out.println((position.getVal() + 1) + ". " + position);
            }

            int positionVal = scan.nextInt();

            Position position = Position.values()[positionVal - 1];

            Person person;
            try {
                person = dbHelper.savedPerson(name, age, salary, position);
                System.out.println("success\n" + person);

                System.out.println("Хотите продолжить добавление в базу сотрудников? (1/0)");

                if (scan.nextInt() == 0)
                    localAns = 0;
            } catch (AgeControlExcep | SalaryExcep e) {
                System.out.println(e.getMessage());
                localAns = 1;
            }

        }
    }
}