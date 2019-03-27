package com.packt.mockito.advanced.voidmethods;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.packt.mockito.advanced.voidmethods.DemoController;
import com.packt.mockito.advanced.voidmethods.ErrorHandlerImpl;
import com.packt.mockito.advanced.voidmethods.LoginController;
import com.packt.mockito.advanced.voidmethods.MessageRepository;

@RunWith(MockitoJUnitRunner.class)
public class InjectMocksAnnotationTest {

	@Mock LoginController loginController;
	@Mock MessageRepository repository;
	@Spy ErrorHandlerImpl errorHandler;

	@InjectMocks
	DemoController controller;

	@Mock HttpServletRequest request;
	@Mock HttpServletResponse response;
	@Mock RequestDispatcher dispatcher;

	@Test
	public void when_mocks_are_injected() throws Exception {
		when(request.getServletPath()).thenReturn("/");
		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
		controller.doGet(request, response);

		verify(request).getRequestDispatcher(eq("login.jsp"));
	}
}
