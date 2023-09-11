package work;



import java.util.ArrayList;



public class PasswordCheckerUtility {

		/*This class is a password checker. A set of criteria is presented
		 * to the user that makes a password acceptable. The user is then prompted
		 * to enter a password that fits the criteria presented.
		Ismail Akdere
		@author*/

		public static boolean isValidLength(String password) throws LengthException {

			if (password.length() < 6) {

				throw new LengthException();

			}

			return true;

		}

		public static boolean hasDigits(String password) {
		    for (int i = 0; i < password.length(); i++) {
		        if (Character.isDigit(password.charAt(i))) {
		            return true; // Return true if at least one digit is found
		        }
		    }
		    
		    return false; // Return false if no digit is found
		}


		public static void comparePasswords(String password, String passwordCon) throws UnmatchedException {

			if (!password.equals(passwordCon)) {

				throw new UnmatchedException();

			}

			

		}

		public static boolean comparePasswordsWithReturn(String password, String passwordCon) {

			if (password.equals(passwordCon)) {

				return true;

			}

			return false;

		}

		public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException { 

			for (int i = 0; i < password.length(); i++) {

				if (Character.isUpperCase(password.charAt(i))) {

					return true;

				}

			}

			throw new NoUpperAlphaException();

		}

		public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {

			for (int i = 0; i < password.length(); i++) {

				if (Character.isLowerCase(password.charAt(i))) {

					return true;

				}

			}

			throw new NoLowerAlphaException();

		}

		public static boolean NoSameCharInSequence(String password) throws InvalidSequenceException {

			for (int i = 0; i < password.length() - 2; i++) {

				if (password.charAt(i) == password.charAt(i + 1) && password.charAt(i) == password.charAt(i + 2)) {

					throw new InvalidSequenceException();

				}

			}

			return false;

		}

		public static boolean hasBetweenSixAndNineChars(String password) {

			int PassLength = password.length();

			return PassLength >= 6 && PassLength <= 9;

		}

		public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {

			boolean SpecialC = false;

			for (int i = 0; i < password.length(); i++) {

				char c = password.charAt(i);

				if (!Character.isLetterOrDigit(c)) {

					SpecialC = true;

					

				}

			}

			if (SpecialC) {

				return true;

				

			}

			throw new NoSpecialCharacterException();

		}

		public static boolean isValidPassword(String password) 

				throws LengthException, NoUpperAlphaException, NoLowerAlphaException, 

				NoDigitException, NoSpecialCharacterException, InvalidSequenceException {

			boolean Valid = true;

			

			if (!isValidLength(password)) {

				Valid = false;

				throw new LengthException();

			}

			if (!hasUpperAlpha(password)) {

				Valid = false;

				throw new NoUpperAlphaException();

			}

			if (!hasLowerAlpha(password)) {

				Valid = false;

				throw new NoLowerAlphaException();	

			}

			if (!hasDigits(password)) {

				Valid = false;

				throw new NoDigitException();

			}

			if (!hasSpecialChar(password)) {

				Valid = false;

				throw new NoSpecialCharacterException();

			}

			if (!NoSameCharInSequence(password)) {

				Valid = false;

				throw new InvalidSequenceException();

			}

			return Valid;



		}

		public static boolean isWeakPassword(String password)

				throws WeakPasswordException, LengthException, NoDigitException, 

				NoUpperAlphaException, NoLowerAlphaException, InvalidSequenceException, 

				NoSpecialCharacterException {

			int passLength = password.length();

			if (passLength >= 6 && passLength <= 9) {

				throw new WeakPasswordException();

					

				}

				return false;

			

		}

		public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {

			ArrayList<String> Checker = new ArrayList<>();

			for (int i = 0; i < passwords.size(); i++) {

				try {

					isValidPassword(passwords.get(i));

				} catch (Exception e) {

					Checker.add(passwords.get(i) + " " + e.getMessage());

				}

			}

			return Checker;

		}

		

		

		

		

}



//package work;
//
//import java.util.ArrayList;
//
//public class PasswordCheckerUtility {
//	
//	public PasswordCheckerUtility()
//	{
//		
//	}
//	
//	public static boolean isValidPassword(String password) throws LengthException, NoDigitException, 
//	NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException
//	{
//		if (!isValidLength(password))
//		{
//			throw new LengthException();
//		}
//		
//		if (!hasUpperAlpha(password))
//		{
//			throw new NoUpperAlphaException();
//		}
//		
//		if (!hasLowerAlpha(password))
//		{
//			throw new NoLowerAlphaException();
//		}
//		
//		if (!hasDigit(password))
//		{
//			throw new NoDigitException();
//		}
//		
//		if (!hasSpecialChar(password))
//		{
//			throw new NoSpecialCharacterException();
//		}
//		
//		if(!NoSameCharInSequence(password))
//		{
//			throw new InvalidSequenceException();
//		}
//		
//		return true;
//	}
//		
//	
//	
//	public static boolean hasDigit(String password) throws NoDigitException
//	{
//		for (int i = 0; i < password.length(); i++)
//		{
//			if (Character.isDigit(i))
//			{
//				return true;
//			}
//		}
//		throw new NoDigitException();
//	}
//	
//	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException
//	{
//		String specialChars = "!@#$%^&*()_+[]{}|;':,.<>?";
//		
//		for (char ch  : password.toCharArray())
//		{
//			if (specialChars.contains(String.valueOf(ch)))
//			{
//				return true;
//			}
//		}
//		throw new NoSpecialCharacterException();
//	}
//	
//	public static boolean NoSameCharInSequence(String password) throws InvalidSequenceException
//	{		
//		for (int i = 0; i < password.length() - 2; i++)
//		{
//			if (password.charAt(i) == password.charAt(i+1) && password.charAt(i) == password.charAt(i+2))
//			{
//				throw new InvalidSequenceException();
//			}
//		}
//		return false;
//	}
//	
//	public static boolean hasBetweenSixAndNineChars(String password)
//	{
//		return password.length() >= 6 && password.length() <= 9;
//	}
//	
//	public static boolean isWeakPassword(String password) throws WeakPasswordException
//	{
		//int length = password.Length();
		
//		if (length >= 6 && pass<= 9)
//		{
//			throw new WeakPasswordException();
//		}
//		return false;
//	}
//	
//	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException
//	{
//		boolean hasLowerCase = false;
//		
//		for (int i = 0; i < password.length(); i++)
//		{
//			char ch = password.charAt(i);
//			if (Character.isLowerCase(ch))
//			{
//				hasLowerCase = true;
//				break;
//			}
//		}
//		if(hasLowerCase)
//		{
//			return true;
//		}
//		else
//			throw new NoLowerAlphaException();
//	}
//	
//	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException
//	{
//		boolean hasUpperCase = false;
//		
//		for (int i = 0; i < password.length(); i++)
//		{
//			char ch = password.charAt(i);
//			if (Character.isUpperCase(ch))
//			{
//				hasUpperCase = true;
//				break;
//			}
//		}
//		if (hasUpperCase)
//		{
//			return true;
//		}
//		else
//			throw new NoUpperAlphaException();
//	}
//	
//	public static void comparePasswords(String passwordConfirm, String password) throws UnmatchedException
//	{
//		if (!password.equals(passwordConfirm))
//		{
//			throw new UnmatchedException();
//		}
//	}
//	
//	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm)
//	{
//		return passwordConfirm.equals(password);
//	}
//	
//	public static boolean isValidLength(String password) throws LengthException
//	{
//		if (password.length() > 6)
//		{
//			return true;
//		}
//		else
//			throw new LengthException();
//	}
//	
//	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords)
//	{
//		ArrayList<String> invalidPasswords = new ArrayList<>();
//		
//		for (int i = 0; i < passwords.size(); i++) 
//		{
//			
//			try 
//			{
//				isValidPassword(passwords.get(i));
//			} 
//			catch (Exception e) 
//			{
//				invalidPasswords.add(passwords.get(i) + " " + e.getMessage());
//			}
//			
//		 }
//			
//		return invalidPasswords;
//	}
				
//}
	

