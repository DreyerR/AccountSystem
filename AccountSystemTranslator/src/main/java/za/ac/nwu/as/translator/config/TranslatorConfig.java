package za.ac.nwu.as.translator.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import za.ac.nwu.as.repo.config.RepositoryConfig;

@Import({RepositoryConfig.class})
@ComponentScan({
        "za.ac.nwu.as.translator"
})
@Configuration
public class TranslatorConfig {
}
