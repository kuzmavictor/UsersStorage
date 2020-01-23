package ua.kharkiv.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import ua.kharkiv.storage.config.DataBaseContextHolder;
import ua.kharkiv.storage.config.DataBaseType;
import ua.kharkiv.storage.entity.Locality;
import ua.kharkiv.storage.entity.Region;
import ua.kharkiv.storage.entity.Role;
import ua.kharkiv.storage.repository.LocalityRepository;
import ua.kharkiv.storage.repository.RegionRepository;
import ua.kharkiv.storage.repository.RoleRepository;
import ua.kharkiv.storage.repository.UserRepository;

/**
 * An entry point to CatalogCompanies application.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

public class Application {

    @Autowired
    LocalityRepository localityRepository;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Autowired
    public void test() {
        DataBaseContextHolder.setCurrentDb(DataBaseType.CANADA);
        Locality locality = new Locality();
        locality.setName("locality1");

        localityRepository.save(locality);

       // DataBaseContextHolder.setCurrentDb(DataBaseType.USA);
       // localityRepository.save(locality);

        Region region = new Region();
        region.setName("RegionName1");

        //DataBaseContextHolder.setCurrentDb(DataBaseType.CANADA);
        regionRepository.save(region);

       // DataBaseContextHolder.setCurrentDb(DataBaseType.USA);
        //regionRepository.save(region);

        Role role = new Role();
        role.setName("ROLE_ADMIN");

        //DataBaseContextHolder.setCurrentDb(DataBaseType.CANADA);
        roleRepository.save(role);

        //DataBaseContextHolder.setCurrentDb(DataBaseType.USA);
       // roleRepository.save(role);




    }

//    @Autowired
//    public void test() {
//        System.out.println("CurrentDB====================> " + DataBaseContextHolder.getCurrentDb());
//        Company company = new Company();
//        company.setDescription("dewferferger");
//        company.setCreationDate(LocalDateTime.now());
//        company.setName("canada1");
//
//        DataBaseContextHolder.setCurrentDb(DataBaseType.CANADA);
//        System.out.println("CurrentDB====================> " + DataBaseContextHolder.getCurrentDb());
//        userRepository.save(company);
//
//        DataBaseContextHolder.setCurrentDb(DataBaseType.USA);
//        System.out.println("CurrentDB====================> " + DataBaseContextHolder.getCurrentDb());
//        Company company1 = new Company();
//        company1.setDescription("dewferferger");
//        company1.setCreationDate(LocalDateTime.now());
//        company1.setName("usa1");
//        userRepository.save(company1);
//
//        System.out.println("CurrentDB====================> " + DataBaseContextHolder.getCurrentDb());
//        DataBaseContextHolder.setCurrentDb(DataBaseType.CANADA);
//        Company company3 = new Company();
//        company3.setDescription("dewferferger");
//        company3.setCreationDate(LocalDateTime.now());
//        company3.setName("canada333333");
//
//        userRepository.save(company3);
//
//    }
}

