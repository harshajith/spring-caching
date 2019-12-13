package com.harsha.spring.cache;


import com.hazelcast.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelCastConfiguration {
    @Bean
    public Config hazelCastConfig(){
        Config config = new Config();
        config.getGroupConfig().setName("dev");
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(true);
        ManagementCenterConfig mcc = new ManagementCenterConfig().setUrl("http://localhost:32778/hazelcast-mancenter").setEnabled(true);
        config.setManagementCenterConfig(mcc);
        config.setInstanceName("hazelcast-instance")
                .addMapConfig(
                        new MapConfig()
                                .setName("configuration")
                                .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(-1));
        return config;
    }
}
