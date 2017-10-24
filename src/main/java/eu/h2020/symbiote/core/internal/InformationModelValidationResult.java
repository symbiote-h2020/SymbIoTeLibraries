package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.model.mim.InformationModel;

/**
 * Created by mateuszl on 14.08.2017.
 */
public class InformationModelValidationResult extends ValidationResult<InformationModel> {

    public InformationModelValidationResult() {

    }

    public InformationModelValidationResult(boolean success, String description, String modelToBeValidated, String modelValidatedAgainst, InformationModel objectDescription) {
        super(success, description, modelToBeValidated, modelValidatedAgainst, objectDescription);
    }
}
