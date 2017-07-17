package eu.h2020.symbiote.core.internal;

/**
 * PIM instance validation result model.
 *
 * Created by Mael on 16/03/2017.
 *
 * @deprecated Integrated with {@link PIMMetaModelValidationResult}
 */
@Deprecated
public class PIMInstanceValidationResult extends ValidationResult<PIMInstanceDescription> {

    public PIMInstanceValidationResult() {

    }

    public PIMInstanceValidationResult(boolean success, String description, String modelToBeValidated, String modelValidatedAgainst, PIMInstanceDescription objectDescription) {
        super(success, description, modelToBeValidated, modelValidatedAgainst, objectDescription);
    }
}
