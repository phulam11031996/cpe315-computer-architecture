import java.lang.Math;

public class Predictor {
    
    // data
    private int totalPrediction = 0;
    private int wrongPrediction = 0;
    private StringBuilder ghr = new StringBuilder();
    private int[] preTable;

    // constructor
    public Predictor(int ghrBits) {
        if (ghrBits == 2)
            this.ghr.append("00");

        else if (ghrBits == 4)
            this.ghr.append("0000");

        else if (ghrBits == 8)
            this.ghr.append("00000000");

        else
            throw new IllegalArgumentException("ghr must be 2, 4, or 8.");

        this.preTable = new int[(int) Math.pow(2, ghrBits)];
    }

    // setter and getter
    protected void increWrongPre() {
        this.wrongPrediction++;
    }

    protected int getIdx() {
        return Integer.parseInt(this.ghr.toString(), 2);
    }

    // private method
    protected int getPre() {
        if (this.preTable[this.getIdx()] <= 1)
            return 1; // taken

        return 0; // not taken
    }

    protected  void updatePre(int correct) {
        this.totalPrediction += 1;

        if (correct == 1) {
            if (preTable[this.getIdx()] < 3)
                this.preTable[this.getIdx()]++;
        } else {
            if (preTable[this.getIdx()] > 0)
                this.preTable[this.getIdx()]--;
        }

        this.ghr.append(correct);
        this.ghr.deleteCharAt(0);
    }

    // normal method
    public void displayStat() {
        int total = this.totalPrediction;
        int correct = this.totalPrediction - this.wrongPrediction;
        double percentage = (double) correct / total * 100;
        System.out.printf("\n\t\taccuracy %.2f%% (%d correct predictions, %d predictions)\n\n",
                percentage, correct, total);
    }

}
