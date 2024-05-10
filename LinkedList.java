package LinkedList;

public class LinkedList{
  public static class Node{
    int data;
    Node next;
    public Node(int data) {
      this.data = data;
      this.next = null;
    }
  }
  public static Node head;
  public static Node tail;
  public  int size;

  public void addFirst(int data) {
    // Step 1 = create new node
    Node newNode = new Node(data);
    size++;
    if(head == null) {
      head = tail = newNode;
      return;
    }
    // Step 2 = newNode = head
    newNode.next = head;
    // Step 3 = head
    head = newNode;
  }

  public void addLast(int data) {
    // Step 1 = create new node
    Node newNode = new Node(data);
    size++;
    if(head == null) {
      head = tail = newNode;
      return;
    }
    // Step 2 = newNode = tail
    tail.next = newNode;
    // Step 3 = tail
     tail = newNode;
  }

  //addIn_middle
  public void middleAdd(int idx, int data) {
    Node newNode = new Node(data);
    size++;
    Node temp = head;
    int i = 0;

    while(i < idx-1) {
      temp = temp.next;
      i++;
    }

    // i = idx-1; temp -> prev
    newNode.next = temp.next;
    temp.next = newNode;
  }

  //Remove First
  public int removeFirst() {
    if(size == 0) {
      System.out.println("LL is empty");
      return Integer.MIN_VALUE;
    }
    else if(size == 1) {
      int val = head.data;
      head = tail = null;
      size = 0;
      return val;
    }
    int val = head.data;
    head = head.next;
    size--;
    return val;
  }

  //Remove Last
  public int removeLast() {
    if(size == 0) {
      System.out.println("LL is empty");
      return Integer.MIN_VALUE;
    }
    else if(size == 1) {
      int val = head.data;
      head = tail = null;
      size = 0;
      return val;
    }

    //prev : i = size-2
    Node prev = head;
    for(int i=0; i<size-2; i++) {
      prev = prev.next;
    }
    int val = prev.next.data;
    prev.next = null;
    tail = prev;
    size--;
    return val; 
  }

  // Search (Iterative)
  public int itrSearch(int key) {
    Node temp = head;
    int i=0;

    while(temp != null) {
      if(temp.data == key) {
        return i;
      }
      temp = temp.next;
      i++;
    }
    return -1;
  }

  //Search (Recursive)
  public int helper(Node head, int key) {
    if(head == null) {
      return -1;
    }

    if(head.data == key) {
      return 0;
    }

    int idx = helper(head.next, key);
    if(idx == -1) {
      return -1;
    }
    return idx+1;
  }
  public int recSearch(int key) {
    return helper(head, key);
  }

  //Reverse a LinkedList
  public void reverse() {
    Node prev = null;
    Node curr = tail = head;
    Node next;
    while(curr != null) {
      next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    head = prev;
  }

  //Delete Nth node from end
  public void deleteNthfromEnd(int n) {
    //calcute size
    int sz = 0;
    Node temp = head;
    while(temp != null) {
      temp = temp.next;
      sz++;
    }

    //corner case
    if(n == sz) {
      head = head.next;
      return;
    }
    
    //sz-n
    int i = 1;
    int iToFind = sz-n;
    Node prev = head;
    while(i < iToFind) {
      prev = prev.next;
      i++;
    }

    prev.next = prev.next.next;
    return;
  }

  // Check Palindrome
  public Node findMid(Node head) {
    Node slow = head;
    Node fast = head;
    while(fast!=null && fast.next!=null) {
      slow = slow.next;
      fast = fast.next;
    }
    return slow; //slow is my midNode
  }
  public boolean cheackPalindrom() {
  if(head==null || head.next==null) {
    return true;
  }

  //step 1 - find mid
  Node midNode = findMid(head);

  //step 2 - reverse 2nd half
  Node prev = null;
  Node curr = midNode;
  Node next;
  while(curr != null) {
    next = curr.next;
    curr.next = prev;
    prev = curr;
    curr = next;
  }

  Node right = prev; // right half head
  Node left = head;

  //stemp 3 - check left half & right half
  while(right != null) {
    if(left.data != right.data) {
      return false;
    }
    left = left.next;
    right = right.next;
  }
  return true;
}

//Detect a cycle in a LinkedList
public static boolean isCycle() {
  Node slow = head;
  Node fast = head;
  while(fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
    if(slow == fast) {
      return true;
    }
  }
  return false;
}

// Remove a cycle in a LinkedList
public static void removeCycle() {
  //detect cycle
  Node slow = head;
  Node fast = head;
  boolean cycle = false;
  while(fast != null & fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
    if(fast == slow) {
      cycle = true;
      break;
    }
  }
  if(cycle == false) {
    return;
  }

  //find metting point
  slow = head;
  Node prev = null;
  while(slow != fast) {
    prev = fast;
    slow = slow.next;
    fast = fast.next;
  }
  //remove cycle -> last.next = null
  prev.next = null;
}

//Merge Sort on LinkedList
  private Node getMid(Node head) {
    Node slow = head;
    Node fast = head.next;
    while(fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow; //mid
  }

  private Node merge(Node head1, Node head2) {
    Node mergedLL = new Node(-1);
    Node temp = mergedLL;

    while(head1 != null && head2 != null) {
      if(head1.data <= head2.data) {
        temp.next = head1;
        head1 = head1.next;
        temp = temp.next;
      }else{
        temp.next = head2;
        head2 = head2.next;
        temp = temp.next;
      }
    }
    while(head1 != null) {
      temp = head1;
      head1 = head1.next;
      temp = temp.next;
    }
    while(head2 != null) {
      temp = head2;
      head2 = head2.next;
      temp = temp.next;
    }
    return mergedLL.next;
  }
  public Node mergeSort(Node head) {
    if(head==null || head.next == null) {
      return head;
    }
    //find mid
    Node mid = getMid(head);
    //left & right MS
    Node rightHead = mid.next;
    mid.next = null;
    Node newLeft = mergeSort(head);
    Node newRight = mergeSort(rightHead);

    //merge
    return merge(newLeft, newRight);
  }

  //Zig Zag LinkedList
  //find mid
  public void zigZag() {
    Node slow = head;
    Node fast = head.next;
    while(fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    Node mid = slow;

    //reverse 2nd half
    Node curr = mid.next;
    mid.next = null;
    Node prev = null;
    Node next;

    while(curr != null) {
      next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }

    Node left = head;
    Node right = prev;
    Node nextL, nextR;

    //alt merge - zig-Zag merge
    while(left!=null && right != null) {
      nextL = left.next;
      left.next = right;
      nextR = right.next;
      right.next = nextL;

      //updation
      left = nextL;
      right = nextR;
    }
}

  //Print LinkedList
  public void print() {
    if(head==null) {
      System.out.println("LL is Empty");
    }
    Node temp = head;
    while(temp != null) {
      System.out.print(temp.data+"->");
      temp = temp.next;
    }
    System.out.println("null");
  }

  public static void main(String args[]) {
    LinkedList ll = new LinkedList();
    // ll.addFirst(2);
    // ll.addFirst(1);
    // ll.addLast(3);
    // ll.addLast(4);
    // ll.addLast(5);

    // ll.middleAdd(2, 9);
    // ll.print();
    // // size
    // // System.out.println(ll.size);
    // ll.removeFirst();
    // ll.print();

    // ll.removeLast();
    // ll.print();
    // System.out.println(ll.size);

    // ll.print();
    // System.out.println(ll.itrSearch(3));
    // System.out.println(ll.itrSearch(8));

    // ll.print();
    // System.out.println(ll.recSearch(4));
    // System.out.println(ll.recSearch(7));

    // ll.reverse();
    // ll.print();

    // ll.deleteNthfromEnd(3);
    // ll.print();
/* 
    //check Palindrome
    ll.addLast(1);
    ll.addLast(2);
    ll.addLast(2);
    ll.addLast(1);

    ll.print();
    System.out.println(ll.cheackPalindrom());
*/

/* 
//Detect loop
    head = new Node(1);
    head.next = new Node(2);
    head.next.next = new Node(3);
    head.next.next.next = head;    
*/

/* 
    //Remove a cycle in a LinkedList
    head = new Node(1);
    Node temp = new Node(2);
    head.next = temp;
    head.next.next = new Node(3);
    head.next.next.next = temp;

    System.out.println(isCycle());
    removeCycle();                
    System.out.println(isCycle());   
*/               

/*
//Merge Sort on LinkedList
    ll.addFirst(1);
    ll.addFirst(2);
    ll.addFirst(3);
    ll.addFirst(4);
    ll.addFirst(5);

    ll.print();
    ll.head = ll.mergeSort(ll.head);
    ll.print();
 */

  ll.addLast(1);
  ll.addLast(2);
  ll.addLast(3);
  ll.addLast(4);
  ll.addLast(5);
  ll.addLast(6);

  ll.print();
  ll.zigZag();
  ll.print();
  }
}
