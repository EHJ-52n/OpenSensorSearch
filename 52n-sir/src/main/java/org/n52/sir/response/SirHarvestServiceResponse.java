/**
 * ﻿Copyright (C) 2012 52°North Initiative for Geospatial Open Source Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.n52.sir.response;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.n52.sir.SirConfigurator;
import org.n52.sir.datastructure.SirSensor;
import org.n52.sir.util.XmlTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.x52North.sir.x032.HarvestServiceResponseDocument;
import org.x52North.sir.x032.HarvestServiceResponseDocument.HarvestServiceResponse;
import org.x52North.sir.x032.HarvestServiceResponseDocument.HarvestServiceResponse.DeletedSensor;
import org.x52North.sir.x032.HarvestServiceResponseDocument.HarvestServiceResponse.FailedSensor;
import org.x52North.sir.x032.HarvestServiceResponseDocument.HarvestServiceResponse.InsertedSensor;
import org.x52North.sir.x032.HarvestServiceResponseDocument.HarvestServiceResponse.UpdatedSensor;

/**
 * Internal response to a harvest service request
 * 
 * @author Jan Schulte
 * 
 */
public class SirHarvestServiceResponse extends AbstractXmlResponse {

    private static Logger log = LoggerFactory.getLogger(SirHarvestServiceResponse.class);

    /**
     * collection of the deleted sensors in the SIR
     */
    private Collection<SirSensor> deletedSensors;

    /**
     * collection of sensors which could not be inserted in the SIR
     */
    private Collection<String> failedSensors;

    /**
     * 
     */
    private Map<String, String> failureDescriptions = new HashMap<String, String>();

    /**
     * collection of the inserted sensors in the SIR
     */
    private Collection<SirSensor> insertedSensors;

    /**
     * number of sensors deleted from the SIR
     */
    private int numberOfDeletedSensors;

    /**
     * number of sensors which could no be inserted in the SIR
     */
    private int numberOfFailedSensors;

    /**
     * number of sensors found in the service
     */
    private int numberOfFoundSensors;

    /**
     * number of sensors inserted in the SIR
     */
    private int numberOfInsertedSensors;

    /**
     * number of sensors updated in the SIR
     */
    private int numberOfUpdatedSensors;

    /**
     * the harvested service type
     */
    private String serviceType;

    /**
     * the harvested service URL
     */
    private String serviceUrl;

    /**
     * collection of the updated sensors in the SIR
     */
    private Collection<SirSensor> updatedSensors;

    /**
     * 
     * @param sensor
     * @param description
     */
    public void addFailureDescription(String sensor, String description) {
        this.failureDescriptions.put(sensor, description);
    }

    @Override
    public HarvestServiceResponseDocument createXml() {
        HarvestServiceResponseDocument document = HarvestServiceResponseDocument.Factory.newInstance();
        HarvestServiceResponse harvServResp = document.addNewHarvestServiceResponse();

        // set service URL
        harvServResp.setServiceURL(this.serviceUrl);
        // set service type
        harvServResp.setServiceType(this.serviceType);
        // set number of found sensors
        harvServResp.setNumberOfFoundSensors(this.numberOfFoundSensors);
        // set number of inserted sensors
        harvServResp.setNumberOfInsertedSensors(this.numberOfInsertedSensors);
        // set number of deleted sensors
        harvServResp.setNumberOfDeletedSensors(this.numberOfDeletedSensors);
        // set number of updated sensors
        harvServResp.setNumberOfUpdatedSensors(this.numberOfUpdatedSensors);
        // set number of failed sensors
        harvServResp.setNumberOfFailedSensors(this.numberOfFailedSensors);

        // set collection of inserted sensors
        for (SirSensor inSens : this.insertedSensors) {
            InsertedSensor insertedSensor = harvServResp.addNewInsertedSensor();
            insertedSensor.setSensorIDInSIR(inSens.getInternalSensorID());
            insertedSensor.setServiceSpecificSensorID(inSens.getServDescs().iterator().next().getServiceSpecificSensorId());
        }
        // set collection of deleted sensors
        for (SirSensor delSens : this.deletedSensors) {
            DeletedSensor deletedSensor = harvServResp.addNewDeletedSensor();
            deletedSensor.setSensorIDInSIR(delSens.getInternalSensorID());
            deletedSensor.setServiceSpecificSensorID(delSens.getServDescs().iterator().next().getServiceSpecificSensorId());
        }
        // set collection of updated sensors
        for (SirSensor upSens : this.updatedSensors) {
            UpdatedSensor updatedSensor = harvServResp.addNewUpdatedSensor();
            updatedSensor.setSensorIDInSIR(upSens.getInternalSensorID());
            updatedSensor.setServiceSpecificSensorID(upSens.getServDescs().iterator().next().getServiceSpecificSensorId());
        }
        // set collection of failed sensors
        for (String failSens : this.failedSensors) {
            String failureDescr = "NOT_AVAILABLE";
            if (this.failureDescriptions.get(failSens) != null) {
                failureDescr = this.failureDescriptions.get(failSens);
            }

            FailedSensor failedSensor = harvServResp.addNewFailedSensor();
            failedSensor.setFailureDescription(failureDescr);
            failedSensor.setServiceSpecificSensorID(failSens);
        }

        XmlTools.addSirAndSensorMLSchemaLocation(harvServResp);

        if (SirConfigurator.getInstance().isValidateResponses()) {
            if ( !document.validate())
                log.warn("Service created invalid document!\n" + XmlTools.validateAndIterateErrors(document));
        }

        return document;
    }

    /**
     * @return the deletedSensors
     */
    public Collection<SirSensor> getDeletedSensors() {
        return this.deletedSensors;
    }

    /**
     * @return the failedSensors
     */
    public Collection<String> getFailedSensors() {
        return this.failedSensors;
    }

    /**
     * @return the failureDescriptions
     */
    public Map<String, String> getFailureDescriptions() {
        return this.failureDescriptions;
    }

    /**
     * @return the insertedSensors
     */
    public Collection<SirSensor> getInsertedSensors() {
        return this.insertedSensors;
    }

    /**
     * @return the numberOfDeletedSensors
     */
    public int getNumberOfDeletedSensors() {
        return this.numberOfDeletedSensors;
    }

    /**
     * @return the numberOfFailedSensors
     */
    public int getNumberOfFailedSensors() {
        return this.numberOfFailedSensors;
    }

    /**
     * @return the numberOfFoundSensors
     */
    public int getNumberOfFoundSensors() {
        return this.numberOfFoundSensors;
    }

    /**
     * @return the numberOfInsertedSensors
     */
    public int getNumberOfInsertedSensors() {
        return this.numberOfInsertedSensors;
    }

    /**
     * @return the numberOfUpdatedSensors
     */
    public int getNumberOfUpdatedSensors() {
        return this.numberOfUpdatedSensors;
    }

    /**
     * @return the serviceType
     */
    public String getServiceType() {
        return this.serviceType;
    }

    /**
     * @return the serviceUrl
     */
    public String getServiceUrl() {
        return this.serviceUrl;
    }

    /**
     * @return the updatedSensors
     */
    public Collection<SirSensor> getUpdatedSensors() {
        return this.updatedSensors;
    }

    /**
     * @param deletedSensors
     *        the deletedSensors to set
     */
    public void setDeletedSensors(Collection<SirSensor> deletedSensors) {
        this.deletedSensors = deletedSensors;
    }

    /**
     * @param failedSensors
     *        the failedSensors to set
     */
    public void setFailedSensors(Collection<String> failedSensors) {
        this.failedSensors = failedSensors;
    }

    /**
     * @param insertedSensors
     *        the insertedSensors to set
     */
    public void setInsertedSensors(Collection<SirSensor> insertedSensors) {
        this.insertedSensors = insertedSensors;
    }

    /**
     * @param numberOfDeletedSensors
     *        the numberOfDeletedSensors to set
     */
    public void setNumberOfDeletedSensors(int numberOfDeletedSensors) {
        this.numberOfDeletedSensors = numberOfDeletedSensors;
    }

    /**
     * @param numberOfFailedSensors
     *        the numberOfFailedSensors to set
     */
    public void setNumberOfFailedSensors(int numberOfFailedSensors) {
        this.numberOfFailedSensors = numberOfFailedSensors;
    }

    /**
     * @param numberOfFoundSensors
     *        the numberOfFoundSensors to set
     */
    public void setNumberOfFoundSensors(int numberOfFoundSensors) {
        this.numberOfFoundSensors = numberOfFoundSensors;
    }

    /**
     * @param numberOfInsertedSensors
     *        the numberOfInsertedSensors to set
     */
    public void setNumberOfInsertedSensors(int numberOfInsertedSensors) {
        this.numberOfInsertedSensors = numberOfInsertedSensors;
    }

    /**
     * @param numberOfUpdatedSensors
     *        the numberOfUpdatedSensors to set
     */
    public void setNumberOfUpdatedSensors(int numberOfUpdatedSensors) {
        this.numberOfUpdatedSensors = numberOfUpdatedSensors;
    }

    /**
     * @param serviceType
     *        the serviceType to set
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * @param serviceUrl
     *        the serviceUrl to set
     */
    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    /**
     * @param updatedSensors
     *        the updatedSensors to set
     */
    public void setUpdatedSensors(Collection<SirSensor> updatedSensors) {
        this.updatedSensors = updatedSensors;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("SirHarvestServiceResponse: ");
        sb.append("ServiceType: " + this.serviceType);
        sb.append(", ServiceURL: " + this.serviceUrl);
        sb.append(", Number Found Sensors: " + this.numberOfFoundSensors);
        sb.append(", Number Inserted Sensors: " + this.numberOfInsertedSensors);
        sb.append(", Number Deleted Sensors: " + this.numberOfDeletedSensors);
        sb.append(", Number Updated Sensors: " + this.numberOfUpdatedSensors);
        sb.append(", Number Failed Sensors: " + this.numberOfFailedSensors);
        sb.append(", Inserted Sensors: " + this.insertedSensors);
        sb.append(", Deleted Sensors: " + this.deletedSensors);
        sb.append(", Updated Sensors: " + this.updatedSensors);
        sb.append(", Failed Sensors: " + this.failedSensors);
        return sb.toString();
    }

}
