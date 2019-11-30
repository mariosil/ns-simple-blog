package ns.demos.blog.exception;

public class BlogException extends Exception {

    public BlogException(String message) {
        super(message);
    }

    public BlogException(String message, Throwable cause) {
        super(message, cause);
    }
}
