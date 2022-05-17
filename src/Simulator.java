import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Simulator extends Emulator {

    // data
    private int cycles = 4;
    private int numInsts = 0;
    private int squashFlag = 0;

    private List<Integer> pcList = null;
    private List<Inst> ifid = null;
    private List<Inst> idex = null;
    private List<Inst> exme = null;
    private List<Inst> mewb = null;

    private Inst empInst = new Inst("empty", null, null, null, null, null, null, null, null);
    private Inst staInst = new Inst("stall", null, null, null, null, null, null, null, null);
    private Inst squInst = new Inst("squash", null, null, null, null, null, null, null, null);

    // constructor
    public Simulator(String fileName) {
        super(fileName);
        this.initS();
    }

    public void initS() {
        this.pcList = new LinkedList<Integer>();
        this.ifid = new LinkedList<Inst>();
        this.idex = new LinkedList<Inst>();
        this.exme = new LinkedList<Inst>();
        this.mewb = new LinkedList<Inst>();

        this.pcList.add(0);
        this.ifid.add(empInst);
        this.idex.add(empInst);
        this.exme.add(empInst);
        this.mewb.add(empInst);
    }

    // secure method
    private void hS() {
        System.out.println("\t\th = show help");
        System.out.println("\t\td = dump register state");
        System.out.println("\t\tp = show pipeline registers");
        System.out.println("\t\ts = step through a single clock cycle step (i.e. simulate 1 cycle and stop)");
        System.out.println("\t\ts num = step through num clock cycles");
        System.out.println("\t\tr = run until the program ends and display timing summary");
        System.out.println("\t\tm num1 num2 = display data memory from location num1 to num2");
        System.out.println("\t\tc = clear all registers, memory, and the program counter to 0");
        System.out.println("\t\tq = exit the program");
    }

    private void pS() {
        int lastIndex = this.ifid.size() - 1;

        System.out.println("\n\t\tpc\tif/id\tid/exe\texe/mem\tmem/wb");
        System.out.println("\t\t" + this.pcList.get(lastIndex) + "\t" +
                this.ifid.get(lastIndex).getInstName() + "\t" +
                this.idex.get(lastIndex).getInstName() + "\t" +
                this.exme.get(lastIndex).getInstName() + "\t" +
                this.mewb.get(lastIndex).getInstName() + "\n");
    }

    private void sS(int numSteps) {
        for (int i = 0; i < numSteps; i++)
            this.step();
    }

    private void cS() {
        this.initS();
        super.cE();
    }

    private boolean step() {
        int lastIndex = this.ifid.size() - 1;

        String idexName = this.idex.get(lastIndex).getInstName();
        String idexRt = this.idex.get(lastIndex).getRt();

        String ifidName = this.ifid.get(lastIndex).getInstName();
        String ifidRt = this.ifid.get(lastIndex).getRt();
        String ifidRs = this.ifid.get(lastIndex).getRs();

        if (this.squashFlag == 1)
            return squash3StepFlag1();

        if (this.squashFlag == 2)
            return squash3StepFlag2();

        if (idexName.equals("lw") && (!idexRt.equals("00000"))) {

            if ("add sub and or slt".contains(ifidName) && idexRt.equals(ifidRt) || idexRt.equals(ifidRs))
                return stallStep();

            if ("lw sw addi".contains(ifidName) && idexRt.equals(ifidRs))
                return stallStep();

            if ("sll".contains(ifidName) && idexRt.equals(ifidRt))
                return stallStep();

            return normalStep();

        } else if (("j jal jr".contains(ifidName))) {
            return squash1Step();
        } else if ("bne beq".contains(ifidName)) {
            return squash3Step();
        } else {
            return normalStep();
        }
    }

    private boolean normalStep() {
        if (sE(super.asmInst, super.labAdds)) {
            int lastIndex = this.ifid.size() - 1;

            this.pcList.add(this.pcList.get(lastIndex) + 1);
            this.mewb.add(this.exme.get(lastIndex));
            this.exme.add(this.idex.get(lastIndex));
            this.idex.add(this.ifid.get(lastIndex));
            this.ifid.add(super.currInst);

            this.numInsts += 1;
            this.cycles += 1;
            return true;
        } else {
            return false;
        }
    }

    private boolean stallStep() {
        int lastIndex = this.ifid.size() - 1;

        this.pcList.add(super.pc);
        this.mewb.add(this.exme.get(lastIndex));
        this.exme.add(this.idex.get(lastIndex));
        this.idex.add(this.staInst);
        this.ifid.add(this.ifid.get(lastIndex));

        this.cycles += 1;
        return true;
    }

    private boolean squash1Step() {
        int lastIndex = this.ifid.size() - 1;

        this.pcList.add(super.pc);
        this.mewb.add(this.exme.get(lastIndex));
        this.exme.add(this.idex.get(lastIndex));
        this.idex.add(this.ifid.get(lastIndex));
        this.ifid.add(this.squInst);

        this.cycles += 1;
        return true;
    }

    private boolean squash3Step() {
        int lastIndex = this.ifid.size() - 1;

        if (this.pcList.get(lastIndex - 1) == (super.pc - 1)) {
            sE(super.asmInst, super.labAdds);

            this.pcList.add(super.pc);
            this.mewb.add(this.exme.get(lastIndex));
            this.exme.add(this.idex.get(lastIndex));
            this.idex.add(this.ifid.get(lastIndex));
            this.ifid.add(super.currInst);

            this.numInsts += 1;
            this.cycles += 1;
        } else {
            int pcIdx = this.pcList.get(lastIndex - 1);
            Inst inst = super.binInst.get(pcIdx + 1);

            this.pcList.add(pcIdx + 2);
            this.mewb.add(this.exme.get(lastIndex));
            this.exme.add(this.idex.get(lastIndex));
            this.idex.add(this.ifid.get(lastIndex));
            this.ifid.add(inst);

            this.squashFlag = 1;
            this.cycles += 1;
        }
        return true;
    }

    private boolean squash3StepFlag1() {
        int lastIndex = this.ifid.size() - 1;
        int pcIdx = this.pcList.get(lastIndex - 1);
        Inst inst = super.binInst.get(pcIdx + 1);

        this.pcList.add(pcIdx + 2);
        this.mewb.add(this.exme.get(lastIndex));
        this.exme.add(this.idex.get(lastIndex));
        this.idex.add(this.ifid.get(lastIndex));
        this.ifid.add(inst);

        this.squashFlag = 2;
        this.cycles += 1;
        return true;
    }

    private boolean squash3StepFlag2() {
        int lastIndex = this.ifid.size() - 1;
        int pcIdx = this.pcList.get(lastIndex - 1);

        this.pcList.add(pcIdx + 2);
        this.mewb.add(this.exme.get(lastIndex));
        this.exme.add(this.squInst);
        this.idex.add(this.squInst);
        this.ifid.add(this.squInst);

        this.squashFlag = 0;
        this.cycles += 1;
        return true;
    }

    private boolean nextPrint(String[] data) {
        if (Objects.equals(data[0], "h"))
            this.hS();

        if (Objects.equals(data[0], "c"))
            this.cS();

        if (Objects.equals(data[0], "d"))
            super.dE();

        if (Objects.equals(data[0], "p"))
            this.pS();

        if (Objects.equals(data[0], "q"))
            return false;

        if (Objects.equals(data[0], "m"))
            super.mE(Integer.parseInt(data[1]), Integer.parseInt(data[2]));

        if (Objects.equals(data[0], "r")) {
            while (this.step())
                ;
            System.out.println("\n\t\tProgram complete");
            System.out.format("\t\tCPI = %.3f\tCycles = %d\tInstructions = %d\n\n",
                    (double) this.cycles / this.numInsts, this.cycles,
                    this.numInsts);
        }

        if (Objects.equals(data[0], "s")) {
            if (data.length == 2)
                this.sS(Integer.parseInt(data[1]));
            else
                this.sS(1);
            this.pS();
        }
        return true;
    }

    // lab 4 method
    public void scriptModeS(String scriptFile) {
        try {
            File myObj = new File(scriptFile);
            Scanner myReader = new Scanner(myObj);
            boolean flag = true;
            while (flag && myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(" ");

                // display mips>
                if (data.length == 1)
                    System.out.format("mips> %s\n", data[0]);
                if (data.length == 2)
                    System.out.format("mips> %s %s\n", data[0], data[1]);
                if (data.length == 3)
                    System.out.format("mips> %s %s %s\n", data[0], data[1], data[2]);

                flag = nextPrint(data);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Script File doesn't exist.");
            e.printStackTrace();
        }
    }

    public void interactiveModeS() {
        boolean flag = true;
        Scanner myObj = new Scanner(System.in);

        while (flag) {
            System.out.print("mips> ");
            String[] input = myObj.nextLine().split(" ");
            flag = nextPrint(input);
        }
        myObj.close();
    }

}