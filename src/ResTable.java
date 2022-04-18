import java.util.HashMap;

public class ResTable {
    private HashMap<String, ResVal> registers = new HashMap<>();

    public ResTable() {
        this.registers.put("$zero", new ResVal("00000", 0));
        this.registers.put("$0", new ResVal("00000", 0));
        this.registers.put("$v0", new ResVal("00010", 2));
        this.registers.put("$v1", new ResVal("00011", 3));
        this.registers.put("$a0", new ResVal("00100", 4));
        this.registers.put("$a1", new ResVal("00101", 5));
        this.registers.put("$a2", new ResVal("00110", 6));
        this.registers.put("$a3", new ResVal("00111", 7));
        this.registers.put("$t0", new ResVal("01000", 8));
        this.registers.put("$t1", new ResVal("01001", 9));
        this.registers.put("$t2", new ResVal("01010", 10));
        this.registers.put("$t3", new ResVal("01011", 11));
        this.registers.put("$t4", new ResVal("01100", 12));
        this.registers.put("$t5", new ResVal("01101", 13));
        this.registers.put("$t6", new ResVal("01110", 14));
        this.registers.put("$t7", new ResVal("01111", 15));
        this.registers.put("$s0", new ResVal("10000", 16));
        this.registers.put("$s1", new ResVal("10001", 17));
        this.registers.put("$s2", new ResVal("10010", 18));
        this.registers.put("$s3", new ResVal("10011", 19));
        this.registers.put("$s4", new ResVal("10100", 20));
        this.registers.put("$s5", new ResVal("10101", 21));
        this.registers.put("$s6", new ResVal("10110", 22));
        this.registers.put("$s7", new ResVal("10111", 23));
        this.registers.put("$t8", new ResVal("11000", 24));
        this.registers.put("$t9", new ResVal("11001", 25));
        this.registers.put("$sp", new ResVal("11101", 29));
        this.registers.put("$ra", new ResVal("11111", 31));
    }

    public String getBinaryCode(String registerName) {
        return this.registers.get(registerName).getBinaryCode();
    }

}
