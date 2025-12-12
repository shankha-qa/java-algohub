package Basic;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class CustomDS {

    public static class CustomStack{
        int[] data;
        int tos;

        CustomStack(int cap) {
            data = new int[cap];
            tos = -1;
        }

        int size(){
            return tos + 1;
        }

        void display(){
            for(int i = tos; i >= 0; i--) {
                System.out.print(data[i] + " ");
            }
            System.out.println();
        }

        void push(int item){
            if (tos == data.length - 1) {
                System.out.println("Stack Overflow");
            }
            else {
                tos ++;
                data[tos] = item;
            }
        }

        void dynamicPush(int item){
            if (tos == data.length - 1) {
                int[] ndata = new int[data.length * 2];
                for (int i = 0; i < data.length; i++) {
                    ndata[i] = data[i];
                }
                data = ndata;
            }
            tos ++;
            data[tos] = item;
        }

        int pop(){
            if (tos == data.length - 1) {
                System.out.println("Stack Underflow");
                return -1;
            }
            else {
                int item = data[tos];
                tos --;
                return item;
            }
        }

        int peek(){
            if(tos == -1){
                return -1;
            }
            else {
                return data[tos];
            }
        }
    }

    public static class CustomQueue{
        int[] data;
        int front;
        int rear;

        CustomQueue(int cap){
            data = new int[cap];
            front = 0;
            rear = 0;
        }

        int size(){
            return rear + 1;
        }

        void display(){
            for(int i = front; i <= rear; i++) {
                System.out.print(data[i] + " ");
            }
            System.out.println();
        }

        void add(int item){
            if (rear == data.length - 1) {
                System.out.println("Queue Overflow");
            }
            else {
                rear ++;
                data[rear] = item;
            }
        }

        void dynamicAdd(int item){
            if (rear == data.length - 1) {
                int[] ndata = new int[data.length * 2];
                for (int i = 0; i < data.length; i++) {
                    ndata[i] = data[i];
                }
                data = ndata;
            }
            else {
                rear ++;
                data[rear] = item;
            }
        }

        int delete(){
            if (front == -1) {
                System.out.println("Queue Underflow");
                return -1;
            }
            else {
                int item = data[front];
                front --;
                return item;
            }
        }

        int peek(){
            if (front == -1) {
                return -1;
            }
            else {
                return data[front];
            }
        }

    }

    public static class QueueToStackAdapterPopEfficient {
        Queue<Integer> mainQ;
        Queue<Integer> helperQ;

        public QueueToStackAdapterPopEfficient() {
            mainQ = new ArrayDeque<>();
            helperQ = new ArrayDeque<>();
        }

        int size() {
            return mainQ.size();
        }

        void push(int value){
            while(!mainQ.isEmpty()) {
                helperQ.add(mainQ.remove());
            }
            mainQ.add(value);
            while(!helperQ.isEmpty()) {
                mainQ.add(helperQ.remove());
            }
        }

        int pop() {
            if (size() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            }
            else {
                return mainQ.remove();
            }
        }

        int top() {
            if (size() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            }
            else {
                return mainQ.peek();
            }
        }
    }

    public static class QueueToStackAdapterPushEfficient {
        Queue<Integer> mainQ;
        Queue<Integer> helperQ;

        public QueueToStackAdapterPushEfficient() {
            mainQ = new ArrayDeque<>();
            helperQ = new ArrayDeque<>();
        }

        int size() {
            return mainQ.size();
        }

        void push(int value){
            mainQ.add(value);
        }

        int pop() {
            if (size() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            }
            else {
                while((mainQ.size() > 1)) {
                    helperQ.add(mainQ.remove());
                }
                int val = mainQ.remove();
                while(!helperQ.isEmpty()) {
                    mainQ.add(helperQ.remove());
                }
                return val;
            }
        }

        int peek() {
            if (size() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            }
            else {
                while((mainQ.size() > 1)) {
                    helperQ.add(mainQ.remove());
                }
                int val = mainQ.remove();
                helperQ.add(val);
                while(!helperQ.isEmpty()) {
                    mainQ.add(helperQ.remove());
                }
                return val;
            }

        }
    }

    public static class StackToQueueAdapterRemoveEfficient {
        Stack<Integer> mainStack;
        Stack<Integer> helperStack;

        public StackToQueueAdapterRemoveEfficient() {
            mainStack = new Stack<>();
            helperStack = new Stack<>();
        }

        int size() {
            return mainStack.size();
        }

        void add(int value){
            while(!mainStack.isEmpty()) {
                helperStack.push(mainStack.pop());
            }
            mainStack.push(value);
            while(!helperStack.isEmpty()) {
                mainStack.push(helperStack.pop());
            }
        }

        int pop() {
            if (size() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            }
            else {
                return mainStack.pop();
            }
        }

        int top() {
            if (size() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            }
            else {
                return mainStack.peek();
            }
        }
    }

    public static class StackToQueueAdapterAddEfficient {
        Stack<Integer> mainStack;
        Stack<Integer> helperStack;

        public StackToQueueAdapterAddEfficient() {
            mainStack = new Stack<>();
            helperStack = new Stack<>();
        }

        int size() {
            return mainStack.size();
        }

        void add(int value){
            mainStack.push(value);
        }

        int remove() {
            if (size() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            }
            else {
                while(mainStack.size() > 1) {
                    helperStack.push(mainStack.pop());
                }
                int val = mainStack.pop();
                while(helperStack.size() > 0) {
                    mainStack.push(helperStack.pop());
                }
                return val;
            }
        }

        int peek() {
            if (size() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            }
            else {
                while(mainStack.size() > 1) {
                    helperStack.push(mainStack.pop());
                }
                int val = mainStack.peek();
                helperStack.push(val);
                while(!helperStack.isEmpty()) {
                    mainStack.push(helperStack.pop());
                }
                return val;
            }
        }
    }

    public static class TwoStack {
        int[] data;
        int tos1;
        int tos2;

        public TwoStack(int cap){
            data = new int[cap];
            tos1 = -1;
            tos2 = data.length;
        }

        void push1(int value) {
            if(tos1 + 1 == tos2) {
                System.out.println("Stack overflow");
            }
            else {
                tos1++;
                data[tos1] = value;
            }
        }

        void push2(int value) {
            if(tos1 + tos2 == data.length) {
                System.out.println("Stack overflow");
            }
            else {
                tos2--;
                data[tos2] = value;
            }
        }

        int size1() {
            return tos1 + 1;
        }

        int size2() {
            return data.length - tos2;
        }

        int pop1() {
            if (size1() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            }
            else {
                int val = data[tos1];
                tos1 --;
                return val;
            }
        }

        int pop2() {
            if (size2() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            }
            else {
                int val = data[tos1];
                tos2 ++;
                return val;
            }
        }

        int top1() {
            if (size1() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            }
            else {
                return data[tos1];
            }
        }

        int top2() {
            if (size2() == 0) {
                System.out.println("Stack Underflow");
                return -1;
            }
            else {
                return data[tos2];
            }
        }

    }


}
