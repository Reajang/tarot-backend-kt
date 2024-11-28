package com.example.backend.config

import com.example.backend.dbmigration.InitDB
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

@Component
class StartupApplicationListener(
    private val initDbComponent: InitDB
) : ApplicationListener<ContextRefreshedEvent> {


    @Value("\${recreate.migration.re-init:false}")
    val recreateJsonMigrationInitFile: Boolean? = null

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        if (recreateJsonMigrationInitFile == true) {
            initDbComponent.execution()
        }
    }
}