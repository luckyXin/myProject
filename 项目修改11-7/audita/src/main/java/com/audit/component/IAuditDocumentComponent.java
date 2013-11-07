/**
 * 
 */
package com.audit.component;

import java.util.Map;

/**
 * @author User
 *
 */
public interface IAuditDocumentComponent {

	/**
	 * 根据模板生成word文档
	 * @param masterplateUrl 模板地址
	 * @param name模板名称
	 * @param map 写入内容
	 * @return 生成的word文档地址
	 */
	public String writeWord(String masterplateUrl,String name ,Map<String, String> map);
	
	/**
	 * @param masterplateUrl
	 * @param name
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String writeWordOtherMethod(String masterplateUrl, String name, Map<String, String> map) throws Exception;
	
	/**
	 * word to html
	 */
	public String wordToHtml(String masterplateUrl,String name, Map<String, String> map);
}
