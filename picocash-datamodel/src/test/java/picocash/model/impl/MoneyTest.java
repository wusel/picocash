package picocash.model.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wusel
 */
public class MoneyTest {

    public MoneyTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void initializerTest() {
        Money expectedResult = new Money(10034);
        Money value = new Money(100, 34);
        assertEquals(expectedResult, value);
        value = new Money(10034);
        assertEquals(expectedResult, value);
        value = new Money("100,34");
        assertEquals(expectedResult, value);
    }

    @Test
    public void testAddToZero() {
        Money value = new Money(100);
        Money instance = new Money(0);
        Money expResult = new Money(100);
        Money result = instance.add(value);
        assertTrue(expResult.equals(result));
    }

    @Test
    public void testAdd() {
        Money value = new Money(100);
        Money instance = new Money(12223);
        Money expResult = new Money(12323);
        Money result = instance.add(value);
        assertEquals(expResult, result);
    }

    @Test
    public void testAddNegative() {
        Money value = new Money(-100);
        Money instance = new Money(12223);
        Money expResult = new Money(12123);
        Money result = instance.add(value);
        assertEquals(expResult, result);
    }

    @Test
    public void testAddNegativeWithNegative() {
        Money value = new Money(-100);
        Money instance = new Money(-100);
        Money expResult = new Money(-200);
        Money result = instance.add(value);
        assertEquals(expResult, result);
    }

    @Test
    public void testAddWithNegativeResult() {
        Money value = new Money(-100000);
        Money instance = new Money(10000);
        Money expResult = new Money(-90000);
        Money result = instance.add(value);
        assertEquals(expResult, result);
    }

    @Test
    public void testSubstractWithZero() {
        Money value = new Money(0);
        Money instance = new Money(100);
        Money expResult = new Money(100);
        Money result = instance.substract(value);
        assertEquals(expResult, result);
    }

    @Test
    public void testSubstractWithNegative() {
        Money value = new Money(-50);
        Money instance = new Money(100);
        Money expResult = new Money(150);
        Money result = instance.substract(value);
        assertEquals(expResult, result);
    }

    @Test
    public void testSubstractWithNegativeResult() {
        Money value = new Money(1000);
        Money instance = new Money(100);
        Money expResult = new Money(-900);
        Money result = instance.substract(value);
        assertEquals(expResult, result);
    }

    @Test
    public void testSubstract2() {
        Money value = new Money(50, 00);
        Money instance = new Money(100, 00);
        Money expResult = new Money(5000);
        Money result = instance.substract(value);
        assertEquals(expResult, result);
    }

    @Test
    public void testSubstract3() {
        Money value = new Money(0);
        Money instance = new Money(0);
        Money expResult = new Money(0);
        Money result = instance.substract(value);
        assertEquals(expResult, result);
    }

    @Test
    public void muliply1() {
        long value = 0;
        Money instance = new Money(1313233135464213l);
        Money expResult = new Money(0);
        Money result = instance.multiply(value);
        assertEquals(expResult, result);
    }

    @Test
    public void muliply2() {
        long value = 1;
        Money instance = new Money(1000, 235);
        Money expResult = new Money(1000, 235);
        Money result = instance.multiply(value);
        assertEquals(expResult, result);
    }

    @Test
    public void muliply3() {
        long value = 10;
        Money instance = new Money(1000, 23);
        Money expResult = new Money(10002, 30);
        Money result = instance.multiply(value);
        assertEquals(expResult, result);
    }

    @Test
    public void muliply4() {
        long value = -1;
        Money instance = new Money(1000, 23);
        Money expResult = new Money(-1000, 23);
        Money result = instance.multiply(value);
        assertEquals(expResult, result);
    }

    @Test
    public void muliply5() {
        long value = 2;
        Money instance = new Money(1000, 23);
        Money expResult = new Money(2000, 46);
        Money result = instance.multiply(value);
        assertEquals(expResult, result);
    }
}