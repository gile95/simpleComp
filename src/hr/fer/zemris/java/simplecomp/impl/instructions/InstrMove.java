package hr.fer.zemris.java.simplecomp.impl.instructions;

import java.util.List;

import hr.fer.zemris.java.simplecomp.RegisterUtil;
import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;

/**
 * Premješta broj iz drugog argumenta u prvi argument.
 * Prvi argument može biti registar ili indirektna adresa; drugi argument
 * može biti registar, indirektna adresa ili broj.
 * Instrukcija: move rx, ry; move rx, broj; move [rx+o1],[ry+o2]; ...
 * @author Mislav Gillinger
 * @version 1.0
 */
public class InstrMove implements Instruction{

	/**
	 * Deskriptor prvog registra.
	 */
	private int deskriptorPrvogRegistra;
	/**
	 * Drugi argument.
	 */
	private InstructionArgument drugiArgument;
	
	/**
	 * Stvara novu naredbu {@link InstrMove}.
	 * @param arguments Očekivana dva: deskriptor prvog registra i drugi argument.
	 */
	public InstrMove(List<InstructionArgument> arguments){
		
		if(arguments.size() != 2){
			throw new IllegalArgumentException("Očekivani broj argumanata je 2!");
		}
		
		if(!arguments.get(0).isRegister()){
			throw new IllegalArgumentException("Prvi argument mora biti registar!");
		}
		
		if(!arguments.get(1).isRegister() && !arguments.get(1).isNumber()){
			throw new IllegalArgumentException("Drugi argument mora biti registar ili broj!");
		}		
		
		deskriptorPrvogRegistra = (int)arguments.get(0).getValue();
		drugiArgument = arguments.get(1);
	}
	
	@Override
	public boolean execute(Computer computer) {
		Object vrijednost = dohvatiVrijednost(computer);
		
		postaviVrijednost(computer, vrijednost);
		
		return false;
	}

	/**
	 * Postavlja danu vrijenost u prvi registar.
	 * @param computer Referenca na objekt tipa {@link Computer}.
	 * @param vrijednost Nova vrijednost prvog registra.
	 */
	private void postaviVrijednost(Computer computer, Object vrijednost) {
		int indexPrvogRegistra = RegisterUtil.getRegisterIndex(deskriptorPrvogRegistra);
		
		if(!RegisterUtil.isIndirect(deskriptorPrvogRegistra)){
			computer.getRegisters().setRegisterValue(indexPrvogRegistra, vrijednost);
		}
		else{
			int vrijednostPrvogRegistra = (int)computer.getRegisters().getRegisterValue(indexPrvogRegistra);
			int offsetPrvogRegistra = RegisterUtil.getRegisterOffset(deskriptorPrvogRegistra);
			int lokacija = vrijednostPrvogRegistra + offsetPrvogRegistra;
			computer.getMemory().setLocation(lokacija, vrijednost);
		}
	}

	/**
	 * Dohvaća vrijesnot drugog argumenta i stavlja ga u varijablu tipa {@link Object}.
	 * @param computer Referenca na objekt tipa {@link Computer}.
	 * @return Vrijednost drugog argumenta.
	 */
	private Object dohvatiVrijednost(Computer computer) {
		if(drugiArgument.isNumber()){
			return (Integer)drugiArgument.getValue();
		}
		else{
			int deskriptor = (int)drugiArgument.getValue();
			int index = RegisterUtil.getRegisterIndex(deskriptor);
			Object vrijednost = computer.getRegisters().getRegisterValue(index);
			
			if(!RegisterUtil.isIndirect(deskriptor)){
				return vrijednost;
			}
			else{
				int offset = RegisterUtil.getRegisterOffset(deskriptor);
				return computer.getMemory().getLocation(offset + (int)vrijednost);
			}
		}
	}

}
