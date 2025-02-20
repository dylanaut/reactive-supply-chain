package group.msg.gbi.supplychain.admin;

import io.quarkus.liquibase.LiquibaseFactory;
import liquibase.Liquibase;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MigrationService {
    final LiquibaseFactory liquibaseFactory;


    public MigrationService(LiquibaseFactory liquibaseFactory) {
        this.liquibaseFactory = liquibaseFactory;
    }


    public void runMigrations() {
        try (Liquibase liquibase = liquibaseFactory.createLiquibase()) {
            liquibase.update(liquibaseFactory.createContexts(), liquibaseFactory.createLabels());
        } catch (Exception e) {
            throw new RuntimeException("Migration failed", e);
        }
    }
}
