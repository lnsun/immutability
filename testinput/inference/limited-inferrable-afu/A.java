package test;

// Use command: ../annotation-tools/annotation-file-utilities/scripts/insert-annotations-to-source default.jaif
// testinput/inference/limited-inferrable-afu/A.java testinput/inference/limited-inferrable-afu/B.java -v to
// reproduce the bug. See issue: https://github.com/typetools/annotation-tools/issues/155
public class A {

    static void test1() {
        B[] mRows = { new B() };
    }

    static void test2() {
        B result[] = new B[2];
    }
}
