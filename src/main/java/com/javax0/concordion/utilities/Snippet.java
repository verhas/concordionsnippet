package com.javax0.concordion.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Snippet {
	private final Class<?> fixtureClass;

	public Snippet(Class<?> fixtureClass) {
		this.fixtureClass = fixtureClass;
	}

	private enum LineType {
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
			final String snippetEndRegex = SNIPPET_END_REGEX_PREFIX
					+ snippetName + SNIPPET_LINE_POSTFIX;
			if (line.matches(snippetEndRegex)) {
				return END;
			}
			if (line.matches(SKIPLINE_REGEX)) {
				return SKIPLINE;
			}
			return CONTENT;
		}
	}

	public String snippet(String snippetName) throws FileNotFoundException,
			IOException {
		final File fixtureFile = new File("src/test/java/"
				+ fixtureClass.getName().replaceAll("\\.", "/") + ".java");
		final StringBuilder snippetBuilder = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(
				fixtureFile))) {
			String line;
			boolean includeLine = false;
			while ((line = reader.readLine()) != null) {
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
		}
		return snippetBuilder.toString();
	}
}
