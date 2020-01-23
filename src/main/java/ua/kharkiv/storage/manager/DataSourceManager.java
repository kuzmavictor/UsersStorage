package ua.kharkiv.storage.manager;

import org.springframework.stereotype.Service;
import ua.kharkiv.storage.config.DataBaseContextHolder;
import ua.kharkiv.storage.config.DataBaseType;
import ua.kharkiv.storage.dto.RegistrationCredentials;

import java.util.Objects;

/**
 * Allows chose the necessary datasource.
 */
@Service
public class DataSourceManager {

    /**
     * Sets the necessary datasource for continue of registration.
     *
     * @param credentials
     *         an instance of {@code RegistrationCredentials}
     * @throws DataSourceManagerException
     *         if the name of the country is wrong
     */
    public void setDataSourceForRegistration(RegistrationCredentials credentials)
            throws DataSourceManagerException {
        String country = credentials.getCountry();
        if (Objects.equals(country, "Canada")) {
            DataBaseContextHolder.setCurrentDb(DataBaseType.CANADA);
        } else if (Objects.equals(country, "USA")) {
            DataBaseContextHolder.setCurrentDb(DataBaseType.USA);
        } else {
            throw new DataSourceManagerException(
                    "Registration credentials contain wrong country name.");
        }
    }
}
