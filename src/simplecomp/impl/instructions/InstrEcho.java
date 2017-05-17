package hr.fer.zemris.java.simplecomp.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.simplecomp.RegisterUtil;
import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;

/**
 * Naredba koja ispisuje sadržaj određenog registra.
 * Podržava indirektno adresiranje.
 * Instrukcija: echo rx
 * @author Mislav Gillinger
 * @version 1.0
 */
public class InstrEcho implements Instruction{

	/**
	 * Deskriptor registra koji se želi ispisati.
	 */
	int deskriptorRegistra;
	
	/**
	 * Stvara novu naredbu {@link InstrEcho}.
	 * @param arguments Deskriptor registra čiji se sadržaj želi ispisati.
	 */
	public InstrEcho(List<InstructionArgument> arguments){
		
		if(arguments.size() != 1){
			throw new IllegalArgumentException("Očekivani broj argumenata je 1!");
		}
		
		if(!arguments.get(0).isRegister()) {
			throw new IllegalArgumentException("Argument mora biti registar!");
		}
		
		deskriptorRegistra = (int)arguments.get(0).getValue();
	}
	
	@Override
	public boolean execute(Computer computer) {
		int indexRegistra = RegisterUtil.getRegisterIndex(deskriptorRegistra);
		
		if(!RegisterUtil.isIndirect(deskriptorRegistra)){
			System.out.print(computer.getRegisters().getRegisterValue(indexRegistra));
		}
		else {
			int sadržajRegistra = ((int)computer.getRegisters().getRegisterValue(indexRegistra));
			sadržajRegistra += RegisterUtil.getRegisterOffset(deskriptorRegistra);
			System.out.print(computer.getMemory().getLocation(sadržajRegistra));
		}
		
		return false;
	}

}
