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

package org.n52.oss.opensearch;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.n52.oss.opensearch.listeners.OpenSearchListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * TODO move everything in configurator to external configuration file!
 * 
 * @author Daniel Nüst (d.nuest@52north.org)
 * 
 */
public class OpenSearchConfigurator {

    private static Logger log = LoggerFactory.getLogger(OpenSearchConfigurator.class);

    public static final String HOME_URL = "/OpenSensorSearch";

    // private static SirConfigurator sirConfigurator = SirConfigurator.getInstance();

    private int capabilitiesCacheMaximumAgeSeconds = 60 * 60;

    /**
     * use contextually shortended urls (replacing long string identifiers with integer ids)
     */
    private boolean compressPermalinks = true;

    private String cssFile = "sir.css";

    private String feedAuthor = "Open Sensor Search by 52°North";

    // TODO move to external configuration file
    private String permalinkBaseURL = "http://sensorweb.demo.52north.org/sensorwebclient-webapp-stable/";

    private SimpleDateFormat permalinkDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    HashMap<String, String> responseFormats = new HashMap<>();

    private String responseAuthor = "52°North";

    private String openSearchPath = "/search";

    private String fullServicePath = "http://localhost:8080" + HOME_URL;

    public OpenSearchConfigurator() {
        log.info("NEW {}", this);
    }

    public void addResponseFormat(OpenSearchListener listener) {
        this.responseFormats.put(listener.getMimeType(), listener.getName());
    }

    public int getCapabilitiesCacheMaximumAgeSeconds() {
        return this.capabilitiesCacheMaximumAgeSeconds;
    }

    public String getCssFile() {
        return this.cssFile;
    }

    public String getFeedAuthor() {
        return this.feedAuthor;
    }

    public String getFullOpenSearchPath() {
        return getWebsiteHome().toString() + getOpenSearchPath();
    }

    public String getWebsiteHome() {
        return this.fullServicePath;
    }

    public String getOpenSearchPath() {
        return this.openSearchPath;
    }

    public String getPermalinkBaseURL() {
        return this.permalinkBaseURL;
    }

    public SimpleDateFormat getPermalinkDateFormat() {
        return this.permalinkDateFormat;
    }

    public Map<String, String> getResponseFormats() {
        return this.responseFormats;
    }

    public boolean isCompressPermalinks() {
        return this.compressPermalinks;
    }

    public String getResponseAuthor() {
        return this.responseAuthor;
    }

}
