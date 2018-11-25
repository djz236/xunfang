package com.immoc.service.search;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.immoc.ApplicationTests;
import com.immoc.service.ServiceMultiResult;
import com.immoc.web.form.RentSearch;

/**
 * Created by 瓦力.
 */
public class SearchServiceTests extends ApplicationTests {//extends ApplicationTests {

    @Autowired
    private ISearchService searchService;
    
    @Test
    public void testIndex() {
        Long targetHouseId = 15L;
         searchService.index(targetHouseId);
      //  Assert.assertTrue(success);
    }
    
    
    @Test
    public void testRemove() {
        Long targetHouseId = 15L;

        searchService.remove(targetHouseId);
    }
 
    @Test
    public void testQuery() {
        RentSearch rentSearch = new RentSearch();
        rentSearch.setCityEnName("bj");
        rentSearch.setStart(0);
        rentSearch.setSize(10);
        rentSearch.setKeywords("a");
        ServiceMultiResult<Long> serviceResult = searchService.query(rentSearch);
        Assert.assertTrue(serviceResult.getTotal() > 0);
    }
}
