package robertofoglia.pulsar.samples.controllers.exceptions.handlers;

public class ExceptionResponseEntity {
    String clazz;
    String message;

    public ExceptionResponseEntity(String clazz, String message) {
        this.clazz = clazz;
        this.message = message;
    }
}
