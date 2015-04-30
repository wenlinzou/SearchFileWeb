package temp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Delete {
	public static void main(String[] args) throws IOException {
		File file = new File("f:del1.txt");
//		File file = new File("f:test1.txt");
		// replaceFileSpace(file);
//		splitWord(file);
		File transword = new File("f:read1.txt");
		List<String> transList = readTransWord(transword);
		File readFile = new File("f:del11.txt");
		List<String> transafter = appendWord(readFile, transList);
		for (String s : transafter) {
			/*while(s.contains(" ")){
				s = s.replace(" ", "");
			}*/
			s = clearSpace(s);
			System.out.println(s);
		}
		
		
	}
	
	//clear space 
	private static String clearSpace(String word){
		while(word.contains(" ")){
			word = word.replace(" ", "");
		}
		return word;
	}
	
	//拼接源文件 + 翻译后的中文txt
	private static List<String> appendWord(File file, List<String> transList) throws IOException {
		List<String> list = new ArrayList<String>();
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		int i = 0;
		while ((line = br.readLine()) != null) {
			StringBuilder sb = new StringBuilder();
			
			String temp = line;
			temp = temp.trim();
			int index = temp.indexOf("=");
			temp = temp.substring(0, index+1);
//			System.out.println(temp);
			sb.append(temp);
			String transTemp = transList.get(i);
			i++;
			sb.append(transTemp);
//			System.out.println(sb.toString());
			list.add(sb.toString());
			
		}
		br.close();
		fr.close();
		return list;
	}
	

	//读取翻译后的中文
	private static List<String> readTransWord(File file) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		List<String> list = new ArrayList<String>();
		
		while ((line = br.readLine()) != null) {
			String temp = line;
			list.add(temp);
		}
		br.close();
		fr.close();
		return list;
	}


	//获取需要翻译的=后面的英文
	private static void getSplitTransWord(File file ) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		while ((line = br.readLine()) != null) {
			String temp = line;
			temp = temp.trim();
			int index = temp.indexOf("=");
			temp = temp.substring(index+1);
			System.out.println(temp);
		}
		br.close();
		fr.close();
	}

	
	//暂未使用
	private static void replaceFileSpace(File file) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = "";

		Pattern pattern = Pattern.compile("[//s//p{Zs}]");

		while ((line = br.readLine()) != null) {
			String temp = line;
			temp = temp.trim();
			while (temp.contains(" ")) {
				temp = temp.replace(" ", "");
			}

			Matcher re = pattern.matcher(temp);
			temp = re.replaceAll("");
			// temp.replaceAll("\\s", "");
			System.out.println(temp);
		}
		br.close();
		fr.close();

	}

}
