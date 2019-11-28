package com.ryanair.passenger.config;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.leader.DefaultCandidate;
import org.springframework.integration.zookeeper.config.CuratorFrameworkFactoryBean;
import org.springframework.integration.zookeeper.leader.LeaderInitiator;

@Configuration
public class ZookeeperConfig {

    @Value("${zookeeper.address}")
    private String zookeeperAddress;

    @Bean
    CuratorFrameworkFactoryBean curatorFrameworkFactoryBean() {
        return new CuratorFrameworkFactoryBean(zookeeperAddress);
    }

    @Bean
    LeaderInitiator leaderInitiator(final CuratorFramework curatorFramework) {
        return new LeaderInitiator(curatorFramework, new DefaultCandidate());
    }
}
