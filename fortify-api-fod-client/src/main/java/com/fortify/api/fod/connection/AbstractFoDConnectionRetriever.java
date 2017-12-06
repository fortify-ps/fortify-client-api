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
package com.fortify.api.fod.connection;

import javax.ws.rs.core.Form;

import com.fortify.api.util.rest.connection.AbstractRestConnectionRetriever;
import com.fortify.api.util.rest.connection.IRestConnectionRetriever;

/**
 * <p>This abstract {@link IRestConnectionRetriever} will create 
 * an authenticated FoD REST connection based on the configured 
 * properties like base URL, proxy configuration and authentication 
 * data.</p>
 * 
 * <p>Subclasses will need to provide the actual authentication
 * data.</p>  
 */
public abstract class AbstractFoDConnectionRetriever extends AbstractRestConnectionRetriever<FoDAuthenticatingRestConnection> implements IFoDConnectionRetriever {
	private String baseUrl = "https://hpfod.com/";
	private String scope = "https://hpfod.com/tenant";
	private String grantType;
	
	protected final FoDAuthenticatingRestConnection createConnection() {
		Form form = new Form();
		form.param("scope",getScope());
		form.param("grant_type", getGrantType());
		addCredentials(form);
		return new FoDAuthenticatingRestConnection(getBaseUrl(), form, getProxy(), getConnectionProperties());
	}

	protected abstract void addCredentials(Form form);
	
	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
}
