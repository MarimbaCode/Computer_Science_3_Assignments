package net.thejrdev.assignments.cycle_one.lfsr;

public class LFSR {

    protected int state;
    protected int length;
    protected int tap;


    public LFSR(int initialState, int length, int tap){
        this.state = initialState;
        this.length = length;
        this.tap = tap;
    }

    private int shift(){
        int bit = getBit(length-1);
        int tapBit = getBit(tap);
        state <<= 1;
        state &= (1<<length) -1;
        state |= bit^tapBit;
        return bit ^ tapBit;
    }

    private int getBit(int loc){
        return (state >> loc) & 1;
    }

    public long getByte(){
        return getNumber(8);
    }

    public long getNumber(int bits){
        long res = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bits; i++) {
            System.out.println(Integer.toBinaryString(state));
            res <<= 1;
            int bit = shift();
            res += bit;
            sb.append(bit);
        }
        System.out.println(sb.toString());
        return res;
    }

}
