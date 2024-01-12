package be.tftic.spring.demo.bll.exceptions;

public class EntityNotFoundException extends RuntimeException {

    private final Object searchValue;
    private final String searchBy;
    private final Class<?> entityType;

    public EntityNotFoundException(Object searchValue, Class<?> entityType) {
        super("entity {"+entityType.getSimpleName()+"} could not be found with 'id' {"+searchValue+"}");
        this.searchValue = searchValue;
        this.entityType = entityType;
        this.searchBy = "id";
    }

    public EntityNotFoundException(String message, Object searchValue, Class<?> entityType) {
        super(message);
        this.searchValue = searchValue;
        this.entityType = entityType;
        this.searchBy = "id";
    }

    public EntityNotFoundException(Object searchValue, String searchBy, Class<?> entityType) {
        super("entity {"+entityType.getSimpleName()+"} could not be found with '"+ searchBy +"' {"+searchValue+"}");
        this.searchValue = searchValue;
        this.searchBy = searchBy;
        this.entityType = entityType;
    }
}
