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
package org.n52.sir.xml;

import net.opengis.sensorML.x101.SensorMLDocument.SensorML;

import org.n52.sir.ows.OwsExceptionReport;

/**
 * @author Daniel Nüst
 * 
 */
public interface IValidatorFactory {

    /**
     * 
     * Method returns an instance of an {@link IProfileValidator} which is to be used for validating a
     * {@link SensorML} document before it is transformed for insertion into a catalogue.
     * 
     * @return
     * @throws OwsExceptionReport
     */
    public abstract IProfileValidator getSensorMLProfile4DiscoveryValidator() throws OwsExceptionReport;

}
