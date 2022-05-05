import java.util.HashMap;

public class RegisterTable {
    // data
    private HashMap<String, String> registers = new HashMap<>();

    // constructor
    public RegisterTable() {
        this.registers.put("$zero", "00000");
        this.registers.put("$0", "00000");
        this.registers.put("$v0", "00010");
        this.registers.put("$v1", "00011");
        this.registers.put("$a0", "00100");
        this.registers.put("$a1", "00101");
        this.registers.put("$a2", "00110");
        this.registers.put("$a3", "00111");
        this.registers.put("$t0", "01000");
        this.registers.put("$t1", "01001");
        this.registers.put("$t2", "01010");
        this.registers.put("$t3", "01011");
        this.registers.put("$t4", "01100");
        this.registers.put("$t5", "01101");
        this.registers.put("$t6", "01110");
        this.registers.put("$t7", "01111");
        this.registers.put("$s0", "10000");
        this.registers.put("$s1", "10001");
        this.registers.put("$s2", "10010");
        this.registers.put("$s3", "10011");
        this.registers.put("$s4", "10100");
        this.registers.put("$s5", "10101");
        this.registers.put("$s6", "10110");
        this.registers.put("$s7", "10111");
        this.registers.put("$t8", "11000");
        this.registers.put("$t9", "11001");
        this.registers.put("$sp", "11101");
        this.registers.put("$ra", "11111");
    }

    // setters and getters
    public String getBinaryCode(String registerName) {
        return this.registers.get(registerName);
    }
}
