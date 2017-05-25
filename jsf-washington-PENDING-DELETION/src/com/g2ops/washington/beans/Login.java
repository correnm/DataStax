package com.g2ops.washington.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.g2ops.washington.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userOrg; // "Acme, Inc."
	private String userEmail; // "test@g2-ops.com"
	private String userPassCode = "";
	private String orgKeyspace = "";

	public String getUserOrg() {
		return(userOrg);
	}

	public String getUserEmail() {
		return(userEmail);
	}

	public String getUserPassCode() {
		return(userPassCode);
	}

	public String getOrgKeyspace() {
		return(orgKeyspace);
	}

	public void setUserOrg(String userOrg) {
		this.userOrg = userOrg;
	}
  
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserPassCode(String userPassCode) {
		this.userPassCode = userPassCode;
	}

	public void setOrgKeyspace(String orgKeyspace) {
		this.orgKeyspace = orgKeyspace;
	}
  
	public String loginActionControllerMethod() {

		// verify UserOrg value and get corresponding keyspace name
		if (userOrg.equals("G2-Ops")) {
			setOrgKeyspace("testKeyspace");
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Invalid Organization Name",
							""));
				return("login");
		}

		// authenticate user in corresponding keyspace with userEmail and userPassCode and set user's preferences
		if ((userEmail.equals("test@g2-ops.com")) && (userPassCode.equals("testing"))) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("orgKeyspace", "testKeyspace");
			session.setAttribute("userEmail", userEmail);
			session.setAttribute("userRole", "administrator");
			return("business-dashboard");
		} else {
			FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Incorrect Email Address and/or Passcode",
						""));
			return("login");
		}
		
	}

	// logout event, invalidate session
	public String logoutActionControllerMethod() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}

	public String helpActionControllerMethod() {
		return "page-b";
	}
	
}
