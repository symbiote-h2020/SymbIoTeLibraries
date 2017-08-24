package eu.h2020.symbiote.core.internal;


/**
 * PIM Meta Model validation result.
 *
 * Created by Mael on 16/03/2017.
 *
 * @deprecated changed for {@link InformationModelValidationResult}
 */
@Deprecated
public class PIMMetaModelValidationResult extends ValidationResult<PIMMetaModelDescription> {

    public PIMMetaModelValidationResult() {

    }

    public PIMMetaModelValidationResult(boolean success, String description, String modelToBeValidated, String modelValidatedAgainst, PIMMetaModelDescription objectDescription) {
        super(success, description, modelToBeValidated, modelValidatedAgainst, objectDescription);
    }

}
