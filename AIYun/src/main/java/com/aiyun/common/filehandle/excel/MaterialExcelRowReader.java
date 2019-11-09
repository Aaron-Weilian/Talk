/**
 * 
 */
package com.aiyun.common.filehandle.excel;

import java.util.List;
import java.util.Vector;

import com.aiyun.common.tool.Log;

/**
 * @author ZT
 *
 */
public class MaterialExcelRowReader extends ExcelRowReader {

    
    private final String beanName = "Material";
    
    public MaterialExcelRowReader() {
        commonBean.setBeanName(beanName);
    }
    
    public void setRows(int sheetIndex, int curRow, List<String> rowlist) {
        
        if (rowlist == null ) return ;
        
        if (curRow == 0) {
            commonBean.setColNames(rowlist.toArray(new String[rowlist.size()]));
        }
        else {
            commonBean.addRow(new Vector(rowlist), curRow-1);
        }
        
        Log.info(this, "Sheet:"+sheetIndex+",Row:"+curRow+",Data:"+rowlist);
        
    }
    
    
    

}
