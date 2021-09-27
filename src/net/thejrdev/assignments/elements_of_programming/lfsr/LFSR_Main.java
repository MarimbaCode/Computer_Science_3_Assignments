package net.thejrdev.assignments.elements_of_programming.lfsr;

public class LFSR_Main {

    public static void main(String[] args) {

        LFSR lfsr = new LFSR(0b11001001001, 11, 8);

        System.out.println(Long.toBinaryString(lfsr.getNumber(37)));
        System.out.println();
        System.out.println("1110110111001011010111001100010111111");

    }
}
//1001001001101111110110110100100100100

//1110110111001011010111001100010111111