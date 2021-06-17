public enum Status {

    RECEIVED("R", "Received"), WASHING("W", "Washing"), READY("RPD" , "Ready for Pickup/Delivery"), BLANK("", "");

    private String code;
    private String text;

    private Status(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public static Status getByCode(String genderCode) {
        for (Status g : Status.values()) {
            if (g.code.equals(genderCode)) {
                return g;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.text;
    }

 }