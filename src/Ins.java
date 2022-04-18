import java.util.ArrayList;

public class Ins {
    private String opcode;
    private String rs;
    private String rt;
    private String rd;
    private String shamt;
    private String funct;
    private String immediate;
    private String address;

    // r type
    public Ins(String opcode, String rs, String rt, String rd, String shamt, String funct) {
        this.opcode = opcode;
        this.rs = rs;
        this.rt = rt;
        this.rd = rd;
        this.shamt = shamt;
        this.funct = funct;
    }

    // i type
    public Ins(String opcode, String rs, String rt, String immediate) {
        this.opcode = opcode;
        this.rs = rs;
        this.rt = rt;
        this.immediate = immediate;
    }

    // j type
    public Ins(String opcode, String address) {
        this.opcode = opcode;
        this.address = address;
    }

    @Override
    public String toString() {
        ArrayList<String> r = new ArrayList<String>() {
            {
                add("000000");
            }
        };
        ArrayList<String> i = new ArrayList<String>() {
            {
                add("001000");
                add("000100");
                add("000101");
                add("100011");
                add("101011");
            }
        };
        ArrayList<String> j = new ArrayList<String>() {
            {
                add("000010");
                add("000011");
            }
        };

        if (r.contains(this.opcode))
            return this.opcode + " " + this.rs + " " + this.rt + " " + this.rd + " " + this.shamt + " " + this.funct;
        if (i.contains(this.opcode)) return this.opcode + " " + this.rt + " " + this.rs + " " + this.immediate;
        return this.opcode + " " + this.address;
    }


    public String getAddress() {
        return address;
    }

    public String getFunct() {
        return funct;
    }

    public String getImmediate() {
        return immediate;
    }

    public String getOpcode() {
        return opcode;
    }

    public String getRd() {
        return rd;
    }

    public String getRs() {
        return rs;
    }

    public String getRt() {
        return rt;
    }

    public String getShamt() {
        return shamt;
    }
}
