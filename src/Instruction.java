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

    public String and() {
        return this.instructions.get("and");
    }

    public String or() {
        return this.instructions.get("or");
    }

    public String addi() {
        return this.instructions.get("addi");
    }

    public String sll() {
        return this.instructions.get("sll");
    }

    public String sub() {
        return this.instructions.get("sub");
    }

    public String slt() {
        return this.instructions.get("slt");
    }

    public String beq() {
        return this.instructions.get("beq");
    }

    public String bne() {
        return this.instructions.get("bne");
    }

    public String lw() {
        return this.instructions.get("lw");
    }

    public String sw() {
        return this.instructions.get("sw");
    }

    public String j() {
        return this.instructions.get("j");
    }

    public String jr() {
        return this.instructions.get("jr");
    }

    public String jal() {
        return this.instructions.get("jal");
    }
}
