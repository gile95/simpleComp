package hr.fer.zemris.java.simplecomp.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.simplecomp.RegisterUtil;
import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;

/**
 * Naredba koja povećava sadržaj registra za zadani broj.
 * Ne podržava indirektno adresiranje.
 * Instrukcija: increment rx
 * Opis: rx <- rx + 1
 * @author Mislav Gillinger
 * @version 1.0
 */
public class InstrIncrement implements Instruction{

	/**
	 * Index registra kojemu će se vrijednost uvećati.
	 */
	int indexRegistra;
	
	/**
	 * Stvara novu naredbu {@link InstrIncrement}.
	 * @param arguments Index registra kojemu će se vrijednost uvećati.
	 */
	public InstrIncrement(List<InstructionArgument> arguments){
		if(arguments.size() != 1){
			throw new IllegalArgumentException("Očekivani broj argumenata je 1!");
		}
		
		if(!arguments.get(0).isRegister() || RegisterUtil.isIndirect((Integer)arguments.get(0).getValue())) {
			throw new IllegalArgumentException("Nepodržan tip za argument!");
		}
		
		indexRegistra = RegisterUtil.getRegisterIndex((Integer)arguments.get(0).getValue());
	}
	
	@Override
	public boolean execute(Computer computer) {
		Object value = computer.getRegisters().getRegisterValue(indexRegistra);
		computer.getRegisters().setRegisterValue(indexRegistra, (Integer)value + 1);
		
		return false;
	}

}
