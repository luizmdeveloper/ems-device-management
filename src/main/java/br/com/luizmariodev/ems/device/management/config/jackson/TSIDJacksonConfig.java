package br.com.luizmariodev.ems.device.management.config.jackson;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.hypersistence.tsid.TSID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TSIDJacksonConfig {

    @Bean
    public Module module() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(TSID.class, new TSIDToStringSerializer());
        simpleModule.addDeserializer(TSID.class, new StringToTSIDDeserializer());
        return simpleModule;
    }
}
