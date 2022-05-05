import java.util.HashMap;

public class OpcodeTable {
    // data
    private HashMap<String, String> instructions = new HashMap<>();

    // constructor
    public OpcodeTable() {
        this.instructions.put("and", "000000");
        this.instructions.put("or", "000000");
        this.instructions.put("add", "000000");
        this.instructions.put("sll", "000000");
        this.instructions.put("sub", "000000");
        this.instructions.put("slt", "000000");
        this.instructions.put("jr", "000000");
        this.instructions.put("addi", "001000");
        this.instructions.put("beq", "000100");
        this.instructions.put("bne", "000101");
        this.instructions.put("lw", "100011");
        this.instructions.put("sw", "101011");
        this.instructions.put("j", "000010");
        this.instructions.put("jal", "000011");
    }

    // setters and getters
    public String getBinaryCode(String name) {
        return this.instructions.get(name);
    }
}
