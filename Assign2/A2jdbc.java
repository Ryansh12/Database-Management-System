import java.sql.*;
import java.util.Scanner;

public class Index_View 
{
 public static void main(String args[]) throws Exception
 {
	 int flag=1,ch;
	 Scanner obj= new Scanner(System.in);
	 //Registering JDBC driver
	 String url= "jdbc:mysql://192.168.5.154:3306/";
	 String dbname= "te3454db";
	 String username= "te3454";
	 String password= "te3454";
	 
	 Class.forName("com.mysql.jdbc.Driver").newInstance();
	 
	 //Creating Connection
	 Connection conn = DriverManager.getConnection(url+dbname,username,password);
	 
	 //Creating Query
	 
	 Statement statement=conn.createStatement();
	 String query;
	 ResultSet rs;
	
	 while(flag==1)
	 {
		 System.out.println("1.Create sequence \n2.Create view and show \n3.Create Simple index \n4.Create Unique index \n5.Create Composite index  \n6.Show index \n7.Drop view \n8.Drop index \n9.Exit \nEnter choice-: ");
		 ch=obj.nextInt();
		 
		 switch(ch)
		 {
		 case 1:
			 query="create table student" +"(roll Integer unsigned not null auto_increment ," +"primary key(roll)," + "name varchar(10)," + "city varchar(10))";
			 statement.executeUpdate(query);
			 query="insert into student values(null,'chinmay','jalgaon')";
			 statement.executeUpdate(query);
			 query="insert into student values(null,'dev','nashik')";
			 statement.executeUpdate(query);
			 query="insert into student values(null,'sid','nashik')";
			 statement.executeUpdate(query);
			 query="insert into student values(null,'sangat','nagpur')";
			 statement.executeUpdate(query);
			 
			 query="select * from student";
			 rs=statement.executeQuery(query);

				System.out.println("Created table using sequence is-: \n");
			 while (rs.next()) 
			 {
				int roll = rs.getInt("roll");
				String name = rs.getString("name");
				String city = rs.getString("city");
				System.out.println("\nStudent Roll No.-:"+roll +"\nStudent Name-: " +name +"\nStudent city-: " +city +"\n");
			 }
			 break;
			 
		 case 2:
			 System.out.println("Enter columns for creating view-: ");
			 String col1=obj.next();
			 String col2=obj.next();
			 query="create view name_mail as select "+col1+","+col2+" from professors";
			 statement.executeUpdate(query);
			 
			 System.out.println("Created View of professors is-: \n");
			 query="select * from name_mail";
			 rs=statement.executeQuery(query);
			 while(rs.next())
			 {
				 String par1=rs.getString(col1);
				 String par2=rs.getString(col2);
				 System.out.println("\n"+col1+"-: "+par1+"\t"+col2+"-:"+par2);
			 }
			 
			 break;
			 
		 case 3:
			 query="create index simpl on professors(city)";
			 statement.executeUpdate(query);
			 break;
			 
		 case 4:
			 query="create unique index uniq on professors(prof_fname)";
			 statement.executeUpdate(query);
			 break;
			 
		 case 5:
			 query="create index compos on professors(prof_lname,phone)";
			 statement.executeUpdate(query);
			 break;
			 
		 case 6:
			 System.out.println("Indexes of professors are-:  \n");
			 query="show index from professors";
			 rs=statement.executeQuery(query);
			 
			 while(rs.next())
			 {
				 String keyname=rs.getString("Key_name");
				 String column_name=rs.getString("Column_name");
				 System.out.println("\nKey_Name-: "+keyname+"\tColumn_Name-: "+column_name);
			 }
			 break;
			 
		 case 7:
			 query="drop view name_mail";
			 statement.executeUpdate(query);
			 break;
			 
		 case 8:
			 query="drop index uniq on professors";
			 statement.executeUpdate(query);
			 query="drop index simpl on professors";
			 statement.executeUpdate(query);			
			 query="drop index compos on professors";
			 statement.executeUpdate(query);
			 System.out.println("Indexes are dropped!!\n");
			 break;
			 
		 case 9:
			 flag=0;
			 break;
			 
			 default:
				 System.out.println("Enter valid choice!!");
				 break;
		 }
	 }
	 
	 conn.close();
 }
}
