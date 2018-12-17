  
//DAMONSPARTDAMONSPART DAMONSPART DAMONSPART DAMONSPART DAMONSPART DAMONSPART DAMONSPART DAMONSPART DAMONSPART DAMONSPART DAMONSPART DAMONSPART DAMONSPART DAMONSPART 
// Program to print all prime factors 
import java.math.BigInteger;
import java.math.*;
import java.util.*;
import java.io.*;

public class bruteForce {
    public static void main (String[] args) { 
        long startTime = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        System.out.println("n?");
        BigInteger n = sc.nextBigInteger();
        System.out.println("a?");
        BigInteger a = sc.nextBigInteger();
        primeFactors(n,a);
        long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - startTime) + " milliseconds to break the encryption");
 
    } 
    // A function to print all prime factors 
    // of a given number n 
    public static void primeFactors(BigInteger n, BigInteger a) {
        BigInteger givena = a; 
        BigInteger t1 = BigInteger.ZERO; //used to run Euclidean
        BigInteger t2 = BigInteger.ZERO; //used to run Euclidean

        BigInteger ZERO = BigInteger.ZERO;
        BigInteger ONE = BigInteger.ONE;
        BigInteger TWO = new BigInteger("2");
        BigInteger THREE = new BigInteger("3");
        // Print the number of 2s that divide n 
        while (n.mod(TWO) == ZERO) 
        { 
            System.out.print(TWO + " "); 
            n = n.divide(TWO); 
        } 
  
        // n must be odd at this point.  So we can 
        // skip one element (Note i = i +2) 
        for (BigInteger i = THREE; i.compareTo(sqrt(n)) < 1; i = i.add(TWO)) 
        { 
            // While i divides n, print i and divide n 
            while (n.mod(i) == ZERO) 
            { 
                System.out.print(i + " ");
                t1 = i; 
                n = n.divide(i); 
            } 
        } 
  
        // This condition is to handle the case whien 
        // n is a prime number greater than 2 
        if (n.compareTo(TWO) > 0) {
            t2 = n;
            System.out.println(n);
        }
        System.out.println("Your B is: " + reverseE(givena,t1.subtract(ONE).multiply(t2.subtract(ONE))));
    }
    public static BigInteger sqrt(BigInteger x) {
        BigInteger div = BigInteger.ZERO.setBit(x.bitLength()/2);
        BigInteger div2 = div;
        // Loop until we hit the same value twice in a row, or wind
        // up alternating.
        for(;;) {
            BigInteger y = div.add(x.divide(div)).shiftRight(1);
            if (y.equals(div) || y.equals(div2))
            return y;
            div2 = div;
            div = y;
        }
    }
    public static BigInteger reverseE(BigInteger num1, BigInteger num2) {
    /** Solves the reverse Euclidean Equation (1 = a*b + c*phi(n)
    * @param num1 the first number
    * @param num2 the second number
    */
        BigInteger p1 = num1;
        BigInteger p2 = num2;
        BigInteger a = num1;
        BigInteger b = num2;
        if(p1.compareTo(p2) < 0) {
            BigInteger p3 = p1;
            p1 = p2;
            p2 = p3;
            BigInteger c = a;
            a = b;
            b = c;
        }
        BigInteger x =  BigInteger.ZERO;
        BigInteger y = BigInteger.ONE;
        BigInteger lastx = BigInteger.ONE;
        BigInteger lasty = BigInteger.ZERO;
        BigInteger temp;
        while(b.compareTo(BigInteger.ZERO) != 0) {
            BigInteger q = a.divide(b);
            BigInteger r = a.mod(b);
            a = b;
            b = r;

            temp = x;
            x = lastx.subtract(q.multiply(x));
            lastx = temp;

            temp = y;
            y = lasty.subtract(q.multiply(y));
            lasty = temp;
        }
        return lasty; //this is B
    }
}

  //credit to wikibooks for algorithm - small tweaks have been made
import java.math.*;
import java.util.*;
import java.io.*; 
public class SimplePrime {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Bits?");
		int bitLength = sc.nextInt();
		getPrimes(bitLength);
	}
	public static BigInteger[] getPrimes(int bits) {
		BigInteger[] primes = new BigInteger[4];
		int bitLength = bits;
		Random rnd = new Random();
		BigInteger p = BigInteger.probablePrime(bitLength, rnd);
		BigInteger q = BigInteger.probablePrime(bitLength, rnd);
		BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		do { BigInteger a = BigInteger.probablePrime(phi.bitLength(), rnd);
		} while (a.compareTo(phi) < 0);
		primes[0] = p;
		primes[1] = q;
		primes[2] = phi;
		primes[3] = a;
	}
}