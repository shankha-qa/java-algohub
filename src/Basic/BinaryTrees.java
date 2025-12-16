package Basic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class BinaryTrees {

    private static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    //Display Tree
    public static void displayTree(Node node) {
        if (node == null) {
            return;
        }

        String str = "";
        str += node.left == null ? "." : node.left.data + "";
        str += "<- " + node.data + " ->";
        str += node.right == null ? "." : node.right.data + "";
        System.out.println(str);

        displayTree(node.left);
        displayTree(node.right);
    }

    //Find Size
    public static int size(Node node) {
        if (node == null) {
            return 0;
        }

        int ls = size(node.left);
        int rs = size(node.right);
        int res = ls + rs + 1;

        return res;
    }

    //Find Sum
    public static int sum(Node node) {
        if (node == null) {
            return 0;
        }

        int ls = sum(node.left);
        int rs = sum(node.right);
        int res = ls + rs + node.data;

        return res;
    }

    //Find Max
    public static int max(Node node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }

        int lm = max(node.left);
        int rm = max(node.right);
        int res = Math.max(Math.max(lm,rm), node.data);

        return res;
    }

    //Find Height
    public static int height(Node node) {
        if (node == null) {
            return -1;
        }
        int lht = height(node.left);
        int rht = height(node.right);
        int ht = Math.max(lht, rht);

        ht += 1;

        return ht;
    }

    //Pre Order Traversal
    public static void printPreOrderTraversal(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + "\t");
        printPreOrderTraversal(node.left);
        printPreOrderTraversal(node.right);
    }

    //In Order Traversal
    public static void printInOrderTraversal(Node node) {
        if (node == null) {
            return;
        }
        printInOrderTraversal(node.left);
        System.out.print(node.data + "\t");
        printInOrderTraversal(node.right);
    }

    //Post Order Traversal
    public static void printPostOrderTraversal(Node node) {
        if (node == null) {
            return;
        }
        printPostOrderTraversal(node.left);
        printPostOrderTraversal(node.right);
        System.out.print(node.data + "\t");
    }

    //Level Order Traversal
    public static void levelOrderTraversal(Node node) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);
        while(!q.isEmpty()) {
            int temp = q.size();
            for (int i = 1; i <= temp; i++) {
                Node toBeRemoved = q.remove();
                System.out.print(toBeRemoved.data + "\t");
                if(toBeRemoved.left != null) {
                    q.add(toBeRemoved.left);
                }
                if(toBeRemoved.right != null) {
                    q.add(toBeRemoved.right);
                }
            }
            System.out.println();
        }
    }

    //FInd particular data
    public static boolean find (Node node, int target) {
        if (node == null) {
            return false;
        }
        if (node.data == target) {
            return true;
        }
        boolean lSide = find(node.left, target);
        if (lSide) {
            return true;
        }
        boolean rSide = find(node.left, target);
        if (rSide) {
            return true;
        }
        return false;
    }

    //Add Node to Root paths to ArrayLists and return
    public static ArrayList<Integer> nodeToRootPath (Node node, int target) {
        if (node == null) {
            return new ArrayList<>();
        }
        if (node.data == target) {
            ArrayList<Integer> match = new ArrayList<>();
            match.add(node.data);
            return match;
        }
        ArrayList<Integer> lSide = nodeToRootPath(node.left, target);
        if (!lSide.isEmpty()){
            lSide.add(node.data);
            return lSide;
        }
        ArrayList<Integer> rSide = nodeToRootPath(node.left, target);
        if (!rSide.isEmpty()){
            rSide.add(node.data);
            return rSide;
        }
        return new ArrayList<>();
    }

    //Print all nodes which are K Levels deep
    public static void printNodesKLevelsDeep (Node node, int k) {
        if (node == null || k < 0) {
            return;
        }
        if(k == 0){
            System.out.println(node.data);
        }
        printNodesKLevelsDeep(node.left,k-1);
        printNodesKLevelsDeep(node.right,k-1);
    }

    // Node to root path via node
    public static ArrayList<Node> nodeToRootPathViaNode (Node node, int target) {
        if (node == null) {
            return new ArrayList<>();
        }
        if (node.data == target) {
            ArrayList<Node> match = new ArrayList<>();
            match.add(node);
            return match;
        }
        ArrayList<Node> lSide = nodeToRootPathViaNode(node.left, target);
        if (!lSide.isEmpty()){
            lSide.add(node);
            return lSide;
        }
        ArrayList<Node> rSide = nodeToRootPathViaNode(node.left, target);
        if (!rSide.isEmpty()){
            rSide.add(node);
            return rSide;
        }
        return new ArrayList<>();
    }

    public static void printNodesKLevelsDeep (Node node, int k, Node blocker) {
        if (node == null || k < 0 || node == blocker) {
            return;
        }
        if(k == 0){
            System.out.println(node.data);
        }
        printNodesKLevelsDeep(node.left,k-1, blocker);
        printNodesKLevelsDeep(node.right,k-1, blocker);
    }

    //Print nodes K Distance Away
    public static void printNodesKDistanceAway (Node node, int target, int k) {
        ArrayList<Node> path = nodeToRootPathViaNode(node, target);
        for (int i = 0; i <= path.size() - 1; i++){
            printNodesKLevelsDeep(path.get(i), k-i, i == 0 ? null : path.get(i - 1));
        }
    }

    //Print All leaves path from node
    public static void printAllLeafPathFromANode(Node node, String path) {
        if(node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            path = path + node.data + "";
            System.out.println(path);
        }
        printAllLeafPathFromANode(node.left, path + node.data + "");
        printAllLeafPathFromANode(node.right, path + node.data + "");
    }

    //Check if both the trees are same
    public boolean isSameTree(Node p, Node q) {
        if (p == null || q == null) {
            return p == q;
        }
        if (p.data != q.data) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    //Print All leaves path from node within a certain range
    public static void printlLeafPathFromANodeBetweenRange(Node node, int sum, String path, int lo, int hi) {
        if(node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            sum += node.data;
            path = path + node.data + "";
            if (sum > lo && sum < hi) {
                System.out.println(path);
            }
        }
        printlLeafPathFromANodeBetweenRange(node.left, sum + node.data, path + node.data + "", lo, hi);
        printlLeafPathFromANodeBetweenRange(node.right, sum + node.data, path + node.data + "", lo, hi);
    }

    //Create Left cloned Tree
    public static Node createLeftClonedTree(Node node) {
        if(node == null) {
            return null;
        }
        Node lcr = createLeftClonedTree(node.left);
        Node rcr = createLeftClonedTree(node.right);
        Node nl = new Node(node.data, lcr, null);
        node.left = nl;
        node.right = rcr;

        return node;
    }

    //Convert back from Left cloned Tree
    public static Node convertBackFromLeftClonedTree(Node node) {
        if(node == null) {
            return null;
        }
        Node lcr = convertBackFromLeftClonedTree(node.left.left);
        Node rcr = convertBackFromLeftClonedTree(node.right);
        node.left = lcr;
        node.right = rcr;

        return node;
    }

    // Print all Single Child Nodes
    public static void printSingleChildNode(Node parent, Node node) {
        if(node == null) {
            return;
        }
        if (parent != null && parent.left == node && parent.right == null) {
            System.out.println(node.data);
        } else if (parent != null && parent.left == null && parent.right == node) {
            System.out.println(node.data);
        }

        printSingleChildNode(node, node.left);
        printSingleChildNode(node, node.right);
    }

    //Remove all leaf nodes
    public static Node removeLeaves(Node node) {
        if (node == null) {
            return null;
        }
        if(node.left == null && node.right == null) {
            return null;
        }
        Node nlr = removeLeaves(node.right);
        Node nrr = removeLeaves(node.left);
        node.left = nlr;
        node.right = nrr;
        return node;
    }

    // Calculate Diameter
    public static int calculateDiameter(Node node) {
        if (node == null) {
            return 0;
        }

        int ld = calculateDiameter(node.left);
        int rd = calculateDiameter(node.right);
        int f = height(node.left) + height(node.right) + 2;

        int dia = Math.max(Math.max(ld,rd), f);
        return dia;
    }

    static class DiaPair{
        int ht;
        int dia;
    }

    // Calculate Diameter - Optimized approach
    public static DiaPair calculateDiameter2(Node node) {
        if (node == null) {
            DiaPair dp = new DiaPair();
            dp.ht = -1;
            dp.dia = 0;
        }
        DiaPair lp = calculateDiameter2(node.left);
        DiaPair rp = calculateDiameter2(node.right);

        DiaPair mp = new DiaPair();
        mp.ht =  Math.max(lp.ht, rp.ht) + 1;
        mp.dia = Math.max(Math.max(lp.dia, rp.dia), lp.ht + rp.ht + 2);

        return mp;
    }

    // Tilt of a Tree
    static int tilt;
    public static int tilt(Node node){
        if (node == null){
            return 0;
        }
        int ls = tilt(node.left);
        int rs = tilt(node.left);

        tilt += Math.abs(ls-rs);

        int sum = ls + rs + node.data;
        return sum;
    }

    public static class BSTPair{
        boolean isBST;
        int min;
        int max;
    }

    // Check if a tree is BST or not
    public static BSTPair isBST(Node node) {
        if(node == null){
            BSTPair baseBSTPair = new BSTPair();
            baseBSTPair.isBST =  true;
            baseBSTPair.max = Integer.MIN_VALUE;
            baseBSTPair.min = Integer.MAX_VALUE;
        }

        BSTPair leftBST = isBST(node.left);
        BSTPair rightBST = isBST(node.right);

        BSTPair mp = new BSTPair();
        mp.isBST =  leftBST.isBST && rightBST.isBST && leftBST.max <= node.data && rightBST.min >= node.data;
        mp.min = Math.min(Math.min(leftBST.min, rightBST.min), node.data);
        mp.max = Math.max(Math.max(leftBST.max, rightBST.max), node.data);

        return mp;

    }

    public static class BalancedPair{
        boolean isBalanced;
        int ht;
    }

    // Check if a tree is Balanced or not
    public static BalancedPair isBalanced(Node node) {
        if(node == null){
            BalancedPair baseBalancedPair = new BalancedPair();
            baseBalancedPair.isBalanced = true;
            baseBalancedPair.ht = 0;
        }

        BalancedPair leftBalanced = isBalanced(node.left);
        BalancedPair rightBalanced = isBalanced(node.right);

        BalancedPair bp = new BalancedPair();
        bp.isBalanced =  leftBalanced.isBalanced && rightBalanced.isBalanced &&
                ( Math.abs(leftBalanced.ht - rightBalanced.ht) ) <= 1;
        bp.ht = Math.max(leftBalanced.ht, rightBalanced.ht) + 1;

        return bp;
    }

    public static class LargestBSTPair{
        boolean isBST;
        int min;
        int max;
        Node largestBSTRoot;
        int largestBSTSize;
    }

    // Check if a tree is BST or not and also the largest BSTsize and root of the same
    public static LargestBSTPair isLargestBST(Node node) {
        if(node == null){
            LargestBSTPair baseBSTPair = new LargestBSTPair();
            baseBSTPair.isBST =  true;
            baseBSTPair.max = Integer.MIN_VALUE;
            baseBSTPair.min = Integer.MAX_VALUE;
            baseBSTPair.largestBSTRoot = node;
            baseBSTPair.largestBSTSize =  0;

        }

        LargestBSTPair isLeftBST = isLargestBST(node.left);
        LargestBSTPair isRightBST = isLargestBST(node.right);

        LargestBSTPair mp = new LargestBSTPair();
        mp.isBST =  isLeftBST.isBST && isRightBST.isBST && isLeftBST.max <= node.data && isRightBST.min >= node.data;
        mp.min = Math.min(Math.min(isLeftBST.min, isRightBST.min), node.data);
        mp.max = Math.max(Math.max(isLeftBST.max, isRightBST.max), node.data);

        if (mp.isBST) {
            mp.largestBSTRoot = node;
            mp.largestBSTSize = isLeftBST.largestBSTSize + isRightBST.largestBSTSize + 1;
        } else if (isLeftBST.isBST) {
            mp.largestBSTRoot = isLeftBST.largestBSTRoot;
            mp.largestBSTSize = isLeftBST.largestBSTSize;
        }
        else if (isRightBST.isBST) {
            mp.largestBSTRoot = isRightBST.largestBSTRoot;
            mp.largestBSTSize = isRightBST.largestBSTSize;
        }

        return mp;

    }

}
