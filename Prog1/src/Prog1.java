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

public class Prog1 {

    public static final int DECK_SIZE = 52;

    public static void main(String args[]) throws IOException {

        for (int i =0; i < 2; i++) {
            int set_number = 1;
            int number_of_cards = 0;


            System.out.println("Name: Joachim Isaac\nProgram1: Solitaire Stacks\n");
            String copy_of_cards = read_in_cards(getinputFile());
            File output_file = getoutFile();
            number_of_cards = getnumber_of_cards(copy_of_cards);
            ArrayList<RecordStack> card_stack = new ArrayList<RecordStack>(number_of_cards);

            card_stack = set_cards(set_stacks(card_stack, number_of_cards), copy_of_cards);
            card_stack = play(card_stack);

            print_results(card_stack, output_file, copy_of_cards, set_number);

        }

        // Display what is remaining from the play , i.e results


        //Figure out how ro make it print two sets.


    }


    public static File getinputFile() {

        //Gets the input file name and stores it in a variable.
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the input file name:");
        String input_file_name = input.nextLine();
        File input_file = new File(input_file_name.strip());

        return input_file;
    }

    public static File getoutFile() {
        Scanner input = new Scanner(System.in);
//      Gets the output file name and stores it in a variable.
        System.out.println("Enter the output file name:");
        String output_file_name = input.nextLine();

//        Stores the files names as a File object*******
//        Strip gets rid of any unwanted white space.
        File output_file = new File(output_file_name.strip());

        return output_file;
    }


    public static String read_in_cards(File input_file_name) {
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
                    cards += "\n" + line;
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


    public static int getnumber_of_cards(String cards) {
        //Name delimiters something else later.
        String delims = "[ ]+";
//        String array to put the cards into
// change tokens name --> better naming needed. But it is an array o strings that contains each card in a
        // index thanks to split.(delims);
        String[] tokens = cards.split(delims);

        System.out.println("Number in string: " + tokens.length);
        return tokens.length;
    }

    public static ArrayList<RecordStack> set_stacks(ArrayList card_stack, int number_of_cards) {

        for (int i = 0; i < number_of_cards; i++) {
            RecordStack stack = new RecordStack();
            card_stack.add(stack);

        }
        return card_stack;
    }


    public static ArrayList<RecordStack> set_cards(ArrayList<RecordStack> card_stack, String cards) {
        String delims = "[ ]+";
        String[] tokens = cards.split(delims);

        for (int i = 0; i < tokens.length; i++) {
            card_stack.get(i).push(tokens[i]);
        }
        return card_stack;
    }

    public static ArrayList<RecordStack> play(ArrayList<RecordStack> card_stacks) {
        int card_pointer = 1;
        Boolean gap_closed = false;

        //go over the logic again but for now it seems almost done !
        while (card_pointer < card_stacks.size()) {

            if (card_pointer >= 3 && check_3_left(card_stacks, card_pointer)) {
                String card_to_move = card_stacks.get(card_pointer).pop();
                card_stacks.get(card_pointer - 3).push(card_to_move);

                if (card_stacks.get(card_pointer).isEmpty()) {
                    card_stacks.remove(card_pointer);
                    gap_closed = true;
                }
                card_pointer -= 3;

                //Look for gap
            } else if (card_pointer >= 1 && check_1_left(card_stacks, card_pointer)) {
                if (!(check_3_left(card_stacks, card_pointer)) && check_1_left(card_stacks, card_pointer)){
                    String card_to_move = card_stacks.get(card_pointer).pop();
                card_stacks.get(card_pointer - 1).push(card_to_move);

                if (card_stacks.get(card_pointer).isEmpty()) {//Removes the gap if there is any before continuing
                    card_stacks.remove(card_pointer);
                    gap_closed = true;
                }
                card_pointer = card_pointer - 1;///updates card pointer
            }
            }
            else if (card_pointer == 0) {
                card_pointer += 1;
            }
            if (card_pointer > 0 && gap_closed == false ) {
                card_pointer += 1;
            }

            gap_closed = false;

        }

        return card_stacks;

    }

    public static Boolean check_3_left(ArrayList<RecordStack> card_stacks, int card_pointer) {
        if (card_pointer >= 3) {
            String card_to_compare = card_stacks.get(card_pointer - 3).peek();
            String card_started_on = card_stacks.get(card_pointer).peek();

            for (int i = 0; i < 2; i++) {
                if (card_to_compare.charAt(i) == card_started_on.charAt(i)) {
                    return true;
                }
            }
        }

        return false;

    }


    public static Boolean check_1_left(ArrayList<RecordStack> card_stacks, int card_pointer) {
        if (card_pointer >= 1) {
            String card_to_compare = card_stacks.get(card_pointer - 1).peek();
            String card_started_on = card_stacks.get(card_pointer).peek();

            for (int i = 0; i < 2; i++) {
                if (card_to_compare.charAt(i) == card_started_on.charAt(i)) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void print_results(ArrayList<RecordStack> card_stacks, File output_file_name, String cards, int set_number) {
        String amount_in_piles = "";
        int number_of_piles = card_stacks.size();
        int current_amount = 0;

        for (int i = 0; i < number_of_piles; i++) {
            current_amount = card_stacks.get(i).number_of_cards();
            amount_in_piles += " " + Integer.toString(current_amount);
        }

        try {//Output to txt file , will
            PrintWriter output = new PrintWriter(output_file_name);
            output.println("Set " + set_number + ":");
            output.println(cards);
            System.out.println(cards);
            output.println(number_of_piles + " Piles remaining:" + amount_in_piles);
            System.out.println(number_of_piles + " Piles remaining:" + amount_in_piles);
            output.close();
        } catch (IOException ex) {
            System.out.println("File not found");
        }
    }










}
