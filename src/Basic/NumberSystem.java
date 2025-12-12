package Basic;

public class NumberSystem {

    // Change from any base to any base for a given number
    public long baseChange(long number, int sourceBase, int destinationBase) {
        double resultantNumber = 0;
        int powerCounter = 0;
        while(number != 0) {
            long lastDigit = number % destinationBase;
            resultantNumber += lastDigit * Math.pow(sourceBase, powerCounter);
            number = number / destinationBase;
            powerCounter ++;
        }
        return (long)resultantNumber;
    }

    // Find frequency of a digit in a number
    public int getFrequency(long number, int digit) {
        int freqCounter = 0;
        while(number > 0) {
            long lastDigit = number % 10;
            if (lastDigit == digit) {
                freqCounter += 1;
            }
            number = number / 10;
        }
        return freqCounter;
    }

    //Reverse all digits of a number
    public long reverseNumber(long number) {
        long reversedNumber = 0;
        while(number > 0) {
            long lastDigit = number % 10;
            reversedNumber = ( reversedNumber * 10 ) + lastDigit;
            number = number / 10;
        }
        return reversedNumber;
    }

    //Armstrong Number
    public void armstrongNumber(long number, int power) {
        int sum = 0;
        long origNumber = number;
        while(number > 0) {
            long lastDigit = number % 10;
            sum += (int) Math.pow(lastDigit, power);
            number = number / 10;
        }
        if (sum == origNumber) {
            System.out.println("Armstrong Number");
        }
        else {
            System.out.println("Non Armstrong Number");
        }
    }

    //Swap 2 numbers without help of 3rd variable
    public void swap2Numbers() {
        int a = 10;
        int b = 20;

        System.out.print("Before swapping : " + a + " and : " + b);

        a = a + b;
        b = a - b;
        a = a - b;

        System.out.print("Swapped to : " + a + " and : " + b);

    }

//    Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
//
//    The algorithm for myAtoi(string s) is as follows:
//
//    Whitespace: Ignore any leading whitespace (" ").
//    Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity if neither present.
//    Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered or the end of the
//    string is reached. If no digits were read, then the result is 0.
//    Rounding: If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then round the integer to remain
//    in the range. Specifically, integers less than -231 should be rounded to -231, and integers greater than 231 - 1 should
//    be rounded to 231 - 1.

    public int convertStringToInt(String s) {
        s = s.trim();
        if (s.isEmpty())
            return 0;

        boolean neg = false;
        long ans = 0;

        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if (i == 0){
                if (ch == '-'){
                    neg = true;
                    continue;
                }
                if (ch == '+'){
                    continue;
                }
            }
            if (ch >= '0' && ch <= '9') {
                int digit = ch - '0'; //remember
                ans = ans * 10 + digit;
                if (neg) {
                    long check = -ans;
                    if(check < Integer.MIN_VALUE)
                        return Integer.MIN_VALUE;
                }
                else {
                    if(ans > Integer.MAX_VALUE)
                        return Integer.MAX_VALUE;
                }
            }
            else {
                break;
            }
        }

        if(neg)
            ans = -ans;

        return (int)ans;
    }

    //Integer to Roman conversion
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        process(sb, ' ', ' ', 'M', num/1000);
        num = num % 1000;
        process(sb, 'M', 'D', 'C', num/100);
        num = num % 100;
        process(sb, 'C', 'L', 'X', num/10);
        num = num % 10;
        process(sb, 'X', 'V', 'I', num);

        return sb.toString();

    }

    public void process(StringBuilder sb, char major, char middle, char minor, int val) {
        if(val <= 3){
            for (int i = 1 ; i <= val; i++) {
                sb.append(minor);
            }
        }
        else if(val == 4) {
            sb.append(minor);
            sb.append(middle);
        }
        else if(val == 5) {
            sb.append(middle);
        }
        else if(val <=8) {
            sb.append(middle);
            for (int i = 6 ; i <= val; i++) {
                sb.append(minor);
            }
        }
        else if(val == 9) {
            sb.append(minor);
            sb.append(major);
        }
    }

    //Roman to Integer conversion
    public int romanToInt(String s) {
        int num = 0;
        int i = 0;

        while(i < s.length()){
            char ch = s.charAt(i);
            if(ch == 'M'){
                num += 1000;
            }
            else if(ch == 'D'){
                num += 500;
            }
            else if(ch == 'C' && i+1 < s.length() && s.charAt(i+1) == 'D') {
                num -= 100;
            }
            else if(ch == 'C' && i+1 < s.length() && s.charAt(i+1) == 'M') {
                num -= 100;
            }
            else if(ch == 'C'){
                num += 100;
            }
            else if(ch == 'L'){
                num += 50;
            }
            else if(ch == 'X' && i+1 < s.length() && s.charAt(i+1) == 'C') {
                num -= 10;
            }
            else if(ch == 'X' && i+1 < s.length() && s.charAt(i+1) == 'L') {
                num -= 10;
            }
            else if(ch == 'X'){
                num += 10;
            }
            else if(ch == 'V'){
                num += 5;
            }
            else if(ch == 'I' && i+1 < s.length() && s.charAt(i+1) == 'X'){
                num -= 1;
            }
            else if(ch == 'I' && i+1 < s.length() && s.charAt(i+1) == 'V'){
                num -= 1;
            }
            else if(ch == 'I'){
                num += 1;
            }
            i++;
        }
        return num;
    }

}
