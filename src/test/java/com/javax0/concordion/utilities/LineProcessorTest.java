package com.javax0.concordion.utilities;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class LineProcessorTest {

	@Test
	public void collectsLinesBetweenStartAndEndLine() {
		LineProcessor processor = new LineProcessor("name");
		processor.process("//SNIPPET START: name");
		processor.process("line to be included");
		assertThat(processor.getSnippet(), is(equalTo("line to be included\n")));
	}

	@Test
	public void skipsLinesAtTheStart() {
		LineProcessor processor = new LineProcessor("name");
		processor.process("line that is skipped");
		processor.process("//SNIPPET START: name");
		processor.process("line to be included");
		assertThat(processor.getSnippet(), is(equalTo("line to be included\n")));
	}

	@Test
	public void skipsLinesAfterEndTag() {
		LineProcessor processor = new LineProcessor("name");
		processor.process("//SNIPPET START: name");
		processor.process("line to be included");
		processor.process("//SNIPPET END: name");
		processor.process("line that is skipped");
		assertThat(processor.getSnippet(), is(equalTo("line to be included\n")));
	}

	@Test
	public void skipsLinesOfOtherSnippets() {
		LineProcessor processor = new LineProcessor("name");
		processor.process("//SNIPPET START: name");
		processor.process("//SNIPPET START: otherName");
		processor.process("line to be included");
		assertThat(processor.getSnippet(), is(equalTo("line to be included\n")));
	}
	
	@Test
	public void buildMultiSegmentSnippets() {
		LineProcessor processor = new LineProcessor("name");
		processor.process("//SNIPPET START: name");
		processor.process("line to be included");
		processor.process("//SNIPPET END: name");

		processor.process("this line will be skipped");
		
		processor.process("//SNIPPET START: name");
		processor.process("line to be included");
		processor.process("//SNIPPET END: name");
		assertThat(processor.getSnippet(), is(equalTo("line to be included\nline to be included\n")));
	}
}
