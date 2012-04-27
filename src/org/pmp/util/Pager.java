/**
 * Author            : Elan
 * Created On        : 2012-3-21 下午12:45:49
 * 
 * Copyright 2012.  All rights reserved. 
 * 
 */
package org.pmp.util;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class Pager {
    //~ Instance Fields ================================================================================================
    private Integer pageSize;
    private Integer rowsCount;
    private Integer pagesCount;
    private Integer currentPage;
    private boolean isFirst;
    private boolean isLast;
    private boolean hasNext;
    private boolean hasPrev;
    //~ Constructor ====================================================================================================
    public Pager(Integer pageSize, Integer currentPage){
	this.pageSize = pageSize;
	this.currentPage = currentPage;
    }
    //~ Methods ========================================================================================================
    
    //~ Getters and Setters ============================================================================================
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getRowsCount() {
        return rowsCount;
    }
    public void setRowsCount(Integer rowsCount) {
        this.rowsCount = rowsCount;
    }
    public Integer getPagesCount() {
	pagesCount=rowsCount/pageSize;
	if(rowsCount%pageSize!=0){
		pagesCount++;
	}
	return pagesCount;
    }
    public void setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
    }
    public Integer getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
    public boolean isFirst() {
        return isFirst;
    }
    public void setFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }
    public boolean isLast() {
        return isLast;
    }
    public void setLast(boolean isLast) {
        this.isLast = isLast;
    }
    public boolean isHasNext() {
        return hasNext;
    }
    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
    public boolean isHasPrev() {
        return hasPrev;
    }
    public void setHasPrev(boolean hasPrev) {
        this.hasPrev = hasPrev;
    }
}
