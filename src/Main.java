import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Droid> droids = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Battle battle = new Battle();
        BattleHistory battleHistory = new BattleHistory("battle.txt");

        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Створити дроїда");
            System.out.println("2. Показати всіх дроїдів");
            System.out.println("3. Бій 1 на 1");
            System.out.println("4. Бій команда на команду");
            System.out.println("5. Відтворити бій з файлу");
            System.out.println("6. Вийти");
            System.out.print("Виберіть опцію: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введіть ім'я дроїда: ");
                    String name = scanner.nextLine();
                    System.out.println("Виберіть тип дроїда:");
                    System.out.println("1. Атакуючий дроїд");
                    System.out.println("2. Снайпер");
                    System.out.println("3. Щитовик");
                    int typeChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (typeChoice) {
                        case 1:
                            droids.add(new AttackerDroid(name));
                            break;
                        case 2:
                            droids.add(new SniperDroid(name));
                            break;
                        case 3:
                            droids.add(new ShieldDroid(name));
                            break;
                        default:
                            System.out.println("Невірний вибір типу дроїда.");
                            break;
                    }
                    System.out.println("Дроїд створений!");
                    break;

                case 2:
                    if (droids.isEmpty()) {
                        System.out.println("Немає жодного дроїда.");
                    } else {
                        System.out.println("Список дроїдів:");
                        for (Droid droid : droids) {
                            System.out.println(droid.getName());
                        }
                    }
                    break;

                case 3:
                    battle.setDroids(droids);
                    battle.oneVSone(scanner);
                    break;

                case 4:
                    if (droids.size() < 2) {
                        System.out.println("Потрібно хоча б 2 дроїди для командного бою.");
                        break;
                    }

                    System.out.println("Введіть кількість дроїдів у команді 1:");
                    int team1Size = scanner.nextInt();
                    scanner.nextLine();
                    List<Droid> team1 = new ArrayList<>();

                    System.out.println("Виберіть дроїдів для команди 1:");
                    for (int i = 0; i < team1Size; i++) {
                        System.out.println("Оберіть дроїда:");
                        for (int j = 0; j < droids.size(); j++) {
                            System.out.println((j + 1) + ". " + droids.get(j).getName());
                        }
                        int team1DroidIndex = scanner.nextInt() - 1;
                        team1.add(droids.get(team1DroidIndex));
                    }

                    System.out.println("Введіть кількість дроїдів у команді 2:");
                    int team2Size = scanner.nextInt();
                    scanner.nextLine();
                    List<Droid> team2 = new ArrayList<>();

                    System.out.println("Виберіть дроїдів для команди 2:");
                    for (int i = 0; i < team2Size; i++) {
                        System.out.println("Оберіть дроїда (введіть номер):");
                        for (int j = 0; j < droids.size(); j++) {
                            System.out.println((j + 1) + ". " + droids.get(j).getName());
                        }
                        int team2DroidIndex = scanner.nextInt() - 1;
                        team2.add(droids.get(team2DroidIndex));
                    }

                    battle.teamBattle(team1, team2);
                    break;

                case 5:
                    System.out.println("Відтворення бою з файлу:");
                    battleHistory.displayHistory();
                    break;

                case 6:
                    System.out.println("Вихід з програми.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
                    break;
            }
        }
    }
}
