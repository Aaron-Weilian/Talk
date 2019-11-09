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
public interface IExcelRowReader {

    /**
     * @param sheetIndex
     * @param curRow
     * @param rowlist
     */
    void setRows(int sheetIndex, int curRow, List<String> rowlist);
    
    CommonBean getRows();

}
