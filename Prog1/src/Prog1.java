//------------------------------------------------------------------------------------------------------------ //
// Name: Joachim Isaac //
// Course: CS 2143, Fall 19, Dr. Stringfellow //
// Purpose: To implement a dynamic array stack and use it to replicate a game of solitaire.
//
////------------------------------------------------------------------------------------------------------------


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Prog1 {

    public static final int DECK_SIZE = 52;

    public static void main(String args[])throws IOException {

        System.out.println("Name: Joachim Isaac\nProgram1: Solitaire Stacks\n");
        String copy_of_cards = read_in_cards(getinputFile());
        File output_file = getoutFile();
        System.out.println(copy_of_cards);


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
            //Name delimiters something else later.
            String delims = "[ ]+";

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

    public static void print_to_outputfile(File output_file_name, String cards){//Here is what you use to print out the set of cards you recieved. <---- work on it next
        try {//Output to txt file , will
//            PrintWriter output = new PrintWriter(output_file_name);
//            output.println("Hello world");
//            output.println(42);
//            output.close();
        } catch (IOException ex) {
//            System.out.println("File not found");
        }
    }


}





