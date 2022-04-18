import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Simulator {
    private final String fileName;
    private final InsTable insTable = new InsTable();
    private final ResTable resTable = new ResTable();
    private final List<String> tempInstructLines = new ArrayList<>();
    private final List<String> finalInstructLines = new ArrayList<>();
    private final HashMap<String, Integer> labelAddresses = new HashMap<>();
    private final List<Ins> binaryInstructLines = new ArrayList<>();


    public Simulator(String fileName) {
        this.fileName = fileName;
        readFile();
        firstPass();
        secondPass();
        convertBinary();
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
                        this.tempInstructLines.add(data.trim().substring(0, data.trim().lastIndexOf("#")));
                    else this.tempInstructLines.add(data.trim());
                }
            }
            myReader.close();

            // combines label to the next element and deletes the next element
            List<Integer> delIndexes = new ArrayList<>();
            for (String line : this.tempInstructLines) {  // combines
                if (line.endsWith(":")) {
                    int labelIdx = this.tempInstructLines.indexOf(line);
                    this.tempInstructLines.set(labelIdx, this.tempInstructLines.get(labelIdx) + this.tempInstructLines.get(labelIdx + 1));
                    delIndexes.add(labelIdx + 1);
                }
            }

            for (Integer delIdx : delIndexes)   // deletes
                this.tempInstructLines.remove((int) delIdx);

        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist.");
            e.printStackTrace();
        }
    }

    public void firstPass() {
        for (int i = 0; i != this.tempInstructLines.size(); i++) {
            if (this.tempInstructLines.get(i).contains(":")) {
                String currStr = this.tempInstructLines.get(i);
                String label = currStr.substring(0, currStr.lastIndexOf(":"));
                this.labelAddresses.put(label, i);

                this.tempInstructLines.set(i, currStr.substring(currStr.lastIndexOf(":") + 1));
            }
        }
    }

    public void secondPass() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789$()-";
        for (String line : tempInstructLines) {
            StringBuilder first = new StringBuilder();
            StringBuilder second = new StringBuilder();
            StringBuilder third = new StringBuilder();
            StringBuilder fourth = new StringBuilder();

            int counter = 0;
            for (String ch : line.split("")) {
                if (!first.isEmpty() && counter == 0 && (ch.equals("$") || !alphabet.contains(ch))) counter++;
                if (ch.equals(",")) counter++;

                if (alphabet.contains(ch)) {
                    if (counter == 0) first.append(ch);
                    if (counter == 1) second.append(ch);
                    if (counter == 2) third.append(ch);
                    if (counter == 3) fourth.append(ch);
                }
            }
            this.finalInstructLines.add(first + " " + second + " " + third + " " + fourth);
        }
    }

    public void convertBinary() {
        for (String line : this.finalInstructLines) {
            String[] instruction = line.split(" ");
            switch (instruction[0]) {
                case ("and"): // r types = opcode + rs + rt + rd + shampt + funct
                case ("add"):
                    Ins ins = new Ins(this.insTable.getBinaryCode(instruction[0]),
                            this.resTable.getBinaryCode(instruction[2]),
                            this.resTable.getBinaryCode(instruction[3]),
                            this.resTable.getBinaryCode(instruction[1]),
                            "00000",
                            "100000");
                    this.binaryInstructLines.add(ins);
                    break;
                case ("or"):
                    //
                    //
                    break;
                case ("addi"): // i types = opcode + rs + rt + immediate
//                    currBinInstruction += this.instruction.getBinaryCode(instruction[0]) + " ";
//                    currBinInstruction += this.register.getBinaryCode(instruction[1]) + " ";
//                    currBinInstruction += this.register.getBinaryCode(instruction[2]) + " ";
//                    currBinInstruction += toBinary(Integer.parseInt(instruction[3]), 16);
//                    this.binaryInstructLines.add(currBinInstruction);
                    break;
                case ("sll"):
                    //
                    //
                    break;
                case ("sub"):
                    //
                    //
                    break;
                case ("slt"):
                    //
                    //
                    break;
                case ("beq"):
                    //
                    //
                    break;
                case ("bne"):
                    //
                    //
                    break;
                case ("lw"):
                    //
                    //
                    break;
                case ("sw"):
                    //
                    //
                    break;
                case ("j"):
                    //
                    //
                    break;
                case ("jr"):
                    //
                    //
                    break;
                case ("jal"):
                    //
                    //
                    break;
                default:
                    // invalid instruction
                    //
                    break;

            }
        }
    }

    public static String toBinary(int number, int binLength) {
        if (binLength > 0) {
            return String.format("%" + binLength + "s", Integer.toBinaryString(number)).replaceAll(" ", "0");
        }

        return null;
    }

    public List<String> getTempInstructLines() {
        return this.tempInstructLines;
    }

    public HashMap<String, Integer> getLabelAddresses() {
        return this.labelAddresses;
    }

    public List<String> getFinalInstructLines() {
        return finalInstructLines;
    }

    public List<Ins> getBinaryInstructLines() {
        return binaryInstructLines;
    }

    public static void main(String[] args) {
        Simulator simulator = new Simulator("test1.asm");

//        for (String line : simulator.getFinalInstructLines()){
//            System.out.println(line);
//        }

        for (Ins line : simulator.getBinaryInstructLines()) {
            System.out.println(line);
//            System.out.print(line.getOpcode());
//            System.out.print(line.getRs());
//            System.out.print(line.getRt());
//            System.out.print(line.getRd());
        }

    }
}
