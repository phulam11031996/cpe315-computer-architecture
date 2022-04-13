public class lab2 {

    public static void main(String[] args) {
        MIPSSimulator example = new MIPSSimulator("he");

        System.out.println(example.getRegisters().get("$zero").getRegisterNumber());
    }
}
