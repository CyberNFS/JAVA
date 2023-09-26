import java.util.Random;

/*  References:
    Koblitz, Neal. A Course in Number Theory and Cryptography (2nd Edition). Springer, 1994
    Menezes, Alfred J., Paul C. van Oorschot, and Scott A. Vanstone. Handbook of Applied Cryptography (5th Edition). CRC Press, 2001
    Miller, Gary L. "Riemann's Hypothesis and Tests for Primality." Journal of Computer and System Sciences 13 (1976): 300-322*/
    
public class MillerRabinTest {
    // Check if a number is prime with high probability using the Miller-Rabin test
    public static boolean isPrime(int n, int k) {
        // Convert 'n' to a long to avoid integer overflow
        long longN = (long) n;

        // Handle special cases: n <= 1 is not prime, n <= 3 is prime
        return (longN <= 1) ? false :
               (longN <= 3) ? true :
               millerRabinTest(longN, k);
    }

    // Perform the Miller-Rabin primality test with 'k' iterations
    private static boolean millerRabinTest(long n, int k) {
        long r = 0;
        long d = n - 1;
        // Calculate 'r' and 'd' such that n-1 = 2^r * d where 'd' is odd
        while (d % 2 == 0) {
            r++;
            // Divide 'd' by 2 until it is odd
            d /= 2;
        }

        // Perform the Miller-Rabin test 'k' times
        for (int i = 0; i < k; i++) {
            // Generate a random number 'a' such that 2 <= a <= n-2, which is the range of possible witnesses
            long a = 2 + new Random().nextInt((int) (n - 3));
            long x = modPow(a, d, n);

            // Check if 'x' is congruent to 1 or n-1 modulo 'n'
            if (x == 1 || x == n - 1) continue;

            // Calculate x = x^2 % n repeatedly until x is congruent to n-1 or 'r' iterations have been completed
            for (int j = 0; j < r - 1; j++) {
                x = modPow(x, 2, n);
                // If 'x' becomes congruent to n-1, exit the loop
                if (x == n - 1) break;
            }
            // If 'x' is not congruent to n-1, 'n' is composite
            if (x != n - 1) return false;
        }

        return true;
    }

    // Efficiently computes (a^b) % c
    private static long modPow(long a, long b, long c) {
        long result = 1;
        a = a % c;
        while (b > 0) {
            // Use ternary operator to calculate (result * a) % c or just result
            result = (b % 2 == 1) ? (result * a) % c : result;
            a = (a * a) % c;
            b = b >> 1; // Right shift 'b' to divide it by 2
        }
        return result;
    }

    // Prints all prime numbers less than or equal to 'n'
    public static void printPrimes(int n) {
        // Loop through all numbers from 2 to 'n'
        for (int i = 2; i <= n; i++) {
            if (isPrime(i, 5)) {
                System.out.println(i);
            }
        }
    }
}