package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.model.internal.CoreResource;

import java.util.Map;

/**
 * Resource Instance validation result.
 *
 * Created by Mael on 16/03/2017.
 */
public class ResourceInstanceValidationResult extends ValidationResult<Map<String, CoreResource>> {

    public ResourceInstanceValidationResult() {

    }

    public ResourceInstanceValidationResult(boolean success, String description, String modelToBeValidated, String modelValidatedAgainst, Map<String, CoreResource> objectDescription) {
        super(success, description, modelToBeValidated, modelValidatedAgainst, objectDescription);
    }
}
