/**
 * 
 */
package com.aiyun.common.filehandle.excel;

import java.util.List;

import com.aiyun.common.vo.CommonBean;

/**
 * @author ZT
 *
 */
public class ExcelRowReader  implements IExcelRowReader {

    public CommonBean commonBean = new CommonBean();
    
    @Override
    public void setRows(int sheetIndex, int curRow, List<String> rowlist) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public CommonBean getRows() {
        return this.commonBean;
    }

}
