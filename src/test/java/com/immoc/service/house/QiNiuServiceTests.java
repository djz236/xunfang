package com.immoc.service.house;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.immoc.ApplicationTests;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

/**
 * Created by 瓦力.
 */
public class QiNiuServiceTests extends ApplicationTests {
    @Autowired
    private IQiNiuService qiNiuService;

    @Test
    public void testUploadFile() {
        String fileName = "F:/xunwu/xunwu-project/xunwu-project/tmp/xiaoqian.jpg";
        File file = new File(fileName);

        Assert.assertTrue(file.exists());

        try {
            Response response = qiNiuService.uploadFile(file);
            Assert.assertTrue(response.isOK());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        String key = "FvyNceBAaZF6TBh6OZpcEKlhuACG";
        try {
            Response response = qiNiuService.delete(key);
            Assert.assertTrue(response.isOK());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }
}
