package work;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


	/**STUDENT tests for the methods of PasswordChecker
	/@author**/ 


public class PasswordCheckerTest_STUDENT {
		ArrayList<String> passwords;
		String pw1, pw2;
		private Scanner scanner;
	@Before
	public void setUp() throws Exception {
		String[] pw = {"Akderei2023!", "NoG103", "NoGooodze403", "NOLOWERCASE0941", "WorksOk2023",
				"alllowercase2023", "Goodpas2023!", "Weaky405", "StrongPass102!", "ThisDoesNotWork", "Validx2023", "Wow10"}; 
		passwords = new ArrayList<String>();
		passwords.addAll(Arrays.asList(pw));
		}
	

	@After
	public void tearDown() throws Exception {
		passwords = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try
		{
			assertTrue(PasswordCheckerUtility.isValidPassword("WorksOk2023!"));
			PasswordCheckerUtility.isValidPassword("NoG103");
			assertTrue("Did not throw LengthException", false);
		}
		catch(LengthException e)
			{
			assertTrue("Threw LengthException", true);
			}
			catch(Exception e)
			{
				assertTrue("Threw another exception instead of LengthException", false);
			}
		}
	
	
	/**
//	 * Test if the password has at least one uppercase alpha character
//	 * This test should throw a NoUpperAlphaException for second case
//	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try
		{
			assertTrue(PasswordCheckerUtility.isValidPassword("WorksOk2023!"));
			PasswordCheckerUtility.isValidPassword("alllowercase2023");
			assertTrue("Did not throw NoUpperAlphaException", false);
		}
		catch(NoUpperAlphaException e)
		{
			assertTrue("Threw NoUpperAlphaException", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw another exception instead of NoUpperAlphaException", false);
		}


	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try
		{
			assertTrue(PasswordCheckerUtility.isValidPassword("Goodpas2023!"));
			PasswordCheckerUtility.isValidPassword("NOLOWERCASE0941");
			assertTrue("Did not throw NoLowerAlphaException", false);
		}
		catch(NoLowerAlphaException e)
		{
			assertTrue("Successfully threw NoLowerAlphaException", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw another exception instead of NoLowerAlphaException", false);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try
		{
			assertTrue(PasswordCheckerUtility.isWeakPassword("Weaky405"));
			assertFalse(PasswordCheckerUtility.isWeakPassword("StrongPass102!"));
		}
		catch(Exception e)
		{
			assertTrue("Threw another exception instead of NoLowerAlphaException", false);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try
		{
			assertTrue(PasswordCheckerUtility.isValidPassword("Validx2023!"));
			PasswordCheckerUtility.isValidPassword("NoGooodze406");
			assertTrue("Did not throw InvalidSequenceException", false);
		}
		catch(InvalidSequenceException e)
		{
			assertTrue("Successfully threw InvalidSequenceException", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw another exception instead of InvalidSequenceException", false);
		}
		
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("Akderei2023!"));
			PasswordCheckerUtility.isValidPassword("ThisDoesNotWork");
			assertTrue("Did not throw NoDigitException", false);
		}
		catch(NoDigitException e)
		{
			assertTrue("Successfully threw NoDigitException", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw another exception instead of NoDigitException", false);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("Akderei2023!"));
			PasswordCheckerUtility.isValidPassword("Wow10");
			assertTrue("Did not throw any Exception", false);
		}
		catch (Exception e)
		{
			assertTrue("Threw some exception", true);
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> tests = new ArrayList<>(Arrays.asList(
				"ValidPassword123!",
				"ShortPw",
				"NoDigits",
				"nouppercase123",
				"NODIGITALC",
				"Special@123"
				));
		
		ArrayList<String> invalidPasswords = PasswordCheckerUtility.getInvalidPasswords(tests);
		
		assertEquals(6, invalidPasswords.size());
		
		assertTrue(invalidPasswords.contains("ShortPw The password must be atleast 6 characters long"));
		assertTrue(invalidPasswords.contains("NoDigits The password must contain at least one digit"));
		assertTrue(invalidPasswords.contains("nouppercase123 The password must contain at least one uppercase alphabetic character"));
		assertFalse(invalidPasswords.contains("ValidPassword123!"));
		assertFalse(invalidPasswords.contains("Special@123"));
	}
	
}



