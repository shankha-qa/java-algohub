package Basic;

public class LinkedList {

    public static class Node {
        Node next;
        int data;
        Node random;

        public Node(int data) {
            this.data = data;
        }

        public Node() {
        }
    }

    public static class LL {
        Node head;
        Node tail;
        int size;

        //Size of a Linked List
        int size(){
            return size;
        }

        //Display a Linked List
        void display(){
            Node temp = head;
            while (temp != null) {
                System.out.println(temp.data + "\t");
                temp = temp.next;
            }
        }

        //Display a Linked List in a recursive Way
        void displayLLRecursively(Node head){
            if(head == null) {
                return;
            }
            System.out.print(head.data + "\t");
            displayLLRecursively(head.next);
        }

        //Display a Reversed Linked List in a recursive Way
        void displayReversedLLRecursively(Node head){
            if(head == null) {
                return;
            }
            displayReversedLLRecursively(head.next);
            System.out.print(head.data + "\t");
        }

        //Get Linked list value at a Index
        int getValueAtIndex(int index){
            if(size == 0) {
                System.out.println("Linked List underflow");
                return -1;
            }
            else if ( size <= index ) {
                System.out.println("Linked List is not big enough");
                return -1;
            }
            else if ( index < 0 ) {
                System.out.println("Index is too small to operate with");
                return -1;
            }
            else {
                int counter = 0;
                Node temp = head;
                while (counter < index) {
                    temp = temp.next;
                    counter++;
                }
                return temp.data;
            }
        }

        //Add First
        void addFirst(int val) {
            Node temp = new Node();
            temp.data = val;
            temp.next =  null;

            if(size == 0) {
                head = tail = temp;
            }
            else {
                temp.next =  head;
                head = temp;
            }
            size ++;
        }

        //Add Last
        void addLast(int val) {
            Node temp = new Node();
            temp.data = val;
            temp.next =  null;

            if(size == 0) {
                head = tail = temp;
            }
            else {
                tail.next = temp;
                tail = temp;
            }
            size ++;
        }

        //Add at any index
        void addAtAnyIndex(int val, int index) {
            if(size == 0) {
                System.out.println("Linked List underflow");
            }
            else if ( size <= index ) {
                System.out.println("Linked List is not big enough");
            }
            else if ( index < 0 ) {
                System.out.println("Index is too small to operate with");
            }
            else {
                Node node = new Node();
                node.data = val;

                Node temp = head;
                for (int i = 0; i < index - 1; i++) {
                    temp = temp.next;
                }
                node.next = temp.next;
                temp.next = node;
            }
        }

        //Remove First
        int removeFirst(){
            if(size == 0) {
                System.out.println("Linked List underflow");
                return -1;
            }
            else if(size == 1) {
                int val = head.data;
                head = tail = null;
                size --;
                return val;
            }
            else {
                Node temp = head;
                head = head.next;
                temp.next = null;

                size--;
                return temp.data;
            }
        }

        //Remove Last
        int removeLast(){
            if(size == 0) {
                System.out.println("Linked List underflow");
                return -1;
            }
            else if(size == 1) {
                int val = head.data;
                head = tail = null;
                size --;
                return val;
            }
            else {
                Node temp = head;
                for (int i = 0 ; i < size - 2; i++) {
                    temp = temp.next;
                }
                int val = temp.next.data;
                temp.next = null;
                tail =  temp;
                size --;
                return val;
            }
        }

        //Remove from any index
        int removeFromAnyIndex(int index){
            if(size == 0) {
                System.out.println("Linked List underflow");
                return -1;
            }
            else if ( size <= index ) {
                System.out.println("Linked List is not big enough");
                return -1;
            }
            else if ( index < 0 ) {
                System.out.println("Index is too small to operate with");
                return -1;
            }
            else {
                Node temp = head;
                for (int i = 0 ; i < index - 1; i++) {
                    temp = temp.next;
                }
                Node node = temp.next;
                int val = node.data;
                temp.next = node.next;
                node.next = null;

                size --;
                return val;
            }
        }

        private Node getNodeAt(int index) {
            Node temp = head;
            if(index == 0){
                return temp;
            }
            for (int i = 1 ; i < index - 1; i++) {
                temp =  temp.next;
            }
            return temp;
        }

        //Reverse a Linked List Data Iteratively
        public void reverseLinkedListDI(){
            int li = 0;
            int ri = size - 1;
            while(li < ri){
                Node left = getNodeAt(li);
                Node right = getNodeAt(ri);

                int temp = left.data;
                left.data = right.data;
                right.data =  temp;

                li++;
                ri--;
            }
        }

        //Reverse a Linked List Data Recursively
        public void reverseLinkedListDIRecursiveHelper(Node right, int floor) {
            if (right == null) {
                return;
            }
            reverseLinkedListDIRecursiveHelper(right.next, floor + 1);
            if (floor >= size /2) {
                int temp = right.data;
                right.data = rleft.data;
                rleft.data = temp;

                rleft = rleft.next;
            }
        }

        Node rleft;
        public void reverseLinkedListDIRecursive() {
            rleft = head;
            reverseLinkedListDIRecursiveHelper(head,0);
        }

        //Reverse a Linked List Pointer Iteratively
        public void reverseLinkedListPI() {
            Node prev = null;
            Node curr = head;

            while (curr != null) {
                Node next = curr.next;
                curr.next = prev;

                prev = curr;
                curr = next;
            }
        }

        //Reverse a Linked List Pointer Recursively
        public void reverseLinkedListPIRecursiveHelper(Node node) {
            while(node.next == null) {
                return;
            }
            reverseLinkedListPIRecursiveHelper(node.next);
            node.next.next = node;
        }

        public void reverseLinkedListPIRecursive() {
            reverseLinkedListPIRecursiveHelper(head);
            head.next =  null;
            Node temp = tail;

            tail = head;
            head =  temp;
        }

        //Find K-th Node from Last without using size property
        public int kThNodeFromReverse(int k){
            Node slow =  head;
            Node fast = head;
            for (int i= 0; i < k; i++) {
                fast = fast.next;
            }
            while(fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow.data;
        }

        //Find Middle of the linked List
        public int findMidOfLinkedList(){
            Node slow =  head;
            Node fast = head;
            while(fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow.data;
        }

        //Check if Linked List has a loop in it
        public static boolean loopCheck(Node head){
            if (head == null || head.next == null) {
                return false;
            }

            Node slow = head;
            Node fast = head.next;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {
                    return true;
                }
            }

            return false;
        }

        //Merge 2 sorted Linked List into one
        public static LL merge2SortedLists(LL l1, LL l2){
            Node one = l1.head;
            Node two = l2.head;

            LL result = new LL();
            while(one != null && two != null) {
                if (one.data <= two.data) {
                    result.addLast(one.data);
                    one = one.next;
                }
                else {
                    result.addLast(two.data);
                    two = two.next;
                }
            }
            while(one != null) {
                result.addLast(one.data);
                one = one.next;
            }
            while(two != null) {
                result.addLast(two.data);
                two = two.next;
            }
            return result;
        }

        public static Node findMidNode(Node head, Node tail) {
            Node slow =  head;
            Node fast = head;
            while(fast != tail && fast.next != tail) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        //Merge Sort of a Linked List into one
        public static LL mergeSortOfAList(Node head, Node tail){
            if(head == tail) {
                LL bres = new LL();
                bres.addLast(head.data);
                return bres;
            }
            Node mid = findMidNode(head, tail);
            LL fsh = mergeSortOfAList(head, mid);
            LL ssh = mergeSortOfAList(mid, tail);

            return merge2SortedLists(fsh, ssh);
        }

        //Remove Duplicates from a sorted list
        public static LL removeDuplicates(LL duplicate){
            LL result = new LL();

            while (duplicate.size > 0) {
                int data = duplicate.removeFirst();
                if(result.size == 0 || result.tail.data != data) {
                    result.addFirst(data);
                }
            }
            return result;
        }

        //Odd-Even number seperation in a list
        public static boolean isOdd(int number) {
            if (number % 2 == 0) {
                return false;
            }
            return true;
        }

        public static LL oddEvenSeperation(LL oddEven){
            LL odd = new LL();
            LL even = new LL();

            while (oddEven.size > 0) {
                int data = oddEven.removeFirst();
                if(isOdd(data)) {
                    odd.addLast(data);
                }
                else {
                    even.addLast(data);
                }
            }

            if (even.size == 0 && odd.size > 0) {
                return odd;
            } else if (odd.size == 0 && even.size > 0) {
                return even;
            }
            else if (odd.size == 0 && even.size == 0) {
                return null;
            }
            else {
                odd.tail.next = even.head;
                return odd;
            }
        }

        //Reverse the list in a group of K
        public static LL reverseTheListInGroupOfK(LL original, int k) {
            LL previous = null;

            while(original.size() > 0) {
                LL current = new LL();
                if(original.size > k) {
                    for (int i = 0; i < k; i++) {
                        int data = original.removeFirst();
                        current.addFirst(data);
                    }
                }
                else {
                    int os = original.size;
                    for (int i = 0; i < os; i++) {
                        int data = original.removeFirst();
                        current.addLast(data);
                    }
                }

                if(null == previous) {
                    previous = current;
                }
                else {
                    previous.tail.next = current.head;
                    previous.tail = current.tail;
                    previous.size += current.size;
                }
            }
            return previous;
        }

        Node pleft;
        public boolean isThisLLAPalindrome(Node head){
            pleft =  head;
            return palindromeChecker(head);
        }

        //Check if a Linked List is Palindrome
        public boolean palindromeChecker(Node right) {
            if(right == null) {
                return true;
            }
            boolean rres = palindromeChecker(right.next);
            if(!rres) {
                return false;
            }
            else {
                if (pleft.data != right.data) {
                    return false;
                } else {
                    pleft = pleft.next;
                    return true;
                }
            }
        }

        //Fold a Linked List
        Node fleft;
        public void foldLinkedList(Node head){
            fleft = head;
            foldLinkedListHelper(head, 0);
        }

        public void foldLinkedListHelper(Node right, int floor) {
            if(right == null) {
                return;
            }
            foldLinkedListHelper(right.next, floor + 1);
            if ( floor > size / 2) {
                right.next = fleft.next;
                fleft.next = right;
                fleft = right.next;
            }
            else if (floor == size / 2) {
                right.next = null;
            }
        }

        public static int addListHelper(Node one, int pv1, Node two, int pv2, LL result) {
            if(one == null && two == null) {
                return 0;
            }
            int data;
            if (pv1 > pv2) {
                int oc = addListHelper(one.next, pv1 - 1, two, pv2, result);
                data = one.data + oc;
            } else if (pv1 < pv2) {
                int oc = addListHelper(one, pv1, two.next, pv2 - 1, result);
                data = two.data + oc;
            } else { //pv1 == pv2
                int oc = addListHelper(one.next, pv1 - 1, two.next, pv2 - 1, result);
                data = one.data + two.data + oc;
            }
            int nd = data % 10;
            int nc = data / 10;
            result.addFirst(nd);
            return nc;
        }

        //Add Two Linked Lists
        public static LL addTwoLists(LL one, LL two) {
            LL result = new LL();
            int oc = addListHelper(one.head, one.size, two.head, two.size, result);
            if (oc > 0) {
                result.addFirst(oc);
            }
            return result;
        }

        public Node addTwoNumbers(Node l1, Node l2) {
            Node l3 = new Node(0);
            Node head = l3;
            int carry = 0;
            while(l1 != null && l2 != null) {
                int val = l1.data + l2.data + carry;
                int digit = val % 10;
                carry = val / 10;
                l3.next = new Node(digit);
                l3 = l3.next;

                l1 = l1.next;
                l2 = l2.next;
            }
            while(l1 != null) {
                int val = l1.data + carry;
                int digit = val % 10;
                carry = val / 10;
                l3.next = new Node(digit);
                l3 = l3.next;

                l1 = l1.next;
            }
            while(l2 != null) {
                int val = l2.data + carry;
                int digit = val % 10;
                carry = val / 10;
                l3.next = new Node(digit);
                l3 = l3.next;

                l2 = l2.next;
            }
            if (carry > 0)
                l3.next = new Node(carry);

            return head.next;
        }

        //Find Intersection point of 2 Linked Lists
        public static Node intersectionPoint(LL ll1, LL ll2) {
            Node t1 = ll1.head;
            Node t2 = ll2.head;
            if (ll1.size > ll2.size){
                int gap = ll1.size - ll2.size;
                for(int i = 0; i < gap; i++) {
                    t1 = t1.next;
                }

            } else if (ll2.size > ll1.size){
                int gap = ll2.size - ll1.size;
                for(int i = 0; i < gap; i++) {
                    t2 = t2.next;
                }
            }

            while (t1 != t2 ) {
                t1 = t1.next;
                t2 = t2.next;
            }
            return t1;
        }
    }

    public static void copyLinkedList(Node head){
        Node curr = head;
        while ( curr != null ) {
            Node next = curr.next;

            Node n = new Node();
            n.data = curr.data;
            curr.next = n;
            n.next = next;

            curr = next;
        }
    }

    public static void copyRandomPointers(Node head) {
        Node curr = head;
        while ( curr != null ) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }
    }

    public static Node extractDeepCopy(Node head) {
        Node dummyHead = new Node();
        Node prev = dummyHead;
        Node curr = head;

        while(curr != null){
            prev.next = curr.next;
            curr.next = curr.next.next;

            prev = prev.next;
            curr = curr.next;
        }
        return dummyHead.next;
    }

    //Deep Copy Linked list with Random Pointers
    public static Node deepCopyLinkedListWithRandomPointers(Node head) {
        copyLinkedList(head);
        copyRandomPointers(head);
        return extractDeepCopy(head);
    }

}
