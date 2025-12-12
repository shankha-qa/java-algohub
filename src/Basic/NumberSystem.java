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


}
