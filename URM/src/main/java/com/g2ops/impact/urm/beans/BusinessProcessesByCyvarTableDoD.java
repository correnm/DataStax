package com.g2ops.impact.urm.beans;

/**
 * @author 		John Reddy, G2 Ops, Virginia Beach, VA
 * @version 	1.00, May 2017
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 07-Jul-2017		corren.mccoy		Added additional placeholder data for demo purposes
 * 
 */

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.g2ops.impact.urm.types.BusinessProcessDoD;
import com.g2ops.impact.urm.utils.BusinessProcessesByCyvarDoD;

@Named("businessProcessesByCyvarTableDoD")
@RequestScoped
public class BusinessProcessesByCyvarTableDoD extends BusinessProcessesByCyvarDoD {
	private static final long serialVersionUID = 1L;
	public BusinessProcessesByCyvarTableDoD () {
		super("Business Process CyVaR Details",
				new BusinessProcessDoD("Critical Mission 6", "Administrative", "3.3", "11.9%"),
				new BusinessProcessDoD("Critical Mission 5", "Mission Essential", "3.7", "6.7%"),
				new BusinessProcessDoD("Critical Mission 1", "Mission Critical", "9.3",	"14.8%"),
				new BusinessProcessDoD("Critical Mission 4", "Mission Essential", "4.8", "21.9%"),
				new BusinessProcessDoD("Critical Mission 2", "Mission Essential", "6.3", "17.4%"),
				new BusinessProcessDoD("Critical Mission 7", "Administrative", "2.3", "12.1%"),
				new BusinessProcessDoD("Critical Mission 8", "Administrative", "2.3", "11.4%"),
				new BusinessProcessDoD("Critical Mission 3", "Mission Essential", "4.9", "5.8%")
				);
	}
}
