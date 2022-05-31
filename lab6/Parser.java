import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    public String fileName = new String();
    public List<Integer> lines = new ArrayList<Integer>();

    public Parser(String fileName) {
        this.fileName = fileName;
        this.readFile();
    }

    public void readFile() {
        try {
            File myObj = new File(this.fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int address = Integer.parseInt(data.split("\t")[1], 16);
                this.lines.add(address);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
            e.printStackTrace();
        }
    }

    public List<Integer> getLines() {
        return this.lines;
    }

    public Integer getNumberOfLines() {
        return this.lines.size();
    }
}
