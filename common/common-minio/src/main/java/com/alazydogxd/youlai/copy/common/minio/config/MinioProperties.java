package com.alazydogxd.youlai.copy.common.minio.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ALazyDogXD
 * @date 2022/7/9 13:47
 * @description Minio 参数
 */

@ConfigurationProperties(prefix = "common.minio")
public class MinioProperties {

    private String host;

    private int port;

    private String accessKey;

    private String secretKey;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String toString() {
        return "MinioProperties{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", accessKey='" + accessKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }
}
