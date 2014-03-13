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

	public String snippet(String snippetName) throws FileNotFoundException,
			IOException {
		final File fixtureFile = new File("src/test/java/"
				+ fixtureClass.getName().replaceAll("\\.", "/") + ".java");

		try (BufferedReader reader = new BufferedReader(new FileReader(
				fixtureFile))) {
			String line;
			LineProcessor processor = new LineProcessor(snippetName);
			while ((line = reader.readLine()) != null) {
				processor.process(line);
			}
			return processor.getSnippet();
		}
	}

}
