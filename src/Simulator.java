import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Simulator {

    private final Instruction instruction = new Instruction();
    private final Register register = new Register();
    private final String fileName;
    private final List<String> instructionLines = new ArrayList<>();
    private final HashMap<String, Integer> labelAdresses = new HashMap<>();

    public Simulator(String fileName) {
        this.fileName = fileName;
        readFile();
        firstPass();
    }

    public void readFile() {
        try {
            File myObj = new File(this.fileName);
            Scanner myReader = new Scanner(myObj);

            // reads file
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if ((!data.trim().startsWith("#") || data.trim().contains(":")) && !data.trim().isEmpty()) {
                    if (data.contains("#")) // removes comments
                        this.instructionLines.add(data.trim().substring(0, data.trim().lastIndexOf("#")));
                    else
                        this.instructionLines.add(data.trim());
                }
            }
            myReader.close();

            // combines label to the next element and deletes the next element
            List<Integer> delIndexes = new ArrayList<>();
            for (String line : this.instructionLines) {  // combines
                if (line.endsWith(":")) {
                    int labelIdx = this.instructionLines.indexOf(line);
                    this.instructionLines.set(labelIdx, this.instructionLines.get(labelIdx) + this.instructionLines.get(labelIdx + 1));
                    delIndexes.add(labelIdx + 1);
                }
            }

            for (Integer delIdx : delIndexes)   // deletes
                this.instructionLines.remove((int) delIdx);

        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist.");
            e.printStackTrace();
        }
    }

    public void firstPass() {
        for (String line : instructionLines) {
            if (line.contains(":")) {
                String label = line.substring(0, line.lastIndexOf(":"));
                Integer address = this.instructionLines.indexOf(line);
                this.labelAdresses.put(label, address);
            }
        }
    }

    public List<String> getInstructionLines() {
        return this.instructionLines;
    }

    public HashMap<String, Integer> getLabelAddresses() {
        return this.labelAdresses;
    }


    public static void main(String[] args) {
        Simulator simulator =  new Simulator("test1.asm");
        for (String line : simulator.getInstructionLines())
            System.out.println(line);

        System.out.println(simulator.getLabelAddresses());
    }
}
