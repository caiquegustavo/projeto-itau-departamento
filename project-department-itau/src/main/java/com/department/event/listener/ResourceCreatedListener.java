package com.department.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.department.event.ResourceCreatedEvent;

@Component
public class ResourceCreatedListener implements ApplicationListener<ResourceCreatedEvent>{
	
	@Override
	public void onApplicationEvent(ResourceCreatedEvent resourceCreatedEvent) {
		
		HttpServletResponse httpServletResponse = resourceCreatedEvent.getHttpServletResponse();
		Long code = resourceCreatedEvent.getCode();
		
		addHeaderLocation(httpServletResponse, code);
	}

	private void addHeaderLocation(HttpServletResponse httpServletResponse, Long code) {
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}")
				.buildAndExpand(code).toUri();
			
		httpServletResponse.setHeader("Location", uri.toASCIIString());
	}

}
