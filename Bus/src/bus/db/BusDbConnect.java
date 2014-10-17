package bus.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class BusDbConnect 
{
	//프로젝트 오른쪽 클릭 후 Bulid path에 들어가서
	//Configure Build Path를 클릭 오른쪽 tab에 
	//Libraries로 가서 add External Jars를 누른 후 
	//오라클 설치 폴더에서 /jdbc 폴더를 찾아 들어간 후
	//ojdbc6.jar 이나 ojdbc7.jar을 추가 합니다. 
	//그럼 밑에 4줄을 쓸 수가 있음. 
	protected static Connection conn;
	protected Statement state;
	protected PreparedStatement pstmt = null;
	protected ResultSet result;
	
	public static Connection getConnect()//버스DB에 연결할 생성자 입니다.  
	{
		String id = "siri";//오라클 접속 id
		String pw = "123456";//오라클 접속 비밀번호
		String url  = "jdbc:oracle:thin:@180.228.49.152:1521/pdborcl"; // 192.168.0.172
		
		try 
		{
			//DriverManager라는 것을 통해 접속. 
			conn = DriverManager.getConnection(url, id, pw);			
			System.out.println("DataBase Connect!");			
		} 
		catch (Exception e) 
		{
			conn= null;
			System.out.println(e);			
		}
		return conn;
	}
	
	public void setQuery(String query) 
	{
		try 
		{
			//여긴 무슨 의미인지 잘 모르겠음.
			//접속 한 곳의 정보를 받아와서 state에 넣어주고
			//그 접속정보에 query를 날리는 그런 개념인듯. 
			state = conn.createStatement();
			state.executeQuery(query);//쿼리를 날리는 곳
			
			System.out.println("Complete Query!");
		} 
		catch(Exception e) 
		{
			System.out.println(e);
		} 
		finally 
		{
			close();
		}
	}
	
	public void close() 
	{
		try 
		{
			//디비 접속을 종료
			conn.close();			
			System.out.println("DataBase Close!");
		} 
		catch(Exception e) 
		{
			System.out.println(e);
		}
	}	
}
