package Utilities;
import resources.Utilities.FunctionsHelper;
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
import Utilities.*;
/**
 * Description   : Functional Test Script
 * @author flintn
 */
public class Functions extends FunctionsHelper
{
	/**
	 * Script Name   : <b>Function</b>
	 * Generated     : <b>4 Sep 2012 10:16:54</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/09/04
	 * @author flintn
	 */
	public void testMain(Object[] args) 
	{
		
		
	
		}
	
	
	
	// SurveyId Function - retrieve Survey Id prefix AutoTest
	
	

	
	
	public String SurveyId(){
		
		String sNum = CurrTime().toString();
		String s1 = sNum.substring(0, 2).toString();  String s2 = sNum.substring(3, 5).toString(); String s3 =sNum.substring(6, 8).toString();
		
		String sId = "AutoTest"+s1+s2+s3;
		
		return sId;
	}
	
	
public String SurveyName(){
		
		String sNum = CurrTime().toString();
		String s1 = sNum.substring(0, 2).toString();  String s2 = sNum.substring(3, 5).toString(); String s3 =sNum.substring(6, 8).toString();
		
		String sId = "AutoName"+s1+s3+s2;
		
		return sId;
	}
	
	
	
	
	public void Login(String sUsername, String sPassword){
//		CloseAllBrowsers();
		// Username = test@liferay.com
		// Password = ons123
		//  SetText in UserName Field
		page("Welcome").TextField("_58_login").SetText(sUsername);
		
		String sVerify = page("Welcome").TextField("_58_login").GetText().toString();		
		
		if (sVerify.equalsIgnoreCase(sUsername))
			logTestResult("User Name displayed as expected", true);
		else
			logTestResult("User Name is NOT displayed as Expected within the User Name TextField, Expected " +
					"Username Txt = "+sUsername+" Actualal Username Txt = "+sVerify+"", false);
		
		//SetText in Password Field
		page("Welcome").TextField("_58_password").SetText(sPassword);
				
//		Click Login Button
//		page("Welcome").Button("value:=Sign In").Click();
		
		RootTestObject root = getRootTestObject();
		TestObject[] tobut = root.find(atDescendant("class","aui-button-input aui-button-input-submit"),false);
		GuiTestObject gto = new GuiTestObject(tobut[0]);
		gto.click();
		
		
		
		
		
		RootTestObject rto = getRootTestObject();
		TestObject[] to = rto.find(atDescendant(".class","Html.INPUT.submit",".value","Sign In"),false);
		int i = 0;
		while (to.length>=1& i <=100)
		{
		sleep(1);
		to = rto.find(atDescendant(".class","Html.INPUT.submit",".value","Sign In"),false);
		i++;
		}
		if(to.length==0)
		{
			sleep(5);
		}
	}
	
//	public String SurveyId ()
//	{
//	
//	
//	String s = CurrTime();	
//	String s1 = s.substring(0, 2).toString();
//	String s2 = s.subSequence(3, 5).toString();
//	String s3 = s.subSequence(6, 8).toString();
//	String sVal = s1+s2+s3;
//		
//	return("Auto"+"_"+CurrDate(1)+"_"+sVal);
//	}

	
	public void dropdown_LegalBasis_selectItem(String sItem){
		
		
		RootTestObject root = getRootTestObject();
		TestObject[] to = root.find(atDescendant("name","A1064:l1:c1:f1:fs1:c1a:legalBasisId_editableInput"),false);
		GuiTestObject gto = new GuiTestObject(to[0]);
		String sItemVal = gto.getProperty(".value").toString();
		if (sItem.equalsIgnoreCase(sItemVal)){
			logTestResult("Value within Drop Down was already Selected and theerefore skipping Selection", true);
			}

		else
		{
			
			gto.click();
		
			root = getRootTestObject();
			to = root.find(atDescendant("class","aui-column","id","A1064:l1:c1:f1:fs1:c1a"),false);
			while (to.length!=1){
				sleep(0.5);
				int iwait = 1;
				if(iwait==30){
					to[0].unregister();
					stop();
						}
				iwait++;
				}
			

			root = getRootTestObject();
			to = root.find(atDescendant("id","A1064:l1:c1:f1:fs1:c1a:legalBasisId_panel"),false);
					
			TestObject[] to1 = to[0].find(atDescendant("class","ui-selectonemenu-item ui-selectonemenu-list-item ui-corner-all",".text",sItem),false);
			gto = new GuiTestObject(to1[0]);
			gto.click();
			
			to[0].unregister();
			to1[0].unregister();
			gto.unregister();
		
	}
	}
	
	
}