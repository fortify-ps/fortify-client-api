/*******************************************************************************
 * (c) Copyright 2017 EntIT Software LLC, a Micro Focus company
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
package com.fortify.client.webinspect.api;

import java.nio.file.CopyOption;
import java.nio.file.Path;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import com.fortify.client.webinspect.connection.WebInspectAuthenticatingRestConnection;
import com.fortify.util.rest.json.JSONMap;

/**
 * This class is used to access WebInspect scan-related functionality.
 * 
 * @author Ruud Senden
 *
 */
public class WebInspectScanAPI extends AbstractWebInspectAPI {
	public WebInspectScanAPI(WebInspectAuthenticatingRestConnection conn) {
		super(conn);
	}

	public JSONMap createScan(JSONMap scanData) {
		return conn().executeRequest(HttpMethod.POST, conn().getBaseResource().path("/scanner/scans"),
				Entity.entity(scanData, MediaType.APPLICATION_JSON), JSONMap.class);
	}
	
	public void saveScan(String scanId, String extension, String detailType, Path outputPath, CopyOption... copyOptions) {
		conn().executeRequestAndSaveResponse(HttpMethod.GET, conn().getBaseResource().path("/scanner/scans/{scanId}.{extension}")
				.resolveTemplate("scanId", scanId)
				.resolveTemplate("extension", extension), outputPath, copyOptions);
	}
}
