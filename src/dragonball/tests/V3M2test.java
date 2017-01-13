package dragonball.tests;
import static org.junit.Assert.*;

import java.lang.reflect.Method;
import org.junit.Test;

import dragonball.model.character.*;
import dragonball.model.character.fighter.*;



public class V3M2test {
	@Test(timeout = 1000)
	public void testInterfaceSpectator() throws Exception {
		Method[] methods = Spectator.class.getDeclaredMethods();
		
		assertTrue(
				"The class does not have the needed methods.",
				containsMethodName(methods, "helpFighter"));
		assertTrue(
				"The class does not have the needed methods.",
				containsMethodName(methods, "cheerFighter"));
		

	}
	
	@Test(timeout = 1000)
	public void testInterfaceNonFighter() throws Exception {
		Method[] methods = NonFighter.class.getDeclaredMethods();

		assertTrue(
				"The class does not have the needed methods.",
				containsMethodName(methods, "helpFighter"));
		assertTrue(
				"The class does not have the needed methods.",
				containsMethodName(methods, "cheerFighter"));
		

	}
	
	@Test(timeout = 1000)
	public void testPolyHealer() throws Exception {
		Method[] methods = Healer.class.getDeclaredMethods();

		assertTrue(
				"The class does not have the needed methods.",
				containsMethodName(methods, "helpFighter"));
		assertFalse(
				"The class has an invalid method.",
				containsMethodName(methods, "cheerFighter"));

	}
	
	@Test(timeout = 1000)
	public void testPolyTrainer() throws Exception {
		Method[] methods = Trainer.class.getDeclaredMethods();

		assertTrue(
				"The class does not have the needed methods.",
				containsMethodName(methods, "helpFighter"));
		assertFalse(
				"The class has an invalid method.",
				containsMethodName(methods, "cheerFighter"));

	}
	
	@Test(timeout = 1000)
	public void testCheerFighterNonFighter() throws Exception {
		Fighter f = new Saiyan("Saiyan");
		f.setKi(2);
		f.setStamina(1);
		NonFighter nf = new Healer("Healer",5);
		nf.cheerFighter(f);
		
		
		assertEquals(
				"Fighters stamina should increase after cheer fighter if the current ki is greater than the current stamina",3, f.getStamina());
		
		assertEquals(
				"Fighters ki should not increase after cheer fighter if the current ki is greater than the current stamina",2,f.getKi());
		
		

	}
	
	@Test(timeout = 1000)
	public void testCheerFighterTrainer() throws Exception {
		Fighter f = new Saiyan("Saiyan");
		f.setKi(2);
		f.setStamina(1);
		NonFighter nf = new Trainer("Trainer",6);
		nf.cheerFighter(f);
		
		
		assertEquals(
				"Fighters stamina should increase after cheer fighter if the current ki is greater than the current stamina",3, f.getStamina());
		
		assertEquals(
				"Fighters ki should not increase after cheer fighter if the current ki is greater than the current stamina",2,f.getKi());
		
		

	}
	
	@Test(timeout = 1000)
	public void testCheerFighterNonFighter2() throws Exception {
		Fighter f = new Saiyan("Saiyan");
		f.setKi(2);
		f.setStamina(1);
		NonFighter nf = new Healer("Healer",5);
		nf.cheerFighter(f);
	
		f.setKi(2);
		f.setStamina(3);
		nf.cheerFighter(f);
		
		assertEquals(
				"Fighters stamina should not increase after cheer fighter if the current ki is not greater than the current stamina",3, f.getStamina());
		
		assertEquals(
				"Fighters ki should increase after cheer fighter if the current ki is not greater than the current stamina",5,f.getKi());
		

	}

	@Test(timeout = 1000)
	public void testHelpFighterNonFighter() throws Exception {
		Fighter f = new Saiyan("Saiyan");
		f.setKi(2);
		f.setStamina(1);
		NonFighter nf = new Healer("Healer",5);
		nf.helpFighter(f);
		
		
		assertEquals(
				"Fighters stamina should increase after help fighter",2, f.getStamina());
		
		assertEquals(
				"Fighters stamina should increase after help fighter",3,f.getKi());

	}
	
	@Test(timeout = 1000)
	public void testHelpFighterHealer() throws Exception {
		Fighter f = new Saiyan("Saiyan");
		f.setKi(2);
		f.setStamina(1);
		int initialHP = f.getHealthPoints();
		Healer nf = new Healer("Healer",50);
		nf.helpFighter(f);
		

		assertEquals(
				"Fighters health points should increase by the possibleHPrecovery of the healer after help fighter",initialHP+50,f.getHealthPoints());

	}
	
	@Test(timeout = 1000)
	public void testHelpFighterTrainer() throws Exception {
		Fighter f = new Saiyan("Saiyan");
		f.setKi(2);
		f.setStamina(1);
		int initialBD = f.getBlastDamage();
		int initialPD = f.getPhysicalDamage();
		Trainer nf = new Trainer("Healer",50);
		nf.helpFighter(f);
		
		
		assertEquals(
				"Fighters stamina should increase after help fighter",2, f.getStamina());
		
		assertEquals(
				"Fighters stamina should increase after help fighter",3,f.getKi());
		assertEquals(
				"Fighters blast damage should increase by the added damage of the trainer after help fighter",initialBD+50,f.getBlastDamage());
		assertEquals(
				"Fighters physical damage should increase by the added damage of the trainer after help fighter",initialPD+50,f.getPhysicalDamage());

	}
	
		
	// --------------------------------------------Helper methods----------------------------------------------------------
			public static boolean containsMethodName(Method[] methods, String name) {
				for (Method method : methods) {
					if (method.getName().equals(name))
						return true;
				}
				return false;
			}
	
}
