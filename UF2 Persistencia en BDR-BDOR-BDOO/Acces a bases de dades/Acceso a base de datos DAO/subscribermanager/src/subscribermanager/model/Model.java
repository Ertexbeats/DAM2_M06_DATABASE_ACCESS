
package subscribermanager.model;

import java.io.IOException;
import java.sql.SQLException;
import subscribermanager.model.persistence.SubscriberDao;
import java.util.List;
import subscribermanager.model.persistence.DbConnect;
/**
 * Model to use data access layer to get access to data in database.
 * In this example, SQLException is passed to caller, who would be responsible of catching it and treat error.
 * Another solution would be catching it and deciding here what to do.
 * @author ProvenSoft
 */
public class Model {

    private final String DBCONNPROPS = "files/dbconn.properties";
    private final SubscriberDao subscriberDao;
    
    public Model() throws ClassNotFoundException, IOException {
        DbConnect dbConn = new DbConnect();
        dbConn.setPropsFile(DBCONNPROPS);
        this.subscriberDao = new SubscriberDao(dbConn);
    }
    
    public Subscriber searchSubscriberById(long id) throws SQLException {
        return subscriberDao.findById(id);
    }

    public List<Subscriber> searchAllSubscribers() throws SQLException {
        List<Subscriber> result;
        result = subscriberDao.findAll();
        return result;
    }
    
    public List<Subscriber> searchSubscribersLikeName(String name) throws SQLException {
        List<Subscriber> result;
        result = subscriberDao.findLikeName(name);
        return result;
    } 
    
    public List<Subscriber> searchSubscribersByPhone(String phone) throws SQLException {
        List<Subscriber> result;
        result = subscriberDao.findByPhone(phone);
        return result;
    }

    public List<Subscriber> searchSubscribersByAge( int age ) throws SQLException {
        List<Subscriber> result;
        result = subscriberDao.findByAge(age);
        return result;
    }
    
    public int addSubscriber(Subscriber s) throws SQLException {
        int result = subscriberDao.insert(s);
        return result;
    }
    
    public int updateSubscriber(Subscriber s) throws SQLException {
        int result = subscriberDao.update(s);
        return result;
    }
    
    public int deleteSubscriber(Subscriber s) throws SQLException {
        int result = subscriberDao.delete(s);
        return result;
    }
    
}//end of class
