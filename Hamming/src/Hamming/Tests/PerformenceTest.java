package Hamming.Tests;

import Hamming.Logic;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.Console;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;

public class PerformenceTest {
    static String tenThousendWords;
    static String millionWords;
    Logic logic = new Logic();

    @BeforeClass
    public static void Setup() {

        try {
            tenThousendWords = new String(Files.readAllBytes(Paths.get("C:\\Users\\arjen\\Documents\\Fontys\\S4 EFF\\WoordenApplicatie\\tenThousand.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            millionWords = new String(Files.readAllBytes(Paths.get("C:\\Users\\arjen\\Documents\\Fontys\\S4 EFF\\WoordenApplicatie\\million.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void RunThousendTestEncoding(){
        BitSet testSet = logic.textToEncodedBits(tenThousendWords);
        logic.errorMaking(testSet);
        logic.decode(testSet);
    }
    @Test
    public void RunMillionTest(){
        BitSet testSet = logic.textToEncodedBits(millionWords);
        logic.errorMaking(testSet);
        logic.decode(testSet);
    }
}
