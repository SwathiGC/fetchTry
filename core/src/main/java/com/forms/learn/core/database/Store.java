package com.forms.learn.core.database;

import javax.sql.DataSource;
import org.apache.felix.scr.annotations.Activate;
import java.util.Dictionary;
import org.osgi.service.component.ComponentContext;
import com.adobe.granite.workflow.WorkflowException;
import java.io.InputStream;
import javax.jcr.Node;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.Writer;
import org.apache.commons.io.IOUtils;
import java.io.StringWriter;
import javax.jcr.Session;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import org.apache.felix.scr.annotations.Reference;
import com.day.commons.datasource.poolservice.DataSourcePool;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Component;
import com.adobe.granite.workflow.exec.WorkflowProcess;

@Component(immediate = true, label = "Testing....", metatype = true)
@Properties({ @Property(name = "DataSourceName", description = "Provide the data source name"), @Property(name = "TableName", description = "Provide the table name"), @Property(name = "FormName", description = "Column name for the Form Name"), @Property(name = "ColumnName", description = "Column name for Form Data"), @Property(label = "Workflow Label", name = "process.label", value = { "Insert AF Form Data" }, description = "Insert Af Form Data") })
@Service
public class Store implements WorkflowProcess
{
    private String tableName;
    private String columnName;
    private String dataSourceName;
    private String formName;
    
    @Reference
    DataSourcePool dataSource;
    
    public void execute(final WorkItem workItem, final WorkflowSession session, final MetaDataMap metaDataMap) throws WorkflowException {
        String AFName = null;
        if (metaDataMap.containsKey((Object)"PROCESS_ARGS")) {
            System.out.println("The value of column name is " + ((String)metaDataMap.get("PROCESS_ARGS", (Object)"string")).toString());
            final String proccesArgsVals = (String)metaDataMap.get("PROCESS_ARGS", (Object)"string");
            final String[] values = proccesArgsVals.split(",");
            AFName = values[0];
            System.out.println("***Adative Form Name is " + values[0]);
        }
        final String payloadPath = workItem.getWorkflowData().getPayload().toString();
        System.out.println("The cuurent assignee is " + workItem.getCurrentAssignee());
        System.out.println("The payload  in InsertFormData  is " + workItem.getWorkflowData().getPayload().toString());
        final Session jcrSession = (Session)session.adaptTo((Class)Session.class);
        final String dataFilePath = payloadPath + "/data.xml/jcr:content";
        System.out.println("The data file path is " + dataFilePath);
        PreparedStatement ps = null;
        Connection con = null;
        try {
            final Node xmlDataNode = jcrSession.getNode(dataFilePath);
            final InputStream xmlDataStream = xmlDataNode.getProperty("jcr:data").getBinary().getStream();
            System.out.println("XMLDATA STREAM: "+xmlDataStream.read());
            final StringWriter sw = new StringWriter();
            System.out.println("String writer: "+sw.toString());
            IOUtils.copy(xmlDataStream, (Writer)sw);
            con = this.getConnection();
            final String queryStmt = "insert into " + this.tableName + "(" + this.columnName + "," + this.formName + ") values(?,?)";
            System.out.println("The query I got was ..." + queryStmt);
            ps = con.prepareStatement(queryStmt);
            ps.setString(1, sw.toString());
            ps.setString(2, AFName);
            ps.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
            if (ps != null) {
                try {
                    ps.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                }
                catch (SQLException e3) {
                    e3.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                }
                catch (SQLException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }
    
    @Activate
    protected void activate(final ComponentContext ctx) {
        final Dictionary props = ctx.getProperties();
        this.tableName = (String)props.get("TableName");
        this.columnName = (String)props.get("ColumnName");
        this.formName = (String)props.get("FormName");
        this.dataSourceName =(String) props.get("DataSourceName");
        System.out.println("The table name  is" + this.tableName + " and the data source name is ...." + this.dataSourceName);
    }
    
    private Connection getConnection() {
        System.out.println("Trying to get connection");
        DataSource dbSource = null;
        Connection con = null;
        try {
            dbSource = (DataSource)this.dataSource.getDataSource(this.dataSourceName);
            System.out.println("got db source");
            con = dbSource.getConnection();
            System.out.println("returning connection");
            return con;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    protected void bindDataSource(final DataSourcePool dataSource) {
        this.dataSource = dataSource;
    }
    
    protected void unbindDataSource(final DataSourcePool dataSourcePool) {
        if (this.dataSource == dataSourcePool) {
            this.dataSource = null;
        }
    }
}
