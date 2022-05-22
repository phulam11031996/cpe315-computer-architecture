// Section: 1 ( 8:10 AM in the morning )
// Names: Phu & Rohit

public class lab5 {

    public static void main(String[] args) {
        // Simulator simulator = new Simulator("lab4_fib20.asm", 8);

        // simulator.interactiveModeE();
        // // simulator.displayMachineCode();

        // if (args.length == 1)
        // simulator.interactiveModeS();
        // else if (args.length == 2)
        // simulator.scriptModeS(args[1]);
        // else
        // System.out.println("Invalid input. Please try again.");

        // Predictor pre = new Predictor(8);

        // System.out.println(args[0]);
        // System.out.println(args[1]);
        // System.out.println(args[2]);

        if (args.length == 2) {
            Simulator simulator = new Simulator(args[0], Integer.parseInt(args[1]));
            simulator.interactiveModeE();
        } else if (args.length == 3) {
            Simulator simulator = new Simulator(args[0], Integer.parseInt(args[2]));
            simulator.scriptModeE(args[1]);
        } else
            System.out.println("Invalid input. Please try again.");
    }
}
