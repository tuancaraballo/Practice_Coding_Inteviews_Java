
class Node {
    String data;
    Node next;
    Node(String data, Node next){
        this.data = data;
        this.next = next;
    }
}
public class Main {

    /** Shortcut for printing **/
    public static <T> void print(T t){
        System.out.println(t);
    }

    /** Given a Linked List, m, n, reverse the sublinklist that's between m and n**/

    /**
     *  Approach:
     *   1- Separate the sublist, for this we need:
     *      - Node before start node
     *      - Start Node
     *      - End Node
     *      - Node that comes after End Node
     *   2- Set end_node.Next = null, so that the list is completely separated from the main list, while we keep the
     *      pointers to connect the sublist back
     *   3- Call a Recursive function that:
     *      - takes a previous pointer to its antecedent
     *      - takes current pointer
     *      - breaking condition is when current pointer is null (this is why we set end_node.Next = null
     *   4- Connect the sublist back to the Main List
     *      - set node_before_start_node.NEXT = end_node
     *      - set start_node.NEXT = node_after_end_node
     */

    public static void printLinkedList(Node head){
        if(head == null) return;
        Node current = head;

        while(current!= null){
            print(current.data);
            current = current.next;
        }
    }

    public static Node buildLinkedList(){
        Node fifth = new Node("E", null);
        Node fourth = new Node("D", fifth);
        Node third = new Node("C", fourth);
        Node second = new Node("B", third);
        Node first = new Node("A", second );

        return first;
    }

    private static void reverseList(Node previous, Node current){
        if(current == null) return;

        reverseList(current, current.next);

        current.next = previous;
    }
    // -assumes that end < length of list
    protected static void reverseLinkedList(Node head, int start, int end){
        if (head == null) return;
        if(start >= end) return;

        // We need 4 "pointers"  (see above explanation)
        /*
            1- Node before start node
            2- Start Node
            3- End Node
            4- Node after end Node
         */
        Node current = head;
        Node previous= head;
        int count = 0;
        //- Finding the start_node, using extra variables for the sake of clarity
        while(current != null){
            if(count+1 == start){
                break;
            }
            count++;
            previous = current;
            current = current.next;
        }
        Node before_start_node = previous;
        Node start_node = current;

        //- Finding end_node (could combine both while loop, but clarity is more important here)
        while(current != null){
            if(count+1 == end){
                break;
            }
            count++;
            current = current.next;
        }
        Node end_node = current;
        Node after_end_node = end_node.next;
        end_node.next = null;
        print("Start node: " + start_node.data);
        print("End node: " + end_node.data);

        reverseList(null, start_node);


        start_node.next = after_end_node;
        if(start == 1){  // --> reset head
            head = end_node;
        }
        if(start > 1 ){ //-- > if the sublinnk is not at the beginning
            before_start_node.next = end_node;
        }

        printLinkedList(head);

    }

    protected static void reverseSubLinklistSetup(){
        print("~~~~ ReverseSubLinkList ~~~");
        //1- Create LinkList
        Node head  = buildLinkedList();
        print("Printing linked list");
        printLinkedList(head);

        int start = 4;
        int end = 5;
        reverseLinkedList(head,start,end);


    }
    public static void main(String [] args){
        reverseSubLinklistSetup();
    }
}
