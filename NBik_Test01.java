ackage Scripts;
import resources.Scripts.PP_010_I_Test1Helper;
import Utilities.Functions;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Functional Test Script
 * @author flintn
 */
public class PP_010_I_Test1 extends PP_010_I_Test1Helper
{
	/**
	 * Script Name   : <b>PP_010_I_Test1</b>
	 * Generated     : <b>5 Sep 2012 12:37:32</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/09/05`
	 * @author flintn
	 * 
	 * 
	 * > Test Case Id > PP_010_I_Test1		
	 * > Test Description > Administrator Successfully Creates a New Survey
     *
	 * 
	 ******************* ---- PRE-CONDITIONS ---- ************************************************
	 * 
	 * > "Administrator User is Signed In
	 * > Administrator User is Using the Internal EDC URL 
	 * > Administrator User has Permissions to Create a New Survey
	 * > Workgroups 'Respondent Relations', 'Administrators', 'Collection Exercise Coordinators' 
	 * > Plus a 'Test Group' are Available on the System
	 * > System Users Logon Credentials, Other than Administrator or Collections Exercise Coordinator are Required"		
	 *		
     * ----------------------------------------------------------------------------------------------------------------------
	 * 
	 */
	
	
	
	public void testMain(Object[] args) 
	{
		com.rational.test.ft.util.OptionManager.set("rt.interactive",
				new Boolean(false));
		Functions call = new Functions();

		/*
		 * Login application with Administrator Verifies UserName
		 */
		/**
		 * Needs attention !!! Currently does not cover the Administrator Page
		 * format
		 * 
		 */

		CloseAllBrowsers();

		StartBrowser("http://10.27.112.135:7001/");
		// 1 Step Administrator User is on the Administrator Home Page

		// 2 VP System displays Administrator Home Page
		call.Login("test@liferay.com", "ons123");

		// Build User Story 10
		// 3 Step Administrator User Selects the New Survey Option

		sleep(5);
		RootTestObject rto = getRootTestObject();
		TestObject[] to = rto.find(
				atDescendant(".class", "Html.A", ".text", "Survey"), false);
		GuiTestObject gto = new GuiTestObject(to[0]);
		gto.click();

		// 4 VP System displays the Create New Survey page

		/**
		 * This needs TBC
		 * 
		 * 
		 */
		sleep(5);
		page("Welcome")
		.TextField("_surveyportlet1_WAR_edcwmainlatest001SNAPSHOT_survid");
		
		       
		
		// Verify Survey ID TextField is Displayed as Expected
		
		page("Welcome")
				.TextField("_surveyportlet1_WAR_edcwmainlatest001SNAPSHOT_survid");

		// Verify Survey Title TextField is Displayed as Expected
		page("Welcome").TextField(
				"_surveyportlet1_WAR_edcwmainlatest001SNAPSHOT_survtitle");

		// Verify Legal Basis: ListBox is Displayed as Expected
		page("Welcome").ListBox(
				"_surveyportlet1_WAR_edcwmainlatest001SNAPSHOT_surveystatus");

		// Verify WorkGroup: ListBox is Displayed as Expected
		page("Welcome").ListBox("_surveyportlet1_WAR_edcwmainlatest001SNAPSHOT_workgrp");

		// Verify WorkGroup: ListBox is Displayed as Expected
		// Verify Save Button is Displayed as Expected
		page("Welcome").Button("value:=Save");
		
		
//		5		Step	User Enters the Survey ID and Survey Code (Record For Re-use in Test 7)
		
		String sTxt = call.SurveyId().toString();
		page("Welcome").TextField("_surveyportlet1_WAR_edcwmainlatest001SNAPSHOT_survid").SetText(sTxt);
				
		
		
//		6		VP	Survey ID Verified
		String sVP = page("Welcome").TextField("_surveyportlet1_WAR_edcwmainlatest001SNAPSHOT_survid").GetText();
		if(sVP.equalsIgnoreCase(sTxt))
			logTestResult("Step 6 Verification Point PASSED as Expected (Survey ID) ", true);
		else{
			
			logTestResult("Step 6 Verification Point FAILED @ (Survey ID) - Therefore Test will stop @ Test Step 6 (PP_010_I_Test1)", false);
			stop();
		}
			
		
//		7		VP	Survey Title Verified
		String sSurveyTitle = "Auto_SurveyId_123";
		page("Welcome").TextField("_surveyportlet1_WAR_edcwmainlatest001SNAPSHOT_survtitle").SetText(sSurveyTitle);
		
		
		
//		8		Step	User Selects a Workgroup to Associate with the New Survey
		page("Welcome").ListBox("_surveyportlet1_WAR_edcwmainlatest001SNAPSHOT_workgrp").SelectItem("Owner");
		
		
		
//		9		VP	Workgroup Selection Verified
		sVP = page("Welcome").ListBox("_surveyportlet1_WAR_edcwmainlatest001SNAPSHOT_workgrp").GetSelectedTxt(".value").toString();
		
		if(sVP.equalsIgnoreCase("Owner"))
			logTestResult("Step 9 Verification Point PASSED as Expected (Workgroup) ", true);
		else{
			
			logTestResult("Step 9 Verification Point FAILED @ (WorkGroup) - Therefore Test will stop @ Test Step 9 (PP_010_I_Test1)", false);
			stop();
		}
		
//		10		Step	User Sets a Legal Basis (Statutory Survey) Flag
		
		page("Welcome").ListBox(
		"_surveyportlet1_WAR_edcwmainlatest001SNAPSHOT_surveystatus").SelectItem("Voluntary");
		
		
//		11		VP	Legal Basis Flag Set Verified
		sVP = page("Welcome").ListBox("_surveyportlet1_WAR_edcwmainlatest001SNAPSHOT_surveystatus").GetSelectedTxt(".value").toString();
		
		if(sVP.equalsIgnoreCase("Voluntary"))
			logTestResult("Step 11 Verification Point PASSED as Expected (Legal Basis) ", true);
		else{
			
			logTestResult("Step 9 Verification Point FAILED @ (Legal Basis) - Therefore Test will stop @ Test Step 10/11 (PP_010_I_Test1)", false);
			stop();
		}
//		12		VP	Verify The ->Survey Status Table-> Is Populated With: ‘Statistics of Trade Act 1947’, ‘Varies by region’ and ‘Voluntary’ 
		
//		Function tBC
		
		
//		13		Step	User Hits <Enter> or Clicks on <Create Survey> Button
		stop();
		
		sleep(1);
		page("Welcome").Button("value:=Save").Click();
		sleep(4);
		
//		need to verify MSG
		
		
//		14		Step	User Enters the Survey ID and Survey Code
		
		

		/**
		 * TBC
		 */
		
		//		15		VP	Check DataBase for Survey ID and Survey Code

		
		//		16		VP	Confirm Navigation is provided in line with the ‘site map’ and consistent with the documented standard behaviours.
//		17		VP	Home Page Displayed

		
		

		CloseAllBrowsers();
		
		
	}
}
