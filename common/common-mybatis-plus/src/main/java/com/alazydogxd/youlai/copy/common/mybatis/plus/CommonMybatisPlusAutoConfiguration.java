package com.alazydogxd.youlai.copy.common.mybatis.plus;

import com.alazydogxd.youlai.copy.common.mybatis.plus.handler.MetaHandler;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ALazyDogXD
 * @date 2022/6/12 0:34
 * @description Mybatis Plus 配置
 */

@ComponentScan
public class CommonMybatisPlusAutoConfiguration {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInnerInterceptor paginationInterceptor() {
        return new PaginationInnerInterceptor();
    }


    /**
     * 乐观锁插件
     */
    @Bean
    public OptimisticLockerInnerInterceptor mybatisPlusInterceptor() {
        return new OptimisticLockerInnerInterceptor();
    }

    /**
     * 数据填充
     */
    @Bean
    public MetaHandler metaHandler() {
        return new MetaHandler();
    }

}
