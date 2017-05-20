package hr.fer.zemris.java.simplecomp;

/**
 * Rezred koji definira metode za rad sa deskriptorom registra.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class RegisterUtil {
	
	/**
	 * Vraća index registra.
	 * @param registerDescriptor Deskriptor registra.
	 * @return Index registra.
	 */
	public static int getRegisterIndex(int registerDescriptor) {
		return registerDescriptor & 0xFF;
	}
	/**
	 * Provjerava da li registar koristi indirektno adresiranje.
	 * @param registerDescriptor Deskriptor registra.
	 * @return True ako registar koristi indirekto adresiranje, inače false.
	 */
	public static boolean isIndirect(int registerDescriptor) {
		return (registerDescriptor & 0x1000000) != 0;
	}
	/**
	 * Vraća offset registra. Offset je broj memorijskih lokacija udaljen od neke zadane.
	 * @param registerDescriptor Deskriptor registra.
	 * @return Offset registra.
	 */
	public static int getRegisterOffset(int registerDescriptor) {
		if ((registerDescriptor & 0x800000) != 0){
			return -((((registerDescriptor & 0x7FFF00) ^ 0x7FFF00) >> 8) + 1);
		}
		else{
			return (registerDescriptor & 0x7FFF00) >> 8;
		}
	}
}
