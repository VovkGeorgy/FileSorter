package by.home.fileSorter.entity;

/**
 * Entity of Error message, in whom we parse JSON files
 */
public class ErrorMessage {

    private Long errorid;
    private String message;
    private String typeOfError;
    private String thrownClass;
    private String thrownMethod;
    private Integer thrownLine;

    public ErrorMessage() {
    }

    public ErrorMessage(String message, String typeOfError, String thrownClass, String thrownMethod, Integer thrownLine, Long errorid) {
        this.errorid = errorid;
        this.message = message;
        this.typeOfError = typeOfError;
        this.thrownClass = thrownClass;
        this.thrownMethod = thrownMethod;
        this.thrownLine = thrownLine;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTypeOfError() {
        return typeOfError;
    }

    public void setTypeOfError(String typeOfError) {
        this.typeOfError = typeOfError;
    }

    public String getThrownClass() {
        return thrownClass;
    }

    public void setThrownClass(String thrownClass) {
        this.thrownClass = thrownClass;
    }

    public String getThrownMethod() {
        return thrownMethod;
    }

    public void setThrownMethod(String thrownMethod) {
        this.thrownMethod = thrownMethod;
    }

    public Integer getThrownLine() {
        return thrownLine;
    }

    public void setThrownLine(Integer thrownLine) {
        this.thrownLine = thrownLine;
    }

    public Long getErrorid() {
        return errorid;
    }

    public void setErrorid(Long errorid) {
        this.errorid = errorid;
    }
}
