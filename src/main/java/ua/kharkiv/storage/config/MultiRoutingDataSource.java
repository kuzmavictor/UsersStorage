package ua.kharkiv.storage.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Allows dynamically set the actual DataSource that based on the current context.
 */
public class MultiRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataBaseContextHolder.getCurrentDb();
    }
}
