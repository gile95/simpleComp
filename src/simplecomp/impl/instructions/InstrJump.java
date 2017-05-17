package hr.fer.zemris.java.simplecomp.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;

/**
 * Naredba koja postavla programsko brojilo na danu lokaciju.
 * Instrukcija: jump lokacija
 * Opis: pc <- lokacija
 * @author Mislav Gillinger
 * @version 1.0
 */
public class InstrJump implements Instruction{

	/**
	 * Na ovu lokaciju će se postaviti vrijednost pogramskog brojila.
	 */
	int lokacija;
	
	/**
	 * Stvara novu naredbu {@link InstrJump}.
	 * @param arguments Lokacija na koju će se postaviti vrijednost programskog brojila.
	 */
	public InstrJump(List<InstructionArgument> arguments){
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
		computer.getRegisters().setProgramCounter(lokacija);

		return false;
	}

}
