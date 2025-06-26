package enums;

public enum Error {
    NOT_MATH("Epic sadface: Username and password do not match any user in this service"),
    USER_LOCKED("Epic sadface: Sorry, this user has been locked out."),
    USERNAME_REQUIRED("Epic sadface: Username is required"),
    PASSWORD_REQUIRED("Epic sadface: Password is required"),
    FIRST_NAME_REQUIRED("Error: First Name is required"),
    LAST_NAME_REQUIRED("Error: Last Name is required"),
    POSTAL_CODE_REQUIRED("Error: Postal Code is required");

    private final String errormessage;

    Error(String errormessage) {
        this.errormessage = errormessage;
    }

    public String getErrorMessage() {
        return errormessage;
    }
}
