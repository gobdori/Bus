package TEST;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class TIMETABLE {
	
	static String[] tmnid1= {"DG",	"KJ","CW","DJ","MS","JJ","YS","MP",	"SL","BS"};
	static String[] tmnid2 = {"DG",	"KJ","CW","DJ","MS","JJ","YS","MP",	"SL","BS"};
	static String[] tmnname = {"대구", "경주",	"창원","마산","대전", "전주","여수","목포","서울","부산"};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//출발지, 도착지, 출발시간의 hour, 출발시간의 min, 배가 간격 분)
									//(출발, 도착, 버스, 년도, 월, 일, 출발시간, 분, 출발간격, 가격, 걸리는 시간)
		
		/*new TIMETABLE("BS","SL","일반",2014, 10, 18, 8, 0, 40, 23100,  "4시간 50분");
		new TIMETABLE("BS","SL","우등",2014, 10, 18, 8, 10, 40, 25100,  "4시간 50분");
		new TIMETABLE("BS","MP","일반",2014, 10, 18, 8, 20, 40, 24200,  "3시간 50분");
		new TIMETABLE("BS","MP","우등",2014, 10, 18, 8, 15, 40, 26200,  "3시간 50분");
		new TIMETABLE("BS","YS","일반",2014, 10, 18, 8, 25, 40, 27000,  "2시간 50분");
		new TIMETABLE("BS","YS","우등",2014, 10, 18, 8, 35, 40, 28600,  "2시간 50분");
		new TIMETABLE("BS","JJ","일반",2014, 10, 18, 8, 45, 40, 23500,  "1시간 50분");		
		new TIMETABLE("BS","JJ","우등",2014, 10, 18, 8, 0, 40, 26100,  "1시간 50분");*/
		
		/*new TIMETABLE("BS","MS","일반",2014, 10, 17, 8, 0, 40, 23100,  "1시간 50분");
		new TIMETABLE("BS","MS","우등",2014, 10, 17, 8, 0, 40, 23100,  "1시간 50분");
		new TIMETABLE("BS","DJ","일반",2014, 10, 17, 8, 0, 40, 23100,  "2시간 50분");
		new TIMETABLE("BS","DJ","우등",2014, 10, 17, 8, 0, 40, 23100,  "2시간 50분");
		new TIMETABLE("BS","CW","일반",2014, 10, 17, 8, 0, 40, 23100,  "1시간 30분");
		new TIMETABLE("BS","CW","우등",2014, 10, 17, 8, 0, 40, 23100,  "1시간 30분");*/
		
		new TIMETABLE("BS","KJ","일반",2014, 10, 17, 8, 0, 40, 20100,  "2시간 50분");
		new TIMETABLE("BS","KJ","우등",2014, 10, 17, 8, 0, 40, 22200,  "2시간 50분");
		new TIMETABLE("BS","DG","일반",2014, 10, 17, 8, 0, 40, 23300,  "3시간 50분");
		new TIMETABLE("BS","DG","우등",2014, 10, 17, 8, 0, 40, 24600,  "3시간 50분");
		
	}
		
	TIMETABLE(String des, String arr, String BUSGRADE,int year, int month, int date, int stthour, int sttmin, int  gaptime, int BUSPRICE,String BUSLEADTIME )
	{
		Calendar cal =  Calendar.getInstance();
		
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DATE, date);
		cal.set(Calendar.HOUR_OF_DAY, stthour);
		cal.set(Calendar.MINUTE, sttmin);		
				
		String BUSSTTIME="";
		
		if(BUSGRADE=="우등")
			BUSGRADE="PBUS";
		else if(BUSGRADE=="일반")
			BUSGRADE="OBUS";
		
		while(cal.getTime().getHours() !=1)
		{		
				if(des !=arr)
				{					
						BUSSTTIME = cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DATE)+" "+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
						
						System.out.println(
								"INSERT INTO BUSTIMETBL(BUSTIMEID, BUSTYPEID, DESTMNID, ARRTMNID, BUSSTTIME, BUSPRICE,BUSSTOP, BUSLEADTIME)"
								+ " VALUES ("+"'"+des.toString()+"D'"+"||SEQ_BUSTIME.NEXTVAL,'"+BUSGRADE+"','"+des.toString()+"','"+arr.toString()+"',"+"TO_DATE('"+BUSSTTIME+"','YYYY-MM-DD HH24:MI')"+","+BUSPRICE+",'','"+BUSLEADTIME+"');");
												
						System.out.println(
								"INSERT INTO BUSTIMETBL(BUSTIMEID, BUSTYPEID, DESTMNID, ARRTMNID, BUSSTTIME, BUSPRICE,BUSSTOP, BUSLEADTIME)"
								+ " VALUES ("+"'"+arr.toString()+"D'"+"||SEQ_BUSTIME.NEXTVAL,'"+BUSGRADE+"','"+arr.toString()+"','"+des.toString()+"',"+"TO_DATE('"+BUSSTTIME+"','YYYY-MM-DD HH24:MI')"+","+BUSPRICE+",'','"+BUSLEADTIME+"');");
												
				}			
				cal.set(cal.MINUTE, cal.get(cal.MINUTE)+gaptime);				
		}
		
	}
}

