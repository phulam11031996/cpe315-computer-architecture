import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

abstract class Parser {

    // data
    private final String fileName;

    private final RegTab regTab = new RegTab();
    private final OpcTab opcTab = new OpcTab();

    protected HashMap<String, Integer> labAdds = new HashMap<>();
    protected List<String> asmInst = new ArrayList<>();
    protected List<Inst> binInst = new ArrayList<>();

    // constructor
    public Parser(String filename) {
        this.fileName = filename;
        readFile();
    }

    // private method
    private void readFile() {
        try {
            List<String> tempInstructLines = new ArrayList<>();
            File myObj = new File(this.fileName);
            Scanner myReader = new Scanner(myObj);

            // reads file
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if ((!data.trim().startsWith("#") || data.trim().contains(":")) && !data.trim().isEmpty()) {
                    if (data.contains("#")) { // removes comments
                        String temp = data.trim().substring(0, data.trim().lastIndexOf("#"));
                        if (!temp.equals(""))
                            tempInstructLines.add(temp);
                    } else
                        tempInstructLines.add(data.trim());
                }
            }
            myReader.close();

            // combines label to the next element and deletes the next element
            List<Integer> delIndexes = new ArrayList<>();
            for (String line : tempInstructLines) { // combines
                if (line.endsWith(":")) {
                    int labelIdx = tempInstructLines.indexOf(line);
                    tempInstructLines.set(labelIdx,
                            tempInstructLines.get(labelIdx) + tempInstructLines.get(labelIdx + 1));
                    delIndexes.add(labelIdx + 1);
                }
            }

            for (Integer delIdx : delIndexes) // deletes
                tempInstructLines.remove((int) delIdx);

            firstPass(tempInstructLines);

        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist.");
            e.printStackTrace();
        }
    }

    private void firstPass(List<String> tempInstructLines) {
        for (int i = 0; i != tempInstructLines.size(); i++) {
            if (tempInstructLines.get(i).contains(":")) {
                String currStr = tempInstructLines.get(i);
                String label = currStr.substring(0, currStr.lastIndexOf(":"));
                this.labAdds.put(label, i);

                tempInstructLines.set(i, currStr.substring(currStr.lastIndexOf(":") + 1));
            }
        }
        secondPass(tempInstructLines);
    }

    private void secondPass(List<String> tempInstructLines) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789$-";
        for (String line : tempInstructLines) {
            StringBuilder first = new StringBuilder();
            StringBuilder second = new StringBuilder();
            StringBuilder third = new StringBuilder();
            StringBuilder fourth = new StringBuilder();

            int counter = 0;
            for (String ch : line.split("")) {
                if (first.length() != 0 && counter == 0 && (ch.equals("$") || !alphabet.contains(ch)))
                    counter++;
                if (ch.equals(",") || ch.equals("("))
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
            this.asmInst.add(first + " " + second + " " + third + " " + fourth);
        }
        convertBinary();
    }

    private void convertBinary() {
        int counter = 0;
        for (String line : this.asmInst) {
            String[] instruction = line.split(" ");
            switch (instruction[0]) {
                case ("and"):
                case ("add"):
                case ("slt"):
                case ("sub"):
                case ("or"):
                    String func;
                    if (instruction[0].equals("slt"))
                        func = "101010";
                    else if (instruction[0].equals("sub"))
                        func = "100010";
                    else if (instruction[0].equals("or"))
                        func = "100101";
                    else if (instruction[0].equals("and"))
                        func = "100100";
                    else
                        func = "100000";
                    Inst ins1 = new Inst(
                            instruction[0],
                            this.opcTab.getBinaryCode(instruction[0]),
                            this.regTab.getBinaryCode(instruction[2]),
                            this.regTab.getBinaryCode(instruction[3]),
                            this.regTab.getBinaryCode(instruction[1]),
                            "00000",
                            func,
                            null,
                            null);
                    this.binInst.add(ins1);
                    break;
                case ("addi"):
                    Inst ins2 = new Inst(
                            instruction[0],
                            this.opcTab.getBinaryCode(instruction[0]),
                            this.regTab.getBinaryCode(instruction[2]),
                            this.regTab.getBinaryCode(instruction[1]),
                            null,
                            null,
                            null,
                            toBinary(Integer.parseInt(instruction[3]), 16),
                            null);
                    this.binInst.add(ins2);
                    break;
                case ("sll"):
                    Inst ins3 = new Inst(
                            instruction[0],
                            this.opcTab.getBinaryCode(instruction[0]),
                            "00000",
                            this.regTab.getBinaryCode(instruction[2]),
                            this.regTab.getBinaryCode(instruction[1]),
                            toBinary(Integer.parseInt(instruction[3]), 5),
                            "000000",
                            null,
                            null);
                    this.binInst.add(ins3);
                    break;
                case ("beq"):
                case ("bne"):
                    int address;
                    if (counter - this.labAdds.get(instruction[3]) > 0)
                        address = -(counter + 1 - this.labAdds.get(instruction[3]));
                    else
                        address = this.labAdds.get(instruction[3]) - counter - 1;

                    Inst ins4 = new Inst(
                            instruction[0],
                            this.opcTab.getBinaryCode(instruction[0]),
                            this.regTab.getBinaryCode(instruction[1]),
                            this.regTab.getBinaryCode(instruction[2]),
                            null,
                            null,
                            null,
                            toBinary(address, 16),
                            null);
                    this.binInst.add(ins4);
                    break;
                case ("lw"):
                case ("sw"):
                    Inst ins5 = new Inst(
                            instruction[0],
                            this.opcTab.getBinaryCode(instruction[0]),
                            this.regTab.getBinaryCode(instruction[3]),
                            this.regTab.getBinaryCode(instruction[1]),
                            null,
                            null,
                            null,
                            toBinary(Integer.parseInt(instruction[2]), 16),
                            null);
                    this.binInst.add(ins5);
                    break;

                case ("j"):
                case ("jal"):
                    Inst ins6 = new Inst(
                            instruction[0],
                            this.opcTab.getBinaryCode(instruction[0]),
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            toBinary(this.labAdds.get(instruction[1]), 26));
                    this.binInst.add(ins6);
                    break;
                case ("jr"):
                    Inst ins7 = new Inst(
                            instruction[0],
                            this.opcTab.getBinaryCode(instruction[0]),
                            this.regTab.getBinaryCode(instruction[1]),
                            "00000",
                            "00000",
                            "00000",
                            "001000",
                            null,
                            null);
                    this.binInst.add(ins7);
                    break;
                default:
                    Inst ins8 = new Inst(instruction[0],
                            null, null, null, null,
                            null, null, null, instruction[0]);
                    this.binInst.add(ins8);
                    break;
            }
            counter++;
            if (opcTab.getBinaryCode(instruction[0]) == null)
                break;
        }
    }

    private static String toBinary(int num, int length) {
        StringBuilder strResult = new StringBuilder();
        for (int i = length - 1; i >= 0; i--) {
            int mask = 1 << i;
            strResult.append((num & mask) != 0 ? 1 : 0);
        }
        return strResult.toString();
    }

    // lab 2 method
    public void displayMachineCode() {
        for (Inst binInst : this.binInst) {
            System.out.println(binInst);
        }
    }

    public void displayMipsCode() {
        for (String mipsInst : this.asmInst) {
            System.out.println(mipsInst);
        }
    }

}
