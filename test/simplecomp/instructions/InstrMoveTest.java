package hr.fer.zemris.java.simplecomp.instructions;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import hr.fer.zemris.java.simplecomp.impl.instructions.InstrMove;
import hr.fer.zemris.java.simplecomp.models.Computer;
import hr.fer.zemris.java.simplecomp.models.InstructionArgument;
import hr.fer.zemris.java.simplecomp.models.Registers;

@SuppressWarnings("javadoc")
@RunWith(MockitoJUnitRunner.class)
public class InstrMoveTest {

	@Mock
	private Registers registers;
	@Mock 
	private Computer computer;
	@Mock
	private List<InstructionArgument> list;
	@Mock
	private InstructionArgument arg1;
	@Mock
	private InstructionArgument arg2;
	
	@Test
	public void testMove(){
		
		when(list.size()).thenReturn(2);
		when(list.get(0)).thenReturn(arg1);
		when(list.get(1)).thenReturn(arg2);
		
		when(arg1.isRegister()).thenReturn(true);
		when((arg1.getValue())).thenReturn(3); //NEKI DIREKTAN REGISTAR
		when(arg2.isNumber()).thenReturn(true);
		when(arg2.getValue()).thenReturn(20); //DRUGI ARGUMENT JE BROJ 20
		
		when(computer.getRegisters()).thenReturn(registers);
		
		InstrMove move = new InstrMove(list);
		move.execute(computer);
		
		verify(computer, times(1)).getRegisters();
		verify(registers, times(1)).setRegisterValue(3, 20); //U TOM REGISTRU POSTAVLJENA JE VRIJEDNOST NA 20
	}
}
