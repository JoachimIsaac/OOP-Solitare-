//------------------------------------------------------------------------------------------------------------ //
// Name: Joachim Isaac //
// Course: CS 2143, Fall 19, Dr. Stringfellow //
// Purpose: To implement a dynamic array stack and use it to replicate a game of solitaire.
//
////------------------------------------------------------------------------------------------------------------


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
//I Implemented my Stacks with linked lists using java.
//I implemented program without a close gap function
public class Prog1 {

   public static final int DECK_SIZE = 52;

    public static void main(String args[]) throws IOException {

            int set_number = 1;

            //position is used to indicate what position we are when sorting through each deck of cards.
            //The position in incremented by 52 after a game so that we start at 52 if there is another
            //deck of cards.
            int position = 0;
            //capacity effects how much you iterate over the String of card values. It prevents you from reading in more
            //Than a deck each iteration. It is incremented by 55 each time so that it will start at the start of
            //The next deck based on the input string.
            int capacity = DECK_SIZE;

            String result=""; //Stores all the results.

            System.out.println("Name: Joachim Isaac\nProgram1: Solitaire Stacks\n");
            String copy_of_cards = read_in_cards(getinputFile()); //Reads in the string of cards and puts it into a variable.
            File output_file = getoutFile();
            int games = number_of_games(copy_of_cards);//gets the number of games.
            ArrayList<String>card_tokens = new ArrayList<String>(games * DECK_SIZE);
            ArrayList<String>results_list = new ArrayList<String>(games);//Stores a string of all the results.
            card_tokens = get_card_tokens(card_tokens,copy_of_cards);//Puts each individual card into an array and returns that array.

            ArrayList<RecordStack> card_stack = new ArrayList<RecordStack>(DECK_SIZE);


            for (int i =0; i < games; i++) {
                //Sets the stacks and then sets the cards within the separate stacks
                card_stack = set_cards(set_stacks(card_stack, DECK_SIZE),card_tokens,position,capacity);

                //Game logic to manipulate the stacks and it returns the array of stack that is changed.
                card_stack = play(card_stack);

                //store_results gets all the results and stores it into a string.
                result = store_results(card_stack, copy_of_cards, set_number, games);

                //This adds the result per game into an array index.
                results_list.add(result);

                //We declare a new ArrayList over the old card stack so that we can add a new set of cards to it.
                card_stack = new ArrayList<RecordStack>(DECK_SIZE);

                //We increment all of our postion manipulation values.
                position += DECK_SIZE;
                capacity += DECK_SIZE;
                set_number += 1;
            }

            print_all_results(results_list,games);

    }


    public static File getinputFile() {

        //Gets the input file name and stores it in a variable.
        Scanner input = new Scanner(System.in).useDelimiter("\\s*fish\\s*");
        System.out.println("Enter the input file name:");
        String input_file_name = input.nextLine();
        File input_file = new File(input_file_name.strip());

        return input_file;
    }

    public static File getoutFile() {
        Scanner input = new Scanner(System.in).useDelimiter("\\s*fish\\s*");
//      Gets the output file name and stores it in a variable.
        System.out.println("Enter the output file name:");
        String output_file_name = input.nextLine();

//        Stores the files names as a File object*******
//        Strip gets rid of any unwanted white space.
        File output_file = new File(output_file_name.strip());

        return output_file;
    }


    public static String read_in_cards(File input_file_name) { //Issues here
        try {
            //Works with file object -->input_file<--
            //In this case it will be reading from the input file rather than from the console/terminal.
            Scanner infile = new Scanner(input_file_name);

            String cards = "";
            String line = "";

            //Looks for a next line and returns a bool value.
            while (infile.hasNextLine()) {
                //Read in a line from the file
                line = infile.nextLine();

                if (line.charAt(0) != '#') {//
                    cards += "\n"+ line;
                    //add the "+ /n"

                    System.out.println(line);
                }
                if (line.charAt(0) == '#') {//
                   break;
                }

                line = "";
                //initialized it to and empty string for the next line
            }
            return cards;

        } catch (FileNotFoundException ex) {//Catch to see if file is not found.
            System.out.println("File not found");
        }
        return "";
    }


    public static ArrayList<RecordStack> set_stacks(ArrayList card_stack, int number_of_cards) {

        for (int i = 0; i < number_of_cards; i++) {
            RecordStack stack = new RecordStack();
            card_stack.add(stack);

        }
        return card_stack;
    }


    public static ArrayList<RecordStack> set_cards(ArrayList<RecordStack> card_stack, ArrayList<String> tokens,int position_to_start_at,int capacity) {



        for (int i = position_to_start_at; i < capacity; i++) {
                card_stack.get(i-position_to_start_at).push(tokens.get(i));
//        System.out.println(cards_to_input.nextToken());

        }


        return card_stack;
    }

    public static ArrayList<String>get_card_tokens(ArrayList<String> token_holder,String cards){

        StringTokenizer cards_to_count = new StringTokenizer(cards);
        for (int i = 0; cards_to_count.hasMoreTokens(); i++) {
            token_holder.add(cards_to_count.nextToken());

        }
        return token_holder;
    }

    public static int number_of_games(String cards){
        StringTokenizer cards_to_count = new StringTokenizer(cards);
        int counter = 0;

        for (int i = 0; cards_to_count.hasMoreTokens(); i++) {
            cards_to_count.nextToken();
            counter++;
        }
        return counter/DECK_SIZE;
    }

    public static ArrayList<RecordStack> play(ArrayList<RecordStack> card_stacks) {
        int card_pointer = 1;
        Boolean gap_closed = false;


        //go over the logic again but for now it seems like it should work< issue with the array .
        while (card_pointer < card_stacks.size()) {
//            System.out.println("\n"); //Comment everything from here let it run without errors
//            System.out.println("Check one from left "+ check_1_left(card_stacks, card_pointer));
//            System.out.println("Check three from left " + check_3_left(card_stacks, card_pointer));
//            System.out.println("The array which contains the stacks' size:--> " + card_stacks.size());
//            System.out.println("Length of card away from pointer(-1): " + card_stacks.get(card_pointer - 1).peek().length());
//            System.out.println("Length of card away from pointer(-3): " + card_stacks.get(card_pointer - 1).peek().length() + "\n");

            if (card_pointer >= 3 && check_3_left(card_stacks, card_pointer)) {
                String card_to_move = card_stacks.get(card_pointer).pop();
                card_stacks.get(card_pointer - 3).push(card_to_move);
                //keeping in mind we keep incrementing to get it to a situation where it will fit that scenario

                if (card_stacks.get(card_pointer).isEmpty()) {
                    card_stacks.remove(card_pointer);
                    gap_closed = true;
                }
                card_pointer -= 3;

                //Look for gap
            }else if (card_pointer >= 1 && check_1_left(card_stacks, card_pointer)) {

                    String card_to_move = card_stacks.get(card_pointer).pop();
                    card_stacks.get(card_pointer - 1).push(card_to_move) ;

                if (card_stacks.get(card_pointer).isEmpty()) {//Removes the gap if there is any before continuing
                    card_stacks.remove(card_pointer);
                    gap_closed = true;
                }
                card_pointer -= 1;///updates card pointer
            }
            else{
                if (card_pointer > 0 && gap_closed == false ){
               card_pointer += 1;
                }
            }

             if (card_pointer == 0) {
                card_pointer += 1;
            }

            gap_closed = false;

            for(int i =0; i < card_stacks.size(); i++){
                System.out.println(card_stacks.get(i).peek() +" cardpointer: "+  card_pointer);
            }
        }

        return card_stacks;

    }

    public static Boolean check_3_left(ArrayList<RecordStack> card_stacks, int card_pointer) {//logic sound
        if (card_pointer >= 3) {
            String card_to_compare = card_stacks.get(card_pointer - 3).peek().trim();
            String card_started_on = card_stacks.get(card_pointer).peek().trim();

            for (int i = 0; i < 2; i++) {
                if (card_to_compare.charAt(i) == card_started_on.charAt(i)) {
                    return true;
                }
            }
        }

        return false;

    }


    public static Boolean check_1_left(ArrayList<RecordStack> card_stacks, int card_pointer) {//logic is sound
        if (card_pointer >= 1) {
            String card_to_compare = card_stacks.get(card_pointer - 1).peek().trim();
            String card_started_on = card_stacks.get(card_pointer).peek().trim();

            for (int i = 0; i < 2; i++) {
                if (card_to_compare.charAt(i) == card_started_on.charAt(i)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String store_results(ArrayList<RecordStack> card_stacks, String cards, int set_number,int number_of_games) {

        String results= "";

        String amount_in_piles = "";
        int number_of_piles = card_stacks.size();
        int current_amount = 0;

        for (int i = 0; i < number_of_piles; i++) {
            current_amount = card_stacks.get(i).number_of_cards();
            amount_in_piles += " " + Integer.toString(current_amount);
        }

        results = "Set " + set_number + ":" + "\n" + cards + "\n" +"\n"+ number_of_piles + " Piles remaining:" + amount_in_piles;


       return  results;
    }
    public static void print_all_results (ArrayList<String> results,int number_of_games) {
        System.out.println("\n" +"Name: Joachim Isaac\nProgram1: Solitaire Stacks\n");
        for(int i =0; i < number_of_games; i++){
            System.out.println(results.get(i) + "\n" +"\n");
        }

    }
}
