package com.nttdata.interfacciafunzionale;

@FunctionalInterface

public interface Scontatore {

	public double applicaSconto(double prezzo, double percentuale);

}
