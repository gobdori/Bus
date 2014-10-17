package TEST;

public class MakeInsert {

	static String[] tmnid1= {"DG",	"KJ","CW","DJ","MS","JJ","YS","MP",	"SL","BS"};
	static String[] tmnid2 = {"DG",	"KJ","CW","DJ","MS","JJ","YS","MP",	"SL","BS"};
	static String[] tmnname = {"대구", "경주",	"창원","마산","대전", "전주","여수","목포","서울","부산"};
	
	public static void main(String[] args) 
	{	
		
	}
	
	public MakeInsert(String sttime, String ss)
	{
		for(int i = 0; i<tmnid1.length;i++)
		{
			for(int j = 0; j<tmnid2.length;j++)
			{
				if(tmnid1 !=tmnid2)
				{
					System.out.println("insert into ");
				}
			}
		}
	}

}
