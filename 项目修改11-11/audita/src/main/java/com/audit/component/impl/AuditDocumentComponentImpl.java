package com.audit.component.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import com.audit.common.AuditStringUtils;
import com.audit.component.IAuditDocumentComponent;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.rtf.RtfWriter2;

/**
 * @author DengXin
 */
public class AuditDocumentComponentImpl implements IAuditDocumentComponent {

	private Logger logger = Logger.getLogger(AuditDocumentComponentImpl.class);
	
	public static final int WORD_HTML = 8;
	
	public static final int WORD_TXT = 7;
	
	public static final int EXCEL_HTML = 44;

	/**
	 * 根据模板生成word文档 2013-6-9
	 * 
	 * @see com.audit.component.IAuditDocumentComponent#writeWord(java.lang.String,
	 *      java.util.Map)
	 */
	@Override
	public String writeWord(String masterplateUrl, String name,
			Map<String, String> map) {
		String fileUrl = AuditStringUtils.EMPTY;
		try {
			// 读取word模板
			FileInputStream in = new FileInputStream(masterplateUrl + "/"
					+ name);
			HWPFDocument hdt = new HWPFDocument(in);

			// 读取word文本内容
			Range range = hdt.getRange();
			// 替换文本内容
			for (Map.Entry<String, String> entry : map.entrySet()) {
				if (AuditStringUtils.isNotEmpty(entry.getValue())) {
					range.replaceText(entry.getKey(), entry.getValue());
				} else {
					range.replaceText(entry.getKey(), "");
				}
			}
			ByteArrayOutputStream ostream = new ByteArrayOutputStream();
			String fileName = name.replace(".doc", "") + "_"
					+ System.currentTimeMillis();
			fileName += ".doc";
			fileUrl = masterplateUrl + "/file/" + fileName;
			// 删除该目录下面的所有文件
			File newfile = new File(masterplateUrl + "/file/");
			File[] files = newfile.listFiles();
			if(null!=files)
			{
				for (File file : files) {
					if (file.isFile()) {
						file.delete();
					}
				}
			}	
			
			hdt.write(ostream);
			OutputStream out = new FileOutputStream(fileUrl);
			out.write(ostream.toByteArray());
			// 输出字节流
			out.close();
			ostream.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return fileUrl;
	}

	/**
	 * (non-Javadoc) 2013-7-21
	 * 
	 * @throws Exception
	 * @see com.audit.component.IAuditDocumentComponent#writeWordOtherMethod()
	 */
	@Override
	public String writeWordOtherMethod(String masterplateUrl, String name,
			Map<String, String> map) throws Exception {
		String fileUrl = AuditStringUtils.EMPTY;
		Document document = new Document(PageSize.A4);
		String fileName = name.replace(".doc", "") + "_"
				+ System.currentTimeMillis();
		fileName += ".doc";
		fileUrl = masterplateUrl + "/file/" + fileName;
		RtfWriter2.getInstance(document, new FileOutputStream(fileUrl));
		return null;
	}

	/**
	 * (non-Javadoc) 2013-7-22
	 * 
	 * @see com.audit.component.IAuditDocumentComponent#writeWordOtherMethod(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public String wordToHtml(String masterplateUrl,String name, Map<String, String> map) {
		// 创建html地址
		String htmlfile = AuditStringUtils.EMPTY;
		
		String wordUrl = masterplateUrl + "/" + name;
		// 启动word
		ActiveXComponent app = new ActiveXComponent("Word.Application");
		try {
			// 设置word不可见
			app.setProperty("Visible", new Variant(false));
			// 获得documents对象
			Dispatch docs = (Dispatch) app.getProperty("Documents").toDispatch();
			// 打开文件
			Dispatch doc = Dispatch.invoke(docs,"open",Dispatch.Method, new Object[] { 
					wordUrl, new Variant(false), new Variant(true) }, new int[1]).toDispatch();
			
			htmlfile = name.replace(".doc", "") + "_"+ System.currentTimeMillis();
			htmlfile += ".html";
			htmlfile =masterplateUrl + "temporary/" + htmlfile;
			Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] { htmlfile, new Variant(WORD_HTML) }, new int[1]);
			Variant f = new Variant(false);
			Dispatch.call(doc, "Close", f);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			app.invoke("Quit", new Variant[] {});
		}
		return htmlfile;
	}
}
