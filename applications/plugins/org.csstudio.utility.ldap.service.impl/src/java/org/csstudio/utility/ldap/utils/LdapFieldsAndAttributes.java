/*
 * Copyright (c) 2010 Stiftung Deutsches Elektronen-Synchrotron,
 * Member of the Helmholtz Association, (DESY), HAMBURG, GERMANY.
 *
 * THIS SOFTWARE IS PROVIDED UNDER THIS LICENSE ON AN "../AS IS" BASIS.
 * WITHOUT WARRANTY OF ANY KIND, EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR PARTICULAR PURPOSE AND
 * NON-INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR
 * THE USE OR OTHER DEALINGS IN THE SOFTWARE. SHOULD THE SOFTWARE PROVE DEFECTIVE
 * IN ANY RESPECT, THE USER ASSUMES THE COST OF ANY NECESSARY SERVICING, REPAIR OR
 * CORRECTION. THIS DISCLAIMER OF WARRANTY CONSTITUTES AN ESSENTIAL PART OF THIS LICENSE.
 * NO USE OF ANY SOFTWARE IS AUTHORIZED HEREUNDER EXCEPT UNDER THIS DISCLAIMER.
 * DESY HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS,
 * OR MODIFICATIONS.
 * THE FULL LICENSE SPECIFYING FOR THE SOFTWARE THE REDISTRIBUTION, MODIFICATION,
 * USAGE AND OTHER RIGHTS AND OBLIGATIONS IS INCLUDED WITH THE DISTRIBUTION OF THIS
 * PROJECT IN THE FILE LICENSE.HTML. IF THE LICENSE IS NOT INCLUDED YOU MAY FIND A COPY
 * AT HTTP://WWW.DESY.DE/LEGAL/LICENSE.HTM
 *
 * $Id$
 */
package org.csstudio.utility.ldap.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

import org.apache.log4j.Logger;
import org.csstudio.platform.logging.CentralLogger;

/**
 * Constants for LDAP field names, popular values, and forbidden symbols in LDAP entry names.
 *
 * @author bknerr
 * @author $Author$
 * @version $Revision$
 * @since 20.04.2010
 */
public final class LdapFieldsAndAttributes {

    private static final Logger LOG =
        CentralLogger.getInstance().getLogger(LdapFieldsAndAttributes.class);

    public static final String COUNTRY_FIELD_NAME = "c";
    public static final String ORGANIZATION_FIELD_NAME = "o";
    public static final String OU_FIELD_NAME = "ou";

    public static final String EPICS_CTRL_FIELD_VALUE = "EpicsControls";
    public static final String EPICS_AUTH_ID_FIELD_VALUE = "EpicsAuthorizeID";
    public static final String FIELD_SEPARATOR = ",";
    public static final String FIELD_ASSIGNMENT = "=";
    public static final String FIELD_WILDCARD = "*";

    public static final String EFAN_FIELD_NAME = "efan";
    public static final String ECOM_FIELD_NAME = "ecom";
    public static final String ECON_FIELD_NAME = "econ";
    public static final String EREN_FIELD_NAME = "eren";
    public static final String ECOM_EPICS_IOC_FIELD_VALUE = "EPICS-IOC";

    public static final String EAIN_FIELD_NAME = "eain";
    public static final String EAIR_FIELD_NAME = "eair";
    public static final String EAIG_FIELD_NAME = "eaig";


    public static final String ATTR_FIELD_OBJECT_CLASS = "objectClass";
    public static final String ATTR_VAL_RECORD_OBJECT_CLASS = "epicsRecord";
    public static final String ATTR_VAL_IOC_OBJECT_CLASS = "epicsController";

    public static final String ATTR_FIELD_RESPONSIBLE_PERSON = "epicsResponsibleName";

    public static final String ATTR_FIELD_LAST_UPDATED = "lastUpdated";
    public static final String ATTR_FIELD_LAST_UPDATED_IN_MILLIS = "lastUpdatedInMillis";

    // TODO (bknerr) : try to figure out whether enums could be used for distinct groups
    public static final String ATTR_FIELD_ALARM_SEVERITY = "epicsAlarmSeverity";
    public static final String ATTR_FIELD_ALARM_STATUS = "epicsAlarmStatus";
    public static final String ATTR_FIELD_ALARM_TIMESTAMP = "epicsAlarmTimeStamp";
    public static final String ATTR_FIELD_ALARM_HIGH_UNACK = "epicsAlarmHighUnAckn";


    public static final Set<String> FORBIDDEN_SUBSTRINGS = new HashSet<String>();
    public static LdapName LDAP_ROOT;

    static {
        List<Rdn> rdns;
        try {
            rdns = Arrays.asList(new Rdn[] {new Rdn(COUNTRY_FIELD_NAME + FIELD_ASSIGNMENT + "DE"),
                                            new Rdn(ORGANIZATION_FIELD_NAME + FIELD_ASSIGNMENT + "DESY")});
        LDAP_ROOT = new LdapName(rdns);
        } catch (final InvalidNameException e) {
            LOG.error("LDAP ROOT variable could not be initialised.", e);
        }
    }

    /**
     * See http://www.ietf.org/rfc/rfc2253.txt
     */
    static {
        FORBIDDEN_SUBSTRINGS.add(",");
        FORBIDDEN_SUBSTRINGS.add("+");
        FORBIDDEN_SUBSTRINGS.add("\"");
        FORBIDDEN_SUBSTRINGS.add("/");
        FORBIDDEN_SUBSTRINGS.add("\\");
        FORBIDDEN_SUBSTRINGS.add("<");
        FORBIDDEN_SUBSTRINGS.add(">");
        FORBIDDEN_SUBSTRINGS.add(";");
    }

    /**
     * Don't instantiate.
     */
    private LdapFieldsAndAttributes() {
        // Empty
    }

}
