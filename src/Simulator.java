import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.*;

public class Simulator {

    private final Instruction instruction = new Instruction();
    private final Register register = new Register();
    private final String fileName;
    private final List<String> instructionLines = new ArrayList<>();
    private final HashMap<String, Integer> labelAddresses = new HashMap<>();

    public Simulator(String fileName) {
        this.fileName = fileName;
        readFile();
        firstPass();
        secondPass();
//        display();
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
        for (int i = 0; i != this.instructionLines.size(); i++) {
            if (this.instructionLines.get(i).contains(":")) {
                String currStr = this.instructionLines.get(i);
                String label = currStr.substring(0, currStr.lastIndexOf(":"));
                this.labelAddresses.put(label, i);

                this.instructionLines.set(i, currStr.substring(currStr.lastIndexOf(":") + 1));
            }
        }
    }

    public void secondPass() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789$()-";
        for (String line : instructionLines) {
            StringBuilder first = new StringBuilder();
            StringBuilder second = new StringBuilder();
            StringBuilder third = new StringBuilder();
            StringBuilder fourth = new StringBuilder();

            int counter = 0;
            for (String ch : line.split("")) {
                if (!first.isEmpty() && counter == 0 && (ch.equals("$") || !alphabet.contains(ch)))
                    counter++;

                if (ch.equals(","))
                    counter++;

                if (alphabet.contains(ch)) {
                    if (counter == 0)
                        first.append(ch);

                    if (counter == 1)
                        second.append(ch);

                    if (counter == 2)
                        third.append(ch);

                    if (counter == 3)
                        fourth.append(ch);

                }
            }

            System.out.println(first + " " + second + " " + third + " " + fourth);
        }

    }


    public List<String> getInstructionLines() {
        return this.instructionLines;
    }

    public HashMap<String, Integer> getLabelAddresses() {
        return this.labelAddresses;
    }


    public static void main(String[] args) {
        Simulator simulator = new Simulator("test1.asm");

        System.out.println(" ----------- ");
        for (String line : simulator.getInstructionLines())
            System.out.println(line);

//        System.out.println(simulator.getLabelAddresses());
    }
}
