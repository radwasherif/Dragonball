package dragonball.tests;
import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import org.junit.Test;

import dragonball.model.character.*;
import dragonball.model.character.Character;
import dragonball.model.character.fighter.*;


public class V3M1test {


	@Test(timeout = 1000)
	public void testNonFighterClassConstructor() throws Exception {

		Class aClass = NonFighter.class;
		boolean thrown = false;
		try {
			Constructor constructor = aClass.getConstructor(new Class[] {
					String.class});
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse("Missing constructor with 1 parameter in NonFighter class.",
				thrown);
	}
	
	@Test(timeout = 1000)
	public void testAbstraction() throws Exception {

		assertTrue("No objects of type NonFighter can be instantiated.",
				Modifier.isAbstract(NonFighter.class.getModifiers()));
	}
	
	@Test(timeout = 1000)
	public void testNonFighterClassInterface() throws Exception {
		Class[] interfaces = NonFighter.class.getInterfaces();
		boolean inherits = false;
		for (Class i : interfaces) {
			if (i.toString().equals(Spectator.class.toString()))
				inherits = true;
		}
		assertTrue(
				"NonFighter class should implement Spectator interface.",
				inherits);
	}
	
	@Test(timeout = 1000)
	public void testSpectator() throws Exception{
		assertEquals("Spectator should be an Interface", 1537,
				Spectator.class.getModifiers());
	}
	
	@Test(timeout=1000)
	public void testInheritanceTrainer() throws Exception{

		assertEquals("Trainer should have an appropraite superclass.",
				NonFighter.class, Trainer.class.getSuperclass());
	}
	
	@Test(timeout=1000)
	public void testInheritanceHealer() throws Exception{
	
		assertEquals("Healer should have an appropraite superclass.",
				NonFighter.class, Healer.class.getSuperclass());
	}
	
	@Test(timeout = 1000)
	public void testInheritanceClasses() throws Exception {

		assertEquals("NonFighter should have an appropraite superclass.",
				Character.class, NonFighter.class.getSuperclass());
	}
	
	@Test(timeout = 1000)
	public void testEncapsulationTrainer() throws Exception {
		Method[] methods = Trainer.class.getDeclaredMethods();
		assertTrue(
				"The class does not have the needed methods.",
				containsMethodName(methods, "getAddedDamage"));
		assertFalse(
				"The class has an invalid method.",
				containsMethodName(methods, "setAddedDamage"));

	}
	
	@Test(timeout = 1000)
	public void testConstructorTrainer()throws Exception
	{
		
		Trainer t = new Trainer("newTrainer",3);

		
		assertEquals("Trainer constructor should initialize class variables correctly",3,t.getAddedDamage());

		
		assertEquals("Trainer constructor should initialize class variables correctly","newTrainer",t.getName());
	}

	@Test(timeout = 1000)
	public void testModifierTrainer() throws Exception
	{
		Field field = Trainer.class.getDeclaredField("addedDamage");
		assertEquals("You should update variables' access modifiers to an appropriate one.", 2, field.getModifiers());
	}
	
	@Test(timeout = 1000)
	public void testEncapsulationHealer() throws Exception {

		Method[] methods = Healer.class.getDeclaredMethods();
	
		assertTrue(
				"The class does not have the needed methods.",
				containsMethodName(methods, "getPossibleHPrecovery"));
		assertFalse(
				"The class has an invalid method.",
				containsMethodName(methods, "setPossibleHPrecovery"));
	
	}
	
	@Test(timeout = 1000)
	public void testConstructorHealer() throws Exception
	{
		Healer h = new Healer("newHealer",2);
		assertEquals("Healer constructor should initialize class variables correctly","newHealer",h.getName());
		assertEquals("Healer constructor should initialize class variables correctly",2,h.getPossibleHPrecovery());
	}
	
	@Test(timeout = 1000)
	public void testModifierHealer() throws Exception
	{
		Field field = Healer.class.getDeclaredField("possibleHPrecovery");
		assertEquals("You should update variables' access modifiers to an appropriate one.", 2, field.getModifiers());
	}
	
	@Test(timeout = 1000)
	public void testNamekianChanges ()throws Exception
	{
		Namekian n = new Namekian("Namekian");
		assertEquals("You should update Namekian initial blast damage to the required value.", 50, n.getBlastDamage());
		assertEquals("You shouldn't update Namekian max health points.", 1350, n.getMaxHealthPoints());
		assertEquals("You shouldn't update Namekian ki.", 0, n.getKi());

	
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
