package Basic;

import java.util.ArrayList;

public class BinarySearchTrees {

    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static Node createBinarySearchTree(int[] arr, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        int mid = (lo + hi)/2;
        int data = arr[mid];
        Node left = createBinarySearchTree(arr, lo, mid - 1);
        Node right = createBinarySearchTree(arr, mid + 1, hi);
        Node root = new Node(data, left, right);

        return root;
    }

    //Size
    public static int size(Node node) {
        if (node == null) {
            return 0;
        }

        int ls = size(node.left);
        int rs = size(node.right);
        int res = ls + rs + 1;

        return res;
    }

    //Sum
    public static int sum(Node node) {
        if (node == null) {
            return 0;
        }

        int ls = sum(node.left);
        int rs = sum(node.right);
        int res = ls + rs + node.data;

        return res;
    }

    //Max
    public static int max(Node node) {
        if (node.right != null) {
            return max(node.right);
        } else {
            return node.data;
        }
    }

    //Min
    public static int min(Node node) {
        if (node.left != null) {
            return min(node.left);
        } else {
            return node.data;
        }
    }

    // Check existence of Data in a BST
    public static boolean find(Node node, int target) {
        if (node == null) {
            return false;
        }
        if (node.data > target) {
            return find(node.left, target);
        }
        else if (node.data < target) {
            return find(node.left, target);
        }
        else {
            return true;
        }
    }

    // Add a node in a BST
    public static Node addNodeToABST(Node node, int data) {
        if (node == null) {
            return new Node(data, null, null);
        }

        if (data >= node.data) {
            node.right = addNodeToABST(node.right, data);
        }
        else {
            node.left = addNodeToABST(node.left, data);
        }
        return node;
    }

    // Remove a node from a BST
    public static Node removeNodeFromABST(Node node, int data) {
        if (node == null) {
            return null;
        }

        if (data > node.data) {
            node.right = removeNodeFromABST(node.right, data);
        }
        else if (data < node.data) {
            node.left = removeNodeFromABST(node.left, data);
        }
        else {
            if (node.left != null && node.right != null) {
                int lmax = max(node.left);
                node.data = lmax;
                node.left = removeNodeFromABST(node.left, lmax);
            } else if (node.left != null) {
                return node.left;
            } else if (node.right != null) {
                return node.right;
            } else {
                return null;
            }
        }
        return node;
    }

    static int sum = 0;
    public static Node replaceSumOfLargerBST(Node node) {
        if (node == null) {
            return null;
        }
        replaceSumOfLargerBST(node.right);
        int temp = node.data;
        node.data = sum;
        sum += temp;
        replaceSumOfLargerBST(node.left);

        return node;
    }

    // Find lowest common ancestor
    public static Node findLowestCommonAncestor(Node node, int data1, int data2) {
        if (node == null) {
            return null;
        }
        if (node.data > data1 && node.data > data2) {
            return findLowestCommonAncestor(node.left, data1, data2);
        } else if (node.data < data1 && node.data < data2) {
            return findLowestCommonAncestor(node.right, data1, data2);
        } else {
            return node;
        }
    }

    public static void printInRange(Node node, int lo, int hi) {
        if (node == null) {
            return;
        }
        if (node.data < lo) {
            printInRange(node.right, lo, hi);
        }
        else if (node.data > hi) {
            printInRange(node.left, lo, hi);
        }
        else {
            printInRange(node.left, lo, hi);
            System.out.println(node.data);
            printInRange(node.right, lo, hi);
        }
    }

    public static void printTargetSumPair(Node root, Node node, int target) {
        if(node == null) {
            return;
        }

        printTargetSumPair(root, node.left, target);
        int comp = target - node.data;
        if (comp > node.data) {
            if (find(root, comp)){
                System.out.println(node.data + "---" + comp);
            }
        }
        printTargetSumPair(root, node.right, target);
    }

    public static void addNodesToList( Node node, ArrayList<Integer> list) {
        if(node == null) {
            return;
        }

        addNodesToList(node.left, list);
        list.add(node.data);
        addNodesToList(node.right, list);
    }

    public static void printTargetSumPair2(Node node, int target) {
        ArrayList<Integer> list = new ArrayList<>();
        addNodesToList(node, list);

        int lo = 0;
        int hi = list.size() - 1;
        while (lo < hi) {
            if (list.get(lo) + list.get(hi) > target) {
                lo++;
            } else if (list.get(lo) + list.get(hi) < target) {
                hi--;
            } else {
                System.out.println(list.get(lo) + "----" + list.get(hi));
                lo++;
                hi--;
            }
        }

    }

}
