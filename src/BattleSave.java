import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BattleSave {
    private String fileName;

    public BattleSave(String fileName) {
        this.fileName = fileName;
    }

    public void BattleResult(Droid droid1, Droid droid2, String winner) {
        try (PrintWriter wr = new PrintWriter(new FileWriter(fileName, true))) {
            wr.println(droid1.getName() + " VS " + droid2.getName());
            wr.println("Результат: " + winner + " виграв");
            wr.println("-----------------------------------");
        } catch (IOException e) {
            System.out.println("Сталася помилка при записі у файл");
        }
    }
}
