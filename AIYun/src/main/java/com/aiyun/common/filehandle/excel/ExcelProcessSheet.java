/**
 * 
 */
package com.aiyun.common.filehandle.excel;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.StylesTable;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class ExcelProcessSheet {

    /**
     * The type of the data value is indicated by an attribute on the cell. The
     * value is usually in a "v" element within the cell.
     */
    enum xssfDataType {
        BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER,
    }

    private OPCPackage xlsxPackage;
    private int minColumns;
    private PrintStream output;

    // 当前行
    private int curRow = 0;

    private int sheetIndex = 0;

    private IExcelRowReader excelFileReader;

    /**
     * Creates a new XLSX -> CSV converter
     *
     * @param pkg        The XLSX package to process
     * @param output     The PrintStream to output the CSV to
     * @param minColumns The minimum number of columns to output, or -1 for no
     *                   minimum
     * @param rowReader
     */
    public ExcelProcessSheet(OPCPackage pkg, PrintStream output, int minColumns, IExcelRowReader excelFileReader) {
        this.xlsxPackage = pkg;
        this.output = output;
        this.minColumns = minColumns;
        this.excelFileReader = excelFileReader;
    }

    /**
     * Parses and shows the content of one sheet using the specified styles and
     * shared-strings tables.
     *
     * @param styles
     * @param strings
     * @param sheetInputStream
     */
    public void processSheet(StylesTable styles, ReadOnlySharedStringsTable strings, InputStream sheetInputStream)
            throws IOException, ParserConfigurationException, SAXException {

        InputSource sheetSource = new InputSource(sheetInputStream);
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxFactory.newSAXParser();
        XMLReader sheetParser = saxParser.getXMLReader();
        ContentHandler handler = new SystemSheetHandler(styles, strings, this.minColumns, this.output,excelFileReader,minColumns,sheetIndex,curRow);
        sheetParser.setContentHandler(handler);
        sheetParser.parse(sheetSource);
    }

    /**
     * Initiates the processing of the XLS workbook file to CSV.
     *
     * @throws IOException
     * @throws OpenXML4JException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public void process() throws Exception {
        InputStream stream = null;
        try {
            ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(this.xlsxPackage);
            XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);
            StylesTable styles = xssfReader.getStylesTable();
            XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
            while (iter.hasNext()) {
                curRow = 0;
                sheetIndex++;
                stream = iter.next();
                processSheet(styles, strings, stream);
                stream.close();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("读取文件失败，此文件可能已损坏，请参照模板重新上传！");
        } finally {
            if (null != stream) {
                stream.close();
            }
        }
    }

    

}
