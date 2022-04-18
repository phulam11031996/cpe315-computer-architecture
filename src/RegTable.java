import java.util.HashMap;

public class RegTable {
    // data
    private HashMap<String, RegValue> registers = new HashMap<>();

    // constructor
    public RegTable() {
        this.registers.put("$zero", new RegValue("00000", 0));
        this.registers.put("$0", new RegValue("00000", 0));
        this.registers.put("$v0", new RegValue("00010", 2));
        this.registers.put("$v1", new RegValue("00011", 3));
        this.registers.put("$a0", new RegValue("00100", 4));
        this.registers.put("$a1", new RegValue("00101", 5));
        this.registers.put("$a2", new RegValue("00110", 6));
        this.registers.put("$a3", new RegValue("00111", 7));
        this.registers.put("$t0", new RegValue("01000", 8));
        this.registers.put("$t1", new RegValue("01001", 9));
        this.registers.put("$t2", new RegValue("01010", 10));
        this.registers.put("$t3", new RegValue("01011", 11));
        this.registers.put("$t4", new RegValue("01100", 12));
        this.registers.put("$t5", new RegValue("01101", 13));
        this.registers.put("$t6", new RegValue("01110", 14));
        this.registers.put("$t7", new RegValue("01111", 15));
        this.registers.put("$s0", new RegValue("10000", 16));
        this.registers.put("$s1", new RegValue("10001", 17));
        this.registers.put("$s2", new RegValue("10010", 18));
        this.registers.put("$s3", new RegValue("10011", 19));
        this.registers.put("$s4", new RegValue("10100", 20));
        this.registers.put("$s5", new RegValue("10101", 21));
        this.registers.put("$s6", new RegValue("10110", 22));
        this.registers.put("$s7", new RegValue("10111", 23));
        this.registers.put("$t8", new RegValue("11000", 24));
        this.registers.put("$t9", new RegValue("11001", 25));
        this.registers.put("$sp", new RegValue("11101", 29));
        this.registers.put("$ra", new RegValue("11111", 31));
    }

    // setters and getters
    public String getBinaryCode(String registerName) {
        return this.registers.get(registerName).getBinaryCode();
    }
}
