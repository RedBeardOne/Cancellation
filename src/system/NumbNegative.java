package system;

public class NumbNegative extends Exception{
    public NumbNegative() {
    }
    public NumbNegative(String reason) {
        super(reason);
    }
    public NumbNegative(String reason, Throwable except) {
        super(reason, except);
    }

    public NumbNegative(Throwable except) {
        super(except);
    }
}
