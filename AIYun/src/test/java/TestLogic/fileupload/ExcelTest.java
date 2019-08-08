/**
 * 
 */
package TestLogic.fileupload;

import com.aiyun.common.fileupload.ExcelHandle;

/**
 * @author ZT
 *
 */
public class ExcelTest {
	
	public static void main(String[] args) {
		
		String filePath = "E:\\20170730_Phone_SO.xlsx";
		
		//filePath.replace("\\", "//");
		long begin = System.currentTimeMillis();
		ExcelHandle eh = new ExcelHandle(filePath,ExcelHandle.EXCEL_UPLOAD);
		eh.run();
		long end = System.currentTimeMillis();
		System.out.println("time:"+(begin-end));
	}

}
