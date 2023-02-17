package ba.unsa.etf.rpr.exceptions;

/**
 * Exception that occurs when there is some fault with operations with the OrderDatabase
 */
public class OrderException extends Exception{

    public OrderException(String message) {
        super(message);
    }
}
