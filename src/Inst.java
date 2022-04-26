public class Inst {
    // data
    private String instName;
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
            String func,
            String immediate,
            String address
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
        return this.address;
    }

    public String getFunct() {
        return this.funct;
    }

    public String getImmediate() {
        return this.immediate;
    }

    public String getOpcode() {
        return this.opcode;
    }

    public String getRd() {
        return this.rd;
    }

    public String getRs() {
        return this.rs;
    }

    public String getRt() {
        return this.rt;
    }

    public String getShamt() {
        return this.shamt;
    }

    public String getInstName() {
        return this.instName;
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
