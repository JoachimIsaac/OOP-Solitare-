public class RecordStack {
    //Stack using linked list

    //Linked list node
    private class Node{
        private String val;
        private Node next = null;
        private Node(String data){
            this.val = data;
        }
    }

    //Top reference variable.
   private Node top;
    //Property to keep track of stack contents
   private int stack_amount;

    //Constructor
    RecordStack(){
        this.top = null;
        this.stack_amount = 0;
    }

    public void push(String card){

        Node node = new Node(card);

           if(node == null) {
               System.out.println("\n Stack overFlow");
               return;
           }
           node.next = top;
           top = node;
           stack_amount += 1;
    }

    public boolean isEmpty(){

        //If top points to null then it is empty if it doesn't point to null then it has contents.
        return this.top == null;
    }

    public String peek(){

        if(!isEmpty()){
            return top.val;
        }
        else {
            System.out.println("The Stack is Empty");
            return null;
        }
    }


    public String pop(){

        if(top == null){
            System.out.println("\nStack Underflow!");
        }

        String data = top.val;
        top = top.next;
        stack_amount -= 1;
        return data;

    }

    public int number_of_cards(){
        return this.stack_amount;
    }

    public void empty_stack(){

        if(!isEmpty()){
            //Implement how it empties the stack.
            while(this.stack_amount > 0){
                this.pop();
            }

        }
        else {
            System.out.println("The Stack is Empty");
        }

    }


}
