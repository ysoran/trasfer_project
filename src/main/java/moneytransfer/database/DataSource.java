package moneytransfer.database;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.PoolableConnection;

public class DataSource {

    private static volatile DataSource datasource;
    private BasicDataSource ds;
    static final String JDBC_DRIVER = "org.h2.Driver"; 
    private DataSource() throws IOException, SQLException, PropertyVetoException {
        ds = new BasicDataSource();
        ds.setDriverClassName(JDBC_DRIVER);
        ds.setUsername("sa");
        ds.setPassword("");
        ds.setUrl("jdbc:h2:~/test;DB_CLOSE_DELAY=-1;SELECT_FOR_UPDATE_MVCC=FALSE;MV_STORE=FALSE;MVCC=FALSE");       
        ds.setMinIdle(5);
        ds.setMaxActive(10);
        ds.setMaxIdle(20);
        ds.setMaxOpenPreparedStatements(180);
        ds.setDefaultAutoCommit(false);
        ds.setDefaultTransactionIsolation(PoolableConnection.TRANSACTION_READ_COMMITTED);
    }

    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            synchronized (DataSource.class) {
                if (datasource == null) {
                    datasource = new DataSource();
                }
            }
            datasource = new DataSource();
        }
        return datasource;
    }

    public Connection getConnection() throws SQLException {
        return this.ds.getConnection();
    }
    
    public static void closeConnection(Connection connection){
    	try{
       	 if(connection!=null){
       		connection.close();
       	 }
        }catch(Exception e){
       	 
        }
    }

}
