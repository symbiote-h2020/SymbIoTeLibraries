package eu.h2020.symbiote.ssp.exception;

/**
 * Created by vasgl on 8/23/2017.
 */
public class InvalidMacAddressException extends Exception{

    public InvalidMacAddressException(String macAddress) {
        super("The provided MAC address = " + macAddress + " is INVALID");
    }
}
