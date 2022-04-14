import java.util.HashMap;

public class Instruction {
    private HashMap<String, String> instructions = new HashMap<>();

    public Instruction() {
        this.instructions.put("and", "000000");
        this.instructions.put("or", "000000");
        this.instructions.put("add", "000000");
        this.instructions.put("addi", "001000");
        this.instructions.put("sll", "000000");
        this.instructions.put("sub", "000000");
        this.instructions.put("slt", "000000");
        this.instructions.put("beq", "000100");
        this.instructions.put("bne", "000101");
        this.instructions.put("lw", "100011");
        this.instructions.put("sw", "101011");
        this.instructions.put("j", "000010");
        this.instructions.put("jr", "000000");
        this.instructions.put("jal", "000011");
    }

    public HashMap<String, String> getInstructions() {
        return instructions;
    }

    public String getBinaryCode(String instructionName) {
        return this.instructions.get(instructionName);
    }

}
