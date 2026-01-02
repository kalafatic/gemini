@Echo off

rem reg delete 	HKCR\.torrent /ve /f
rem reg add 	HKCR\.torrent /ve /d Gemini /f
rem reg add 	HKCR\Gemini\DefaultIcon /ve /d "shimgvw.dll,2" /f


rem reg delete 	HKCR\Gemini\ /ve /f
rem reg add 	HKCR\Gemini\ /ve /f 

rem reg delete 	HKCR\Gemini\shell\GeminiMenu  /ve /f
rem reg add 	HKCR\Gemini\shell\GeminiMenu  /ve /d (Default) /f
rem reg add 	HKCR\Gemini\shell\GeminiMenu  /t REG_SZ /v "MuiVerb" /d "Open with Gemini" /f   

rem reg add 	HKCR\Gemini\shell\GeminiMenu  /t REG_SZ /v "SubCommands" /d "Windows.delete;Windows.properties;Windows.rename;Windows.cut;Windows.copy;Windows.paste" /f

rem ADDING PROGRAM TO PATH
rem -----------------------------------------------------------------------------------------------

rem reg delete 	HKLM\Software\Microsoft\Windows\CurrentVersion\App Paths\Gemini.exe /v /f
rem reg add         HKLM\Software\Microsoft\Windows\CurrentVersion\App Paths\Gemini.exe /v /f    
rem reg add   	HKLM\Software\Microsoft\Windows\CurrentVersion\App Paths\Gemini /t REG_SZ /ve /d "Windows.delete"  /f 
rem reg add   	HKLM\Software\Microsoft\Windows\CurrentVersion\App Paths\Gemini.exe /t REG_SZ /v "Path" /d "c:\Program Files\7-Zip\"  /f 

rem CREATE RIGHT MENU
rem -----------------------------------------------------------------------------------------------

reg delete 	HKCR\*\shell\GeminiMenu /ve /f
reg add   	HKCR\*\shell\GeminiMenu /ve /f
reg add   	HKCR\*\shell\GeminiMenu /t REG_SZ /v "MuiVerb" /d "Open with Gemini"   /f 
reg add   	HKCR\*\shell\GeminiMenu /t REG_SZ /v "Icon"    /d "shell32.dll,-243"   /f 
reg add 	  HKCR\*\shell\GeminiMenu /t REG_SZ /v "SubCommands" /d "GeminiMenu.Open;GeminiMenu.Edit;" /f   

reg add 	  HKCR\*\shell\GeminiMenu\shell\Open /ve /f
reg add 	  HKCR\*\shell\GeminiMenu\shell\Open /t REG_SZ /v "MuiVerb" /d "Open"   /f 
reg add     HKCR\*\shell\GeminiMenu\shell\Open /t REG_SZ /v "Icon"    /d "shell32.dll,-243"   /f 
reg add 	  HKCR\*\shell\GeminiMenu\shell\Open\Command /t REG_SZ /ve  /d "notepad.exe \"%1\" /f 

reg add 	  HKCR\*\shell\GeminiMenu\shell\Edit /ve /f
reg add 	  HKCR\*\shell\GeminiMenu\shell\Edit /t REG_SZ /v "MuiVerb" /d "Edit"   /f 
reg add     HKCR\*\shell\GeminiMenu\shell\Edit /t REG_SZ /v "Icon"    /d "shell32.dll,-243"   /f 
reg add 	  HKCR\*\shell\GeminiMenu\shell\Edit\Command /t REG_SZ /ve  /d "Windows.delete;" /f 



reg delete 	HKCR\*\shellex\ContextMenuHandlers\GeminiMenu /ve /f
reg add   	HKCR\*\shellex\ContextMenuHandlers\GeminiMenu /ve /f
reg add   	HKCR\*\shellex\ContextMenuHandlers\GeminiMenu /t REG_SZ /v "MuiVerb" /d "Open with Gemini"   /f 
reg add   	HKCR\*\shellex\ContextMenuHandlers\GeminiMenu /t REG_SZ /v "Icon"    /d "shell32.dll,-243"   /f 
reg add 	  HKCR\*\shellex\ContextMenuHandlers\GeminiMenu /t REG_SZ /v "SubCommands" /d "Windows.delete;Windows.properties" /f 

rem reg add 	  HKCR\*\shellex\ContextMenuHandlers\GeminiMenu /t REG_SZ /v "SubCommands" /d "Windows.delete;Windows.properties" /f 


rem reg add 	  HKCR\*\shell\GeminiMenu\command /t REG_SZ /v "(Default)" /d "Windows.delete;Windows.properties;\"notepad.exe\" %%1" /f  

rem reg delete 	HKCR\*\shell\GeminiMenu\GeminiMenu1  /ve /f
rem reg add 	HKCR\*\shell\GeminiMenu\GeminiMenu1  /ve /d (Default) /ve /f
rem reg add 	HKCR\*\shell\GeminiMenu\GeminiMenu1  /t REG_SZ /v "MuiVerb" /d "Open with Gemini" /ve /f  

rem reg delete 	HKCR\*\shell\GeminiMenu\GeminiMenu2  /ve /f
rem reg add 	HKCR\*\shell\GeminiMenu\GeminiMenu2  /ve /d (Default) /f
rem reg add 	HKCR\*\shell\GeminiMenu\GeminiMenu2  /t REG_SZ /v "MuiVerb" /d "Open with Gemini" /ve /f   

rem reg add 	HKCR\*\shell\GeminiMenu\command /t REG_SZ /v "(Default)" /d "notepad.exe\"%1\"  /ve /f


rem -----------------------------------------------------------------------------------------------


rem reg add 	HKEY_LOCAL_MACHINE\Software\Microsoft\Windows\CurrentVersion\Explorer\CommandStore\Shell\GeminiMenu /ve /f
rem reg add 	HKEY_LOCAL_MACHINE\Software\Microsoft\Windows\CurrentVersion\Explorer\CommandStore\Shell\GeminiMenu\command /t REG_SZ /v (Default)  /d "notepad.exe %1" 

rem "Windows.delete;Windows.properties;Windows.rename;Windows.cut;Windows.copy;Windows.paste"

rem reg delete 	HKCR\Gemini\shell\open\command /ve /f
rem reg add 	HKCR\Gemini\shell\open\command /t REG_SZ /v "" /d "notepad.exe %1"  


rem reg delete 	HKCR\*\shellex\ContextMenuHandlers\GeminiMenu  /ve /f
rem reg add  	  HKCR\*\shellex\ContextMenuHandlers\GeminiMenu  /ve /d (Default) /f
rem reg add 	  HKCR\*\shellex\ContextMenuHandlers\GeminiMenu  /t REG_SZ /v "" 	/d "Open with Gemini" /f

rem reg delete 	HKCR\*\shellex\ContextMenuHandlers\GeminiMenu\shell\GeminiMenu1  /ve /f
rem reg add 	  HKCR\*\shellex\ContextMenuHandlers\GeminiMenu\shell\GeminiMenu1  /ve /d (Default) /f
rem reg add 	  HKCR\*\shellex\ContextMenuHandlers\GeminiMenu\shell\GeminiMenu1  /t REG_SZ /v "MuiVerb" /d "Open with Gemini" /f

rem reg add 	HKCR\*\shellex\ContextMenuHandlers\GeminiMenu\shell\GeminiMenu1\command /t REG_SZ /v "(Default)" /d "notepad.exe\"%1\"  /f



rem reg delete 	HKCR\*\shellex\ContextMenuHandlers\GeminiMenu\shell\GeminiMenu2  /ve /f
rem reg add 	  HKCR\*\shellex\ContextMenuHandlers\GeminiMenu\shell\GeminiMenu2  /ve /d (Default) /f
rem reg add 	  HKCR\*\shellex\ContextMenuHandlers\GeminiMenu\shell\GeminiMenu2  /t REG_SZ /v "MuiVerb" /d "Open with Gemini" /f

rem reg add    	HKCR\*\shellex\ContextMenuHandlers\GeminiMenu\shell\GeminiMenu2\command /t REG_SZ /v "(Default)" /d "notepad.exe \"%1\"   /f





rem -----------------------------------------------------------------------------------------------





rem reg add 	HKCR\*\shell\Gemini /ve /d (Default)


rem reg delete 	HKCR\Gemini\shell\open\command /ve /f
rem reg add 	HKCR\Gemini\shell\open\command /ve /d "\"%1\" %*\" /f

rem reg add 	HKCR\*\shell\Gemini /t REG_SZ /v "" /d "Open with Gemini" /f
rem reg add 	HKCR\*\shell\open_with_gemini\command" /t REG_SZ /v "" /d "Gemini ^"%1^"" 



pause