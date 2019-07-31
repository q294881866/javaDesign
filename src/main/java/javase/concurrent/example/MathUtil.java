package javase.concurrent.example;

import java.util.Collection;

public final class MathUtil {
    public static boolean isPrime(int c, final Collection<Integer> primeNumbers) {
        if (0 == c) return false;
        if (1 == c) return true;

        // 后面不需要比了
        int limit = (int) Math.sqrt(c) + 1;

        for (Integer prime : primeNumbers) {
            if (prime > limit) break;
            if (c % prime == 0) {
                return false;
            }
        }
        primeNumbers.add(c);
        return true;
    }

}
