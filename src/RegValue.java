public class RegValue {
    // data
    private Integer registerNumber;
    private String binaryCode;

    // constructor
    public RegValue(String binaryCode, Integer registerNumber) {
        this.registerNumber = registerNumber;
        this.binaryCode = binaryCode;
    }

    // setters and getters
    public Integer getRegisterNumber() {
        return registerNumber;
    }
    public String getBinaryCode() {
        return binaryCode;
    }
}
