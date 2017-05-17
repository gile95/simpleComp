package hr.fer.zemris.java.simplecomp.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.simplecomp.RegisterUtil;
import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;
import hr.fer.zemris.java.simplecomp.models.Registers;

/**
 * Sadržaj registra rx sprema na memorijsku lokaciju koja je trenutni vrh stoga; 
 * potom adresu vrha stoga umanjuje za 1.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class InstrPush implements Instruction{

	/**
	 * Sadržaj ovog registra sprema se na stog.
	 */
	private int indexRegistra;
	
	/**
	 * Stvara novu naredbu {@link InstrPush}.
	 * @param arguments Sadržaj registra koji se sprema na stog.
	 */
	public InstrPush(List<InstructionArgument> arguments){
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
		Object vrijednostRegistra = computer.getRegisters().getRegisterValue(indexRegistra);
		int vrhStoga = (int)computer.getRegisters().getRegisterValue(Registers.STACK_REGISTER_INDEX);
		
		computer.getMemory().setLocation(vrhStoga, vrijednostRegistra);
		
		computer.getRegisters().setRegisterValue(Registers.STACK_REGISTER_INDEX, vrhStoga - 1);

		return false;
	}
	
}
