package bus.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

public class DbQuery 
{	
	//로그인이 성공인지 아닌지 체크하는 값
	private boolean log;  //로그인이 됐는지 아닌지 확인하는 값
	private static String username;  //로그인 성공시 가져올 회원이름
	private static String userid;  //로그인 성공시 가져올 회원 ID
	public static String getUserid() {
		return userid;
	}
	public static void setUserid(String userid) {
		DbQuery.userid = userid;
	}

	 Connection conn;	//연결객체
	 PreparedStatement pstmt = null;//쿼리를 날리기 위한 어떤 것;;
	 ResultSet result = null;//쿼리를 보낸 후 받아올 결과값을 저장할 어떤 것.
	private static Vector<BusColumn> obustime;//일반버스 시간표 벡터값
	private static Vector<BusColumn> pbustime;//우등버스 시간표 벡터값
	
	
////////////////////////////////////////////////////////////////
/////GETTER and SETTER////
////////////////////////////////////////////////////////////////
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		DbQuery.username = username;
	}

	public static Vector<BusColumn> getObustime() {
		return obustime;
	}
	public static void setObustime(Vector<BusColumn> obustime) {
		DbQuery.obustime = obustime;
	}

	public static Vector<BusColumn> getPbustime() {
		return pbustime;
	}
	public static void setPbustime(Vector<BusColumn> pbustime) {
		DbQuery.pbustime = pbustime;
	}

	public boolean isLog()
	{
		return log;
	}
	public void setLog(boolean log) 
	{
		this.log = log;
	}	
	
	
	
////////////////////////////////////////////////////////////////////////
/////출발지, 도착지, 날짜를 선택 했을 경우 버스 시간표를 가져 오는 메소드////
/////실행을 시키기 위해선 날짜정보, 출발지정도, 도착지정보가 필요함.    ////
///////////////////////////////////////////////////////////////////////
	public Vector<BusColumn> getBusList(String des, String arr, String date)
	{
		//쿼리를 보낸 후 result 값을 받은 후에 그 result 값을 넣을 벡터값 생성
		Vector<BusColumn> busList = new Vector<BusColumn>();
		
		//위에서 받은 벡터 값들 중에서 일반버스와 우등버스 값을 받을
		//각각의 벡터값을 생성. 
		obustime = new Vector<BusColumn>();
		pbustime = new Vector<BusColumn>();
	
		try
		{
			conn= BusDbConnect.getConnect();//DB 연결

			//DB에 보낼 쿼리 구문
			pstmt = conn.prepareStatement("select BUSTIMEID,BUSTYPEID,DESTMNID,ARRTMNID,to_char(bussttime,'HH24:mi') as BUSSTTIME,BUSPRICE,BUSLEADTIME "
					+ "from view_bustime "
					+ "where DESTMNID = ? and ARRTMNID = ? and TO_CHAR(BUSSTTIME, 'yyyy-mm-dd') like ?");
			date = date+"%";
			
			pstmt.setString(1, des);//쿼리 구문중에 ?에 date를 넣어줌.날짜정보
			System.out.println(des);
			pstmt.setString(2, arr);//쿼리 구문중에 ?에 des를 넣어줌.출발지정보
			System.out.println(arr);
			pstmt.setString(3, date);//쿼리 구문중에 ?에 arr를 넣어줌.도착지정보
			System.out.println(date);
			result=pstmt.executeQuery();//쿼리를 DB로 보냄(실행시킨다는 의미)
					
			if(result.next())
			{
				do
				{				
					BusColumn buscol = new BusColumn();//벡터에 들어갈 컬럼값 객체를 생성(이게 아마 vo..)
					
					//컬럼이 "BUSTIMEID" 인 결과값을 위에서 생성한 buscol 에 셋팅해줌
					//buscol 자체가 vo로 생성한 것이기에 vo안에 만든 setter로 셋팅함. 
					buscol.setBUSTIMEID(result.getString("BUSTIMEID"));
					buscol.setBUSTYPEID(result.getString("BUSTYPEID"));
					buscol.setDESTMNID(result.getString("DESTMNID"));
					buscol.setARRTMNID(result.getString("ARRTMNID"));
					buscol.setBUSSTTIME(result.getString("BUSSTTIME"));				
					buscol.setBUSPRICE(result.getInt("BUSPRICE"));
					buscol.setBUSLEADTIME(result.getString("BUSLEADTIME"));
					//여기까지 같은 의미 
					
					//buscol의 의미는 벡터에 들어갈 한 행을 뜻함. 
					//벡터 busList 에  buscol를 추가(한 행을 추가한다는 의미)
					busList.add(buscol);				
					
					//우등버스일 경우 들어갈 벡터와
					//일반버스일 경우 들어갈 벡터에도 값을 넣음
					if(result.getString("BUSTYPEID").equals("OBUS"))
					{	
						System.out.println("obustime");
						obustime.add(buscol);
					}
					else if(result.getString("BUSTYPEID").equals("PBUS"))
					{					
						System.out.println("pbustime");
						pbustime.add(buscol);
					}
				}
				while(result.next());//결과값이 있으면~~~~밑에 부분이 돌아감.		
			}
			else
			{
				JOptionPane.showMessageDialog(null,"출발버스가 없습니다");
			}
			  
						
			
			
		}
		catch(SQLException e)
		{
			System.out.println("에러");
			e.printStackTrace();			
		}
		finally
		{
			try
			{
				result.close();
				pstmt.close();
				conn.close();
			}
			catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null,e.getErrorCode());
			}
			
		}
		return busList;
		
	}
	
	//로그인을 하는 메소드(매개변수로(textfield에 있는 값)는 id,pwd를 쓴다)
	public void DbLogin(String id , String pwd)
	{		
		//쿼리를 날리기 위해 DB연결 생성		
		try
		{
			conn = BusDbConnect.getConnect();
			//select 구문을 prepareStatement에 저장시킨다는 의미 같음. 잘 모르겠음.
			pstmt = conn.prepareStatement("select USERID, UPWD, UNAME from USRTBL where USERID = ? AND UPWD= ?");		
						
			//prepareStatement에..즉 pstmt의 변수("?") 두개 값에 id,랑 pwd를 넣어준다. 
			//넣어 줌으로써 완전한 select 구문이 완성 된다. 
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);			
			
			//pstmt의 완전한 구문을 executeQuery하란 의미 즉, DB로 보내라는 의미
			//그 결과 값을 result에 넣으라는 의미
			result = pstmt.executeQuery();
			
			//result는 한줄씩 결과값을 가져온다. 
			//다음 줄이 있을 경우에~~ ~를 하란 의미이다. 
			if(result.next())
			{
				//다음 줄이 있으므로 텍스트 박스에 넣은 id와 pwd로 usrtbl에 검색이 되었다는 의미이다.
				//conn.result.getString("UNAME") 
				//이건 검색된 행의 UNAME컬럼의 값을 의미한다. 여기선 사용자 이름!!
				username = result.getString("UNAME");
				userid = id;
				JOptionPane.showMessageDialog(null,result.getString("UNAME")+"님이 로그인 하셨습니다.");
				
				//로그인이 성공적으로 되었으므로 체크값을 true로 바꾼다.  
				setLog(true);
			}
			else
			{
				//다음 줄이 없으므로 로그인 안됨. 체크값 false;
				setLog(false);
				return;
			}			
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getErrorCode());
		}
		finally
		{
			try 
			{
				//오라클 접속을 종료.
				if(result != null) result.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} 
			catch(SQLException e) 
			{
				JOptionPane.showMessageDialog(null,e.getErrorCode());
			}
		}
	}

////////////////////////////////////////////////////////////////////////
/////터미널ID를 가져오기 위한 메소드/////////////////////////////////////
/////실행을 시키기 위해선 터미널이름이 필요함.    /////////////////////////
///////////////////////////////////////////////////////////////////////
	public String getTmnId(String tmnname) 
	{
		//실행시킬 쿼리구문을 써놓음
		String selectTmnId = "SELECT tmnid FROM TMNINFOTBL where tmnname = ?";		
		String tnmid="";//결과값(result)을 받아올 객체를 생성. 
		try 
		{
			conn = BusDbConnect.getConnect();//연결
			pstmt = conn.prepareStatement(selectTmnId);//쿼리구문 설정.
			pstmt.setString(1, tmnname);//쿼리구문 속 ?에 들어갈 값(터미널이름)을 넣어줌.
			
			result = pstmt.executeQuery();//쿼리 실행. 		
			
			while(result.next()) //결과값이 있으면 밑으로~
			{				
				tnmid =result.getString("tmnid");//tmnid 컬럼의 결과값을 tmnid(위에서 만든 객체)에 넣음
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if(result != null) result.close();
				if(pstmt != null) result.close();
				if(conn != null) conn.close();
				
			} catch(SQLException e) {}
		}		
		return tnmid;	
	}	
	
	//위에랑 같음. 여기선 터미널ID로 터미널이름을 찾는 것. 
	public Vector<String> getTmnName() 
	{
		String selectTmnId = "SELECT tmnname FROM TMNINFOTBL";
		Vector<String> tname = new Vector<String>(); 
		String tnmid="";
		try 
		{
			conn = BusDbConnect.getConnect();
			pstmt = conn.prepareStatement(selectTmnId);			
			
			result = pstmt.executeQuery();				
			
			while(result.next()) 
			{				
				tname.add(result.getString("tmnname"));			
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if(result != null) result.close();
				if(pstmt != null) result.close();
				if(conn != null) conn.close();
				
			} catch(SQLException e) {}
		}		
		return tname;	
	}	
	
	//만들다 만 메소드. 
	public void udtRsvQry(String rsvid)
	{
		conn = BusDbConnect.getConnect();
		String qry = "update RSVINFOTBL set RSVSTATEID = 'paid' where rsvid = ?";
		
		try
		{
			pstmt = conn.prepareStatement(qry);
			pstmt.setString(1, rsvid);
			int results = pstmt.executeUpdate();
			
			if(results !=1)
				JOptionPane.showMessageDialog(null,"저장에 실패하였습니다.");			
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getErrorCode());
		}
		finally
		{
			try
			{				
				pstmt.close();
				conn.close();
			}
			catch(SQLException e)
			{
				
			}
		}
		
	}
	
////////////////////////////////////////////////////////////////////////
/////버스 예약시 DB에 예약 정보를 저장시키는 메소드(INSERT)/////////////
/////실행을 시키기 위해선 유져ID,버스시간ID,좌석정보가 필요함////////////
///////////////////////////////////////////////////////////////////////
	public void istRsv(String userid, String bustimeid, String rsvseat, int paymoney, String usergrade)
	{
		conn =BusDbConnect.getConnect(); 
		
		try
		{
			pstmt = conn.prepareStatement(
					"insert into RSVINFOTBL(rsvid, userid, bustimeid, rsvseat, rsvstateid, rsvdate,paymoney,usergrade)"
					+ "values(SEQ_RSVINFO.NEXTVAL, ? , ?, ?,'beforepaid',SYSDATE,?,?)");
			pstmt.setString(1, userid);
			System.out.println(userid);
			pstmt.setString(2, bustimeid);
			System.out.println(bustimeid);
			pstmt.setString(3, rsvseat);
			System.out.println(rsvseat);
			pstmt.setInt(4, paymoney);
			System.out.println(paymoney);
			pstmt.setString(5, usergrade);
			System.out.println(usergrade);
			
			int results = pstmt.executeUpdate();
			if(results !=1)
				JOptionPane.showMessageDialog(null,"저장에 실패하였습니다.");
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,"인서트 에러"+e.getErrorCode());
		}
		finally
		{
			try
			{				
				pstmt.close();
				conn.close();
			}
			catch(SQLException e)
			{
				
			}
		}
		
	}
	
////////////////////////////////////////////////////////////////////////
/////예약 테이블을 불러오기 위한 메소드  /////////////
/////실행을 시키기 위해선 유져ID가 필요함////////////
///////////////////////////////////////////////////////////////////////	
	public Vector<RsvColumn> getRsvList(String name){

		String qry = "select rsvid, userid, rsvseat, rsvstateid, paymentid, rsvtrip, "
				+ "rsvdate, paymoney,busprice ,usergrade, handycap, bustimeid, "
				+ "bussttime, destmnid, arrtmnid, desname, arrname from view_rsv where userid = ?";

		Vector<RsvColumn> rsvList = new Vector<RsvColumn>();

		try {
			/* DBManager 클래스를 이용해 Connection 객체를 얻는다. */
			conn = BusDbConnect.getConnect();
			pstmt = conn.prepareStatement(qry);
			pstmt.setString(1, name);
			result = pstmt.executeQuery();

			while (result.next()) {
				RsvColumn rsvCol = new RsvColumn();

				rsvCol.setRSVID(result.getString("rsvid"));
				rsvCol.setUSERID(result.getString("userid"));
				rsvCol.setRSVSEAT(result.getString("rsvseat"));
				rsvCol.setRSVSTATEID(result.getString("rsvstateid"));
				rsvCol.setPAYMENTID(result.getString("paymentid"));				
				rsvCol.setRSVDATE(result.getString("rsvdate"));
				rsvCol.setPAYMONEY(result.getInt("paymoney"));
				rsvCol.setBUSPRICE(result.getInt("busprice"));
				rsvCol.setHANDYCAP(result.getString("handycap"));
				rsvCol.setBUSTIMEID(result.getString("bustimeid"));
				rsvCol.setBUSSTTIME(result.getString("bussttime"));
				rsvCol.setDESTMNID(result.getString("destmnid"));
				rsvCol.setARRTMNID(result.getString("arrtmnid"));
				rsvCol.setDESNAME(result.getString("desname"));
				rsvCol.setARRNAME(result.getString("arrname"));
				
				rsvList.add(rsvCol);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getRsvList"+e.getErrorCode());
		} finally {
			try
			{
				result.close();
				pstmt.close();
				conn.close();
			}
			catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null,e.getErrorCode());
			}			
		}
		return rsvList;
	}
	
	public Vector<String> GetUsedSeat(String busid)
	{
		conn =BusDbConnect.getConnect();
		Vector<String> seats= new Vector<String>();
		try
		{
			pstmt = conn.prepareStatement("select distinct(RSVSEAT) as RSVSEAT from RSVINFOTBL where bustimeid = ?");
			pstmt.setString(1, busid);
			result = pstmt.executeQuery();
			
			
			while(result.next())
			{		
				seats.add(result.getString("RSVSEAT"));		
			}
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,"selectSeat"+e.getErrorCode());
		}		
		finally
		{
			try
			{
				result.close();
				pstmt.close();
				conn.close();
			}
			catch(SQLException e)
			{
				
			}
		}
		return seats;
	}
////////////////////////////////////////////////////////////////////////
/////회원 가입시 DB에 회원 정보를 저장시키는 메소드(INSERT)/////////////
/////실행을 시키기 위해선 유져ID,비밀번호, 유저등급, 나이, 이름////////////
/////주소, 생일, 이메일, 폰 정보가 필요함. ////////////
///////////////////////////////////////////////////////////////////////
	public void istUserqry(String USERID, String UPWD, String UGRADE, int UAGE,  String UNAME, String UADDR, String UBIRTH,String UEMAIL, String UPHONE)
	{
		conn =BusDbConnect.getConnect();
		
		try
		{	
			pstmt = conn.prepareStatement("INSERT INTO USRTBL(USERID, UPWD, UGRADE,UAGE,UNAME,UADDR, UBIRTH, UEMAIL, UPHONE) VALUES(?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),?,?)");
			
			if(USERID.equals(null))//ID가 null 일 때 중지 
				return;
			if(UPWD.equals(null))//비밀번호가 null 일 때 중지
				return;
			if(UGRADE.equals(null))//유저등급 null 일 때 USER로 설정(일반 사용자 등급)
				UGRADE = "USER";
			if(UAGE==0)
				UAGE = 0;
			if(UNAME.equals(null))
				UNAME = "";
			if(UADDR.equals(null))
				UADDR = "";
			if(UBIRTH.equals(null))//생일은 기본값 1970-01-01로 설정. 
				UBIRTH = "1970-01-01";
			if(UEMAIL.equals(null))
				UEMAIL = "";
			if(UPHONE.equals(null))
				UPHONE = "";
			pstmt.setString(1, USERID);
			pstmt.setString(2, UPWD);			
			pstmt.setString(3, UGRADE);
			pstmt.setInt(4, UAGE);
			pstmt.setString(5, UNAME);
			pstmt.setString(6, UADDR);
			pstmt.setString(7, UBIRTH);
			pstmt.setString(8, UEMAIL);
			pstmt.setString(9, UPHONE);
			
			//완성된 insert 구문을 DB로 보냄. select가 아니라 받아올 값이 
			//없으므로 result는 쓰지 않음.			
			int results = pstmt.executeUpdate();
			if(results !=1)
				JOptionPane.showMessageDialog(null,"저장에 실패하였습니다.");
		}
		catch(SQLException e)
		{			
			JOptionPane.showMessageDialog(null,e.getErrorCode());			
		}
		finally
		{
			try 
			{		
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} 
			catch(SQLException e) 
			{
				JOptionPane.showMessageDialog(null,e.getErrorCode());
			}
		}
	}	
}


class Tmn
{
	static String tmnid;
	static String tmnname;
	
	public static String getTmnid() {
		return tmnid;
	}
	public static void setTmnid(String tmnid) {
		tmnid = tmnid;
	}
	public static String getTmnname() {
		return tmnname;
	}
	public static void setTmnname(String tmnname) {
		tmnname = tmnname;
	}	
}