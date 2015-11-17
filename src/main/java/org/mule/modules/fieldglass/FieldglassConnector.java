/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */


package org.mule.modules.fieldglass;

import javax.xml.ws.BindingProvider;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.mule.api.ConnectionException;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.TestConnectivity;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.display.Password;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.annotations.param.Default;
import org.mule.modules.fieldglass.api.ConnectorPortType;
import org.mule.modules.fieldglass.api.Download;
import org.mule.modules.fieldglass.api.Echo;
import org.mule.modules.fieldglass.api.InsiteFaultMessage;
import org.mule.modules.fieldglass.api.Upload;
import org.mule.modules.fieldglass.api.UploadXML;


/**
 * Anypoint Connector.
 * 
 * No description available
 * 
 * @author MuleSoft, Inc.
 * 
 */
@Connector(name = "fieldglass", friendlyName = "Fieldglass", schemaVersion = "1.0")
public class FieldglassConnector {

    /**
     * Endpoint for interface ConnectorPortType
     * 
     */
    @Configurable
    @Default("https://ws.fieldglass.net/ws2/services/Connector")
    private java.lang.String connectorPortTypeEndpoint;
    /**
     * Interface to send requests using ConnectorPortType
     * 
     */
    private ConnectorPortType connectorPortType;

    /**
     * Retrieves connectorPortTypeEndpoint
     * 
     */
    public java.lang.String getConnectorPortTypeEndpoint() {
        return this.connectorPortTypeEndpoint;
    }

    /**
     * Sets connectorPortTypeEndpoint
     * 
     * @param value Value to set
     */
    public void setConnectorPortTypeEndpoint(java.lang.String value) {
        this.connectorPortTypeEndpoint = value;
    }

    /**
     * Retrieves connectorPortType
     * 
     */
    public ConnectorPortType getConnectorPortType() {
        return this.connectorPortType;
    }

    /**
     * Sets connectorPortType
     * 
     * @param value Value to set
     */
    public void setConnectorPortType(ConnectorPortType value) {
        this.connectorPortType = value;
    }

    /**
     * No summary available
     * 
     * {@sample.xml ../../../doc/fieldglass-connector.xml.sample fieldglass:upload}
     * 
     * @param upload No description available
     * @return Return value
     * @throws InsiteFaultMessage      When the call fails
     */
    @Processor
    public java.lang.String upload(
        @Default("#[payload]")
        Upload upload)
        throws InsiteFaultMessage
    {
        return connectorPortType.upload(upload);
    }

    /**
     * No summary available
     * 
     * {@sample.xml ../../../doc/fieldglass-connector.xml.sample fieldglass:download}
     * 
     * @param download No description available
     * @return Return value
     * @throws InsiteFaultMessage      When the call fails
     */
    @Processor
    public java.lang.String download(
        @Default("#[payload]")
        Download download)
        throws InsiteFaultMessage
    {
        return connectorPortType.download(download);
    }

    /**
     * No summary available
     * 
     * {@sample.xml ../../../doc/fieldglass-connector.xml.sample fieldglass:echo}
     * 
     * @param echo No description available
     * @return Return value
     * @throws InsiteFaultMessage      When the call fails
     */
    @Processor
    public java.lang.String echo(
        @Default("#[payload]")
        Echo echo)
        throws InsiteFaultMessage
    {
        return connectorPortType.echo(echo);
    }

    /**
     * No summary available
     * 
     * {@sample.xml ../../../doc/fieldglass-connector.xml.sample fieldglass:upload-x-m-l}
     * 
     * @param uploadXML No description available
     * @return Return value
     * @throws InsiteFaultMessage      When the call fails
     */
    @Processor
    public java.lang.String uploadXML(
        @Default("#[payload]")
        UploadXML uploadXML)
        throws InsiteFaultMessage
    {
        return connectorPortType.uploadXML(uploadXML);
    }

    /**
     * Connect
     * 
     * @param username A username
     * @param password A password
     * @throws ConnectionException      When the call fails
     */
    @Connect
    @TestConnectivity
    public void connect(
        @ConnectionKey
        java.lang.String username,
        @Password
        java.lang.String password)
        throws ConnectionException
    {
        //TODO: Add connection logic
        if (connectorPortType == null) {
            JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
            factory.setServiceClass((org.mule.modules.fieldglass.api.ConnectorPortType.class));
            connectorPortType = ((ConnectorPortType) factory.create());
            ((BindingProvider) connectorPortType).getRequestContext().put((BindingProvider.ENDPOINT_ADDRESS_PROPERTY), connectorPortTypeEndpoint);
        }
    }

    /**
     * Disconnect
     * 
     */
    @Disconnect
    public void disconnect() {
        //TODO: Add disconnection logic
		connectorPortType=null;
    }

    /**
     * Are we connected
     * 
     */
    @ValidateConnection
    public boolean isConnected() {
        //TODO: Add some logic
		return  connectorPortType!=null ;
    }

    /**
     * Connection Id
     * 
     */
    @ConnectionIdentifier
    public String connectionId() {
        //TODO: put real code to generate it.
		return "001";
    }

}
