public class TestBody {
    public static void main(string[] args) {
        Body Me = new Body(0, 0, 0, 0, 1000, 'Kevin.gif');
        Body Money = new Body(100, 100, -10, -5, 400, 'asteroid.gif');
        System.out.print(Money.calcForceExertedBy(Me));
    }
}
