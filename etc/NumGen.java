package sg.edu.nus.workshop12.workshop12;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class NumGen {
    
    public static void main(String[] args) {
        Random intGen = new Random(System.currentTimeMillis());
        Scanner ui = new Scanner(System.in); 
        Integer DEFAULT_LOWER = 0;
        Integer DEFAULT_UPPER = 100;
        
        Integer numGen = -1;
        do {
            System.out.println("Enter a number from 1 to 30:");
            if (ui.hasNextInt()) {
                numGen = ui.nextInt();
            }
        } while ( numGen < 1 || numGen > 30);
        
        Integer[] randArray = new Integer[numGen];

        int ind = 0; 
        while (hasDupes(randArray)) {
            randArray[ind] = DEFAULT_LOWER + intGen.nextInt(DEFAULT_UPPER);
            ind = (ind + 1) % numGen;
        }

        System.out.println(Arrays.toString(randArray));
        ui.close();
    }

    public static Boolean hasDupes(Integer[] generated) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < generated.length; i++) {
            if (generated[i] == null) {
                return true;
            } else if (map.get(generated[i]) == null) {
                map.put(generated[i],1);
            } else {
                return true;
            }
        }
        return false;
    }
}
