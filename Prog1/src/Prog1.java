//------------------------------------------------------------------------------------------------------------
// Name: Joachim Isaac //
// Course: CS 2143, Fall 19, Dr. Stringfellow //
// Purpose: To implement a dynamic array stack and use it to replicate a game of solitaire.
// I Implemented my Stacks with linked lists using java.
// I implemented program without a close gap function.
//
//-----------------------------------------------------------------------------------------------------------


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Prog1 {

    //Deck size public Constant variable.
    public static final int DECK_SIZE = 52;

    //Main Function.
    public static void main(String args[])throws IOException {

        //This variable keeps track of the sets we are printing to the screen.
        int set_number = 1;


        //Is used to indicate what position we are when sorting
        // through each deck of cards through each iteration.
        int position = 0;


        //Increments by 52 each time, to start on the next deck.
        int capacity = DECK_SIZE;

        //current deck
        String c_deck = "";


        //Stores each set of results through each iteration.
        String result="";

        System.out.println("Name: Joachim Isaac\nProgram1: Solitaire Stacks\n");

        //Reads in the string of cards and puts it into a variable.
        String copy_of_cards = read_in_cards(getinputFile());

        //Calculates the number of games and returns it into the games variable.
        int games = number_of_games(copy_of_cards);

        //String array list to store the cards while they are being tokenized.
        //The size is based on the number of games.
        ArrayList<String>card_tokens = new ArrayList<String>(games * DECK_SIZE);

        //Array to store the results.
        //The size is based on the number of games.
        ArrayList<String>results_list = new ArrayList<String>(games);

        //get_card_tokens Puts each individual card into an array and returns that array.
        card_tokens = get_card_tokens(card_tokens,copy_of_cards);

        //ArrayList of RecordStack to store the stack ADT and the Cards.
        ArrayList<RecordStack> card_stack = new ArrayList<RecordStack>(DECK_SIZE);

        //This loops based on home many games we have.
        for (int i =0; i < games; i++) {

            //Sets the stack ADT and then sets the cards within the separate stacks.
            card_stack = set_cards(set_stacks(card_stack, DECK_SIZE),card_tokens,position,capacity);

            //Stores the current deck so it can be printed.
            c_deck = current_deck(card_tokens,position,capacity);

            //Game logic to manipulate the cards within the stacks.
            //it returns the array of stacks after manipulating it.
            card_stack = play(card_stack);

            //store_results gets all the results from the stack after it has been manipulated
            //then stores it into a string.
            result = store_results(card_stack, c_deck, set_number, games);

            //This adds each result per game into an array.
            results_list.add(result);

            //We declare a new ArrayList over the old card stack so that we can add a new set of cards to it.
            card_stack = new ArrayList<RecordStack>(DECK_SIZE);

            //We increment the position manipulation values.
            //So that we begin inputting card from the next deck.
            position += DECK_SIZE;
            capacity += DECK_SIZE;

            set_number += 1;

        }//End of for loop.


        //This prints out all the results that we stored in the results_list array.
        print_all_results(results_list,games);
    }

    //Prompts user from input file name and then stores it into a file variable.
    //Then it returns that file variable.
    public static File getinputFile() {
        Scanner input = new Scanner(System.in).useDelimiter("\\s*fish\\s*");
        System.out.println("Enter the input file name:");
        String input_file_name = input.nextLine();
        File input_file = new File(input_file_name.strip());

        return input_file;
    }

    //Takes in a file variable, then reads in the data on that file.
    //Then it stores it into a variable. If there is a '#' it stops reading in.
    public static String read_in_cards(File input_file_name) { //Issues here
        try {
            //Works with file object -->input_file<--
            //In this case it will be reading
            //from the input file rather than from the console/terminal.
            Scanner infile = new Scanner(input_file_name);

            //String to hold all the cards.
            String cards = "";

            //String to hold a line of cards.
            String line = "";

            //Loops each time while there is a next line to read.
            while (infile.hasNextLine()) {

                //Reads in a line from the file and stores it into line.
                line = infile.nextLine();

                //If the first character in the line is not a '#' then we add the line to the cards variable.
                if (line.charAt(0) != '#') {
                    cards += "\n" + line;

                }//If the first character in the line is a '#' then we return cards.
                else if(line.charAt(0) == '#') {
                   return cards;
                }
            }//End of while loop.

            return cards;

            //Catch exception: if the file name was not found.
            //It prints "File not found".
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
        return "";
    }

    //This method takes in an Arraylist of type 'RecordStack'
    // and the adds stacks into each index.
    public static ArrayList<RecordStack> set_stacks(ArrayList card_stack,
        int number_of_cards) {

            for (int i = 0; i < number_of_cards; i++) {
                RecordStack stack = new RecordStack();
                card_stack.add(stack);

            }
            return card_stack;
    }

    //This method adds cards to the stacks within the RecordStack ArrayList.
    public static ArrayList<RecordStack> set_cards(ArrayList<RecordStack> card_stack,
       ArrayList<String> tokens,int position_to_start_at, int capacity){
        //The values are placed so that we begin inputting cards from the next deck.
        //When everything increments.
            for (int i = position_to_start_at; i < capacity; i++) {
                    card_stack.get(i-position_to_start_at).push(tokens.get(i));
            }
            return card_stack;
    }

    //This stores the current deck as a string so it can be printed.
    public static String current_deck(ArrayList<String> tokens,int position_to_start_at, int capacity){

        String current_deck = "";
        int counter = 0;

        for (int i = position_to_start_at; i < capacity; i++) {
            if(counter == 26){
                current_deck +=  "\n"+ tokens.get(i) + " ";
            }
            else {
                current_deck += tokens.get(i) + " ";
            }
            counter++;
        }
        return current_deck;
    }


    //This method get the cards separated and placed into an array so they can be easily accessed via index.
    public static ArrayList<String>get_card_tokens(ArrayList<String> token_holder,String cards){

        StringTokenizer cards_to_count = new StringTokenizer(cards);

        for (int i = 0; cards_to_count.hasMoreTokens(); i++) {
            token_holder.add(cards_to_count.nextToken());
        }
        return token_holder;
    }

    //This method checks how many games we have based on the cards that were read in.
    public static int number_of_games(String cards){
        StringTokenizer cards_to_count = new StringTokenizer(cards);
        int counter = 0;

        for (int i = 0; cards_to_count.hasMoreTokens(); i++) {
            cards_to_count.nextToken();
            counter++;
        }
        return counter/DECK_SIZE;
    }

    //This is the logic which manipulates the cards inside of the array of stacks.
    //It matches cards that are the same suit or rank.
    //Three cards away or one card away.
    public static ArrayList<RecordStack> play(ArrayList<RecordStack> card_stacks) {

        //Card pointer is your index position.
        int card_pointer = 1;

        Boolean gap_closed = false;

        //This while loop continues as long as the card pointer is
        // less than the size of the stack array.
        while (card_pointer < card_stacks.size()) {

            //This checks whether the card that is
            //3 positions away matches with the current card.
            if (card_pointer >= 3 && check_3_left(card_stacks, card_pointer)) {

                //Stores the card we want to move.
                String card_to_move = card_stacks.get(card_pointer).pop();

                //Then pushes the card we want to move on to the card it matches with.
                card_stacks.get(card_pointer - 3).push(card_to_move);

                //If the position we remove the card from is empty then we remove that
                //Arraylist Index This removes the gap and then we set gap_closed = to true.
                if (card_stacks.get(card_pointer).isEmpty()) {
                    card_stacks.remove(card_pointer);
                    gap_closed = true;
                }

                //This ensures that we are at the position
                // where we matched the cards, 3 spaces away.
                card_pointer -= 3;

                //This checks whether the card that is 1 position away
                //matches with the current card.
            }else if (card_pointer >= 1 && check_1_left(card_stacks, card_pointer)) {

                    //Stores the card we want to move.
                    String card_to_move = card_stacks.get(card_pointer).pop();

                    //Then pushes the card we want to move on to the card it matches with.
                    card_stacks.get(card_pointer - 1).push(card_to_move) ;


                //If the position we remove the card from is empty
                //then we remove that Arraylist Index.
                //This removes the gap and then we set gap_closed = to true.
                if (card_stacks.get(card_pointer).isEmpty()) {
                    card_stacks.remove(card_pointer);
                    gap_closed = true;
                }

                //This ensures that we are at the position where
                //we matched the card, 1 space away.
                card_pointer -= 1;
            }
            else{//If there was no card to match then and no gap
                // was closed then we increment the card pointer by 1.
                if (card_pointer > 0 && gap_closed == false ){
                     card_pointer += 1;
                }
            }

            //This ensures that the card we are currently at is never the first card.
             if (card_pointer == 0) {
                card_pointer += 1;
            }

             //Reset the gap_close variable to be false.
            gap_closed = false;

        }//While loop ends here.

        return card_stacks;
    }

    //This checks whether the card that is 3 positions away
    // matches with the current card and returns a Boolean value.
    public static Boolean check_3_left(ArrayList<RecordStack> card_stacks, int card_pointer) {

        if (card_pointer >= 3) {

            //Card to compare that is 3 positions away.
            String card_to_compare = card_stacks.get(card_pointer - 3).peek().trim();

            //Current card to be compared with.
            String card_started_on = card_stacks.get(card_pointer).peek().trim();

            //This checks whether their ranks or suits are equal.
            for (int i = 0; i < 2; i++) {
                if (card_to_compare.charAt(i) == card_started_on.charAt(i)) {
                    return true;
                }
            }
        }

        return false;
    }

    //This checks whether the card that is 1 position away matches
    // with the current card and returns a Boolean value.
    public static Boolean check_1_left(ArrayList<RecordStack>card_stacks,int card_pointer) {

        if (card_pointer >= 1) {

            //Card to compare that is 1 position away.
            String card_to_compare = card_stacks.get(card_pointer - 1).peek().trim();

            //Current card to be compared with.
            String card_started_on = card_stacks.get(card_pointer).peek().trim();

            //This checks whether their ranks or suits are equal.
            for (int i = 0; i < 2; i++) {
                if (card_to_compare.charAt(i) == card_started_on.charAt(i)) {
                    return true;
                }
            }
        }
        return false;
    }

    //Gets all results and stores it into a string and returns that string.
    public static String store_results(ArrayList<RecordStack> card_stacks,
        String cards,int set_number,int number_of_games) {

            //Variable to hold the results.
            String results= "";

            //Variable to hold the current amount in a pile.
            String amount_in_piles = "";

            //Variables that holds the number of piles we have.
            int number_of_piles = card_stacks.size();

            //Variable to hold the current amount in a pile.
            int current_amount = 0;

            //This iterates through the piles and stores each current amount in the
            //amount in piles variable as a string.
            for (int i = 0; i < number_of_piles; i++) {
                current_amount = card_stacks.get(i).number_of_cards();
                amount_in_piles += " " + Integer.toString(current_amount);
            }

            //Results stores all the results.
            results = "Set " + set_number + ":" + "\n" + cards + "\n" +"\n"+
                    number_of_piles + " Piles remaining:" + amount_in_piles;

           return  results;
    }


    //This method loops through all the results that are in
    //the results array and prints them out.
    //If the number of games is zero it prints
    // for the user that a deck was not read.
    public static void print_all_results (ArrayList<String> results, int number_of_games) {

        System.out.println("\n" +"Name: Joachim Isaac\nProgram1: Solitaire Stacks\n");

        if(number_of_games == 0) System.out.println("A deck was not read");

        for(int i =0; i < number_of_games; i++){
            System.out.println(results.get(i) + "\n" +"\n");
        }
    }
}
