package hr.fer.zemris.java.simplecomp.impl;

import hr.fer.zemris.java.simplecomp.models.Memory;

/**
 * Predstavlja memoriju računala. Za razliku
 * od klasične memorije računala koja je bajtno orijentirana,
 * ova memorija na svakoj lokaciji može pohraniti proizvoljno
 * veliki (jedan) objekt tipa String i Integer.
 * Prazna memorija na svim lokacijama ima vrijednost {@code null}.
 * Implementira sučelje {@link Memory}.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class MemoryImpl implements Memory{

	/**
	 * Polje koje predstavlja memoriju.
	 */
	private Object[] memory;
	
	/**
	 * Stvara objekt tipa {@link MemoryImpl}.
	 * @param size Željena veličina memorije.
	 */
	public MemoryImpl(int size) {
		if(size < 0){
			throw new IllegalArgumentException("Veličina memorije ne smije biti negativna!");
		}
		
		memory = new Object[size];
	}

	@Override
	public void setLocation(int location, Object value) {
		if(location < 0 || location >= memory.length){
			throw new IndexOutOfBoundsException("Lokacija u memoriji ne smije biti manja od 0 ili veća od adrese"
					+ " posljednje postojeće memorijske lokacije!");
		}
		
		memory[location] = value;
	}

	@Override
	public Object getLocation(int location) {
		if(location < 0 || location >= memory.length){
			throw new IndexOutOfBoundsException("Lokacija u memoriji ne smije biti manja od 0 ili veća od adrese"
					+ " posljednje postojeće memorijske lokacije");
		}
		
		return memory[location];
	}

}
