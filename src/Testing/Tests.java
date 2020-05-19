package Testing;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Tests {

    @Test(expected = NullPointerException.class)
    public void testNull(){
        Integer.decode(null);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testEmpty(){
        exceptionRule.expect(NumberFormatException.class);
        exceptionRule.expectMessage("Zero length string");
        Integer.decode("");
    }

    @Test(expected = NumberFormatException.class)
    public void testOnlyMinus(){
        Integer.decode("-");
    }

    @Test(expected = NumberFormatException.class)
    public void testOnlyPlus(){
        Integer.decode("+");
    }

    @Test
    public void testSignWrongPosition(){
        exceptionRule.expect(NumberFormatException.class);
        exceptionRule.expectMessage("Sign character in wrong position");
        Integer.decode("++554");
    }

    @Test(expected = NumberFormatException.class)
    public void testMinusWithSpace(){
        Integer.decode("- 10");
    }

    @Test(expected = NumberFormatException.class)
    public void testPlusWithSpace(){
        Integer.decode("+ 10");
    }

    @Test(expected = NumberFormatException.class)
    public void testDouble(){
        Integer.decode("3.14");
    }

    @Test(expected = NumberFormatException.class)
    public void testLong(){
        String str = String.format("%s",Long.MAX_VALUE);
        Integer.decode(str);
    }

    @Test
    public void testDecimalNumeral(){
        assertEquals(new Integer(197),Integer.decode("197"));
        assertEquals(new Integer(-197),Integer.decode("-197"));
        assertEquals(new Integer(0),Integer.decode("0"));
    }

    @Test
    public void testOctalDigits(){
        assertEquals(new Integer(34),Integer.decode("042"));
        assertEquals(new Integer(-34),Integer.decode("-042"));
    }

    @Test
    public void testHexDigits(){
        assertEquals(new Integer(0xcab),Integer.decode("0xcab"));
        assertEquals(new Integer(-0xA16f),Integer.decode("-0xA16f"));
        assertEquals(new Integer(0X12d1),Integer.decode("0X12d1"));
        assertEquals(new Integer(-0Xa90),Integer.decode("-0Xa90"));
        assertEquals(new Integer(4095),Integer.decode("#fff"));
        assertEquals(new Integer(-2580),Integer.decode("-#A14"));
    }

}
