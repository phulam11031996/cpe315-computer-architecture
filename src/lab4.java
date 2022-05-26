// Section: 1 ( 8:10 AM in the morning )
// Names: Phu & Rohit

public class lab4 {

    public static void main(String[] args) {

        // simulator.displayMachineCode();

        if (args.length == 1) {
            System.out.println(("Fill this out"));
        }
        else if (args.length == 3)
            Emulator emulator = new Emulator(args[0], Integer.valueOf(args[2]));
            emulator.scriptModeS(args[1]);
        else
            System.out.println("Invalid input. Please try again.");
    }
}
