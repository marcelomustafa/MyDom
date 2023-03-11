package br.com.mariapuri.mydom.config.system;

import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;



@Configuration
//@PropertySource("classpath:basemodel.properties")
public class DateConfig {
	public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	
	
	public static LocalDateTimeSerializer LOCAL_DATETIME_SERIALIZER = new LocalDateTimeSerializer(
			DateTimeFormatter.ofPattern(DATETIME_FORMAT));
	
	public static ZonedDateTimeSerializer ZONED_DATETIME_SERIALIZER = new ZonedDateTimeSerializer(
			DateTimeFormatter.ofPattern(DATETIME_FORMAT));

	private JavaTimeModule javaTimeModule() {
		JavaTimeModule module = new JavaTimeModule();
		module.addSerializer(LOCAL_DATETIME_SERIALIZER);
		module.addSerializer(ZONED_DATETIME_SERIALIZER);
		return module;
	}
	
	@Bean
	@Primary
	ObjectMapper objectMapper() {

		return new ObjectMapper()
				//.registerModule(new JSR310Module())
//				.findAndRegisterModules()
//				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//				.enable(SerializationFeature.INDENT_OUTPUT)
//				.setSerializationInclusion(JsonInclude.Include.NON_NULL)
//				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//				.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true)
				.registerModule(javaTimeModule())
				;
		
		
//		ObjectMapper mapper = JsonMapper.builder()
//		    .addModule(new JavaTimeModule())
//		    .build();		
		
	}
	
	
	
	
//	@Bean
//	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
//	    return builder -> builder.serializationInclusion(JsonInclude.Include.NON_NULL)
//	      .serializers(LOCAL_DATETIME_SERIALIZER,ZONED_DATETIME_SERIALIZER);
//	}	
//	
//	@Bean
//	public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
//	    return new Jackson2ObjectMapperBuilder().serializers(LOCAL_DATETIME_SERIALIZER,ZONED_DATETIME_SERIALIZER)
//	      .serializationInclusion(JsonInclude.Include.NON_NULL);
//	}
//	
//	@Bean
//	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//	    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder().serializers(LOCAL_DATETIME_SERIALIZER,ZONED_DATETIME_SERIALIZER)
//	      .serializationInclusion(JsonInclude.Include.NON_NULL);
//	    return new MappingJackson2HttpMessageConverter(builder.build());
//	}	
}