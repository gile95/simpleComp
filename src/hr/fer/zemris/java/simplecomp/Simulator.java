package hr.fer.zemris.java.simplecomp;

import java.io.File;
import java.util.Scanner;

import hr.fer.zemris.java.simplecomp.impl.ComputerImpl;
import hr.fer.zemris.java.simplecomp.impl.ExecutionUnitImpl;
import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.ExecutionUnit;
import hr.fer.zemris.java.simplecomp.models.InstructionCreator;
import hr.fer.zemris.java.simplecomp.parser.InstructionCreatorImpl;
import hr.fer.zemris.java.simplecomp.parser.ProgramParser;

/**
 * Program koji pokreće implementaciju ovog računala. Program izvršava kod koji primi kao argument. 
 * Primjeri kodova dani su u direktoriju /examples.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class Simulator {

	/**
	 * Program kreće sa izvođenjem od ove metode.
	 * @param args Argumenti naredbenog retka.
	 */
	public static void main(String[] args) {
	
		String path;
		
		if(args.length == 1){
			path = args[0];
		}
		else{
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			System.out.print("Unesite put do datoteke: ");
			path = sc.nextLine();
		}
		
		if(!new File(path).exists()){
			System.out.println("Datoteka ne postoji!");
			System.exit(-1);
		}
		
		Computer comp = new ComputerImpl(256, 16);

		InstructionCreator creator = null;
		
		creator = new InstructionCreatorImpl("hr.fer.zemris.java.simplecomp.impl.instructions");
		
		try {
			ProgramParser.parse(path, comp, creator);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Code cannot be parsed!");
			System.exit(-1);
		}
		
		ExecutionUnit exec = new ExecutionUnitImpl();
		
		exec.go(comp);
	}

}
