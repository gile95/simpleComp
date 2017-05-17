package hr.fer.zemris.java.simplecomp.impl;

import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.ExecutionUnit;
import hr.fer.zemris.java.simplecomp.models.Instruction;

/**
 * Upravljački sklop računala. Ovaj razred "izvodi"
 * program odnosno predstavlja impulse takta za sam
 * procesor.
 * Implementira {@link ExecutionUnit}.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class ExecutionUnitImpl implements ExecutionUnit {

	@Override
	public boolean go(Computer computer) {
		
		computer.getRegisters().setProgramCounter(0);
		
		while(true){
			//dohvat instrukcije sa trenutnog programskog brojila
			Instruction instruction = null;
			Object potentialInstruction = computer.getMemory().getLocation(computer.getRegisters().getProgramCounter());
			if(potentialInstruction instanceof Instruction){
				instruction = (Instruction) potentialInstruction;
			}
			
			computer.getRegisters().incrementProgramCounter();
			
			if(instruction.execute(computer)){
				//true ako treba zaustaviti procesor
				break;
			}
		}
		
		return true;
	}

}
