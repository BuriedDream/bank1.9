package cn.gluttonous.bank.view.test;
 
import cn.gluttonous.bank.view.tool.FrameUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.JPanel;
import javax.swing.JButton;
 
public class JTableDefineTest extends JFrame{   
 
	 private int currentPage=1;
	 private  int pageSize=13;
	 private int lastPage;
	 JTable table=null;
	 DefaultTableModel dtm=null;
	 JScrollPane jsp=null;
	 JTableDefineTest jTableDefineTest=null;
	 List list,list1; 
	 JButton button1 =null;
	   
	public int getLastPage() {
		return lastPage;
	}
 
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
 
	public int getCurrentPage() {
		return currentPage;
	}
 
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
 
	public int getPageSize() {
		return pageSize;
	}
 
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
 
	public JTableDefineTest(){  
		
		list=FenYeTest.list;

		//设置最后一页
        if(list.size()%pageSize==0){
        	setLastPage(list.size()/getPageSize());
        }else{
        	setLastPage(list.size()/getPageSize()+1);
        }
		
		
		String[] columnNames = {"日期","交易金额","交易余额","摘要"};
		dtm=new DefaultTableModel(columnNames, 0);
		
		 table=new JTable(dtm);
		 jsp = new JScrollPane();
		 jsp.setViewportView(table);
		 getContentPane().add(jsp);
	
		setTitle("账 单 明 细");
		//setBounds(100,100,500,500);
		FrameUtil.initFrame(this,750,500);
	
		showTable(currentPage);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JButton button = new JButton("首页");
		button.addActionListener(new MyTable());
		button.setActionCommand("首页");
		panel.add(button);
		 button1 = new JButton("上一页");
		button1.addActionListener(new MyTable());
		panel.add(button1);
		JButton button2 = new JButton("下一页");
		button2.addActionListener(new MyTable());
		panel.add(button2);
		JButton button3 = new JButton("末页");
		button3.addActionListener(new MyTable());
		panel.add(button3);
		
		}   
	
	public void showTable(int currentPage){
		// 清除原有行
		dtm.setRowCount(0);
		FenYeTest f=new FenYeTest();
		setCurrentPage(currentPage);
		list1=f.findUsers(currentPage, pageSize);
		//获得数据
		for(int row = 0;row<list1.size();row++) {
	     	Vector rowV = new Vector();
	     	User user= (User) list1.get(row);
	     	//数据
			rowV.add(user.getName());
			rowV.add(user.getPassword());
			dtm.addRow(rowV);    
		}
		 
		
	    //	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //关闭表格列的自动调整功能。
		//单选
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSelectionBackground(Color.YELLOW);       
		table.setSelectionForeground(Color.RED); 
		table.setRowHeight(30);  
	}
	
	public  void init(){
		
	}
	
	public static void main(String[] args) {  		
		 new JTableDefineTest(); 						
	}
	
	
	class MyTable  implements ActionListener  
	{      
		@Override
		public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("首页")){
					showTable(1);
				}
				
	            if(e.getActionCommand().equals("上一页")){
	            	if(getCurrentPage()<=1){
	            		setCurrentPage(2);
	            	}
	            	showTable(getCurrentPage()-1);
				}
				
	            if(e.getActionCommand().equals("下一页")){
	            	if(getCurrentPage()<getLastPage()){
	            		showTable(getCurrentPage()+1);
	            	}else{
	            		showTable(getLastPage());
	            	}
				}
				
	            if(e.getActionCommand().equals("末页")){
	            	showTable(getLastPage());
				}
			}	
		}
	}