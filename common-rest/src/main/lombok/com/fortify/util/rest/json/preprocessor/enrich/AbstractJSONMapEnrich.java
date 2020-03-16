/*******************************************************************************
 * (c) Copyright 2020 Micro Focus or one of its affiliates, a Micro Focus company
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
package com.fortify.util.rest.json.preprocessor.enrich;

import com.fortify.util.rest.json.JSONMap;
import com.fortify.util.rest.json.preprocessor.IJSONMapPreProcessor;

/**
 * This abstract implementation for {@link IJSONMapPreProcessor} allows for
 * enriching a given {@link JSONMap} with additional data. Concrete implementations
 * must implement the {@link #enrich(JSONMap)} method to actually enrich the
 * given {@link JSONMap}.
 * 
 * @author Ruud Senden
 *
 */
public abstract class AbstractJSONMapEnrich implements IJSONMapPreProcessor {

	@Override
	public boolean preProcess(JSONMap json) {
		enrich(json);
		return true;
	}

	protected abstract void enrich(JSONMap json);

}
