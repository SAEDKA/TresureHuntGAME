package saidgame;

public class Node {
    public int id;
    public String type;
    public Node next;
    public Node prev; // للدوبل لينكد لست

    public Node(int id, String type) {
        this.id = id;
        this.type = type;
        this.next = null;
        this.prev = null;
    }
}
