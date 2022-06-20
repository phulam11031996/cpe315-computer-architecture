// Section: 1 ( 8:10 AM in the morning )
// Names: Phu & Rohit

public class lab5 {

    public static void main(String[] args) {

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
