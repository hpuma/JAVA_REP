import java.util.*;
/*  
"CaesarShift"
A Simple Program that takes a string and applies a Caesar Shift 
based on values of the integer array "Shift".  
NOTE: Improve later on so that it shifts alphabetically rather than the ASCII value!
S: 8/7/19
E: 8/8/19
*/
class CaesarShift{
    public static void main(String[] args) {
        String testString = "HELLO WORLD";
        int []shiftTest = {-1,7};
        String newString = CShift(testString,shiftTest);
        System.out.println(newString);
        System.out.println("SUCCESS!");
    }
    // Precondition: We are given an input string "Input", and an int array "Shift" that contains the amount to shift each character.
    // Postcondition: A shifted string is outputed based on the values of the "Shift" array. 
    // RUNTIME: O(N^2)
    public static String CShift(String Input, int []Shift){
        if(Shift.length == 0 || Input.isEmpty()){ // In case the array or input string is empty, just return the Input string.
            return Input;
        }
        String tempString = Input; // A temporary string used to hold the current state of input, we are not directly manipulating Input.
        StringBuilder stringSkel = new StringBuilder(); // String builder used to update the temp string after every iteration from the array.
        for(int i = 0; i < Shift.length; i++){ // For-loop iterating through the ShiftArray.
            for(int j = 0; j < Input.length(); j++){ // For-Loop iterating throught the Input String.
                if(tempString.charAt(j) == ' '){ // If we are at a space, then add a space to the string builder.
                    stringSkel.append(' ');
                }
                else{ // Just shift the character by the value in the shift arrray.
                    stringSkel.append((char)(((int)tempString.charAt(j)+Shift[i])% 127));
                }
            }
            tempString = stringSkel.toString(); // Update the temp string with what is currently in the String Builder.
            stringSkel.setLength(0); // Reset the contents of the string builder.
        } 
        return tempString; // Return the final product of the shift of the array.
    }
}