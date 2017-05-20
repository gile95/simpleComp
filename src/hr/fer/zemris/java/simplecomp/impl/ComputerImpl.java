package hr.fer.zemris.java.simplecomp.impl;

import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Memory;
import hr.fer.zemris.java.simplecomp.models.Registers;

/**
 * Predstavlja računalo, koje ima memoriju i registre.
 * Implementira {@link Computer}.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class ComputerImpl implements Computer{

	/**
	 * Objekt tipa razreda koji implementira sučelje {@link Registers}.
	 */
	private RegistersImpl registers;
	/**
	 * Objekt tipa razreda koji implementira sučelje {@link Memory}.
	 */
	private MemoryImpl memory;
	
	/**
	 * Stvara računalo s proizvoljnim brojem registara i memorijskih lokacija.
	 * @param numberOfMemoryLocations Broj memorijskih lokacija.
	 * @param numberOfRegisters Broj registara.
	 */
	public ComputerImpl(int numberOfMemoryLocations, int numberOfRegisters) {
		memory = new MemoryImpl(numberOfMemoryLocations);
		registers = new RegistersImpl(numberOfRegisters);
	}

	@Override
	public Registers getRegisters() {
		return registers;
	}

	@Override
	public Memory getMemory() {
		return memory;
	}

}
