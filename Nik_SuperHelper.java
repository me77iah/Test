package Utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import com.ibm.dtfj.image.javacore.BuilderData;
import com.rational.test.ft.ObjectIsDisposedException;
import com.rational.test.ft.ObjectNotFoundException;
import com.rational.test.ft.SubitemNotFoundException;
import com.rational.test.ft.TargetGoneException;
import com.rational.test.ft.object.interfaces.BrowserTestObject;
import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.RootTestObject;
import com.rational.test.ft.object.interfaces.SelectGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.StatelessGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.ToggleTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestDataElementList;
import com.rational.test.ft.vp.ITestDataList;
import com.rational.test.ft.vp.ITestDataTable;
import Utilities.*;

/**
 * Description   : Super class for script helper
 * 
 * @since  2012/09/04
 * @author flintn
 */


@SuppressWarnings("unchecked")
public class SuperHelper extends RationalTestScript
{
	
	//Global Variable declaration section 
	
	public
	
	GuiTestObject PageObj = null;
	GuiTestObject TextFieldObj = null;
	GuiTestObject ButtonObj = null;
	GuiTestObject ImageObj = null;
	SelectGuiSubitemTestObject ListBoxObj = null;
	GuiTestObject LinkObj = null;
	GuiTestObject TableObj = null;
	GuiTestObject LabelObj = null;
	ToggleTestObject CheckBoxObj = null;
	ToggleGUITestObject RadioObj = null;
	
	

	
	
	public static HashMap DataObj = new HashMap();
	public SuperHelper(){}


	//####################################################################################################
	// Start of building dynamic application objects section
	
	public SuperHelper page(String properties) {
		if (!(PageExists(properties)))
			logTestResult("Specified page ''" + properties
					+ "'' does NOT exist", false);
		return this;

	}

	public GuiTestObject DialogButton(String properties) {

		GuiTestObject DynoObj = BuildDynoObj("", properties);

		// GuiTestObject DynoObj = BuilDynoObj("Html.DialogButton",properties);
		if (!(objExists(DynoObj, 10)))
			logTestResult("specified DialogButton ''" + properties
					+ "'' does not exist", false);
		else
			return DynoObj;
		return null;

	}
	
	public GuiTestObject BuildDynoObj(String ClassType, String Properties) {
		String[] proplist = Properties.split(";");
		TestObject[] to = null;
		if (Instr(ClassType, "Dialog")) {
			RootTestObject root = getRootTestObject();
			to = root.find(atDescendant(".class", ClassType));
		}

		else
			to = (PageObj.find(atDescendant(".class", ClassType)));

		// If there are more than one property sets then build the dynamic
		// object accordingly
		if (Instr(Properties, ";")) {
			switch ((proplist.length) - 1) {
			case 1:
				for (int i = 0; i < to.length; i++) {
					GuiTestObject to1 = new GuiTestObject(to[i]);
					if ((to1.getProperty("."
							+ GetPropValue(proplist[0])[0].trim())
							.equals(GetPropValue(proplist[0])[1].trim()))
							&& (to1.getProperty("."
									+ GetPropValue(proplist[1])[0].trim())
									.equals(GetPropValue(proplist[1])[1].trim())))
						return to1;
				}
				break;
			case 2:
				for (int i = 0; i < to.length; i++) {
					GuiTestObject to1 = new GuiTestObject(to[i]);
					if ((to1.getProperty("."
							+ GetPropValue(proplist[0])[0].trim())
							.equals(GetPropValue(proplist[0])[1].trim()))
							&& (to1.getProperty("."
									+ GetPropValue(proplist[1])[0].trim())
									.equals(GetPropValue(proplist[1])[1].trim()))
							&& (to1.getProperty("."
									+ GetPropValue(proplist[2])[0].trim())
									.equals(GetPropValue(proplist[2])[1].trim())))
						return to1;
				}
			}
		} else if (Instr(Properties, ":=")) {
			for (int i = 0; i < to.length; i++) {
				GuiTestObject to1 = new GuiTestObject(to[i]);
				if (to1.getProperty("." + GetPropValue(Properties)[0].trim())
						.equals(GetPropValue(Properties)[1].trim()))
					return to1;
			}
		}

		return null;
	}
	
	private String[] GetPropValue(String propset) {
		return (propset.split(":="));
	}


	public boolean PageExists(String Pagetitle) {
		int count = 0;
		try {
			RootTestObject root = getRootTestObject();
			TestObject[] to = root.find(atDescendant(".class",
					"Html.HtmlDocument"));
			int len = to.length;
			for (int i = 0; i < len; i++) {
				GuiSubitemTestObject to1 = new GuiSubitemTestObject(to[i]);
				if (Instr(to1.getProperty("title").toString(), Pagetitle)) {
					DataObj.put("Page" + i, to1);
					PageObj = to1;
					count++;
					if (count > 1)
						logInfo("Found another instance of page with the title"
								+ to1.getProperty("title").toString());

				}
			}
			if (count >= 1) {
				DataObj.put("count", count);
				DataObj.put("Result", true);
				return true;
			} else
				DataObj.put("Result", false);
			return false;
		}

		catch (ObjectNotFoundException e) {
			logTestResult(e.getMessage().toString(), false);

		}
		return false;
	}
	
	
	public boolean Instr(String Mainstring, String Substring) {
		if (Mainstring != "" && Substring != "") {
			if ((Mainstring.indexOf(Substring, 0)) >= 0) {
				// logTestResult("Sub String ''"+SubString+"'' exists in the Main String ''"+MainString+"''",true);
				return true;
			} else {
				// logTestResult("Sub String ''"+SubString+"'' does NOT exists in the Main String ''"+MainString+"''",false);
				return false;
			}
		}
		return false;
	}
			
//*************** TEXT FIELD **************************************

	public TextField TextField(String Properties) {
		GuiTestObject to1 = null;
		TestObject[] to = null;

		if (PageObj != null) {
			if (Instr(Properties, ":=")) {
				to1 = BuildDynoObj("Html.INPUT.text", Properties);

				// if Input text fields couldn't match the description, then
				// search for password text fields
				if (to1 == null)
					to1 = BuildDynoObj("Html.INPUT.password", Properties);

				// Search for Html.TEXTAREA fields if input.text and
				// input.passwords fields are not found with the matched
				// description
				if (to1 == null)
					to1 = BuildDynoObj("Html.TEXTAREA", Properties);

				if (to1 != null) {
					DataObj.put("TextFieldObj", new GuiSubitemTestObject(to1));
				} else {
					logTestResult("TextField Object ''" + Properties
							+ "'' NOT found. Hence stopping the test.", false);
					stop();
				}
			} else {
				to = PageObj.find(atDescendant(".class", "Html.INPUT.text"));
				for (int i = 0; i < to.length; i++) {
					GuiSubitemTestObject to2 = new GuiSubitemTestObject(to[i]);
					if (Instr(to2.getProperty("id").toString(), Properties)) {
						DataObj.put("TextFieldObj", new GuiSubitemTestObject(
								to[i]));
						return new TextField();
					}

					if (Instr(to2.getProperty("name").toString(), Properties)) {
						DataObj.put("TextFieldObj", new GuiSubitemTestObject(
								to[i]));
						return new TextField();
					}
				}
				// if the function does not return the value then search for
		
				to = PageObj
						.find(atDescendant(".class", "Html.INPUT.password"));
				for (int i = 0; i < to.length; i++) {
					GuiSubitemTestObject to2 = new GuiSubitemTestObject(to[i]);
					if (Instr(to2.getProperty("id").toString(), Properties)) {
						DataObj.put("TextFieldObj", new GuiSubitemTestObject(
								to[i]));

						return new TextField();
					}
				}

				to = PageObj.find(atDescendant(".class", "Html.TEXTAREA"));
				for (int i = 0; i < to.length; i++) {
					GuiSubitemTestObject to2 = new GuiSubitemTestObject(to[i]);
					if (Instr(to2.getProperty("id").toString(), Properties)) {
						DataObj.put("TextFieldObj", new GuiSubitemTestObject(
								to[i]));

						return new TextField();
					}
				}

			}
		} else
			stop();

		logTestResult("TextField Object ''" + Properties
				+ "'' NOT found. Hence stopping the test.", false);
		stop();

		return new TextField();
	}
	
	
	
	public TextField TextField(GuiTestObject gto) {
		DataObj.put("TextFieldObj", new GuiSubitemTestObject(gto));
		return new TextField();

	}
	
	
	
	public static boolean objExists(GuiTestObject gto, int sec) {
		int i;
		for (i = 1; i <= sec; i++) {
			if (gto.exists() == true)
				return true;

		}
		return false;
	}

	//*********************** Start of Button **************************
	public Button Button(String Properties) {
		GuiTestObject to1 = null;
		TestObject[] to = null;

		if (PageObj != null) {
			if (Instr(Properties, ":=")) {
				to1 = BuildDynoObj("Html.INPUT.submit", Properties);
				// if Input text fields couldn't match the description, then
				// search for password text fields
				if (to1 == null)
					to1 = BuildDynoObj("Html.INPUT.button", Properties);

				if (to1 != null) {
					DataObj.put("ButtonObj", new GuiTestObject(to1));
					return new Button();
				} else {
					logTestResult("Button Object ''" + Properties
							+ "'' NOT found. Hence stopping the test.", false);
					stop();
				}
			} else {
				to = PageObj.find(atDescendant(".class", "Html.INPUT.submit"));

				if (to == null)
					to = PageObj.find(atDescendant(".class",
							"Html.INPUT.button"));

				for (int i = 0; i < to.length; i++) {
					GuiSubitemTestObject to2 = new GuiSubitemTestObject(to[i]);
					if (Instr(to2.getProperty("name").toString(), Properties)) {
						DataObj.put("ButtonObj", new GuiTestObject(to[i]));
						return new Button();
					}
				}
			}
		} else
			stop();

		logTestResult("Button Object ''" + Properties
				+ "'' NOT found. Hence stopping the test.", false);
		stop();
		return new Button();
	}
	
	public Button Button(GuiTestObject gto) {
		DataObj.put("ButtonObj", new GuiTestObject(gto));
		return new Button();
	}
	
	
	
	//********************** End Of Button ********************
	
	
	// ******************* Start - Links ***************************
	public Link Link(String Properties) {
		GuiTestObject to1 = null;
		TestObject[] to = null;

		
//		RootTestObject rto = getRootTestObject();
//		TestObject[] to = rto.find(atDescendant(".class","Html.A","id","aui_3_4_0_1_557"),false);
//		System.out.println(to.length);
//		GuiTestObject gto = new GuiTestObject(to[0]);
//		gto.click();
		
		
		if (PageObj != null) {
			if (Instr(Properties, ":=")) {
				to1 = BuildDynoObj("Html.A", Properties);

				if (to1 != null) {
					DataObj.put("LinkObj", new GuiTestObject(to1));
					return new Link();
				} else {
					logTestResult("Link Object ''" + Properties
							+ "'' NOT found. Hence stopping the test.", false);
					stop();
				}
			} else {
				to = PageObj.find(atDescendant(".class", "Html.A"));  //Jumps Here
				for (int i = 0; i < to.length; i++) {
					GuiSubitemTestObject to2 = new GuiSubitemTestObject(to[i]);
//					GuiTestObject to2 = new GuiTestObject(to[i]);
					if (Instr(to2.getProperty("innertext").toString(),
							Properties)) {
						DataObj.put("LinkObj", new GuiTestObject(to[i]));
						return new Link();
					}
				}
			}
		} else
			stop();

		logTestResult("Link Object ''" + Properties
				+ "'' NOT found. Hence stopping the test.", false);
		stop();
		return new Link();
	}

	public Link Link(GuiTestObject gto) {
		DataObj.put("LinkObj", new GuiTestObject(gto));
		return new Link();
	}
	
	
	//******************* End - Links *********************
	
		
	//******************* Start - List Boxes *****************
		
	public ListBox ListBox(String Properties) {
		GuiTestObject to1 = null;
		TestObject[] to = null;

		if (PageObj != null) {
			if (Instr(Properties, ":=")) {
				to1 = BuildDynoObj("Html.SELECT", Properties);

				if (to1 != null) {
					DataObj.put("ListBoxObj", new SelectGuiSubitemTestObject(
							to1));
					return new ListBox();
				} else {
					logTestResult("ListBox Object ''" + Properties
							+ "'' NOT found. Hence stopping the test.", false);
					stop();
				}
			} else {
				to = PageObj.find(atDescendant(".class", "Html.SELECT"));
				for (int i = 0; i < to.length; i++) {
					GuiSubitemTestObject to2 = new GuiSubitemTestObject(to[i]);
					if (Instr(to2.getProperty("name").toString(), Properties)) {
						DataObj.put("ListBoxObj",
								new SelectGuiSubitemTestObject(to[i]));
						return new ListBox();
					}
					if (Instr(to2.getProperty("id").toString(), Properties)) {
						DataObj.put("ListBoxObj",
								new SelectGuiSubitemTestObject(to[i]));
						return new ListBox();
					}
				}
			}
		} else
			stop();

		logTestResult("ListBox Object ''" + Properties
				+ "'' NOT found. Hence stopping the test.", false);
		stop();
		return new ListBox();
	}
		
	public ListBox ListBox(GuiTestObject gto)
	{
		DataObj.put("ListBoxObj",new GuiTestObject(gto));
		return new ListBox();
	}
		
		
	
	//******************* End - List Boxes *******************	
		
	
	
	//**************** Extra *************************
	
	public static void CloseAllBrowsers() {
		RootTestObject root = getRootTestObject();
		TestObject[] to = root.find(atDescendant(".class", "Html.HtmlBrowser"));
		int len = to.length;
		for (int i = 0; i < len; i++) {
			BrowserTestObject Topobj = new BrowserTestObject(to[i]);

			try {

				Topobj.getProcess().kill();
				sleep(1);
				len = to.length;
			} catch (TargetGoneException e) {
				logWarning("Exception occurred during close browser,still continuing with the script after 2 seconds wait");
				sleep(2);
			}
		}

	}

	
	// StartBrowser
	
	public void StartBrowser(String sURL) {
		sleep(3);

		startBrowser(sURL);
		BrowserSync();
		sleep(3);

		RootTestObject root = getRootTestObject();
		TestObject[] to = root.find(atDescendant(".class", "Html.HtmlBrowser"));
		TopLevelTestObject TopObj = new TopLevelTestObject(to[0]);
		TopObj.maximize();

	}
	

	
	// Browser Sync
	
	
	public static void BrowserSync() {
		sleep(5);
		RootTestObject root = getRootTestObject();
		int count = 0;
		TestObject[] to = root.find(atDescendant(".class", "Html.HtmlBrowser"));

		int len = to.length;
		if (len > 0) {
			for (int i = 0; i < len; i++) {
				BrowserTestObject Topobj = new BrowserTestObject(to[i]);
				try {
					while (!(Topobj.getProperty(".readyState").toString()
							.equals("4")) && (count < 30)) {
						sleep(1.0);
						count++;
					}
				} catch (ObjectIsDisposedException e) {
					logWarning("ObjectIs DisposedException, still continuing with the script after 2 seconds wait");
					sleep(2);
				}
			}
		}

	}
	
	
//	String CurrDate(int days)
	public static String CurrDate(int days)
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (days==0)
		{
			return (sdf.format(cal.getTime())).toString();
		}
		else
			cal.add(Calendar.DATE, days);
		
		return (sdf.format(cal.getTime())).toString();
	}

	public static String CurrTime()
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return (sdf.format(cal.getTime())).toString();
		
	}

	public static void LogStepInfo(int StepNo)
	{
		logInfo("Step '"+StepNo+"' Step '"+StepNo+"' Step '"+StepNo+"'");
	}
	
	
	public void LogStepInfo(int StepNo, String StepDesc)
	{
		logInfo("Step: "+StepNo+" Desc: "+StepDesc);
	}
	
	
	
	public static void LogCheckInfo(int CheckNo)
	{
		logInfo("Check '"+CheckNo+"' Check '"+CheckNo+"' Check '"+CheckNo+"'");
		
	}
	
	
	public static void LogCheckInfo(int CheckNo, String CheckDesc)
	{
		logInfo("Check: "+CheckNo+" Desc: "+CheckDesc);
	}
	
	
	
	//**************** End of Extra ********************
	
	
}
	
	