package hr.fer.zemris.java.simplecomp.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.simplecomp.RegisterUtil;
import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;

/**
 * Naredba koja na osnovu usporedbe dva argumenta postavlja zastavicu. Vrijednost zastavice
 * postavlja se na true ukoliko su argumenti jednaki, inače na false.
 * Ne podržava indirektno adresiranje.
 * Instrukcija: testEquals rx, ry
 * @author Mislav Gillinger
 * @version 1.0
 */
public class InstrTestEquals implements Instruction{

	/**
	 * Index registra koji predstavlja prvi argument.
	 */
	private int indexRegistra1;
	/**
	 * Index registra koji predstavlja drugi argument.
	 */
	private int indexRegistra2;
	
	/**
	 * Stvara novu naredbu {@link InstrTestEquals}.
	 * @param arguments Dva registra čije se vrijednosti uspoređuju.
	 */
	public InstrTestEquals(List<InstructionArgument> arguments){
		
		if(arguments.size() != 2){
			throw new IllegalArgumentException("Očekivani broj argumanata je 2!");
		}
		
		for(int i = 0; i < 2; i++) {
			if(!arguments.get(i).isRegister() || RegisterUtil.isIndirect((Integer)arguments.get(i).getValue())) {
				throw new IllegalArgumentException("Nepodržan tip za argumnet broj "+i+"!");
			}
		}
		
		indexRegistra1 = RegisterUtil.getRegisterIndex((int)arguments.get(0).getValue());
		indexRegistra2 = RegisterUtil.getRegisterIndex((int)arguments.get(1).getValue());
	}
	
	@Override
	public boolean execute(Computer computer) {
		Object vrijednostRegistra1 = computer.getRegisters().getRegisterValue(indexRegistra1);
		Object vrijednostRegistra2 = computer.getRegisters().getRegisterValue(indexRegistra2);
		
		if(vrijednostRegistra1.equals(vrijednostRegistra2)){
			computer.getRegisters().setFlag(true);
		}
		else{
			computer.getRegisters().setFlag(false);
		}
		
		return false;
	}

}
