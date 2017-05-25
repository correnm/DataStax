package com.exercise;
import javax.annotation.ManagedBean;
import javax.faces.bean.*;


//added all of the below for date
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
//sample requested javax.faces.bean.ManagedBean but that is already included
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

@ManagedBean
//@SessionScoped
public class OrgTest {
	
	//uuid
	public String org_id;

	//UDT = User Defined Type
	public Date  dateadded; 
	public String addedbyusername;
	
	//UDT = User Defined Type 
	public Date dateupdated;
	public String updatedbyusername;
	
	//text
	public String industry_name_r; 
	public String organization_name; 
//changed all from private to public
	
	public String getOrgName()
	{ 
		return (organization_name);
	}
	public void setOrgName(String organization_name) 
	{ 
		this.organization_name = organization_name;
	}
	
	
	public String getIndName() 
	{
		return(industry_name_r);
	}
	public void setIndustryName(String industry_name_r)
	{ 
		this.industry_name_r = industry_name_r;
	}
	
	//Added this for date calendar selection
	public void onDateSelect(SelectEvent event){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
	
	
	//changed data type to Date
	public Date getDateName() {
		return(dateadded);
	}
	public void setDateName(Date dateadded)
	{ 
		this.dateadded = dateadded;
	}
	
	
	public Date getDateUpdated()
	{
		return(dateupdated);
	}
	public void setDateUpdated(Date dateupdated)
	{ 
		this.dateupdated = dateupdated;
	}
	
	
	public String getAddedByUsername() 
	{		
		return(addedbyusername);
	}
	public void setAddedBy(String addedbyusername)
	{ 
		this.addedbyusername = addedbyusername;
	}
	
	
	public String getOrgID() 
	{
		return(org_id);
	}
	public void setOrgID(String org_id)
	{ 
		this.org_id = org_id;
	}
	
	
	public String getupdatedbyusername()
	{
		return(updatedbyusername);
	}
	public void setDateUpdated(String updatedbyusername)
	{
		this.updatedbyusername = updatedbyusername;
	}
 
public String addName() 
{
	//removed ||(dateName== "") from if statement below
	if ((organization_name== "")|| (industry_name_r== "")|| (addedbyusername== "") || (org_id== "") 
			)
	{
		return ("error");
	}
	else 
	{
		return ("VictoryRepeat");
	}
}
}
