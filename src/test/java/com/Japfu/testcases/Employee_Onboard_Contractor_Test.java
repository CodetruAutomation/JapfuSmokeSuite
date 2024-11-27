package com.Japfu.testcases;

import org.testng.annotations.Test;

import com.Japfu.Base.BaseTest;

public class Employee_Onboard_Contractor_Test extends BaseTest {

	
	 @Test(priority = 1)
	 public void SC_Verify_Adding_A_New_Contractor_With_Missing_Mandatory_Fields() throws Exception {
		 
		 getContractor().verify_Navigating_to_Homepage_Once_click_on_Login();
		 getContractor().CheckForVendor();
		 getContractor().EmployeeInvalidInformation();
		 
	 }
	 
	
	 @Test(priority = 2)
	 public void SC_Verify_Adding_A_New_Contractor_With_Valid_Details() throws Exception {
		 
		 getContractor().Employee_Information();
		 getLogout_page().Logout();
		 
	 }
	 
}
