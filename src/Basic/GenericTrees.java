package Basic;

import java.util.*;

public class GenericTrees {

    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }

    // Construct a generic Tree
    public static Node createGenericTree(int[] arr) {

        Node root =  null;
        Stack<Node> st = new Stack<>();

        for(int i = 0; i <= arr.length - 1; i++) {

            if (arr[i] == -1){
                st.pop();
            }
            else {
                Node t = new Node();
                t.data = arr[i];
                if (st.isEmpty()) {
                    root = t;
                }
                else {
                    st.peek().children.add(t);
                }
                st.push(t);
            }
        }
        return root;
    }

    //Display Tree
    public static void displayTree(Node node) {
        String str = node.data + "->";
        for (Node child : node.children) {
            str += child.data + ", ";
        }
        str += ".";
        System.out.println(str);

        for (Node child : node.children) {
            displayTree(child);
        }
    }

    // Print Traversal of the Tree
    public static void printTraversal(Node node) {
        System.out.println("Node Pre " + node.data);
        for(Node child : node.children) {
            System.out.println("Edge Pre " + node.data + "----" + child.data);
            printTraversal(child);
            System.out.println("Edge Post " + node.data+ "----" + child.data);
        }
        System.out.println("Node Post " + node.data);
    }

    //Find Size
    public static int size(Node node) {
        int s = 0;
        for (Node child : node.children) {
            int cs = size(child);
            s += cs;
        }
        s += 1;

        return s;
    }

    //Find Max
    public static int max(Node node) {
        int max = Integer.MIN_VALUE;
        for (Node child : node.children) {
            max = Math.max(max, max(child));
        }
        max = Math.max(max, node.data);

        return max;
    }

    //Find height
    public static int height(Node node) {
        int ht = -1;
        for (Node child : node.children) {
            ht = Math.max(ht, height(child));
        }
        ht += 1;

        return ht;
    }

    //Level Order traversal
    public static void levelOrderTraversal(Node node) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);
        while(q.size() > 0) {
            Node toBeRemoved = q.remove();

            System.out.print(toBeRemoved.data + "\t");

            for(Node child : toBeRemoved.children) {
                q.add(child);
            }
        }
    }

    //Level Order traversal - Linewise
    public static void levelOrderTraversalLineWise(Node node) {
        Queue<Node> mainQ = new ArrayDeque<>();
        Queue<Node> helperQ = new ArrayDeque<>();
        mainQ.add(node);
        while(mainQ.size() > 0) {
            Node toBeRemoved = mainQ.remove();

            System.out.print(toBeRemoved.data + "\t");

            for(Node child : toBeRemoved.children) {
                helperQ.add(child);
            }

            if(mainQ.isEmpty()) {
                System.out.println();
                while(!helperQ.isEmpty()) {
                    mainQ.add(helperQ.remove());
                }
            }
        }
    }

    //Level Order traversal - Zigzag
    public static void levelOrderTraversalZigzag(Node node) {
        Stack<Node> mainS = new Stack<>();
        Stack<Node> helperS = new Stack<>();
        mainS.add(node);
        int level = 1;

        while(mainS.size() > 0) {
            Node toBeRemoved = mainS.pop();

            System.out.print(toBeRemoved.data + "\t");

            if (level % 2 == 1){
                for (int i = 0; i <= toBeRemoved.children.size()-1; i++) {
                    helperS.push(toBeRemoved.children.get(i));
                }
            }else {
                for (int i = toBeRemoved.children.size()-1; i >=0 ; i--) {
                    helperS.push(toBeRemoved.children.get(i));
                }
            }

            if(mainS.isEmpty()) {
                mainS = helperS;
                helperS =  new Stack<>();
                level ++;
                System.out.print(toBeRemoved.data);
            }
        }
    }

    //Mirror a Tree
    public static void mirror(Node node) {
        for(Node child: node.children) {
            mirror(child);
        }
        Collections.reverse(node.children);
    }

    //Remove all Leaf Nodes
    public static void removeLeaves(Node node) {
        for (int i = node.children.size() - 1; i >= 0; i--) {
            Node child = node.children.get(i);
            if(child.children.isEmpty()) {
                node.children.remove(child);
            }
        }
        for(Node child: node.children) {
            removeLeaves(child);
        }
    }

    public static Node getTail(Node node) {
        while(node.children.size() == 1) {
            node = node.children.get(0);
        }
        return node;
    }

    //Linearize or eifjcbflrhcnvhrjnveuufiuuhkftjuknbuncfdljulr
    // Straighten a Tree
    public static void linearize(Node node) {
        for(Node child: node.children) {
            linearize(child);
        }

        while(node.children.size() > 1) {
            Node ln = node.children.remove(node.children.size() - 1);
            Node sl = node.children.get(node.children.size() - 1);
            Node sltail = getTail(sl);
            sltail.children.add(ln);
        }
    }

    //Find a data
    public static boolean find (Node node, int target) {
        if (node.data == target) {
            return true;
        }
        for(Node child: node.children) {
            boolean resultForChildren = find(child, target);
            if (resultForChildren) {
                return true;
            }
        }
        return false;
    }

    // Save Node to root paths in an Arraylist
    public static ArrayList<Integer> nodeToRootPath (Node node, int target) {
        if (node.data == target) {
            ArrayList<Integer> path = new ArrayList<>();
            path.add(node.data);
            return path;
        }
        for(Node child: node.children) {
            ArrayList<Integer> cPath =  nodeToRootPath(child, target);
            if (!cPath.isEmpty()) {
                cPath.add(node.data);
                return cPath;
            }
        }
        return new ArrayList<>();
    }

    // Find Lowest Common Ancestor
    public static Integer lowestCommonAncestor (Node node, int target1, int target2) {
        ArrayList<Integer> nRP1 = nodeToRootPath(node, target1);
        ArrayList<Integer> nRP2 = nodeToRootPath(node, target2);

        int i = nRP1.size() - 1;
        int j = nRP2.size() - 1;
        while (i > 0 && j > 0 && nRP1.get(i) == nRP2.get(j)) {
            i--;
            j--;
        }
        i++;
        return nRP1.get(i);
    }

    //Find distance between nodes
    public static Integer distanceBetween2Nodes (Node node, int target1, int target2) {
        ArrayList<Integer> nRP1 = nodeToRootPath(node, target1);
        ArrayList<Integer> nRP2 = nodeToRootPath(node, target2);

        int i = nRP1.size() - 1;
        int j = nRP2.size() - 1;
        while (i > 0 && j > 0 && nRP1.get(i) == nRP2.get(j)) {
            i--;
            j--;
        }
        i++;
        j++;
        return i+j;
    }

    // Are Similar ?
    public static boolean areSimilar (Node node1, Node node2) {
        if(node1.children.size() != node2.children.size()) {
            return false;
        }
        for (int i = 0; i < node1.children.size(); i++) {
            Node child1 = node1.children.get(i);
            Node child2 = node2.children.get(i);
            if (!areSimilar(child1, child2)) {
                return false;
            }
        }
        return true;
    }

    //Are Mirror ?
    public static boolean areMirror (Node node1, Node node2) {
        if(node1.children.size() != node2.children.size()) {
            return false;
        }
        for (int i = 0; i < node1.children.size(); i++) {
            int j = node2.children.size() - 1 - i;
            Node child1 = node1.children.get(i);
            Node child2 = node2.children.get(j);
            if (!areMirror(child1, child2)) {
                return false;
            }
        }
        return true;
    }

    // Is Symmetric ?
    public static boolean isSymmetric (Node node) {
        return areMirror(node,node);
    }

    //Find Predecessor and Successor of a target value
    static Node predecessor;
    static Node successor;
    static int state = 0;
    public static void findPredecessorAndSuccessor (Node node, int target) {
        if( state == 0) {
            if (node.data == target) {
                state = 1;
            }
            else {
                predecessor = node;
            }
        }
        else if( state == 1) {
            successor = node;
            state = 2;
        }
        else if( state == 2) {
            return;
        }

        for(Node child: node.children) {
            findPredecessorAndSuccessor(child, target);
        }
    }

    //Find Ceil and Floor of a target value
    static int ceil = Integer.MAX_VALUE;
    static int floor = Integer.MIN_VALUE;
    public static void findCeilAndFloor (Node node, int target) {
        if (node.data > target && node.data < ceil) {
            ceil = node.data;
        }
        if (node.data < target && node.data > floor) {
            floor = node.data;
        }
        for (Node child : node.children) {
            findCeilAndFloor(child, target);
        }
    }

    static int ffloor = Integer.MIN_VALUE;
    public void findFloor(Node node, int target){
        if (node.data < target && node.data > ffloor) {
            ffloor =  node.data;
        }
        for (Node child : node.children) {
            findFloor(child, target);
        }
    }

    //Find K-th Largest Element
    public int findKthLargestElement(Node node, int k){
        int factor = Integer.MAX_VALUE;
        for (int i = 1; i <= k; i++) {
            findFloor(node, factor);
            factor = ffloor;
        }
        return factor;
    }

    //Find Max Sum Subtree
    static int maxSumNode;
    static int maxSum;
    public int nodeWithMaximumSumSubtree(Node node) {
        int sum = 0;
        for (Node child : node.children) {
            int csum = nodeWithMaximumSumSubtree(child);
            sum += csum;
        }
        sum += node.data;

        if (sum > maxSum) {
            maxSum = sum;
            maxSumNode =  node.data;
        }
        return sum;
    }

    static int diameter;
    public static int calculateDiaReturnHeight(Node node) {
        int dch = -1;
        int sdch = -1;
        for (Node child : node.children) {
            int cht = calculateDiaReturnHeight(child);
            if (cht > dch) {
                sdch = dch;
                dch = cht;
            }
            else if (cht > sdch) {
                sdch = cht;
            }
        }
        if (dch + sdch + 2 > diameter) {
            diameter =  dch + sdch + 2;
        }
        dch += 1;
        return dch;
    }

}
