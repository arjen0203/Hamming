package Hamming.Tests;

import Hamming.Logic;
import org.junit.Test;

import java.util.BitSet;

import static org.junit.Assert.assertEquals;

public class UnitTests {
    Logic logic = new Logic();

    @Test
    public void GlobalTest(){
        //information rate is 14 bits per character so 14n
        BitSet test = logic.textToEncodedBits("Lets test if this will correct very nicely yes???? we adding some woreds to go over the thousend bits because yes reasons");
        logic.errorMaking(test);
        System.out.println(logic.decode(test));
    }

    @Test
    public void EncodeTest(){
        String input = "SUCC";
        int[] expetedTrues = new int[] {1, 4, 6, 7, 12, 13, 15, 18, 20, 22, 25, 27, 28, 31, 32, 35, 40, 41, 42, 45, 46, 49, 54, 55};
        BitSet expectedOutput = new BitSet(69);
        for (int expected: expetedTrues){
            expectedOutput.set(expected);
        }

        assertEquals(expectedOutput, logic.textToEncodedBits(input));
    }

    @Test
    public void EncodeTest2(){
        String input = "x \r\n x";
        int[] expetedTrues = new int[] {3, 4, 5, 6, 7, 8, 9, 15, 17, 19, 35, 37, 39, 41, 49, 51, 52, 54, 57, 59, 61, 73, 74, 75, 76, 77, 78, 79};
        BitSet expectedOutput = new BitSet(97);
        for (int expected: expetedTrues){
            expectedOutput.set(expected);
        }

        assertEquals(expectedOutput, logic.textToEncodedBits(input));
    }
    @Test
    public void DecodeTest(){
        int[] inputTrues = new int[] {1, 4, 6, 7, 12, 13, 15, 18, 20, 22, 25, 27, 28, 31, 32, 35, 40, 41, 42, 45, 46, 49, 54, 55};
        BitSet input = new BitSet(69);
        String expectedOutput = "SUCC";
        for (int inputT : inputTrues){
            input.set(inputT);
        }

        assertEquals(expectedOutput, logic.decode(input));
    }

    @Test
    public void DecodeTest2(){
        int[] inputTrues = new int[] {3, 4, 5, 6, 7, 8, 9, 15, 17, 19, 35, 37, 39, 41, 49, 51, 52, 54, 57, 59, 61, 73, 74, 75, 76, 77, 78, 79};
        BitSet input = new BitSet(69);
        String expectedOutput = "x \r\n x";
        for (int inputT : inputTrues){
            input.set(inputT);
        }

        assertEquals(expectedOutput, logic.decode(input));
    }

    @Test
    public void CorrectTest(){
        String input= "1101111";
        String output = logic.correctMessage(input);

        assertEquals("1111", output);
    }

    @Test
    public void CorrectTest2(){
        String input= "1110111";
        String output = logic.correctMessage(input);

        assertEquals("1111", output);
    }

    @Test
    public void CorrectTest3(){
        String input= "1111011";
        String output = logic.correctMessage(input);

        assertEquals("1111", output);
    }

    @Test
    public void CorrectTest4(){
        String input= "1111101";
        String output = logic.correctMessage(input);

        assertEquals("1111", output);
    }
    @Test
    public void CorrectTest5(){
        String input= "1111110";
        String output = logic.correctMessage(input);

        assertEquals("1111", output);
    }
}
