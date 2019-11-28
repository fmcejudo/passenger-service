package com.ryanair.passenger.config;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import com.ryanair.passenger.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hazelcast.repository.config.EnableHazelcastRepositories;

@Configuration
@EnableHazelcastRepositories(basePackageClasses = PassengerRepository.class)
public class HazelcastConfig {

    @Value("${hazelcast.instance-name}")
    private  String name;

    @Value("${hazelcast.secret}")
    private String secret;

    @Bean
    ClientConfig clientConfig() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setGroupConfig(new GroupConfig(name, secret));
        clientConfig.getNetworkConfig().addAddress("0.0.0.0");
        return clientConfig;
    }

    @Bean
    HazelcastInstance hazelcastInstance(final ClientConfig clientConfig) {
        return new HazelcastClientFactory(clientConfig).getHazelcastInstance();
    }
}
