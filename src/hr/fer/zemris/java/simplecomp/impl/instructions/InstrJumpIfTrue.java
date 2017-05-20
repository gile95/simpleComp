package hr.fer.zemris.java.simplecomp.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;

/**
 * Naredba koja, ukoliko je vrijednost zastavice na true, postavlja vrijednost programskog brojila
 * na danu memorijsku lokaciju.
 * Instrukcija: jumpIfTrue lokacija
 * Opis: ako je flag=1 tada pc <- lokacija
 * @author Mislav Gillinger
 * @version 1.0
 */
public class InstrJumpIfTrue implements Instruction{

	/**
	 * Na ovu lokaciju će se postaviti vrijednost pogramskog brojila.
	 */
	int lokacija;
	
	/**
	 * Stvara novu naredbu {@link InstrJumpIfTrue}.
	 * @param arguments Lokacija na koju će se postaviti vrijednost programskog brojila.
	 */
	public InstrJumpIfTrue(List<InstructionArgument> arguments){
		if(arguments.size() != 1 || !arguments.get(0).isNumber()){
			throw new IllegalArgumentException("Potrebno je unijeti jedan broj!");
		}
		
		lokacija = (int)arguments.get(0).getValue();
		
		if(lokacija < 0) {
			throw new IllegalArgumentException("Argument mora biti nenegativni broj!");
		}
	}
	
	@Override
	public boolean execute(Computer computer) {
		if(computer.getRegisters().getFlag()){
			computer.getRegisters().setProgramCounter(lokacija);
		}

		return false;
	}

}
