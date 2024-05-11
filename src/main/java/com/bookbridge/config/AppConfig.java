package com.bookbridge.config;

import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper mapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);

        Converter<String, String> skipEmptyStringConverter = new Converter<String, String>() { // Don't convert to lambda
            @Override
            public String convert(MappingContext<String, String> context) {
                if (context.getSource() != null && !context.getSource().isEmpty()) {
                    return context.getSource();
                }
                return context.getDestination();
            }
        };

        mapper.addConverter(skipEmptyStringConverter);
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return mapper;
    }
}
