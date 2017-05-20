package hr.fer.zemris.java.simplecomp.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;
import hr.fer.zemris.java.simplecomp.models.Registers;

/**
 * Poziv potprograma. Trenutni sadržaj registra PC (program counter) pohranjuje 
 * na stog; potom u taj registar upisuje predanu adresu čime definira sljedeću 
 * instrukciju koja će biti izvedena.
 * Instrukcija: call adresa
 * @author Mislav Gillinger
 * @version 1.0
 */
public class InstrCall implements Instruction{

	/**
	 * Adresa koja se upisuje u programsko brojilo.
	 */
	private int adresa;

	/**
	 * Stvara novu naredbu {@link InstrCall}.
	 * @param arguments Adresa koja se upisuje u programsko brojilo.
	 */
	public InstrCall(List<InstructionArgument> arguments){
		if(arguments.size() != 1 || !arguments.get(0).isNumber()){
			throw new IllegalArgumentException("Očekivani broj argumenata je 1!");
		}
		
		adresa = (int)arguments.get(0).getValue();
	}
	
	@Override
	public boolean execute(Computer computer) {
		int indexPC = computer.getRegisters().getProgramCounter();
		int vrhStoga = (int)computer.getRegisters().getRegisterValue(Registers.STACK_REGISTER_INDEX);
		computer.getMemory().setLocation(vrhStoga, indexPC);
		computer.getRegisters().setRegisterValue(Registers.STACK_REGISTER_INDEX, vrhStoga - 1);
		
		computer.getRegisters().setProgramCounter(adresa);

		return false;
	}

}
