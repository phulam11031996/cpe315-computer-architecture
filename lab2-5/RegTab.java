import java.util.HashMap;

public class RegTab {
    // data
    private HashMap<String, String> registers = new HashMap<>();

    // constructor
    public RegTab() {
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

        this.registers.put("00000", "$zero");
        this.registers.put("00000", "$0");
        this.registers.put("00010", "$v0");
        this.registers.put("00011", "$v1");
        this.registers.put("00100", "$a0");
        this.registers.put("00101", "$a1");
        this.registers.put("00110", "$a2");
        this.registers.put("00111", "$a3");
        this.registers.put("01000", "$t0");
        this.registers.put("01001", "$t1");
        this.registers.put("01010", "$t2");
        this.registers.put("01011", "$t3");
        this.registers.put("01100", "$t4");
        this.registers.put("01101", "$t5");
        this.registers.put("01110", "$t6");
        this.registers.put("01111", "$t7");
        this.registers.put("10000", "$s0");
        this.registers.put("10001", "$s1");
        this.registers.put("10010", "$s2");
        this.registers.put("10011", "$s3");
        this.registers.put("10100", "$s4");
        this.registers.put("10101", "$s5");
        this.registers.put("10110", "$s6");
        this.registers.put("10111", "$t7");
        this.registers.put("11000", "$t8");
        this.registers.put("11001", "$t9");
        this.registers.put("11101", "$sp");
        this.registers.put("11111", "$ra");
    }

    // setters and getters
    public String getBinaryCode(String registerName) {
        return this.registers.get(registerName);
    }

}
