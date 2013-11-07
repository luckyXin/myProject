var moduleId = moduleId();
var moduleName = moduleName();
var moduleUrl = moduleUrl();
var parentModuleId = parentModuleId();
var parentModuleName = parentModuleName();
/**
 * 获取当前模块ID
 */
function moduleId() {
	var frame = self.frameElement;
	if (frame)
		return frame.id;
	else
		return "";
}
/**
 * 获取当前模块名称
 */
function moduleName() {
	var frame = self.frameElement;
	if (frame)
		return frame.title;
	else
		return "";
}
/**
 * 获取当前模块URL
 * 
 * @returns
 */
function moduleUrl() {
	var frame = self.frameElement;
	if (frame)
		return frame.src;
	else
		return "";
}

function parentModuleId() {
	var parentModuleId = moduleId.substring(0, moduleId.lastIndexOf("-"));
	return parentModuleId;
}
function parentModuleName() {
	var parentModuleName = moduleName.substring(0, moduleName.lastIndexOf("-"));
	return parentModuleName;
}
function converyNumToDaXie(num) { 
	   var first = ""; //第一位（左起,如二十三，则二为第一位）;   
	   var second = ""; //第二位  
	   var third = "";     //先求第一位及第二位      
	   if (9 <= parseFloat(parseInt(num) / parseInt(10)))//九十   
	   {        
		   first = "九";        
		   second = "十";    
	   } 
	   else if (8 <= parseFloat(parseInt(num) / parseInt(10)))//八十  
	   {         
		   first = "八";      
		   second = "十";    
	   } else if (7 <= parseFloat(parseInt(num) / parseInt(10)))//七十  
	   {     
		   first = "七";   
		   second = "十";   
	   } else if (6 <= parseFloat(parseInt(num) / parseInt(10)))//六十     
	   {        
		   first = "六";    
		   second = "十";    
	   } else if (5 <= parseFloat(parseInt(num) / parseInt(10)))//五十    
			{        
		   first = "五";  
		   second = "十";    
	   } else if (4 <= parseFloat(parseInt(num) / parseInt(10)))//四十    
	   {        
		   first = "四";   
		   second = "十";    
	   } else if (3 <= parseFloat(parseInt(num) / parseInt(10)))//三十 
	   {       
		   first = "三";   
		   second = "十";    
		}    
		else if (2 <= parseFloat(parseInt(num) / parseInt(10)))//二十  
		{      
		   first = "二";    
		   second = "十";   
		} else if (1 < parseFloat(parseInt(num) / parseInt(10)))//十  
		{        
			second = "十";   
		}     //十位以下的    
	    if (parseInt(num) == 0) //为0   
		{        
		   third = "零";  
		}   
	    
	    else if (parseInt(num) % parseInt(10) == 1)//为1  
		{         third = "一";     }    
	    else if (parseInt(num) % parseInt(10) == 2)//为2   
		   {         third = "二";     }   
	    else if (parseInt(num) % parseInt(10) == 3)//为3    
		   {         third = "三";     }   
	    else if (parseInt(num) % parseInt(10) == 4)//为4    
		   {         third = "四";     }   
	    else if (parseInt(num) % parseInt(10) == 5)//为5  
		   {         third = "五";     }    
	    else if (parseInt(num) % parseInt(10) == 6)//为6   
		   {         third = "六";     }     
	    else if (parseInt(num) % parseInt(10) == 7)//为7   
		   {         third = "七";     }     
	    else if (parseInt(num) % parseInt(10) == 8)//为8  
		   {         third = "八";     }    
	    else if (parseInt(num) % parseInt(10) == 9)//为9   
		   {         third = "九";     }     
	    else if (parseInt(num)==10)//为10   
		   {         third = "十";     }       
		  
	   return (first + second + third);
	}