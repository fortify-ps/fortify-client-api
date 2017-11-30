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
package com.fortify.api.ssc.connection.api.query;

import java.util.List;
import java.util.Map;

import javax.ws.rs.client.WebTarget;

import com.fortify.api.ssc.connection.SSCAuthenticatingRestConnection;

import lombok.Builder;
import lombok.Singular;

public final class SSCApplicationVersionQuery extends AbstractSSCEntityQuery {
	
	/**
	 * 
	 * @param conn SSC connection
	 * @param paramQAnds Add elements to SSC's 'q' request parameter
	 * @param paramFields Set the entity fields to be returned by SSC
	 * @param paramOrderBy Specify the field to order the result by
	 * @param maxResults Maximum number of results to return
	 */
	@Builder
	private SSCApplicationVersionQuery(
			SSCAuthenticatingRestConnection conn, 
			@Singular Map<String, String> paramQAnds,
			List<String> paramFields, 
			String paramOrderBy, 
			Integer maxResults) {
		super(conn);
		setParamQAnds(paramQAnds);
		setParamFields(paramFields);
		setParamOrderBy(paramOrderBy);
		setMaxResults(maxResults);
	}
	
	/**
	 * Test
	 * @author Ruud Senden
	 *
	 */
	public static class SSCApplicationVersionQueryBuilder {
		public SSCApplicationVersionQueryBuilder id(String id) {
			return paramQAnd("id", id);
		}

		public SSCApplicationVersionQueryBuilder applicationName(String applicationName) {
			return paramQAnd("project.name", applicationName);
		}

		public SSCApplicationVersionQueryBuilder versionName(String versionName) {
			return paramQAnd("name", versionName);
		}
	}
	
	@Override
	protected boolean isPagingSupported() {
		return true;
	}

	@Override
	protected WebTarget getBaseWebTarget() {
		return conn().getBaseResource().path("api/v1/projectVersions");
	}
}