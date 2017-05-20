package hr.fer.zemris.java.simplecomp.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.simplecomp.RegisterUtil;
import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;

/**
 * Naredba koja zbraja sadržaje dva registra i stavlja ih u treći.
 * Ne podržava indirektno adresiranje.
 * Instrukcija: add rx, ry, rz
 * Opis: rx <- ry + rz
 * @author Mislav Gillinger
 * @version 1.0
 */
public class InstrAdd implements Instruction{

	/**
	 * Index registra u kojeg se sprema rezultat.
	 */
	int indexRegistra1;
	/**
	 * Index registra prvog operanda.
	 */
	int indexRegistra2;
	/**
	 * Index registra drugog operanda.
	 */
	int indexRegistra3;
	
	/**
	 * Stvara novu naredbu {@link InstrAdd} sa danim argumentima.
	 * @param arguments Argumenti, odnosno registri. Očekivana tri: registar za rezultat i dva registra s oprandima.
	 */
	public InstrAdd(List<InstructionArgument> arguments){
		if(arguments.size() != 3){
			throw new IllegalArgumentException("Očekivani broj argumenata je 3!");
		}
		
		for(int i = 0; i < 3; i++) {
			if(!arguments.get(i).isRegister() || RegisterUtil.isIndirect((Integer)arguments.get(i).getValue())) {
				throw new IllegalArgumentException("Nepodržan tip za argumnet broj "+i+"!");
			}
		}
		
		indexRegistra1 = RegisterUtil.getRegisterIndex((Integer)arguments.get(0).getValue());
		indexRegistra2 = RegisterUtil.getRegisterIndex((Integer)arguments.get(1).getValue());
		indexRegistra3 = RegisterUtil.getRegisterIndex((Integer)arguments.get(2).getValue());
	}
	
	@Override
	public boolean execute(Computer computer) {
		Object value1 = computer.getRegisters().getRegisterValue(indexRegistra2);
		Object value2 = computer.getRegisters().getRegisterValue(indexRegistra3);
		computer.getRegisters().setRegisterValue(
				indexRegistra1,
				(Integer)value1+(Integer)value2
				);
		
		return false;
	}

}
