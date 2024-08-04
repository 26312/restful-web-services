package com.rest.webservices.restful_web_services.filtering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public NormalBean filtering() {
		return new NormalBean("Value-1","Value-2","Value-3");
	}
	
	
	@GetMapping("/filtering-list")
	public List<NormalBean> filteringList() {
		return Arrays.asList(new NormalBean("Value-1","Value-2","Value-3"),
							new NormalBean("Value-4","Value-5","Value-6"));
	}
	
	
	
	@GetMapping("/filtering-mappingjacksonvalue")
	public MappingJacksonValue filteringMappingJacksonValue() {
		NormalBean normalBean = new NormalBean("Value-1","Value-2","Value-3");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(normalBean);
		
		
		// Instance of MappingJacksonValue will now allow to define Filters
		
		/**
		 * After defining code for filtering corresponding attribute, the class which contains these attributes
		 * must also be annotated with @JsonFilter and must contain parameter "SomeBeanFilter" which is defined within SimpleFilterProvider
		 * finally the annotation should appear as @JsonFilter("SomeBeanFilter") 
		 */
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter); 
		mappingJacksonValue.setFilters(filters);
				
		return mappingJacksonValue;
	}
	
	@GetMapping("/filtering-list-mappingjacksonvalue")  // field2, field3
	public MappingJacksonValue filteringListMappingJacksonValue() {
		List<NormalBean> normalBean =  Arrays.asList(new NormalBean("Value-1","Value-2","Value-3"),
													new NormalBean("Value-4","Value-5","Value-6"));
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(normalBean);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
		mappingJacksonValue.setFilters(filters );
		
		return mappingJacksonValue;		
		
		
	}

}
