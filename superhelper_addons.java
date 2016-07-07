		
		
		// 
		//     "String Data to extract substring""   "Textual Starting Marker "  "Textual Ending Marker" "iImdex Index for use if duplicate starting markers" 
		public static String subString(String sStringData, String sStartName, String sEndName, int iIndex)
		
		{
			//------------------------------------------------------------
			// Sub String Extraction                        
			//------------------------------------------------------------
			// DESTRUCTIVE TO UNDERLYING STRING
			
			// FIND FROM "sStartName">
			String sInterMediateData = sStringData.split(sStartName);
			// Assign Above Sub String To String sInterMediateData               
			
			sStartName = sInterMediateData[iIndex];
			// Grab SubString From Index (iIndex) to Occurrence Of "sEndName" In String 		
			
			// FIND TO <"sEndName"
			int iLength = (sStartName.indexOf(sEndName));
			
			// Truncate End Pre-Marker 1 ("Additional members ") and Start Post_Marker 2 (sEndName)
			//------------------------------------------------------------
			sStartName = sStartName.substring(0, iLength);
			//------------------------------------------------------------

			//String sAdditionalMembers = sFromThanks[1];
			System.out.println("Start Name = "+sStartName); 
			//System.out.println("sStartName = "+ i);
			
			return (sStartName).toString();
		}


		
		
		
		
		
		
		// REPLACE THE 4 METHODS BELOW WITH 1
		
		//                  "iDMY days in future"   "sDYM is it? days, months, years" - "Format of returned date - dd MMM yyyy, dd/MM/yyyy, MM, dd, MMM"
				public static String Ubi_Date(int iDMY, String sDMY, String sDMY_Format) // As Current Date But Different :-) 
		{                                         // Format As Presented On HMI
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(sDMY_Format);
			if (iDMY==0) // iDMY = Number of Days, Months or Years in the Future
			{
				return (sdf.format(cal.getTime())).toString();
			}
			else
				cal.add(Calendar.DATE, sDMY); // sDMY = Unit = days, months, years
			
			return (sdf.format(cal.getTime())).toString();
		}

		
		
/* 		//-------------------------------------------------------------------------
		// SCREEN DATE            - START  ----------------------------------------
		public static String ScreenDate(int days) // As Current Date But Different :-) 
		{                                         // Format As Presented On HMI
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
			if (days==0)
			{
				return (sdf.format(cal.getTime())).toString();
			}
			else
				cal.add(Calendar.DATE, days);
			
			return (sdf.format(cal.getTime())).toString();
		}
		// SCREEN DATE            - END  ------------------------------------------
		//-------------------------------------------------------------------------
		//-------------------------------------------------------------------------		
		//-------------------------------------------------------------------------
		// CURRENT DATE           - START  ----------------------------------------
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
		// CURRENT DATE           - END  ------------------------------------------
		//-------------------------------------------------------------------------
		//-------------------------------------------------------------------------
		// CURRENT MONTH          - START  ----------------------------------------
		public static String CurrMonth(int months)
		{
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("MM");
			if (months==0)
			{
				return (sdf.format(cal.getTime())).toString();
			}
			else
				cal.add(Calendar.DATE, months);
			
			return (sdf.format(cal.getTime())).toString();
		}
		// CURRENT MONTH          - END  ------------------------------------------
		//-------------------------------------------------------------------------
		//-------------------------------------------------------------------------
		// CURRENT DAY            - START  ----------------------------------------
		public static String CurrDay(int days)
		{
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd");
			if (days==0)
			{
				return (sdf.format(cal.getTime())).toString();
			}
			else
				cal.add(Calendar.DATE, days);
			
			return (sdf.format(cal.getTime())).toString();
		}
		// CURRENT DATE           - END  ------------------------------------------ */