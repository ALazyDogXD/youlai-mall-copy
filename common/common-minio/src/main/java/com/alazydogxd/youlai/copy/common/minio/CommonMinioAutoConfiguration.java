package com.alazydogxd.youlai.copy.common.minio;

import com.alazydogxd.youlai.copy.common.minio.config.MinioProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ALazyDogXD
 * @date 2022/7/9 12:00
 * @description Minio 配置
 */

@ComponentScan
@EnableConfigurationProperties(MinioProperties.class)
public class CommonMinioAutoConfiguration {
}
