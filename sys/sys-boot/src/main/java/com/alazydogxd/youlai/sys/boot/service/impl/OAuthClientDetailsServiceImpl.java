package com.alazydogxd.youlai.sys.boot.service.impl;

import com.alazydogxd.youlai.sys.boot.entity.OAuthClientDetailsDO;
import com.alazydogxd.youlai.sys.boot.mapper.OAuthClientDetailsMapper;
import com.alazydogxd.youlai.sys.boot.service.OAuthClientDetailsService;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ALazyDogXD
 * @date 2022/6/12 1:43
 * @description OAuth 客户端服务
 */

@SuppressWarnings("ALL")
@Service
@Transactional(rollbackFor = Exception.class)
public class OAuthClientDetailsServiceImpl extends ServiceImpl<OAuthClientDetailsMapper, OAuthClientDetailsDO> implements OAuthClientDetailsService {
}
