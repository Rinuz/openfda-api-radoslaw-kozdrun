package pl.kozdrun.openfdaapi.config;

import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

@Configuration
public class SpringITConfig {

    @Bean
    public MongodConfig embeddedMongoConfiguration() throws UnknownHostException {
        return MongodConfig.builder()
                .version(Version.Main.V6_0)
                .net(new Net(2572, Network.localhostIsIPv6()))
                .build();
    }
}