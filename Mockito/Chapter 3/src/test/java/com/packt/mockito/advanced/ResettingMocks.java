package com.packt.mockito.advanced;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.mockito.Mockito;

public class ResettingMocks {
	@Test
	public void when_resetting_mocks() throws Exception {
		Bar bar= Mockito.mock(Bar.class);
		when(bar.getName()).thenReturn("***");
		assertNotNull(bar.getName());
		reset(bar);
		//Bar is reset, the getName() stub is cleared
		assertNull(bar.getName());		
	}

	class Bar {
		String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	class Foo {
		Bar bar;

		public Bar getBar() {
			return bar;
		}

		public void setBar(Bar bar) {
			this.bar = bar;
		}

	}
}
