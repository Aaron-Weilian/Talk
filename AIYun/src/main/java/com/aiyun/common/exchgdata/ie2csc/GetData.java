//package com.aiyun.common.exchgdata.ie2csc;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.OutputStreamWriter;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Vector;
//
//import org.dom4j.Document;
//import org.dom4j.DocumentHelper;
//import org.dom4j.Element;
//import org.dom4j.io.OutputFormat;
//import org.dom4j.io.SAXReader;
//import org.dom4j.io.XMLWriter;
//
//import com.aiyun.common.bo.DBUtil;
//import com.aiyun.common.bo.DataBaseObject;
//import com.aiyun.common.util.StrTool;
//import com.aiyun.common.vo.CommonBean;
//
//public class GetData extends DBUtil {
//	private static String MAIN_DIR = "/app/paink/data/IE";
//	//private static String MAIN_DIR = "c:/data/ocl";
//	private static HashMap hmUnit = null;
//	private boolean bgetbom = false;	//�Ƿ���յ���bom����
//	
//	private static GetData getdata = new GetData();
//	public static GetData getGetData () {
//		return getdata;
//	}
//	
//	private GetData() {
//		//must get the same instance
//		super();
//		init();
//	}
//	
//	static void init() {
//		if (hmUnit == null) {
//			hmUnit = new HashMap();
//			
//			hmUnit.put("EA","054");
//			hmUnit.put("EACH","054");
//			
//			hmUnit.put("KG","035");
//			hmUnit.put("KILOGRAM","035");
//			
//			hmUnit.put("ML","096");
//			hmUnit.put("MILLILITER","096");
//		}
//	}
//	
//	public static void main(String[] args) {
//		try {
//			SAXReader saxReader = new SAXReader();
//			Document d_fromfile = saxReader.read("e:/data/gb2312.xml");
//			
//			Document d_create = DocumentHelper.createDocument();
//			Element root = d_create.addElement("root");
//			Element en = root.addElement("entry");
//			
//			Element item = null;
//			item = en.addElement("ljno");
//			item.setText("SXA 109 5989/26.R1A");
//			en.addElement("organization");
//			item.setText("IP");
//			item = en.addElement("wlflag");
//			item.setText("B");
//			item = en.addElement("cpflag");
//			item.setText("COM");
//			item = en.addElement("sname");
//			item.setText("LABEL/CO-BRAND LABEL,GRANITE BLACK����");
//			item = en.addElement("model");
//			item.setText("NA");
//			item = en.addElement("unit");
//			item.setText("Ea");
//			item = en.addElement("modify_mark");
//			item.setText("1");
//			
//			en = root.addElement("entry");
//			item = en.addElement("ljno");
//			item.setText("SXA 109 5989/26.R1A");
//			item = en.addElement("organization");
//			item.setText("IP");
//			item = en.addElement("wlflag");
//			item.setText("B");
//			item = en.addElement("cpflag");
//			item.setText("COM");
//			item = en.addElement("sname");
//			item.setText("LABEL/CO-BRAND LABEL,GRANITE BLACK����");
//			item = en.addElement("model");
//			item.setText("LILY");
//			item = en.addElement("unit");
//			item.setText("Ea");
//			item = en.addElement("modify_mark");
//			item.setText("1");
//
//
//			OutputFormat format = OutputFormat.createPrettyPrint();
//			format.setEncoding("utf-8");
//			format.setNewlines(false);
//			format.setTrimText(false); 
//			FileOutputStream fos = new FileOutputStream("e:/data/abc.xml");
//			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));
//			XMLWriter writer = new XMLWriter(bw , format);
//			writer.write(d_create);
//			bw.close();
//
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	//ÿ�ε��ȵ��������
//	public synchronized boolean getData() {
//		try {
//			//�����ϼ�
//			getBALJ();
//			
//			//������Ʒ
//			getBACP();
//			
//			//�ϼ��滻��ϵ
//			getLJChange();
//			
//			//����BOM
//			getBABOM();
//			
//			//SO��Ϣ
//			getCPCK();
//			
//			//��ⵥ��Ϣ
//			getGR();
//			
//			//���ⵥ��Ϣ
//			getGI();
//			
//			//PO��Ϣ
//			getPO();
//			
//			//����
//			getGD();
//			
//			//�������ϳ�Ʒ�����Ϣ
//			getCPRK();
//			
//			//��˰Ͷ�ϵ�
//			getTL();
//			
//			//��������
//			getFQXH();
//			
//			//���
//			getKC();
//			
//			//����������
//			getAdjData();
//			
//			//��֯��ת��
//			getOrgTrans();
//			
//			//���ʵ���
//			getCurr();
//			
//			//�Զ�����bom
//			if (bgetbom) autoBom();
//			
//			return true;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//	
//	
//	private boolean getGD() {
//		try {
//			//search file list
//			Vector vecFileList = getFileList("gd");
//		
//			DataBaseObject dbo = getDataBaseObject();
//			for (int i=0; i < vecFileList.size(); i++) {
//				try {
//					SAXReader saxReader = new SAXReader();
//					Document document = saxReader.read(MAIN_DIR+"/gd/"+(String)vecFileList.get(i));
//					Element root = document.getRootElement();
//					//���ڳ�Ʒ
//					Iterator iter_root = root.elementIterator();
//					
//					CommonBean cbGDHead = new CommonBean();
//					cbGDHead.setBeanName("ie_gdhead");
//					cbGDHead.setAttribute("insert");
//					
//					CommonBean cbGDBody = new CommonBean();
//					cbGDBody.setBeanName("ie_gdbody");
//					cbGDBody.setAttribute("insert");
//					
//					String strHeaderID = null;
//					while (iter_root.hasNext()) {
//						Element element_entry = (Element)iter_root.next();
//						Iterator iter_entry = element_entry.elementIterator();
//						while (iter_entry.hasNext()) {
//							Element element_gd = (Element)iter_entry.next();
//							//ͷ��Ϣ
//							if (element_gd.getName().equalsIgnoreCase("job_order")) cbGDHead.addValue("job_order",element_gd.getText());
//							if (element_gd.getName().equalsIgnoreCase("so_no")) cbGDHead.addValue("so_no",element_gd.getText());
//							if (element_gd.getName().equalsIgnoreCase("cpno")) cbGDHead.addValue("cpno",element_gd.getText());
//							if (element_gd.getName().equalsIgnoreCase("organization")) {
//								String org = element_gd.getText();
//								if (org.equalsIgnoreCase("ac")) org = "VP";
//								cbGDHead.addValue("organization",org);
//							} 
//							if (element_gd.getName().equalsIgnoreCase("version")) {
//								String ver = element_gd.getText();
//								if (ver==null || ver.equals("")) {
//									ver = "0";
//								}
//								cbGDHead.addValue("version",ver);
//							} 
//							if (element_gd.getName().equalsIgnoreCase("quantity")) {
//								cbGDHead.addValue("quantity",StrTool.str2double(element_gd.getText())/1000+"");
//								cbGDHead.addValue("remain",StrTool.str2double(element_gd.getText())/1000+"");
//							} 
//							if (element_gd.getName().equalsIgnoreCase("complete_date")) cbGDHead.addValue("complete_date",element_gd.getText());
//							
//							if (element_gd.getName().equalsIgnoreCase("item")) {
//								Iterator iter_item= element_gd.elementIterator();
//								
//								cbGDBody.addValue("job_order",cbGDHead.getCellStr(cbGDHead.getRowNum()-1,"job_order"));
//								while (iter_item.hasNext()) {
//									Element element_item = (Element)iter_item.next();
//									String ename = element_item.getName();
//									String etext = element_item.getText();
//									if (ename.equalsIgnoreCase("required")) {
//										//cbGDBody.addValue("required",StrTool.str2double(etext)/1000+"");
//										cbGDBody.addValue("required",etext);
//									} 
//									else if (ename.equalsIgnoreCase("issued")) {
//										//cbGDBody.addValue("issued",StrTool.str2double(etext)/1000+"");
//										cbGDBody.addValue("issued",etext);
//										//cbGDBody.addValue("gd_issued",etext);
//									} 
//									//��λ
//									else if (ename.equalsIgnoreCase("sldw")) {
//											cbGDBody.addValue(ename.toLowerCase(),(String)hmUnit.get(etext.toUpperCase()));
//									}
//									else {
//										cbGDBody.addValue(ename,etext);
//									}
//								}
//							}
//						}
//
//						cbGDHead.addValue("adddate","_SYSDATE");
//					}
//					boolean bFlag = true;
//					bFlag = dbo.execute(cbGDHead) && dbo.execute(cbGDBody);
//
//					//��λ
//					dbo.execute("update ie_gdbody set ljver='0' where ljver is null and oper_flag=-1");
//					//���ĵ�λ��Ϣ��������,���unit<>054��Ҫ��1000,���򲻳�1000
//					dbo.execute("update ie_gdbody set quantity=quantity*1000 where sldw<>'054' and oper_flag=-1");
//					
//					String[] asql = new String[8];
//					//����,���պ�Ĺ�������,��������Ѿ���ʹ��,���ٽ���,ɾ���½��յĹ���
//					asql[0] = "Delete From  ie_gdhead t \n"
//							+"Where t.oper_flag=-1 \n"
//							+"And Exists (Select job_order From ie_gdhead s Where s.oper_flag=0  And s.GD_FLAG=1  And s.job_order=t.job_order) ";
//					asql[1] = "Delete From  ie_gdbody \n"
//							+"Where ie_gdbody.oper_flag=-1  \n"
//							+"And Exists (Select job_order From ie_gdhead s Where s.oper_flag=0  And s.GD_FLAG=1 And s.job_order=ie_gdbody.job_order) \n";
//
//					asql[2] = "Update ie_gdhead t \n"
//							+ "Set remain=remain-(Select s.quantity-s.remain From ie_gdhead s Where s.oper_flag=0 And s.job_order=t.job_order) \n"
//							+ "Where t.oper_flag=-1 \n"
//							+ "And Exists (Select job_order From ie_gdhead g Where g.oper_flag=0 And g.job_order=t.job_order)";
//
//					asql[3] = "Delete From ie_gdhead t  \n"
//							+ "Where t.oper_flag=0  \n"
//							+ "And Exists (Select job_order From ie_gdhead s Where s.oper_flag=-1 And s.job_order=t.job_order)";
//
//					asql[4] = "Delete From ie_gdbody  \n"
//							+ "Where ie_gdbody.oper_flag=0 \n" 
//							+ "And Exists (Select job_order From ie_gdhead s Where s.oper_flag=-1 And s.job_order=ie_gdbody.job_order)";
//					asql[5] = "UPDATE ie_gdbody SET ISSUED=ISSUED/1000,REQUIRED=REQUIRED/1000 where SLDW='054' And oper_flag=-1";
//					asql[6] = "Update ie_gdhead Set oper_flag=0 Where oper_flag=-1";
//					asql[7] = "Update ie_gdbody Set oper_flag=0 Where oper_flag=-1";
//					
//					bFlag = bFlag && dbo.execute(asql);
//					
//					//ɾ���ļ�
//					if (bFlag) {
//						File f = new File(MAIN_DIR+"/gd/"+(String)vecFileList.get(i));
//						boolean bdel = f.delete();
//						multicommit();
//					}
//				}
//				catch(Exception e) {
//					e.printStackTrace();
//					multirollback();
//				}
//			}
//			commit();
//			return true;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//			return false;
//		}
//	}
//	
//	/*
//	 * ���� - �ϼ�
//	 * Ŀ¼: balj
//	 */
//	private boolean getBALJ() {
//		try {
//			//search file list
//			Vector vecFileList = getFileList("balj");
//			
//			DataBaseObject dbo = getDataBaseObject();
//			
//			for (int i=0; i < vecFileList.size(); i++) {
//				try {
//					boolean bFlag = true;
//					if (i == 0) {
//						dbo.execute("Delete From ie_lj Where adddate<Sysdate-10 And oper_flag=0 And wlflag='N'");
//						multicommit();
//					}
//					
//					SAXReader saxReader = new SAXReader();
//					Document document = saxReader.read(MAIN_DIR+"/balj/"+(String)vecFileList.get(i));
//					
//					Element root = document.getRootElement();
//					//�ϼ�
//					Iterator iter_root = root.elementIterator();
//					while (iter_root.hasNext()) {
//						Element element_entry = (Element)iter_root.next();
//						//�ϼ���
//						Iterator iter_entry = element_entry.elementIterator();
//						CommonBean cbBALJ = new CommonBean();
//						cbBALJ.addValue("id",com.aiyun.common.util.Oid.getOid());
//						while (iter_entry.hasNext()) {
//							Element element_item = (Element)iter_entry.next();
//							if (element_item.getName().equalsIgnoreCase("ljno")) cbBALJ.addValue("ljno",element_item.getText());
//							else if (element_item.getName().equalsIgnoreCase("organization")) cbBALJ.addValue("organization",element_item.getText());
//							else if (element_item.getName().equalsIgnoreCase("wlflag")) {
//								String s_wlflag = element_item.getText();
//								if (s_wlflag.equalsIgnoreCase("m")) s_wlflag = "B";
//								cbBALJ.addValue("wlflag",s_wlflag);
//							} 
//							else if (element_item.getName().equalsIgnoreCase("cpflag")) {
//								String cpflag=element_item.getText();
//								if (cpflag.equalsIgnoreCase("krh") || cpflag.equalsIgnoreCase("roas"))
//									cbBALJ.addValue("cpflag","1");
//								else
//									cbBALJ.addValue("cpflag","0");
//							} 
//							else if (element_item.getName().equalsIgnoreCase("sname")) cbBALJ.addValue("spname",element_item.getText());
//							else if (element_item.getName().equalsIgnoreCase("model")) cbBALJ.addValue("guige",element_item.getText());
//							//��λ
//							else if (element_item.getName().equalsIgnoreCase("unit")){
//									String strUnit=element_item.getText();
//									cbBALJ.addValue("unit",(String)hmUnit.get(strUnit.toUpperCase()));																		
//							}
//							 
//							else if (element_item.getName().equalsIgnoreCase("modify_mark")) {
//								cbBALJ.addValue("modify_mark",element_item.getText());
//							} 
//							
//						}
//						cbBALJ.addValue("adddate","_SYSDATE");
//						cbBALJ.addValue("oper_flag","-1");
//						
//						if (cbBALJ.getValue("wlflag")!=null && !cbBALJ.getValue("wlflag").equals("")) {
//							cbBALJ.setBeanName("ie_lj");
//							cbBALJ.setAttribute("insert");
//							bFlag = bFlag && dbo.execute(cbBALJ);
//						}
//					}
//					
//					bFlag = bFlag && dbo.execute("update ie_lj set oper_flag=1 where oper_flag=0 and exists (select ljno from ba_dzzc_lj_qy balj where balj.ljno=ie_lj.ljno)");
//					bFlag = bFlag && dbo.execute("delete from ie_lj lj0 where oper_flag=0 and exists (select ljno from ie_lj ljn where oper_flag=-1 and ljn.ljno=lj0.ljno)");
//					bFlag = bFlag && dbo.execute("update ie_lj set modify_mark=3 where oper_flag=-1 and modify_mark=1");
//					bFlag = bFlag && dbo.execute("update ie_lj set oper_flag=0 where oper_flag=-1");
//					
//					//ɾ���ļ�
//					if (bFlag) {
//						File f = new File(MAIN_DIR+"/balj/"+(String)vecFileList.get(i));
//						boolean bdel = f.delete();
//						multicommit();
//					}
//				}
//				catch(Exception e) {
//					e.printStackTrace();
//					multirollback();
//				}
//			}
//			commit();
//			return true;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//			return false;
//		}
//	}
//	/*
//		 * �ϼ��滻��ϵ
//		 * Ŀ¼: ljchange
//		 */
//		private boolean getLJChange() {
//			try {
//				//search file list
//				Vector vecFileList = getFileList("wldt");
//			
//				DataBaseObject dbo = getDataBaseObject();
//			
//				for (int i=0; i < vecFileList.size(); i++) {
//					try {
//						boolean bFlag = true;
//						
//					
//						SAXReader saxReader = new SAXReader();
//						Document document = saxReader.read(MAIN_DIR+"/wldt/"+(String)vecFileList.get(i));
//					
//						Element root = document.getRootElement();
//						//�ϼ��滻
//						Iterator iter_root = root.elementIterator();
//						while (iter_root.hasNext()) {
//							Element element_entry = (Element)iter_root.next();
//							//�ϼ���
//							Iterator iter_entry = element_entry.elementIterator();
//							CommonBean cbLJ = new CommonBean();
//							cbLJ.addValue("id",com.aiyun.common.util.Oid.getOid());
//							while (iter_entry.hasNext()) {
//								Element element_item = (Element)iter_entry.next();
//								if (element_item.getName().equalsIgnoreCase("ljno")) cbLJ.addValue("ljno",element_item.getText());
//								else if (element_item.getName().equalsIgnoreCase("organization")) cbLJ.addValue("organization",element_item.getText());
//								else if (element_item.getName().equalsIgnoreCase("subno")) cbLJ.addValue("subno",element_item.getText());
//													 
//								else if (element_item.getName().equalsIgnoreCase("modify_mark")) {
//									cbLJ.addValue("modify_mark",element_item.getText());
//								} 
//							
//							}
//							cbLJ.addValue("adddate","_SYSDATE");
//							cbLJ.addValue("oper_flag","-1");
//						
//							if (cbLJ.getValue("ljno")!=null && !cbLJ.getValue("ljno").equals("")) {
//								cbLJ.setBeanName("ie_ljchange");
//								cbLJ.setAttribute("insert");
//								bFlag = bFlag && dbo.execute(cbLJ);
//							}
//						}
//					
//						//bFlag = bFlag && dbo.execute("delete from ie_ljchange lj0 where oper_flag=0 and exists (select ljno from ie_ljchange ljn where oper_flag=-1 and ljn.ljno=lj0.ljno and ljn.subno=lj0.subno and ljn.organization=lj0.organization)");
//						bFlag = bFlag && dbo.execute("delete from ie_ljchange lj0 where oper_flag=0 and exists (select ljno from ie_ljchange ljn where oper_flag=-1 and ljn.ljno=lj0.ljno and ljn.subno=lj0.subno)");
//						bFlag = bFlag && dbo.execute("update ie_ljchange set modify_mark=3 where oper_flag=-1 and modify_mark=1");
//						bFlag = bFlag && dbo.execute("update ie_ljchange set oper_flag=0 where oper_flag=-1");
//					
//						//ɾ���ļ�
//						if (bFlag) {
//							File f = new File(MAIN_DIR+"/wldt/"+(String)vecFileList.get(i));
//							boolean bdel = f.delete();
//							multicommit();
//						}
//					}
//					catch(Exception e) {
//						e.printStackTrace();
//						multirollback();
//					}
//				}
//				commit();
//				return true;
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//				rollback();
//				return false;
//			}
//		}
//	
//	/*
//	 * ���� - ��Ʒ
//	 * Ŀ¼: bacp
//	 */
//	private boolean getBACP() {
//		try {
//			//search file list
//			Vector vecFileList = getFileList("bacp");
//		
//			DataBaseObject dbo = getDataBaseObject();
//			for (int i=0; i < vecFileList.size(); i++) {
//				try {
//					boolean bFlag = true;
//					SAXReader saxReader = new SAXReader();
//					Document document = saxReader.read(MAIN_DIR+"/bacp/"+(String)vecFileList.get(i));
//					Element root = document.getRootElement();
//					//��Ʒ
//					Iterator iter_root = root.elementIterator();
//					while (iter_root.hasNext()) {
//						Element element_entry = (Element)iter_root.next();
//						//��Ʒ����Ϣ
//						Iterator iter_entry = element_entry.elementIterator();
//						CommonBean cbBACP = new CommonBean();
//						cbBACP.addValue("id",com.aiyun.common.util.Oid.getOid());
//						while (iter_entry.hasNext()) {
//							Element element_item = (Element)iter_entry.next();
//							if (element_item.getName().equalsIgnoreCase("cpno")) cbBACP.addValue("cpno",element_item.getText());
//							else if (element_item.getName().equalsIgnoreCase("version")) cbBACP.addValue("version",element_item.getText());
//							else if (element_item.getName().equalsIgnoreCase("organization")) cbBACP.addValue("organization",element_item.getText());
//							else if (element_item.getName().equalsIgnoreCase("cpflag")) cbBACP.addValue("cpflag",element_item.getText());
//							else if (element_item.getName().equalsIgnoreCase("sname")) cbBACP.addValue("spname",element_item.getText().replaceAll("'"," "));
//							else if (element_item.getName().equalsIgnoreCase("model")) cbBACP.addValue("guige",element_item.getText());
//							else if (element_item.getName().equalsIgnoreCase("unit")) {
//								cbBACP.addValue("unit",(String)hmUnit.get(element_item.getText().toUpperCase()));
//							} 
//							else if (element_item.getName().equalsIgnoreCase("modify_mark")) cbBACP.addValue("modify_mark",element_item.getText());
//							//�Ķ�,�����ֶ�PURCHASEFLAG
//							else if (element_item.getName().equalsIgnoreCase("PURCHASEFLAG")) cbBACP.addValue("purchaseflag",element_item.getText());
//							
//						}
//						cbBACP.addValue("adddate","_SYSDATE");
//						cbBACP.addValue("oper_flag","0");
//						
//						cbBACP.setBeanName("ie_cp");
//						cbBACP.setAttribute("insert");
//						bFlag = bFlag && dbo.execute(cbBACP);
//						
//					}
//					//ɾ���ļ�
//					if (bFlag) {
//						File f = new File(MAIN_DIR+"/bacp/"+(String)vecFileList.get(i));
//						boolean bdel = f.delete();
//						multicommit();
//					}
//				}
//				catch(Exception e) {
//					e.printStackTrace();
//					multirollback();
//				}
//			}
//			commit();
//			return true;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//			return false;
//		}
//	}
//	
//	/*
//	 * ���� - BOM
//	 * Ŀ¼: babom
//	 */
//	private boolean getBABOM() {
//		try {
//			//search file list
//			Vector vecFileList = getFileList("babom");
//		
//			DataBaseObject dbo = getDataBaseObject();
//			for (int i=0; i < vecFileList.size(); i++) {
//				try {
//					boolean bFlag = true;
//					
//					bgetbom = true;	//�ӵ���bom����
//					
//					SAXReader saxReader = new SAXReader();
//					Document document = saxReader.read(MAIN_DIR+"/babom/"+(String)vecFileList.get(i));
//					Element root = document.getRootElement();
//					//BOM
//					Iterator iter_root = root.elementIterator();
//					CommonBean cbBABom = new CommonBean();
//					
//					while (iter_root.hasNext()) {
//						Element element_entry = (Element)iter_root.next();
//						//BOM
//						Iterator iter_entry = element_entry.elementIterator();
//						cbBABom.addValue("id",com.aiyun.common.util.Oid.getOid());
//						String cpno = null;
//						String ver = null;
//						while (iter_entry.hasNext()) {
//							Element element_item = (Element)iter_entry.next();
//							if (element_item.getName().equalsIgnoreCase("cpno")) cbBABom.addValue("cpno",element_item.getText());
//							else if (element_item.getName().equalsIgnoreCase("version")) cbBABom.addValue("version",element_item.getText());
//							else if (element_item.getName().equalsIgnoreCase("ljno")) cbBABom.addValue("ljno",element_item.getText());
//							else if (element_item.getName().equalsIgnoreCase("quantity")) cbBABom.addValue("dh",element_item.getText());
//							else if (element_item.getName().equalsIgnoreCase("organization")) cbBABom.addValue("organization",element_item.getText());
//							else if (element_item.getName().equalsIgnoreCase("modify_mark")) cbBABom.addValue("modify_mark",element_item.getText());
//						}
//						cbBABom.addValue("adddate","_SYSDATE");
//						cbBABom.addValue("oper_flag","-1");
//					}
//					
//					//����
//					cbBABom.setBeanName("ie_bom");
//					cbBABom.setAttribute("insert");
//					bFlag = bFlag && dbo.execute(cbBABom);
//					
//					String sql = null;
//					sql = "Delete From ba_dzzc_bom \n"
//						+ "Where Exists ( \n"
//						+ "Select cpno  \n"
//						+ "From ie_bom  \n"
//						+ "Where oper_flag=-1 And ie_bom.cpno=ba_dzzc_bom.cpno And ie_bom.version=ba_dzzc_bom.version And ie_bom.ljno=ba_dzzc_bom.ljno \n"
//						+ ")";
//					bFlag = bFlag && dbo.execute(sql);
//					
//					sql = "Delete From ba_dzzc_bom_qy  \n"
//						+ "Where Exists ( \n"
//						+ "Select cpno  \n"
//						+ "From ie_bom  \n"
//						+ "Where oper_flag=-1 And ie_bom.cpno=ba_dzzc_bom_qy.cpno And ie_bom.version=ba_dzzc_bom_qy.version And ie_bom.ljno=ba_dzzc_bom_qy.ljno \n"
//						+ ")";
//					bFlag = bFlag && dbo.execute(sql);
//					
//					sql = "Delete From ie_bom t Where oper_flag<>-1 And Exists (Select cpno From ie_bom s Where oper_flag=-1 And s.cpno=t.cpno And s.version=t.version And s.ljno=t.ljno)";
//					bFlag = bFlag && dbo.execute(sql);
//					
//					sql = "Delete From ie_bom Where oper_flag=-1 And modify_mark=2";
//					bFlag = bFlag && dbo.execute(sql);
//					
//					sql = "Update ie_bom Set modify_mark=3 Where oper_flag=-1 And modify_mark=1";
//					bFlag = bFlag && dbo.execute(sql);
//					
//					sql = "Update ie_bom Set oper_flag=0 Where oper_flag=-1";
//					bFlag = bFlag && dbo.execute(sql);
//
//					//ɾ���ļ�
//					if (bFlag) {
//						File f = new File(MAIN_DIR+"/babom/"+(String)vecFileList.get(i));
//						boolean bdel = f.delete();
//						multicommit();
//					}
//				}
//				catch(Exception e) {
//					e.printStackTrace();
//					multirollback();
//				}
//			}
//			commit();
//			return true;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//			return false;
//		}
//	}
//	
//	//���Ȳ���������Ϣ,�Զ���bom����
//	private void autoBom() {
//		try {
//			cAutoBom();
//			
//			release();
//			return;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//			return;
//		}
//	}
//	
//	//���Ȳ���������Ϣ,�Զ���bom����
//	public void cAutoBom() {
//		try {
//			DataBaseObject dbo = getDataBaseObject();
//			
//			//���¿�ʼ����bom����
//			dbo.execute("delete from ba_dzzc_bom_mid");
//			
//			//����ie_bom,��Ϊ�Զ�����bom����Դ��Ȼ��ie_bom
//			StringBuffer sbSQL = new StringBuffer();
//			sbSQL.append("INSERT INTO BA_DZZC_BOM_QY (ID,CPNO,VERSION,ZCH,LJNO,DH,ADDDATE) \n");
//			sbSQL.append("SELECT sys_guid(),CPNO,VERSION,sys_zch.zch,LJNO,DH,Sysdate \n");
//			sbSQL.append("FROM ie_bom iebom \n");
//			sbSQL.append("Left Join sys_zch On 1>0 And sys_zch.bdefault=1 \n");
//			sbSQL.append("Where iebom.oper_flag=0 \n");
//			sbSQL.append("And Exists (Select cpno From ba_dzzc_cp_qy bacp Where bacp.cpno=iebom.cpno And bacp.version=iebom.version) \n");
//			sbSQL.append("And (Exists (Select ljno From ba_dzzc_lj_qy balj Where balj.ljno=iebom.ljno) \n");
//			sbSQL.append("Or Exists (Select cpno From ba_dzzc_cp_qy bacp Where bacp.cpno=iebom.ljno) \n");
//			sbSQL.append(")");
//			dbo.execute(sbSQL.toString());
//			
//			sbSQL = new StringBuffer();
//			sbSQL.append("Insert Into ba_dzzc_bom_ver (id,guige,cpno) \n");
//			sbSQL.append("Select sys_guid(),a_ver.* From ( \n");
//			sbSQL.append("Select Distinct cp.guige,cp.cpno \n");
//			sbSQL.append("From ba_dzzc_bom_qy bom \n");
//			sbSQL.append("Inner Join ba_dzzc_cp_qy cp On bom.cpno=cp.cpno And bom.version=cp.version \n");
//			sbSQL.append("Where Not Exists (Select cpno From ba_dzzc_bom_ver bver Where bver.guige=cp.guige And bver.cpno=cp.cpno) \n"); 
//			sbSQL.append("And (Not (Exists (Select ljno From ba_dzzc_bom_qy bb Where bb.ljno=cp.cpno) And cp.version='0') \n");
//			sbSQL.append("Or Exists (Select ljno From ba_dzzc_lj_qy roalj Where roalj.ljno=cp.cpno)) \n");
//			sbSQL.append(") a_ver");
//			dbo.execute(sbSQL.toString());
//			//�������汾��
//			CommonBean cbVer = dbo.getData("Select * From ba_dzzc_bom_ver tver Where tver.ver=0");
//			for (int i=0; i<cbVer.getRowNum(); i++) {
//				dbo.execute("Update ba_dzzc_bom_ver tver set ver=(Select nvl(max(ver)+1,1) From ba_dzzc_bom_ver fver Where fver.guige=tver.guige) Where tver.id='"+cbVer.getCellStr(i,"id")+"'"); 
//			}
//			
//			sbSQL = new StringBuffer();
//			sbSQL.append("Insert Into ba_dzzc_bom_mid (cpno,version,gbver,guige,ljno,dh,oper_flag) \n");
//			sbSQL.append("Select bom.cpno,bom.version,ver.ver,cp.guige,bom.ljno,bom.dh,0  \n");
//			sbSQL.append("From ba_dzzc_bom_qy bom \n");
//			sbSQL.append("Left Join ba_dzzc_cp_qy cp On cp.cpno=bom.cpno And cp.version=bom.version \n");
//			sbSQL.append("Inner Join (Select cpno,guige,Max(version) As version From ba_dzzc_cp_qy Group By cpno,guige) mcp On mcp.cpno=bom.cpno And mcp.version=bom.version \n");
//			sbSQL.append("Inner Join ba_dzzc_bom_ver ver On ver.guige=mcp.guige And ver.cpno=mcp.cpno And ver.oper_flag=0");
//			dbo.execute(sbSQL.toString());
//			
//			dbo.execute("Update ba_dzzc_bom_ver Set oper_flag=1 where oper_flag=0");
//			
//			//��ʶ�Ѵ�����Ľӿ�����
//			sbSQL = new StringBuffer();
//			sbSQL.append("Update ie_bom iebom Set oper_flag=1 \n");
//			sbSQL.append("Where iebom.oper_flag=0 \n");
//			sbSQL.append("And Exists (Select cpno From ba_dzzc_cp_qy bacp Where bacp.cpno=iebom.cpno And bacp.version=iebom.version) \n");
//			sbSQL.append("And ( \n");
//			sbSQL.append("Exists (Select ljno From ba_dzzc_lj_qy balj Where balj.ljno=iebom.ljno) \n");
//			sbSQL.append("Or Exists (Select cpno From ba_dzzc_cp_qy bacp Where bacp.cpno=iebom.ljno) \n");
//			sbSQL.append(")");
//			dbo.execute(sbSQL.toString());
//					
//			//���²���Ʒ
//			while (true) {
//				CommonBean cbDup = dbo.getData("select ljno from ba_dzzc_bom_mid mid where oper_flag=0 and exists (select cpno from ba_dzzc_cp_qy bacp where mid.ljno=bacp.cpno)");
//				if (cbDup.getRowNum() == 0) break;
//				
//				sbSQL = new StringBuffer();
//				sbSQL.append("Insert Into ba_dzzc_bom_mid (cpno,version,gbver,guige,ljno,dh) \n");
//				sbSQL.append("Select mid.ljno,'0',gbver,mid.guige,bom.ljno,bom.dh \n");
//				sbSQL.append("From ba_dzzc_bom_mid mid \n");
//				sbSQL.append("Left Join ba_dzzc_bom_qy bom On bom.cpno=mid.ljno And bom.version='0' \n");
//				sbSQL.append("Where Exists (Select cpno From ba_dzzc_cp_qy cp Where cp.cpno=mid.ljno And cp.version='0') And mid.oper_flag=0");
//				dbo.execute(sbSQL.toString());
//				
//				dbo.execute("Update ba_dzzc_bom_mid mid Set oper_flag=1 Where Exists (Select cpno From ba_dzzc_cp_qy cp Where cp.cpno=mid.ljno And cp.version='0') And Exists (Select ljno From ba_dzzc_lj_qy lj Where lj.ljno=mid.ljno) And mid.oper_flag=0");
//				dbo.execute("Delete From ba_dzzc_bom_mid mid Where Exists (Select cpno From ba_dzzc_cp_qy cp Where cp.cpno=mid.ljno And cp.version='0') And mid.oper_flag=0");
//				
//				dbo.execute("Update ba_dzzc_bom_mid Set oper_flag=1 Where oper_flag=0");
//				dbo.execute("Update ba_dzzc_bom_mid Set oper_flag=0 Where oper_flag=-1");
//			}
//			
//			dbo.execute("Update ba_dzzc_bom_mid mid set oper_flag=1");
//			
//			sbSQL = new StringBuffer();
//			sbSQL.append("Insert Into ba_dzzc_bom_gb (Id,cpno,version,ljno,dh,sh,oper_flag,exchged,guige) \n");
//			sbSQL.append("Select sys_guid(),gbbom.guige,gbbom.gbver,gbbom.ljno,gbbom.dh,0,0,0,gbbom.gbver \n");
//			sbSQL.append("From ( \n");
//			sbSQL.append("Select guige,gbver,ljno,sum(dh) As dh \n");
//			sbSQL.append("From ba_dzzc_bom_mid \n");
//			sbSQL.append("Where ljno is not null \n");
//			sbSQL.append("Group By guige,gbver,ljno \n");
//			sbSQL.append(") gbbom");
//			dbo.execute(sbSQL.toString());
//			
//			sbSQL = new StringBuffer();
//			sbSQL.append("delete from ba_dzzc_bom_mid");
//			dbo.execute(sbSQL.toString());
//			//���³�Ʒ�����ǡ����Ʒ���Ĵ����־��1
//			dbo.execute("Update ba_dzzc_bom_gb  set exchged=1 where exchged=0 and guige in (select guige from ba_dzzc_cp_qy where spname='���Ʒ')");
//			multicommit();	
//		
//			return;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//			return;
//		}
//	}
//	
//	/*
//	 * ��ⵥ��Ϣ
//	 * Ŀ¼: gr
//	 */
//	private boolean getGR() {
//		try {
//			//search file list
//			Vector vecFileList = getFileList("gr");
//		
//			DataBaseObject dbo = getDataBaseObject();
//			for (int i=0; i < vecFileList.size(); i++) {
//				try {
//					boolean bFlag = true;
//					SAXReader saxReader = new SAXReader();
//					Document document = saxReader.read(MAIN_DIR+"/gr/"+(String)vecFileList.get(i));
//					Element root = document.getRootElement();
//					//��ⵥ��Ϣ
//					Iterator iter_root = root.elementIterator();
//					boolean bHasData = false;
//					while (iter_root.hasNext()) {
//						bHasData = true;
//						Element element_entry = (Element)iter_root.next();
//						//��ⵥ��Ϣ��
//						Iterator iter_entry = element_entry.elementIterator();
//						CommonBean cbGR = new CommonBean();
//						cbGR.addValue("id",com.aiyun.common.util.Oid.getOid());
//						while (iter_entry.hasNext()) {
//							Element element_item = (Element)iter_entry.next();
//							String ename = element_item.getName();
//							String etext = element_item.getText();
//							if (ename.equalsIgnoreCase("organization") && etext.equalsIgnoreCase("ac")) {
//								cbGR.addValue(ename.toLowerCase(),"VP");
//							}
//							else if (ename.equalsIgnoreCase("quantity")) {
//								//cbGR.addValue("quantity",StrTool.str2double(etext)/1000+"");
//								cbGR.addValue("quantity",etext);
//							}
//							//����,�����λ��KG,���ó�1000,�����1000
//							else if (ename.equalsIgnoreCase("unit")) {
//								cbGR.addValue(ename.toLowerCase(),(String)hmUnit.get(etext.toUpperCase()));
//									
//							}
//							else {
//								cbGR.addValue(ename.toLowerCase(),etext);
//							}
//						}
//						cbGR.addValue("adddate","_SYSDATE");
//						
//						
//						if (!cbGR.getValue("organization").equalsIgnoreCase("cs") && !cbGR.getValue("organization").equalsIgnoreCase("rs")) {
//							cbGR.setBeanName("ie_gr");
//							cbGR.setAttribute("insert");
//							bFlag = bFlag && dbo.execute(cbGR);
//						}
//					}
//					
//					//��������
//					String sql = null;
//					if (bFlag && bHasData) {
//						bFlag = bFlag && dbo.execute("Update ie_gr Set bmc_flag='DPY' Where bmc_flag is null And oper_flag=0");
//						
//						//����:��λ��Ϣ��������,���unit=054��Ҫ��1000,���򲻳�1000
//						sql = "UPDATE IE_GR SET QUANTITY=QUANTITY/1000 where UNIT='054' And oper_flag=0";
//						bFlag = bFlag && dbo.execute(sql);
//						
//						//����,���еĲ�����Ҫ����BMC_FLAG��ROA_FLAG��־,���⴦��database_smֻ����PO���յ�����,RMA��������,ֻ��¼��ʷ����
//						sql = "Update DATABASE_SM Set QUANTITY=QUANTITY+ \n"
//						+ "( \n"
//						+ "SELECT SUM(QUANTITY)  \n"
//						+ "FROM IE_GR  \n"
//						+ "WHERE OPER_FLAG=0 And flag=0 AND GR_FLAG='PO' AND BMC_FLAG=DATABASE_SM.BMC_FLAG AND ROA_FLAG=DATABASE_SM.ROA_FLAG AND LJNO=DATABASE_SM.LJNO And ORGANIZATION=DATABASE_SM.Organization And WLFLAG=DATABASE_SM.BSFLAG \n"
//						+ ")  \n"
//						+ "Where LJNO||Organization||BSFLAG||BMC_FLAG||ROA_FLAG In (Select LJNO||Organization||WLFLAG||BMC_FLAG||ROA_FLAG From IE_GR Where OPER_FLAG=0 And flag=0  AND GR_FLAG='PO' \n"
//						+ ") \n";
//						bFlag = bFlag && dbo.execute(sql);
//
//						sql = "INSERT INTO DATABASE_SM  \n"
//						+ "( \n"
//						+ "SELECT LJNO,WLFLAG,ORGANIZATION,SUM(QUANTITY),UNIT,BMC_FLAG,ROA_FLAG  \n"
//						+ "FROM IE_GR  \n"
//						+ "WHERE OPER_FLAG=0 And flag=0 AND GR_FLAG='PO' AND LJNO||ORGANIZATION||WLFLAG||BMC_FLAG||ROA_FLAG NOT IN (SELECT LJNO||ORGANIZATION||BSFLAG||BMC_FLAG||ROA_FLAG FROM DATABASE_SM)  \n"
//						+ "Group By LJNO,WLFLAG,Organization,UNIT,BMC_FLAG,ROA_FLAG \n"
//						+ ") \n";
//						bFlag = bFlag && dbo.execute(sql);
//
//						sql = "Update DATABASE_tl Set QUANTITY=QUANTITY+ \n"
//						+ "( \n"
//						+ "SELECT SUM(QUANTITY)  \n"
//						+ "FROM IE_GR  \n"
//						+ "WHERE OPER_FLAG=0 And flag=1 AND GR_FLAG='PO' AND BMC_FLAG=DATABASE_TL.BMC_FLAG AND ROA_FLAG=DATABASE_TL.ROA_FLAG  AND LJNO=database_tl.LJNO And ORGANIZATION=database_tl.Organization And WLFLAG=DATABASE_tl.BSFLAG \n"
//						+ ")  \n"
//						+ "Where exists (Select LJNO From IE_GR Where OPER_FLAG=0 and flag=1  AND GR_FLAG='PO' And ie_gr.BMC_FLAG=DATABASE_TL.BMC_FLAG AND ie_gr.ROA_FLAG=DATABASE_TL.ROA_FLAG  AND ie_gr.LJNO=database_tl.LJNO And ie_gr.ORGANIZATION=database_tl.Organization And ie_gr.WLFLAG=DATABASE_tl.BSFLAG \n"
//						//+ "Where LJNO||Organization||BSFLAG||BMC_FLAG||ROA_FLAG In (Select LJNO||Organization||WLFLAG||BMC_FLAG||ROA_FLAG From IE_GR Where OPER_FLAG=0 and flag=1  AND GR_FLAG='PO' \n"
//						+ ") \n";
//						bFlag = bFlag && dbo.execute(sql);
//
//						sql = "INSERT INTO database_tl (LJNO,BSFLAG,ORGANIZATION,QUANTITY,BMC_FLAG,ROA_FLAG )  \n"
//						+ "( \n"
//						+ "SELECT LJNO,WLFLAG,ORGANIZATION,SUM(QUANTITY),BMC_FLAG,ROA_FLAG   \n"
//						+ "FROM IE_GR  \n"
//						+ "WHERE OPER_FLAG=0 And flag=1 AND GR_FLAG='PO' and wlflag is not null AND Not Exists (SELECT ljno FROM database_tl stl Where stl.ljno=ie_gr.ljno And stl.Organization=ie_gr.Organization And stl.bsflag=ie_gr.wlflag And stl.bmc_flag=ie_gr.bmc_flag And stl.roa_flag=ie_gr.roa_flag)  \n"
//						//+ "WHERE OPER_FLAG=0 And flag=1 AND GR_FLAG='PO' AND LJNO||ORGANIZATION||WLFLAG||BMC_FLAG||ROA_FLAG NOT IN (SELECT LJNO||ORGANIZATION||BSFLAG||BMC_FLAG||ROA_FLAG FROM database_tl)  \n"
//						+ "Group By LJNO,WLFLAG,Organization,BMC_FLAG,ROA_FLAG  \n"
//						+ ") \n";
//						bFlag = bFlag && dbo.execute(sql);
//						
//						sql = "UPDATE IE_GR SET OPER_FLAG=1 WHERE OPER_FLAG=0";
//						bFlag = bFlag && dbo.execute(sql);
//					}
//					
//					//ɾ���ļ�
//					if (bFlag) {
//						File f = new File(MAIN_DIR+"/gr/"+(String)vecFileList.get(i));
//						boolean bdel = f.delete();
//						multicommit();
//					}
//				}
//				catch(Exception e) {
//					e.printStackTrace();
//					multirollback();
//				}
//			}
//			commit();
//			return true;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//			return false;
//		}
//	}
//	
//	/*
//	 * ���ⵥ��Ϣ
//	 * Ŀ¼: gi
//	 */
//	private boolean getGI() {
//		try {
//			//search file list
//			Vector vecFileList = getFileList("gi");
//		
//			DataBaseObject dbo = getDataBaseObject();
//			for (int i=0; i < vecFileList.size(); i++) {
//				try {
//					boolean bFlag = true;
//					SAXReader saxReader = new SAXReader();
//					Document document = saxReader.read(MAIN_DIR+"/gi/"+(String)vecFileList.get(i));
//					Element root = document.getRootElement();
//					//���ⵥ��Ϣ
//					Iterator iter_root = root.elementIterator();
//					boolean bHasData = false;
//					while (iter_root.hasNext()) {
//						bHasData = true;
//						Element element_entry = (Element)iter_root.next();
//						//���ⵥ��Ϣ��
//						Iterator iter_entry = element_entry.elementIterator();
//						CommonBean cbGI = new CommonBean();
//						cbGI.addValue("id",com.aiyun.common.util.Oid.getOid());
//						while (iter_entry.hasNext()) {
//							Element element_item = (Element)iter_entry.next();
//							String ename = element_item.getName();
//							String etext = element_item.getText();
//							if (ename.equalsIgnoreCase("organization") && etext.equalsIgnoreCase("ac")) {
//								cbGI.addValue(ename.toLowerCase(),"VP");
//							}
//							else if (ename.equalsIgnoreCase("quantity")) {
//								//cbGI.addValue("quantity",StrTool.str2double(etext)*-1/1000+"");
//								cbGI.addValue("quantity",etext);
//							}
//							//��λ
//							else if (ename.equalsIgnoreCase("unit")) {
//								cbGI.addValue(ename.toLowerCase(),(String)hmUnit.get(etext.toUpperCase()));
//							}
//							else {
//								cbGI.addValue(ename.toLowerCase(),etext);
//							}
//						}
//						cbGI.addValue("adddate","_SYSDATE");
//						
//						if (!cbGI.getValue("organization").equalsIgnoreCase("cs") && !cbGI.getValue("organization").equalsIgnoreCase("rs")) {
//							cbGI.setBeanName("ie_gi");
//							cbGI.setAttribute("insert");
//							bFlag = bFlag && dbo.execute(cbGI);
//						}
//					}
//					
//					
//
//					//��������
//					String sql = null;
//					if (bFlag && bHasData) {
//						bFlag = bFlag && dbo.execute("Update ie_gi Set bmc_flag='DPY' Where bmc_flag is null And oper_flag=0");
//						
//						sql = "Update ie_gi set version=0 where version Is null And oper_flag=0";
//						bFlag = bFlag && dbo.execute(sql);
//						//���ӵ�λ��Ϣ��������,���unit=054��Ҫ��1000,���򲻳�1000
//						sql = "UPDATE IE_GI SET QUANTITY=QUANTITY*(-1) where  oper_flag=0";
//						bFlag = bFlag && dbo.execute(sql);
//						sql = "UPDATE IE_GI SET QUANTITY=QUANTITY/1000 where UNIT='054' And oper_flag=0";
//						bFlag = bFlag && dbo.execute(sql);
//						//����,���еĲ�����Ҫ����BMC_FLAG��ROA_FLAG��־,���⴦��flag��־����database_sm,database_tl,database_cprk
//						//FLAG=0,����ԭ���Ͽ�DATABASE_SM
//						sql = "Update DATABASE_SM Set QUANTITY=QUANTITY- \n"
//						+ "( \n"
//						+ "SELECT SUM(QUANTITY)  \n"
//						+ "FROM IE_GI  \n"
//						+ "WHERE OPER_FLAG=0 And flag=0  AND BMC_FLAG=DATABASE_SM.BMC_FLAG  AND ROA_FLAG=DATABASE_SM.ROA_FLAG AND CPNO=DATABASE_SM.LJNO And ORGANIZATION=DATABASE_SM.Organization And WLFLAG=DATABASE_SM.BSFLAG \n"
//						+ ")  \n"
//						+ "Where LJNO||Organization||BSFLAG||BMC_FLAG||ROA_FLAG In (Select CPNO||Organization||WLFLAG||BMC_FLAG||ROA_FLAG From IE_GI Where OPER_FLAG=0 And flag=0 \n"
//						+ ") \n";
//						bFlag = bFlag && dbo.execute(sql);
//
//						sql = "INSERT INTO DATABASE_SM  \n"
//						+ "( \n"
//						+ "SELECT CPNO,WLFLAG,ORGANIZATION,-SUM(QUANTITY),UNIT,BMC_FLAG,ROA_FLAG  \n"
//						+ "FROM IE_GI  \n"
//						+ "WHERE OPER_FLAG=0 And flag=0  AND CPNO||ORGANIZATION||WLFLAG||BMC_FLAG||ROA_FLAG NOT IN (SELECT LJNO||ORGANIZATION||BSFLAG||BMC_FLAG||ROA_FLAG FROM DATABASE_SM)  \n"
//						+ "Group By CPNO,WLFLAG,Organization,UNIT,BMC_FLAG,ROA_FLAG \n"
//						+ ") \n";
//						bFlag = bFlag && dbo.execute(sql);
//
//						//FLAG=1,����Ͷ�Ͽ�DATABASE_TL
//						sql = "Update DATABASE_tl Set QUANTITY=QUANTITY- \n"
//						+ "( \n"
//						+ "SELECT SUM(QUANTITY)  \n"
//						+ "FROM IE_GI  \n"
//						+ "WHERE OPER_FLAG=0 And flag=1  AND BMC_FLAG=DATABASE_TL.BMC_FLAG AND ROA_FLAG=DATABASE_TL.ROA_FLAG  AND CPNO=database_tl.LJNO And ORGANIZATION=database_tl.Organization And WLFLAG=DATABASE_tl.BSFLAG \n"
//						+ ")  \n"
//						+ "Where LJNO||Organization||BSFLAG||BMC_FLAG||ROA_FLAG In (Select ie_gi.cpNO||ie_gi.Organization||ie_gi.WLFLAG||ie_gi.BMC_FLAG||ie_gi.ROA_FLAG From IE_GI Where OPER_FLAG=0 and flag=1  \n"
//						+ ") \n";
//						bFlag = bFlag && dbo.execute(sql);
//
//						
//						//FLAG=0,����Ͷ�Ͽ�DATABASE_TL ,�ѳ����ϼ��ӵ�Ͷ�Ͽ�
//						sql = "Update DATABASE_tl Set QUANTITY=QUANTITY+ \n"
//						+ "( \n"
//						+ "SELECT SUM(QUANTITY)  \n"
//						+ "FROM IE_GI  \n"
//						+ "WHERE OPER_FLAG=0 And flag=0  AND BMC_FLAG=DATABASE_TL.BMC_FLAG AND ROA_FLAG=DATABASE_TL.ROA_FLAG  AND CPNO=database_tl.LJNO And ORGANIZATION=database_tl.Organization And WLFLAG=DATABASE_tl.BSFLAG \n"
//						+ ")  \n"
//						+ "Where LJNO||Organization||BSFLAG||BMC_FLAG||ROA_FLAG In (Select ie_gi.cpNO||ie_gi.Organization||ie_gi.WLFLAG||ie_gi.BMC_FLAG||ie_gi.ROA_FLAG From IE_GI Where OPER_FLAG=0 and flag=0  \n"
//						+ ") \n";
//						bFlag = bFlag && dbo.execute(sql);							
//
//						
//						sql = "INSERT INTO database_tl (LJNO,BSFLAG,ORGANIZATION,QUANTITY,BMC_FLAG,ROA_FLAG )  \n" 
//							+ "(  \n"
//							+ "SELECT CPNO,WLFLAG,ORGANIZATION,SUM(decode(flag,0,QUANTITY,1,-QUANTITY)),BMC_FLAG,ROA_FLAG    \n"
//							+ "FROM IE_GI   \n"
//							+ "WHERE flag in (0,1) \n"
//							+ "and cpno in (select ljno from ba_dzzc_lj_qy)	 \n"					
//							+ "AND not exists (SELECT ljno FROM database_tl tl where tl.ljno=ie_gi.cpno and tl.organization=ie_gi.organization and tl.bsflag=ie_gi.wlflag and tl.bmc_flag=ie_gi.bmc_flag and tl.roa_flag=ie_gi.roa_flag ) \n"  
//							+ "and OPER_FLAG=0 \n"
//							+ "Group By CPNO,WLFLAG,Organization,BMC_FLAG,ROA_FLAG   \n"
//							+ ")  \n";								
//						
//						bFlag = bFlag && dbo.execute(sql);
//
//						
//						
//						//FLAG=2,�����Ʒ��DATABASE_CPRK
//						sql = "Update DATABASE_CPRK Set QUANTITY=QUANTITY- \n"
//						+ "( \n"
//						+ "SELECT SUM(QUANTITY)  \n"
//						+ "FROM IE_GI  \n"
//						+ "WHERE OPER_FLAG=0 And flag=2  AND BMC_FLAG=DATABASE_CPRK.BMC_FLAG  AND ROA_FLAG=DATABASE_CPRK.ROA_FLAG AND CPNO=DATABASE_CPRK.CPNO And ORGANIZATION=DATABASE_CPRK.Organization And VERSION=DATABASE_CPRK.VERSION \n"
//						+ ")  \n"
//						+ "Where CPNO||Organization||VERSION||BMC_FLAG||ROA_FLAG In (Select CPNO||Organization||VERSION||BMC_FLAG||ROA_FLAG From IE_GI Where OPER_FLAG=0 And flag=2 \n"
//						+ ") \n";
//						bFlag = bFlag && dbo.execute(sql);
//
//						sql = "INSERT INTO DATABASE_CPRK(CPNO,VERSION,ORGANIZATION,QUANTITY,UNIT,BMC_FLAG,ROA_FLAG)  \n"
//						+ "( \n"
//						+ "SELECT CPNO,VERSION,ORGANIZATION,SUM(QUANTITY),UNIT,BMC_FLAG,ROA_FLAG  \n"
//						+ "FROM IE_GI  \n"
//						+ "WHERE OPER_FLAG=0 And flag=2  AND CPNO||ORGANIZATION||VERSION||BMC_FLAG||ROA_FLAG NOT IN (SELECT CPNO||ORGANIZATION||VERSION||BMC_FLAG||ROA_FLAG FROM DATABASE_CPRK)  \n"
//						+ "Group By CPNO,VERSION,Organization,UNIT,BMC_FLAG,ROA_FLAG \n"
//						+ ") \n";
//						bFlag = bFlag && dbo.execute(sql);
//						
//						sql = "UPDATE IE_GI SET OPER_FLAG=1 WHERE OPER_FLAG=0";
//						bFlag = bFlag && dbo.execute(sql);
//					}
//					
//					
//					
//					
//					
////					//��������
////					if (bFlag && bHasData) {
////						String sql = null;
////						sql = "Update ie_gi set version=0 where version Is null And oper_flag=0";
////						bFlag = bFlag && dbo.execute(sql);
////						sql = "Update Database_sm sm Set quantity=quantity-( \n"
////							+ "Select Sum(quantity) From ie_gi Where ie_gi.cpno=sm.ljno And ie_gi.Organization=sm.Organization And ie_gi.wlflag=sm.bsflag And ie_gi.flag=0 And oper_flag=0 \n"
////							+ ") \n"
////							+ "Where Exists ( \n"
////							+ "Select cpno From ie_gi Where ie_gi.cpno=sm.ljno And ie_gi.Organization=sm.Organization And ie_gi.wlflag=sm.bsflag And ie_gi.flag=0 And oper_flag=0 \n"
////							+ ")";
////						bFlag = bFlag && dbo.execute(sql);
////						sql = "UPDATE IE_GI SET OPER_FLAG=1 WHERE OPER_FLAG=0";
////						bFlag = bFlag && dbo.execute(sql);
////					}
//					
//					//ɾ���ļ�
//					if (bFlag) {
//						File f = new File(MAIN_DIR+"/gi/"+(String)vecFileList.get(i));
//						boolean bdel = f.delete();
//						multicommit();
//					}
//				}
//				catch(Exception e) {
//					e.printStackTrace();
//					multirollback();
//				}
//			}
//			commit();
//			return true;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//			return false;
//		}
//	}
//	
//	/*
//	 * ���� - PO��
//	 * Ŀ¼: cp
//	 */
//	private boolean getPO() {
//		try {
//			//search file list
//			Vector vecFileList = getFileList("po");
//		
//			DataBaseObject dbo = getDataBaseObject();
//			for (int i=0; i < vecFileList.size(); i++) {
//				try {
//					boolean bFlag = true;
//					CommonBean cbPO = new CommonBean();
//					cbPO.setBeanName("ie_po");
//					cbPO.setAttribute("insert");
//					
//					SAXReader saxReader = new SAXReader();
//					Document document = saxReader.read(MAIN_DIR+"/po/"+(String)vecFileList.get(i));
//					Element root = document.getRootElement();
//					//��Ʒ
//					Iterator iter_root = root.elementIterator();
//					while (iter_root.hasNext()) {
//						Element element_entry = (Element)iter_root.next();
//						//��Ʒ����Ϣ
//						Iterator iter_entry = element_entry.elementIterator();
//						cbPO.addValue("id",com.aiyun.common.util.Oid.getOid());
//						while (iter_entry.hasNext()) {
//							Element element_item = (Element)iter_entry.next();
//							String ename = element_item.getName();
//							String etext = element_item.getText();
//							if (ename.equalsIgnoreCase("ljno")) {
//								if (etext.length()>30) {
//									cbPO.addValue(ename.toLowerCase(),etext.substring(0,30));
//								}
//								else {
//									cbPO.addValue(ename.toLowerCase(),etext);
//								}
//							}
//							//��λ
//							else if (ename.equalsIgnoreCase("unit")) {
//								cbPO.addValue(ename.toLowerCase(),(String)hmUnit.get(etext.toUpperCase()));
//							}
//							else if (ename.equalsIgnoreCase("quantity")) {
//								//cbPO.addValue(ename.toLowerCase(),StrTool.str2double(etext)/1000+"");
//								//cbPO.addValue("remain",StrTool.str2double(etext)/1000+"");
//								cbPO.addValue(ename.toLowerCase(),etext);
//								cbPO.addValue("remain",etext);
//							}
//							else if (ename.equalsIgnoreCase("cancel_quantity")) {
//								//cbPO.addValue(ename.toLowerCase(),StrTool.str2double(etext)/1000+"");
//								cbPO.addValue(ename.toLowerCase(),etext);
//							}
//							else if (ename.equalsIgnoreCase("price")) {
//								//cbPO.addValue(ename.toLowerCase(),StrTool.str2double(etext)/1000+"");
//								cbPO.addValue(ename.toLowerCase(),etext);
//							}
//							else if (ename.equalsIgnoreCase("shippingto") && etext.equalsIgnoreCase("ac")) {
//								cbPO.addValue(ename.toLowerCase(),"VP");
//							}
//							else {
//								cbPO.addValue(ename.toLowerCase(),etext);
//							}
//						}
//						cbPO.addValue("oper_flag","-1");
//						cbPO.addValue("adddate","_SYSDATE");
//					}
//					if (cbPO.getRowNum()>0) {
//						bFlag = bFlag && dbo.execute(cbPO);
//					}
//					
//					String sql = "";
//					if (bFlag && cbPO.getRowNum()>0) {
//						
//						//����:��λ��Ϣ��������,���unit=054��Ҫ��1000,���򲻳�1000
//						sql = "UPDATE IE_PO SET QUANTITY=QUANTITY/1000,REMAIN=REMAIN/1000,CANCEL_QUANTITY=CANCEL_QUANTITY/1000 where UNIT='054' And oper_flag=-1";
//						bFlag = bFlag && dbo.execute(sql);
//						
//						
//						sql = "UPDATE IE_PO SET CURR=(SELECT ID FROM CL_CURR WHERE SENAME=IE_PO.CURR) WHERE OPER_FLAG=-1";
//						bFlag = bFlag && dbo.execute(sql);
//						sql = "Insert Into cl_vendor (Select distinct supplier,supplier From ie_po Where supplier Not In (Select id From cl_vendor) And supplier Is Not null And  OPER_FLAG=-1)";
//						bFlag = bFlag && dbo.execute(sql);
//
//						sql = "Delete From ie_po \n"
//							+ "Where oper_flag=0 \n"
//							+ "And Exists (Select po_no From ie_po tpo Where modify_mark ='2' And tpo.po_no=ie_po.po_no And tpo.ljno=ie_po.ljno And tpo.wlflag=ie_po.wlflag And tpo.shippingto=ie_po.shippingto)";
//						bFlag = bFlag && dbo.execute(sql);
//						sql = "Update ie_po s Set quantity=(Select sum(quantity) From ie_po t Where t.oper_flag=-1 And t.po_no=s.po_no And t.ljno=s.ljno And t.wlflag=s.wlflag And t.shippingto=s.shippingto) \n"
//							+ ",cancel_quantity=(Select sum(cancel_quantity) From ie_po t Where t.oper_flag=-1 And t.po_no=s.po_no And t.ljno=s.ljno And t.wlflag=s.wlflag And t.shippingto=s.shippingto) \n"
//							+ ",remain=remain-quantity+cancel_quantity+(Select sum(quantity-cancel_quantity) From ie_po t Where t.oper_flag=-1 And t.po_no=s.po_no And t.ljno=s.ljno And t.wlflag=s.wlflag And t.shippingto=s.shippingto) \n"
//							+ "Where s.oper_flag=0 \n"
//							+ "And Exists (Select po_no From ie_po t Where t.oper_flag=-1 And t.po_no=s.po_no And t.ljno=s.ljno And t.wlflag=s.wlflag And t.shippingto=s.shippingto)";
//						bFlag = bFlag && dbo.execute(sql);
//						sql = "Insert Into IE_PO (Id,PO_NO,WLFLAG,LJNO,QUANTITY,CANCEL_QUANTITY,REMAIN,UNIT,PRICE,CURR,BUYER,SUPPLIER,COUNTRY,SHIPPINGTO,IMPORTTYPE,MODIFY_MARK,ADDDATE,OPER_FLAG)  \n"
//							+ "Select max(SYS_GUID()),PO_NO,WLFLAG,LJNO,sum(QUANTITY),sum(CANCEL_QUANTITY),sum(QUANTITY-CANCEL_QUANTITY),max(UNIT),sum(PRICE),max(CURR),max(BUYER),max(SUPPLIER),max(CL_COUNTRY.ID),SHIPPINGTO,max(IMPORTTYPE),'3',max(Sysdate),0  \n"
//							+ "From IE_PO  \n"
//							+ "Left Join CL_COUNTRY On CL_COUNTRY.SENAME=IE_PO.COUNTRY  \n"
//							+ "Where OPER_FLAG=-1 and modify_mark in (1,3) \n"
//							+ "And Not Exists (Select po_no From ie_po tpo Where oper_flag=0 And tpo.po_no=ie_po.po_no And tpo.ljno=ie_po.ljno And tpo.wlflag=ie_po.wlflag And tpo.shippingto=ie_po.shippingto) \n"
//							+ "Group By po_no,wlflag,ljno,shippingto";
//						bFlag = bFlag && dbo.execute(sql);
//						bFlag = bFlag && dbo.execute("Insert Into ie_ora_po (PO_NO,WLFLAG,LJNO,QUANTITY,CANCEL_QUANTITY,UNIT,PRICE,CURR,BUYER,SUPPLIER,COUNTRY,SHIPPINGTO,IMPORTTYPE,MODIFY_MARK,ADDDATE) \n"
//													+"Select PO_NO,WLFLAG,LJNO,QUANTITY,CANCEL_QUANTITY,UNIT,PRICE,CURR,BUYER,SUPPLIER,COUNTRY,SHIPPINGTO,IMPORTTYPE,MODIFY_MARK,ADDDATE \n"
//													+"From ie_po Where oper_flag=-1");
//						bFlag = bFlag && dbo.execute("DELETE FROM IE_PO WHERE OPER_FLAG=-1");
//					}
//					
//					//ɾ���ļ�
//					if (bFlag) {
//						File f = new File(MAIN_DIR+"/po/"+(String)vecFileList.get(i));
//						boolean bdel = f.delete();
//						multicommit();
//					}
//				}
//				catch(Exception e) {
//					e.printStackTrace();
//					multirollback();
//				}
//			}
//			commit();
//			return true;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//			return false;
//		}
//	}
//	
//	/*
//	 * �������ϳ�Ʒ�����Ϣ
//	 * Ŀ¼: cprk
//	 */
//	private boolean getCPRK() {
//		try {
//			//search file list
//			Vector vecFileList = getFileList("cprk");
//		
//			DataBaseObject dbo = getDataBaseObject();
//			for (int i=0; i < vecFileList.size(); i++) {
//				try {
//					boolean bFlag = true;
//					SAXReader saxReader = new SAXReader();
//					Document document = saxReader.read(MAIN_DIR+"/cprk/"+(String)vecFileList.get(i));
//					Element root = document.getRootElement();
//					//�������ϳ�Ʒ�����Ϣ
//					Iterator iter_root = root.elementIterator();
//					boolean bHasData = false;
//					while (iter_root.hasNext()) {
//						bHasData = true;
//						Element element_entry = (Element)iter_root.next();
//						//�������ϳ�Ʒ�����Ϣ��
//						Iterator iter_entry = element_entry.elementIterator();
//						CommonBean cbCPRK = new CommonBean();
//						cbCPRK.addValue("id",com.aiyun.common.util.Oid.getOid());
//						while (iter_entry.hasNext()) {
//							Element element_item = (Element)iter_entry.next();
//							String ename = element_item.getName();
//							String etext = element_item.getText();
//							if (ename.equalsIgnoreCase("organization") && etext.equalsIgnoreCase("ac")) {
//								cbCPRK.addValue(ename.toLowerCase(),"VP");
//							}
//							else if (ename.equalsIgnoreCase("quantity")) {
//								cbCPRK.addValue("quantity",etext);
//							}
//							else if (ename.equalsIgnoreCase("unit")) {
//								cbCPRK.addValue("unit",(String)hmUnit.get(etext.toUpperCase()));
//							}
//							else {
//								cbCPRK.addValue(ename.toLowerCase(),etext);
//							}
//						}
//						cbCPRK.addValue("adddate","_SYSDATE");
//						
//						if (!cbCPRK.getValue("organization").equalsIgnoreCase("cs") && !cbCPRK.getValue("organization").equalsIgnoreCase("rs")) {
//							cbCPRK.setBeanName("ie_cprk");
//							cbCPRK.setAttribute("insert");
//							
//							bFlag = bFlag && dbo.execute(cbCPRK);
//						}
//					}
//					
//					if (bHasData) {
//						//�ϼ�����
//						String[] t_sql = new String[3];
//						
//						//����,���еĲ�����Ҫ����BMC_FLAG��ROA_FLAG��־
//						bFlag = bFlag && dbo.execute("Update IE_CPRK Set bmc_flag='DPY' Where bmc_flag is null And oper_flag=0");
//						//��λ
//						bFlag = bFlag && dbo.execute("Update IE_CPRK Set quantity=quantity/1000 Where unit='054' And oper_flag=0");
//						
//						t_sql[0] = "UPDATE DATABASE_CPRK SET QUANTITY=QUANTITY+(SELECT SUM(QUANTITY) FROM IE_CPRK WHERE CPNO=DATABASE_CPRK.CPNO AND ORGANIZATION=DATABASE_CPRK.Organization And VERSION=DATABASE_CPRK.VERSION And BMC_FLAG=DATABASE_CPRK.BMC_FLAG And ROA_FLAG=DATABASE_CPRK.ROA_FLAG AND OPER_FLAG=0) WHERE CPNO||ORGANIZATION||VERSION||BMC_FLAG||ROA_FLAG IN (SELECT CPNO||ORGANIZATION||VERSION||BMC_FLAG||ROA_FLAG FROM IE_CPRK WHERE OPER_FLAG=0)";
//						t_sql[1] = "INSERT INTO DATABASE_CPRK (SELECT CPNO,SUM(QUANTITY) AS QUANTITY,Organization,0,UNIT,VERSION,BMC_FLAG,ROA_FLAG FROM IE_CPRK WHERE OPER_FLAG=0 AND CPNO||Organization||VERSION||BMC_FLAG||ROA_FLAG NOT IN (SELECT CPNO||Organization||VERSION||BMC_FLAG||ROA_FLAG FROM DATABASE_CPRK) GROUP BY CPNO,Organization,UNIT,VERSION,BMC_FLAG,ROA_FLAG)";
//						t_sql[2] = "UPDATE IE_CPRK SET OPER_FLAG=1 WHERE OPER_FLAG=0";
//						bFlag = bFlag && dbo.execute(t_sql);
//					}
//					
//					//ɾ���ļ�
//					if (bFlag) {
//						File f = new File(MAIN_DIR+"/cprk/"+(String)vecFileList.get(i));
//						boolean bdel = f.delete();
//						multicommit();
//					}
//				}
//				catch(Exception e) {
//					e.printStackTrace();
//					multirollback();
//				}
//			}
//			
//			commit();
//			
//			return true;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//			return false;
//		}
//	}
//	
//	/*
//	 * ��˰Ͷ�ϵ�
//	 * Ŀ¼: tl
//	 */
//	private boolean getTL() {
//		try {
//			//search file list
//			Vector vecFileList = getFileList("tl");
//		
//			DataBaseObject dbo = getDataBaseObject();
//			for (int i=0; i < vecFileList.size(); i++) {
//				try {
//					boolean bFlag = true;
//					SAXReader saxReader = new SAXReader();
//					Document document = saxReader.read(MAIN_DIR+"/tl/"+(String)vecFileList.get(i));
//					Element root = document.getRootElement();
//					//��˰Ͷ�ϵ�
//					Iterator iter_root = root.elementIterator();
//					boolean bHasData = false;
//					while (iter_root.hasNext()) {
//						bHasData = true;
//						Element element_entry = (Element)iter_root.next();
//						//��˰Ͷ�ϵ���
//						Iterator iter_entry = element_entry.elementIterator();
//						CommonBean cbTL = new CommonBean();
//						cbTL.addValue("id",com.aiyun.common.util.Oid.getOid());
//						while (iter_entry.hasNext()) {
//							Element element_item = (Element)iter_entry.next();
//							String ename = element_item.getName();
//							String etext = element_item.getText();
//							if (ename.equalsIgnoreCase("organization") && etext.equalsIgnoreCase("ac")) {
//								cbTL.addValue(ename.toLowerCase(),"VP");
//							}
//							else if (ename.equalsIgnoreCase("quantity")) {
//								//cbTL.addValue("quantity",StrTool.str2double(etext)*-1/1000+"");
//
//								cbTL.addValue("quantity",etext);
//							}
//							//��λ
//							else if (ename.equalsIgnoreCase("sldw")) {
//									cbTL.addValue(ename.toLowerCase(),(String)hmUnit.get(etext.toUpperCase()));
//							}
//							else {
//								cbTL.addValue(ename.toLowerCase(),etext);
//							}
//						}
//						cbTL.addValue("adddate","_SYSDATE");
//						
//						if (!cbTL.getValue("organization").equalsIgnoreCase("cs") && !cbTL.getValue("organization").equalsIgnoreCase("rs")) {
//							cbTL.setBeanName("ie_tl");
//							cbTL.setAttribute("insert");
//							
//							bFlag = bFlag && dbo.execute(cbTL);
//						}
//					}
//					//multicommit();
//					//�ϼ�����
//					if (bHasData) {
//						bFlag = bFlag && dbo.execute("Update ie_tl Set bmc_flag='DPY' Where bmc_flag is null And oper_flag=0");
//						String[] t_sql = new String[10];
//
//						//���ӵ�λ��Ϣ��������,���unit=054��Ҫ��1000,���򲻳�1000
//						t_sql[0] = "UPDATE IE_TL SET QUANTITY=QUANTITY*(-1) where oper_flag=0";
//						t_sql[1] = "UPDATE IE_TL SET QUANTITY=QUANTITY/1000 where SLDW='054' And oper_flag=0";
//						
//						//���п�洦������BMC_FLAG��ROA_FLAG�Ĵ���
//						t_sql[2] = "INSERT INTO DATABASE_TL (LJNO,Organization,BSFLAG,QUANTITY,BMC_FLAG,ROA_FLAG) Select LJNO,Organization,'B',0,BMC_FLAG,ROA_FLAG From (SELECT LJNO,ORGANIZATION,BMC_FLAG,ROA_FLAG FROM IE_TL WHERE OPER_FLAG=0 AND BSFLAG='NO' And Not (bmc_flag='ROA' And roa_flag='1') MINUS SELECT LJNO,ORGANIZATION,BMC_FLAG,ROA_FLAG FROM DATABASE_TL WHERE BSFLAG='B')";
//						t_sql[3] = "INSERT INTO DATABASE_TL (LJNO,Organization,BSFLAG,QUANTITY,BMC_FLAG,ROA_FLAG) Select LJNO,Organization,'N',0,BMC_FLAG,ROA_FLAG From (SELECT LJNO,ORGANIZATION,BMC_FLAG,ROA_FLAG FROM IE_TL WHERE OPER_FLAG=0 AND BSFLAG='NO' And Not (bmc_flag='ROA' And roa_flag='1') MINUS SELECT LJNO,ORGANIZATION,BMC_FLAG,ROA_FLAG FROM DATABASE_TL WHERE BSFLAG='N')";
//						t_sql[4] = "UPDATE DATABASE_TL SET QUANTITY=QUANTITY+(SELECT SUM(QUANTITY) FROM IE_TL WHERE LJNO=DATABASE_TL.LJNO And Organization=DATABASE_TL.Organization And BSFLAG=DATABASE_TL.BSFLAG And BMC_FLAG=DATABASE_TL.BMC_FLAG And ROA_FLAG=DATABASE_TL.ROA_FLAG AND OPER_FLAG=0) WHERE Exists (SELECT ie_tl.LJNO FROM IE_TL WHERE OPER_FLAG=0 And database_tl.ljno=ie_tl.ljno And database_tl.Organization=ie_tl.Organization And ie_tl.bsflag=database_tl.bsflag And ie_tl.BMC_FLAG=DATABASE_TL.BMC_FLAG And ie_tl.ROA_FLAG=DATABASE_TL.ROA_FLAG)";
//						t_sql[5] = "INSERT INTO DATABASE_TL (LJNO,Organization,BSFLAG,QUANTITY,BMC_FLAG,ROA_FLAG) (SELECT LJNO,ORGANIZATION,BSFLAG,SUM(QUANTITY),BMC_FLAG,ROA_FLAG FROM IE_TL WHERE OPER_FLAG=0 AND LJNO||Organization||BSFLAG||BMC_FLAG||ROA_FLAG NOT IN (SELECT LJNO||Organization||BSFLAG||BMC_FLAG||ROA_FLAG FROM DATABASE_TL) GROUP BY LJNO,Organization,BSFLAG,BMC_FLAG,ROA_FLAG)";
//						t_sql[6] = "Update DATABASE_SM Set QUANTITY=QUANTITY-(SELECT SUM(QUANTITY) FROM IE_TL WHERE LJNO=DATABASE_SM.LJNO And ORGANIZATION=DATABASE_SM.Organization And BSFLAG=DATABASE_SM.BSFLAG And BMC_FLAG=DATABASE_SM.BMC_FLAG And ROA_FLAG=DATABASE_SM.ROA_FLAG And OPER_FLAG=0) Where exists (Select LJNO From IE_TL Where OPER_FLAG=0 And ie_tl.ljno=database_sm.ljno And ie_tl.Organization=database_sm.Organization And ie_tl.bsflag=database_sm.bsflag And ie_tl.BMC_FLAG=database_sm.BMC_FLAG And ie_tl.ROA_FLAG=database_sm.ROA_FLAG)";
//						t_sql[7] = "INSERT INTO DATABASE_SM (LJNO,Organization,BSFLAG,QUANTITY,unit,BMC_FLAG,ROA_FLAG) (SELECT LJNO,ORGANIZATION,BSFLAG,SUM(-1*QUANTITY),SLDW,BMC_FLAG,ROA_FLAG FROM IE_TL WHERE OPER_FLAG=0 AND LJNO||Organization||BSFLAG||BMC_FLAG||ROA_FLAG NOT IN (SELECT LJNO||Organization||BSFLAG||BMC_FLAG||ROA_FLAG FROM DATABASE_SM) GROUP BY LJNO,Organization,BSFLAG,SLDW,BMC_FLAG,ROA_FLAG)";
//						t_sql[8] = "Update DATABASE_CPRK Set QUANTITY=QUANTITY-(SELECT SUM(QUANTITY) FROM IE_TL WHERE LJNO=DATABASE_CPRK.CPNO And ORGANIZATION=DATABASE_CPRK.Organization And BMC_FLAG=DATABASE_CPRK.BMC_FLAG And ROA_FLAG=DATABASE_CPRK.ROA_FLAG And OPER_FLAG=0) Where CPNO||Organization||BMC_FLAG||ROA_FLAG In (Select LJNO||Organization||BMC_FLAG||ROA_FLAG From IE_TL Where OPER_FLAG=0)";
//						t_sql[9] = "UPDATE IE_TL SET OPER_FLAG=1 WHERE OPER_FLAG=0";
//						bFlag = bFlag && dbo.execute(t_sql);
//					}
//						
//					//ɾ���ļ�
//					if (bFlag) {
//						File f = new File(MAIN_DIR+"/tl/"+(String)vecFileList.get(i));
//						boolean bdel = f.delete();
//						multicommit();
//					}
//				}
//				catch(Exception e) {
//					e.printStackTrace();
//					multirollback();
//				}
//			}
//			
//			commit();
//			
//			return true;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//			return false;
//		}
//	}
//	
//	/*
//	 * ��������
//	 * Ŀ¼: fqxh
//	 */
//	private boolean getFQXH() {
//		try {
//			//search file list
//			Vector vecFileList = getFileList("fqxh");
//		
//			DataBaseObject dbo = getDataBaseObject();
//			
//			for (int i=0; i < vecFileList.size(); i++) {
//				try {
//					boolean bFlag = true;
//					SAXReader saxReader = new SAXReader();
//					Document document = saxReader.read(MAIN_DIR+"/fqxh/"+(String)vecFileList.get(i));
//					Element root = document.getRootElement();
//					//��������
//					Iterator iter_root = root.elementIterator();
//					//��ҪתΪ�ϼ��ĳ�Ʒ�ļ�¼id
//					String stoljid = "";
//					boolean bHasData = false;
//					while (iter_root.hasNext()) {
//						bHasData = true;
//						Element element_entry = (Element)iter_root.next();
//						//��������
//						Iterator iter_entry = element_entry.elementIterator();
//						CommonBean cbFQXH = new CommonBean();
//						cbFQXH.addValue("id",com.aiyun.common.util.Oid.getOid());
//						while (iter_entry.hasNext()) {
//							Element element_item = (Element)iter_entry.next();
//							String ename = element_item.getName();
//							String etext = element_item.getText();
//							if (element_item.getName().equalsIgnoreCase("quantity")) {
//								//cbFQXH.addValue(ename,StrTool.str2double(etext)*-1/1000+"");
//								//cbFQXH.addValue("remain",StrTool.str2double(etext)*-1/1000+"");
//								cbFQXH.addValue(ename,etext);
//								cbFQXH.addValue("remain",etext);
//							}
//							//��λ
//							else if (ename.equalsIgnoreCase("sldw")) {
//									cbFQXH.addValue(ename.toLowerCase(),(String)hmUnit.get(etext.toUpperCase()));
//							}
//							else if (ename.equalsIgnoreCase("organization") && etext.equalsIgnoreCase("ac")) {
//								cbFQXH.addValue(ename.toLowerCase(),"VP");
//							}
//							else {
//								cbFQXH.addValue(ename.toLowerCase(),etext);
//							}
//						}
//						cbFQXH.addValue("oper_flag","-1");
//						cbFQXH.addValue("adddate","_SYSDATE");
//						
//						if (!cbFQXH.getValue("organization").equalsIgnoreCase("cs") && !cbFQXH.getValue("organization").equalsIgnoreCase("rs")) {
//							cbFQXH.setBeanName("ie_fqxh_mx");
//							cbFQXH.setAttribute("insert");
//							bFlag = bFlag && dbo.execute(cbFQXH);
//						}
//					}
//					
//					String sql = "";
//					if (bHasData) {
//						//�����ֶ���,sub_flag��Ϊflag
//						//�����ֶ�
//						//BMC_FLAG	VARCHAR2(10)	Y			�ֿ��ʶ-DPY/ROA
//						//SUB_FLAG	VARCHAR2(10)	Y			����ʶ-B/N/NO
//						//B_FLAG	VARCHAR2(10)	Y			�Ƿ���Ҫ��˰Y/(��)
//						//ROA_FLAG	VARCHAR2(10)	Y			0����ROA��Ʒ/���Ʒ��1��ROA
//						//VERSION	VARCHAR2(10)	Y			�汾��
//
//						//���ӵ�λ��Ϣ��������,���unit=054��Ҫ��1000,���򲻳�1000
//						sql = "UPDATE IE_fqxh_mx SET QUANTITY=QUANTITY*(-1),REMAIN=REMAIN*(-1) where oper_flag=-1";
//						bFlag = bFlag && dbo.execute(sql);	
//						sql = "UPDATE IE_fqxh_mx SET QUANTITY=QUANTITY/1000,REMAIN=REMAIN/1000 where SLDW='054' And oper_flag=-1";
//						bFlag = bFlag && dbo.execute(sql);	
//						
//						//���°汾��Version
//						sql="Update IE_fqxh_mx set version=0 where version Is null And oper_flag=-1";
//						bFlag = bFlag && dbo.execute(sql);	
//						//����,ie_fqxh����ʷ��,����л��ܱ�,���ٲ���.				
//						//����BMC_FLAG,ROA_FLAG,B_FLAG:�Ƿ���Ҫ��˰Y/(��)�Ĵ���,
//						sql="Update IE_fqxh_mx set b_flag='Y' where b_flag Is null And oper_flag=-1";
//						bFlag = bFlag && dbo.execute(sql);	
//						
//						sql = "Update ie_fqxh aded Set quantity=quantity+ \n"
//							+ "(Select Sum(quantity) \n"
//							+ "From ie_fqxh_mx ad \n"
//							+ "Where oper_flag=-1 And ad.item_no=aded.item_no And ad.wlflag=aded.wlflag And ad.Organization=aded.Organization And ad.bmc_flag=aded.bmc_flag And ad.roa_flag=aded.roa_flag And ad.b_flag=aded.b_flag And ad.version=aded.version  And ad.wltype=aded.wltype  \n" 
//							+ "), \n"
//							+ "remain=remain+ \n"
//							+ "(Select Sum(quantity) \n"
//							+ "From ie_fqxh_mx ad \n"
//							+ "Where oper_flag=-1 And ad.item_no=aded.item_no And ad.wlflag=aded.wlflag And ad.Organization=aded.Organization And ad.bmc_flag=aded.bmc_flag And ad.roa_flag=aded.roa_flag And ad.b_flag=aded.b_flag And ad.version=aded.version  And ad.wltype=aded.wltype \n"
//							+ ") \n"
//							+ "Where  aded.item_no||aded.Organization||aded.wlflag||aded.bmc_flag||aded.roa_flag||aded.b_flag||version||wltype	 In (Select item_no||Organization||wlflag||bmc_flag||roa_flag||b_flag||version||wltype From ie_fqxh_MX Where oper_flag=-1)";
//						dbo.execute(sql);
//						sql = "Insert Into ie_fqxh (ID,item_no,quantity,remain,wlflag,Organization,wltype,bmc_flag,roa_flag,b_flag,version) \n" 
//							+ "Select SYS_GUID(),item_no,quantity,quantity,wlflag,Organization,wltype,bmc_flag,roa_flag,b_flag,version  \n"
//							+ "From  \n"
//							+ "(Select item_no,sum(quantity) As quantity,wlflag,Organization,wltype,bmc_flag,roa_flag,b_flag,version \n" 
//							+ "From ie_fqxh_mx fqxh  \n"
//							+ "Where fqxh.oper_flag=-1 And item_no||Organization||wlflag||bmc_flag||roa_flag||b_flag||version||wltype Not In (Select f.item_no||f.Organization||f.wlflag||f.bmc_flag||f.roa_flag||f.b_flag||f.version||f.wltype From ie_fqxh f ) \n" 
//							+ "Group By item_no,wlflag,Organization,bmc_flag,roa_flag,b_flag,version,wltype ) skrh";
//						dbo.execute(sql);
////						//Ͷ�Ͽ�ı���
////						sql = "Update database_tl tl Set quantity = quantity-( \n"
////							+ "Select Sum(quantity)  \n"  
////							+ "From ie_fqxh \n"  
////							+ "Where flag=1 And oper_flag=-1 \n"  
////							+ "And Organization=tl.Organization And ie_fqxh.item_no=tl.ljno \n"  
////							+ "And ie_fqxh.wlflag=tl.bsflag And ie_fqxh.bmc_flag=tl.bmc_flag And ie_fqxh.roa_flag=tl.roa_flag) \n"  
////							+ "Where Exists ( \n"  
////							+ "Select Id  \n"  
////							+ "From ie_fqxh fq \n"  
////							+ "Where fq.item_no=tl.ljno And fq.Organization=tl.Organization And fq.wlflag=tl.bsflag And fq.bmc_flag=tl.bmc_flag And fq.roa_flag=tl.roa_flag \n"  
////							+ "And fq.oper_flag=-1 And fq.flag=1 \n"  
////							+ ")";  
////						dbo.execute(sql);
//
////						//ԭ���Ͽ�ı���,�ۼ�ԭ���Ͽ�
//						sql = "Update database_sm sm Set quantity = quantity-( \n"
//							+ "Select Sum(quantity)  \n"  
//							+ "From ie_fqxh \n"  
//							+ "Where flag=0 And oper_flag=-1 \n"  
//							+ "And Organization=sm.Organization And ie_fqxh.item_no=sm.ljno \n"  
//							+ "And ie_fqxh.wlflag=sm.bsflag And ie_fqxh.bmc_flag=sm.bmc_flag And ie_fqxh.roa_flag=sm.roa_flag) \n"  
//							+ "Where Exists ( \n"  
//							+ "Select Id  \n"  
//							+ "From ie_fqxh fq \n"  
//							+ "Where fq.item_no=sm.ljno And fq.Organization=sm.Organization And fq.wlflag=sm.bsflag And fq.bmc_flag=sm.bmc_flag And fq.roa_flag=sm.roa_flag \n"  
//							+ "And fq.oper_flag=-1 And fq.flag=0 \n"  
//							+ ")";  
//						dbo.execute(sql);
//						
//						//ԭ���Ͽ�ı���,�ӵ�Ͷ�Ͽ⣬�Ա���ҵ��ʱ�ۼ�
//						sql = "Update database_tl tl Set quantity = quantity+( \n"
//							+ "Select Sum(quantity) \n"
//							+ "From ie_fqxh   \n"
//							+ "Where flag=0 And oper_flag=-1 \n"   
//							+ "And Organization=tl.Organization And ie_fqxh.item_no=tl.ljno \n"   
//							+ "And ie_fqxh.wlflag=tl.bsflag And ie_fqxh.bmc_flag=tl.bmc_flag And ie_fqxh.roa_flag=tl.roa_flag) \n"   
//							+ "Where Exists (    \n"
//							+ "Select Id     \n"
//							+ "From ie_fqxh fq   \n" 
//							+ "Where fq.item_no=tl.ljno And fq.Organization=tl.Organization And fq.wlflag=tl.bsflag And fq.bmc_flag=tl.bmc_flag And fq.roa_flag=tl.roa_flag \n"   
//							+ "And fq.oper_flag=-1 And fq.flag=0    \n"
//							+ ")";
//						dbo.execute(sql);
//						
//						dbo.execute("UPDATE IE_FQXH_MX SET OPER_FLAG=0 WHERE OPER_FLAG=-1");
//					}
//					
//					//ɾ���ļ�
//					if (bFlag) {
//						File f = new File(MAIN_DIR+"/fqxh/"+(String)vecFileList.get(i));
//						boolean bdel = f.delete();
//						multicommit();
//					}
//				}
//				catch(Exception e) {
//					e.printStackTrace();
//					multirollback();
//				}
//			}
//			commit();
//			
//			return true;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//			return false;
//		}
//	}
//	
//	/*
//	 * �˲��ÿ����Ϣ
//	 * Ŀ¼: hc
//	 */
//	private boolean getKC() {
//		try {
//			//search file list
//			Vector vecFileList = getFileList("hc");
//			boolean delkc = true;
//			DataBaseObject dbo = getDataBaseObject();
//			for (int i=0; i < vecFileList.size(); i++) {
//				try {
//					boolean bFlag = true;
//					if (delkc) {
//						dbo.execute("Delete From ie_bskc");
//						delkc = false;
//					} 
//					SAXReader saxReader = new SAXReader();
//					Document document = saxReader.read(MAIN_DIR+"/hc/"+(String)vecFileList.get(i));
//					Element root = document.getRootElement();
//					//�˲��ÿ����Ϣ
//					Iterator iter_root = root.elementIterator();
//					while (iter_root.hasNext()) {
//						Element element_entry = (Element)iter_root.next();
//						//�˲��ÿ����Ϣ
//						Iterator iter_entry = element_entry.elementIterator();
//						CommonBean cbKC = new CommonBean();
//						cbKC.addValue("id",com.aiyun.common.util.Oid.getOid());
//						while (iter_entry.hasNext()) {
//							Element element_item = (Element)iter_entry.next();
//							String ename = element_item.getName();
//							String etext = element_item.getText();
//							if (ename.equalsIgnoreCase("organization") && etext.equalsIgnoreCase("ac")) {
//								cbKC.addValue(ename.toLowerCase(),"VP");
//							}
//							else if (ename.equalsIgnoreCase("quantity")) {
//								//cbKC.addValue("quantity",StrTool.str2double(etext)/1000+"");
//								cbKC.addValue("quantity",etext);
//							}
//							//��λ
//							else if (ename.equalsIgnoreCase("sldw")) {
//								cbKC.addValue(ename.toLowerCase(),(String)hmUnit.get(etext.toUpperCase()));
//							}
//							else {
//								cbKC.addValue(ename.toLowerCase(),etext);
//							}
//						}
//						cbKC.addValue("adddate","_SYSDATE");
//						
//						if ( cbKC.getValue("organization").equalsIgnoreCase("vp")) {
//							cbKC.setBeanName("ie_bskc");
//							cbKC.setAttribute("insert");
//							bFlag = bFlag && dbo.execute(cbKC);
//						}
//					}
//					//����λ
//					String sql=null;
//					//���ӵ�λ��Ϣ��������,���unit=054��Ҫ��1000,���򲻳�1000
//					sql = "UPDATE IE_BSKC SET QUANTITY=QUANTITY/1000 where SLDW='054'";
//					bFlag = bFlag && dbo.execute(sql);
//					bFlag = bFlag && dbo.execute("Update ie_bskc set flag=0 where flag Is null and upper(type)='BUY'");
//					//ɾ���ļ�
//					if (bFlag) {
//						File f = new File(MAIN_DIR+"/hc/"+(String)vecFileList.get(i));
//						boolean bdel = f.delete();
//						multicommit();
//					}
//				}
//				
//				catch(Exception e) {
//					e.printStackTrace();
//					multirollback();
//				}
//			}
//			commit();
//			
//			return true;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//			return false;
//		}
//	}
//	
//	/*
//	 * ����������
//	 * Ŀ¼: adjdata
//	 */
//	private boolean getAdjData() {
//		try {
//			//search file list
//			Vector vecFileList = getFileList("adjdata");
//		
//			DataBaseObject dbo = getDataBaseObject();
//			for (int i=0; i < vecFileList.size(); i++) {
//				try {
//					boolean bFlag = true;
//					SAXReader saxReader = new SAXReader();
//					Document document = saxReader.read(MAIN_DIR+"/adjdata/"+(String)vecFileList.get(i));
//					Element root = document.getRootElement();
//					//����������
//					Iterator iter_root = root.elementIterator();
//					while (iter_root.hasNext()) {
//						Element element_entry = (Element)iter_root.next();
//						//����������
//						Iterator iter_entry = element_entry.elementIterator();
//						CommonBean cbAdj = new CommonBean();
//						cbAdj.addValue("id",com.aiyun.common.util.Oid.getOid());
//						while (iter_entry.hasNext()) {
//							Element element_item = (Element)iter_entry.next();
//							String ename = element_item.getName();
//							String etext = element_item.getText();
//							if (ename.equalsIgnoreCase("organization") && etext.equalsIgnoreCase("ac")) {
//								cbAdj.addValue(ename.toLowerCase(),"VP");
//							}
//							//��λ
//							else if (ename.equalsIgnoreCase("unit")) {
//								cbAdj.addValue(ename.toLowerCase(),(String)hmUnit.get(etext.toUpperCase()));
//							}
//							else if (ename.equalsIgnoreCase("quantity")) {
//								//cbAdj.addValue(ename.toLowerCase(),StrTool.str2double(etext)/1000+"");
//								cbAdj.addValue(ename.toLowerCase(),etext);
//							}
//							else {
//								cbAdj.addValue(ename.toLowerCase(),etext);
//							}
//						}
//						cbAdj.addValue("adddate","_SYSDATE");
//						if (!cbAdj.getValue("organization").equalsIgnoreCase("cs") && !cbAdj.getValue("organization").equalsIgnoreCase("rs")) {
//							cbAdj.setBeanName("ie_adj");
//							cbAdj.setAttribute("insert");
//							bFlag = bFlag && dbo.execute(cbAdj);
//						}
//					}
//					
//					String sql = "";
//					//�����ֶ���:flag��Ϊf_flag,SUB_FLAG��Ϊflag (number)
//					//�����ֶ�
//					//BMC_FLAG	VARCHAR2(10)	Y			�ֿ��ʶ-DPY/ROA
//					//SUB_FLAG	VARCHAR2(10)	Y			����ʶ-B/N/NO
//					//ROA_FLAG	VARCHAR2(10)	Y			0����ROA��Ʒ/���Ʒ��1��ROA
//					
//					bFlag = bFlag && dbo.execute("Update ie_adj Set bmc_flag='DPY' Where bmc_flag is null And oper_flag=0");
//					
//					//���ӵ�λ��Ϣ��������,���unit=054��Ҫ��1000,���򲻳�1000
//					sql = "UPDATE IE_ADJ SET QUANTITY=QUANTITY/1000 where UNIT='054' And oper_flag=0";
//					bFlag = bFlag && dbo.execute(sql);
//					//���Ӵ���BMC_FLAG��ROA_FLAG��־
//					//����ԭ���Ͽ�����
//					sql = "Update database_tl tl Set quantity = quantity+( \n"
//						+ "Select Sum(quantity)  \n"
//						+ "From ie_adj \n"
//						+ "Where ie_adj.flag=1 And oper_flag=0 \n"
//						+ "And Organization=tl.Organization And ljno=tl.ljno \n"
//						+ "And wlflag=tl.bsflag And BMC_FLAG=tl.BMC_FLAG And ROA_FLAG=tl.ROA_FLAG) \n"
//						+ "Where Exists ( \n"
//						+ "Select Id  \n"
//						+ "From ie_adj  \n"
//						+ "Where ie_adj.ljno=tl.ljno And ie_adj.Organization=tl.Organization And ie_adj.wlflag=tl.bsflag And ie_adj.BMC_FLAG=tl.BMC_FLAG And ie_adj.ROA_FLAG=tl.ROA_FLAG \n"
//						+ "And oper_flag=0 And ie_adj.flag=1 \n"
//						+ ") \n";
//					dbo.execute(sql);
//					
//					sql = "Insert Into database_tl (ljno,Organization,bsflag,quantity,BMC_FLAG,ROA_FLAG) \n"
//						+ "Select ljno,Organization,wlflag,Sum(quantity),BMC_FLAG,ROA_FLAG \n"
//						+ "From ie_adj \n"
//						+ "Where ie_adj.flag=1 And oper_flag=0 And wlflag Is Not Null \n"
//						+ "And Not Exists (Select ljno From database_tl tl Where tl.ljno=ie_adj.ljno And tl.Organization=ie_adj.Organization And tl.bsflag=ie_adj.wlflag And ie_adj.BMC_FLAG=tl.BMC_FLAG And ie_adj.ROA_FLAG=tl.ROA_FLAG) \n"
//						+ "Group By ljno,Organization,wlflag,BMC_FLAG,ROA_FLAG \n";
//					dbo.execute(sql);
//					//����Ͷ�Ͽ�����
//					sql = "Update database_sm sm Set quantity = quantity+( \n"
//						+ "Select Sum(quantity)  \n"
//						+ "From ie_adj \n"
//						+ "Where ie_adj.flag=0 And oper_flag=0 \n"
//						+ "And Organization=sm.Organization And ljno=sm.ljno \n"
//						+ "And wlflag=sm.bsflag And BMC_FLAG=sm.BMC_FLAG And ROA_FLAG=sm.ROA_FLAG) \n"
//						+ "Where Exists ( \n"
//						+ "Select Id  \n"
//						+ "From ie_adj  \n"
//						+ "Where ljno=sm.ljno And Organization=sm.Organization And wlflag=sm.bsflag And BMC_FLAG=sm.BMC_FLAG And ROA_FLAG=sm.ROA_FLAG \n"
//						+ "And oper_flag=0 And ie_adj.flag=0 \n"
//						+ ") \n";
//					dbo.execute(sql);
//					
//					sql = "Insert Into database_sm (ljno,Organization,bsflag,quantity,unit,BMC_FLAG,ROA_FLAG) \n"
//						+ "Select ljno,Organization,wlflag,Sum(quantity),unit,BMC_FLAG,ROA_FLAG \n"
//						+ "From ie_adj \n"
//						+ "Where ie_adj.flag=0 And oper_flag=0 And wlflag Is Not Null \n"
//						+ "And Not Exists (Select ljno From database_sm sm Where sm.ljno=ie_adj.ljno And sm.Organization=ie_adj.Organization And sm.bsflag=ie_adj.wlflag And ie_adj.BMC_FLAG=sm.BMC_FLAG And ie_adj.ROA_FLAG=sm.ROA_FLAG) \n"
//						+ "Group By ljno,Organization,wlflag,unit,BMC_FLAG,ROA_FLAG \n";
//					dbo.execute(sql);
//					//������Ʒ/���Ʒ������
//					sql = "Update database_cprk cprk Set quantity = quantity+( \n"
//						+ "Select Sum(quantity)  \n"
//						+ "From ie_adj \n"
//						+ "Where ie_adj.flag=2 And oper_flag=0 \n"
//						+ "And Organization=cprk.Organization And ljno=cprk.cpno \n"
//						+ "And version=cprk.version And BMC_FLAG=cprk.BMC_FLAG And ROA_FLAG=cprk.ROA_FLAG) \n"
//						+ "Where Exists ( \n"
//						+ "Select Id  \n"
//						+ "From ie_adj  \n"
//						+ "Where ljno=cprk.cpno And Organization=cprk.Organization And version=cprk.version And BMC_FLAG=cprk.BMC_FLAG And ROA_FLAG=cprk.ROA_FLAG \n"
//						+ "And oper_flag=0 And ie_adj.flag=2 \n"
//						+ ") \n";
//					dbo.execute(sql);
//					
//					sql = "Insert Into database_cprk (CPNO,VERSION,ORGANIZATION,QUANTITY,UNIT,BMC_FLAG,ROA_FLAG) \n"
//						+ "Select ljno,version,Organization,Sum(quantity),unit,BMC_FLAG,ROA_FLAG \n"
//						+ "From ie_adj \n"
//						+ "Where ie_adj.flag=2 And oper_flag=0 \n"
//						+ "And Not Exists (Select cpno From database_cprk cprk Where cprk.cpno=ie_adj.ljno And cprk.Organization=ie_adj.Organization And cprk.version=ie_adj.version And ie_adj.BMC_FLAG=cprk.BMC_FLAG And ie_adj.ROA_FLAG=cprk.ROA_FLAG) \n"
//						+ "Group By ljno,Organization,version,unit,BMC_FLAG,ROA_FLAG \n";
//					dbo.execute(sql);
//					
//					sql = "Update ie_adj Set oper_flag=1 Where oper_flag=0 \n";
//					dbo.execute(sql);
//					
//					//ɾ���ļ�
//					if (bFlag) {
//						File f = new File(MAIN_DIR+"/adjdata/"+(String)vecFileList.get(i));
//						boolean bdel = f.delete();
//						multicommit();
//					}
//				}
//				catch(Exception e) {
//					e.printStackTrace();
//					multirollback();
//				}
//			}
//			commit();
//			
//			return true;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//			return false;
//		}
//	}
//	
//	/*
//	 * ��֯��ת��
//	 * Ŀ¼: orgtrans
//	 */
//	private boolean getOrgTrans() {
//		try {
//			//search file list
//			Vector vecFileList = getFileList("orgtrans");
//		
//			DataBaseObject dbo = getDataBaseObject();
//			for (int i=0; i < vecFileList.size(); i++) {
//				try {
//					boolean bFlag = true;
//					SAXReader saxReader = new SAXReader();
//					Document document = saxReader.read(MAIN_DIR+"/orgtrans/"+(String)vecFileList.get(i));
//					Element root = document.getRootElement();
//					//��֯��ת��
//					Iterator iter_root = root.elementIterator();
//					boolean bHasData = false;
//					while (iter_root.hasNext()) {
//						bHasData = true;
//						Element element_entry = (Element)iter_root.next();
//						//��֯��ת��
//						Iterator iter_entry = element_entry.elementIterator();
//						CommonBean cbOrgTrans = new CommonBean();
//						cbOrgTrans.addValue("id",com.aiyun.common.util.Oid.getOid());
//						while (iter_entry.hasNext()) {
//							Element element_item = (Element)iter_entry.next();
//							String ename = element_item.getName();
//							String etext = element_item.getText();
//							if (ename.equalsIgnoreCase("from_org") && etext.equalsIgnoreCase("ac")) {
//								cbOrgTrans.addValue(ename.toLowerCase(),"VP");
//							}
//							else if (ename.equalsIgnoreCase("to_org") && etext.equalsIgnoreCase("ac")) {
//								cbOrgTrans.addValue(ename.toLowerCase(),"VP");
//							}
//							else if (ename.equalsIgnoreCase("quantity")) {
//								//cbOrgTrans.addValue(ename.toLowerCase(),StrTool.str2double(etext)/1000+"");
//								cbOrgTrans.addValue(ename.toLowerCase(),etext);
//							}
//							//��λ
//							else if (ename.equalsIgnoreCase("unit")) {
//								cbOrgTrans.addValue(ename.toLowerCase(),(String)hmUnit.get(etext.toUpperCase()));
//							}
//							else {
//								cbOrgTrans.addValue(ename.toLowerCase(),etext);
//							}
//						}
//						cbOrgTrans.addValue("adddate","_SYSDATE");
//						
//						cbOrgTrans.setBeanName("ie_orgtrans");
//						cbOrgTrans.setAttribute("insert");
//						bFlag = bFlag && dbo.execute(cbOrgTrans);
//					}
//					
//					//��������
//					if (bHasData) {
//						bFlag = bFlag && dbo.execute("Update IE_ORGTRANS Set from_bmc_flag='DPY' Where from_bmc_flag is null And oper_flag=0");
//						bFlag = bFlag && dbo.execute("Update IE_ORGTRANS Set to_bmc_flag='DPY' Where to_bmc_flag is null And oper_flag=0");
//						String[] t_sql = new String[13];
//						t_sql[0]="UPDATE IE_ORGTRANS SET QUANTITY=QUANTITY/1000 where UNIT='054' And oper_flag=0";
//						//��Ͷ�Ͽ�ת����
//						//����,����from _bmc_flag,to_bmc_flag��ROA_FLAG�Ĵ���
//						//������֯����Ӧ���¿��,ԭ��תCS��û�и��¿��
//						t_sql[1] = "UPDATE DATABASE_TL SET QUANTITY=QUANTITY+ \n"
//								+ "( \n"
//								+ "SELECT SUM(QUANTITY)  \n"
//								+ "FROM IE_ORGTRANS  \n"
//								+ "WHERE IE_ORGTRANS.LJNO=DATABASE_TL.LJNO And FROM_ORG=DATABASE_TL.Organization And from_bmc_flag=DATABASE_TL.bmc_flag And roa_flag=DATABASE_TL.roa_flag  \n"
//								+ "And IE_ORGTRANS.WLFLAG=DATABASE_TL.BSFLAG AND IE_ORGTRANS.FROM_ORG ='VP'  \n"
//								+ "AND OPER_FLAG=0 And IE_ORGTRANS.from_flag=1 \n"
//								+ ")  \n"
//								+ "WHERE DATABASE_TL.LJNO||DATABASE_TL.Organization||DATABASE_TL.BSFLAG||DATABASE_TL.BMC_FLAG||DATABASE_TL.ROA_FLAG IN  \n"
//								+ "( \n"
//								+ "SELECT IE_ORGTRANS.LJNO||IE_ORGTRANS.FROM_ORG||IE_ORGTRANS.WLFLAG||IE_ORGTRANS.FROM_BMC_FLAG||IE_ORGTRANS.ROA_FLAG  \n"
//								+ "FROM IE_ORGTRANS  \n"
//								+ "WHERE OPER_FLAG=0 AND IE_ORGTRANS.FROM_ORG ='VP' And to_org<>'CS' And IE_ORGTRANS.from_flag=1 \n"
//								+ ")";
//						//��Ͷ�Ͽ�ת����,û��Ͷ�Ͽ��е�����,��������
//						t_sql[2] = "INSERT INTO DATABASE_TL (LJNO,Organization,BSFLAG,QUANTITY,BMC_FLAG,ROA_FLAG) \n"
//								+ "( \n"
//								+ "SELECT LJNO,FROM_ORG,WLFLAG,SUM(QUANTITY),FROM_BMC_FLAG,ROA_FLAG  \n"
//								+ "FROM IE_ORGTRANS  \n"
//								+ "WHERE  OPER_FLAG=0 AND FROM_ORG ='VP'  And from_flag=1 \n"								
//								+ "AND  LJNO||FROM_ORG||WLFLAG||FROM_BMC_FLAG||ROA_FLAG NOT IN  \n"
//								+ "( \n"
//								+ "SELECT LJNO||Organization||BSFLAG||BMC_FLAG||ROA_FLAG   \n"
//								+ "FROM DATABASE_TL  \n"																
//								+ ") \n"
//								+ "GROUP BY LJNO,FROM_ORG,WLFLAG,FROM_BMC_FLAG,ROA_FLAG)";
//						//ת��Ͷ�Ͽ��е�
//						t_sql[3] = "UPDATE DATABASE_TL SET QUANTITY=QUANTITY- \n"
//								+ "( \n"
//								+ "SELECT SUM(QUANTITY)  \n"
//								+ "FROM IE_ORGTRANS  \n"
//								+ "WHERE IE_ORGTRANS.LJNO=DATABASE_TL.LJNO And IE_ORGTRANS.to_ORG=DATABASE_TL.Organization And IE_ORGTRANS.to_bmc_flag=DATABASE_TL.bmc_flag And IE_ORGTRANS.roa_flag=DATABASE_TL.roa_flag  \n"
//								+ "And IE_ORGTRANS.WLFLAG=DATABASE_TL.BSFLAG AND IE_ORGTRANS.to_ORG ='VP'  \n"
//								+ "AND OPER_FLAG=0 And IE_ORGTRANS.to_flag=1 \n"
//								+ ")  \n"
//								+ "WHERE DATABASE_TL.LJNO||DATABASE_TL.Organization||DATABASE_TL.BSFLAG||DATABASE_TL.BMC_FLAG||DATABASE_TL.ROA_FLAG IN  \n"
//								+ "( \n"
//								+ "SELECT IE_ORGTRANS.LJNO||IE_ORGTRANS.to_ORG||IE_ORGTRANS.WLFLAG||IE_ORGTRANS.TO_BMC_FLAG||IE_ORGTRANS.ROA_FLAG  \n"
//								+ "FROM IE_ORGTRANS  \n"
//								+ "WHERE OPER_FLAG=0 And IE_ORGTRANS.to_flag=1 AND IE_ORGTRANS.to_ORG ='VP' \n"
//								+ ")";
//						//ת��Ͷ�Ͽ��е�,û��Ͷ�Ͽ��е�����,��������
//						t_sql[4] = "INSERT INTO DATABASE_TL (LJNO,Organization,BSFLAG,QUANTITY,BMC_FLAG,ROA_FLAG) \n"
//								+ "( \n"
//								+ "SELECT LJNO,TO_ORG,WLFLAG,-SUM(QUANTITY),TO_BMC_FLAG,ROA_FLAG  \n"
//								+ "FROM IE_ORGTRANS  \n"
//								+ "WHERE  OPER_FLAG=0 AND TO_ORG ='VP'  And TO_flag=1 \n"								
//								+ "AND  LJNO||TO_ORG||WLFLAG||TO_BMC_FLAG||ROA_FLAG NOT IN  \n"
//								+ "( \n"
//								+ "SELECT LJNO||Organization||BSFLAG||BMC_FLAG||ROA_FLAG   \n"
//								+ "FROM DATABASE_TL  \n"																
//								+ ") \n"
//								+ "GROUP BY LJNO,TO_ORG,WLFLAG,TO_BMC_FLAG,ROA_FLAG)";
//						//��ԭ���Ͽ�ת����
//						t_sql[5] = "UPDATE DATABASE_sm SET QUANTITY=QUANTITY+ \n"
//								+ "( \n"
//								+ "SELECT SUM(QUANTITY) \n"
//								+ "FROM IE_ORGTRANS \n"
//								+ "WHERE IE_ORGTRANS.LJNO=database_sm.LJNO And FROM_ORG=database_sm.Organization And FROM_BMC_FLAG=database_sm.BMC_FLAG And ROA_FLAG=database_sm.ROA_FLAG \n"
//								+ "And IE_ORGTRANS.WLFLAG=database_sm.BSFLAG AND IE_ORGTRANS.FROM_ORG ='VP' \n"
//								+ "AND OPER_FLAG=0 And IE_ORGTRANS.from_flag=0 \n"
//								+ ") \n"
//								+ "WHERE LJNO||Organization||BSFLAG||BMC_FLAG||ROA_FLAG IN \n"
//								+ "( \n"
//								+ "SELECT IE_ORGTRANS.LJNO||IE_ORGTRANS.FROM_ORG||IE_ORGTRANS.WLFLAG||IE_ORGTRANS.FROM_BMC_FLAG||IE_ORGTRANS.ROA_FLAG \n"
//								+ "FROM IE_ORGTRANS \n"
//								+ "WHERE OPER_FLAG=0  AND IE_ORGTRANS.FROM_ORG ='VP' And IE_ORGTRANS.from_flag=0 \n"
//								+ ")";
//								
//						//��ԭ���Ͽ�ת����,û��ԭ���Ͽ��е�����,��������							
//						t_sql[6] = "INSERT INTO DATABASE_SM (LJNO,Organization,BSFLAG,QUANTITY,UNIT,BMC_FLAG,ROA_FLAG) \n"
//								+ "( \n"
//								+ "SELECT LJNO,FROM_ORG,WLFLAG,SUM(QUANTITY),UNIT,FROM_BMC_FLAG,ROA_FLAG  \n"
//								+ "FROM IE_ORGTRANS  \n"
//								+ "WHERE  OPER_FLAG=0 AND FROM_ORG ='VP'  And from_flag=0 \n"								
//								+ "AND  LJNO||FROM_ORG||WLFLAG||FROM_BMC_FLAG||ROA_FLAG NOT IN  \n"
//								+ "( \n"
//								+ "SELECT LJNO||Organization||BSFLAG||BMC_FLAG||ROA_FLAG   \n"
//								+ "FROM DATABASE_SM  \n"																
//								+ ") \n"
//								+ "GROUP BY LJNO,FROM_ORG,WLFLAG,UNIT,FROM_BMC_FLAG,ROA_FLAG)";
//									
//						//ת��ԭ���Ͽ��е�
//						t_sql[7] = "UPDATE DATABASE_sm SET QUANTITY=QUANTITY- \n"
//								+ "( \n"
//								+ "SELECT SUM(QUANTITY) \n"
//								+ "FROM IE_ORGTRANS \n"
//								+ "WHERE IE_ORGTRANS.LJNO=database_sm.LJNO And IE_ORGTRANS.to_ORG=database_sm.Organization And IE_ORGTRANS.to_bmc_flag=database_sm.bmc_flag And IE_ORGTRANS.roa_flag=database_sm.roa_flag  \n"
//								+ "And IE_ORGTRANS.WLFLAG=database_sm.BSFLAG AND IE_ORGTRANS.to_ORG ='VP' \n"
//								+ "AND OPER_FLAG=0 And IE_ORGTRANS.to_flag=0 \n"
//								+ ") \n"
//								+ "WHERE LJNO||Organization||BSFLAG||BMC_FLAG||ROA_FLAG IN \n"
//								+ "( \n"
//								+ "SELECT IE_ORGTRANS.LJNO||IE_ORGTRANS.to_ORG||IE_ORGTRANS.WLFLAG||IE_ORGTRANS.TO_BMC_FLAG||IE_ORGTRANS.ROA_FLAG \n"
//								+ "FROM IE_ORGTRANS \n"
//								+ "WHERE OPER_FLAG=0 And IE_ORGTRANS.to_flag=0 AND IE_ORGTRANS.to_ORG ='VP' \n"
//								+ ")";
//						
//						
//						//ת��ԭ���Ͽ��е�,û��ԭ���Ͽ��е�����,��������							
//						t_sql[8] = "INSERT INTO DATABASE_SM (LJNO,Organization,BSFLAG,QUANTITY,UNIT,BMC_FLAG,ROA_FLAG) \n"
//								+ "( \n"
//								+ "SELECT LJNO,TO_ORG,WLFLAG,-SUM(QUANTITY),UNIT,TO_BMC_FLAG,ROA_FLAG  \n"
//								+ "FROM IE_ORGTRANS  \n"
//								+ "WHERE  OPER_FLAG=0 AND TO_ORG ='VP'  And to_flag=0 \n"								
//								+ "AND  LJNO||TO_ORG||WLFLAG||TO_BMC_FLAG||ROA_FLAG NOT IN  \n"
//								+ "( \n"
//								+ "SELECT LJNO||Organization||BSFLAG||BMC_FLAG||ROA_FLAG   \n"
//								+ "FROM DATABASE_SM  \n"																
//								+ ") \n"
//								+ "GROUP BY LJNO,TO_ORG,WLFLAG,UNIT,TO_BMC_FLAG,ROA_FLAG)";
//						
//						//�ӳ�Ʒ��ת����
//						t_sql[9] = "UPDATE DATABASE_cprk SET QUANTITY=QUANTITY+ \n"
//								+ "( \n"
//								+ "SELECT SUM(QUANTITY) \n"
//								+ "FROM IE_ORGTRANS \n"
//								+ "WHERE IE_ORGTRANS.LJNO=DATABASE_cprk.CPNO And FROM_ORG=DATABASE_cprk.Organization And FROM_BMC_FLAG=DATABASE_cprk.BMC_FLAG And ROA_FLAG=DATABASE_cprk.ROA_FLAG \n"
//								+ "And IE_ORGTRANS.VERSION=DATABASE_cprk.VERSION AND IE_ORGTRANS.FROM_ORG ='VP' And to_org<>'CS' \n"
//								+ "AND OPER_FLAG=0 And IE_ORGTRANS.from_flag=2 \n"
//								+ ") \n"
//								+ "WHERE CPNO||Organization||VERSION||BMC_FLAG||ROA_FLAG IN \n"
//								+ "( \n"
//								+ "SELECT IE_ORGTRANS.LJNO||IE_ORGTRANS.FROM_ORG||IE_ORGTRANS.VERSION||IE_ORGTRANS.FROM_BMC_FLAG||IE_ORGTRANS.ROA_FLAG \n"
//								+ "FROM IE_ORGTRANS \n"
//								+ "WHERE OPER_FLAG=0 And to_org<>'CS' AND IE_ORGTRANS.to_ORG ='VP' And IE_ORGTRANS.from_flag=2 \n"
//								+ ")";
//
//						//�ӳ�Ʒ��ת����,û�ڳ�Ʒ���е�����,��������							
//						t_sql[10] = "INSERT INTO DATABASE_CPRK(CPNO,VERSION,ORGANIZATION,QUANTITY,UNIT,BMC_FLAG,ROA_FLAG)  \n"
//								+ "( \n"
//								+ "SELECT LJNO,VERSION,FROM_ORG,SUM(QUANTITY),UNIT,FROM_BMC_FLAG,ROA_FLAG  \n"
//								+ "FROM IE_ORGTRANS  \n"
//								+ "WHERE  OPER_FLAG=0 AND FROM_ORG ='VP'  And from_flag=2 \n"								
//								+ "AND  LJNO||VERSION||FROM_ORG||FROM_BMC_FLAG||ROA_FLAG NOT IN  \n"
//								+ "( \n"
//								+ "SELECT CPNO||VERSION||Organization||BMC_FLAG||ROA_FLAG   \n"
//								+ "FROM DATABASE_CPRK  \n"																
//								+ ") \n"
//								+ "GROUP BY LJNO,VERSION,FROM_ORG,UNIT,FROM_BMC_FLAG,ROA_FLAG)";
//						//ת����Ʒ���
//						t_sql[11] = "UPDATE DATABASE_cprk SET QUANTITY=QUANTITY- \n"
//								+ "( \n"
//								+ "SELECT SUM(QUANTITY) \n"
//								+ "FROM IE_ORGTRANS \n"
//								+ "WHERE IE_ORGTRANS.LJNO=DATABASE_cprk.CPNO And IE_ORGTRANS.to_ORG=DATABASE_cprk.Organization And IE_ORGTRANS.to_bmc_flag=DATABASE_cprk.bmc_flag And IE_ORGTRANS.roa_flag=DATABASE_cprk.roa_flag  \n"
//								+ "And IE_ORGTRANS.VERSION=DATABASE_cprk.VERSION AND IE_ORGTRANS.to_ORG ='VP' \n"
//								+ "AND OPER_FLAG=0 And IE_ORGTRANS.to_flag=2 \n"
//								+ ") \n"
//								+ "WHERE CPNO||Organization||VERSION||BMC_FLAG||ROA_FLAG IN \n"
//								+ "( \n"
//								+ "SELECT IE_ORGTRANS.LJNO||IE_ORGTRANS.to_ORG||IE_ORGTRANS.VERSION||IE_ORGTRANS.TO_BMC_FLAG||IE_ORGTRANS.ROA_FLAG \n"
//								+ "FROM IE_ORGTRANS \n"
//								+ "WHERE OPER_FLAG=0 And IE_ORGTRANS.to_flag=2 AND IE_ORGTRANS.to_ORG ='VP' \n"
//								+ ")";
//							
//
//						//ת����Ʒ���,û�ڳ�Ʒ���е�����,��������							
//						t_sql[12] = "INSERT INTO DATABASE_CPRK(CPNO,VERSION,ORGANIZATION,QUANTITY,UNIT,BMC_FLAG,ROA_FLAG)  \n"
//								+ "( \n"
//								+ "SELECT LJNO,VERSION,TO_ORG,-SUM(QUANTITY),UNIT,TO_BMC_FLAG,ROA_FLAG  \n"
//								+ "FROM IE_ORGTRANS  \n"
//								+ "WHERE  OPER_FLAG=0 AND TO_ORG ='VP'  And TO_flag=2 \n"								
//								+ "AND  LJNO||VERSION||TO_ORG||TO_BMC_FLAG||ROA_FLAG NOT IN  \n"
//								+ "( \n"
//								+ "SELECT CPNO||VERSION||Organization||BMC_FLAG||ROA_FLAG   \n"
//								+ "FROM DATABASE_CPRK  \n"																
//								+ ") \n"
//								+ "GROUP BY LJNO,VERSION,TO_ORG,UNIT,TO_BMC_FLAG,ROA_FLAG)";
//						
//						bFlag = bFlag && dbo.execute(t_sql);				
//						
//						t_sql = new String[4];
//						//ά����˰��¼����
//						t_sql[0] = "Insert Into ljnx_total \n"
//								 + "Select Distinct ljno,org,0,3 \n"
//								 + "From  \n"
//								 + "( \n"
//								 + "Select Distinct ljno,from_org As org \n"
//								 + "From ie_orgtrans \n"
//								 + "Where ie_orgtrans.from_org ='VP' And to_org='CS' And oper_flag=0 \n"
//								 + "Union  \n"
//								 + "Select Distinct ljno,to_org As org \n"
//								 + "From ie_orgtrans \n"
//								 + "Where ie_orgtrans.to_org ='VP' And from_org='CS' And oper_flag=0 \n"
//								 + ") \n"
//								 + "Where ljno||org Not In (Select ljno||Organization From ljnx_total Where srctype=3)";
//								 
//						t_sql[1] = "Update ljnx_total Set quantity=quantity- \n"
//								 + "( \n"
//								 + "Select Sum(quantity)  \n"
//								 + "From ie_orgtrans ot  \n"
//								 + "Where from_org =ljnx_total.Organization And to_org='CS' And wlflag='B' And ot.ljno=ljnx_total.ljno And oper_flag=0 \n"
//								 + ") \n"
//								 + "Where ljno||Organization In (Select ljno||from_org From ie_orgtrans Where from_org ='VP' And to_org='CS' And wlflag='B' And oper_flag=0) And srctype=3";
//		
//						t_sql[2] = "Update ljnx_total Set quantity=quantity+ \n"
//								 + "( \n"
//								 + "Select Sum(quantity)  \n"
//								 + "From ie_orgtrans ot  \n"
//								 + "Where to_org=ljnx_total.Organization And from_org='CS' And wlflag='B' And ot.ljno=ljnx_total.ljno And oper_flag=0 \n"
//								 + ") \n"
//								 + "Where ljno||Organization In (Select ljno||to_org From ie_orgtrans Where to_org ='VP' And from_org='CS' And wlflag='B' And oper_flag=0) And srctype=3";
//
//						//��Ʒ,���Ʒת��CS����Ҫ����???????,Ŀǰ��������ת�Ƴ�Ʒ/���Ʒ�ǲ���ϸ�IE
//						
//						t_sql[3] = "UPDATE IE_ORGTRANS SET OPER_FLAG=1 WHERE OPER_FLAG=0";
//						bFlag = bFlag && dbo.execute(t_sql);
//					}
//					
//					//ɾ���ļ�
//					if (bFlag) {
//						File f = new File(MAIN_DIR+"/orgtrans/"+(String)vecFileList.get(i));
//						boolean bdel = f.delete();
//						multicommit();
//					}
//				}
//				catch(Exception e) {
//					e.printStackTrace();
//					multirollback();
//				}
//			}
//			commit();
//			
//			return true;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//			return false;
//		}
//	}
//	
//	/*
//	 * ����
//	 * Ŀ¼: curr
//	 */
//	private boolean getCurr() {
//		try {
//			//search file list
//			Vector vecFileList = getFileList("curr");
//
//			DataBaseObject dbo = getDataBaseObject();
//			for (int i=0; i < vecFileList.size(); i++) {
//				try {
//					boolean bFlag = true;
//					SAXReader saxReader = new SAXReader();
//					Document document = saxReader.read(MAIN_DIR+"/curr/"+(String)vecFileList.get(i));
//					Element root = document.getRootElement();
//					//����
//					Iterator iter_root = root.elementIterator();
//					while (iter_root.hasNext()) {
//						Element element_entry = (Element)iter_root.next();
//						//����
//						Iterator iter_entry = element_entry.elementIterator();
//						CommonBean cbCurr = new CommonBean();
//						while (iter_entry.hasNext()) {
//							Element element_item = (Element)iter_entry.next();
//							cbCurr.addValue(element_item.getName().toLowerCase(),element_item.getText());
//						}
//						
//						String[] arrSQL = new String[cbCurr.getRowNum()];
//						for (int si = 0; si < arrSQL.length; si++) {
//							String curr = cbCurr.getCellStr(si,"rate");
//							arrSQL[si] = "UPDATE CL_CURR SET RATE = "+(curr==null || curr.equals("")?"0":curr)+" WHERE SENAME='"+cbCurr.getCellStr(si,"for_currency")+"'";
//						}
//						
//						bFlag = bFlag && dbo.execute(arrSQL);
//					}
//					//ɾ���ļ�
//					if (bFlag) {
//						File f = new File(MAIN_DIR+"/curr/"+(String)vecFileList.get(i));
//						boolean bdel = f.delete();
//						multicommit();
//					}
//				}
//				catch(Exception e) {
//					e.printStackTrace();
//					multirollback();
//				}
//			}
//			commit();
//			
//			return true;
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			rollback();
//			return false;
//		}
//	}
//	
//	/*
//	 * get file list
//	 * @subdir: sub directory for different object
//	 */
//	private Vector getFileList(String subdir) {
//		Vector vecFileList = new Vector();
//		String dirname = MAIN_DIR + "/" + subdir;
//		File fdir = new File(dirname);
//		String[] filelist = fdir.list();
//		for (int i = 0; i < filelist.length; i++) {
//			File f = new File(dirname + "/" + filelist[i]);
//			if (f.isFile()) {
//				vecFileList.add(filelist[i]);
//			}
//		}
//		return vecFileList;
//	}
//}
