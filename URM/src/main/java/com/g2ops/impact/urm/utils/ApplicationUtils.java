package com.g2ops.impact.urm.utils;

/**
 * @author 		John Reddy, G2 Ops, Virginia Beach, VA
 * @version 	1.00, March 2018
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 */

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Named("applicationUtils")
@ApplicationScoped
public class ApplicationUtils implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Pattern singleNumberPattern, singleUpperCasePattern, singleLowerCasePattern, singleSpecialCharacterPattern;
	private Matcher passcodeMatcher;

	// constructor
	public ApplicationUtils() {

		// compile regular expressions used to validate user passcodes
		singleNumberPattern = Pattern.compile("[0-9]+");
		singleUpperCasePattern = Pattern.compile("[A-Z]+");
		singleLowerCasePattern = Pattern.compile("[a-z]+");
		singleSpecialCharacterPattern = Pattern.compile("[!@#$&*]+");

	}

	// method to validate a new user passcode
	public Boolean meetsPasscodeRequirements(String passcodeToCheck) {

		// check for at least one number
		passcodeMatcher = singleNumberPattern.matcher(passcodeToCheck);
		if (!passcodeMatcher.find()) {
			System.out.println("*** in ApplicationUtils meetsPasscodeRequirements - passcode " + passcodeToCheck + " is missing a number ***");
			return false;
		}

		// check for at least one upper case letter
		passcodeMatcher = singleUpperCasePattern.matcher(passcodeToCheck);
		if (!passcodeMatcher.find()) {
			System.out.println("*** in ApplicationUtils meetsPasscodeRequirements - passcode " + passcodeToCheck + " is missing an upper case letter ***");
			return false;
		}

		// check for at least one lower case letter
		passcodeMatcher = singleLowerCasePattern.matcher(passcodeToCheck);
		if (!passcodeMatcher.find()) {
			System.out.println("*** in ApplicationUtils meetsPasscodeRequirements - passcode " + passcodeToCheck + " is missing a lower case letter ***");
			return false;
		}

		// check for at least one special character
		passcodeMatcher = singleSpecialCharacterPattern.matcher(passcodeToCheck);
		if (!passcodeMatcher.find()) {
			System.out.println("*** in ApplicationUtils meetsPasscodeRequirements - passcode " + passcodeToCheck + " is missing a special character ***");
			return false;
		}

		// check that the new passcode is at least eight characters in length and doesn't contain disallowed characters
		if (!Pattern.matches("[0-9A-Za-z!@#$&*]{8,}", passcodeToCheck)) {
			System.out.println("*** in ApplicationUtils meetsPasscodeRequirements - passcode " + passcodeToCheck + " is not at least 8 characters long or contains disallowed characters ***");
			return false;
		}

		// if got this far, then passcode must meet requirements
		System.out.println("*** in ApplicationUtils meetsPasscodeRequirements - passcode " + passcodeToCheck + " meets all requirements ***");
		return true;

	}

}
