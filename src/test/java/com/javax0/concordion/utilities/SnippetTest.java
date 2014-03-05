package com.javax0.concordion.utilities;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.concordion.api.FullOGNL;
import org.concordion.api.extension.Extensions;
import org.concordion.ext.EmbedExtension;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
//SNIPPET START: classAnnotations
@RunWith(ConcordionRunner.class)
@Extensions(EmbedExtension.class)
@FullOGNL
//SNIPPET END: classAnnotations
public class SnippetTest {
//SNIPPET START: snippetField	
	private final Snippet snippet = new Snippet(this.getClass());
//SNIPPET END: snippetField	

//SNIPPET START: method	
	public String snippet(String snippetName) throws FileNotFoundException, IOException {
		return snippet.snippet(snippetName);
	}
//SNIPPET END: method	
}
