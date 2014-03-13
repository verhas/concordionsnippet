package com.javax0.concordion.utilities;

class LineProcessor {
	private final String snippetName;

	protected LineProcessor(String snippetName) {
		this.snippetName = snippetName;
	}

	boolean includeLine = false;
	final StringBuilder snippetBuilder = new StringBuilder();

	void process(String line) {
		line = line.replaceAll("\n", "").replaceAll("\r", "");
		switch (LineType.of(line, snippetName)) {
		case START:
			includeLine = true;
			break;
		case END:
			includeLine = false;
			break;
		case SKIPLINE:
			break;
		case CONTENT:
			if (includeLine) {
				snippetBuilder.append(line).append("\n");
			}
			break;
		}
	}
	
	String getSnippet(){
		return snippetBuilder.toString();
	}
}
