package controller;

public class Page {
	
	String[] content;
	Page next;
	
	public Page (int size) {
		content = new String[size];
	}

	public Page search (String word, boolean fullSearch) {
		for (String item : content) if (item.equals(word)) return this;
		if (fullSearch) return next == null ? null : next.search(word, fullSearch);
		return null;
	}

	public Page add (String word) throws Exception {
		if (word == null) throw new Exception("Null strings not allowed.");
		for (int i = 0; i < content.length; i++) {
			if (content[i] == null) {
				content[i] = word;
				return this;
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