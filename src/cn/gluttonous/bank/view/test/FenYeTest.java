package cn.gluttonous.bank.view.test;
 
import java.util.ArrayList;
import java.util.List;
 
public class FenYeTest {
 
	static List<User> list=new ArrayList<User>();

	static{

		for (int i = 0; i < 20; i++) {
			User user=new User(""+i,""+i);
			list.add(user);
		}


	}
 
	public FenYeTest() {
	
	}

	
	public static List<User> findUsers(int currentPage,int pageSize){
	
		
       List<User> list1=new ArrayList();
       int listLength=list.size();
       if(currentPage<1){
    	   currentPage=1;
       }
       int startIndex=(currentPage-1)*pageSize;
       int endIndex=startIndex+pageSize;
       
       if(endIndex>=listLength){
    	   endIndex=listLength;
       }
	   list1=  list.subList(startIndex, endIndex);  
        return list1;  
    }  
	
	
}