import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class Emulator extends Parser {
    // data
    protected int pc;
    protected int[] dataMem;
    protected Inst currInst = null;
    BranchPredictor br;

    protected HashMap<String, Integer> reg = new HashMap<>();

    protected Predictor predictor;

    // constructor
<<<<<<< HEAD
    public Emulator(String filename, int ghrBits) {
        super(filename);
        this.init();
        this.predictor = new Predictor(ghrBits);
=======
    public Emulator(String filename,int bits) {
        super(filename);
        this.init();
        this.br = new BranchPredictor(bits);
>>>>>>> refs/remotes/origin/main
    }

    protected void init() {
        this.pc = 0;
        this.dataMem = new int[8192];
        reg.put("$0", 0);
        reg.put("$v0", 0);
        reg.put("$v1", 0);
        reg.put("$a0", 0);
        reg.put("$a1", 0);
        reg.put("$a2", 0);
        reg.put("$a3", 0);
        reg.put("$t0", 0);
        reg.put("$t1", 0);
        reg.put("$t2", 0);
        reg.put("$t3", 0);
        reg.put("$t4", 0);
        reg.put("$t5", 0);
        reg.put("$t6", 0);
        reg.put("$t7", 0);
        reg.put("$s0", 0);
        reg.put("$s1", 0);
        reg.put("$s2", 0);
        reg.put("$s3", 0);
        reg.put("$s4", 0);
        reg.put("$s5", 0);
        reg.put("$s6", 0);
        reg.put("$s7", 0);
        reg.put("$t8", 0);
        reg.put("$t9", 0);
        reg.put("$sp", 0);
        reg.put("$ra", 0);
    }

    private void clearFunctCallReg() {
        this.reg.put("v0", 0);
        this.reg.put("v1", 0);
        this.reg.put("a0", 0);
        this.reg.put("a1", 0);
        this.reg.put("a2", 0);
        this.reg.put("a3", 0);
        this.reg.put("t0", 0);
        this.reg.put("t1", 0);
        this.reg.put("t2", 0);
        this.reg.put("t3", 0);
        this.reg.put("t4", 0);
        this.reg.put("t5", 0);
        this.reg.put("t6", 0);
        this.reg.put("t7", 0);
        this.reg.put("t8", 0);
        this.reg.put("t9", 0);
    }

    // secure method
    private void hE() {
        System.out.println("\t\th = show help");
        System.out.println("\t\td = dump register state");
        System.out.println("\t\ts = single step through the program (i.e. execute 1 instruction and stop)");
        System.out.println("\t\ts num = step through num instructions of the program");
        System.out.println("\t\tr = run until the program ends");
        System.out.println("\t\tm num1 num2 = display data memory from location num1 to num2");
        System.out.println("\t\tb = output the branch predictor accuracy.");
        System.out.println("\t\tc = clear all registers, memory, and the program counter to 0");
        System.out.println("\t\tq = exit the program");
    }

    protected void dE() {
        System.out.format("\t\tpc = %d\n", this.pc);
        System.out.format("\t\t$0 = %d\t$v0 = %d\t$v1 = %d\t$a0 = %d\n", reg.get("$0"), reg.get("$v0"), reg.get("$v1"),
                reg.get("$a0"));
        System.out.format("\t\t$a1 = %d\t$a2 = %d\t$a3 = %d\t$t0 = %d\n", reg.get("$a1"), reg.get("$a2"),
                reg.get("$a3"), reg.get("$t0"));
        System.out.format("\t\t$t1 = %d\t$t2 = %d\t$t3 = %d\t$t4 = %d\n", reg.get("$t1"), reg.get("$t2"),
                reg.get("$t3"), reg.get("$t4"));
        System.out.format("\t\t$t5 = %d\t$t6 = %d\t$t7 = %d\t$s0 = %d\n", reg.get("$t5"), reg.get("$t6"),
                reg.get("$t7"), reg.get("$s0"));
        System.out.format("\t\t$s1 = %d\t$s2 = %d\t$s3 = %d\t$s4 = %d\n", reg.get("$s1"), reg.get("$s2"),
                reg.get("$s3"), reg.get("$s4"));
        System.out.format("\t\t$s5 = %d\t$s6 = %d\t$s7 = %d\t$t8 = %d\n", reg.get("$s5"), reg.get("$s6"),
                reg.get("$s7"), reg.get("$t8"));
        System.out.format("\t\t$t9 = %d\t$sp = %d\t$ra = %d\n", reg.get("$t9"), reg.get("$sp"), reg.get("$ra"));
    }

    protected void mE(int num1, int num2) {
        for (int i = num1; i != num2 + 1; i++) {
            System.out.format("\t\t[%d] = %d\n", i, this.dataMem[i]);
        }
    }

    protected void cE() {
        this.init();
        System.out.println("\t\tSimulator reset");
    }

    protected boolean sE(List<String> asmInst, HashMap<String, Integer> labAdds) {
        if (this.pc <= asmInst.size() - 1) {
            String currInst[] = asmInst.get(this.pc).split(" ");
            this.currInst = binInst.get(this.pc);

            switch (currInst[0]) {
                case "addi":
                    int tempInt = Integer.parseInt(currInst[3]) + this.reg.get(currInst[2]);
                    this.reg.put(currInst[1], tempInt);
                    this.pc++;
                    break;
                case "add":
                    int tempInt1 = this.reg.get(currInst[3]) + this.reg.get(currInst[2]);
                    this.reg.put(currInst[1], tempInt1);
                    this.pc++;
                    break;
                case "sw":
                    int index = Integer.parseInt(currInst[2]) + this.reg.get(currInst[3]);
                    this.dataMem[index] = this.reg.get(currInst[1]);
                    this.pc++;
                    break;
                case "bne":
<<<<<<< HEAD
                    if (!Objects.equals(this.reg.get(currInst[1]), this.reg.get(currInst[2]))) {
                        this.pc = labAdds.get(currInst[3]);
                        
                        if (this.predictor.getPre() == 1)
                            this.predictor.increWrongPre();
                        this.predictor.updatePre(1);
                    } else {
                        this.pc++;

                        if (this.predictor.getPre() == 0)
                            this.predictor.increWrongPre();
                        this.predictor.updatePre(0);
                    }
                    break;
                case "beq":
                    if (Objects.equals(this.reg.get(currInst[1]), this.reg.get(currInst[2]))) {
                        this.pc = labAdds.get(currInst[3]);

                        if (this.predictor.getPre() == 1)
                            this.predictor.increWrongPre();
                        this.predictor.updatePre(1);
                    } else {
                        this.pc++;
                        
                        if (this.predictor.getPre() == 0)
                            this.predictor.increWrongPre();
                        this.predictor.updatePre(0);
=======
                    int taken = this.br.predict();
                    if (!Objects.equals(this.reg.get(currInst[1]), this.reg.get(currInst[2]))) {
                        this.pc = labAdds.get(currInst[3]);
                        this.br.learn(taken,1);
                    }
                    else {
                        this.pc++;
                        this.br.learn(taken,0);
                    }
                    break;
                case "beq":
                    int taken2 = this.br.predict();
                    if (Objects.equals(this.reg.get(currInst[1]), this.reg.get(currInst[2]))) {
                        this.pc = labAdds.get(currInst[3]);
                        this.br.learn(taken2,1);
                    }
                    else {
                        this.pc++;
                        this.br.learn(taken2,0);
>>>>>>> refs/remotes/origin/main
                    }
                    break;
                case "jal":
                    this.reg.put("$ra", this.pc + 1);
                    this.pc = labAdds.get(currInst[1]);
                    this.clearFunctCallReg();
                    break;
                case "slt":
                    if (this.reg.get(currInst[2]) < this.reg.get(currInst[3]))
                        this.reg.put(currInst[1], 1);
                    else
                        this.reg.put(currInst[1], 0);
                    this.pc++;
                    break;
                case "lw":
                    int tempInt2 = this.dataMem[this.reg.get(currInst[3]) + Integer.parseInt(currInst[2])];
                    this.reg.put(currInst[1], tempInt2);
                    this.pc++;
                    break;
                case "jr":
                    this.pc = this.reg.get(currInst[1]);
                    break;
                case "j":
                    this.pc = labAdds.get(currInst[1]);
                    break;
                case "and":
                    int tempInt3 = this.reg.get(currInst[2]) & this.reg.get(currInst[3]);
                    this.reg.put(currInst[1], tempInt3);
                    this.pc++;
                    break;
                case "or":
                    int tempInt4 = this.reg.get(currInst[2]) | this.reg.get(currInst[3]);
                    this.reg.put(currInst[1], tempInt4);
                    this.pc++;
                    break;
                case "sll":
                    int tempInt5 = this.reg.get(currInst[2]) << Integer.parseInt(currInst[3]);
                    this.reg.put(currInst[1], tempInt5);
                    this.pc++;
                    break;
                case "sub":
                    int tempInt6 = this.reg.get(currInst[3]) - this.reg.get(currInst[2]);
                    this.reg.put(currInst[1], tempInt6);
                    this.pc++;
                    break;
                default:
                    break;
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean nextPrint(String[] data) {
        // display info after mips>
        if (Objects.equals(data[0], "b"))
            this.predictor.displayStat();

        if (Objects.equals(data[0], "c"))
            this.cE();

        if (Objects.equals(data[0], "d"))
            this.dE();

        if (Objects.equals(data[0], "h"))
            this.hE();

        if (Objects.equals(data[0], "m"))
            this.mE(Integer.parseInt(data[1]), Integer.parseInt(data[2]));

        if (Objects.equals(data[0], "q"))
            return false;

        if (Objects.equals(data[0], "r"))
            while (this.sE(super.asmInst, super.labAdds))
                ;

        if (Objects.equals(data[0], "s")) {
            if (data.length == 2) {
                for (int i = 0; i != Integer.parseInt(data[1]); i++)
                    this.sE(super.asmInst, super.labAdds);
                System.out.println("\t\t" + data[1] + " instruction(s) executed");
            } else {
                this.sE(super.asmInst, super.labAdds);
                System.out.println("\t\t1 instruction(s) executed");
            }
        }
        return true;
    }

    // lab 3 method
    public void scriptModeE(String scriptFile) {
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

    public void interactiveModeE() {
        boolean flag = true;
        Scanner myObj = new Scanner(System.in);
        while (flag) {
            System.out.print("mips> ");
            String[] input = myObj.nextLine().split(" ");
            flag = nextPrint(input);
        }
        myObj.close();
    }

    //make script mode for lab 5

}
