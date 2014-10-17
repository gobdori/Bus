package bus.db;

public class BusColumn 
{
	private String BUSTIMEID;
	private String BUSTYPEID;
	private String DESTMNID;	
	private String ARRTMNID;
	private String BUSSTTIME;		
	private int BUSPRICE;
	private String BUSSTOP;
	private String BUSLEADTIME;
	
	public String getBUSTIMEID() {
		return BUSTIMEID;
	}
	public void setBUSTIMEID(String bUSTIMEID) {
		BUSTIMEID = bUSTIMEID;
	}
	
	public String getBUSTYPEID() {
		return BUSTYPEID;
	}
	public void setBUSTYPEID(String bUSTYPEID) {
		BUSTYPEID = bUSTYPEID;
	}
	public String getDESTMNID() {
		return DESTMNID;
	}
	public void setDESTMNID(String dESTMNID) {
		DESTMNID = dESTMNID;
	}
	public String getARRTMNID() {
		return ARRTMNID;
	}
	public void setARRTMNID(String aRRTMNID) {
		ARRTMNID = aRRTMNID;
	}
	public String getBUSSTTIME() {
		return BUSSTTIME;
	}
	public void setBUSSTTIME(String bUSSTTIME) {
		BUSSTTIME = bUSSTTIME;
	}
	public int getBUSPRICE() {
		return BUSPRICE;
	}
	public void setBUSPRICE(int bUSPRICE) {
		BUSPRICE = bUSPRICE;
	}
	public String getBUSSTOP() {
		return BUSSTOP;
	}
	public void setBUSSTOP(String bUSSTOP) {
		BUSSTOP = bUSSTOP;
	}
	public String getBUSLEADTIME() {
		return BUSLEADTIME;
	}
	public void setBUSLEADTIME(String bUSLEADTIME) {
		BUSLEADTIME = bUSLEADTIME;
	}	

}
