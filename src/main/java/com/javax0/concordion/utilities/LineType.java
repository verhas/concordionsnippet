package com.javax0.concordion.utilities;

enum LineType {
	START, END, CONTENT, SKIPLINE;
	private static final String SNIPPET_REGEX_PREFIX = ".*SNIPPET\\s+";
	private static final String SNIPPET_REGEX_POSTFIX = "\\s*:\\s*";
	private static final String SNIPPET_LINE_POSTFIX = "\\s*";

	private static final String SNIPPET_START_REGEX_PREFIX = SNIPPET_REGEX_PREFIX
			+ "START" + SNIPPET_REGEX_POSTFIX;
	private static final String SNIPPET_END_REGEX_PREFIX = SNIPPET_REGEX_PREFIX
			+ "END" + SNIPPET_REGEX_POSTFIX;

	private static final String SKIPLINE_REGEX = SNIPPET_REGEX_PREFIX
			+ "(START|END)" + SNIPPET_REGEX_POSTFIX + "\\w+"
			+ SNIPPET_LINE_POSTFIX;

	static LineType of(String line, String snippetName) {
		final String snippetStartRegex = SNIPPET_START_REGEX_PREFIX
				+ snippetName + SNIPPET_LINE_POSTFIX;
		if (line.matches(snippetStartRegex)) {
			return START;
		}
		final String snippetEndRegex = SNIPPET_END_REGEX_PREFIX + snippetName
				+ SNIPPET_LINE_POSTFIX;
		if (line.matches(snippetEndRegex)) {
			return END;
		}
		if (line.matches(SKIPLINE_REGEX)) {
			return SKIPLINE;
		}
		return CONTENT;
	}
}
