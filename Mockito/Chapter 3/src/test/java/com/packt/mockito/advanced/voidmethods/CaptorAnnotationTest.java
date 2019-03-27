package com.packt.mockito.advanced.voidmethods;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CaptorAnnotationTest {

	@Captor
	ArgumentCaptor<List<String>> captor;
	@Mock Service service;
	
	@Test
	public void when_captor_annotation_is_used() throws Exception {
		service.call(Arrays.asList("a","b"));
		verify(service).call(captor.capture());
		assertTrue(captor.getValue().containsAll(Arrays.asList("a","b")));
	}
}

