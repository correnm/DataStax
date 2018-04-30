package com.g2ops.impact.urm.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
 
@FacesValidator("emailValidator")
public class EmailValidator implements Validator<Object> {

    private final static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    private final static Pattern EMAIL_COMPILED_PATTERN = Pattern.compile(EMAIL_PATTERN);

	@Override
	public void validate(FacesContext fc, UIComponent c, Object o) throws ValidatorException {

        // Email must have a value
        if (o == null || "".equals((String)o)) {
            FacesMessage msg = new FacesMessage("Email cannot be blank");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
         
        // the email matcher
        Matcher matcher = EMAIL_COMPILED_PATTERN.matcher((String)o);

        // Email doesn't match
        if (!matcher.matches()) {
            FacesMessage msg = new FacesMessage("Email entered is invalid");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }		
	}

}
