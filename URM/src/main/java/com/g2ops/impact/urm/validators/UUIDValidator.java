package com.g2ops.impact.urm.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
 
@FacesValidator("uuidValidator")
public class UUIDValidator implements Validator<Object> {

    private final static String UUID_PATTERN = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
    
    private final static Pattern UUID_COMPILED_PATTERN = Pattern.compile(UUID_PATTERN);

	@Override
	public void validate(FacesContext fc, UIComponent c, Object o) throws ValidatorException {

        // UUID must have a value
        if (o == null || "".equals((String)o)) {
            FacesMessage msg = new FacesMessage("UUID cannot be blank");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
         
        // the uuid matcher
        Matcher matcher = UUID_COMPILED_PATTERN.matcher((String)o);

        // UUID doesn't match
        if (!matcher.matches()) {
            FacesMessage msg = new FacesMessage("UUID is invalid");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
	}

}
