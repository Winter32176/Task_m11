public class MyExceptions1 extends Exception {
    public String getMyCause() {
        return cause;
    }

    public static long getUID() {
        return serialVersionUID;
    }

    private static long serialVersionUID = 1L;
    private String cause;

    public MyExceptions1(String message, String cause) {
        super(message);
        serialVersionUID++;
        this.cause = cause;
    }
}
