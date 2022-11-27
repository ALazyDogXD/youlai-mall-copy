package com.alazydogxd.youlai.copy.common.minio.service;

import com.sun.javaws.exceptions.InvalidArgumentException;
import io.minio.errors.*;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Mr_W
 * @date 2021/3/13 12:39
 * @description MinIo 文件服务
 */
@SuppressWarnings("all")
public interface MinIoFileService {

    /**
     * 上传文件
     *
     * @param bucketName  桶名称
     * @param contentType 媒体类型
     * @param path        文件路径
     * @param fileName    文件名称
     * @param fileByte    文件字节数组
     * @return 图片路径
     */
    String upload(String bucketName, String contentType, String path, String fileName, byte[] fileByte)
            throws IOException,
            InsufficientDataException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            InternalException,
            InvalidArgumentException,
            ErrorResponseException,
            ServerException,
            InvalidResponseException,
            XmlParserException;

    /**
     * 上传文件
     *
     * @param bucketName  桶名称
     * @param contentType 媒体类型
     * @param path        文件路径
     * @param fileName    文件名称
     * @param in          文件流
     * @return 图片路径
     */
    String upload(String bucketName, String contentType, String path, String fileName, InputStream in)
            throws InsufficientDataException,
            NoSuchAlgorithmException,
            IOException,
            InvalidKeyException,
            InternalException,
            InvalidArgumentException,
            ErrorResponseException,
            ServerException,
            InvalidResponseException,
            XmlParserException;

    /**
     * 上传文件
     *
     * @param bucketName 桶名称
     * @param path       文件路径
     * @param fileName   文件名称
     * @param fileByte   文件字节数组
     * @return 图片路径
     */
    String upload(String bucketName, String path, String fileName, byte[] fileByte)
            throws InsufficientDataException,
            IOException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            InternalException,
            InvalidArgumentException,
            ErrorResponseException,
            ServerException,
            InvalidResponseException,
            XmlParserException;

    /**
     * 删除文件
     *
     * @param bucketName 桶名称
     * @param path       文件路径(包含文件名)
     */
    void remove(String bucketName, String path)
            throws InsufficientDataException,
            ErrorResponseException,
            NoSuchAlgorithmException,
            IOException,
            InvalidKeyException,
            InternalException,
            ServerException,
            InvalidResponseException,
            XmlParserException;

}
