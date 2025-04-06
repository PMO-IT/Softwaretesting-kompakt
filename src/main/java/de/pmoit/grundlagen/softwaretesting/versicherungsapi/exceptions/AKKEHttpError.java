package de.pmoit.grundlagen.softwaretesting.versicherungsapi.exceptions;

public class AKKEHttpError extends Exception {
    private static final long serialVersionUID = 7247551690405306327L;

    public AKKEHttpError(String errorMsg) {
        super(errorMsg);
    }
}
