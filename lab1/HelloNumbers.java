public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0;
        int y = 0;
        while (x < 46) {
            System.out.print(x + " ");
            y = y + 1;
            x = x + y;
        }
    }
}
