package hr.fer.zemris.java.simplecomp.instructions;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import hr.fer.zemris.java.simplecomp.impl.instructions.InstrPop;
import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;
import hr.fer.zemris.java.simplecomp.models.Memory;
import hr.fer.zemris.java.simplecomp.models.Registers;

@SuppressWarnings("javadoc")
@RunWith(MockitoJUnitRunner.class)
public class InstrPopTest {

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
	
	@Test
	public void testPop(){
		
		when(list.size()).thenReturn(1);
		when(list.get(0)).thenReturn(registar);
		
		when(registar.isRegister()).thenReturn(true);
		when((registar.getValue())).thenReturn(3); //NEKI DIREKTAN REGISTAR
		
		when(computer.getRegisters()).thenReturn(registers);
		when(computer.getMemory()).thenReturn(memory);
				
		when(memory.getLocation(151)).thenReturn(25); //VRIJEDNOST KOJA JE NA VRHU STOGA JE 25
		when(registers.getRegisterValue(Registers.STACK_REGISTER_INDEX)).thenReturn(150); //RECIMO DA JE VRH STOGA NA 150
		
		InstrPop pop = new InstrPop(list);
		pop.execute(computer);
		
		verify(computer, times(3)).getRegisters();
		verify(computer, times(1)).getMemory();
		verify(registers, times(1)).setRegisterValue(3, 25); //25 IDE U REGISTAR
		verify(registers, times(1)).setRegisterValue(Registers.STACK_REGISTER_INDEX, 151); //VRH STOGA POVEĆA SE ZA 1
	}
}
