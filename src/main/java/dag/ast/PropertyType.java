package dag.ast;

public enum  PropertyType {
    UNKNOWN,
    INT,
    BOOL,
    CHAR;

    public static PropertyType typeOf(String input) {
        switch (input.toLowerCase()) {
            case "int":
                return INT;
            case "bool":
                return BOOL;
            case "char":
                return CHAR;

            default:
                return UNKNOWN;
        }
    }
}
