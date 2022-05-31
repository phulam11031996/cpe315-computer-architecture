public class Cache7 {

    private int[] tagArray = new int[1024]; // 2KB / 1W
    private Integer hit = 0;

    public int getHit() {
        return this.hit;
    }

    public void process(int address) {
        // tag = 32 - 12
        // idx = 11 - 2
        // off = 0 - 1

        int index = lab6.bitExtraction(address, 10, 2);
        int flag = lab6.bitExtraction(address, 22, 10);

        if (tagArray[index] == flag)
            this.hit++;

        this.tagArray[index] = flag;
    }

    public void display(int total) {
        System.out.println("Cache #7");
        System.out.println("Cache size: 4096B	Associativity: 1	Block size: 1");
        System.out.format("Hits: %d	Hit Rate: %.2f%%\n", this.hit, this.hit * 100.0 / total);
        System.out.println("---------------------------");
    }
}
