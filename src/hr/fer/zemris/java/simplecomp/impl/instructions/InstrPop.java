package hr.fer.zemris.java.simplecomp.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.simplecomp.RegisterUtil;
import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;
import hr.fer.zemris.java.simplecomp.models.Registers;

/**
 * S vrha stoga skida podatak koji pohranjuje u registar rx. Kazaklju vrha stoga uvećava za 1.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class InstrPop implements Instruction{

	/**
	 * Index registra u kojeg se dohvaća vrijednost zadnja stavljena na stog.
	 */
	private int indexRegistra;
	
	/**
	 * Stvara naredbu {@link InstrPop}.
	 * @param arguments Index registra u kojeg se dohvaća vrijednost zadnja stavljena na stog.
	 */
	public InstrPop(List<InstructionArgument> arguments){
		if(arguments.size() != 1){
			throw new IllegalArgumentException("Očekivani broj argumenata je 1!");
		}
		
		if(!arguments.get(0).isRegister() || RegisterUtil.isIndirect((Integer)arguments.get(0).getValue())) {
				throw new IllegalArgumentException("Nepodržan tip za dani argumnet!");
		}
		
		indexRegistra = RegisterUtil.getRegisterIndex((Integer)arguments.get(0).getValue());
	}
	
	@Override
	public boolean execute(Computer computer) {
		int vrhStoga = (int)computer.getRegisters().getRegisterValue(Registers.STACK_REGISTER_INDEX);
		Object vrijednost = computer.getMemory().getLocation(vrhStoga + 1);
		
		computer.getRegisters().setRegisterValue(indexRegistra, vrijednost);
		
		computer.getRegisters().setRegisterValue(Registers.STACK_REGISTER_INDEX, vrhStoga + 1);

		return false;
	}

}
