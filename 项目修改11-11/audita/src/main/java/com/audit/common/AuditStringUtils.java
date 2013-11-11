package com.audit.common;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.audit.entity.DifferenceListString;

public class AuditStringUtils extends StringUtils {

	/**
	 * "yyyy-MM-dd"类型
	 */
	public final static String DATE_YYYYMMMDD = "yyyy-MM-dd";
	
	private static final int DEF_DIV_SCALE = 3; 


	/**
	 * List 转换为 String 已","进行分割
	 * 
	 * @param list
	 * @return
	 */
	public static String getListToString(List<String> list) {

		list.get(0);
		Iterator<String> str = list.iterator();
		StringBuilder sb = new StringBuilder();
		while (str.hasNext()) {
			sb.append(str);
			sb.append(",");
		}
		return sb.toString();
	}

	/**
	 * 字符串 空判断
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if (null != str && !"".equals(str) && !"null".equals(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 字符串转换
	 * 
	 * @param str
	 * @return
	 */
	public static String toString(Object str) {
		if (null == str) {
			return "";
		} else {
			return str.toString();
		}
	}

	public static String getID(String str1, Integer id, int num) {
		if (null == id) {
			id = 0;
		}
		Integer max = id + 1;
		String temp = String.valueOf(max);
		int len = temp.length();
		for (int i = 0; i < num - len; i++) {
			temp = "0" + temp;
		}
		return str1 + "" + temp;
	}

	public static String converyNumToDaXie(String num1) {
		Integer num = Integer.parseInt(num1);
		String first = ""; // 第一位（左起,如二十三，则二为第一位）;
		String second = ""; // 第二位
		String third = ""; // 先求第一位及第二位
		if (9 <= (num / 10))// 九十
		{
			first = "九";
			second = "十";
		} else if (8 <= (num / 10))// 八十
		{
			first = "八";
			second = "十";
		} else if (7 <= (num / 10))// 七十
		{
			first = "七";
			second = "十";
		} else if (6 <= (num / 10))// 六十
		{
			first = "六";
			second = "十";
		} else if (5 <= (num / 10))// 五十
		{
			first = "五";
			second = "十";
		} else if (4 <= (num / 10))// 四十
		{
			first = "四";
			second = "十";
		} else if (3 <= (num / 10))// 三十
		{
			first = "三";
			second = "十";
		} else if (2 <= (num / 10))// 二十
		{
			first = "二";
			second = "十";
		} else if (1 <= (num / 10))// 十
		{
			second = "十";
		} // 十位以下的
		if (num == 0) // 为0
		{
			third = "零";
		}

		else if (num % 10 == 1)// 为1
		{
			third = "一";
		} else if (num % 10 == 2)// 为2
		{
			third = "二";
		} else if (num % 10 == 3)// 为3
		{
			third = "三";
		} else if (num % 10 == 4)// 为4
		{
			third = "四";
		} else if (num % 10 == 5)// 为5
		{
			third = "五";
		} else if (num % 10 == 6)// 为6
		{
			third = "六";
		} else if (num % 10 == 7)// 为7
		{
			third = "七";
		} else if (num % 10 == 8)// 为8
		{
			third = "八";
		} else if (num % 10 == 9)// 为9
		{
			third = "九";
		} else if (num == 10)// 为10
		{
			third = "十";
		}

		return (first + second + third);
	}

	/**
	 * 生成32位ID
	 * 
	 * @return
	 */
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		return s.replaceAll("-", "").toUpperCase();
	}

	/**
	 * 数值字符串（元）转换为（万元）
	 */
	public static String convertMillion(String numberStr) {

		if (!isNotEmpty(numberStr)) {
			return "";
		}

		if (numberStr.length() >= 5) {
			int indexCount = 0;
			if (indexOf(numberStr, ".") == -1) {
				indexCount = numberStr.length() - 4;
			} else {
				int i = numberStr.substring(indexOf(numberStr, ".") + 1, numberStr.length()).length();
				numberStr = replace(numberStr, ".", "");
				indexCount = numberStr.length() - (4 + i);
			}
			StringBuilder sb = new StringBuilder(numberStr);
			sb.insert(indexCount, ".");
			if (sb.toString().indexOf(".") == 0) {
				sb.insert(0, "0");
			}
			return sb.toString();
		} else {
			StringBuilder sb = new StringBuilder(numberStr);
			sb.insert(0, "0.");
			return sb.toString();
		}
	}

	/**
	 * 截取时间 XXXX-XX-XX
	 */
	public static String getYYYYMMDD(String date) {
		return substring(date, 0, 9);
	}

	/**
	 * 截取时间 XXXX-XX
	 */
	public static String getYYYYMM(String date) {
		return substring(date, 0, 7);
	}

	/**
	 * 截取时间 XXXX-XX-XX
	 */
	public static String getDatetoyyyyMMdd(String date) {
		return substring(date, 0, 10);
	}

	/**
	 * 比较ListString
	 * 
	 * @param oldList
	 * @param newList
	 * @return
	 */
	public static DifferenceListString compareListString(List<String> oldList, List<String> newList) {

		List<String> deleteString = new ArrayList<String>();
		List<String> addString = new ArrayList<String>();

		for (String newStr : newList) {
			if (!oldList.contains(newStr)) {
				addString.add(newStr);
			}
		}
		for (String oldStr : oldList) {
			if (!newList.contains(oldStr)) {
				deleteString.add(oldStr);
			}
		}
		DifferenceListString differenceListString = new DifferenceListString();
		differenceListString.setAddString(addString);
		differenceListString.setDeleteString(deleteString);
		return differenceListString;
	}

	/**
	 * String to Date
	 * 
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date strToDate(String str, String formatType) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		Date date = sdf.parse(str);
		return date;
	}

	/**
	 * Date to String
	 * 
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static String dateToStr(Date date, String formatType) throws ParseException {
		if (date != null && isNotEmpty(formatType)) {
			SimpleDateFormat sdf = new SimpleDateFormat(formatType);
			String str = sdf.format(date);
			return str;
		} else {
			return "";
		}
	}

	/**
	 * 获取指定格式的系统时间
	 * 
	 * @param formatType
	 * @return
	 * @throws ParseException
	 */
	public static String getSystem(String formatType) throws ParseException {
		if (isNotEmpty(formatType)) {
			SimpleDateFormat sdf = new SimpleDateFormat(formatType);
			String str = sdf.format(new Date());
			return str;
		} else {
			return "";
		}
	}

	/**
	 * 文件上传
	 * 
	 * @param file
	 * @param url
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static List<Map<String, String>> uploadfile(List<MultipartFile> file, String url, HttpServletRequest request)
			throws IOException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (int i = 0; i < file.size(); i++) {
			// 判断是否有文件
			if (!file.get(i).isEmpty()) {
				String fileName = file.get(i).getOriginalFilename();
				// 获取文件后缀
				int index = fileName.lastIndexOf(".");
				String suffix = fileName.substring(index, fileName.length());
				String name = fileName.substring(0, index);
				SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHMMss");
				String newFileName = name + sf.format(new Date()) + suffix;
				byte[] bytes = file.get(i).getBytes();
				String uploadPath = "/upload/" + url;
				File filepath = new File(request.getSession().getServletContext().getRealPath(uploadPath));
				if (!filepath.exists()) {
					filepath.mkdir();
				}
				File filePath = new File(request.getSession().getServletContext()
						.getRealPath(uploadPath + "/" + newFileName));
				/**
				 * 文件开始上传到服务器上
				 */
				FileCopyUtils.copy(bytes, filePath);
				// 定义文件对象
				Map<String, String> map = new HashMap<String, String>();
				map.put("fileName", fileName);
				map.put("url", url + "/" + newFileName);
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * 删除文件
	 * 
	 * @param url
	 */
	public static void deletefile(String url, HttpServletRequest request) {
		String filePath = request.getSession().getServletContext().getRealPath("/upload/" + url);
		File newfile = new File(filePath);
		if (newfile.isFile()) {
			newfile.delete();
		}
	}

	/**
	 * 拼接字符串 ,
	 * 
	 * @param list
	 * @return
	 */
	public static String addString(List<String> list) {
		StringBuilder str = new StringBuilder();
		for (String strStemp : list) {
			if (str.length() == 0) {
				str.append(strStemp);
			} else {
				str.append(",");
				str.append(strStemp);
			}
		}
		return str.toString();
	}

	/**
	 * 两时间相差的天数
	 */
	@SuppressWarnings("deprecation")
	public static long twoDateSubtract(String startDate, String endDate) {
		long day = 0;
		try {
			Date date1 = new SimpleDateFormat("yyyy-mm-dd").parse(startDate);
			Date date2 = new SimpleDateFormat("yyyy-mm-dd").parse(endDate);
			while (date1.compareTo(date2) <= 0) {
				if (date1.getDay() != 6 && date1.getDay() != 0)
					day++;
				date1.setDate(date1.getDate() + 1);
			}
		} catch (ParseException e) {
			return 0;
		}
		return day;
	}
	
	
	public static String parseMoney(String str1) {   
	     int len=str1.length(); 
	    int k = 0; 
	    int m = 0; 
	    int dot = 0; 
	    int start = 0; 
	    String str_dot=""; 
	    String str_start=""; 
	    for(k=1;k<len;k++) 
	    { 
	        if(str1.substring(k,k+1).equals(".")) 
	         { 
	                  dot=k+1;                 
	                  break; 
	             } 
	    } 
	    start=(dot-1)%DEF_DIV_SCALE; 
	    if(start==0) 
	    { 
	      str_start=""; 
	    } 
	    else 
	    { 
	           str_start=str1.substring(0,start)+","; 
	    } 
	    m=0; 
	    for(k=start;k<dot;k+=DEF_DIV_SCALE) 
	    { 
	        str_start+=str1.substring(k,k+DEF_DIV_SCALE)+","; 
	        m++; 
	    } 
	    if(start==0) 
	    { 
	        str_start=str_start.substring(0,dot+m-3); 
	    } 
	    else 
	    {    
	        str_start=str_start.substring(0,dot+m-2); 
	    } 
	    str_dot=str1.substring(dot,len); 
	    str1=str_start+"."+str_dot; 

	return str1; 
	}

}
