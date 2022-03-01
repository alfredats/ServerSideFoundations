package sg.edu.nus.iss.workshop12.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
/**
 * Random Number Generator based on user input
 *
 */
public class RandomNumberGenerator
{
    public List<Integer> nonDupNumbers = new ArrayList<Integer>();

    public RandomNumberGenerator() {}

    public RandomNumberGenerator(Integer x) {
        for (int i = 0; i < x; i++ ) {
            this.rand();
        }
    }

    public void rand(){
        Random rand = new Random();
        int randomNumInRange = 1  + rand.nextInt((30-1) + 1);
        if(!nonDupNumbers.contains(randomNumInRange)){
            System.out.println(randomNumInRange);
            nonDupNumbers.add(randomNumInRange);
        }else{
            rand();
        }
    }

    public static void main( String[] args )
    {
        System.out.println( "Random Number Generator!" );
        int inNumInt = 0;
        Scanner userInput = new Scanner(System.in);  // Create a Scanner object
        System.out.println("How many number do you wish to generate ?");
        //int numberNeededToGen = 3;
        String numberNeededToGen = userInput.nextLine();

        try{
            inNumInt = Integer.parseInt(numberNeededToGen);
        }catch(NumberFormatException e){
            System.out.println("Only numbers are allowed");
            System.exit(1);
        }

        System.out.println(inNumInt);
        if(inNumInt > 30){
            System.out.println("Only allow 1 to 30");
            System.exit(1);
        }

        RandomNumberGenerator generator = new RandomNumberGenerator();

        for(int i=0; i < inNumInt; i++){
            generator.rand();
        }

        System.out.println("list: " + generator.nonDupNumbers);
        for (Integer val : generator.nonDupNumbers) {
            System.out.println(val + ".png");
        }
        userInput.close();
    }
}