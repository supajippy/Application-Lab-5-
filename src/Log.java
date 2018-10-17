import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log {
    private PrintWriter sortie;

    public Log() {
        try {
            sortie = new PrintWriter(new BufferedWriter(new FileWriter("users.csv")));
        }
        catch (IOException e) { }
    }

    public void writeData(String texte) {
        sortie.println(texte);
    }

    public void saveData() {
        sortie.flush();
    }}