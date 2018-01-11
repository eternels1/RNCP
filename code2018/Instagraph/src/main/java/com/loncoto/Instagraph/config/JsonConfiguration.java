package com.loncoto.Instagraph.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class JsonConfiguration {
	
	@Autowired(required=true)
	public void configJackson(ObjectMapper jackson2ObjectMapper) {
		//config de la serialisation en json par jackson des dates
		jackson2ObjectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}

}
