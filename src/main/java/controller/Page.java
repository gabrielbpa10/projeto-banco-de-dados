package controller;

import java.util.ArrayList;
import java.util.Arrays;

import Utils.StringUtil;

public class Page {

	ArrayList<String> content = new ArrayList<String>();
	Page next;
	Integer maxSize;
	
	public Page (int size) {
		maxSize = size;
	}

	public Page () {}

	public Page search (String word, boolean fullSearch) {
		for (String item : content) 
			if (item.equals(word)) return this;
		if (fullSearch) return next == null ? null : next.search(word, fullSearch);
		return null;
	}

	public Page add (String word) throws Exception {
		if (word == null) throw new Exception("Null strings not allowed.");
		if (maxSize == null) {
			content.add(word);
			return this;
		} else {
			if (content.size() < maxSize) {
				content.add(word);
				return this;
			} else {
				next = new Page(content.size());
				return next.add(word);
			}
		}
	}

	public Page getLastPage () {
		Page pointer = this;
		while (pointer.next != null) pointer = pointer.next;
		return pointer;
	}

	public void clear () {
		content.clear();
		next = null;
	}

	@Override
	public String toString() {
		return String.format("(%s) -> %s", StringUtil.toString(content), next == null ? "null" : next.toString());
	}
}