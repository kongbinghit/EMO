import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tj {

	public static void main(String[] args) {

		String pathinput = "C:/Users/lin/Desktop/myemotion/trainData4000-weight.xml";
		String pathinput2 = "C:/Users/lin/Desktop/myemotion/testData10000-weight.xml";
		String pathoutput = "C:/Users/lin/Desktop/tj.csv";

		File file = new File(pathinput);
		File file2 = new File(pathinput2);
		BufferedReader reader = null;
		BufferedReader reader2 = null;

		try {
			Map<String, Integer> weibomap = new HashMap<String, Integer>();
			weibomap.put("happiness", 0);
			weibomap.put("like", 1);
			weibomap.put("anger", 2);
			weibomap.put("sadness", 3);
			weibomap.put("fear", 4);
			weibomap.put("disgust", 5);
			weibomap.put("surprise", 6);
			weibomap.put("none", 7);
			Map<String, Integer> sentencemap = new HashMap<String, Integer>();
			sentencemap.put("happiness", 0);
			sentencemap.put("like", 1);
			sentencemap.put("anger", 2);
			sentencemap.put("sadness", 3);
			sentencemap.put("fear", 4);
			sentencemap.put("disgust", 5);
			sentencemap.put("surprise", 6);
			sentencemap.put("none", 7);

			int[][] weiboi = new int[8][8];
			int[][] sentencei = new int[8][8];
			Pattern patweibo = Pattern
					.compile("(<weibo id=\")([0-9]*)(\" emotion-type1=\")(.*)(\" emotion-type2=\")(.*)(\">)");

			Pattern patn = Pattern
					.compile("(<sentence id=\"[0-9]*\" opinionated=\"N\">)(.*)(</sentence>)");
			Pattern paty = Pattern
					.compile("(<sentence id=\"[0-9]*\" opinionated=\"Y\" emotion-1-type=\")(.*)(\" emotion-1-weight=\"[1-3]\" emotion-2-type=\")(none|happiness|like|sadness|anger|fear|disgust|surprise)(\".*>)(.*)(</sentence>)");

			reader = new BufferedReader(new FileReader(file));
			reader2 = new BufferedReader(new FileReader(file2));
			String tempString = null;

			OutputStreamWriter writer = new OutputStreamWriter(
					new FileOutputStream(pathoutput), "UTF-8");
			writer.write(",happiness,like,anger,sadness,fear,disgust,surprise,none\r\n");
			int num = 0, num2 = 0;

			String emotiontype1 = "none";
			String emotiontype2 = "none";
			while ((tempString = reader.readLine()) != null) {

				// find weiboid
				Matcher matcherweibo = patweibo.matcher(tempString);
				if (matcherweibo.find()) {

					emotiontype1 = matcherweibo.group(4);
					emotiontype2 = matcherweibo.group(6);

					weiboi[weibomap.get(emotiontype1)][weibomap
							.get(emotiontype2)]++;
					// if(weibomap.containsKey(emotiontype1+"-"+emotiontype2)){
					// weibomap.put(emotiontype1+"-"+emotiontype2,
					// weibomap.get(emotiontype1+"-"+emotiontype2)+1);
					// }else{
					// weibomap.put(emotiontype1+"-"+emotiontype2, 1);
					// }
					num++;
				}
				// find sentence that opinionated="N"
				Matcher matchern = patn.matcher(tempString);
				if (matchern.find()) {
					emotiontype1 = "none";
					emotiontype2 = "none";
					sentencei[sentencemap.get(emotiontype1)][sentencemap
							.get(emotiontype2)]++;
					// if(sentencemap.containsKey(emotiontype1+"-"+emotiontype2)){
					// sentencemap.put(emotiontype1+"-"+emotiontype2,
					// sentencemap.get(emotiontype1+"-"+emotiontype2)+1);
					// }else{
					// sentencemap.put(emotiontype1+"-"+emotiontype2, 1);
					// }
					num2++;
				}

				// find sentence that opinionated="Y"
				Matcher matchery = paty.matcher(tempString);
				if (matchery.find()) {
					emotiontype1 = matchery.group(2);
					emotiontype2 = matchery.group(4);
					sentencei[sentencemap.get(emotiontype1)][sentencemap
							.get(emotiontype2)]++;
					// if(sentencemap.containsKey(emotiontype1+"-"+emotiontype2)){
					// sentencemap.put(emotiontype1+"-"+emotiontype2,
					// sentencemap.get(emotiontype1+"-"+emotiontype2)+1);
					// }else{
					// sentencemap.put(emotiontype1+"-"+emotiontype2, 1);
					// }
					num2++;
				}

			}

			while ((tempString = reader2.readLine()) != null) {

				// find weiboid
				Matcher matcherweibo = patweibo.matcher(tempString);
				if (matcherweibo.find()) {

					emotiontype1 = matcherweibo.group(4);
					emotiontype2 = matcherweibo.group(6);

					weiboi[weibomap.get(emotiontype1)][weibomap
							.get(emotiontype2)]++;
					// if(weibomap.containsKey(emotiontype1+"-"+emotiontype2)){
					// weibomap.put(emotiontype1+"-"+emotiontype2,
					// weibomap.get(emotiontype1+"-"+emotiontype2)+1);
					// }else{
					// weibomap.put(emotiontype1+"-"+emotiontype2, 1);
					// }
					num++;
				}
				// find sentence that opinionated="N"
				Matcher matchern = patn.matcher(tempString);
				if (matchern.find()) {
					emotiontype1 = "none";
					emotiontype2 = "none";
					sentencei[sentencemap.get(emotiontype1)][sentencemap
							.get(emotiontype2)]++;
					// if(sentencemap.containsKey(emotiontype1+"-"+emotiontype2)){
					// sentencemap.put(emotiontype1+"-"+emotiontype2,
					// sentencemap.get(emotiontype1+"-"+emotiontype2)+1);
					// }else{
					// sentencemap.put(emotiontype1+"-"+emotiontype2, 1);
					// }
					num2++;
				}

				// find sentence that opinionated="Y"
				Matcher matchery = paty.matcher(tempString);
				if (matchery.find()) {
					emotiontype1 = matchery.group(2);
					emotiontype2 = matchery.group(4);
					sentencei[sentencemap.get(emotiontype1)][sentencemap
							.get(emotiontype2)]++;
					// if(sentencemap.containsKey(emotiontype1+"-"+emotiontype2)){
					// sentencemap.put(emotiontype1+"-"+emotiontype2,
					// sentencemap.get(emotiontype1+"-"+emotiontype2)+1);
					// }else{
					// sentencemap.put(emotiontype1+"-"+emotiontype2, 1);
					// }
					num2++;
				}

			}

			for (int i = 0; i < 8; i++) {

				for (int j = 0; j < 8; j++) {
					System.out.print(weiboi[i][j] + ",");
					writer.write("," + weiboi[i][j]);
				}
				System.out.println();
				writer.write("\r\n");
			}
			writer.write(",happiness,like,anger,sadness,fear,disgust,surprise,none\r\n");
			for (int i = 0; i < 8; i++) {

				for (int j = 0; j < 8; j++) {
					System.out.print(weiboi[i][j] + ",");
					writer.write("," + sentencei[i][j]);
				}
				System.out.println();
				writer.write("\r\n");
			}
			System.out.println(num);
			System.out.println(num2);
			reader.close();
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

	}

}
