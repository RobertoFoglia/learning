package com.packt.mockito.advanced;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SettingsTest {

	@Test
	public void when_default_settings() throws Exception {

		Foo fooWithReturnDefault = Mockito.mock(Foo.class, Mockito.RETURNS_DEFAULTS);
		// default null is returned
		assertNull(fooWithReturnDefault.getBar());

	}

	@Test
	public void when_changing_default_settings_to_return_smartNULLS(){

		Foo fooWithSmartNull = Mockito.mock(Foo.class,
				Mockito.RETURNS_SMART_NULLS);
		// a smart null is returned
		assertNotNull(fooWithSmartNull.getBar());
		System.out.println("fooWithSmartNull.getBar() =" + fooWithSmartNull.getBar());

	}

	@Test
	public void when_changing_default_settings_to_return_mocks() {

		Foo fooWithReturnsMocks = Mockito
				.mock(Foo.class, Mockito.RETURNS_MOCKS);

		// a mock is returned
		Bar mockBar = fooWithReturnsMocks.getBar();
		assertNotNull(mockBar);
		assertNotNull(mockBar.getTar());
		assertNotNull(mockBar.getTar().getName());
		System.out.println("fooWithReturnsMocks.getBar()=" + mockBar);
		System.out.println("fooWithReturnsMocks.getBar().getTar().getName()={" + mockBar.getTar().getName()+"}");

	}

	@Test
	public void when_returns_deep_stub() throws Exception {
		Foo fooWithDeepStub = Mockito.mock(Foo.class, Mockito.RETURNS_DEEP_STUBS);
		when(fooWithDeepStub.getBar().getTar().getName()).thenReturn("Deep Stub");
		// a deep stubbed mock is returned
		System.out.println("fooWithDeepStub.getBar().getTar().getName()="+ fooWithDeepStub.getBar().getTar().getName());
		assertNotNull(fooWithDeepStub.getBar().getTar().getName());
	}

}

class Tar {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

class Bar {
	Tar tar;

	public Tar getTar() {
		return tar;
	}

	public void setTar(Tar tar) {
		this.tar = tar;
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
