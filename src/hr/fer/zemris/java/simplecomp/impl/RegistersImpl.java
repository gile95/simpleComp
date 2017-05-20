package hr.fer.zemris.java.simplecomp.impl;

import hr.fer.zemris.java.simplecomp.models.Registers;

/**
 * Prestavlja registre računala.
 * Implementira sučelje {@link Registers}.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class RegistersImpl implements Registers{

	/**
	 * Polje koje predstavlja registre računala.
	 */
	private Object[] registers;
	/**
	 * Programsko brojilo. Pokazuje na memorijsku lokaciju gdje je izvođenje programa u tom trenutku.
	 */
	private int programCounter;
	/**
	 * Zastavica koja služi za označavanje true/false nakon usporedbe.
	 */
	private boolean flag;
	
	/**
	 * Stavara primjerak razreda {@linkRegistersImpl}.
	 * @param regsLen Željeni broj registara.
	 */
	public RegistersImpl(int regsLen) {
		registers = new Object[regsLen];
	}

	@Override
	public Object getRegisterValue(int index) {
		if(index < 0 || index >= registers.length){
			throw new IndexOutOfBoundsException("Traženi index ne smije biti manji od 0 ili veći ili jednak broju "
					+ "registara");
		}
		
		return registers[index];
	}

	@Override
	public void setRegisterValue(int index, Object value) {
		if(index < 0 || index >= registers.length){
			throw new IndexOutOfBoundsException("Traženi index ne smije biti manji od 0 ili veći ili jednak broju "
					+ "registara");
		}
		
		registers[index] = value;
	}

	@Override
	public int getProgramCounter() {
		return programCounter;
	}

	@Override
	public void setProgramCounter(int value) {
		programCounter = value;
	}

	@Override
	public void incrementProgramCounter() {
		programCounter++;
	}

	@Override
	public boolean getFlag() {
		return flag;
	}

	@Override
	public void setFlag(boolean value) {
		flag = value;
	}

}
