package com.alazydogxd.youlai.copy.common.minio.service.impl;

import com.alazydogxd.youlai.copy.common.minio.config.MinioProperties;
import com.alazydogxd.youlai.copy.common.minio.service.MinIoFileService;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author Mr_W
 * @date 2021/3/13 14:07
 * @description MinIo 文件服务
 */
@Service
public class MinIoFileServiceImpl implements MinIoFileService {

    private static volatile MinioClient MINIO_CLIENT;

    private final String URL_SEPARATOR = "/";

    private final MinioProperties minioProperties;

    public MinIoFileServiceImpl(MinioProperties minioProperties) {
        this.minioProperties = minioProperties;
    }

    @Override
    public String upload(String bucketName, String contentType, String path, String fileName, byte[] fileByte)
            throws IOException,
            InsufficientDataException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            InternalException,
            ErrorResponseException,
            ServerException,
            InvalidResponseException,
            XmlParserException {
        if (!path.endsWith(URL_SEPARATOR)) {
            path = path + URL_SEPARATOR;
        }
        try (InputStream in = new ByteArrayInputStream(fileByte)) {
            // 上传文件
            upload(bucketName, path + fileName, in, contentType);
        }
        return URL_SEPARATOR + bucketName + URL_SEPARATOR + path + fileName;
    }

    @Override
    public String upload(String bucketName, String contentType, String path, String fileName, InputStream in)
            throws InsufficientDataException,
            NoSuchAlgorithmException,
            IOException,
            InvalidKeyException,
            InternalException,
            ErrorResponseException,
            ServerException,
            InvalidResponseException,
            XmlParserException {
        if (!path.endsWith(URL_SEPARATOR)) {
            path = path + URL_SEPARATOR;
        }
        // 上传文件
        upload(bucketName, path + fileName, in, contentType);
        return URL_SEPARATOR + bucketName + URL_SEPARATOR + path + fileName;
    }

    @Override
    public String upload(String bucketName, String path, String fileName, byte[] fileByte)
            throws InsufficientDataException,
            IOException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            InternalException,
            ErrorResponseException,
            ServerException,
            InvalidResponseException,
            XmlParserException {
        return upload(bucketName, "application/octet-stream", path, fileName, fileByte);
    }

    @Override
    public void remove(String bucketName, String path)
            throws InsufficientDataException,
            ErrorResponseException,
            NoSuchAlgorithmException,
            IOException,
            InvalidKeyException,
            InternalException,
            ServerException,
            InvalidResponseException,
            XmlParserException {
        removeFile(bucketName, path);
    }

    /**
     * 获取 MinIo 客户端
     *
     * @return MinIo 客户端
     */
    private MinioClient getMinIoClient() {
        if (Objects.isNull(MINIO_CLIENT)) {
            synchronized (MinIoFileServiceImpl.class) {
                if (Objects.isNull(MINIO_CLIENT)) {
                    MINIO_CLIENT = MinioClient.builder()
                            .endpoint(minioProperties.getHost(), minioProperties.getPort(), false)
                            .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey()).build();
                }
            }
        }
        return MINIO_CLIENT;
    }

    /**
     * 上传
     *
     * @param bucketName  桶名称
     * @param fileName    文件名称(含路径)
     * @param in          文件流
     * @param contentType 媒体格式
     */
    private void upload(String bucketName, String fileName, InputStream in, String contentType)
            throws InsufficientDataException,
            ErrorResponseException,
            NoSuchAlgorithmException,
            IOException,
            InvalidKeyException,
            InternalException,
            ServerException,
            InvalidResponseException,
            XmlParserException {
        MinioClient minioClient = getMinIoClient();
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(fileName).contentType(contentType).stream(in, in.available(), -1).build());
    }

    /**
     * 下载
     *
     * @param bucketName 桶名称
     * @param fileName   文件名称(含路径)
     * @return 文件流
     */
    private InputStream download(String bucketName, String fileName)
            throws InsufficientDataException,
            ErrorResponseException,
            NoSuchAlgorithmException,
            IOException,
            InvalidKeyException,
            InternalException,
            ServerException,
            InvalidResponseException,
            XmlParserException {
        MinioClient minioClient = getMinIoClient();
        if (fileIsExists(bucketName, fileName)) {
            return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
        }
        // 没有找到文件
        throw new FileNotFoundException("未查询到文件: " + fileName);
    }

    /**
     * 文件是否存在
     *
     * @param bucketName 桶名称
     * @param fileName   文件名称(含路径)
     * @return true 存在
     */
    private boolean fileIsExists(String bucketName, String fileName)
            throws InsufficientDataException,
            ErrorResponseException,
            NoSuchAlgorithmException,
            IOException,
            InvalidKeyException,
            InternalException,
            ServerException,
            InvalidResponseException,
            XmlParserException {
        MinioClient minioClient = getMinIoClient();
        if (minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            for (Result<Item> file : minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build())) {
                if (file.get().objectName().equals(fileName)) {
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * 删除文件
     *
     * @param bucketName 桶名称
     * @param fileName   文件路径
     */
    private void removeFile(String bucketName, String fileName)
            throws InsufficientDataException,
            ErrorResponseException,
            NoSuchAlgorithmException,
            IOException,
            InvalidKeyException,
            InternalException,
            ServerException,
            InvalidResponseException,
            XmlParserException {
        MinioClient minioClient = getMinIoClient();
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(fileName).build());
    }

}
