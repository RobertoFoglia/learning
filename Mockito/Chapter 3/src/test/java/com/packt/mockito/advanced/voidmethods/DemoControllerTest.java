package com.packt.mockito.advanced.voidmethods;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.*;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class DemoControllerTest {
	DemoController controller;
	
	@Mock HttpServletRequest request;
	@Mock HttpServletResponse response;
	@Mock RequestDispatcher dispatcher;
	@Mock LoginController loginController;
	@Mock MessageRepository repository;
	@Mock ErrorHandler errorHandler;
	@Mock Service service;
	
	@Before
    public void beforeEveryTest(){
    	MockitoAnnotations.initMocks(this);
    	controller = new DemoController(loginController,errorHandler, repository);
    }
	
	@Test
	public void when_subsystem_throws_any_exception_Then_finds_error_message_and_routes_to_error_page_() throws Exception {
		doThrow(new IllegalStateException("LDAP error")).when(loginController).process(request, response);
		doAnswer(new Answer<Object>() {

			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Error err = (Error)invocation.getArguments()[0];
				err.setErrorCode("123");
				return err;
			}
		}).when(errorHandler).mapTo(isA(Error.class));
		when(request.getServletPath()).thenReturn("/logon.do");
		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
		
		controller.doGet(request, response);
		ArgumentCaptor<String[]> captor = ArgumentCaptor.forClass(String[].class);
		verify(this.repository).lookUp(captor.capture());
		assertEquals("123", captor.getValue());
		
		verify(request).getRequestDispatcher(eq("error.jsp"));
		verify(dispatcher).forward(request, response);
	}
	
	@Test
	public void when_capturing_variable_args() throws Exception {
		String[] errorCodes = {"a","b","c"};
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		repository.lookUp(errorCodes);
		verify(repository).lookUp(captor.capture(),captor.capture(),captor.capture());
		assertTrue(captor.getAllValues().containsAll(Arrays.asList(errorCodes)));
	}
	
	@Test
	public void when_subsystem_throws_any_exception_Then_routes_to_error_page_() throws Exception {
		doThrow(new IllegalStateException("LDAP error")).when(loginController).process(request, response);
		
		when(request.getServletPath()).thenReturn("/logon.do");
		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
		
		controller.doGet(request, response);
		
		verify(request).getRequestDispatcher(eq("error.jsp"));
		verify(dispatcher).forward(request, response);
	}
	
	@Test
	public void when_do_return_is_not_safe() throws Exception {
		when(request.getServletPath()).thenReturn("/logon.do");
		assertEquals("/logon.do", request.getServletPath());
		
		doReturn(1.111d).when(request.getServletPath());
		request.getServletPath();
	}
	
	@Test
	public void when_captures_collections() throws Exception {
		Class<List<String>> listClass = (Class<List<String>>)(Class)List.class;
		ArgumentCaptor<List<String>> captor	= ArgumentCaptor.forClass(listClass);
		service.call(Arrays.asList("a","b"));
		verify(service).call(captor.capture());
		assertTrue(captor.getValue().containsAll(Arrays.asList("a","b")));
	}
	
	@Test
	public void when_inorder() throws Exception {
		request.getServletPath();
		service.call(Arrays.asList("a","b"));
		
		InOrder inOrder=inOrder(request,service);
		inOrder.verify(request).getServletPath();
		inOrder.verify(service).call(anyList());

	}
	
	@Test
	public void when_spying_real_objects() throws Exception {
		Error error = new Error();
		error.setErrorCode("Q123");
		Error spyError = spy(error);
		//call real method from  spy
        assertEquals("Q123", spyError.getErrorCode());
        
        //Changing value using spy
        spyError.setErrorCode(null);
            
        //verify spy has the changed value
        assertEquals(null, spyError.getErrorCode());
        
        //Stubbing method
        when(spyError.getErrorCode()).thenReturn("E456");
        
        //Changing value using spy
        spyError.setErrorCode(null);
        
        //Stubbed method value E456 is returned NOT NULL
        assertNotEquals(null, spyError.getErrorCode());
        
        //Stubbed method value E456
        assertEquals("E456", spyError.getErrorCode());

	}
	
	@Test
	public void when_doReturn_fails() throws Exception {
		List<String> list = new ArrayList<String>();
		List<String> spy = spy(list);
		
		//doReturn fixed the issue
		doReturn("now reachable").when(spy).get(0);
		assertEquals("now reachable", spy.get(0));
	}
	
	
}

interface Service{
	void call(List<String> args);
}
