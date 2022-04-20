public class Inst {
    // data
    private String opcode;
    private String rs;
    private String rt;
    private String rd;
    private String shamt;
    private String funct;
    private String immediate;
    private String address;

    // constructor
    public Inst(
            String opcode,
            String rs,
            String rt,
            String rd,
            String shamt,
            String func, String immediate, String address
    ) {
        this.opcode = opcode;
        this.rs = rs;
        this.rt = rt;
        this.rd = rd;
        this.shamt = shamt;
        this.funct = func;
        this.immediate = immediate;
        this.address = address;
    }

    // getters and setters
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

    // overrides print
    public String toString() {
        StringBuilder binCode = new StringBuilder();
        if (this.opcode == null)
            return "invalid instruction: " + this.address;
        binCode.append(this.opcode).append(" ");
        if (this.rs != null)
            binCode.append(this.rs).append(" ");
        if (this.rt != null)
            binCode.append(this.rt).append(" ");
        if (this.rd != null)
            binCode.append(this.rd).append(" ");
        if (this.shamt != null)
            binCode.append(this.shamt).append(" ");
        if (this.funct != null)
            binCode.append(this.funct).append(" ");
        if (this.immediate != null)
            binCode.append(this.immediate).append(" ");
        if (this.address != null)
            binCode.append(this.address);

        return binCode.toString().trim();
    }
}
