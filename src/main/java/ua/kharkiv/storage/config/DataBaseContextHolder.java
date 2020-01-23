package ua.kharkiv.storage.config;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A context holder implementation. It is a container that stores the current
 * context as {@code ThreadLocal} reference.
 */
public class DataBaseContextHolder {

    private static final ThreadLocal<DataBaseType> context = new ThreadLocal<>();

    /**
     * Sets the current database.
     *
     * @param dataBaseType
     *         a database type
     * @see DataBaseType
     */
    public static void setCurrentDb(DataBaseType dataBaseType) {
        checkNotNull(dataBaseType);
        context.set(dataBaseType);
    }

    /**
     * Obtains the current database context from {@code ThreadLocal}.
     *
     * @return the a current database from {@code ThreadLocal}.
     */
    public static DataBaseType getCurrentDb() {
        return context.get();
    }

    /**
     * Clears the {@code ThreadLocal} container.
     */
    public static void clear() {
        context.remove();
    }
}
