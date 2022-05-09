// Section: 1 ( 8:10 AM in the morning )
// Names: Phu & Rohit

public class lab3 {

    public static void main(String[] args) {
        Simulator simulator = new Simulator("test1.asm");

        boolean flag = true;
        int counter = 0;
        while (flag) {
            flag = simulator.step();
            simulator.displayPipeLine(counter);
            counter++;

        }

        // for (int i = 0; i < 43; i++) {
        //     simulator.step();
        //     simulator.displayPipeLine(i + 1);
        // }
        // simulator.normalStep();
        // simulator.normalStep();
        // simulator.stallStep();

        // simulator.displayPipeLine(0);
        // simulator.displayPipeLine(1);
        // simulator.displayPipeLine(2);
        // simulator.displayPipeLine(3);


    }
}
