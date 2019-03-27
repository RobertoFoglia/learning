package com.packt.mockito.advanced;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.packt.mockito.advanced.voidmethods.ErrorHandler;

@RunWith(MockitoJUnitRunner.class)
public class SpyAnnotationTest {

	@Spy
	ErrorHandler errorHandler;
	
	@Test
	public void when_spy_annotation_is_used() throws Exception {
		assertNotNull(errorHandler);
	}
}

