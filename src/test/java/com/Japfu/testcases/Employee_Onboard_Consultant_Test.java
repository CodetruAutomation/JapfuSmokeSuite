package com.Japfu.testcases;

import org.testng.annotations.Test;

import com.Japfu.Base.BaseTest;

public class Employee_Onboard_Consultant_Test extends BaseTest {

	
	 @Test(priority = 1)
	 public void SC_Verify_Adding_A_New_Consultant_With_Missing_Mandatory_Fields() throws Exception {
		// getConsultant().verify_Navigating_to_Homepage_Once_click_on_Login();
		 getConsultant().EmployeeInvalidInformation();
		 
	 }
	 
	
	 @Test(priority = 2)
	 public void SC_Verify_Adding_A_New_Consultant_With_Valid_Details() throws Exception {
		 
		 getConsultant().Employee_Information();
		// getLogout_page().Logout();
		 
	 }
	 
}
