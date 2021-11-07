package net.thejrdev.assignments.cycle_3.mendelbrot;

import static java.lang.Math.*;
record ComplexNumber(double re, double im){

    public ComplexNumber add(ComplexNumber other){
        return new ComplexNumber(
                re + other.re,
                im + other.im
        );
    }
    public ComplexNumber subtract(ComplexNumber other){
        return new ComplexNumber(
                re - other.re,
                im - other.im
        );
    }
    public ComplexNumber multiply(ComplexNumber other){
        return new ComplexNumber(
                re * other.re - im * other.im,
                re * other.im + im * other.re
        );
    }
    public ComplexNumber square(){
        return multiply(this);
    }
    public ComplexNumber reciprocal(ComplexNumber other){
        return new ComplexNumber(
                re / (pow(re, 2) + pow(im, 2)),
                im / (pow(re, 2) + pow(im,2))
        );
    }
    public ComplexNumber divide(ComplexNumber other){
        return multiply(reciprocal(other));
    }
    public ComplexNumber power(int exp){
        ComplexNumber cn = this;
        for (int i = 0; i < exp; i++) {
            cn = multiply(this);
        }
        return cn;
    }
    public double magnitude(){
        return Math.hypot(re, im);
    }
    public boolean equals(ComplexNumber o) {
        return re == o.re && im == o.im;
    }


}