import java.util.LinkedList;
import java.util.List;

public class Simulator extends Emulator {

    private int cycles = 0;
    private int numInsts = 0;
    private double cpi = 0.0;
    private List<Integer> pc = new LinkedList<Integer>();
    private List<Inst> ifid = new LinkedList<Inst>();
    private List<Inst> idexe = new LinkedList<Inst>();
    private List<Inst> exemem = new LinkedList<Inst>();
    private List<Inst> memwb = new LinkedList<Inst>();

    private Inst empInst = new Inst("empty", null, null, null, null, null, null, null, null);
    private Inst stallInst = new Inst("stall", null, null, null, null, null, null, null, null);
    private Inst squashInst = new Inst("squash", null, null, null, null, null, null, null, null);

    public Simulator(String fileName) {
        super(fileName);
        this.pc.add(0);
        this.ifid.add(empInst);
        this.idexe.add(empInst);
        this.exemem.add(empInst);
        this.memwb.add(empInst);
    }

    public boolean step() {
        this.cycles += 1;
        int lastIndex = this.ifid.size() - 1;
        String idexeName = this.idexe.get(lastIndex).getInstName();
        String idexeRt = this.idexe.get(lastIndex).getRt();

        String ifidRt = this.ifid.get(lastIndex).getRt();
        String ifidRs = this.ifid.get(lastIndex).getRs();

        
        // check for what kind of step
        if (idexeName.equals("lw")) {
            if (idexeRt.equals(ifidRt) || idexeRt.equals(ifidRs))
                return stallStep();
            else
                return normalStep();
        } else {
            return normalStep();
        }
    }

    public boolean normalStep() {
        if (super.progInst.size() != 0) {
            int lastIndex = this.ifid.size() - 1;

            this.pc.add(this.pc.get(lastIndex) + 1);
            this.memwb.add(this.exemem.get(lastIndex));
            this.exemem.add(this.idexe.get(lastIndex));
            this.idexe.add(this.ifid.get(lastIndex));
            this.ifid.add(super.progInst.get(0));

            super.progInst.remove(0);
            this.numInsts += 1;
            return true;
        } else {
            return false;
        }
    }

    public boolean stallStep() {
        int lastIndex = this.ifid.size() - 1;

        this.memwb.add(this.exemem.get(lastIndex));
        this.exemem.add(this.idexe.get(lastIndex));
        this.idexe.add(this.stallInst);
        this.ifid.add(this.ifid.get(lastIndex));
        this.pc.add(this.pc.get(lastIndex));

        return true;
    }

    public boolean squash3Step() {
        return true;
    }

    public boolean squash1Step() {
        return true;
    }

    public void displayPipeLine(int index) {
        System.out.println("pc\tif/id\tid/exe\texe/mem\tmem/wb");
        System.out.println(this.pc.get(index) + "\t" +
                this.ifid.get(index).getInstName() + "\t" +
                this.idexe.get(index).getInstName() + "\t" +
                this.exemem.get(index).getInstName() + "\t" +
                this.memwb.get(index).getInstName() + "\n");
    }

    public void help() {

    }

    public void displayProgInst() {
        for (Inst inst : super.progInst) {
            System.out.println(inst);
        }
    }
}