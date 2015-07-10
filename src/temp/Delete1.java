package temp;

import java.util.ArrayList;
import java.util.List;


public class Delete1 {
	public static void main(String[] args) {
		long startTIme = System.currentTimeMillis();
		long[] arrs = { 20, 40, 40, 21, 1168, 1170, 1169, 1171, 42, 29, 31, 22,	23, 40, 26, 28, 27, 30, 32, 40, 40 };
//		long[] arrs = new long[1000];

		long[] arrange = getNewArrays1(arrs, 40, 2);
		
		for (int i = 0; i < arrange.length; i++) {
			if(i<arrange.length-1)
				System.out.print(arrange[i]+",");
			else
				System.out.print(arrange[i]);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("\nusertime:"+(endTime-startTIme));
	}

	public static long[] getNewArrays1(long[] arrs, int inputData, int replaceIndex) {
		// 替换下标为2 数字
		arrs[replaceIndex] = inputData;
		List<Long> lists = new ArrayList<Long>();
		for (int i = 0; i < arrs.length; i++) {
			if(i < replaceIndex){
				if(arrs[i]==inputData)
					continue;
				else
					lists.add(arrs[i]);
			}
			if(i==replaceIndex){
				lists.add(arrs[i]);
			}
			if(i > replaceIndex){
				if(arrs[i]==inputData)
					continue;
				else
					lists.add(arrs[i]);
			}
		}
		long[] newArrs = null;
		if(lists.size()>0){
			newArrs = new long[lists.size()];
			for (int i = 0; i < lists.size(); i++) {
				newArrs[i] = lists.get(i);
			}
		}
		return newArrs;
	}
	
	
	public static List<Integer> findRemoveIndex(long[] arrs, int inputData,	int replaceIndex) {
		List<Integer> indexs = new ArrayList<Integer>();
		replaceIndex++;
		for (int i = replaceIndex; i < arrs.length; i++) {
			if (arrs[i] == inputData) {
				indexs.add(i);
			}
		}
		return indexs;
	}

	private static String change2SqlWord(String str) {
		if (str.trim().contains(",")) {
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
	/*
	 * public static void main(String[] args) { ArrayList<Temp> lists = new
	 * ArrayList<Temp>(); for (int i = 0; i < 3; i++) { Temp t = new Temp();
	 * t.setName("name"+i); lists.add(t); }
	 * 
	 * StringBuilder sb = new StringBuilder(); for (int i = 0; i < lists.size();
	 * i++) { if(i<lists.size()-1)
	 * sb.append(lists.get(i).getName()).append(","); else
	 * sb.append(lists.get(i).getName()); }
	 * 
	 * String myStr = change2SqlWord(sb.toString()); System.out.println(myStr);
	 * 
	 * }
	 */

}
