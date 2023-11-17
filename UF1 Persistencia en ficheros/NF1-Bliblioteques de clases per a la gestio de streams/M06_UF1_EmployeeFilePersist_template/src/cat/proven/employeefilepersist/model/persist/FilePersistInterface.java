package cat.proven.employeefilepersist.model.persist;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author ProvenSoft
 * @param <T> the base type
 *
 */
public interface FilePersistInterface<T> {

    /**
     * writes a list to a file
     *
     * @param filename the path to file to write list
     * @param data the list to write to file
     * @return number of elements written
     * @throws java.io.IOException
     */
    public int writeList(String filename, List<T> data) throws IOException;

    /**
     * reads a list from a file
     *
     * @param filename the path to file to read from
     * @return list with read data
     * @throws java.lang.ClassNotFoundException
     * @throws java.io.IOException
     */
    public List<T> readList(String filename) throws ClassNotFoundException, IOException;
}
