//------------------------------------------------------------------------------------------------------------
//
// Name: Joachim Isaac
// Course: CS 2143, Fall 19, Dr. Stringfellow.
// Purpose: To implement a dynamic array stack and use it to replicate a game of solitaire.
// I Implemented my Stacks with linked lists using java.
// I implemented program without a close gap function.
//
//-----------------------------------------------------------------------------------------------------------

//Stack class using linked list implementation.
public class RecordStack {

    //Node class
    private class Node {
        private String val;
        private Node next = null;
        //Node class constructor.
        private Node(String data) {
            this.val = data;
        }
    }


    //Top reference variable.
    private Node top;
    //Property to keep track of stack contents
    private int stack_amount;

    //Constructor of RecordStack class
    RecordStack() {
        this.top = null;
        this.stack_amount = 0;
    }


    //The push method inserts a card into the stack.
    public void push(String card) {

        Node node = new Node(card);

        if (node == null) {
            System.out.println("\n Stack overFlow");
            return;
        }
        node.next = top;
        top = node;
        stack_amount += 1;
    }

    //If the stack is empty it returns true, if not then false.
    public boolean isEmpty() {
        return this.top == null;
    }

    //The peek method returns the value of the card that is on top of the stack.
    public String peek() {

        if (!isEmpty()) {
            return top.val;
        } else {
            //prints out is empty is empty if the stack is empty.
            System.out.println("\nThe Stack is Empty");
            return null;
        }
    }

    //The pop method removes a card at the top of the stack and returns it's value.
    public String pop() {

        //print out is stack underflow if the stack is already empty.
        if (top == null) {
            System.out.println("\nStack Underflow!");
        }

        String data = top.val;
        top = top.next;
        stack_amount -= 1;
        return data;
    }

    //The number_of_cards method returns the amount of cards in the stack.
    public int number_of_cards() {
        return this.stack_amount;
    }

}
