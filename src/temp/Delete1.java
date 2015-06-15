package temp;

import java.util.ArrayList;

import com.itextpdf.text.List;

public class Delete1 {
	private static String change2SqlWord(String str)
	{
		if (str.trim().contains(","))
		{
			str = str.replace(",", "','");
		}
		StringBuilder stringBuilder = new StringBuilder(str);
		stringBuilder.insert(0, "'");
		stringBuilder.append("'");
		str = stringBuilder.toString();
		return str;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Temp> lists = new ArrayList<Temp>();
		for (int i = 0; i < 3; i++) {
			Temp t = new Temp();
			t.setName("name"+i);
			lists.add(t);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lists.size(); i++) {
			if(i<lists.size()-1)
			sb.append(lists.get(i).getName()).append(",");
			else
				sb.append(lists.get(i).getName());
		}
		
		String myStr = change2SqlWord(sb.toString());
System.out.println(myStr);

	}

}
