package cat.proven.categprods.utils;

/**
 * Exception to catch data access layer errors in Store application
 * @author ProvenSoft
 */
public class StoreDalException extends Exception {

    private int errorCode;
    
    public StoreDalException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("StoreDalException{");
        sb.append("errorCode=").append(errorCode);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
