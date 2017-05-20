package hr.fer.zemris.java.simplecomp.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;
import hr.fer.zemris.java.simplecomp.models.Registers;

/**
 * Naredba koja služi za povratak iz potprograma. 
 * Vraća se iz potprograma pozvanog instrukcijom call. S vrha stoga skida 
 * adresu i postavlja je kao vrijednost registra PC (program counter).
 * @author Mislav Gillinger
 * @version 1.0
 */
public class InstrRet implements Instruction{

	/**
	 * Stvara novu naredbu {@link InstrRet}.
	 * @param arguments Ne očekuje argumente.
	 */
	public InstrRet(List<InstructionArgument> arguments){
		if(arguments.size() != 0){
			throw new IllegalArgumentException("Očekivani broj argumenata je 0!");
		}
	}
	
	@Override
	public boolean execute(Computer computer) {
		int vrhStoga = (int)computer.getRegisters().getRegisterValue(Registers.STACK_REGISTER_INDEX);
		int vrijednost = (int)computer.getMemory().getLocation(vrhStoga + 1);
		
		computer.getRegisters().setProgramCounter(vrijednost);
		
		computer.getRegisters().setRegisterValue(Registers.STACK_REGISTER_INDEX, vrhStoga + 1);
		
		return false;
	}

}
