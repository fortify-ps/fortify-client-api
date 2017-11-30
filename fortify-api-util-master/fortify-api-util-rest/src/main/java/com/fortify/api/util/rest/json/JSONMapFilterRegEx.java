/*******************************************************************************
 * (c) Copyright 2017 EntIT Software LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a 
 * copy of this software and associated documentation files (the 
 * "Software"), to deal in the Software without restriction, including without 
 * limitation the rights to use, copy, modify, merge, publish, distribute, 
 * sublicense, and/or sell copies of the Software, and to permit persons to 
 * whom the Software is furnished to do so, subject to the following 
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be included 
 * in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY 
 * KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE 
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
 * IN THE SOFTWARE.
 ******************************************************************************/
package com.fortify.api.util.rest.json;

import java.util.regex.Pattern;

public class JSONMapFilterRegEx extends AbstractJSONMapFilter {
	private final String fieldPath;
	private final Pattern pattern;
	
	public JSONMapFilterRegEx(String fieldPath, Pattern pattern, boolean includeMatching) {
		super(includeMatching);
		this.fieldPath = fieldPath;
		this.pattern = pattern;
	}
	
	public JSONMapFilterRegEx(String fieldPath, String regex, boolean includeMatching) {
		this(fieldPath, Pattern.compile(regex), includeMatching);
	}

	@Override
	protected boolean isMatching(JSONMap json) {
		String value = json.getPath(fieldPath, String.class);
		return value==null ? false : pattern.matcher(value).matches();
	}
}
