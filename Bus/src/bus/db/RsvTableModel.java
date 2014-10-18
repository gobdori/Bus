package bus.db;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;



public class RsvTableModel extends AbstractTableModel {

	/* 기본컬럼명을 저장하는 배열 생성 */
	private final static String[] TABLE_COLUMNS = {
		"","좌석", "예약상태",	"결제방법","왕복", "예약날짜",
		"가격", "출발시간",  "출발지", "도착지"};

	/* progress 테이블에 데이터를 저장하는 Vector선언 */
	private Vector<RsvColumn> progressList;

	/* progress 테이블의 컬럼명을 저장하는 Vector선언 */
	private Vector<String> columnNames;

	public RsvTableModel() {
		// 기본생성자는 외부에서 데이터를 넘겨받지 못하므로 진행사항 정보를 저장할수 있는 Vector<Progress>타입의 객체를
		// 직접생성(progress)하고
		// 컬럼명은 상수로 정의된 배열을 지정하여 인스턴스 변수에 저장하는 다른 생성자를 호출하고 있다.
		this(new Vector<RsvColumn>(), TABLE_COLUMNS);
	}
	
	public RsvTableModel(Vector<RsvColumn> progressList) {
		this(progressList, TABLE_COLUMNS);
	}

	public RsvTableModel(Vector<RsvColumn> progressList, String[] columnNames) {
		setProgressTableModel(progressList, getColumnArrayToVector(columnNames));
	}

	public RsvTableModel(Vector<RsvColumn> progressList,
			Vector<String> columnNames) {
		setProgressTableModel(progressList, columnNames);
	}

	/*
	 * Vector<Progress>타입의 테이블 데이터와 Vector<String>타입의 컬럼명을 매개 변수로 받아 인스턴스 변수에
	 * 저장하는 메소드
	 */
	private void setProgressTableModel(Vector<RsvColumn> progressList,
			Vector<String> columNames) {
		this.progressList = progressList;
		this.columnNames = columNames;
		fireTableStructureChanged();
	}

	private Vector<String> getColumnArrayToVector(String[] columnNames) {
		Vector<String> tableHeaders = new Vector<String>();
		for (int i = 0; i < columnNames.length; i++) {
			tableHeaders.add(TABLE_COLUMNS[i]);
		}
		return tableHeaders;
	}

	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	@Override
	public int getRowCount() {
		return progressList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	
	RsvColumn progress = progressList.get(rowIndex);	
		switch(columnIndex){
		case 0:
			return false ;		
		case 1:
			return progress.getRSVSEAT();
		case 2:
			return progress.getRSVSTATEID();
		case 3:
			return progress.getPAYMENTID();		
		case 4:
			return progress.getRSVTRIP();
		case 5:
			return progress.getRSVDATE();		
		case 6:
			return progress.getPAYMONEY();		
		case 7:
			return progress.getBUSSTTIME();		
		case 8:
			return progress.getDESNAME();
		case 9:
			return progress.getARRNAME();
		
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int columnIndex) {

		return columnNames.get(columnIndex);
	}
	
	/* 체크박스를 만들기 위해 소스수정 */
	 Class[] dataType = new Class[] { Boolean.class, String.class,
	 String.class, String.class, String.class, String.class,
	 String.class, String.class, String.class, String.class };
	
	 @Override
	 public Class<?> getColumnClass(int columnIndex) {
	 return dataType[columnIndex];
	
	 }


}
