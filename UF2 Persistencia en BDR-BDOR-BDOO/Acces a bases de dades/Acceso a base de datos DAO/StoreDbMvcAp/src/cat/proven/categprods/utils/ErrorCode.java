package cat.proven.categprods.utils;

/**
 * Enum to code errors.
 *
 * @author ProvenSoft
 */
public enum ErrorCode {
    DB_NO_CONNECTION(-10),
    DB_SQL_SYNTAX(-11);
    //TODO add more codes

    private final int code;

    private ErrorCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
