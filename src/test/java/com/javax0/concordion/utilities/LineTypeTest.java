package com.javax0.concordion.utilities;

import static com.javax0.concordion.utilities.LineType.of;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class LineTypeTest {

	@Test
	public void recognizesStartLinesWithoutPrefix() {
		assertThat(of("SNIPPET START: name", "name"),
				is(equalTo(LineType.START)));
	}

	@Test
	public void recognizesStartLinesWithCppCommentPrefix() {
		assertThat(of("//SNIPPET START: name", "name"),
				is(equalTo(LineType.START)));
		assertThat(of(" //SNIPPET START: name", "name"),
				is(equalTo(LineType.START)));
		assertThat(of(" // SNIPPET START: name", "name"),
				is(equalTo(LineType.START)));
	}

	@Test
	public void recognizesStartLinesWithCCommentPrefix() {
		assertThat(of("/*SNIPPET START: name", "name"),
				is(equalTo(LineType.START)));
		assertThat(of(" /*SNIPPET START: name", "name"),
				is(equalTo(LineType.START)));
		assertThat(of(" /* SNIPPET START: name", "name"),
				is(equalTo(LineType.START)));
	}

	@Test
	public void recognizesEndLinesWithoutPrefix() {
		assertThat(of("SNIPPET END: name", "name"),
				is(equalTo(LineType.END)));
	}

	@Test
	public void recognizesEndLinesWithCppCommentPrefix() {
		assertThat(of("//SNIPPET END: name", "name"),
				is(equalTo(LineType.END)));
		assertThat(of(" //SNIPPET END: name", "name"),
				is(equalTo(LineType.END)));
		assertThat(of(" // SNIPPET END: name", "name"),
				is(equalTo(LineType.END)));
	}

	@Test
	public void recognizesEndLinesWithCCommentPrefix() {
		assertThat(of("/*SNIPPET END: name", "name"),
				is(equalTo(LineType.END)));
		assertThat(of(" /*SNIPPET END: name", "name"),
				is(equalTo(LineType.END)));
		assertThat(of(" /* SNIPPET END: name", "name"),
				is(equalTo(LineType.END)));
	}

	@Test
	public void recognizesContentLines() {
		assertThat(of("just a simple content line", "name"),
				is(equalTo(LineType.CONTENT)));

	}

	@Test
	public void recognizesSkipLinesWithoutPrefix() {
		assertThat(of("SNIPPET END: name", "noname"),
				is(equalTo(LineType.SKIPLINE)));
	}

	@Test
	public void recognizesSkipLinesWithCppCommentPrefix() {
		assertThat(of("//SNIPPET END: name", "noname"),
				is(equalTo(LineType.SKIPLINE)));
		assertThat(of(" //SNIPPET END: name", "noname"),
				is(equalTo(LineType.SKIPLINE)));
		assertThat(of(" // SNIPPET END: name", "noname"),
				is(equalTo(LineType.SKIPLINE)));
	}

	@Test
	public void recognizesSkipLinesWithCCommentPrefix() {
		assertThat(of("/*SNIPPET END: name", "noname"),
				is(equalTo(LineType.SKIPLINE)));
		assertThat(of(" /*SNIPPET END: name", "noname"),
				is(equalTo(LineType.SKIPLINE)));
		assertThat(of(" /* SNIPPET END: name", "noname"),
				is(equalTo(LineType.SKIPLINE)));
	}
}
