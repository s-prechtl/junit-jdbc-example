package org.example;

import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Fraction {
    private int numerator, denominator;

    public Fraction(int numerator, int denominator) throws ArithmeticException {
        this.setNumerator(numerator);
        this.setDenominator(denominator);
    }

    public Fraction shortened() {
        BigInteger numerator = BigInteger.valueOf(this.numerator);
        BigInteger denominator = BigInteger.valueOf(this.denominator);
        int gcd = numerator.gcd(denominator).intValue();
        return new Fraction(this.numerator / gcd,this.denominator / gcd);
    }

    public void setDenominator(int denominator) throws ArithmeticException {
        if (denominator == 0) {
            throw new ArithmeticException("The denominator of a fraction cannot be zero");
        }
        this.denominator = denominator;
    }

    public Fraction added(Fraction other) {
        int commonDenominator = this.denominator * other.denominator;
        int summand1 = this.numerator * other.denominator;
        int summand2 = other.numerator * this.denominator;
        Fraction sum = new Fraction(summand1 + summand2, commonDenominator);
        return sum.shortened();
    }

    public Fraction subtracted(Fraction other) {
        Fraction subtrahend = new Fraction(other.numerator * -1, other.denominator);
        return this.added(subtrahend);
    }

    public Fraction multiplied(Fraction other) {
        Fraction product = new Fraction(this.numerator * other.numerator, this.denominator * other.denominator);
        return product.shortened();
    }

    public Fraction divided(Fraction other) {
        Fraction divisor = new Fraction(other.denominator, other.numerator);
        return multiplied(divisor);
    }
}
