package com.example.backend.dbmigration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@Getter
public class DbMigrationProps {

    @Value("${recreate.migration.sql-to-json:true}")
    private Boolean recreateJsonMigrationInitFile;

    @Value("classpath:db/migration/V1.1__tarot_initial.sql")
    private Resource initDbSqlFile;

    @Value("classpath:mongockmigraion/init_tarot_cards.json")
    private Resource initDbJsonFile;
}
