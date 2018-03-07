package eu.h2020.symbiote.util;

public interface RabbitConstants {

    String EXCHANGE_RH_NAME_PROPERTY = "rabbit.exchange.rh.name";
    String EXCHANGE_RH_TYPE_PROPERTY = "rabbit.exchange.rh.type";
    String EXCHANGE_RH_DURABLE_PROPERTY = "rabbit.exchange.rh.durable";
    String EXCHANGE_RH_AUTODELETE_PROPERTY = "rabbit.exchange.rh.autodelete";
    String EXCHANGE_RH_INTERNAL_PROPERTY = "rabbit.exchange.rh.internal";
    String EXCHANGE_PLATFORM_REGISTRY_NAME_PROPERTY = "rabbit.exchange.platformRegistry.name";
    String EXCHANGE_PLATFORM_REGISTRY_TYPE_PROPERTY = "rabbit.exchange.platformRegistry.type";
    String EXCHANGE_PLATFORM_REGISTRY_DURABLE_PROPERTY = "rabbit.exchange.platformRegistry.durable";
    String EXCHANGE_PLATFORM_REGISTRY_AUTODELETE_PROPERTY = "rabbit.exchange.platformRegistry.autodelete";
    String EXCHANGE_PLATFORM_REGISTRY_INTERNAL_PROPERTY = "rabbit.exchange.platformRegistry.internal";
    String ROUTING_KEY_RH_REGISTER_PROPERTY = "rabbit.routingKey.rh.core.register";
    String ROUTING_KEY_RH_UPDATE_PROPERTY = "rabbit.routingKey.rh.core.update";
    String ROUTING_KEY_RH_DELETE_PROPERTY = "rabbit.routingKey.rh.core.delete";
    String ROUTING_KEY_PLATFORM_REGISTRY_UPDATE_PROPERTY = "rabbit.routingKey.platformRegistry.update";
    String ROUTING_KEY_PLATFORM_REGISTRY_DELETE_PROPERTY = "rabbit.routingKey.platformRegistry.delete";
    String ROUTING_KEY_PLATFORM_REGISTRY_SHARE_PROPERTY = "rabbit.routingKey.platformRegistry.share";
    String ROUTING_KEY_PLATFORM_REGISTRY_UNSHARE_PROPERTY = "rabbit.routingKey.platformRegistry.unshare";
    String ROUTING_KEY_RH_UPDATED_PROPERTY = "rabbit.routingKey.rh.updated";
    String ROUTING_KEY_RH_DELETED_PROPERTY = "rabbit.routingKey.rh.deleted";
    String ROUTING_KEY_RH_SHARED_PROPERTY = "rabbit.routingKey.rh.shared";
    String ROUTING_KEY_RH_UNSHARED_PROPERTY = "rabbit.routingKey.rh.unshared";

}
