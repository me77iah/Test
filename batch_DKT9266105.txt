@ECHO OFF
:start
:save_args_as_variables
set RFT_PROJECT_LOCATION=P:\BCAS
set RFT_SCRIPT_NAME=Smoke_Tests.a_Smoke_Test_Driver
set RFT_LOGFILE_NAME=BCAS_Overnight_Smoke_Test_Results
goto check_args
:check_args
if "%RFT_PROJECT_LOCATION%" == "" goto missing_args
if "%RFT_SCRIPT_NAME%" == "" goto missing_args
if "%RFT_LOGFILE_NAME%" == "" goto missing_args
goto args_ok
:args_ok
if "%4" == "silent" goto playback
echo.
echo RFT_PROJECT_LOCATION = %RFT_PROJECT_LOCATION%
echo RFT_SCRIPT_NAME = %RFT_SCRIPT_NAME%
echo RFT_LOGFILE_NAME = %RFT_LOGFILE_NAME%
echo IBM_RATIONAL_RFT_ECLIPSE_DIR = %IBM_RATIONAL_RFT_ECLIPSE_DIR%
echo IBM_RATIONAL_RFT_INSTALL_DIR = %IBM_RATIONAL_RFT_INSTALL_DIR%
echo.
echo Initializing RFT Playback...
:playback
java -classpath "C:\Program Files (x86)\IBM\SDP\FunctionalTester\bin\rational_ft.jar" com.rational.test.ft.rational_ft -datastore %RFT_PROJECT_LOCATION% -playback %RFT_SCRIPT_NAME%
if "%4" == "silent" goto end
echo RFT playback complete.
goto end
:missing_args
echo.
echo ERROR: Invalid syntax! Usage: 
echo RFT_PlayScript ProjectPath ScriptName LogName [silent]
goto end
:end 
