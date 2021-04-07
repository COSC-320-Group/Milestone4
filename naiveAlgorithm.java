import java.util.*;
import java.io.*;
import java.text.ParseException;

public class naiveAlgorithm {
	
	static String [] [] listWords;
	
	static int changes=0;
	static long startTime;
	static long endTime;
	public static void main(String[] args) throws ParseException, IOException {
		String path = "/Users/ospic/Desktop/Slang.csv";
	    String line =  null;
	    long startTime;
		long endTime;
	    
	    listWords = mapping(path);
	    try {
		    BufferedReader reader = new BufferedReader(new FileReader("/Users/ospic/Desktop/filtered_data.csv"));
	        reader.readLine();
	        String row;
	        startTime = System.currentTimeMillis();
	        while ((row = reader.readLine()) != null) {                  // read until end of file
	        	System.out.println(row);
	        	System.out.println("=");
	        	System.out.println(Replace(row));
	        	System.out.println("______________________________________________________________________________________________________________________________________________________________");
	        	
	        }
	        endTime = System.currentTimeMillis();
	        long Elapsed = endTime - startTime;
	        System.out.println("Time Elapsed in ms: "+Elapsed);
	        System.out.println("Number of changes: "+changes);
	    }catch (IOException e) {
			// TODO Auto-generated catch block
	    	System.out.println("Cannot find input file!");
		}
	    
	
	    
	    
    } 
    public static String Replace(String tweet) {
    	String[] tweetSubstring = tweet.split(" ");  // tweet split on spaces

        for(int i = 0; i < tweetSubstring.length; i++) {
        	for(int j = 0; j < listWords.length; j++) {
        		if(remove(tweetSubstring[i].toLowerCase()).equals(listWords[j][0])) {
        			tweetSubstring[i] = listWords[j][1];
        			changes++;
        		}
        	}
        }
        String newTweet = String.join(" ", tweetSubstring);
    	return newTweet;
    }
    
//Putting the abbreviations in a key value hashmap
	public static String[][] mapping(String csv) {
		
		String [][] listWords = new String[442][2];
		int index = 0;
		
		try {
            BufferedReader reader = new BufferedReader(new FileReader(csv));
            reader.readLine();                                          
            String row;

            while ((row = reader.readLine()) != null) {                 
                String[] split = row.split(",");                     
                listWords[index][0] = split[0].toLowerCase();
                listWords[index][1] = split[1].toLowerCase();
                index++;
            }
		
		} catch(FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("Cannot find file");
        }
		return listWords;
	}
	
	public static String remove(String word) {
        if (word.length() < 2) //Need to add this so the word in within range
            return word;

        char Char = word.charAt(word.length() - 1);
        if (Char == '!')
            return word.substring(0, word.length() - 1);
        if(Char == ',')
        	return word.substring(0, word.length() - 1);
        if(Char == '.')
        	return word.substring(0, word.length() - 1);
        if(Char == '"')
        	return word.substring(0, word.length() - 1);
        
        
        return word;
    }
	

}