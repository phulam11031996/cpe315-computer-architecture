public class CurrState {
    // data
    int pc = 0;
    int[] dataMem = new int[8192];
    int $0 = 0;
    int $v0 = 0;
    int $v1 = 0;
    int $a0 = 0;
    int $a1 = 0;
    int $a2 = 0;
    int $a3 = 0;
    int $t0 = 0;
    int $t1 = 0;
    int $t2 = 0;
    int $t3 = 0;
    int $t4 = 0;
    int $t5 = 0;
    int $t6 = 0;
    int $t7 = 0;
    int $s0 = 0;
    int $s1 = 0;
    int $s2 = 0;
    int $s3 = 0;
    int $s4 = 0;
    int $s5 = 0;
    int $s6 = 0;
    int $s7 = 0;
    int $t8 = 0;
    int $t9 = 0;
    int $sp = 0;
    int $ra = 0;

    // constructor
    public CurrState() {
    }

    //    h = show help
    public void h() {

    }

    //    d = dump register state
    public void d() {

    }

    //    s = single step through the program (i.e. execute 1 instruction and stop)
    //    s num = step through num instructions of the program
    public void s() {

    }


    //    r = run until the program ends
    public void r() {

    }

    //    m num1 num2 = display data memory from location num1 to num2
    public void m() {

    }

    //    c = clear all registers, memory, and the program counter to 0
    public void c() {

    }

    //    q = exit the program
    public void q() {

    }
}
