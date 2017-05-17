package hr.fer.zemris.java.simplecomp.instructions;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import hr.fer.zemris.java.simplecomp.impl.instructions.InstrLoad;
import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;
import hr.fer.zemris.java.simplecomp.models.Memory;
import hr.fer.zemris.java.simplecomp.models.Registers;

@SuppressWarnings("javadoc")
@RunWith(MockitoJUnitRunner.class)
public class InstrLoadTest {

	@Mock
	private Memory memory;
	@Mock
	private Registers registers;
	@Mock 
	private Computer computer;
	@Mock
	private List<InstructionArgument> list;
	@Mock
	private InstructionArgument registar;
	@Mock
	private InstructionArgument memoryLocation;
	
	@Test
	public void testLoad(){
		
		when(list.size()).thenReturn(2);
		when(list.get(0)).thenReturn(registar);
		when(list.get(1)).thenReturn(memoryLocation);
		
		when(registar.isRegister()).thenReturn(true);
		when((registar.getValue())).thenReturn(3); //NEKI DIREKTAN REGISTAR
		when(memoryLocation.isNumber()).thenReturn(true);
		when(memoryLocation.getValue()).thenReturn(20); //NA LOKACIJI 20
		
		when(computer.getRegisters()).thenReturn(registers);
		when(computer.getMemory()).thenReturn(memory);
				
		when(memory.getLocation(20)).thenReturn(100); //NA LOKACIJI 20 JE BROJ 100
		
		InstrLoad load = new InstrLoad(list);
		load.execute(computer);
		
		verify(computer, times(1)).getRegisters();
		verify(computer, times(1)).getMemory();
		verify(memory, times(1)).getLocation(20);
		verify(registers, times(1)).setRegisterValue(3, 100); //U TOM REGISTRU POSTAVLJENA JE VRIJEDNOST 100
	}
}
