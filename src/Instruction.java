import java.util.ArrayList;
public class Instruction {
    private String opcode;
    private String rs;
    private String rt;
    private String rd;
    private String shamt;
    private String funct;
    private String immediate;
    private String address;

    // r type
    public Instruction(String opcode, String rs, String rt, String rd, String shamt, String funct) {
        this.opcode = opcode;
        this.rs = rs;
        this.rt = rt;
        this.rd = rd;
        this.shamt = shamt;
        this.funct = funct;
    }

    // i type
    public Instruction(String opcode, String rs, String rt, String immediate) {
        this.opcode = opcode;
        this.rs = rs;
        this.rt = rt;
        this.immediate = immediate;
    }

    // j type
    public Instruction(String opcode, String address) {
        this.opcode = opcode;
        this.address = address;
    }

    @Override
    public String toString() {
        ArrayList<String> r = new ArrayList<String>() {
            {
                add("and");
                add("or");
                add("add");
                add("sll");
                add("sub");
                add("slt");
                add("jr");
            }
        };
        ArrayList<String> i = new ArrayList<String>() {
            {
                add("addi");
                add("beq");
                add("bne");
                add("lw");
                add("sw");
            }
        };
        ArrayList<String> j = new ArrayList<String>() {
            {
                add("j");
                add("jal");
            }
        };

        if (r.contains(this.opcode))
            return this.opcode + " " + this.rs + " " + this.rt + " " + this.rd + " " + this.shamt + " " + this.funct;

        if (i.contains(this.opcode))
            return this.opcode + " " + this.rt + " " + this.rs + " " + this.immediate;

        return this.opcode + " " + this.address;
    }
}
