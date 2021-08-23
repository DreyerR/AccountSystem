package za.ac.nwu.as.web.sb.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "za.ac.nwu.as.web.sb.controller",
        "za.ac.nwu.as.web.sb.exception"
})
public class WebConfig {
}
