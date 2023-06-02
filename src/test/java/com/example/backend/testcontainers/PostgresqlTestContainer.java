package com.example.backend.testcontainers;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

public class PostgresqlTestContainer extends PostgreSQLContainer<PostgresqlTestContainer> {
    public PostgresqlTestContainer() {
        super(DockerImageName.parse(
                    "postgres:12.2"
                )
                .asCompatibleSubstituteFor("postgres")
        );
        this.withUsername("postgres");
        this.withPassword("postgres");
        this.withCommand("postgres -c max_connections=200");
        this.withUrlParam("stringtype", "unspecified");
    }

}
