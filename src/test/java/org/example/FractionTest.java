package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FractionTest {
    @Test
    void denominatorNotZero() {
        assertThrows(ArithmeticException.class, () -> new Fraction(1, 0));
    }

    @Test
    void shorten() {
        Fraction fraction = new Fraction(12, 4);
        Fraction shortened = fraction.shortened();
        assertEquals(3, shortened.getNumerator());
        assertEquals(1, shortened.getDenominator());
    }

    @Test
    void added() {
        Fraction summand1 = new Fraction(5, 4);
        Fraction summand2 = new Fraction(6, 8);
        Fraction sum = summand1.added(summand2);
        assertEquals(2, sum.getNumerator());
        assertEquals(1, sum.getDenominator());
    }

    @Test
    void subtracted() {
        Fraction minuend = new Fraction(5, 4);
        Fraction subtrahend = new Fraction(2, 8);
        Fraction difference = minuend.subtracted(subtrahend);
        assertEquals(1, difference.getNumerator());
        assertEquals(1, difference.getDenominator());
    }

    @Test
    void multiplied() {
        Fraction factor1 = new Fraction(16, 4);
        Fraction factor2 = new Fraction(1, 2);
        Fraction product = factor1.multiplied(factor2);
        assertEquals(2, product.getNumerator());
        assertEquals(1, product.getDenominator());
    }

    @Test
    void divided() {
        Fraction dividend = new Fraction(16, 4);
        Fraction divisor = new Fraction(1, 2);
        Fraction quotient = dividend.divided(divisor);
        assertEquals(8, quotient.getNumerator());
        assertEquals(1, quotient.getDenominator());
    }
}