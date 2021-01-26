package com.department.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class ResourceCreatedEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1L;
	private HttpServletResponse httpServletResponse;
	private Long code;
	
	public ResourceCreatedEvent(Object object, HttpServletResponse httpServletResponse, Long code) {
		super(object);
		this.httpServletResponse = httpServletResponse;
		this.code = code;
	}

}
