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
package com.fortify.util.rest.query;

import com.fortify.util.rest.json.processor.IJSONMapProcessor;

import lombok.Getter;
import lombok.ToString;

/**
 * This class is used to hold and calculate data related to paging.
 * 
 * @author Ruud Senden
 *
 */
@Getter @ToString
public class PagingData {
	private int processedTotalBeforeFilters = 0;
	private int processedCurrentPageBeforeFilters = -1;
	private int processedTotalAfterFilters = 0;
	private int totalAvailable = -1;
	private int pageSize = 50;
	private int maxResults = -1;
	private int nextPageSize = -1;
	
	/**
	 * Get the start position for the next page to be loaded.
	 * {@link AbstractRestConnectionQuery} implementations will
	 * usually add this information to paged REST requests.
	 * @return next page start index
	 */
	public int getNextPageStart() {
		return processedTotalBeforeFilters;
	}
	
	/**
	 * Get the size for the next page to be loaded.
	 * {@link AbstractRestConnectionQuery} implementations will
	 * usually add this information to paged REST requests.
	 * @return next page size
	 */
	public int getNextPageSize() {
		return nextPageSize;
	}
	
	/**
	 * Indicate whether we've already loaded the maximum number of results
	 * (if configured).
	 * @return true if maximum results is reached, false otherwise
	 */
	public boolean isMaxResultsReached() {
		return maxResults != -1 && processedTotalAfterFilters >= maxResults;
	}
	
	/**
	 * Set the available total number of results. This is used for
	 * information purposes only (for example to be printed/logged
	 * in the {@link IJSONMapProcessor#notifyNextPage(PagingData)}
	 * method).
	 * 
	 * @param totalAvailable indicates how many results are available in total
	 */
	public void setTotalAvailable(int totalAvailable) {
		this.totalAvailable = totalAvailable;
	}
	
	/**
	 * Package-private method for calculating next page size, returning
	 * the newly calculated page size. This method must be called before
	 * any call to {@link #getNextPageSize()}, and after processing each
	 * page.
	 */
	int calculateNextPageSize() {
		if ( isMaxResultsReached() || 
				(processedCurrentPageBeforeFilters>-1 && processedCurrentPageBeforeFilters < nextPageSize) ) {
			// If we've loaded all required results, or the current page size was smaller than expected 
			// (meaning no more results), return 0.
			nextPageSize = 0;
		} else if ( maxResults < 0 || processedTotalAfterFilters < processedTotalBeforeFilters ) {
			// If no max results is configured, or if results are being filtered, simply return configured page size
			nextPageSize = pageSize;
		} else {
			// For non-filtered results, return either configured page size, or remaining
			// number of results to be loaded if this is smaller than configured page
			// size.
			nextPageSize = Math.min(pageSize, maxResults - processedTotalAfterFilters );
		}
		processedCurrentPageBeforeFilters = 0;
		return nextPageSize;
	}
	
	/**
	 * Package-private method for updating the number
	 * of processed results.
	 * @param count number of items processed before filtering
	 */
	void addProcessedBeforeFilters(int count) {
		processedTotalBeforeFilters += count; processedCurrentPageBeforeFilters += count;
	}
	
	/**
	 * Package-private method for updating the number
	 * of non-filtered processed results.
	 * @param count number of items processed after filtering
	 */
	void addProcessedAfterFilters(int count) {
		processedTotalAfterFilters += count;
	}
	
	/**
	 * Configure the maximum number of results to
	 * be loaded.
	 * @param max number of results to be processed
	 * @return Self for chaining
	 */
	public PagingData maxResults(int max) {
		this.maxResults  = max;
		return this;
	}
	
	/**
	 * Configure the page size; default is 50
	 * @param pageSize specifies the number of entries per page to be retrieved
	 * @return Self for chaining
	 */
	public PagingData pageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}
}