
// Section: 1 ( 8:10 AM in the morning )
// Names: Phu & Rohit

public class lab3 {

    public static void main(String[] args) {
        Simulator simulator = new Simulator(args[0]);

        // simulator.displayMachineCode();

        if (args.length == 1)
            simulator.interactiveMode();
        else if (args.length == 2)
            simulator.scriptMode(args[1]);
        else
            System.out.println("Invalid input. Please try again.");
    }
}
