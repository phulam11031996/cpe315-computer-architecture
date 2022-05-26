import java.util.ArrayList;
import java.util.List;

public class BranchPredictor {

    int GHR = 0;
    List<Integer> table = new ArrayList<Integer>();

    public BranchPredictor(int bits) {
        for (int i=0;i<Math.pow(2,bits);i++)
            table.add(0);
    }

    public int predict() {
        return table.get(this.GHR) > 2 ? 1 : 0;
    }

    private int update(int start,int predicted, int actual) {
        if (predicted==actual) {
            if (start == 2)
                start += 1;
            else if (start == 1)
                start -= 1;
        }
        else {
            if (start == 2)
                start -= 1;
            else if (start == 0)
                start+=1;
        }
        return start;
    }

    public void learn(int predicted, int actual) {
        this.table.add(this.GHR,update(this.table.get(this.GHR),predicted,actual));
        this.GHR = this.GHR << 1 + actual;
    }



}


