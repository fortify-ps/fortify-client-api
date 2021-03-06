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
package com.fortify.client.webinspect.connection;

import javax.ws.rs.client.WebTarget;

import com.fortify.util.rest.connection.AbstractRestConnection;
import com.fortify.util.rest.connection.IRestConnectionBuilder;

/**
 * This class provides an authenticated REST connection for WebInspect. Low-level API's are
 * available through the various executeRequest() methods provided by {@link AbstractRestConnection}.
 * Higher-level API's are available through the {@link #api(Class)} method. Instances of this class
 * can be created using the {@link #builder()} method.
 * 
 * @author Ruud Senden
 *
 */
public class WebInspectAuthenticatingRestConnection extends WebInspectBasicRestConnection {
	private final String apiKey;
	
	protected WebInspectAuthenticatingRestConnection(WebInspectRestConnectionConfig<?> config) {
		super(config);
		this.apiKey = config.getApiKey();
	}
	
	@Override
	protected WebTarget updateWebTarget(WebTarget webTarget) {
		webTarget = super.updateWebTarget(webTarget);
		if ( apiKey != null ) {
			webTarget = webTarget.queryParam("api_key", apiKey);
		}
		return webTarget;
	}
	
	public static final WebInspectAuthenticatingRestConnectionBuilder builder() {
		return new WebInspectAuthenticatingRestConnectionBuilder();
	}
	
	public static class WebInspectAuthenticatingRestConnectionBuilder extends WebInspectRestConnectionConfig<WebInspectAuthenticatingRestConnectionBuilder> implements IRestConnectionBuilder<WebInspectAuthenticatingRestConnection> {
		@Override
		public WebInspectAuthenticatingRestConnection build() {
			return new WebInspectAuthenticatingRestConnection(this);
		}
	}
}
