public class Cache2 {

    private int[] tagArray = new int[256]; // 2KB / 2W
    private Integer hit = 0;

    public int getHit() {
        return this.hit;
    }

    public void process(int address) {
        // tag = 32 - 12
        // idx = 11 - 3
        // woff = 2
        // boff = 0 - 1

        int index = lab6.bitExtraction(address, 8, 3);
        int flag = lab6.bitExtraction(address, 21, 11);

        if (tagArray[index] == flag)
            this.hit++;

        this.tagArray[index] = flag;
    }

    public void display(int total) {
        System.out.println("Cache #2");
        System.out.println("Cache size: 2048B	Associativity: 1	Block size: 2");
        System.out.format("Hits: %d	Hit Rate: %.2f%%\n", this.hit, this.hit * 100.0 / total);
        System.out.println("---------------------------");
    }
}
