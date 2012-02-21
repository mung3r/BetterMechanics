package net.edoxile.bettermechanics.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: Edoxile
 */
public class OutOfMaterialException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 5908603512831179232L;
    private int amount;

    public OutOfMaterialException(int a) {
        amount = a;
    }

    public int getAmount() {
        return amount;
    }
}
