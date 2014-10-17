package bus.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ProgressManagerDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	/* progress 테이블에서 ProgressList를 가져와 리턴하는 메소드 */
	/**
	 * @return
	 */
	public Vector<Progress> getProgressList() {

		String selectProgress = "select * from RSVINFOTBL order by userid";

		Vector<Progress> progressList = new Vector<Progress>();

		try {
			/* DBManager 클래스를 이용해 Connection 객체를 얻는다. */
			conn = BusDbConnect.getConnect();
			pstmt = conn.prepareStatement(selectProgress);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Progress progress = new Progress();

				progress.setRSVID(rs.getString(1));
				progress.setUSERID(rs.getString(2));
				progress.setBUSTIMEID(rs.getString(3));
				progress.setRSVSEAT(rs.getString(4));
				progress.setPAYMENTID(rs.getString(5));
				progress.setISRID(rs.getString(6));
				progress.setRSVTRIP(rs.getString(7));
				progress.setRSVDATE(rs.getString(8));
				progress.setCANCELDATE(rs.getString(9));
				progress.setPAYMONEY(rs.getInt(10));
				progress.setUSERGRADE(rs.getString(11));
				progress.setHANDYCAP(rs.getString(12));
				
				progressList.add(progress);
				// progress 객체를 Vectro에 저장한다.
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ProgressManagerDao - getProgressList()");
		} finally {
			try
			{
				conn.close();
			}
			catch(SQLException e)
			{
				
			}
			
		}
		return progressList;

	}

}
