lab5:
	#replace the following command with your compilation command
	javac *.java

test1:
	java lab5 lab4_fib20.asm lab5.script 2 > out.txt
	diff -w -B out.txt lab5_ghr2.output

test2:
	java lab5 lab4_fib20.asm lab5.script 4 > out.txt
	diff -w -B out.txt lab5_ghr4.output

test3:
	java lab5 lab4_fib20.asm lab5.script 8 > out.txt
	diff -w -B out.txt lab5_ghr8.output

