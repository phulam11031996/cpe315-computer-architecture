public class RegisterValue {
    private Integer registerNumber;
    private String binaryCode;

    public RegisterValue(String binaryCode, Integer registerNumber) {
        this.registerNumber = registerNumber;
        this.binaryCode = binaryCode;
    }

    public Integer getRegisterNumber() {
        return registerNumber;
    }

    public String getBinaryCode() {
        return binaryCode;
    }
}
