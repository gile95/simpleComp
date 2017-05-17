package hr.fer.zemris.java.simplecomp.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;

/**
 * Naredba koja zaustavlja rad računala.
 * Instrukcija: halt
 * @author Mislav Gillinger
 * @version 1.0
 */
public class InstrHalt implements Instruction{
	
	/**
	 * Stvara novu naredbu {@link InstrHalt}.
	 * @param arguments Ne očekuju se argumenti.
	 */
	public InstrHalt(List<InstructionArgument> arguments){
		if(arguments.size() != 0){
			throw new IllegalArgumentException("Očekivani broj argumenata je 0!");
		}
	}
	
	@Override
	public boolean execute(Computer computer) {
		return true;
	}

	
}
