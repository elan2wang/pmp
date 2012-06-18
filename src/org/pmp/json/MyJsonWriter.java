/**
 * Author            : Elan
 * Created On        : 2012-6-16 上午11:03:57
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.json;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;



/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class MyJsonWriter {

    //~ Static Fields ==================================================================================================
    private static final String[] REPLACEMENT_CHARS;
    private static final String[] HTML_SAFE_REPLACEMENT_CHARS;
    static {
      REPLACEMENT_CHARS = new String[128];
      for (int i = 0; i <= 0x1f; i++) {
        REPLACEMENT_CHARS[i] = String.format("\\u%04x", (int) i);
      }
      REPLACEMENT_CHARS['"'] = "\\\"";
      REPLACEMENT_CHARS['\\'] = "\\\\";
      REPLACEMENT_CHARS['\t'] = "\\t";
      REPLACEMENT_CHARS['\b'] = "\\b";
      REPLACEMENT_CHARS['\n'] = "\\n";
      REPLACEMENT_CHARS['\r'] = "\\r";
      REPLACEMENT_CHARS['\f'] = "\\f";
      HTML_SAFE_REPLACEMENT_CHARS = REPLACEMENT_CHARS.clone();
      HTML_SAFE_REPLACEMENT_CHARS['<'] = "\\u003c";
      HTML_SAFE_REPLACEMENT_CHARS['>'] = "\\u003e";
      HTML_SAFE_REPLACEMENT_CHARS['&'] = "\\u0026";
      HTML_SAFE_REPLACEMENT_CHARS['='] = "\\u003d";
      HTML_SAFE_REPLACEMENT_CHARS['\''] = "\\u0027";
    }
    
    //~ Instance Fields ================================================================================================
    private final List<JsonScope> stack = new ArrayList<JsonScope>();
    {
	stack.add(JsonScope.EMPTY_DOCUMENT);
    }
    
    private final Writer out;
    
    private String separator = ":";
    
    private boolean htmlSafe;
    
    private String deferredName;
    
    private String indent;
    
    private boolean lenient;
    
    //~ Constructor ====================================================================================================
    
    public MyJsonWriter(Writer out) {
      if (out == null) {
        throw new NullPointerException("out == null");
      }
      this.out = out;
    }
    
    //~ Methods ========================================================================================================
    public MyJsonWriter nullValue() throws IOException {
	return this;
    }
    
    public void beginObject(){
	
    }
    
    public void endObject(){
	
    }
    
    private JsonScope peek(){
	int size = stack.size();
	if (size==0){
	    throw new IllegalStateException("MyJsonWriter is closed.");
	}
	return stack.get(size-1);
    }
    
    private void replaceTop(JsonScope topOfStack){
	stack.set(stack.size()-1, topOfStack);
    }
    
    private MyJsonWriter name(String name) {
	if (name==null) {
	    throw new NullPointerException("name == null");
	}
	if (deferredName != null) {
	    throw new IllegalStateException();
	}
	if (stack.isEmpty()) {
	    throw new IllegalStateException("MyJsonWriter is closed.");
	}
	deferredName = name;
	return this;
    }
    
    private void writeDeferredName() throws IOException{
	if (deferredName != null){
	    beforeName();
	    string(deferredName);
	    deferredName=null;
	}
    }
    
    private void newline() throws IOException {
	if (indent == null){
	    return;
	}
	out.write("\n");
	for (int i=1;i<stack.size();i++){
	    out.write(indent);
	}
    }
    
    private void beforeName() throws IOException{
	JsonScope context = peek();
	if (context == JsonScope.NONEMPTY_OBJECT){
	    out.write(',');
	} else if (context != JsonScope.EMPTY_OBJECT) {
	    throw new IllegalStateException("Nesting problem: " + stack);
	}
	newline();
	replaceTop(JsonScope.DANGLING_NAME);
    }
    
    private void beforeValue(boolean root) throws IOException {
	switch(peek()){
	case NONEMPTY_DOCUMENT:
	    if (!lenient){
		throw new IllegalStateException("JSON must have only one top-level value");
	    }
	
	case EMPTY_DOCUMENT:
	    if (!lenient && !root){
		throw new IllegalStateException("JSON must start with an array or an object");
	    }
	
	case EMPTY_ARRAY:
	    replaceTop(JsonScope.NONEMPTY_ARRAY);
	    newline();
	    break;
	 
	case NONEMPTY_ARRAY:
	    out.append(',');
	    newline();
	    
	case DANGLING_NAME:
	    out.append(separator);
	    replaceTop(JsonScope.NONEMPTY_OBJECT);
	    break;

	default:
	    throw new IllegalStateException("Nesting problem: " + stack);    
	}
    }
    
    private void string(String value) throws IOException {
	String[] replacements = htmlSafe ? HTML_SAFE_REPLACEMENT_CHARS : REPLACEMENT_CHARS;
	out.write("\"");
	int last =0;
	int length = value.length();
	for (int i=0; i<length; i++){
	    char c = value.charAt(i);
	    String replacement;
	    if (c<128){
		replacement = replacements[c];
		if (replacement == null) {
		    continue;
		}
	    } else if (c=='\u2028') {
		replacement = "\\u2028";
	    } else if (c=='\u2029') {
		replacement = "\\u2029";
	    } else {
		continue;
	    }
	    if (last<i) {
		out.write(value, last, i-last);
	    }
	    out.write(replacement);
	    last = i + 1;
	}
	if (last<length){
	    out.write(value, last, length - last);
	}
	out.write("\"");
    }
    
    //~ Getters and Setters ============================================================================================

}
