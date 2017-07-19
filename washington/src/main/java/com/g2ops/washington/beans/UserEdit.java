package com.g2ops.washington.beans;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.g2ops.washington.utils.PasscodeEncryptionService;
import com.g2ops.washington.utils.SessionUtils;

@ManagedBean
@ViewScoped
public class UserEdit implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Session dbSession = SessionUtils.getOrgDBSession();
	private ResultSet rs;
	private Row row;
	private String userEmail, hashedPassword, hashedPasswordTest, hashedPasswordFromDB, userName, firstName, lastName, applicationRoleName, defaultLensView;
	private Boolean systemAdministratorInd, authenticate;
	byte[] salt = new byte[8];
	// String salt;
	byte[] encryptedPasscode, encryptedPasscodeTest;
	private Integer iterations = 20000;
	private String passcodeValueDelimiter = "***";
	

	public UserEdit() throws NoSuchAlgorithmException, InvalidKeySpecException {

		// execute the query
		rs = dbSession.execute("select user_email, hashed_password, application_role_name, default_lens_view_r, first_name, last_name, user_name, system_administrator_ind from users where user_email = 'john.reddy@g2-ops.com'");

		// get the result values
		row = rs.one();

		// set values to what was returned by the query
		userEmail = row.getString("user_email");
		hashedPasswordFromDB = row.getString("hashed_password");
		userName = row.getString("user_name");
		firstName = row.getString("first_name");
		lastName = row.getString("last_name");
		applicationRoleName = row.getString("application_role_name");
		defaultLensView = row.getString("default_lens_view_r");
		systemAdministratorInd = row.getBool("system_administrator_ind");
		
	}

    public String getUserEmail() {
    	return userEmail;
    }

    public String getHashedPassword() {
    	return hashedPassword;
    }

    public String getFirstName() {
    	return firstName;
    }

    public String getLastName() {
    	return lastName;
    }

    public void setHashedPassword(String hashedPassword) {
    	this.hashedPassword = hashedPassword;
    }

    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }

    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }

	public String editUserActionControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException {

		salt = PasscodeEncryptionService.generateSalt();
		encryptedPasscode = PasscodeEncryptionService.getEncryptedPasscode(hashedPassword, salt, iterations);
		//hashedPasswordTest = hashedPassword;
		//encryptedPasscodeTest = PasscodeEncryptionService.getEncryptedPasscode(hashedPasswordTest, salt, iterations);
    	String saltString = new String(salt);
    	//byte[] saltStringBytes = new byte[8];
    	//saltStringBytes = saltString.getBytes();
    	//String saltStringBytesString = new String(saltStringBytes);
    	String encryptedPasscodeString = new String(encryptedPasscode);
    	//String encryptedPasscodeStringTest = new String(encryptedPasscodeTest);
    	String passcodeValueToStore = iterations.toString();
    	passcodeValueToStore = passcodeValueToStore.concat(passcodeValueDelimiter);
    	passcodeValueToStore = passcodeValueToStore.concat(saltString);
    	passcodeValueToStore = passcodeValueToStore.concat(passcodeValueDelimiter);
    	passcodeValueToStore = passcodeValueToStore.concat(encryptedPasscodeString);
		System.out.println("passcodeValueToStore: " + passcodeValueToStore);

		//System.out.println("salt: " + salt);
		//System.out.println("saltString: " + saltString);
		//System.out.println("saltStringBytes: " + saltStringBytes);
		//System.out.println("saltStringBytesString: " + saltStringBytesString);
		//System.out.println("encryptedPasscode: " + encryptedPasscode);
		System.out.println("encryptedPasscodeString: " + encryptedPasscodeString);
		//System.out.println("encryptedPasscodeStringTest: " + encryptedPasscodeStringTest);
		//System.out.println("passcodeValueToStore: " + passcodeValueToStore);

    	
		// execute the query
		rs = dbSession.execute("update users set hashed_password = '" + passcodeValueToStore + "' where user_email = '" + userEmail + "'");

		//authenticate = PasscodeEncryptionService.authenticate(hashedPassword, encryptedPasscode, salt, iterations);
		//authenticate = PasscodeEncryptionService.authenticate(hashedPassword, encryptedPasscode, salt, iterations);

		//if (authenticate) {
			//System.out.println("authentication succeeded");
		//} else {
			//System.out.println("authentication failed");			
		//}
		
		/*
		String fakePassCode = "password";
		encryptedPasscode = PasscodeEncryptionService.getEncryptedPasscode(fakePassCode, salt2, iterations);
    	String fakeEncryptedPasscodeString = new String(encryptedPasscode);
		System.out.println("fakeEncryptedPasscodeString: " + fakeEncryptedPasscodeString);
		*/
		
		return "users-table";
		
	}
	
}
