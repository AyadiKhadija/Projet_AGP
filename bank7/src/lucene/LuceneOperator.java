package lucene;
import java.util.HashMap;

public class LuceneOperator {
	public static HashMap<String, Float> operator(String keywords) {
		 {
	
			LuceneTester lucene = new LuceneTester("/Users/fatiatravaille/Downloads/sites");
			lucene.mySearch(keywords);
			LuceneTester.deleteDirectory(lucene.getIndexDir());
	
			return lucene.getResult();
		}
	}
}
