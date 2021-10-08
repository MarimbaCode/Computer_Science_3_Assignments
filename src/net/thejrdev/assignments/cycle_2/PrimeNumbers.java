package net.thejrdev.assignments.cycle_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeNumbers {

    List<Long> primes;
    long max;

    public PrimeNumbers(long max){
        this.max = max;
    }

    public List<Long> getPrimes(){

        if (primes == null){
            generatePrimes();
        }
        return primes;
    }

    protected void generatePrimes(){

        primes = new ArrayList<>();

        for (long i = 1; i < max; i++) {
            primes.add(i);
        }

        int index = 1;
        while(primes.get(index) < Math.sqrt(max)){
            filter(primes, primes.get(index));
            index++;
        }

    }

    protected void filter(List<Long> numbers, long by){

        for (long i = by; i * by <= max; i++) {
            numbers.remove(i * by);
        }
    }

    public static void main(String[] args) {

        PrimeNumbers numbers = new PrimeNumbers(100000);

        System.out.println(Arrays.deepToString(numbers.getPrimes().toArray()));

    }



}

/*




1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97
   2  3  5  7  11  13  17  19  23  29  31	37	41	43	47	53	59	61	67	71	73	79	83	89	97
 */