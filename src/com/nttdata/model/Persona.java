package com.nttdata.model;

import com.nttdata.interfacciafunzionale.Parlante;

public class Persona implements Parlante {

	@Override
	public void parla() {
		System.out.println("bla bla bla\n");

	}

}
