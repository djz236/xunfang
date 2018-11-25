package com.immoc.service.house;

import java.io.File;
import java.io.InputStream;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

public interface IQiNiuService {

	Response uploadFile(File file) throws QiniuException;

	Response uploadFile(InputStream inputStream) throws QiniuException;

	Response delete(String key) throws QiniuException;
}
