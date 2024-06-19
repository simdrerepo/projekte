import java.util.Random;

public class RandomUtils {
      public static int nextInt(Random random, int origin, int bound) {
    if (origin >= bound) {
      throw new IllegalArgumentException();
    }
    return origin + random.nextInt(bound - origin);
  }
}
