package ua.kharkiv.storage.manager;

/**
 * This exception is thrown if process of choosing a datasource is failed.
 *
 * <p> For example: the data from client side contains wrong data about country and
 * {@link DataSourceManager} cannot choose the target datasource.
 */
public class DataSourceManagerException extends RuntimeException {

    private static final long serialVersionUID = 0L;

    /**
     * Initializes an exception instance.
     *
     * @param details
     *         additional details to describe the problem properly
     */
    public DataSourceManagerException(String details) {
        super(details);
    }

}
