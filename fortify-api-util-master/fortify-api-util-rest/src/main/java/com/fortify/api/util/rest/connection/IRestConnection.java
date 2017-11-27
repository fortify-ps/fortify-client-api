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
package com.fortify.api.util.rest.connection;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

/**
 * Interface providing low-level methods for building and executing REST requests.
 * 
 * @author Ruud Senden
 *
 */
public interface IRestConnection {

	public abstract <T> T executeRequest(String httpMethod, Builder builder, Entity<?> entity, Class<T> returnType);
	public abstract <T> T executeRequest(String httpMethod, WebTarget webResource, Class<T> returnType);
	public abstract <T> T executeRequest(String httpMethod, WebTarget webResource, Entity<?> entity, Class<T> returnType);
	public abstract String getBaseUrl();
	public abstract WebTarget getBaseResource();
	public abstract WebTarget getResource(String url);
	public abstract Client getClient();
	public abstract void setProxy(ProxyConfiguration proxy);
}
