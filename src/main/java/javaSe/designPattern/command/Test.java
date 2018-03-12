package javaSe.designPattern.command;

public class Test {

    public static void main(String[] args) {
        Boy b = new Boy();
        MM mm = new MM();

        mm.order(b);
    }
}
