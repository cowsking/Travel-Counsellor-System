package com.example.TravelCounsellingSystem;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import login.RegistrationHelper;

public class RegistrationHelperTest {

	
	
	private RegistrationHelper helper;
	private String goodPassword1;

	private String badPassword1;
	private String badPassword2;
	private String badPassword3;
	private String badPassword4;
	private String badPassword5;
	private String badPassword6;

	public String getStudentID() {
		return("201377035");
		
	}
	
	
	
	@Before
	public void setUp() throws Exception {
		helper=new RegistrationHelper();

		goodPassword1="ABcdef123456!";

		badPassword1="ABcdef123456";// missing special char
		badPassword2="defgh123456!";//no upper
		badPassword3="ABCDE123456!";//no lower
		badPassword4="ABCDEABCdef!";//no digit
		badPassword5="Ad1234!";//too short
		badPassword6=null;//null
	}

	@After
	public void tearDown() throws Exception {
		helper=null;
	}
	
	


	
	@Test
	public void test() {
		assertTrue("Checking good password1",helper.checkUsernamePassword(goodPassword1));//check special char
		assertTrue("Checking bad password1",!helper.checkUsernamePassword(badPassword1));//check special char
		assertTrue("Checking bad password2",!helper.checkUsernamePassword(badPassword2));//check uppercase letter
		assertTrue("Checking bad password3",!helper.checkUsernamePassword(badPassword3));//check lowercase letter
		assertTrue("Checking bad password4",!helper.checkUsernamePassword(badPassword4));//check digit
		assertTrue("Checking bad password5",!helper.checkUsernamePassword(badPassword5));//check length<8
		assertTrue("Checking bad password6",!helper.checkUsernamePassword(badPassword6));//check null
		
	}

}
