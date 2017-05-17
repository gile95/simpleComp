package hr.fer.zemris.java.simplecomp.instructions;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import hr.fer.zemris.java.simplecomp.impl.instructions.InstrCall;
import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;
import hr.fer.zemris.java.simplecomp.models.Memory;
import hr.fer.zemris.java.simplecomp.models.Registers;

@SuppressWarnings("javadoc")
@RunWith(MockitoJUnitRunner.class)
public class InstrCallTest {

	@Mock
	private Memory memory;
	@Mock
	private Registers registers;
	@Mock 
	private Computer computer;
	@Mock
	private List<InstructionArgument> list;
	@Mock
	private InstructionArgument adresa;
	
	@Test
	public void testCall(){
		
		when(list.size()).thenReturn(1);
		when(list.get(0)).thenReturn(adresa);
		
		when(adresa.isNumber()).thenReturn(true);
		when((adresa.getValue())).thenReturn(50); //POTPROGRAM JE NA LOKACIJI 50
		
		when(computer.getRegisters()).thenReturn(registers);
		when(computer.getMemory()).thenReturn(memory);
				
		when(registers.getProgramCounter()).thenReturn(13); //PC JE BIO NA 13
		when(registers.getRegisterValue(Registers.STACK_REGISTER_INDEX)).thenReturn(150); //RECIMO DA JE VRH STOGA NA 150
		
		InstrCall call = new InstrCall(list);
		call.execute(computer);
		
		verify(computer, times(4)).getRegisters();
		verify(computer, times(1)).getMemory();
		verify(registers, times(1)).getProgramCounter();
		verify(memory, times(1)).setLocation(150, 13); //25 IDE NA VRG STOGA
		verify(registers, times(1)).setRegisterValue(Registers.STACK_REGISTER_INDEX, 149); //VRH STOGA SMANJI SE ZA 1
		verify(registers, times(1)).setProgramCounter(50); //U PC IDE ADRESA POTPROGRAMA
	}
}
