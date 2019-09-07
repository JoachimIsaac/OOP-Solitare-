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

    public static void main(String args[])throws IOException {
        int set_number = 0;


        System.out.println("Name: Joachim Isaac\nProgram1: Solitaire Stacks\n");
        String copy_of_cards = read_in_cards(getinputFile());
        ArrayList<RecordStack> card_stack = new ArrayList<>(getnumber_of_cards(copy_of_cards));
        File output_file = getoutFile();
        print_set(output_file,copy_of_cards,set_number);
        card_stack = set_cards(set_stacks(card_stack),copy_of_cards);

        //Ok all cards are set right about now. Need to do game logic.

        //After game logic , I can print the results.
        





    }


    public static File getinputFile()  {

        //Gets the input file name and stores it in a variable.
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the input file name:");
        String input_file_name = input.nextLine();
        File input_file = new File(input_file_name.strip());

        return input_file;
    }

    public static File getoutFile()  {
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

                if (line.charAt(0) != '#') {
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

    public static void print_set(File output_file_name, String cards, int set_number){//Here is what you use to print out the set of cards you recieved. <---- work on it next
        try {//Output to txt file , will
            PrintWriter output = new PrintWriter(output_file_name);
            output.println("Set " + set_number + ":");
            output.println(cards);
            System.out.println(cards);
            output.close();

        } catch (IOException ex) {
            System.out.println("File not found");
        }
    }

    public static int getnumber_of_cards(String cards){
        //Name delimiters something else later.
            String delims = "[ ]+";
//        String array to put the cards into
// change tokens name --> better naming needed. But it is an array o strings that contains each card in a
        // index thanks to split.(delims);
            String[] tokens = cards.split(delims);
            return tokens.length;
    }

    public static ArrayList<RecordStack> set_stacks (ArrayList<RecordStack> card_stack){
        RecordStack stack = new RecordStack();
        for(int i = 0; i < card_stack.size(); i++) {
            card_stack.add(stack);
        }
        return card_stack;
    }


    public static ArrayList<RecordStack> set_cards(ArrayList<RecordStack> card_stack, String cards){
        String delims = "[ ]+";
        String[] tokens = cards.split(delims);

        for(int i = 0; i < tokens.length; i++) {
            card_stack.get(i).push(tokens[i]);
        }
        return card_stack;
    }


}





