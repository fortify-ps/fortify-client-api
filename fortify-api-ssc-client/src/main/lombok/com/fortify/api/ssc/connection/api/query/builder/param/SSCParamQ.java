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
package com.fortify.api.ssc.connection.api.query.builder.param;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.fortify.api.util.rest.webtarget.IWebTargetUpdater;
import com.fortify.api.util.rest.webtarget.IWebTargetUpdaterBuilder;
import com.fortify.api.util.rest.webtarget.WebTargetQueryParamUpdater;

public class SSCParamQ implements IWebTargetUpdaterBuilder {
	private final Map<String, String> paramQAnds = new HashMap<>();
	
	public final SSCParamQ paramQAnd(String field, String value) {
		paramQAnds.put(field, value);
		return this;
	}

	@Override
	public IWebTargetUpdater build() {
		String q = null;
		if ( MapUtils.isNotEmpty(paramQAnds) ) {
			StringBuffer sb = new StringBuffer();
			for ( Map.Entry<String, String> entry : paramQAnds.entrySet() ) {
				String qAppend = entry.getKey()+":\""+entry.getValue()+"\"";
				if ( sb.length() == 0 ) {
					sb.append(qAppend);
				} else {
					sb.append("+and+"+qAppend);
				}
			}
			q = sb.toString();
		}
		return new WebTargetQueryParamUpdater("q", q);
	}

}
