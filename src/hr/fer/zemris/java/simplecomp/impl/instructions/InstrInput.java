package hr.fer.zemris.java.simplecomp.impl.instructions;

import java.util.List;
import java.util.Scanner;

import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.Instruction;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;

/**
 * Čita redak s tipkovnice. Sadržaj tumači kao Integer i njega zapisuje na 
 * zadanu memorijsku lokaciju. Dodatno postavlja zastavicu flag na true ako 
 * je sve u redu, odnosno na false ako konverzija nije uspjela ili je drugi 
 * problem s čitanjem.
 * Instrukcija: input lokacija
 * @author Mislav Gillinger
 * @version 1.0
 */
public class InstrInput implements Instruction{

	/**
	 * Lokacija na koju će se spremiti uneseni broj.
	 */
	private int lokacija;
	/**
	 * Čita podatke sa standardnog ulaza.
	 */
	private Scanner sc = new Scanner(System.in);
	
	/**
	 * Stvara novu naredbu {@link InstrInput}.
	 * @param arguments Lokacija na koju će se spremiti uneseni broj.
	 */
	public InstrInput(List<InstructionArgument> arguments){
		if(arguments.size() != 1 || !arguments.get(0).isNumber()){
			throw new IllegalArgumentException("Potrebno je unijeti jedan broj!");
		}
		
		lokacija = (int)arguments.get(0).getValue();
	}
	
	@Override
	public boolean execute(Computer computer) {
		String unos = null;
		Integer broj = null;
		
		computer.getRegisters().setFlag(true);
		
		try{
			unos = sc.nextLine();
			broj = Integer.valueOf(unos);
		}catch(Exception e){
			computer.getRegisters().setFlag(false);
		}
		
		if(computer.getRegisters().getFlag()){
			computer.getMemory().setLocation(lokacija, broj);
		}
		return false;
	}

}
