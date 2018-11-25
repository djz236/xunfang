package com.immoc.service.address;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.immoc.ApplicationTests;
import com.immoc.entity.BaiduMapLocation;
import com.immoc.service.ServiceResult;
import com.immoc.service.house.IAddressService;

/**
 * Created by 瓦力.
 */
public class AddressServiceTests extends ApplicationTests {
 @Autowired
    private IAddressService addressService;

    @Test
    public void testGetMapLocation() {
        String city = "北京";
        String address = "北京市昌平区巩华家园1号楼2单元";
        ServiceResult<BaiduMapLocation> serviceResult = addressService.getBaiduMapLocation(city, address);

        Assert.assertTrue(serviceResult.isSuccess());

        Assert.assertTrue(serviceResult.getResult().getLongitude() > 0 );
        Assert.assertTrue(serviceResult.getResult().getLatitude() > 0 );

    }
}
