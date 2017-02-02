import java.util.Scanner;

class IntegerParser {

  private int length;
  private String raw;
  private int numbers[];

  public static void main(String[] args) {
    // For testing purposes
    int results[];
    IntegerParser p = new IntegerParser();

    results = p.matchProgram(36, "0 12 37 8 24");
    System.out.println(36 + ": " + p.getRaw());
    System.out.println("\t" + results[0] + " " + results[1]);

    results = p.matchProgram(12, "0 12");
    System.out.println(12 + ": " + p.getRaw());
    System.out.println("\t" + results[0] + " " + results[1]);

    results = p.matchProgram(24, "24");
    System.out.println(24 + ": " + p.getRaw());
    System.out.println("\t" + results[0] + " " + results[1]);

    results = p.matchProgram(38, "0 12 37 8 24");
    System.out.println(38 + ": " + p.getRaw());
    System.out.println("\t" + results[0] + " " + results[1]);

    results = p.matchProgram(32, "0 12 37 8 24");
    System.out.println(32 + ": " + p.getRaw());
    System.out.println("\t" + results[0] + " " + results[1]);
  }

  public IntegerParser() {
    length = 0;
    numbers = null;
  }
  public IntegerParser(String s) {
    raw = s;
    length = 0;
    numbers = null;
  }

  public int getLength() {return length;}
  public String getRaw() {return raw;}
  public int[] getNumbers() {return numbers;}

  public void setRaw(String s) {raw = s; length = 0; numbers = null;}

  private void parseNumbers() {
    // Uses linked list class below to dynamically add values, before
    // creating an array
    LinkedList list = new LinkedList();
    Scanner parser = new Scanner(raw);
    while (parser.hasNextInt()) {
      list.append(parser.nextInt());
    }
    numbers = list.makeArray();
    length = list.length;
  }

  private int[] findMatch(int match) {
    // return [-1, -1] if no match, else it returns the indices that add
    // up to the value
    //
    // a single integer will be unable to add to anything, because there are
    // no numbers to add it with
    int end[] = {-1, -1};
    for (int i = 0; i < length-1; i++) {
      for (int j = i+1; j < length; j++) {
        if (numbers[i] + numbers[j] == match) {
          end[0] = i; end[1] = j;
          return end;
        }
      }
    }
    return end;
  }

  public int[] matchProgram(int match, String s) {
    // where users ought to interact with the program
    setRaw(s);
    parseNumbers();
    return findMatch(match);
  }
}

// Helper classes

class LinkedList {
  // One Way Linked List
  // Simple model for this code
  private Node start;
  private Node end;
  public int length;

  public LinkedList() {
    start = null;
    end = null;
    length = 0;
  }

  public void setFirst(Node n) {
    start = n;
    end = n;
    length = 1;
  }

  public void append(Node n) {
    if (length != 0) {
      end.setNext(n);
      end = end.getNext();
      length++;
    }
    else {setFirst(n);}
  }
  public void append(int number) {
    Node n = new Node(number);
    append(n);
  }

  public int[] makeArray() {
    if (length == 0) {return null;}
    int end[] = new int[length];
    Node current = start;
    for (int i = 0; i < length; i++) {
      end[i] = current.getValue();
      current = current.getNext();
    }
    return end;
  }


}

class Node {
  // nodes of the linked list
  private int value;
  private Node next;

  public Node(int number) {
    value = number;
    next = null;
  }

  public int getValue() {return value;}
  public Node getNext() {return next;}
  public void setValue(int v) {value = v;}
  public void setNext(Node n) {next = n;}

}
