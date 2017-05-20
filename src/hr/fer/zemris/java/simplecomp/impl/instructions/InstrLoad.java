package hr.fer.zemris.java.simplecomp.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.simplecomp.RegisterUtil;
import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;

/**
 * Naredba load uzima sadržaj memorijske lokacije (dobit će to kao broj u drugom argumentu) i pohranjuje taj sadržaj u
 * registar rX, gdje je X index registra (index će dobiti kao broj u prvom argumentu). 
 * Instrukcija ne dozvoljava indirektno adresiranje.
 * Instrukcija: load rX, memorijskaAdresa
 * @author Mislav Gillinger
 * @version 1.0
 */

public class InstrLoad implements Instruction{

	/**
	 * Registar u koji će se spremiti vrijednost sa dane memorijske lokacije.
	 */ 
	private int indexRegistra;
	/**
	 * Vrijednost s ove memorijske lokacije spremiti će se u dani registar.
	 */
	private int memorijskaLokacija;
	
	/**
	 * Stvara novu naredbu {@link InstrLoad}.
	 * @param arguments Prvi argument: Registar u koji će se spremiti vrijednost sa dane memorijske lokacije.
	 * 					<br>Drugi argument: Vrijednost s ove memorijske lokacije spremiti će se u dani registar.
	 */
	public InstrLoad(List<InstructionArgument> arguments){
		
		if(arguments.size() != 2){
			throw new IllegalArgumentException("Očekivani broj argumenata je 2!");
		}
		
		if(!arguments.get(0).isRegister() || RegisterUtil.isIndirect((Integer)arguments.get(0).getValue())) {
			throw new IllegalArgumentException("Prvi argument mora biti registar s direktnim adresiranjem!");
		}
		
		if(!arguments.get(1).isNumber()){
			throw new IllegalArgumentException("Drugi argument mora biti broj koji predstavlja memorijsku lokaciju!");
		}

		indexRegistra = RegisterUtil.getRegisterIndex((Integer)arguments.get(0).getValue());
		memorijskaLokacija = (Integer)arguments.get(1).getValue();
		
	}
	
	@Override
	public boolean execute(Computer computer) {
		Object vrijednostMemorijskeLokacije = computer.getMemory().getLocation(memorijskaLokacija);
		computer.getRegisters().setRegisterValue(indexRegistra, vrijednostMemorijskeLokacije);
		
		return false;
	}

}
