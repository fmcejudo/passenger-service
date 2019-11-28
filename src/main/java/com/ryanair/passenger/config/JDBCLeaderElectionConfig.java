package com.ryanair.passenger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.jdbc.lock.DefaultLockRepository;
import org.springframework.integration.jdbc.lock.JdbcLockRegistry;
import org.springframework.integration.jdbc.lock.LockRepository;
import org.springframework.integration.support.leader.LockRegistryLeaderInitiator;
import org.springframework.integration.support.locks.LockRegistry;

import javax.sql.DataSource;

@Configuration
public class JDBCLeaderElectionConfig {

    @Bean
    LockRepository lockRepository(final DataSource dataSource) {
        return new DefaultLockRepository(dataSource);
    }

    @Bean
    LockRegistry lockRegistry(final LockRepository lockRepository) {
        return new JdbcLockRegistry(lockRepository);
    }

    @Bean
    LockRegistryLeaderInitiator lockRegistryLeaderInitiator(final LockRegistry lockRegistry) {
        LockRegistryLeaderInitiator lockRegistryLeaderInitiator = new LockRegistryLeaderInitiator(lockRegistry);
        lockRegistryLeaderInitiator.start();
        return lockRegistryLeaderInitiator;
    }
}
