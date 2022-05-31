public class Cache4 {

    private int[] tagArray1 = new int[256]; // (2KB / 1W) / 2
    private int[] tagArray2 = new int[256]; // (2KB / 1W) / 2

    private int[] lineArray1 = new int[256];
    private int[] lineArray2 = new int[256];

    private Integer hit = 0;

    public int getHit() {
        return this.hit;
    }

    public void process(int address, int lineNumber) {
        // tag = 32 - 11
        // idx = 10 - 2
        // off = 0 - 1

        int index = lab6.bitExtraction(address, 8, 2);
        int flag = lab6.bitExtraction(address, 22, 10);

        if (tagArray1[index] == flag) {
            this.hit++;
            lineArray1[index] = lineNumber;
        }

        else if (tagArray2[index] == flag) {
            this.hit++;
            lineArray2[index] = lineNumber;
        }

        else if (tagArray1[index] == 0) {
            tagArray1[index] = flag;
            lineArray1[index] = lineNumber;
        }

        else if (tagArray2[index] == 0) {
            tagArray2[index] = flag;
            lineArray2[index] = lineNumber;
        }

        else if (lineArray1[index] < lineArray2[index]) {
            tagArray1[index] = flag;
            lineArray1[index] = lineNumber;
        } else {
            tagArray2[index] = flag;
            lineArray2[index] = lineNumber;
        }
    }

    public void display(int total) {
        System.out.println("Cache #4");
        System.out.println("Cache size: 2048B	Associativity: 2	Block size: 1");
        System.out.format("Hits: %d	Hit Rate: %.2f%%\n", this.hit, this.hit * 100.0 / total);
        System.out.println("---------------------------");
    }
}
