import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EquationTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void differentiate() {
    }

    @Test
    public void sampleTests() {
        long result = (long) (66*(Math.pow(441, 3)) + (3*(Math.pow(441, 2)) + 3));
        assertEquals(new BigInteger("12"),  Equation.differentiate("12x+2", 3));
        //assertEquals(new BigInteger("34980718701554823"),  Equation.differentiate("19x^5-45x^4-30x^3+58x^2+2x+7", 4381));
        assertEquals(new BigInteger("-40178462400283850360419"),  Equation.differentiate("-82x^6+14x^5-17x^2-31x+95", 9603));
        assertEquals(new BigInteger("5"),   Equation.differentiate("x^2-x", 3));
        assertEquals(new BigInteger("20"),   Equation.differentiate("3x^2+2x+1", 3));
        //assertEquals(new BigInteger(String.valueOf(-20)), Equation.differentiate("-5x^2+10x+4x^0", 3));
        assertEquals(new BigInteger(String.valueOf(38509884)), Equation.differentiate("66x^3+3x^2+3x^0", 441));
        //assertEquals(new BigInteger(String.valueOf(result)), Equation.differentiate("66x^3+3x^2+4", 441));
    }

    @Test
    void addOnesToAStringTest() {
        assertEquals("1x^1", Equation.addOnesToAString("x"));
        //assertEquals("1x", Equation.addOnesToAString("x"));
        assertEquals("-13x^4-1x^3+4x^2-3x^1+2x^0", Equation.addOnesToAString("-13x^4-x^3+4x^2-3x+2"));
        assertEquals("-13x^4-1x^3+4x^2-1x^1+2x^0", Equation.addOnesToAString("-13x^4-x^3+4x^2-x+2"));
        assertEquals("-13x^4-1x^3+4x^2+1x^1+2x^0", Equation.addOnesToAString("-13x^4-x^3+4x^2+x+2"));
        assertEquals("-1x^4-1x^3+4x^2-3x^1+2x^0", Equation.addOnesToAString("-x^4-x^3+4x^2-3x+2"));
        assertEquals("-82x^6+14x^5-17x^2-31x^1+95x^0", Equation.addOnesToAString("-82x^6+14x^5-17x^2-31x+95"));
    }

    @Test
    void makeACompleteListTest() {
        List<String> exponents = new ArrayList<>();

        List<String> exponents1 = new ArrayList<>();
        exponents.add("^3");
        exponents.add("^4");
        exponents.add("^2");

        exponents1.add("^1");
        List<String> result = new ArrayList<>();

        List<String> result1 = new ArrayList<>();
        result.add("4");
        result.add("3");
        result.add("2");
        result.add("1");
        result.add("0");

        result1.add("1");
        result1.add("0");
        assertEquals(result, Equation.makeACompleteList(exponents));
        assertEquals(result1, Equation.makeACompleteList(exponents1));
    }

}