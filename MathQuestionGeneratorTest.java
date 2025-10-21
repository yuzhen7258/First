package 软件构造.shiyan2;

import static org.junit.Assert.*;
import org.junit.Test;

public class MathQuestionGeneratorTest {

    @Test
    public void testAddition() {
        int[] nums = {3, 4};
        String operator = "+";
        long result = MathQuestionGenerator.performOperation(nums, operator);
        assertEquals(7, result);
    }

    @Test
    public void testSubtraction() {
        int[] nums = {10, 4};
        String operator = "-";
        long result = MathQuestionGenerator.performOperation(nums, operator);
        assertEquals(6, result);
    }

    @Test
    public void testMultiplication() {
        int[] nums = {3, 5};
        String operator = "*";
        long result = MathQuestionGenerator.performOperation(nums, operator);
        assertEquals(15, result);
    }

    @Test
    public void testDivision() {
        int[] nums = {12, 4};
        String operator = "/";
        long result = MathQuestionGenerator.performOperation(nums, operator);
        assertEquals(3, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidOperationResult() {
        int[] nums = {150, 200};
        String operator = "+";
        // Expecting an IllegalArgumentException due to out-of-bound result
        MathQuestionGenerator.performOperation(nums, operator);
    }

    @Test
    public void testLargeMultiplication() {
        int[] nums = {300, 400};
        String operator = "*";
        long result = MathQuestionGenerator.performOperation(nums, operator);
        assertEquals(120000, result);
    }

    @Test
    public void testLargeAddition() {
        int[] nums = {5000, 10000};
        String operator = "+";
        long result = MathQuestionGenerator.performOperation(nums, operator);
        assertEquals(15000, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLargeResultOutOfBounds() {
        int[] nums = {100000, 200000};
        String operator = "*";
        // Expecting an IllegalArgumentException due to out-of-bound result
        MathQuestionGenerator.performOperation(nums, operator);
    }
}
