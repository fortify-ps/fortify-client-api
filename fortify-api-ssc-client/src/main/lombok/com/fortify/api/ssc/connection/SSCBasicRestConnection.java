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
package com.fortify.api.ssc.connection;

import javax.ws.rs.client.Invocation.Builder;

import com.fortify.api.util.rest.connection.RestConnection;
import com.fortify.api.util.rest.connection.RestConnectionWithCache;

/**
 * This class provides a basic, non-authenticating REST connection
 * for SSC. It's main characteristics compared to a standard 
 * {@link RestConnection} is that it will add an 
 * <code>Accept: application/json</code> header.
 * 
 * @author Ruud Senden
 *
 */
public class SSCBasicRestConnection extends RestConnectionWithCache<SSCRestConnectionConfig> {
	
	public SSCBasicRestConnection(SSCRestConnectionConfig config) {
		super(config);
	}
	
	/**
	 * Update the {@link Builder} to add the Accept and OAuth headers.
	 */
	@Override
	public Builder updateBuilder(Builder builder) {
		return super.updateBuilder(builder)
				.accept("application/json").header("Content-Type", "application/json");
	}
}
