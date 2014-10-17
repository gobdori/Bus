package bus.db;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class BusRsvModel extends AbstractTableModel 
{
	private final static String[] TABLE_COLUMNS = { 
		/*"버스시간표ID", "버스등급", "출발지","도착지","출발시간", "가격", "정류소", "소요시간" */
		"출발시간"};
	
	private Vector<BusColumn> buslist;
	
	private Vector<String> columnNames;
	
	public BusRsvModel() 
	{
		
		/* 기본 생성자는 외부에서 데이터를 넘겨받지 못하므로 친구 정보를 저장할 수
		 * 있는 Vector<Friend> 타입의 객체를 직접 생성하고 컬럼명은 상수로 정의된
		 * 배열을 지정하여 인스턴스 변수에 저장하는 다른 생성자를 호출하고 있다.
		 **/
		this(new Vector<BusColumn>(), TABLE_COLUMNS);		
	}
	
	public BusRsvModel(Vector<BusColumn> buslist) 
	{
		
		/* 외부에서 테이블의 데이터를 넘겨받고 컬럼명은 상수로 정의된 배열을 
		 * 지정하여 인스턴스 변수에 저장하는 다른 생성자를 호출하고 있다.
		 **/
		this(buslist, TABLE_COLUMNS);
	}
	
	public BusRsvModel(Vector<BusColumn> buslist, Vector<String> columnNames) 
	{		
		setBusRsvModel(buslist, columnNames);
	}
	
	public BusRsvModel(Vector<BusColumn> buslist, String[] columnNames) {
		
		setBusRsvModel(
				buslist, getColumnsArrayToVecotr(columnNames));
	}
	
	public void setBusRsvModel(
			Vector<BusColumn> buslist, Vector<String> columnNames) {
		
		this.buslist = buslist;
		this.columnNames = columnNames;	
		
		/* Table 구조가 변경되었다고 알리기 위해 TableModelEvent를 발생 시킨다.
		 * 이 이벤트가 발생하면 TableModel로 부터 데이터를 갱신하여 테이블에 표시한다.
		 **/
		fireTableStructureChanged();
	}	
	
	private Vector<String> getColumnsArrayToVecotr(String[] columnNames) {
		
		Vector<String> tableHeaders = new Vector<String>();
		
		for(int i = 0; i < columnNames.length; i++) {
			tableHeaders.add(TABLE_COLUMNS[i]);
		}
		return tableHeaders;
	}
	
	public BusColumn getRowData(int rowIndex) {
		return buslist.get(rowIndex);
	}
	
	public void addRow(BusColumn buscol) {
		buslist.add(buscol);
		
		/* 테이블에 행이 삽입되었다고 알리기 위해 TableModelEvent를 발생 시킨다.
		 * 이 이벤트가 발생하면 TableModel로 부터 데이터를 갱신하여 테이블에 표시한다.
		 **/
		fireTableRowsInserted(getRowCount(), getRowCount());
	}
	
	public void removeRow(int rowIndex) {
		buslist.remove(rowIndex);
		
		/* 테이블에서 행이 삭제되어다고 알리기 위해 TableModelEvent를 발생 시킨다.
		 * 이 이벤트가 발생하면 TableModel로 부터 데이터를 갱신하여 테이블에 표시한다.
		 **/
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public void modifyRow(BusColumn buscol, int rowIndex) {
		buslist.set(rowIndex, buscol);
		
		/* 테이블에서 행이 수정되었다고 알리기 위해 TableModelEvent를 발생 시킨다.
		 * 이 이벤트가 발생하면 TableModel로 부터 데이터를 갱신하여 테이블에 표시한다.
		 **/
		fireTableRowsUpdated(rowIndex, rowIndex);
	}
	
	@Override
	public int getRowCount() {		
		return buslist.size();
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.size();
	}
	
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		/* 테이블에 출력되는 한 행의 데이터는 한 명의 친구정보를 출력하므로
		 * 친구정보 리스트를 저장하고 있는 Vector 객체에서 rowIndex에
		 * 해당하는 한 명의 친구 정보를 가져온다.
		 **/
		BusColumn buscol = buslist.get(rowIndex);
		
		/* 테이블 한 행에 출력되는 친구정보 중 columnIndex에 위치한 셀에 출력될
		 * 데이터가 리턴 되도록 구현한다. 즉 한 명의 친구 정보가 첫 번째 셀에는
		 * 이름, 두 번째 셀에는 유선전화 등으로 리턴 되도록 구현해야 한다.
		 **/		
		switch(columnIndex) {					
		case 0 :
			return buscol.getBUSSTTIME();		
		default :
			return null;			
		}
	}
	@Override
	public String getColumnName(int columnIndex) {
		// 매개변수로 넘어오는 index에 해당하는 컬럼명을 리턴하게 구현한다.
		return columnNames.get(columnIndex);
	}

}
