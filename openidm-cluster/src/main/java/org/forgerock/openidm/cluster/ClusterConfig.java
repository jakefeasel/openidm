/**
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
*
* Copyright (c) 2013-2015 ForgeRock AS. All Rights Reserved
*
* The contents of this file are subject to the terms
* of the Common Development and Distribution License
* (the License). You may not use this file except in
* compliance with the License.
*
* You can obtain a copy of the License at
* http://forgerock.org/license/CDDLv1.0.html
* See the License for the specific language governing
* permission and limitations under the License.
*
* When distributing Covered Code, include this CDDL
* Header Notice in each file and include the License file
* at http://forgerock.org/license/CDDLv1.0.html
* If applicable, add the following below the CDDL Header,
* with the fields enclosed by brackets [] replaced by
* your own identifying information:
* "Portions Copyrighted [year] [name of copyright owner]"
*
*/
package org.forgerock.openidm.cluster;

import java.util.Properties;

import org.forgerock.json.JsonValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClusterConfig {
    final static Logger logger = LoggerFactory.getLogger(ClusterConfig.class);

    
    // Scheduler objects
    private final static String INSTANCE_ID = "instanceId";
    private final static String INSTANCE_TIMEOUT = "instanceTimeout";
    private final static String INSTANCE_RECOVERY_TIMEOUT = "instanceRecoveryTimeout";
    private final static String INSTANCE_CHECK_IN_INTERVAL = "instanceCheckInInterval";
    private final static String INSTANCE_CHECK_IN_OFFSET = "instanceCheckInOffset";
    private final static String ENABLED = "enabled";
    
    private String instanceId = "instance0";
    private long instanceTimeout = 30000;
    private long instanceRecoveryTimeout = 30000;
    private long instanceCheckInInterval = 5000;
    private long instanceCheckInOffset = 0;
    private boolean enabled = true;
    
    public ClusterConfig(JsonValue config) {
        if (!config.isNull()) {
            JsonValue value = config.get(INSTANCE_ID);
            if (!value.isNull()) {
                instanceId = value.asString();
            }
            value = config.get(INSTANCE_TIMEOUT);
            if (!value.isNull()) {
                setInstanceTimeout(Long.parseLong(value.asString()));
            }
            value = config.get(INSTANCE_RECOVERY_TIMEOUT);
            if (!value.isNull()) {
                setInstanceRecoveryTimeout(Long.parseLong(value.asString()));
            }
            value = config.get(INSTANCE_CHECK_IN_INTERVAL);
            if (!value.isNull()) {
                setInstanceCheckInInterval(Long.parseLong(value.asString()));
            }
            value = config.get(INSTANCE_CHECK_IN_OFFSET);
            if (!value.isNull()) {
                setInstanceCheckInOffset(Long.parseLong(value.asString()));
            }
            value = config.get(ENABLED);
            if (!value.isNull() && value.isBoolean()) {
                setEnabled(value.asBoolean());
            } else if (!value.isNull() && value.isString()) {
                setEnabled(Boolean.parseBoolean(value.asString()));
            }
        }
    }
    
    public String getInstanceId() {
            return instanceId;
    }
    
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public Properties toProps() {
        Properties props = new Properties();
        props.put(INSTANCE_TIMEOUT, getInstanceTimeout());
        props.put(INSTANCE_RECOVERY_TIMEOUT, getInstanceRecoveryTimeout());
        props.put(INSTANCE_CHECK_IN_INTERVAL, getInstanceCheckInInterval());
        props.put(INSTANCE_CHECK_IN_OFFSET, getInstanceCheckInOffset());
        props.put(INSTANCE_ID, getInstanceId());
        return props;
    }

    public long getInstanceTimeout() {
        return instanceTimeout;
    }

    public void setInstanceTimeout(long instanceTimeout) {
        this.instanceTimeout = instanceTimeout;
    }

    public long getInstanceRecoveryTimeout() {
        return instanceRecoveryTimeout;
    }

    public void setInstanceRecoveryTimeout(long instanceRecoveryTimeout) {
        this.instanceRecoveryTimeout = instanceRecoveryTimeout;
    }

    public long getInstanceCheckInInterval() {
        return instanceCheckInInterval;
    }

    public void setInstanceCheckInInterval(long instanceCheckInInterval) {
        this.instanceCheckInInterval = instanceCheckInInterval;
    }

    public long getInstanceCheckInOffset() {
        return instanceCheckInOffset;
    }

    public void setInstanceCheckInOffset(long instanceCheckInOffset) {
        this.instanceCheckInOffset = instanceCheckInOffset;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
