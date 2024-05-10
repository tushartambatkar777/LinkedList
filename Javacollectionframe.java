package LinkedList;
import java.util.LinkedList;

public class Javacollectionframe {
  public static void main(String args[]) {
    LinkedList<Integer> ll = new LinkedList<>();
    //add
    ll.addLast(1);
    ll.addLast(2);
    ll.addFirst(0);
    System.out.println(ll);

    //ll.removeLast
    ll.removeLast();
    ll.removeFirst();
    System.out.println(ll);
  }
}
