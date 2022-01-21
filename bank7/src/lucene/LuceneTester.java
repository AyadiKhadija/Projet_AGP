package lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

public class LuceneTester {
		
	   String indexDir = "/Users/fatiatravaille/git/Projet_AGP/bank7/Index";// inserer le chemin de l'index
	   String dataDir;
	   Indexer indexer;
	   Searcher searcher;
       HashMap<String, Float> map = new HashMap<>();
       
       public LuceneTester(String dataDir) {
    	   this.dataDir=dataDir;
       }

	   
	   public void createIndex() throws IOException {
	      indexer = new Indexer(indexDir);
	      int numIndexed;
	      long startTime = System.currentTimeMillis();	
	      numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
	      long endTime = System.currentTimeMillis();
	      indexer.close();
	      System.out.println(numIndexed+" File indexed, time taken: "
	         +(endTime-startTime)+" ms");		
	   }
	   
	   public HashMap<String, Float> search(String searchQuery) throws IOException, ParseException {
	      System.out.println("search : " + searchQuery);
	      System.out.println("search : " + searchQuery);
		   searcher = new Searcher(indexDir);
	      long startTime = System.currentTimeMillis();
	      TopDocs hits = searcher.search(searchQuery);
	      long endTime = System.currentTimeMillis();
	      
	      
	      
	      System.out.println(hits.totalHits +
	         " documents found. Time :" + (endTime - startTime));
	      for(ScoreDoc scoreDoc : hits.scoreDocs) {
	    	 
	    	  
	    	 
	         Document doc = searcher.getDocument(scoreDoc);
	         System.out.println(doc.get(LuceneConstants.FILE_NAME));
	         map.put((doc.get(LuceneConstants.FILE_NAME).split("[.]"))[0], scoreDoc.score);
	            System.out.println("File: "
	            + doc.get(LuceneConstants.FILE_PATH));
	            deleteDirectory(indexDir);
	            
	      }
	      
	      return map;
	      
	   }
	   
	   public String getIndexDir() {
			return indexDir;
		}
	   
	   public void setIndexDir(String indexDir) {
			this.indexDir = indexDir;
		}
	   
	   public void setResult(HashMap<String, Float> map) {
			this.map = map;
		}
	   
	   public HashMap<String, Float> getResult() {
		   return map;
	   }

	   public void mySearch(String Word){
		   LuceneTester tester;
		   try {
				tester = new LuceneTester("/Users/fatiatravaille/Downloads/sites");
				tester.createIndex();

					System.out.println("mySearch : " + Word);
					this.setResult(tester.search(Word));
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}

	   }
	   
	   public static void deleteDirectory(String emplacement) {

			File path = new File(emplacement);
			if (path.exists()) {
				File[] files = path.listFiles();
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory()) {
						deleteDirectory(path + "\\" + files[i]);
					} else {
						files[i].delete();
					}
				}
			}
		}

	

}
