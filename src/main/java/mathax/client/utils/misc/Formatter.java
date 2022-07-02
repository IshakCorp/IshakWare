package mathax.client.utils.misc;

public class Formatter {
    public static int random(int min, int max) { return min + (int) (Math.random() * ((max - min) + 1)); }
    public static double random(double min, double max) {
        return Math.random() * (max - min) + min;
    }
}
