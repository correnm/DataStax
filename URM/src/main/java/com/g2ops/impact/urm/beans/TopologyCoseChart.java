package com.g2ops.impact.urm.beans;

/**
 * @author 		Dang Le, G2 Ops, Virginia Beach, VA
 * @version 	1.00, May 2017
 * @see			https://stackoverflow.com/questions/32251251/java-classloader-getresource-with-special-characters-in-path
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 07-Jul-2017		corren.mccoy		Added additional placeholder data for demo purposes
 * 23-Jul-2017		john.reddy			Changed file access pattern to properly handle getResource with special characters in the path
 * 
 */

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("topologyCoseChart")
@RequestScoped
public class TopologyCoseChart {

	private String elementsData, edgesData;

	public TopologyCoseChart() {

        try {

        	// create StringBuilder object
        	StringBuilder dataString = new StringBuilder();

        	// add the elements (nodes) to the StringBuilder object
        	dataString.append("{ data: { id: '10.106.61.248', bpiv: '7.961963734' } },\n");
        	dataString.append("{ data: { id: '10.106.75.213', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.57.215', bpiv: '6.118372069' } },\n");
        	dataString.append("{ data: { id: '10.106.74.251', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.61.231', bpiv: '7.961297989' } },\n");
        	dataString.append("{ data: { id: '10.106.55.235', bpiv: '7.962313539' } },\n");
        	dataString.append("{ data: { id: '10.106.62.213', bpiv: '7.962890009' } },\n");
        	dataString.append("{ data: { id: '10.106.248.97', bpiv: '6.424462024' } },\n");
        	dataString.append("{ data: { id: '10.106.61.226', bpiv: '7.962151657' } },\n");
        	dataString.append("{ data: { id: '10.106.75.211', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.61.239', bpiv: '7.964597048' } },\n");
        	dataString.append("{ data: { id: '10.106.74.10', bpiv: '6.778189321' } },\n");
        	dataString.append("{ data: { id: '10.106.75.202', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.56.87', bpiv: '7.430538351' } },\n");
        	dataString.append("{ data: { id: '10.106.74.250', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.74.248', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.61.243', bpiv: '7.964986851' } },\n");
        	dataString.append("{ data: { id: '10.106.248.132', bpiv: '8.032022129' } },\n");
        	dataString.append("{ data: { id: '10.106.57.232', bpiv: '7.257667328' } },\n");
        	dataString.append("{ data: { id: '10.106.61.246', bpiv: '7.965375431' } },\n");
        	dataString.append("{ data: { id: '10.106.61.251', bpiv: '7.962006194' } },\n");
        	dataString.append("{ data: { id: '10.106.246.77', bpiv: '4.044894462' } },\n");
        	dataString.append("{ data: { id: '10.106.57.217', bpiv: '7.257667328' } },\n");
        	dataString.append("{ data: { id: '10.106.245.12', bpiv: '6.446303' } },\n");
        	dataString.append("{ data: { id: '10.106.248.98', bpiv: '6.425261274' } },\n");
        	dataString.append("{ data: { id: '10.106.252.220', bpiv: '7.073311459' } },\n");
        	dataString.append("{ data: { id: '10.106.75.248', bpiv: '7.956785174' } },\n");
        	dataString.append("{ data: { id: '10.106.75.212', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.246.107', bpiv: '9.038435129' } },\n");
        	dataString.append("{ data: { id: '10.106.61.229', bpiv: '7.962181803' } },\n");
        	dataString.append("{ data: { id: '10.106.61.227', bpiv: '7.962049746' } },\n");
        	dataString.append("{ data: { id: '10.106.74.245', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.245.253', bpiv: '7.371710959' } },\n");
        	dataString.append("{ data: { id: '10.106.75.250', bpiv: '7.957963412' } },\n");
        	dataString.append("{ data: { id: '10.106.246.84', bpiv: '9.036780157' } },\n");
        	dataString.append("{ data: { id: '10.106.79.9', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.61.235', bpiv: '7.959717393' } },\n");
        	dataString.append("{ data: { id: '10.106.60.78', bpiv: '7.429266601' } },\n");
        	dataString.append("{ data: { id: '10.106.75.240', bpiv: '7.957262188' } },\n");
        	dataString.append("{ data: { id: '10.106.75.203', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.75.191', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.74.12', bpiv: '7.503271933' } },\n");
        	dataString.append("{ data: { id: '10.106.60.124', bpiv: '7.569797984' } },\n");
        	dataString.append("{ data: { id: '10.106.75.205', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.252.208', bpiv: '6.41944879' } },\n");
        	dataString.append("{ data: { id: '10.106.74.246', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.74.29', bpiv: '7.98583446' } },\n");
        	dataString.append("{ data: { id: '10.106.61.245', bpiv: '7.96524604' } },\n");
        	dataString.append("{ data: { id: '10.106.57.235', bpiv: '7.257667328' } },\n");
        	dataString.append("{ data: { id: '10.106.59.177', bpiv: '7.257725558' } },\n");
        	dataString.append("{ data: { id: '10.106.61.222', bpiv: '7.960005404' } },\n");
        	dataString.append("{ data: { id: '10.106.74.249', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.246.86', bpiv: '9.037608505' } },\n");
        	dataString.append("{ data: { id: '10.106.61.224', bpiv: '7.96049958' } },\n");
        	dataString.append("{ data: { id: '10.106.59.243', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.75.204', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.61.244', bpiv: '7.965116513' } },\n");
        	dataString.append("{ data: { id: '10.106.75.206', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.74.23', bpiv: '5.832522936' } },\n");
        	dataString.append("{ data: { id: '10.106.75.214', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.62.241', bpiv: '7.962758332' } },\n");
        	dataString.append("{ data: { id: '10.106.56.102', bpiv: '7.963722216' } },\n");
        	dataString.append("{ data: { id: '10.106.75.197', bpiv: '7.960975246' } },\n");
        	dataString.append("{ data: { id: '10.106.248.100', bpiv: '6.958814012' } },\n");
        	dataString.append("{ data: { id: '10.106.246.101', bpiv: '7.269944742' } },\n");
        	dataString.append("{ data: { id: '10.106.248.133', bpiv: '6.825653907' } },\n");
        	dataString.append("{ data: { id: '10.106.74.52', bpiv: '6.430522866' } },\n");
        	dataString.append("{ data: { id: '10.106.252.217', bpiv: '6.709274082' } },\n");
        	dataString.append("{ data: { id: '10.106.248.125', bpiv: '5.453767375' } },\n");
        	dataString.append("{ data: { id: '10.106.248.106', bpiv: '6.426540651' } },\n");
        	dataString.append("{ data: { id: '10.106.61.236', bpiv: '7.961076635' } },\n");
        	dataString.append("{ data: { id: '10.106.246.163', bpiv: '4.04965439' } },\n");
        	dataString.append("{ data: { id: '10.106.252.218', bpiv: '6.585819655' } },\n");
        	dataString.append("{ data: { id: '10.106.246.161', bpiv: '6.447226293' } },\n");
        	dataString.append("{ data: { id: '10.106.75.242', bpiv: '7.957611475' } },\n");
        	dataString.append("{ data: { id: '10.106.63.73', bpiv: '7.962839541' } },\n");
        	dataString.append("{ data: { id: '10.106.248.127', bpiv: '6.629928062' } },\n");
        	dataString.append("{ data: { id: '10.106.74.135', bpiv: '7.966198576' } },\n");
        	dataString.append("{ data: { id: '10.106.23.41', bpiv: '7.593694632' } },\n");
        	dataString.append("{ data: { id: '10.106.253.80', bpiv: '6.59883403' } },\n");
        	dataString.append("{ data: { id: '10.106.74.252', bpiv: '8.310675852' } },\n");
        	dataString.append("{ data: { id: '10.106.248.89', bpiv: '6.29845491' } },\n");
        	dataString.append("{ data: { id: '10.106.75.241', bpiv: '7.957544129' } },\n");
        	dataString.append("{ data: { id: '10.106.57.225', bpiv: '7.257725558' } },\n");
        	dataString.append("{ data: { id: '10.106.248.130', bpiv: '6.594056475' } },\n");
        	dataString.append("{ data: { id: '10.106.57.227', bpiv: '6.118372069' } },\n");
        	dataString.append("{ data: { id: '10.106.248.95', bpiv: '6.592858245' } },\n");
        	dataString.append("{ data: { id: '10.106.61.237', bpiv: '7.961343098' } },\n");
        	dataString.append("{ data: { id: '10.106.248.126', bpiv: '6.626653322' } },\n");
        	dataString.append("{ data: { id: '10.106.57.228', bpiv: '7.257725558' } },\n");
        	dataString.append("{ data: { id: '10.106.61.250', bpiv: '7.962228985' } },\n");
        	dataString.append("{ data: { id: '10.106.246.76', bpiv: '4.047565845' } },\n");
        	dataString.append("{ data: { id: '10.106.23.39', bpiv: '6.954273205' } },\n");
        	dataString.append("{ data: { id: '10.106.248.248', bpiv: '6.940646094' } },\n");
        	dataString.append("{ data: { id: '10.106.57.221', bpiv: '6.118372069' } },\n");
        	dataString.append("{ data: { id: '10.106.75.192', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.75.190', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.60.87', bpiv: '7.429258785' } },\n");
        	dataString.append("{ data: { id: '10.106.75.221', bpiv: '7.957537378' } },\n");
        	dataString.append("{ data: { id: '10.101.0.238', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.63.76', bpiv: '7.963101776' } },\n");
        	dataString.append("{ data: { id: '10.106.74.170', bpiv: '8.362291313' } },\n");
        	dataString.append("{ data: { id: '10.106.252.23', bpiv: '6.419540392' } },\n");
        	dataString.append("{ data: { id: '10.106.61.234', bpiv: '7.961431188' } },\n");
        	dataString.append("{ data: { id: '10.106.75.189', bpiv: '7.964466842' } },\n");
        	dataString.append("{ data: { id: '10.106.75.218', bpiv: '7.956986385' } },\n");
        	dataString.append("{ data: { id: '10.106.61.233', bpiv: '7.95944662' } },\n");
        	dataString.append("{ data: { id: '10.106.59.245', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.59.178', bpiv: '7.257667328' } },\n");
        	dataString.append("{ data: { id: '10.106.57.239', bpiv: '7.257725558' } },\n");
        	dataString.append("{ data: { id: '10.106.55.234', bpiv: '7.962576967' } },\n");
        	dataString.append("{ data: { id: '10.106.59.244', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.57.226', bpiv: '7.257667328' } },\n");
        	dataString.append("{ data: { id: '10.106.57.218', bpiv: '6.118372069' } },\n");
        	dataString.append("{ data: { id: '10.106.57.231', bpiv: '7.257725558' } },\n");
        	dataString.append("{ data: { id: '10.106.248.91', bpiv: '6.80620873' } },\n");
        	dataString.append("{ data: { id: '10.106.253.81', bpiv: '6.598771194' } },\n");
        	dataString.append("{ data: { id: '10.106.57.237', bpiv: '6.118372069' } },\n");
        	dataString.append("{ data: { id: '10.106.248.96', bpiv: '6.588623512' } },\n");
        	dataString.append("{ data: { id: '10.106.60.88', bpiv: '6.935144995' } },\n");
        	dataString.append("{ data: { id: '10.106.57.234', bpiv: '7.257725558' } },\n");
        	dataString.append("{ data: { id: '10.106.248.107', bpiv: '6.426268656' } },\n");
        	dataString.append("{ data: { id: '10.106.246.106', bpiv: '9.038022032' } },\n");
        	dataString.append("{ data: { id: '10.106.57.233', bpiv: '6.118372069' } },\n");
        	dataString.append("{ data: { id: '10.106.246.132', bpiv: '5.491322201' } },\n");
        	dataString.append("{ data: { id: '10.106.61.242', bpiv: '7.964857053' } },\n");
        	dataString.append("{ data: { id: '10.106.63.77', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.57.224', bpiv: '6.118372069' } },\n");
        	dataString.append("{ data: { id: '10.106.79.8', bpiv: '7.927859414' } },\n");
        	dataString.append("{ data: { id: '10.106.75.215', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.75.198', bpiv: '5.271638204' } },\n");
        	dataString.append("{ data: { id: '10.106.248.69', bpiv: '6.595242748' } },\n");
        	dataString.append("{ data: { id: '10.106.246.165', bpiv: '4.048550935' } },\n");
        	dataString.append("{ data: { id: '10.106.74.99', bpiv: '5.832096019' } },\n");
        	dataString.append("{ data: { id: '10.106.248.101', bpiv: '6.629004675' } },\n");
        	dataString.append("{ data: { id: '10.106.61.238', bpiv: '7.961209936' } },\n");
        	dataString.append("{ data: { id: '10.106.59.242', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.246.83', bpiv: '9.037194546' } },\n");
        	dataString.append("{ data: { id: '10.106.245.17', bpiv: '6.445383461' } },\n");
        	dataString.append("{ data: { id: '10.106.55.237', bpiv: '7.962445322' } },\n");
        	dataString.append("{ data: { id: '10.106.59.176', bpiv: '7.257667328' } },\n");
        	dataString.append("{ data: { id: '10.106.57.219', bpiv: '7.257725558' } },\n");
        	dataString.append("{ data: { id: '10.106.57.223', bpiv: '7.257667328' } },\n");
        	dataString.append("{ data: { id: '10.106.246.71', bpiv: '7.267913903' } },\n");
        	dataString.append("{ data: { id: '10.106.246.78', bpiv: '4.04578686' } },\n");
        	dataString.append("{ data: { id: '10.106.246.133', bpiv: '6.235153852' } },\n");
        	dataString.append("{ data: { id: '10.106.57.216', bpiv: '7.257725558' } },\n");
        	dataString.append("{ data: { id: '10.106.246.75', bpiv: '4.046369106' } },\n");
        	dataString.append("{ data: { id: '10.106.62.247', bpiv: '7.963100016' } },\n");
        	dataString.append("{ data: { id: '10.106.56.115', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.61.241', bpiv: '7.964727118' } },\n");
        	dataString.append("{ data: { id: '10.106.63.74', bpiv: '7.962970776' } },\n");
        	dataString.append("{ data: { id: '10.106.248.108', bpiv: '6.423460403' } },\n");
        	dataString.append("{ data: { id: '10.106.248.70', bpiv: '6.59338192' } },\n");
        	dataString.append("{ data: { id: '10.106.75.243', bpiv: '7.957453435' } },\n");
        	dataString.append("{ data: { id: '10.106.70.199', bpiv: '6.835190776' } },\n");
        	dataString.append("{ data: { id: '10.106.61.247', bpiv: '7.965504687' } },\n");
        	dataString.append("{ data: { id: '10.106.57.220', bpiv: '7.257667328' } },\n");
        	dataString.append("{ data: { id: '10.106.246.171', bpiv: '5.942306607' } },\n");
        	dataString.append("{ data: { id: '10.101.0.242', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.61.252', bpiv: '7.96171515' } },\n");
        	dataString.append("{ data: { id: '10.106.75.216', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.246.131', bpiv: '5.640535552' } },\n");
        	dataString.append("{ data: { id: '10.106.55.238', bpiv: '7.963600291' } },\n");
        	dataString.append("{ data: { id: '10.106.57.230', bpiv: '6.118372069' } },\n");
        	dataString.append("{ data: { id: '10.106.75.210', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.253.68', bpiv: '6.590819333' } },\n");
        	dataString.append("{ data: { id: '10.106.61.221', bpiv: '6.935871805' } },\n");
        	dataString.append("{ data: { id: '10.106.61.232', bpiv: '7.959582077' } },\n");
        	dataString.append("{ data: { id: '10.106.57.222', bpiv: '7.257725558' } },\n");
        	dataString.append("{ data: { id: '10.106.75.236', bpiv: '7.95712369' } },\n");
        	dataString.append("{ data: { id: '10.106.75.201', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.75.209', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.61.253', bpiv: '7.962138526' } },\n");
        	dataString.append("{ data: { id: '10.106.60.131', bpiv: '7.426305816' } },\n");
        	dataString.append("{ data: { id: '10.106.75.200', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.61.249', bpiv: '7.961608626' } },\n");
        	dataString.append("{ data: { id: '10.106.63.75', bpiv: '7.963232738' } },\n");
        	dataString.append("{ data: { id: '10.106.245.10', bpiv: '6.441574101' } },\n");
        	dataString.append("{ data: { id: '10.106.75.208', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.246.85', bpiv: '' } },\n");
        	dataString.append("{ data: { id: '10.106.57.229', bpiv: '7.257667328' } },\n");
        	dataString.append("{ data: { id: '10.106.247.246', bpiv: '6.288270908' } },\n");

        	// convert the StringBuilder object to a string
        	elementsData = dataString.toString();

        	//System.out.println("in topologyCoseChart - elementsData = " + elementsData);
        	
        	// reset the StringBuilder object to be empty
        	dataString.setLength(0);

        	// add the edges (connections) to the StringBuilder object
        	dataString.append("{ data: { id: '15', source: '10.101.0.238', target: '10.106.23.39' } },\n");
        	dataString.append("{ data: { id: '16', source: '10.101.0.238', target: '10.106.23.41' } },\n");
        	dataString.append("{ data: { id: '17', source: '10.101.0.238', target: '10.106.245.10' } },\n");
        	dataString.append("{ data: { id: '18', source: '10.101.0.238', target: '10.106.245.12' } },\n");
        	dataString.append("{ data: { id: '19', source: '10.101.0.238', target: '10.106.245.17' } },\n");
        	dataString.append("{ data: { id: '20', source: '10.101.0.242', target: '10.106.245.253' } },\n");
        	dataString.append("{ data: { id: '21', source: '10.101.0.238', target: '10.106.246.101' } },\n");
        	dataString.append("{ data: { id: '22', source: '10.101.0.238', target: '10.106.246.106' } },\n");
        	dataString.append("{ data: { id: '23', source: '10.101.0.238', target: '10.106.246.107' } },\n");
        	dataString.append("{ data: { id: '24', source: '10.101.0.238', target: '10.106.246.131' } },\n");
        	dataString.append("{ data: { id: '25', source: '10.101.0.238', target: '10.106.246.132' } },\n");
        	dataString.append("{ data: { id: '26', source: '10.101.0.238', target: '10.106.246.133' } },\n");
        	dataString.append("{ data: { id: '27', source: '10.101.0.238', target: '10.106.246.161' } },\n");
        	dataString.append("{ data: { id: '28', source: '10.101.0.238', target: '10.106.246.163' } },\n");
        	dataString.append("{ data: { id: '29', source: '10.101.0.238', target: '10.106.246.165' } },\n");
        	dataString.append("{ data: { id: '30', source: '10.101.0.238', target: '10.106.246.171' } },\n");
        	dataString.append("{ data: { id: '31', source: '10.101.0.238', target: '10.106.246.71' } },\n");
        	dataString.append("{ data: { id: '32', source: '10.101.0.238', target: '10.106.246.75' } },\n");
        	dataString.append("{ data: { id: '33', source: '10.101.0.238', target: '10.106.246.76' } },\n");
        	dataString.append("{ data: { id: '34', source: '10.101.0.238', target: '10.106.246.77' } },\n");
        	dataString.append("{ data: { id: '35', source: '10.101.0.238', target: '10.106.246.78' } },\n");
        	dataString.append("{ data: { id: '36', source: '10.101.0.238', target: '10.106.246.83' } },\n");
        	dataString.append("{ data: { id: '37', source: '10.101.0.238', target: '10.106.246.84' } },\n");
        	dataString.append("{ data: { id: '38', source: '10.101.0.238', target: '10.106.246.85' } },\n");
        	dataString.append("{ data: { id: '39', source: '10.101.0.242', target: '10.106.246.86' } },\n");
        	dataString.append("{ data: { id: '40', source: '10.101.0.238', target: '10.106.247.246' } },\n");
        	dataString.append("{ data: { id: '41', source: '10.101.0.238', target: '10.106.248.100' } },\n");
        	dataString.append("{ data: { id: '42', source: '10.101.0.238', target: '10.106.248.101' } },\n");
        	dataString.append("{ data: { id: '43', source: '10.101.0.238', target: '10.106.248.106' } },\n");
        	dataString.append("{ data: { id: '44', source: '10.101.0.238', target: '10.106.248.107' } },\n");
        	dataString.append("{ data: { id: '45', source: '10.101.0.238', target: '10.106.248.108' } },\n");
        	dataString.append("{ data: { id: '46', source: '10.101.0.238', target: '10.106.248.125' } },\n");
        	dataString.append("{ data: { id: '47', source: '10.101.0.242', target: '10.106.248.126' } },\n");
        	dataString.append("{ data: { id: '48', source: '10.101.0.238', target: '10.106.248.127' } },\n");
        	dataString.append("{ data: { id: '49', source: '10.101.0.242', target: '10.106.248.130' } },\n");
        	dataString.append("{ data: { id: '50', source: '10.101.0.238', target: '10.106.248.132' } },\n");
        	dataString.append("{ data: { id: '51', source: '10.101.0.238', target: '10.106.248.133' } },\n");
        	dataString.append("{ data: { id: '52', source: '10.101.0.238', target: '10.106.248.248' } },\n");
        	dataString.append("{ data: { id: '53', source: '10.101.0.238', target: '10.106.248.69' } },\n");
        	dataString.append("{ data: { id: '54', source: '10.101.0.238', target: '10.106.248.70' } },\n");
        	dataString.append("{ data: { id: '55', source: '10.101.0.238', target: '10.106.248.89' } },\n");
        	dataString.append("{ data: { id: '56', source: '10.101.0.242', target: '10.106.248.91' } },\n");
        	dataString.append("{ data: { id: '57', source: '10.101.0.238', target: '10.106.248.95' } },\n");
        	dataString.append("{ data: { id: '58', source: '10.101.0.238', target: '10.106.248.96' } },\n");
        	dataString.append("{ data: { id: '59', source: '10.101.0.238', target: '10.106.248.97' } },\n");
        	dataString.append("{ data: { id: '60', source: '10.101.0.242', target: '10.106.248.98' } },\n");
        	dataString.append("{ data: { id: '61', source: '10.101.0.238', target: '10.106.252.208' } },\n");
        	dataString.append("{ data: { id: '62', source: '10.101.0.242', target: '10.106.252.217' } },\n");
        	dataString.append("{ data: { id: '63', source: '10.101.0.238', target: '10.106.252.218' } },\n");
        	dataString.append("{ data: { id: '64', source: '10.101.0.238', target: '10.106.252.220' } },\n");
        	dataString.append("{ data: { id: '65', source: '10.101.0.238', target: '10.106.252.23' } },\n");
        	dataString.append("{ data: { id: '66', source: '10.101.0.238', target: '10.106.253.68' } },\n");
        	dataString.append("{ data: { id: '67', source: '10.101.0.238', target: '10.106.253.80' } },\n");
        	dataString.append("{ data: { id: '68', source: '10.101.0.238', target: '10.106.253.81' } },\n");
        	dataString.append("{ data: { id: '69', source: '10.101.0.238', target: '10.106.55.234' } },\n");
        	dataString.append("{ data: { id: '70', source: '10.101.0.238', target: '10.106.55.235' } },\n");
        	dataString.append("{ data: { id: '71', source: '10.101.0.238', target: '10.106.55.237' } },\n");
        	dataString.append("{ data: { id: '72', source: '10.101.0.242', target: '10.106.55.238' } },\n");
        	dataString.append("{ data: { id: '73', source: '10.101.0.238', target: '10.106.56.102' } },\n");
        	dataString.append("{ data: { id: '74', source: '10.101.0.238', target: '10.106.56.115' } },\n");
        	dataString.append("{ data: { id: '75', source: '10.101.0.238', target: '10.106.56.87' } },\n");
        	dataString.append("{ data: { id: '76', source: '10.101.0.238', target: '10.106.57.215' } },\n");
        	dataString.append("{ data: { id: '77', source: '10.101.0.238', target: '10.106.57.216' } },\n");
        	dataString.append("{ data: { id: '78', source: '10.101.0.238', target: '10.106.57.217' } },\n");
        	dataString.append("{ data: { id: '79', source: '10.101.0.238', target: '10.106.57.218' } },\n");
        	dataString.append("{ data: { id: '80', source: '10.101.0.242', target: '10.106.57.219' } },\n");
        	dataString.append("{ data: { id: '81', source: '10.101.0.238', target: '10.106.57.220' } },\n");
        	dataString.append("{ data: { id: '82', source: '10.101.0.238', target: '10.106.57.221' } },\n");
        	dataString.append("{ data: { id: '83', source: '10.101.0.238', target: '10.106.57.222' } },\n");
        	dataString.append("{ data: { id: '84', source: '10.101.0.242', target: '10.106.57.223' } },\n");
        	dataString.append("{ data: { id: '85', source: '10.101.0.242', target: '10.106.57.224' } },\n");
        	dataString.append("{ data: { id: '86', source: '10.101.0.238', target: '10.106.57.225' } },\n");
        	dataString.append("{ data: { id: '87', source: '10.101.0.242', target: '10.106.57.226' } },\n");
        	dataString.append("{ data: { id: '88', source: '10.101.0.242', target: '10.106.57.227' } },\n");
        	dataString.append("{ data: { id: '89', source: '10.101.0.242', target: '10.106.57.228' } },\n");
        	dataString.append("{ data: { id: '90', source: '10.101.0.242', target: '10.106.57.229' } },\n");
        	dataString.append("{ data: { id: '91', source: '10.101.0.238', target: '10.106.57.230' } },\n");
        	dataString.append("{ data: { id: '92', source: '10.101.0.238', target: '10.106.57.231' } },\n");
        	dataString.append("{ data: { id: '93', source: '10.101.0.238', target: '10.106.57.232' } },\n");
        	dataString.append("{ data: { id: '94', source: '10.101.0.238', target: '10.106.57.233' } },\n");
        	dataString.append("{ data: { id: '95', source: '10.101.0.238', target: '10.106.57.234' } },\n");
        	dataString.append("{ data: { id: '96', source: '10.101.0.238', target: '10.106.57.235' } },\n");
        	dataString.append("{ data: { id: '97', source: '10.101.0.238', target: '10.106.57.237' } },\n");
        	dataString.append("{ data: { id: '98', source: '10.101.0.238', target: '10.106.57.239' } },\n");
        	dataString.append("{ data: { id: '99', source: '10.101.0.238', target: '10.106.59.176' } },\n");
        	dataString.append("{ data: { id: '100', source: '10.101.0.238', target: '10.106.59.177' } },\n");
        	dataString.append("{ data: { id: '101', source: '10.101.0.238', target: '10.106.59.178' } },\n");
        	dataString.append("{ data: { id: '102', source: '10.101.0.242', target: '10.106.59.242' } },\n");
        	dataString.append("{ data: { id: '103', source: '10.101.0.238', target: '10.106.59.243' } },\n");
        	dataString.append("{ data: { id: '104', source: '10.101.0.238', target: '10.106.59.244' } },\n");
        	dataString.append("{ data: { id: '105', source: '10.101.0.242', target: '10.106.59.245' } },\n");
        	dataString.append("{ data: { id: '106', source: '10.101.0.238', target: '10.106.60.124' } },\n");
        	dataString.append("{ data: { id: '107', source: '10.101.0.238', target: '10.106.60.131' } },\n");
        	dataString.append("{ data: { id: '108', source: '10.101.0.238', target: '10.106.60.78' } },\n");
        	dataString.append("{ data: { id: '109', source: '10.101.0.238', target: '10.106.60.87' } },\n");
        	dataString.append("{ data: { id: '110', source: '10.101.0.242', target: '10.106.60.88' } },\n");
        	dataString.append("{ data: { id: '111', source: '10.101.0.242', target: '10.106.61.221' } },\n");
        	dataString.append("{ data: { id: '112', source: '10.101.0.238', target: '10.106.61.222' } },\n");
        	dataString.append("{ data: { id: '113', source: '10.101.0.238', target: '10.106.61.224' } },\n");
        	dataString.append("{ data: { id: '114', source: '10.101.0.242', target: '10.106.61.226' } },\n");
        	dataString.append("{ data: { id: '115', source: '10.101.0.238', target: '10.106.61.227' } },\n");
        	dataString.append("{ data: { id: '116', source: '10.101.0.238', target: '10.106.61.229' } },\n");
        	dataString.append("{ data: { id: '117', source: '10.101.0.238', target: '10.106.61.231' } },\n");
        	dataString.append("{ data: { id: '118', source: '10.101.0.242', target: '10.106.61.232' } },\n");
        	dataString.append("{ data: { id: '119', source: '10.101.0.242', target: '10.106.61.233' } },\n");
        	dataString.append("{ data: { id: '120', source: '10.101.0.238', target: '10.106.61.234' } },\n");
        	dataString.append("{ data: { id: '121', source: '10.101.0.238', target: '10.106.61.235' } },\n");
        	dataString.append("{ data: { id: '122', source: '10.101.0.238', target: '10.106.61.236' } },\n");
        	dataString.append("{ data: { id: '123', source: '10.101.0.238', target: '10.106.61.237' } },\n");
        	dataString.append("{ data: { id: '124', source: '10.101.0.242', target: '10.106.61.238' } },\n");
        	dataString.append("{ data: { id: '125', source: '10.101.0.238', target: '10.106.61.239' } },\n");
        	dataString.append("{ data: { id: '126', source: '10.101.0.238', target: '10.106.61.241' } },\n");
        	dataString.append("{ data: { id: '127', source: '10.101.0.238', target: '10.106.61.242' } },\n");
        	dataString.append("{ data: { id: '128', source: '10.101.0.238', target: '10.106.61.243' } },\n");
        	dataString.append("{ data: { id: '129', source: '10.101.0.242', target: '10.106.61.244' } },\n");
        	dataString.append("{ data: { id: '130', source: '10.101.0.238', target: '10.106.61.245' } },\n");
        	dataString.append("{ data: { id: '131', source: '10.101.0.238', target: '10.106.61.246' } },\n");
        	dataString.append("{ data: { id: '132', source: '10.101.0.242', target: '10.106.61.247' } },\n");
        	dataString.append("{ data: { id: '133', source: '10.101.0.242', target: '10.106.61.248' } },\n");
        	dataString.append("{ data: { id: '134', source: '10.101.0.238', target: '10.106.61.249' } },\n");
        	dataString.append("{ data: { id: '135', source: '10.101.0.238', target: '10.106.61.250' } },\n");
        	dataString.append("{ data: { id: '136', source: '10.101.0.238', target: '10.106.61.251' } },\n");
        	dataString.append("{ data: { id: '137', source: '10.101.0.238', target: '10.106.61.252' } },\n");
        	dataString.append("{ data: { id: '138', source: '10.101.0.238', target: '10.106.61.253' } },\n");
        	dataString.append("{ data: { id: '139', source: '10.101.0.238', target: '10.106.62.213' } },\n");
        	dataString.append("{ data: { id: '140', source: '10.101.0.238', target: '10.106.62.241' } },\n");
        	dataString.append("{ data: { id: '141', source: '10.101.0.238', target: '10.106.62.247' } },\n");
        	dataString.append("{ data: { id: '142', source: '10.101.0.238', target: '10.106.63.73' } },\n");
        	dataString.append("{ data: { id: '143', source: '10.101.0.238', target: '10.106.63.74' } },\n");
        	dataString.append("{ data: { id: '144', source: '10.101.0.238', target: '10.106.63.75' } },\n");
        	dataString.append("{ data: { id: '145', source: '10.101.0.242', target: '10.106.63.76' } },\n");
        	dataString.append("{ data: { id: '146', source: '10.101.0.238', target: '10.106.63.77' } },\n");
        	dataString.append("{ data: { id: '147', source: '10.101.0.242', target: '10.106.70.199' } },\n");
        	dataString.append("{ data: { id: '148', source: '10.101.0.238', target: '10.106.74.10' } },\n");
        	dataString.append("{ data: { id: '149', source: '10.101.0.238', target: '10.106.74.12' } },\n");
        	dataString.append("{ data: { id: '150', source: '10.101.0.238', target: '10.106.74.135' } },\n");
        	dataString.append("{ data: { id: '151', source: '10.101.0.242', target: '10.106.74.170' } },\n");
        	dataString.append("{ data: { id: '152', source: '10.101.0.238', target: '10.106.74.23' } },\n");
        	dataString.append("{ data: { id: '153', source: '10.101.0.238', target: '10.106.74.245' } },\n");
        	dataString.append("{ data: { id: '154', source: '10.101.0.238', target: '10.106.74.246' } },\n");
        	dataString.append("{ data: { id: '155', source: '10.101.0.242', target: '10.106.74.248' } },\n");
        	dataString.append("{ data: { id: '156', source: '10.101.0.238', target: '10.106.74.249' } },\n");
        	dataString.append("{ data: { id: '157', source: '10.101.0.238', target: '10.106.74.250' } },\n");
        	dataString.append("{ data: { id: '158', source: '10.101.0.238', target: '10.106.74.251' } },\n");
        	dataString.append("{ data: { id: '159', source: '10.101.0.238', target: '10.106.74.252' } },\n");
        	dataString.append("{ data: { id: '160', source: '10.101.0.238', target: '10.106.74.29' } },\n");
        	dataString.append("{ data: { id: '161', source: '10.101.0.238', target: '10.106.74.52' } },\n");
        	dataString.append("{ data: { id: '162', source: '10.101.0.242', target: '10.106.74.99' } },\n");
        	dataString.append("{ data: { id: '163', source: '10.101.0.238', target: '10.106.75.189' } },\n");
        	dataString.append("{ data: { id: '164', source: '10.101.0.238', target: '10.106.75.190' } },\n");
        	dataString.append("{ data: { id: '165', source: '10.101.0.238', target: '10.106.75.191' } },\n");
        	dataString.append("{ data: { id: '166', source: '10.101.0.238', target: '10.106.75.192' } },\n");
        	dataString.append("{ data: { id: '167', source: '10.101.0.238', target: '10.106.75.197' } },\n");
        	dataString.append("{ data: { id: '168', source: '10.101.0.238', target: '10.106.75.198' } },\n");
        	dataString.append("{ data: { id: '169', source: '10.101.0.238', target: '10.106.75.200' } },\n");
        	dataString.append("{ data: { id: '170', source: '10.101.0.242', target: '10.106.75.201' } },\n");
        	dataString.append("{ data: { id: '171', source: '10.101.0.242', target: '10.106.75.202' } },\n");
        	dataString.append("{ data: { id: '172', source: '10.101.0.238', target: '10.106.75.203' } },\n");
        	dataString.append("{ data: { id: '173', source: '10.101.0.238', target: '10.106.75.204' } },\n");
        	dataString.append("{ data: { id: '174', source: '10.101.0.238', target: '10.106.75.205' } },\n");
        	dataString.append("{ data: { id: '175', source: '10.101.0.238', target: '10.106.75.206' } },\n");
        	dataString.append("{ data: { id: '176', source: '10.101.0.238', target: '10.106.75.208' } },\n");
        	dataString.append("{ data: { id: '177', source: '10.101.0.238', target: '10.106.75.209' } },\n");
        	dataString.append("{ data: { id: '178', source: '10.101.0.238', target: '10.106.75.210' } },\n");
        	dataString.append("{ data: { id: '179', source: '10.101.0.242', target: '10.106.75.211' } },\n");
        	dataString.append("{ data: { id: '180', source: '10.101.0.242', target: '10.106.75.212' } },\n");
        	dataString.append("{ data: { id: '181', source: '10.101.0.238', target: '10.106.75.213' } },\n");
        	dataString.append("{ data: { id: '182', source: '10.101.0.238', target: '10.106.75.214' } },\n");
        	dataString.append("{ data: { id: '183', source: '10.101.0.238', target: '10.106.75.215' } },\n");
        	dataString.append("{ data: { id: '184', source: '10.101.0.238', target: '10.106.75.216' } },\n");
        	dataString.append("{ data: { id: '185', source: '10.101.0.238', target: '10.106.75.218' } },\n");
        	dataString.append("{ data: { id: '186', source: '10.101.0.242', target: '10.106.75.221' } },\n");
        	dataString.append("{ data: { id: '187', source: '10.101.0.238', target: '10.106.75.236' } },\n");
        	dataString.append("{ data: { id: '188', source: '10.101.0.242', target: '10.106.75.240' } },\n");
        	dataString.append("{ data: { id: '189', source: '10.101.0.238', target: '10.106.75.241' } },\n");
        	dataString.append("{ data: { id: '190', source: '10.101.0.242', target: '10.106.75.242' } },\n");
        	dataString.append("{ data: { id: '191', source: '10.101.0.238', target: '10.106.75.243' } },\n");
        	dataString.append("{ data: { id: '192', source: '10.101.0.238', target: '10.106.75.248' } },\n");
        	dataString.append("{ data: { id: '193', source: '10.101.0.238', target: '10.106.75.250' } },\n");
        	dataString.append("{ data: { id: '194', source: '10.101.0.242', target: '10.106.79.8' } },\n");
        	dataString.append("{ data: { id: '195', source: '10.101.0.242', target: '10.106.79.9' } },\n");

        	// convert the StringBuilder object to a string
        	edgesData = dataString.toString();
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
    public String getTopologyCoseChartElementsData() {
    	return elementsData;
    }

    public String getTopologyCoseChartEdgesData() {
    	return edgesData;
    }

}
