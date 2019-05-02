package ru.skillbench.tasks.text;

import java.io.PrintStream;
import java.util.*;

public class WordCounterImpl implements WordCounter {
	private Comparator<Map.Entry<String, Long>> comparator = (o1, o2) -> o2.getValue().compareTo(o1.getValue());
	private String text;
	private String del = ".,?!@#$%^:&*()[] {}\\/_\"";
	private Map<String, Long> wordsFreq = new HashMap<String, Long>();
	public WordCounterImpl() {
		text = new String();
	}
	@Override
	public void setText(String text) {
		this.text = text;
		if (text != null) {
			Scanner in = new Scanner(text);
			while(in.hasNext()) {
				String key = in.next().toLowerCase();
				for (int i  = 0; i < del.length(); i++) {
					key = key.replace(del.charAt(i), ' ');
				}
				key = key.trim();
				if(!key.contains("<")) {
					wordsFreq.putIfAbsent(key, Long.valueOf(0));
					wordsFreq.put(key, wordsFreq.get(key) + 1);
				}
			}	
		}
	}
	@Override
	public String getText() {
		return text;	
	}
	@Override
	public Map<String, Long> getWordCounts() throws IllegalStateException{
		if(text != null) {
			 return wordsFreq;	
		}
		else {
			throw new IllegalStateException();
		}
	}
	@Override
	public List<Map.Entry<String, Long>> getWordCountsSorted(){
		return sort(wordsFreq, comparator);
	}
	@Override
	public <K extends Comparable<K>, V extends Comparable<V>> List<Map.Entry<K,V>> sort(Map<K,V> map, Comparator<Map.Entry<K,V>> comparator) throws IllegalStateException{
		if(this.text != null) {
			List<Map.Entry<K, V>> listOfWordsFreq = new ArrayList<Map.Entry<K, V>>(map.entrySet());
			Collections.sort(listOfWordsFreq, comparator);
			return listOfWordsFreq;	
		}
		else {
			throw new IllegalStateException();
		}
	}
	@Override
	public <K,V> void print(List<Map.Entry<K, V>> entryList, PrintStream ps) {
		for (int i = 0; i < entryList.size(); i++) {
			ps.println(entryList.get(i).getKey().toString().toLowerCase() + " " + entryList.get(i).getValue().toString());
		}
	}
}
