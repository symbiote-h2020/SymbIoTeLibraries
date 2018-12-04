/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.client.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import eu.h2020.symbiote.jsonld.JsonLDObjectMapper;
import eu.h2020.symbiote.jsonld.JsonLDType;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Jacoby <michael.jacoby@iosb.fraunhofer.de>
 */
public interface SemanticRAPClient extends RAPClient {

    /**
     * Actuates a resouce by resourceId. If possible, mapping is applied to
     * input and output.
     *
     * @param resourceId the resource ID
     * @param body parameters to send to the actuator as JSON(-LD).
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     */
    public void actuateWithMapping(String resourceId, String body, boolean serverValidation, Set<String> homePlatformIds);

    /**
     * Actuates a resouce by resourceId. If possible, mapping is applied to
     * input and output.
     *
     * @param resourceId the resource ID
     * @param parameters parameters to send to the actuator. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    public default void actuateWithMapping(String resourceId, Object parameters, boolean serverValidation, Set<String> homePlatformIds) throws JsonProcessingException {
        actuateWithMapping(
                resourceId,
                parameters,
                new JsonLDObjectMapper(getIncludeTypesFromSuperclasses(), getAlwaysUseArrayForTypeProperty()),
                serverValidation,
                homePlatformIds);
    }

    /**
     * Actuates a resouce by resourceId. If possible, mapping is applied to
     * input and output.
     *
     * @param resourceId the resource ID
     * @param parameters parameters to send to the actuator. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param mapper {@link ObjectMapper} for custom de-/serialization from/to
     * JSON-LD
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    public default void
            actuateWithMapping(String resourceId, Object parameters, ObjectMapper mapper, boolean serverValidation, Set<String> homePlatformIds) throws JsonProcessingException {
        actuateWithMapping(resourceId, mapper.writeValueAsString(parameters), serverValidation, homePlatformIds);
    }

    /**
     * Actuates a resouce by resourceId. If possible, mapping is applied to
     * input and output.
     *
     * @param resourceId the resource ID
     * @param parameters parameters to send to the actuator. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * @param parameterPIMId ID of PIM used for parameters
     * @param resultPIMId ID of PIM desired for result
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    public default void actuateWithMapping(String resourceId, Object parameters, boolean serverValidation, Set<String> homePlatformIds, String parameterPIMId, String resultPIMId) throws JsonProcessingException {
        actuateWithMapping(
                resourceId,
                parameters,
                new JsonLDObjectMapper(getIncludeTypesFromSuperclasses(), getAlwaysUseArrayForTypeProperty()),
                serverValidation,
                homePlatformIds,
                parameterPIMId,
                resultPIMId);
    }

    /**
     * Actuates a resouce by resourceId. If possible, mapping is applied to
     * input and output.
     *
     * @param resourceId the resource ID
     * @param parameters parameters to send to the actuator. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param mapper {@link ObjectMapper} for custom de-/serialization from/to
     * JSON-LD
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * @param parameterPIMId ID of PIM used for parameters
     * @param resultPIMId ID of PIM desired for result
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    public default void actuateWithMapping(String resourceId, Object parameters, ObjectMapper mapper, boolean serverValidation, Set<String> homePlatformIds, String parameterPIMId, String resultPIMId) throws JsonProcessingException {
        actuateWithMapping(resourceId, mapper.writeValueAsString(parameters), serverValidation, homePlatformIds, parameterPIMId, resultPIMId);
    }

    /**
     * Actuates a resouce by resourceId. If possible, mapping is applied to
     * input and output.
     *
     * @param resourceId the resource ID
     * @param body parameters to send to the actuator as JSON(-LD)
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * @param parameterPIMId ID of PIM used for parameters
     * @param resultPIMId ID of PIM desired for result
     */
    public void actuateWithMapping(String resourceId, String body, boolean serverValidation, Set<String> homePlatformIds, String parameterPIMId, String resultPIMId);

    /**
     * Actuates a resouce by resourceId. If possible, mapping is applied to
     * input and output.
     *
     * @param resourceId the resource ID
     * @param body parameters to send to the actuator as JSON(-LD).
     * @param serverValidation if true it will validate RAP
     */
    public void actuateWithMappingAsGuest(String resourceId, String body, boolean serverValidation);

    /**
     * Actuates a resouce by resourceId. If possible, mapping is applied to
     * input and output.
     *
     * @param resourceId the resource ID
     * @param parameters parameters to send to the actuator. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param serverValidation if true it will validate RAP
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    public default void actuateWithMappingAsGuest(String resourceId, Object parameters, boolean serverValidation) throws JsonProcessingException {
        actuateWithMappingAsGuest(
                resourceId,
                parameters,
                new JsonLDObjectMapper(getIncludeTypesFromSuperclasses(), getAlwaysUseArrayForTypeProperty()),
                serverValidation);
    }

    /**
     * Actuates a resouce by resourceId. If possible, mapping is applied to
     * input and output.
     *
     * @param resourceId the resource ID
     * @param parameters parameters to send to the actuator. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param mapper {@link ObjectMapper} for custom de-/serialization from/to
     * JSON-LD
     * @param serverValidation if true it will validate RAP
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    public default void actuateWithMappingAsGuest(String resourceId, Object parameters, ObjectMapper mapper, boolean serverValidation) throws JsonProcessingException {
        actuateWithMappingAsGuest(resourceId, mapper.writeValueAsString(parameters), serverValidation);
    }

    /**
     * Actuates a resouce by resourceId. If possible, mapping is applied to
     * input and output.
     *
     * @param resourceId the resource ID
     * @param parameters parameters to send to the actuator. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param serverValidation if true it will validate RAP
     * @param parameterPIMId ID of PIM used for parameters
     * @param resultPIMId ID of PIM desired for result
     */
    public default void actuateWithMappingAsGuest(String resourceId, Object parameters, boolean serverValidation, String parameterPIMId, String resultPIMId) {
        actuateWithMappingAsGuest(
                resourceId,
                parameters,
                new JsonLDObjectMapper(getIncludeTypesFromSuperclasses(), getAlwaysUseArrayForTypeProperty()),
                serverValidation,
                parameterPIMId,
                resultPIMId);
    }

    /**
     * Actuates a resouce by resourceId. If possible, mapping is applied to
     * input and output.
     *
     * @param resourceId the resource ID
     * @param parameters parameters to send to the actuator. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param mapper {@link ObjectMapper} for custom de-/serialization from/to
     * JSON-LD
     * @param serverValidation if true it will validate RAP
     * @param parameterPIMId ID of PIM used for parameters
     * @param resultPIMId ID of PIM desired for result
     */
    public default void actuateWithMappingAsGuest(String resourceId, Object parameters, ObjectMapper mapper, boolean serverValidation, String parameterPIMId, String resultPIMId) {
        try {
            actuateWithMappingAsGuest(resourceId, mapper.writeValueAsString(parameters), serverValidation, parameterPIMId, resultPIMId);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(SemanticRAPClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Actuates a resouce by resourceId. If possible, mapping is applied to
     * input and output.
     *
     * @param resourceId the resource ID
     * @param body parameters to send to the actuator as JSON(-LD)
     * @param serverValidation if true it will validate RAP
     * @param parameterPIMId ID of PIM used for parameters
     * @param resultPIMId ID of PIM desired for result
     */
    public void actuateWithMappingAsGuest(String resourceId, String body, boolean serverValidation, String parameterPIMId, String resultPIMId);

    /**
     * Gets flag alwaysUseArrayForTypeProperty.
     *
     * @return flag alwaysUseArrayForTypeProperty
     * @see #setAlwaysUseArrayForTypeProperty(boolean value)
     */
    public boolean getAlwaysUseArrayForTypeProperty();

    /**
     * Sets flag alwaysUseArrayForTypeProperty. If set to true, the value of
     *
     * @type property when serializing to JSON-LD is always of type String[]. If
     * set to false, the type is either String (if there is only one type) or
     * String[] if there are multiple types.
     *
     * @param value flag alwaysUseArrayForTypeProperty
     */
    public void setAlwaysUseArrayForTypeProperty(boolean value);

    /**
     * Gets flag includeTypesFromSuperclasses
     *
     * @return flag includeTypesFromSuperclasses
     * @see #setIncludeTypesFromSuperclasses(boolean value)
     */
    public boolean getIncludeTypesFromSuperclasses();

    /**
     * Sets flag includeTypesFromSuperclasses. If set to true, JSON-LD
     * serialization will include {@link JsonLDType} annotations from class and
     * all super classes for @type generation. If set to false, only uses
     * JsonLDType annotation from class to be serialized itself.
     *
     * @param value flag includeTypesFromSuperclasses
     */
    public void setIncludeTypesFromSuperclasses(boolean value);

    public String getResource(String resourceId, boolean serverValidation, Set<String> homePlatformIds) throws SecurityHandlerException;

    public String getResourceAsGuest(String resourceId, boolean serverValidation) throws SecurityHandlerException;

    /**
     * Generic access to a resource based on resource ID. If returned JSON is
     * valid JSON-LD and there is a mapping, the returned result is transformed
     * using this mapping. A mapping is discovered first via resourceId and if
     * that fails or is not unique via himePlatformIds. If this is also not
     * unique, no mapping is done.
     *
     * @param resourceId the resource ID
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * to get credentials for the request
     * @return The resource information from RAP, either mapped or unmapped.
     * Returns null if something goes wrong (e.g. resource does not exist, no
     * mapping found, mapping not unique, error applying mapping, etc)
     */
    public String getResourceWithMapping(String resourceId, boolean serverValidation, Set<String> homePlatformIds);

    /**
     * Generic access to a resource based on resource ID. If returned JSON is
     * valid JSON-LD and there is a mapping, the returned result is transformed
     * using this mapping.
     *
     * @param resourceId the resource ID
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * to get credentials for the request
     * @param resultPIMId ID of PIM to use for mapping
     * @return The resource information from RAP, either mapped or unmapped.
     * Returns null if something goes wrong (e.g. resource does not exist, no
     * mapping found, mapping not unique, error applying mapping, etc)
     */
    public String getResourceWithMapping(String resourceId, boolean serverValidation, Set<String> homePlatformIds, String resultPIMId);

    /**
     * Generic access to a resource based on resource ID. If returned JSON is
     * valid JSON-LD and there is a mapping, the returned result is transformed
     * using this mapping. A mapping is discovered first via resourceId and if
     * that fails or is not unique via himePlatformIds. If this is also not
     * unique, no mapping is done.
     *
     * @param <T> tyoe of result
     * @param resourceId the resource ID
     * @param resultType java class use expected result used for deserialization
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * to get credentials for the request
     * @return The resource information from RAP, either mapped or unmapped.
     * Returns null if something goes wrong (e.g. resource does not exist, no
     * mapping found, mapping not unique, error applying mapping, etc)
     */
    public default <T> T getResourceWithMapping(String resourceId, Class<T> resultType, boolean serverValidation, Set<String> homePlatformIds) {
        return getResourceWithMapping(
                resourceId,
                resultType,
                new JsonLDObjectMapper(getIncludeTypesFromSuperclasses(), getAlwaysUseArrayForTypeProperty()),
                serverValidation,
                homePlatformIds);
    }

    /**
     * Generic access to a resource based on resource ID. If returned JSON is
     * valid JSON-LD and there is a mapping, the returned result is transformed
     * using this mapping.
     *
     * @param <T> tyoe of result
     * @param resourceId the resource ID
     * @param resultType java class use expected result used for deserialization
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * to get credentials for the request
     * @param resultPIMId ID of PIM to use for mapping
     * @return The resource information from RAP, either mapped or unmapped.
     * Returns null if something goes wrong (e.g. resource does not exist, no
     * mapping found, mapping not unique, error applying mapping, etc)
     */
    public default <T> T getResourceWithMapping(String resourceId, Class<T> resultType, boolean serverValidation, Set<String> homePlatformIds, String resultPIMId) {
        return getResourceWithMapping(
                resourceId,
                resultType,
                new JsonLDObjectMapper(getIncludeTypesFromSuperclasses(), getAlwaysUseArrayForTypeProperty()),
                serverValidation,
                homePlatformIds,
                resultPIMId);
    }

    /**
     * Generic access to a resource based on resource ID. If returned JSON is
     * valid JSON-LD and there is a mapping, the returned result is transformed
     * using this mapping. A mapping is discovered first via resourceId and if
     * that fails or is not unique via himePlatformIds. If this is also not
     * unique, no mapping is done.
     *
     * @param <T> tyoe of result
     * @param resourceId the resource ID
     * @param resultType java class use expected result used for deserialization
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * to get credentials for the request
     * @return The resource information from RAP, either mapped or unmapped.
     * Returns null if something goes wrong (e.g. resource does not exist, no
     * mapping found, mapping not unique, error applying mapping, etc)
     */
    public default <T> T getResourceWithMapping(String resourceId, JavaType resultType, boolean serverValidation, Set<String> homePlatformIds) {
        return getResourceWithMapping(
                resourceId,
                resultType,
                new JsonLDObjectMapper(getIncludeTypesFromSuperclasses(), getAlwaysUseArrayForTypeProperty()),
                serverValidation,
                homePlatformIds);
    }

    /**
     * Generic access to a resource based on resource ID. If returned JSON is
     * valid JSON-LD and there is a mapping, the returned result is transformed
     * using this mapping.
     *
     * @param <T> tyoe of result
     * @param resourceId the resource ID
     * @param resultType java class use expected result used for deserialization
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * to get credentials for the request
     * @param resultPIMId ID of PIM to use for mapping
     * @return The resource information from RAP, either mapped or unmapped.
     * Returns null if something goes wrong (e.g. resource does not exist, no
     * mapping found, mapping not unique, error applying mapping, etc)
     */
    public default <T> T getResourceWithMapping(String resourceId, JavaType resultType, boolean serverValidation, Set<String> homePlatformIds, String resultPIMId) {
        return getResourceWithMapping(
                resourceId,
                resultType,
                new JsonLDObjectMapper(getIncludeTypesFromSuperclasses(), getAlwaysUseArrayForTypeProperty()),
                serverValidation,
                homePlatformIds,
                resultPIMId);
    }

    /**
     * Generic access to a resource based on resource ID. If returned JSON is
     * valid JSON-LD and there is a mapping, the returned result is transformed
     * using this mapping. A mapping is discovered first via resourceId and if
     * that fails or is not unique via himePlatformIds. If this is also not
     * unique, no mapping is done.
     *
     * @param <T> tyoe of result
     * @param resourceId the resource ID
     * @param resultType java class use expected result used for deserialization
     * @param mapper {@link ObjectMapper} for deserializing result
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * to get credentials for the request
     * @return The resource information from RAP, either mapped or unmapped.
     * Returns null if something goes wrong (e.g. resource does not exist, no
     * mapping found, mapping not unique, error applying mapping, etc)
     */
    public default <T> T getResourceWithMapping(String resourceId, Class<T> resultType, ObjectMapper mapper, boolean serverValidation, Set<String> homePlatformIds) {
        return getResourceWithMapping(
                resourceId,
                TypeFactory.defaultInstance().constructType(resultType),
                mapper,
                serverValidation,
                homePlatformIds);
    }

    /**
     * Generic access to a resource based on resource ID. If returned JSON is
     * valid JSON-LD and there is a mapping, the returned result is transformed
     * using this mapping.
     *
     * @param <T> tyoe of result
     * @param resourceId the resource ID
     * @param resultType java class use expected result used for deserialization
     * @param mapper {@link ObjectMapper} for deserializing result
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * to get credentials for the request
     * @param resultPIMId ID of PIM to use for mapping
     * @return The resource information from RAP, either mapped or unmapped.
     * Returns null if something goes wrong (e.g. resource does not exist, no
     * mapping found, mapping not unique, error applying mapping, etc)
     */
    public default <T> T getResourceWithMapping(String resourceId, Class<T> resultType, ObjectMapper mapper, boolean serverValidation, Set<String> homePlatformIds, String resultPIMId) {
        return getResourceWithMapping(
                resourceId,
                TypeFactory.defaultInstance().constructType(resultType),
                mapper,
                serverValidation,
                homePlatformIds,
                resultPIMId
        );
    }

    /**
     * Generic access to a resource based on resource ID. If returned JSON is
     * valid JSON-LD and there is a mapping, the returned result is transformed
     * using this mapping. A mapping is discovered first via resourceId and if
     * that fails or is not unique via himePlatformIds. If this is also not
     * unique, no mapping is done.
     *
     * @param <T> tyoe of result
     * @param resourceId the resource ID
     * @param resultType java class use expected result used for deserialization
     * @param mapper {@link ObjectMapper} for deserializing result
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * to get credentials for the request
     * @return The resource information from RAP, either mapped or unmapped.
     * Returns null if something goes wrong (e.g. resource does not exist, no
     * mapping found, mapping not unique, error applying mapping, etc)
     */
    public default <T> T getResourceWithMapping(String resourceId, JavaType resultType, ObjectMapper mapper, boolean serverValidation, Set<String> homePlatformIds) {
        String result = getResourceWithMapping(
                resourceId,
                serverValidation,
                homePlatformIds);
        try {
            return mapper.readValue(result, resultType);
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * Generic access to a resource based on resource ID. If returned JSON is
     * valid JSON-LD and there is a mapping, the returned result is transformed
     * using this mapping.
     *
     * @param <T> tyoe of result
     * @param resourceId the resource ID
     * @param resultType java class use expected result used for deserialization
     * @param mapper {@link ObjectMapper} for deserializing result
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * to get credentials for the request
     * @param resultPIMId ID of PIM to use for mapping
     * @return The resource information from RAP, either mapped or unmapped.
     * Returns null if something goes wrong (e.g. resource does not exist, no
     * mapping found, mapping not unique, error applying mapping, etc)
     */
    public default <T> T getResourceWithMapping(String resourceId, JavaType resultType, ObjectMapper mapper, boolean serverValidation, Set<String> homePlatformIds, String resultPIMId) {
        String result = getResourceWithMapping(
                resourceId,
                serverValidation,
                homePlatformIds,
                resultPIMId);
        try {
            return mapper.readValue(result, resultType);
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * Generic access to a resource based on resource ID. If returned JSON is
     * valid JSON-LD and there is a mapping, the returned result is transformed
     * using this mapping.
     *
     * @param resourceId the resource ID
     * @param serverValidation if true it will validate RAP
     * @param resultPIMId ID of PIM to use for mapping
     * @return The resource information from RAP, either mapped or unmapped.
     * Returns null if something goes wrong (e.g. resource does not exist, no
     * mapping found, mapping not unique, error applying mapping, etc)
     */
    public String getResourceWithMappingAsGuest(String resourceId, boolean serverValidation, String resultPIMId);

    /**
     * Generic access to a resource based on resource ID. If returned JSON is
     * valid JSON-LD and there is a mapping, the returned result is transformed
     * using this mapping. A mapping is discovered first via resourceId and if
     * that fails or is not unique via himePlatformIds. If this is also not
     * unique, no mapping is done.
     *
     * @param <T> tyoe of result
     * @param resourceId the resource ID
     * @param resultType java class use expected result used for deserialization
     * @param serverValidation if true it will validate RAP
     * @return The resource information from RAP, either mapped or unmapped.
     * Returns null if something goes wrong (e.g. resource does not exist, no
     * mapping found, mapping not unique, error applying mapping, etc)
     * @throws
     * eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException
     */
    public default <T> T getResourceWithMappingAsGuest(String resourceId, Class<T> resultType, boolean serverValidation) throws SecurityHandlerException {
        return getResourceWithMappingAsGuest(
                resourceId,
                resultType,
                new JsonLDObjectMapper(getIncludeTypesFromSuperclasses(), getAlwaysUseArrayForTypeProperty()),
                serverValidation);
    }

    /**
     * Generic access to a resource based on resource ID. If returned JSON is
     * valid JSON-LD and there is a mapping, the returned result is transformed
     * using this mapping.
     *
     * @param <T> tyoe of result
     * @param resourceId the resource ID
     * @param resultType java class use expected result used for deserialization
     * @param serverValidation if true it will validate RAP
     * @param resultPIMId ID of PIM to use for mapping
     * @return The resource information from RAP, either mapped or unmapped.
     * Returns null if something goes wrong (e.g. resource does not exist, no
     * mapping found, mapping not unique, error applying mapping, etc)
     */
    public default <T> T getResourceWithMappingAsGuest(String resourceId, Class<T> resultType, boolean serverValidation, String resultPIMId) {
        return getResourceWithMappingAsGuest(
                resourceId,
                resultType,
                new JsonLDObjectMapper(getIncludeTypesFromSuperclasses(), getAlwaysUseArrayForTypeProperty()),
                serverValidation,
                resultPIMId);
    }

    /**
     * Generic access to a resource based on resource ID. If returned JSON is
     * valid JSON-LD and there is a mapping, the returned result is transformed
     * using this mapping. A mapping is discovered first via resourceId and if
     * that fails or is not unique via himePlatformIds. If this is also not
     * unique, no mapping is done.
     *
     * @param <T> tyoe of result
     * @param resourceId the resource ID
     * @param resultType java class use expected result used for deserialization
     * @param serverValidation if true it will validate RAP
     * @return The resource information from RAP, either mapped or unmapped.
     * Returns null if something goes wrong (e.g. resource does not exist, no
     * mapping found, mapping not unique, error applying mapping, etc)
     * @throws
     * eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException
     */
    public <T> T getResourceWithMappingAsGuest(String resourceId, JavaType resultType, boolean serverValidation) throws SecurityHandlerException;

    /**
     * Generic access to a resource based on resource ID. If returned JSON is
     * valid JSON-LD and there is a mapping, the returned result is transformed
     * using this mapping.
     *
     * @param <T> tyoe of result
     * @param resourceId the resource ID
     * @param resultType java class use expected result used for deserialization
     * @param serverValidation if true it will validate RAP
     * @param resultPIMId ID of PIM to use for mapping
     * @return The resource information from RAP, either mapped or unmapped.
     * Returns null if something goes wrong (e.g. resource does not exist, no
     * mapping found, mapping not unique, error applying mapping, etc)
     */
    public default <T> T getResourceWithMappingAsGuest(String resourceId, JavaType resultType, boolean serverValidation, String resultPIMId) {
        return getResourceWithMappingAsGuest(
                resourceId,
                resultType,
                new JsonLDObjectMapper(getIncludeTypesFromSuperclasses(), getAlwaysUseArrayForTypeProperty()),
                serverValidation,
                resultPIMId);
    }

    /**
     * Generic access to a resource based on resource ID. If returned JSON is
     * valid JSON-LD and there is a mapping, the returned result is transformed
     * using this mapping. A mapping is discovered first via resourceId and if
     * that fails or is not unique via himePlatformIds. If this is also not
     * unique, no mapping is done.
     *
     * @param <T> tyoe of result
     * @param resourceId the resource ID
     * @param resultType java class use expected result used for deserialization
     * @param mapper {@link ObjectMapper} for deserializing result
     * @param serverValidation if true it will validate RAP
     * @return The resource information from RAP, either mapped or unmapped.
     * Returns null if something goes wrong (e.g. resource does not exist, no
     * mapping found, mapping not unique, error applying mapping, etc)
     * @throws
     * eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException
     */
    public default <T> T getResourceWithMappingAsGuest(String resourceId, Class<T> resultType, ObjectMapper mapper, boolean serverValidation) throws SecurityHandlerException {
        return getResourceWithMappingAsGuest(
                resourceId,
                TypeFactory.defaultInstance().constructType(resultType),
                mapper,
                serverValidation);
    }

    /**
     * Generic access to a resource based on resource ID. If returned JSON is
     * valid JSON-LD and there is a mapping, the returned result is transformed
     * using this mapping.
     *
     * @param <T> tyoe of result
     * @param resourceId the resource ID
     * @param resultType java class use expected result used for deserialization
     * @param mapper {@link ObjectMapper} for deserializing result
     * @param serverValidation if true it will validate RAP
     * @param resultPIMId ID of PIM to use for mapping
     * @return The resource information from RAP, either mapped or unmapped.
     * Returns null if something goes wrong (e.g. resource does not exist, no
     * mapping found, mapping not unique, error applying mapping, etc)
     */
    public default <T> T getResourceWithMappingAsGuest(String resourceId, Class<T> resultType, ObjectMapper mapper, boolean serverValidation, String resultPIMId) {
        return getResourceWithMappingAsGuest(
                resourceId,
                TypeFactory.defaultInstance().constructType(resultType),
                mapper,
                serverValidation,
                resultPIMId
        );
    }

    /**
     * Generic access to a resource based on resource ID. If returned JSON is
     * valid JSON-LD and there is a mapping, the returned result is transformed
     * using this mapping. A mapping is discovered first via resourceId and if
     * that fails or is not unique via himePlatformIds. If this is also not
     * unique, no mapping is done.
     *
     * @param <T> tyoe of result
     * @param resourceId the resource ID
     * @param resultType java class use expected result used for deserialization
     * @param mapper {@link ObjectMapper} for deserializing result
     * @param serverValidation if true it will validate RAP
     * @return The resource information from RAP, either mapped or unmapped.
     * Returns null if something goes wrong (e.g. resource does not exist, no
     * mapping found, mapping not unique, error applying mapping, etc)
     * @throws
     * eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException
     */
    public default <T> T getResourceWithMappingAsGuest(String resourceId, JavaType resultType, ObjectMapper mapper, boolean serverValidation) throws SecurityHandlerException {
        String result = getResourceWithMappingAsGuest(resourceId, resultType, serverValidation);
        try {
            return mapper.readValue(result, resultType);
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * Generic access to a resource based on resource ID. If returned JSON is
     * valid JSON-LD and there is a mapping, the returned result is transformed
     * using this mapping.
     *
     * @param <T> tyoe of result
     * @param resourceId the resource ID
     * @param resultType java class use expected result used for deserialization
     * @param mapper {@link ObjectMapper} for deserializing result
     * @param serverValidation if true it will validate RAP
     * @param resultPIMId ID of PIM to use for mapping
     * @return The resource information from RAP, either mapped or unmapped.
     * Returns null if something goes wrong (e.g. resource does not exist, no
     * mapping found, mapping not unique, error applying mapping, etc)
     */
    public default <T> T getResourceWithMappingAsGuest(String resourceId, JavaType resultType, ObjectMapper mapper, boolean serverValidation, String resultPIMId) {
        String result = getResourceWithMappingAsGuest(
                resourceId,
                serverValidation,
                resultPIMId);
        try {
            return mapper.readValue(result, resultType);
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param resourceId the resource ID
     * @param body parameters to send to the service as JSON(-LD).
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public String invokeServiceWithMapping(String resourceId, String body, boolean serverValidation, Set<String> homePlatformIds);

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default String invokeServiceWithMapping(String resourceId, Object parameters, boolean serverValidation, Set<String> homePlatformIds) {
        return invokeServiceWithMapping(
                resourceId,
                parameters,
                new JsonLDObjectMapper(getIncludeTypesFromSuperclasses(), getAlwaysUseArrayForTypeProperty()),
                serverValidation,
                homePlatformIds);
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param <T> type to cast result type to
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param resultType class to deserialize result type
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default <T> T invokeServiceWithMapping(String resourceId, Object parameters, Class<T> resultType, boolean serverValidation, Set<String> homePlatformIds) {
        return invokeServiceWithMapping(resourceId, parameters, TypeFactory.defaultInstance().constructType(resultType), serverValidation, homePlatformIds);
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param <T> type to cast result type to
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param resultType class to deserialize result type
     * @param mapper {@link ObjectMapper} for custom de-/serialization from/to
     * JSON-LD
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default <T> T invokeServiceWithMapping(String resourceId, Object parameters, JavaType resultType, ObjectMapper mapper, boolean serverValidation, Set<String> homePlatformIds) {
        try {
            String jsonLD = mapper.writeValueAsString(parameters);
            String result = invokeServiceWithMapping(
                    resourceId,
                    jsonLD,
                    serverValidation,
                    homePlatformIds);
            return mapper.readValue(result, resultType);
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param <T> type to cast result type to
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param resultType class to deserialize result type
     * @param mapper {@link ObjectMapper} for custom de-/serialization from/to
     * JSON-LD
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default <T> T invokeServiceWithMapping(String resourceId, Object parameters, Class<T> resultType, ObjectMapper mapper, boolean serverValidation, Set<String> homePlatformIds) {
        return invokeServiceWithMapping(resourceId, parameters, mapper.constructType(resultType), mapper, serverValidation, homePlatformIds);
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param mapper {@link ObjectMapper} for custom de-/serialization from/to
     * JSON-LD
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default String
            invokeServiceWithMapping(String resourceId, Object parameters, ObjectMapper mapper, boolean serverValidation, Set<String> homePlatformIds) {
        return invokeServiceWithMapping(resourceId, parameters, String.class,
                mapper, serverValidation, homePlatformIds);
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * @param parameterPIMId ID of PIM used for parameters
     * @param resultPIMId ID of PIM desired for result
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default String invokeServiceWithMapping(String resourceId, Object parameters, boolean serverValidation, Set<String> homePlatformIds, String parameterPIMId, String resultPIMId) {
        return invokeServiceWithMapping(
                resourceId,
                parameters,
                new JsonLDObjectMapper(getIncludeTypesFromSuperclasses(), getAlwaysUseArrayForTypeProperty()),
                serverValidation,
                homePlatformIds,
                parameterPIMId,
                resultPIMId);
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param mapper {@link ObjectMapper} for custom de-/serialization from/to
     * JSON-LD
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * @param parameterPIMId ID of PIM used for parameters
     * @param resultPIMId ID of PIM desired for result
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default String invokeServiceWithMapping(String resourceId, Object parameters, ObjectMapper mapper, boolean serverValidation, Set<String> homePlatformIds, String parameterPIMId, String resultPIMId) {
        try {
            return invokeServiceWithMapping(resourceId, mapper.writeValueAsString(parameters), serverValidation, homePlatformIds, parameterPIMId, resultPIMId);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param resourceId the resource ID
     * @param body parameters to send to the service as JSON(-LD)
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * @param parameterPIMId ID of PIM used for parameters
     * @param resultPIMId ID of PIM desired for result
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public String invokeServiceWithMapping(String resourceId, String body, boolean serverValidation, Set<String> homePlatformIds, String parameterPIMId, String resultPIMId);

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param <T>
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param resultType
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default <T> T invokeServiceWithMapping(String resourceId, Object parameters, JavaType resultType, boolean serverValidation, Set<String> homePlatformIds) {
        return invokeServiceWithMapping(
                resourceId,
                parameters,
                resultType,
                new JsonLDObjectMapper(getIncludeTypesFromSuperclasses(), getAlwaysUseArrayForTypeProperty()),
                serverValidation,
                homePlatformIds);
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param <T>
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param resultType
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * @param parameterPIMId ID of PIM used for parameters
     * @param resultPIMId ID of PIM desired for result
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default <T> T invokeServiceWithMapping(String resourceId, Object parameters, Class<T> resultType, boolean serverValidation, Set<String> homePlatformIds, String parameterPIMId, String resultPIMId) {
        return invokeServiceWithMapping(
                resourceId,
                parameters,
                TypeFactory.defaultInstance().constructType(resultType),
                serverValidation,
                homePlatformIds,
                parameterPIMId,
                resultPIMId);
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param <T>
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param resultType
     * @param mapper {@link ObjectMapper} for custom de-/serialization from/to
     * JSON-LD
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * @param parameterPIMId ID of PIM used for parameters
     * @param resultPIMId ID of PIM desired for result
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default <T> T invokeServiceWithMapping(String resourceId, Object parameters, JavaType resultType, ObjectMapper mapper, boolean serverValidation, Set<String> homePlatformIds, String parameterPIMId, String resultPIMId) {
        try {
            String jsonLD = mapper.writeValueAsString(parameters);
            String result = invokeServiceWithMapping(
                    resourceId,
                    jsonLD,
                    serverValidation,
                    homePlatformIds,
                    parameterPIMId,
                    resultPIMId);
            return mapper.readValue(result, resultType);
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param <T>
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param resultType
     * @param mapper {@link ObjectMapper} for custom de-/serialization from/to
     * JSON-LD
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * @param parameterPIMId ID of PIM used for parameters
     * @param resultPIMId ID of PIM desired for result
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default <T> T invokeServiceWithMapping(String resourceId, Object parameters, Class<T> resultType, ObjectMapper mapper, boolean serverValidation, Set<String> homePlatformIds, String parameterPIMId, String resultPIMId) {
        return invokeServiceWithMapping(
                resourceId,
                parameterPIMId,
                TypeFactory.defaultInstance().constructType(resultType),
                mapper,
                serverValidation,
                homePlatformIds,
                parameterPIMId,
                resultPIMId);
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param <T>
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param resultType
     * @param serverValidation if true it will validate RAP
     * @param homePlatformIds a set of home platform ids from which we are going
     * @param parameterPIMId ID of PIM used for parameters
     * @param resultPIMId ID of PIM desired for result
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default <T> T invokeServiceWithMapping(String resourceId, Object parameters, JavaType resultType, boolean serverValidation, Set<String> homePlatformIds, String parameterPIMId, String resultPIMId) {
        return invokeServiceWithMapping(
                resourceId,
                parameterPIMId,
                TypeFactory.defaultInstance().constructType(resultType),
                new JsonLDObjectMapper(getIncludeTypesFromSuperclasses(), getAlwaysUseArrayForTypeProperty()),
                serverValidation,
                homePlatformIds,
                parameterPIMId,
                resultPIMId);
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param resourceId the resource ID
     * @param body parameters to send to the service as JSON(-LD).
     * @param serverValidation if true it will validate RAP
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public String invokeServiceWithMappingAsGuest(String resourceId, String body, boolean serverValidation);

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param serverValidation if true it will validate RAP
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default String invokeServiceWithMappingAsGuest(String resourceId, Object parameters, boolean serverValidation) {
        return invokeServiceWithMappingAsGuest(
                resourceId,
                parameters,
                new JsonLDObjectMapper(getIncludeTypesFromSuperclasses(), getAlwaysUseArrayForTypeProperty()),
                serverValidation);
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param <T> type to cast result type to
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param resultType class to deserialize result type
     * @param serverValidation if true it will validate RAP
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default <T> T invokeServiceWithMappingAsGuest(String resourceId, Object parameters, Class<T> resultType, boolean serverValidation) {
        return invokeServiceWithMappingAsGuest(resourceId, parameters, TypeFactory.defaultInstance().constructType(resultType), serverValidation);
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param <T> type to cast result type to
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param resultType class to deserialize result type
     * @param mapper {@link ObjectMapper} for custom de-/serialization from/to
     * JSON-LD
     * @param serverValidation if true it will validate RAP
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default <T> T invokeServiceWithMappingAsGuest(String resourceId, Object parameters, JavaType resultType, ObjectMapper mapper, boolean serverValidation) {
        try {
            String jsonLD = mapper.writeValueAsString(parameters);
            String result = invokeServiceWithMappingAsGuest(
                    resourceId,
                    jsonLD,
                    serverValidation);
            return mapper.readValue(result, resultType);
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param <T> type to cast result type to
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param resultType class to deserialize result type
     * @param mapper {@link ObjectMapper} for custom de-/serialization from/to
     * JSON-LD
     * @param serverValidation if true it will validate RAP
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default <T> T invokeServiceWithMappingAsGuest(String resourceId, Object parameters, Class<T> resultType, ObjectMapper mapper, boolean serverValidation) {
        return invokeServiceWithMappingAsGuest(resourceId, parameters, mapper.constructType(resultType), mapper, serverValidation);
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param mapper {@link ObjectMapper} for custom de-/serialization from/to
     * JSON-LD
     * @param serverValidation if true it will validate RAP
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default String
            invokeServiceWithMappingAsGuest(String resourceId, Object parameters, ObjectMapper mapper, boolean serverValidation) {
        return invokeServiceWithMappingAsGuest(resourceId, parameters, String.class,
                mapper, serverValidation);
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param serverValidation if true it will validate RAP
     * @param parameterPIMId ID of PIM used for parameters
     * @param resultPIMId ID of PIM desired for result
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default String invokeServiceWithMappingAsGuest(String resourceId, Object parameters, boolean serverValidation, String parameterPIMId, String resultPIMId) {
        return invokeServiceWithMappingAsGuest(
                resourceId,
                parameters,
                new JsonLDObjectMapper(getIncludeTypesFromSuperclasses(), getAlwaysUseArrayForTypeProperty()),
                serverValidation,
                parameterPIMId,
                resultPIMId);
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param mapper {@link ObjectMapper} for custom de-/serialization from/to
     * JSON-LD
     * @param serverValidation if true it will validate RAP
     * @param parameterPIMId ID of PIM used for parameters
     * @param resultPIMId ID of PIM desired for result
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default String invokeServiceWithMappingAsGuest(String resourceId, Object parameters, ObjectMapper mapper, boolean serverValidation, String parameterPIMId, String resultPIMId) {
        try {
            return invokeServiceWithMappingAsGuest(resourceId, mapper.writeValueAsString(parameters), serverValidation, parameterPIMId, resultPIMId);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param resourceId the resource ID
     * @param body parameters to send to the service as JSON(-LD)
     * @param serverValidation if true it will validate RAP
     * @param parameterPIMId ID of PIM used for parameters
     * @param resultPIMId ID of PIM desired for result
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public String invokeServiceWithMappingAsGuest(String resourceId, String body, boolean serverValidation, String parameterPIMId, String resultPIMId);

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param <T>
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param resultType
     * @param serverValidation if true it will validate RAP
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default <T> T invokeServiceWithMappingAsGuest(String resourceId, Object parameters, JavaType resultType, boolean serverValidation) {
        return invokeServiceWithMappingAsGuest(
                resourceId,
                parameters,
                resultType,
                new JsonLDObjectMapper(getIncludeTypesFromSuperclasses(), getAlwaysUseArrayForTypeProperty()),
                serverValidation);
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param <T>
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param resultType
     * @param serverValidation if true it will validate RAP
     * @param parameterPIMId ID of PIM used for parameters
     * @param resultPIMId ID of PIM desired for result
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default <T> T invokeServiceWithMappingAsGuest(String resourceId, Object parameters, Class<T> resultType, boolean serverValidation, String parameterPIMId, String resultPIMId) {
        return invokeServiceWithMappingAsGuest(
                resourceId,
                parameters,
                TypeFactory.defaultInstance().constructType(resultType),
                serverValidation,
                parameterPIMId,
                resultPIMId);
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param <T>
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param resultType
     * @param mapper {@link ObjectMapper} for custom de-/serialization from/to
     * JSON-LD
     * @param serverValidation if true it will validate RAP
     * @param parameterPIMId ID of PIM used for parameters
     * @param resultPIMId ID of PIM desired for result
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default <T> T invokeServiceWithMappingAsGuest(String resourceId, Object parameters, JavaType resultType, ObjectMapper mapper, boolean serverValidation, String parameterPIMId, String resultPIMId) {
        try {
            String jsonLD = mapper.writeValueAsString(parameters);
            String result = invokeServiceWithMappingAsGuest(
                    resourceId,
                    jsonLD,
                    serverValidation,
                    parameterPIMId,
                    resultPIMId);
            return mapper.readValue(result, resultType);
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param <T>
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param resultType
     * @param mapper {@link ObjectMapper} for custom de-/serialization from/to
     * JSON-LD
     * @param serverValidation if true it will validate RAP
     * @param parameterPIMId ID of PIM used for parameters
     * @param resultPIMId ID of PIM desired for result
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default <T> T invokeServiceWithMappingAsGuest(String resourceId, Object parameters, Class<T> resultType, ObjectMapper mapper, boolean serverValidation, String parameterPIMId, String resultPIMId) {
        return invokeServiceWithMappingAsGuest(
                resourceId,
                parameterPIMId,
                TypeFactory.defaultInstance().constructType(resultType),
                mapper,
                serverValidation,
                parameterPIMId,
                resultPIMId);
    }

    /**
     * Invokes a service by resourceId. If possible, mapping is applied to input
     * and output.
     *
     * @param <T>
     * @param resourceId the resource ID
     * @param parameters parameters to send to the service. Will be serialized
     * to JSON(-LD). If input mapping should be used, each class must use the
     * {@link JsonLDType} annotation.
     * @param resultType
     * @param serverValidation if true it will validate RAP
     * @param parameterPIMId ID of PIM used for parameters
     * @param resultPIMId ID of PIM desired for result
     * @return result of invoking the service with resourceId, mapped to other
     * PIM if possible
     */
    public default <T> T invokeServiceWithMappingAsGuest(String resourceId, Object parameters, JavaType resultType, boolean serverValidation, String parameterPIMId, String resultPIMId) {
        return invokeServiceWithMappingAsGuest(
                resourceId,
                parameterPIMId,
                TypeFactory.defaultInstance().constructType(resultType),
                new JsonLDObjectMapper(getIncludeTypesFromSuperclasses(), getAlwaysUseArrayForTypeProperty()),
                serverValidation,
                parameterPIMId,
                resultPIMId);
    }

}
