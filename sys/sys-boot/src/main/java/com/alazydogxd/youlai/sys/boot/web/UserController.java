package com.alazydogxd.youlai.sys.boot.web;

import com.alazydogxd.youlai.copy.common.base.resp.ResponseData;
import com.alazydogxd.youlai.sys.boot.entity.vo.LoginUserVO;
import com.alazydogxd.youlai.sys.boot.entity.UserRoleDO;
import com.alazydogxd.youlai.sys.boot.service.RoleService;
import com.alazydogxd.youlai.sys.boot.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static com.alazydogxd.youlai.common.web.util.RequestUtil.getHeader;
import static com.alazydogxd.youlai.copy.common.base.constant.RequestHttpHeaders.TOKEN_PAYLOAD;

/**
 * @author ALazyDogXD
 * @date 2022/6/17 7:49
 * @description 用户 Controller
 */

@Api(tags = "用户相关接口")
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("查询当前登录用户")
    @GetMapping("me")
    public ResponseData<LoginUserVO> getCurrentUser() throws UnsupportedEncodingException {
        LoginUserVO loginUser = new LoginUserVO();
        String payload = getHeader(TOKEN_PAYLOAD);
        JSONObject jsonObject = JSON.parseObject(URLDecoder.decode(payload, StandardCharsets.UTF_8.name()));
        UserRoleDO userRole = userService.getUserRoleByUsername(jsonObject.getString("username"));
        return ResponseData.success((LoginUserVO) loginUser.convert(userRole));
    }

}
