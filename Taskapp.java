package TaskApp;
import java.sql.*;
import java.util.*;



public class Taskapp extends menu_view {

	public static void main(String[] args) {
	 
		try {
		//driver load
		Class.forName("com.mysql.cj.jdbc.Driver");
		//creating a connection
		 String url = "jdbc:mysql://localhost:3306/Demo";
		 String user = "root";
		 String pass = "";
		 
		 Connection con = DriverManager.getConnection(url, user, pass);
		 
		 if(con.isClosed()) {
			 
			 System.out.println("Connection is closed...");
		 }else {
			 System.out.println( "Connection Success...!!");
		 }
		 
	        Scanner sc = new Scanner(System.in);
	        
	        while(true){
	        	menu_view();
	        	int choice ;
		        choice = sc.nextInt();  	
	        switch(choice) {
	        
	        case 1: 
	        	// creating query for inserting the tasks in database
	        	String query = "INSERT INTO `tasklist` (`task_number`, `Task_name`) VALUES (?,?)";
	        	PreparedStatement pstmt = con.prepareStatement(query);
	           
	        	 //Asking for entering the Task number
	        	 System.out.print("*Enter Task Number:- ");
	        	 int task_num = sc.nextInt();
	        	//Asking for entering the Task Name
				 System.out.print("Enter Task Name:- ");
				 String task_name = sc.next();
				  //inseting data into database using PreparedStatement
				
				 try {
					 pstmt.setInt(1, task_num);
					 pstmt.setString(2, task_name);
					 pstmt.executeUpdate();
					 System.out.println("\tTask Added...");
				 }catch (Exception e) {
					 System.out.println("\tDuplicate Task Number...");
				 }
				 break;	  
				 
	        case 2:
	        	try {
	        		System.out.print("Enter task number to delete the task:- ");
		        	 int num = sc.nextInt();
		        	 query = "DELETE FROM `tasklist` WHERE `task_number` = "+num;
		        	 Statement stmt = con.createStatement();
		        	 int rowsaffected = stmt.executeUpdate(query);
		        	 if(rowsaffected  > 0) {
		        		 System.out.println("Task Deleted...");
		        	 }else {
		        		 System.out.println("oh ohh ... try again....");
		        	 }
		         
	        	}catch (Exception e) {
	        		 System.out.println("Somthing Went Wrong..");
	        	}
	        	 
	        	break;
	        
	        case 3:
	        	System.out.println("Tasks Are "); 
	        	String show_task_query = "SELECT * FROM `tasklist`";
	        	pstmt = con.prepareStatement(show_task_query);
	        	ResultSet rs = pstmt.executeQuery();
	        	 rs =  pstmt.executeQuery();	        	
	        	while(rs.next()) 
	        	{
	        		 int tasknum = rs.getInt("task_number");
	        		 System.out.print("\t"+tasknum);
	        		 String taskname = rs.getString("Task_name");
	        		 System.out.println(" "+taskname);
	        	}
	        	break;
	        	
	        case 4:
	        	sc.close();
	        	con.close();
	        	return;	        
	        
	        default:
	        	System.out.println("\t\tInvalid Option Try again");
	        	break;		
	        }
	        }	 	 
		}catch (Exception e){
			System.out.println("Somthing went wrong...");
			
			 
		}

	}

}
