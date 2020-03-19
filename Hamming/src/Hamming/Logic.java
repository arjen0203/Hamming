package Hamming;

import java.util.BitSet;
import java.util.Random;

public class Logic {

    public BitSet textToEncodedBits(String input){

        String encodedString = "";
        for (char ch: input.toCharArray()){ //n
            Byte charInByte = (byte) ch;
            String bits = Integer.toBinaryString(ch); //1
            int neededZeroos = 8 - bits.length();
            for (int i = 0; i < neededZeroos; i++){ //1-7
                bits = "0" + bits;
            }
            String encodedBits;
            encodedBits = addParityBits(bits.substring(0, 4)); //1
            encodedBits += addParityBits(bits.substring(4, 8)); //1
            encodedString += encodedBits; //1
        }
        //System.out.println(encodedString);

        BitSet inputBits = new BitSet(encodedString.length()); //1

        for (int i = 0; i < encodedString.length(); i++) { //n
            if (encodedString.charAt(i) == '1') {
                inputBits.set(i);
            }
        }

        //System.out.println(inputBits);

        return inputBits; //2n The complexity of this part of the code has 2 loops that loop over all input data so the time complexity should be 2n
    }

    public String addParityBits(String bits){
        int parBit1;
        if (isOdd(new int[] {0, 1, 3}, bits.toCharArray())) parBit1 = 1; //1
        else  parBit1 = 0;
        int parBit2;
        if (isOdd(new int[] {0, 2, 3}, bits.toCharArray())) parBit2 = 1; //1
        else  parBit2 = 0;
        int parBit3;
        if (isOdd(new int[] {1, 2, 3}, bits.toCharArray())) parBit3 = 1; //1
        else  parBit3 = 0;

        return (parBit1) + (parBit2 + bits.substring(0, 1)) + parBit3 + bits.substring(1, 4);
    }

    public boolean isOdd(int[] arr, char[] bits){
        int count = 0;
        for (int i: arr){ //n
            if (bits[i] == '1') count++;
        }
        if (count%2 == 0) return false;
        return true;
    }

    public void errorMaking(BitSet bitSet){
        int percentageError = 20;
        if (bitSet.length() > 1000) percentageError = (bitSet.length() / 20);

        Random rnd = new Random();

        for (int i = 0; i < bitSet.length(); i += rnd.nextInt(percentageError)){ //n
            bitSet.flip(i);
        }
        //System.out.println(bitSet);
    }
    public String decode(BitSet input) {
        String binaryString = "";

        int nextTrue = input.nextSetBit(0);
        for (int i = 0; i < input.length() + 13; i++) //n
            if (nextTrue == i) {
                binaryString += "1";
                nextTrue = input.nextSetBit(i + 1);
            } else {
                binaryString += "0";
            }

        //System.out.println(binaryString);
        String output = "";


        for (int i = 0; i < binaryString.length() - 6; i += 7) { //n
            output += correctMessage(binaryString.substring(i, i + 7)); //1
        }
        //System.out.println(output);
        fromBitToString(output);

        return fromBitToString(output); //3n The complexity of this part of the code has 3 loops that loop over all input data so the time complexity should be 3n
    }
    public String correctMessage(String input){

        String charCode = input.substring(2, 3) + input.substring(4, 7);
        String hammingCheck = addParityBits(charCode);

         if (hammingCheck.equals(input)) return charCode;
         else {
             int ErrorIndex = -1;
             for (int i = 0; i < input.length(); i++){
                if (input.charAt(i) != hammingCheck.charAt(i)) ErrorIndex += i + 1;
             }
             String changeThis = "1";
             if (input.charAt(ErrorIndex) == '1') changeThis = "0";
             String corrected = input.substring(0, ErrorIndex) + changeThis + input.substring(ErrorIndex + 1, input.length());

             return corrected.substring(2, 3) + corrected.substring(4,7);
         }
    }

    public String fromBitToString(String input){
        String str = "";
        for (int i = 0; i < input.length() - 7; i += 8){ //n
            int charCode = Integer.parseInt(input.substring(i, i + 8), 2);
            str += Character.toString((char) charCode);
        }
        return str;
        //System.out.println(str);
    }
}
