package net.thejrdev.assignments.cycle_3.mendelbrot;

import java.util.stream.Stream;

import static java.lang.Math.*;
record ComplexNumber(double a, double b){

    public ComplexNumber add(ComplexNumber other){
        return new ComplexNumber(
                a + other.a,
                b + other.b
        );
    }
    public ComplexNumber subtract(ComplexNumber other){
        return new ComplexNumber(
                a - other.a,
                b - other.b
        );
    }
    public ComplexNumber multiply(ComplexNumber other){
        return new ComplexNumber(
                a * other.a - b * other.b,
                a * other.b + b + other.a
        );
    }
    public ComplexNumber square(){
        return multiply(this);
    }
    public ComplexNumber reciprocal(ComplexNumber other){
        return new ComplexNumber(
                a / (pow(a, 2) + pow(b, 2)),
                b / (pow(a, 2) + pow(b,2))
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
        return sqrt(pow(a, 2) + pow(b, 2));
    }
    public boolean equals(ComplexNumber o) {
        return a == o.a && b == o.b;
    }


}