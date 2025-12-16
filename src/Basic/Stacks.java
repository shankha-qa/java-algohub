package Basic;

import java.util.Arrays;
import java.util.Stack;

public class Stacks {

    //Check if Brackets are duplicate. for duplicate return true else return false;
    public static boolean checkBracketDuplicacy(String str) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i <= str.length() - 1; i++) {
            char focus = str.charAt(i);
            if (focus == ')') {
                if (st.peek() == '(') {
                    return true;
                } else {
                    while (st.peek() != '(') {
                        st.pop();
                    }
                    st.pop();
                }
            } else {
                st.push(focus);
            }
        }
        return false;
    }

    //Check if all the Brackets are balanced
    public static boolean checkBracketBalances(String str) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i <= str.length() - 1; i++) {
            char focus = str.charAt(i);
            if (focus == '(' || focus == '{' || focus == '[') {
                st.push(focus);
            } else if (focus == ')') {
                if (st.isEmpty()) {
                    return false;
                } else if (st.peek() != '(') {
                    return false;
                } else {
                    st.pop();
                }
            } else if (focus == '}') {
                if (st.isEmpty()) {
                    return false;
                } else if (st.peek() != '{') {
                    return false;
                } else {
                    st.pop();
                }
            } else if (focus == ']') {
                if (st.isEmpty()) {
                    return false;
                } else if (st.peek() != '[') {
                    return false;
                } else {
                    st.pop();
                }
            }
        }
        return (st.size() == 0);

    }

    //Infix Evaluation
    public int infixEvaluation(String expression) {
        Stack<Integer> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();
        for (int i = 0; i <= expression.length() - 1; i++) {
            char ch = expression.charAt(i);
            if (ch == '(') {
                operators.push(ch);
            } else if (Character.isDigit(ch)) {
                operands.push(ch - '0');
            } else if (ch == ')') {
                while (operators.peek() != '(') {
                    char operator = operators.pop();
                    int val2 = operands.pop();
                    int val1 = operands.pop();
                    operands.push(operation(val1, val2, operator));
                }
                operators.pop();
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!operators.isEmpty() && operators.peek() != '(' && precedence(ch) <= precedence(operators.peek())) {
                    char operator = operators.pop();
                    int val2 = operands.pop();
                    int val1 = operands.pop();
                    operands.push(operation(val1, val2, operator));
                }
                operators.push(ch);
            } else {
            }
        }
        while (!operators.isEmpty()) {
            char operator = operators.pop();
            int val2 = operands.pop();
            int val1 = operands.pop();
            operands.push(operation(val1, val2, operator));
        }
        return operands.peek();
    }

    public int precedence(char operator) {
        if (operator == '+' || operator == '-')
            return 1;
        else if (operator == '*' || operator == '/')
            return 2;
        else
            return 0;
    }

    public int operation(int val1, int val2, char operator) {
        if (operator == '+')
            return (val1 + val2);
        else if (operator == '-')
            return (val1 - val2);
        else if (operator == '*')
            return (val1 * val2);
        else
            return (val1 / val2);
    }

    //Infix Conversion
    public void infixConversion(String expression) {
        Stack<String> prefix = new Stack<>();
        Stack<String> postfix = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i <= expression.length() - 1; i++) {
            char ch = expression.charAt(i);
            if (ch == '(') {
                operators.push(ch);
            } else if ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
                prefix.push(ch + "");
                postfix.push(ch + "");
            } else if (ch == ')') {
                while (operators.peek() != '(') {
                    char operator = operators.pop();

                    String val2 = prefix.pop();
                    String val1 = prefix.pop();
                    String prefixString = operator + val1 + val2;
                    prefix.push(prefixString);

                    val2 = postfix.pop();
                    val1 = postfix.pop();
                    String postfixString = val1 + val2 + operator;
                    postfix.push(postfixString);
                }
                operators.pop();
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!operators.isEmpty() && operators.peek() != '(' && precedence(ch) <= precedence(operators.peek())) {
                    char operator = operators.pop();

                    String val2 = prefix.pop();
                    String val1 = prefix.pop();
                    String prefixString = operator + val1 + val2;
                    prefix.push(prefixString);

                    val2 = postfix.pop();
                    val1 = postfix.pop();
                    String postfixString = val1 + val2 + operator;
                    postfix.push(postfixString);
                }
                operators.push(ch);
            } else {
            }
        }
        while (!operators.isEmpty()) {
            char operator = operators.pop();

            String val2 = prefix.pop();
            String val1 = prefix.pop();
            String prefixString = operator + val1 + val2;
            prefix.push(prefixString);

            val2 = postfix.pop();
            val1 = postfix.pop();
            String postfixString = val1 + val2 + operator;
            postfix.push(postfixString);
        }

        System.out.println("Prefix Conversion " + prefix.peek());
        System.out.println("Postfix Conversion " + postfix.peek());
    }

    //Postfix Evaluation
    public int postfixEvaluation(String expression) {
        Stack<Integer> valueStack = new Stack<>();
        for (int i = 0; i <= expression.length() - 1; i++) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                valueStack.push(ch - '0');
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                char operator = ch;
                int val2 = valueStack.pop();
                int val1 = valueStack.pop();
                valueStack.push(operation(val1, val2, operator));
            }
        }
        return valueStack.peek();
    }

    //Postfix Conversion
    public void postfixConversion(String expression) {
        Stack<String> infix = new Stack<>();
        Stack<String> prefix = new Stack<>();
        for (int i = 0; i <= expression.length() - 1; i++) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                infix.push(ch + "");
                prefix.push(ch + "");
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                char operator = ch;

                String val2 = infix.pop();
                String val1 = infix.pop();
                infix.push("(" + val1 + operator + val2 + ")");

                val2 = prefix.pop();
                val1 = prefix.pop();
                prefix.push(operator + val1 + val2);
            }
        }
        System.out.println("Infix Conversion " + infix.peek());
        System.out.println("Prefix Conversion " + prefix.peek());
    }

    //Prefix Evaluation
    public int prefixEvaluation(String expression) {
        Stack<Integer> valueStack = new Stack<>();
        for (int i = expression.length() - 1; i >= 0; i--) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                valueStack.push(ch - '0');
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                char operator = ch;
                int val1 = valueStack.pop();
                int val2 = valueStack.pop();
                valueStack.push(operation(val1, val2, operator));
            }
        }
        return valueStack.peek();
    }

    //Postfix Conversion
    public void prefixConversion(String expression) {
        Stack<String> infix = new Stack<>();
        Stack<String> postfix = new Stack<>();
        for (int i = expression.length() - 1; i >= 0; i--) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                infix.push(ch + "");
                postfix.push(ch + "");
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                char operator = ch;

                String val1 = infix.pop();
                String val2 = infix.pop();
                infix.push("(" + val1 + operator + val2 + ")");

                val1 = postfix.pop();
                val2 = postfix.pop();
                postfix.push(val1 + val2 + operator);

            }
        }
        System.out.println("Infix Conversion " + infix.peek());
        System.out.println("Postfix Conversion " + postfix.peek());
    }

    // Create an Array of Next greater element on the right side. if no greater is there, add -1
    public static int[] nextGreaterElement(int[] arr) {
        int[] result = new int[arr.length];
        Stack<Integer> st = new Stack<>();

        result[result.length - 1] = -1;
        st.push(arr[arr.length - 1]);
        for (int i = arr.length - 2; i >= 0; i--) {
            // from stack all smaller (than arr[i]) will be popped first and then it will save it in result array
            // and then will push itself
            //Remove
            while (!st.isEmpty() && arr[i] >= st.peek()) {
                st.pop();
            }
            //Work
            if (st.isEmpty())
                result[i] = -1;
            else
                result[i] = st.peek();
            //Add
            st.push(arr[i]);
        }
        return result;
    }

    //Create an array of stock span
    public static int[] createStockSpan(int[] arr) {
        int[] span = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        //Pushing index in the stack
        span[0] = 1;
        st.push(0);
        for (int i = 1; i >= arr.length - 1; i++) {
            while (!st.isEmpty() && arr[i] >= arr[st.peek()]) {
                st.pop();
            }

            if (st.isEmpty())
                span[i] = i + 1;
            else
                span[i] = i - st.peek();

            st.push(i);
        }
        return span;
    }

    //Return Largest Area Histogram
    public static int largestAreaHistogram(int[] arr) {

        //Next smaller element index on right
        int[] rb = new int[arr.length];
        Stack<Integer> st = new Stack<>();

        rb[rb.length - 1] = arr.length;
        st.push(arr.length - 1);

        for (int i = arr.length - 2; i >= 0; i--) {
            if (!st.isEmpty() && arr[i] < arr[st.peek()]) {
                st.pop();
            }
            if (st.isEmpty())
                rb[i] = arr.length;
            else
                rb[i] = st.peek();

            st.push(i);
        }

        //Previous smaller element index on Left
        int[] lb = new int[arr.length];
        st.empty();
        st = new Stack<>();

        lb[0] = -1;
        st.push(0);

        for (int j = 1; j <= arr.length - 1; j++) {
            if (!st.isEmpty() && arr[j] < arr[st.peek()]) {
                st.pop();
            }
            if (st.isEmpty())
                lb[j] = -1;
            else
                lb[j] = st.peek();

            st.push(j);
        }

        int maxArea = 0;
        for (int i = 0; i < arr.length; i++) {
            int width = rb[i] - lb[i] - 1;
            int area = width * arr[i];
            if (area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }

    //Sliding Window : Check the biggest value of a K Sized window.
    public static void slidingWindowProblem(int[] arr, int k) {
        int[] nge = new int[arr.length];
        Stack<Integer> st = new Stack<>();

        nge[nge.length - 1] = arr.length;
        st.push(arr.length - 1);

        for (int i = arr.length - 2; i >= 0; i--) {
            while (!st.isEmpty() && arr[i] > arr[st.peek()]) {
                st.pop();
            }
            if (st.isEmpty())
                nge[i] = arr.length;
            else
                nge[i] = st.peek();

            st.push(i);
        }

        int j = 0;
        for (int i = 0; i <= arr.length - k - 1; i++) {
            if (i > j) {
                j = i;
            }
            while (nge[j] < i + k) {
                j = nge[j];
            }
            System.out.println(arr[j]);
        }
    }

    //Find Celebrity - You have been given a matrix of n*n. There is one member, who knows nobody [0] and everyone
    // knows him [1]. Find out that person
    public void findCelebrity(int[][] matrix) {
        Stack<Integer> st = new Stack<>();
        // push all people
        for (int i = 0; i <= matrix.length - 1; i++) {
            st.push(i);
        }

        // eliminate non-celebrities
        while (st.size() > 1) {
            int people1 = st.pop();
            int people2 = st.pop();
            if (matrix[people1][people2] == 1) {
                //people1 knows people2
                st.push(people2);
            } else {
                //people2 does not know people1
                st.push(people1);
            }
        }

        // possible celebrity
        int pot = st.pop();

        //Verification
        for (int i = 0; i < matrix.length; i++) {
            if (i != pot) {
                if ((matrix[i][pot] == 0 || matrix[pot][i] == 1)) {
                    System.out.println("No celebrity");
                    return;
                }
            }
        }
        System.out.println(pot + " is celebrity");
    }
}

