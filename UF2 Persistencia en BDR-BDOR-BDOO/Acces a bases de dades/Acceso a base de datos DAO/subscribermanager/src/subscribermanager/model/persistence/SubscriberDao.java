package subscribermanager.model.persistence;

import subscribermanager.model.Subscriber;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
//import java.util.Map;
//import java.util.HashMap;

public class SubscriberDao {
    /**
     * queries (can be defined using properties or map)
     */
    //private final Map<String, String> queries;
    private final Properties queries;
    /**
     * connect class to database
     */
    private final DbConnect dbConnect;
    
    public SubscriberDao(DbConnect dbConnect) throws ClassNotFoundException {
        this.dbConnect = dbConnect;
        //this.queries = new HashMap<>();
        this.queries = new Properties();
        initQueries();
    }
    
    /**
     * gets a query given its name
     * @param queryName the name of the query
     * @return query or empty string
     */
    private String getQuery(String queryName) {
        //return queries.getOrDefault(queryName, "");  //with map
        return queries.getProperty(queryName, "");  //with properties
    }
    
    /**
     * initializes all queries
     */
    private void initQueries() {
        //using properties
        queries.setProperty("findAll", "select * from subscribers");
        queries.setProperty("findById", "select * from subscribers where id = ?");
        queries.setProperty("findLikeName", "select * from subscribers where name LIKE ?");
        queries.setProperty("findByPhone", "select * from subscribers where phone = ?");
        queries.setProperty("findByAge", "select * from subscribers where age = ?");
        queries.setProperty("findById", "select * from subscribers where id = ?");
        queries.setProperty("insert", "insert into subscribers values (null, ?, ?, ?, ?)");
        queries.setProperty("update", "update subscribers set name = ?, address = ?, phone = ?, age = ? where id = ?");
        queries.setProperty("delete", "delete from subscribers where id = ?");
/*        //using map        
        queries.put("findAll", "select * from subscribers");
        queries.put("findById", "select * from subscribers where id = ?");
        queries.put("findLikeName", "select * from subscribers where name LIKE ?");
        queries.put("findByPhone", "select * from subscribers where phone = ?");
        queries.put("findByAge", "select * from subscribers where age = ?");
        queries.put("findById", "select * from subscribers where id = ?");
        queries.put("insert", "insert into subscribers values (null, ?, ?, ?, ?)");
        queries.put("update", "update subscribers set name = ?, address = ?, phone = ?, age = ? where id = ?");
        queries.put("delete", "delete from subscribers where id = ?");  
*/
    }
    
    /**
     * gets subscriber data from a resultset
     * @param resulSet the resultset to get data from
     * @return subscriber object
     * @throws SQLException in case of error
     */
    private Subscriber resultsetToSubscriber(ResultSet resulSet) throws SQLException {
        Subscriber subs = null;
        long id = resulSet.getLong("id");
        String name = resulSet.getString("name");
        String address = resulSet.getString("address");
        String phone = resulSet.getString("phone");
        int age = resulSet.getInt("age");
        subs = new Subscriber(id, name, address, phone, age);
        return subs;
    }
    /**
     * gets a subscriber given its id
     * @param id the id to search
     * @return the subscriber found or null if not found
     * @throws SQLException in case of SQL error
     */       
    public Subscriber findById(long id) throws SQLException {
        Subscriber subs=null;       
        try ( Connection conn=dbConnect.getConnection() ) {
            if ( conn != null ) {
                String query = getQuery("findById");
                PreparedStatement st = conn.prepareStatement(query);
                st.setLong(1, id);
                ResultSet rs = st.executeQuery();
                // executeUpdate se utiliza para hacer insterts y cosas asi...
                if ( rs.next() ) {
                    subs = resultsetToSubscriber(rs);
                }
            }
        } 
        return subs;
    }

    /**
     * gets all subscribers
     * @return a list with all subscribers found
     * @throws SQLException in case of SQL error 
     */
    public List<Subscriber> findAll() throws SQLException {
        List<Subscriber> subscribers = new ArrayList<>();
        try ( Connection conn=dbConnect.getConnection() ) {
            if ( conn != null ) {
                String query = getQuery("findAll");
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                while ( rs.next() ) {
                    Subscriber s = resultsetToSubscriber(rs);
                    subscribers.add(s);
                }
            }
        } 
        return subscribers;
    }
    
    /**
     * gets all subscribers with name containing given substring
     * @param name the substring to search
     * @return a list with all subscribers that match given cryteria
     * @throws SQLException in case of SQL error 
     */
    public List<Subscriber> findLikeName(String name) throws SQLException {
        List<Subscriber> subscribers = new ArrayList<>();
        try ( Connection conn=dbConnect.getConnection() ) {
            if ( conn != null ) {
                String query = getQuery("findLikeName");
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, "%"+name+"%");
                ResultSet rs = st.executeQuery();
                while ( rs.next() ) {
                    Subscriber s = resultsetToSubscriber(rs);
                    subscribers.add(s);
                }
            }
        } 
        return subscribers;
    }
    
    /**
     * gets all subscribers with given phone
     * @param phone the phone to search
     * @return a list with all subscribers that match given cryteria
     * @throws SQLException in case of SQL error 
     */
    public List<Subscriber> findByPhone(String phone) throws SQLException {
        List<Subscriber> subscribers = new ArrayList<>();
        try ( Connection conn=dbConnect.getConnection() ) {
            if ( conn != null ) {
                String query = getQuery("findByPhone");
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, phone);
                ResultSet rs = st.executeQuery();
                while ( rs.next() ) {
                    Subscriber s = resultsetToSubscriber(rs);
                    subscribers.add(s);
                }
            }
        } 
        return subscribers;
    }

    /**
     * gets all subscribers with given age 
     * @param age the age to search
     * @return a list with all subscribers that match given cryteria
     * @throws SQLException in case of SQL error 
     */
    public List<Subscriber> findByAge(int age) throws SQLException {
        List<Subscriber> subscribers = new ArrayList<>();
        try ( Connection conn=dbConnect.getConnection() ) {
            if ( conn != null ) {
                String query = getQuery("findByAge");
                PreparedStatement st = conn.prepareStatement(query);
                st.setInt(1, age);
                ResultSet rs = st.executeQuery();
                while ( rs.next() ) {
                    Subscriber s = resultsetToSubscriber(rs);
                    subscribers.add(s);
                }
            }
        } 
        return subscribers;
    }
    
    /**
     * inserts a new subscriber
     * @param entity the subscriber to insert
     * @return number of rows affected
     * @throws SQLException in case of SQL error 
     */
    public int insert (Subscriber entity) throws SQLException {
        int result = 0;
        try ( Connection conn=dbConnect.getConnection() ) {
            if ( conn != null ) {
                String query = getQuery("insert");
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, entity.getName());
                st.setString(2, entity.getAddress());
                st.setString(3, entity.getPhone());
                st.setInt(4, entity.getAge());
                result = st.executeUpdate();
            }
        } 
        return result;
    }

    /**
     * updates a subscriber
     * @param entity the subscriber to update
     * @return number of rows affected
     * @throws SQLException in case of SQL error 
     */    
    public int update (Subscriber entity) throws SQLException {
        int result = 0;
        try ( Connection conn=dbConnect.getConnection() ) {
            if ( conn != null ) {
                String query = getQuery("update");
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, entity.getName());
                st.setString(2, entity.getAddress());
                st.setString(3, entity.getPhone());
                st.setInt(4, entity.getAge());
                st.setLong(5, entity.getId());
                result = st.executeUpdate();
            }
        } 
        return result;
    }

    /**
     * deletes a subscriber
     * @param entity the subscriber to delete
     * @return number of rows affected
     * @throws SQLException in case of SQL error 
     */    
    public int delete (Subscriber entity) throws SQLException {
        int result = 0;
        try ( Connection conn=dbConnect.getConnection() ) {
            if ( conn != null ) {
                String query = getQuery("delete");
                PreparedStatement st = conn.prepareStatement(query);
                st.setLong(1, entity.getId());
                result = st.executeUpdate();
            }
        }
        return result;
    }
     
}//end of class
