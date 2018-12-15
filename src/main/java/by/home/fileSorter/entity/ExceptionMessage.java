package by.home.fileSorter.entity;

/**
 * Entity of Error message, in whom we parse TXT files
 */
public class ExceptionMessage {

    private Long id;
    private String message;
    private String typeOfException;
    private String thrownClass;
    private String thrownMethod;
    private Integer thrownLine;

    public ExceptionMessage() {
    }

    public ExceptionMessage(String message, String typeOfException, String thrownClass, String thrownMethod, Integer thrownLine, Long id) {
        this.message = message;
        this.typeOfException = typeOfException;
        this.thrownClass = thrownClass;
        this.thrownMethod = thrownMethod;
        this.thrownLine = thrownLine;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTypeOfException() {
        return typeOfException;
    }

    public void setTypeOfException(String typeOfException) {
        this.typeOfException = typeOfException;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
