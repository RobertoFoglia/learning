package robertofoglia.pulsar.samples.controllers.exceptions.handlers;

public class ExceptionResponseEntity {
    String clazz;
    String message;

    public ExceptionResponseEntity(String clazz, String message) {
        this.clazz = clazz;
        this.message = message;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
