
package subscribermanager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import subscribermanager.model.Model;
import subscribermanager.model.Subscriber;

public class Main {

    public static void main(String[] args) {
        try {
            Model model = new Model();
            List<Subscriber> list;
            Subscriber subs;
            int result;
            //search by id that exists.
            System.out.println("====Search id="+3+"====");
            subs = model.searchSubscriberById(3);
            System.out.println(subs);
            //search by name that exists.
            System.out.println("====Search name="+"4"+"====");
            list = model.searchSubscribersLikeName("4");
            printList(list);
            //search by phone that exists.
            System.out.println("====Search phone="+"phone05"+"====");
            list = model.searchSubscribersByPhone("phone05");
            printList(list);
            //search by age that exists.
            System.out.println("====Search age="+"12"+"====");
            list = model.searchSubscribersByAge(12);
            printList(list);
            //add a new subscriber.
            System.out.println("====Add a new subscriber====");
            subs = new Subscriber(0, "name09", "address09", "phone09", 19);
            result = model.addSubscriber(subs);
            System.out.println("result="+result);
            //update a subscriber.
            System.out.println("====Update subscriber with id=4====");
            subs = model.searchSubscriberById(4);
            if (subs != null) {
                subs.setName("name44"); subs.setAddress("add44"); subs.setAge(44);
                result = model.updateSubscriber(subs);
                System.out.println("result="+result);
            } else {
                System.out.println("Not found");
            }
            //delete a subscriber.
            System.out.println("====Delete subscriber with id=9====");
            subs = new Subscriber(9);
            result = model.deleteSubscriber(subs);
            System.out.println("result="+result);
            //search all subscribers
            list = model.searchAllSubscribers();
            printList(list);
        } catch (FileNotFoundException  ex) {
            //database connexion properties configuration file not found
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException  ex) {
            //database connexion properties configuration file unable to be read
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            //database driver not found and not loaded.
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //SQL error generated in data access layer
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("SQLState: " + ex.getSQLState());
        }
    }
    
    public static void printList(List<Subscriber> list) {
        System.out.format("Listing %d elements\n", list.size());
        list.forEach(System.out::println);
    }

}
