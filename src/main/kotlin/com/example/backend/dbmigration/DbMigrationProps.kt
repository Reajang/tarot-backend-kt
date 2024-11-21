package com.example.backend.dbmigration

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

@Component
class DbMigrationProps {

    @Value("\${recreate.migration.sql-to-json:true}")
    val recreateJsonMigrationInitFile: Boolean? = null

    @Value("classpath:db/migration/V1.1__tarot_initial.sql")
    val initDbSqlFile: Resource? = null

    @Value("classpath:mongockmigraion/init_tarot_cards.json")
    val initDbJsonFile: Resource? = null
}