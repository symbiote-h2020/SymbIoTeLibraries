package eu.h2020.symbiote.model.cim;

import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents CIM-defined Mobile Sensor class.
 *
 * Created by Mael on 28/03/2017.
 */
@QueryEntity
@Document
public class MobileSensor extends Sensor {

}
