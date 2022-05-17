// Section: 1 ( 8:10 AM in the morning )
// Names: Phu & Rohit

public class lab4 {

    public static void main(String[] args) {
        Simulator simulator = new Simulator(args[0]);

        // simulator.displayMachineCode();

        if (args.length == 1)
            simulator.interactiveModeS();
        else if (args.length == 2)
            simulator.scriptModeS(args[1]);
        else
            System.out.println("Invalid input. Please try again.");
    }
}
