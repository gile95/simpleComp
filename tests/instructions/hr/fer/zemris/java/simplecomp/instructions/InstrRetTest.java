package hr.fer.zemris.java.simplecomp.instructions;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import hr.fer.zemris.java.simplecomp.impl.instructions.InstrRet;
import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;
import hr.fer.zemris.java.simplecomp.models.Memory;
import hr.fer.zemris.java.simplecomp.models.Registers;

@SuppressWarnings("javadoc")
@RunWith(MockitoJUnitRunner.class)
public class InstrRetTest {

	@Mock
	private Memory memory;
	@Mock
	private Registers registers;
	@Mock 
	private Computer computer;
	@Mock
	private List<InstructionArgument> list;
	
	@Test
	public void testRet(){
		
		when(list.size()).thenReturn(0);
		
		when(computer.getRegisters()).thenReturn(registers);
		when(computer.getMemory()).thenReturn(memory);
				
		when(memory.getLocation(151)).thenReturn(25); //VRIJEDNOST KOJA JE NA VRHU STOGA JE 25
		when(registers.getRegisterValue(Registers.STACK_REGISTER_INDEX)).thenReturn(150); //RECIMO DA JE VRH STOGA NA 150
		
		InstrRet ret = new InstrRet(list);
		ret.execute(computer);
		
		verify(computer, times(3)).getRegisters();
		verify(computer, times(1)).getMemory();
		verify(memory, times(1)).getLocation(151);
		verify(registers, times(1)).setProgramCounter(25); //25 IDE U PC
		verify(registers, times(1)).setRegisterValue(Registers.STACK_REGISTER_INDEX, 151); //VRH STOGA POVEĆA SE ZA 1
	}
}
