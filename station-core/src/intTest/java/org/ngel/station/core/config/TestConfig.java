package org.ngel.station.core.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author vgarg
 */
@EnableAutoConfiguration
@EnableJpaRepositories("org.ngel.station.core.persistence")
@EntityScan("org.ngel.station.core.domain.model")
@ComponentScan(basePackages = {"org.ngel.station.core"})
public class TestConfig {
}
