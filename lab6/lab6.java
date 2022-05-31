// Section: 1 ( 8:10 AM in the morning )
// Names: Phu & Rohit

public class lab6 {
    public static int bitExtraction(int number, int k, int p) {

        return (((1 << k) - 1) & (number >>> p));
    }

    public static void main(String[] args) {
        Parser file = new Parser(args[0]);

        Cache1 cache1 = new Cache1();
        Cache2 cache2 = new Cache2();
        Cache3 cache3 = new Cache3();
        Cache4 cache4 = new Cache4();
        Cache5 cache5 = new Cache5();
        Cache6 cache6 = new Cache6();
        Cache7 cache7 = new Cache7();

        int counter = 0;
        for (int number : file.getLines()) {
            cache1.process(number);
            cache2.process(number);
            cache3.process(number);
            cache4.process(number, counter);
            cache5.process(number, counter);
            cache6.process(number, counter);
            cache7.process(number);
            counter++;
        }

        cache1.display(counter);
        cache2.display(counter);
        cache3.display(counter);
        cache4.display(counter);
        cache5.display(counter);
        cache6.display(counter);
        cache7.display(counter);
    }
}
