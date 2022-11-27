package com.baiu.hrrch;

import com.baiu.hrrch.doc.Doc;
import com.baiu.hrrch.doc.DocRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import com.baiu.hrrch.facility.FacilityRepository;

@Component
@EnableMongoRepositories(basePackageClasses = {Doc.class, DocRepository.class, FacilityRepository.class})
@EnableJpaRepositories
public class DataLoader implements ApplicationRunner {

    public void run(ApplicationArguments args) {
        System.out.println("Done");
    }
}
