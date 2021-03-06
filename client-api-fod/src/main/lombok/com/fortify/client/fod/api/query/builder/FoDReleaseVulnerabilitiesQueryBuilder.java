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
package com.fortify.client.fod.api.query.builder;

import com.fortify.client.fod.api.json.embed.FoDEmbedConfig.FoDEmbedConfigBuilder;
import com.fortify.client.fod.api.json.embed.FoDReleaseVulnerabilitiesEmbedConfig;
import com.fortify.client.fod.api.query.FoDEntityQuery;
import com.fortify.client.fod.api.query.builder.AbstractFoDEntityQueryBuilder.IFoDEntityQueryBuilderParamFields;
import com.fortify.client.fod.api.query.builder.AbstractFoDEntityQueryBuilder.IFoDEntityQueryBuilderParamFilter;
import com.fortify.client.fod.api.query.builder.AbstractFoDEntityQueryBuilder.IFoDEntityQueryBuilderParamOrderByWithDirection;
import com.fortify.client.fod.connection.FoDAuthenticatingRestConnection;
import com.fortify.util.applier.ifblank.IfBlankAction;
import com.fortify.util.rest.json.preprocessor.enrich.JSONMapEnrichWithDeepLink;

/**
 * This class allows for building an {@link FoDEntityQuery} instance that allows for
 * querying FoD release vulnerabilities.
 * 
 * @author Ruud Senden
 *
 */
public class FoDReleaseVulnerabilitiesQueryBuilder extends AbstractFoDReleaseChildEntityQueryBuilder<FoDReleaseVulnerabilitiesQueryBuilder> 
	implements IFoDEntityQueryBuilderParamFields<FoDReleaseVulnerabilitiesQueryBuilder>, 
	           IFoDEntityQueryBuilderParamFilter<FoDReleaseVulnerabilitiesQueryBuilder>,
	           IFoDEntityQueryBuilderParamOrderByWithDirection<FoDReleaseVulnerabilitiesQueryBuilder> 
{
	private static final String[] DEEPLINK_FIELDS = {"vulnId"};
	public FoDReleaseVulnerabilitiesQueryBuilder(FoDAuthenticatingRestConnection conn, String releaseId) {
		super(conn, releaseId, true);
		appendPath("vulnerabilities");
		preProcessor(new JSONMapEnrichWithDeepLink(conn.getBrowserBaseUrl().toString()+"/redirect/Issues/${vulnId}", DEEPLINK_FIELDS));
	}
	
	@Override
	protected FoDEmbedConfigBuilder<?, ?> createEmbedConfigBuilder() {
		return FoDReleaseVulnerabilitiesEmbedConfig.builder();
	}
	
	@Override
	public FoDReleaseVulnerabilitiesQueryBuilder paramFields(IfBlankAction ifBlankAction, String... fields) {
		return super.paramFields(ifBlankAction, replaceField(JSONMapEnrichWithDeepLink.DEEPLINK_FIELD, DEEPLINK_FIELDS, fields));
	}
	
	@Override
	public FoDReleaseVulnerabilitiesQueryBuilder paramFilterAnd(IfBlankAction ifBlankAction, String field, String... values) {
		return super.paramFilterAnd(ifBlankAction, field, values);
	}
	
	@Override
	public FoDReleaseVulnerabilitiesQueryBuilder paramFilterAnd(IfBlankAction ifBlankAction, String filter) {
		return super.paramFilterAnd(ifBlankAction, filter);
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder paramOrderBy(IfBlankAction ifBlankAction, FoDOrderBy orderBy) {
		return super.paramOrderBy(ifBlankAction, orderBy);
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder paramExcludeFilters(IfBlankAction ifBlankAction, Boolean excludeFilters) {
		return super.queryParam(ifBlankAction, "excludeFilters", Boolean.toString(excludeFilters));
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder paramIncludeFixed(IfBlankAction ifBlankAction, Boolean includeFixed) {
		return super.queryParam(ifBlankAction, "includeFixed", Boolean.toString(includeFixed));
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder paramIncludeSuppressed(IfBlankAction ifBlankAction, Boolean includeSuppressed) {
		return super.queryParam(ifBlankAction, "includeSuppressed", Boolean.toString(includeSuppressed));
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder paramKeywordSearch(IfBlankAction ifBlankAction, String keywordSearch) {
		return super.queryParam(ifBlankAction, "keywordSearch", keywordSearch);
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandAll() {
		return onDemandAllData().onDemandAuditOptions().onDemandDetails().onDemandHeaders().onDemandHistory()
			.onDemandParameters().onDemandRecommendations().onDemandRequestResponse().onDemandScreenshots()
			.onDemandSummary().onDemandTraces();
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandAllData() {
		return onDemandAllData("allData");
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandSummary() {
		return onDemandSummary("summary");
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandDetails() {
		return onDemandDetails("details");
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandRecommendations() {
		return onDemandRecommendations("recommendations");
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandHistory() {
		return onDemandHistory("history");
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandScreenshots() {
		return onDemandScreenshots("screenshots");
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandRequestResponse() {
		return onDemandRequestResponse("requestResponse");
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandHeaders() {
		return onDemandHeaders("headers");
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandParameters() {
		return onDemandParameters("parameters");
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandTraces() {
		return onDemandTraces("traces");
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandAuditOptions() {
		return onDemandAuditOptions("auditOptions");
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandAllData(String propertyName) {
		return embedSubEntity(propertyName, "all-data");
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandSummary(String propertyName) {
		return embedSubEntity(propertyName, "summary");
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandDetails(String propertyName) {
		return embedSubEntity(propertyName, "details");
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandRecommendations(String propertyName) {
		return embedSubEntity(propertyName, "recommendations");
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandHistory(String propertyName) {
		return embedSubEntity(propertyName, "history");
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandScreenshots(String propertyName) {
		return embedSubEntity(propertyName, "screenshots");
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandRequestResponse(String propertyName) {
		return embedSubEntity(propertyName, "response");
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandHeaders(String propertyName) {
		return embedSubEntity(propertyName, "headers");
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandParameters(String propertyName) {
		return embedSubEntity(propertyName, "parameters");
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandTraces(String propertyName) {
		return embedSubEntity(propertyName, "traces");
	}
	
	public FoDReleaseVulnerabilitiesQueryBuilder onDemandAuditOptions(String propertyName) {
		return embedSubEntity(propertyName, "auditOptions");
	}
}
