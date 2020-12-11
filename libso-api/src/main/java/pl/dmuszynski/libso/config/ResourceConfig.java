package pl.dmuszynski.libso.config;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableScheduling
@EnableJpaAuditing
public class ResourceConfig {

}
