package org.ngel.station.service.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author vgarg
 */
@Configuration
public class CORSConfig extends WebMvcConfigurerAdapter {

    @Value("${cors.allowed.origins}")
    private String allowedOriginsStr;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] allowedOrigins = getAllowedOrigins();

        CorsRegistration registration = registry.addMapping("/**");
        if (allowedOrigins == null) {
            registration.allowedOrigins("*");
        } else {
            registration.allowedOrigins(allowedOrigins);
        }
        registration.allowedMethods("POST", "OPTIONS", "GET", "DELETE")
                .allowedHeaders("x-requested-with", "authorization", "content-type", "Location").maxAge(3600);
    }

    private String[] getAllowedOrigins() {
        String[] allowedOrigins = null;
        if (StringUtils.isNotEmpty(allowedOriginsStr)) {
            allowedOrigins = StringUtils.split(allowedOriginsStr, ',');
        }
        return allowedOrigins;
    }
}
