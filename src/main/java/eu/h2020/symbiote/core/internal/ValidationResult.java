package eu.h2020.symbiote.core.internal;

/**
 * Represents a generic validation result for ontologies.
 *
 * Created by Mael on 16/03/2017.
 */
public abstract class ValidationResult<T> {

    private boolean success;

    //TODO prepare list of enum validation messages to be reused
    private String message;

    //RDF
    private String modelValidated;

    //I don't know if we need it in the result of the validation - for now we can use it as a placeholder and put
    //model uri
    private String modelValidatedAgainst;

    private T objectDescription;

    public ValidationResult() {

    }

    public ValidationResult(boolean success, String description, String modelToBeValidated, String modelValidatedAgainst, T objectDescription) {
        this.success = success;
        this.message = description;
        this.modelValidated = modelToBeValidated;
        this.modelValidatedAgainst = modelValidatedAgainst;
        this.objectDescription = objectDescription;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getModelValidated() {
        return modelValidated;
    }

    public void setModelValidated(String modelValidated) {
        this.modelValidated = modelValidated;
    }

    public String getModelValidatedAgainst() {
        return modelValidatedAgainst;
    }

    public void setModelValidatedAgainst(String modelValidatedAgainst) {
        this.modelValidatedAgainst = modelValidatedAgainst;
    }

    public T getObjectDescription() {
        return objectDescription;
    }

    public void setObjectDescription(T objectDescription) {
        this.objectDescription = objectDescription;
    }
}
