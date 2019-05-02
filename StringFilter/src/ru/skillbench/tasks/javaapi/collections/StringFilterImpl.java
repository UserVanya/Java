package ru.skillbench.tasks.javaapi.collections;

import java.util.*;

public class StringFilterImpl implements StringFilter {
	@FunctionalInterface
	public interface Filter {
		 public boolean compare(String word, String toCompare);
	}
	public Set<String> words = new HashSet<String>();
	public StringFilterImpl(){}
	
	@Override
	public void add(String s) {
		if (s != null) {
			String tmp = s.toLowerCase();
			words.add(tmp);
		}
		else {
			words.add(s);
		}
	}
	@Override
	public boolean remove(String s) {
		return words.remove(s);
	}
	public void removeAll() {
		words.removeAll(words);
	}
	public Collection<String> getCollection(){
		return words;
	}
	private Set<String> matches(String toCompare, Filter f){
		Set<String> tmp = new HashSet<String>();
		for (String word : words) {
			if (f.compare(word, toCompare)) {
				tmp.add(word);
			}
		}
		return tmp;
	}
	@Override
	public Iterator<String> getStringsContaining(String chars){
		if (chars == null || chars == "") {
			words.remove(null);
			return words.iterator();
		}
		return matches(chars, (o1, o2) -> {return (o1 != null) ? o1.contains(o2) : false;}).iterator();
	}
	
	@Override
	public Iterator<String> getStringsStartingWith(String begin){
		if (begin == null) {
			words.remove(null);
			return words.iterator();
		}
		begin = begin.toLowerCase();
		return matches(begin, (o1, o2) -> {return (o1 != null) ? o1.startsWith(o2) : false;}).iterator();
	}
	
	@Override
	public Iterator<String> getStringsByNumberFormat(String format){
		return matches(format, (word, toCompare) -> {
			if (word.length() == toCompare.length()) {
				for (int i = 0; i < word.length(); i++) {
					if (toCompare.charAt(i) == '#' && !Character.isDigit(word.charAt(i))) {
						return false;
					} 
					if(toCompare.charAt(i) != word.charAt(i) && toCompare.charAt(i) != '#') {
						return false;
					}
				}
				return true;
			}
			return false;
		}).iterator();
	}
	@Override
	public Iterator<String> getStringsByPattern(String pattern){
		return matches(pattern, (o1, o2) -> {
			if (o1 != null) {
				String []tmp = o2.split("\\*");
				Integer lastIndex, currIndex = 0;
				for (int i = 0; i < tmp.length; i++) {
					lastIndex = currIndex;
					currIndex = o1.indexOf(tmp[i]);
					if (i == 0 && o2.charAt(0) != '*' && o1.indexOf(tmp[0]) != 0 ) {
						return false;
					}
					if (i == tmp.length - 1 && (o1.length() - o1.indexOf(tmp[i])) != tmp[i].length() && o2.lastIndexOf("*") != (o2.length() - 1) ) {
						return false;
					}
					if (currIndex < lastIndex || currIndex < 0) {
						return false;
					}
				}
				return true;
			}
			else {
				if (o2 == null) {
					return true;
				}
				return false;
			}
		}).iterator();
	}
}
