package Basic;

import java.util.HashMap;

public class LRUCache {

    class Node{
        int key , val;
        Node prev, next;
    }

    HashMap<Integer, Node> cache;
    int capacity;
    Node head, tail;

    public LRUCache(int capacity){
        cache = new HashMap<>();
        this.capacity =  capacity;
        head = new Node();
        tail =  new Node();
        head.next =  tail;
        tail.next = head;
    }

    private void addNode(Node node){ //addFirst
        Node nbr = head.next;

        head.next =  node;
        node.next =  nbr;

        node.prev =  head;
        nbr.prev =  node;
    }

    private void removeNode(Node node){ //removeFirst
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;

        node.prev = null;
        node.next = null;
    }

    private void moveToFront(Node node){
        removeNode(node);
        addNode(node);
    }

    public void put(int key, int val) {
        Node node = cache.get(key);

        if(node == null) {
            Node newNode =  new Node();
            newNode.key = key;
            newNode.val = val;
            if(cache.size() == this.capacity){
                Node LRU_Node = tail.prev;
                cache.remove(LRU_Node.key);
                removeNode(LRU_Node);
            }
            cache.put(key, newNode);
            addNode(newNode);
        }
        else {
            node.val = val;
            moveToFront(node);
        }
    }

    public int get(int key) {
        Node node = cache.get(key);

        if(node == null){
            return -1;
        } else {
            int val = node.val;
            moveToFront(node);
            return val;
        }
    }


}
