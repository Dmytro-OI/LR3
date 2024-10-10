import java.util.List;
import java.util.Scanner;

public class Battle {
    private List<Droid> droids;
    private BattleSave battleSave;

    public Battle() {
        this.battleSave = new BattleSave("battle.txt");
    }

    public void setDroids(List<Droid> droids) {
        this.droids = droids;
    }

    public void oneVSone(Scanner scanner) {
        if (droids.size() < 2) {
            System.out.println("Потрібно хоча б 2 дроїди для реалізації такого бою");
            return;
        }

        System.out.println("Оберіть першого дроїда");
        for (int i = 0; i < droids.size(); i++) {
            System.out.println((i + 1) + ". " + droids.get(i).getName());
        }
        int firstDroidIndex = scanner.nextInt() - 1;

        System.out.println("Оберіть другого дроїда");
        for (int i = 0; i < droids.size(); i++) {
            System.out.println((i + 1) + ". " + droids.get(i).getName());
        }
        int secondDroidIndex = scanner.nextInt() - 1;

        if (firstDroidIndex == secondDroidIndex) {
            System.out.println("Не можна вибрати одного й того ж дроїда");
            return;
        }

        Droid droid1 = droids.get(firstDroidIndex);
        Droid droid2 = droids.get(secondDroidIndex);

        System.out.println(droid1.getName() + " VS " + droid2.getName() + " START");

        while (droid1.getHealth() > 0 && droid2.getHealth() > 0) {
            droid1.attack(droid2);
            System.out.println(droid2.getName() + " HP: " + droid2.getHealth());
            if (droid2.getHealth() <= 0) {
                System.out.println(droid1.getName() + " виграв");
                battleSave.BattleResult(droid1, droid2, droid1.getName());
                return;
            }

            droid2.attack(droid1);
            System.out.println(droid1.getName() + " HP: " + droid1.getHealth());
            if (droid1.getHealth() <= 0) {
                System.out.println(droid2.getName() + " виграв");
                battleSave.BattleResult(droid1, droid2, droid2.getName());
                return;
            }
        }
    }

    public void teamBattle(List<Droid> team1, List<Droid> team2) {
        while (!team1.isEmpty() && !team2.isEmpty()) {
            for (int i = 0; i < Math.min(team1.size(), team2.size()); i++) {
                if (i < team1.size() && i < team2.size()) {
                    Droid attacker = team1.get(i);
                    Droid defender = team2.get(i);
                    battle(attacker, defender);
                }
            }
            cleanUpTeams(team1, team2);
        }
        declareWinner(team1, team2);
    }

    private void battle(Droid attacker, Droid defender) {
        System.out.println(attacker.getName() + " атакує " + defender.getName());
        attacker.attack(defender);

        if (defender.getHealth() <= 0) {
            System.out.println(defender.getName() + " був знищений");
            battleSave.BattleResult(attacker, defender, attacker.getName());
        } else {
            defender.attack(attacker);
            if (attacker.getHealth() <= 0) {
                System.out.println(attacker.getName() + " був знищений");
                battleSave.BattleResult(attacker, defender, defender.getName());
            }
        }
    }

    private void cleanUpTeams(List<Droid> team1, List<Droid> team2) {
        team1.removeIf(droid -> droid.getHealth() <= 0);
        team2.removeIf(droid -> droid.getHealth() <= 0);
    }

    private void declareWinner(List<Droid> team1, List<Droid> team2) {
        if (team1.isEmpty()) {
            System.out.println("Команда 2 виграла");
        } else if (team2.isEmpty()) {
            System.out.println("Команда 1 виграла");
        } else {
            System.out.println("Бій завершено");
        }
    }
}
