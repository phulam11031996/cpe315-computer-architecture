public class Cache5 {

    private int[] tagArray1 = new int[128]; // (2KB / 1W) / 4
    private int[] tagArray2 = new int[128]; // (2KB / 1W) / 4
    private int[] tagArray3 = new int[128]; // (2KB / 1W) / 4
    private int[] tagArray4 = new int[128]; // (2KB / 1W) / 4

    private int[] lineArray1 = new int[128];
    private int[] lineArray2 = new int[128];
    private int[] lineArray3 = new int[128];
    private int[] lineArray4 = new int[128];

    private Integer hit = 0;

    public int getHit() {
        return this.hit;
    }

    public void process(int address, int lineNumber) {
        // tag = 32 - 10
        // idx = 9 - 2
        // off = 0 - 1

        int index = lab6.bitExtraction(address, 7, 2);
        int flag = lab6.bitExtraction(address, 23, 9);

        if (tagArray1[index] == flag) {
            this.hit++;
            lineArray1[index] = lineNumber;
        }

        else if (tagArray2[index] == flag) {
            this.hit++;
            lineArray2[index] = lineNumber;
        }

        else if (tagArray3[index] == flag) {
            this.hit++;
            lineArray3[index] = lineNumber;
        }

        else if (tagArray4[index] == flag) {
            this.hit++;
            lineArray4[index] = lineNumber;
        }

        else if (tagArray1[index] == 0) {
            tagArray1[index] = flag;
            lineArray1[index] = lineNumber;
        }

        else if (tagArray2[index] == 0) {
            tagArray2[index] = flag;
            lineArray2[index] = lineNumber;
        }

        else if (tagArray3[index] == 0) {
            tagArray3[index] = flag;
            lineArray3[index] = lineNumber;
        }

        else if (tagArray4[index] == 0) {
            tagArray4[index] = flag;
            lineArray4[index] = lineNumber;
        }

        else if (lineArray1[index] < lineArray2[index]
                && lineArray1[index] < lineArray3[index]
                && lineArray1[index] < lineArray4[index]) {
            tagArray1[index] = flag;
            lineArray1[index] = lineNumber;
        }

        else if (lineArray2[index] < lineArray1[index]
                && lineArray2[index] < lineArray3[index]
                && lineArray2[index] < lineArray4[index]) {
            tagArray2[index] = flag;
            lineArray2[index] = lineNumber;
        }

        else if (lineArray3[index] < lineArray1[index]
                && lineArray3[index] < lineArray2[index]
                && lineArray3[index] < lineArray4[index]) {
            tagArray3[index] = flag;
            lineArray3[index] = lineNumber;
        }

        else if (lineArray4[index] < lineArray1[index]
                && lineArray4[index] < lineArray2[index]
                && lineArray4[index] < lineArray3[index]) {
            tagArray4[index] = flag;
            lineArray4[index] = lineNumber;
        }
    }

    public void display(int total) {
        System.out.println("Cache #5");
        System.out.println("Cache size: 2048B	Associativity: 4	Block size: 1");
        System.out.format("Hits: %d	Hit Rate: %.2f%%\n", this.hit, this.hit * 100.0 / total);
        System.out.println("---------------------------");
    }
}
