import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BattleHistory {
    private String fileName;

    public BattleHistory(String fileName) {
        this.fileName = fileName;
    }

    public void displayHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Сталася помилка при читанні з файлу" );
        }
    }
}
