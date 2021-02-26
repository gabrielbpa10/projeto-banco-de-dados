package controller;

public class Page {
	
	String[] content;
	Page next;
	
	public Page (int size) {
		content = new String[size];
	}

	public boolean search (String word, boolean fullSearch) {
		for (String item : content) if (item.equals(word)) return true;
		return next == null ? false : next.search(word, fullSearch);
	}

	public Page add (String word) throws Exception {
		if (word == null) throw new Exception("Null strings not allowed.");
		for (int i = 0; i < content.length; i++) {
			if (content[i] == null) {
				content[i] = word;
				return null;
			}
		}
		next = new Page(content.length);
		next.add(word);
		return next;
	}

	public Page getLastPage () {
		Page pointer = this;
		while (pointer.next != null) pointer = pointer.next;
		return pointer;
	}
}
