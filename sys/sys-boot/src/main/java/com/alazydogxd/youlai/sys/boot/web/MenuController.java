package com.alazydogxd.youlai.sys.boot.web;

import com.alazydogxd.youlai.copy.common.base.resp.ResponseData;
import com.alazydogxd.youlai.copy.common.base.util.TreeUtil;
import com.alazydogxd.youlai.sys.boot.entity.MenuRoleDO;
import com.alazydogxd.youlai.sys.boot.entity.vo.MenuRoleVO;
import com.alazydogxd.youlai.sys.boot.service.MenuService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import static com.alazydogxd.youlai.common.web.util.RequestUtil.getHeader;
import static com.alazydogxd.youlai.copy.common.base.constant.RequestHttpHeaders.TOKEN_PAYLOAD;

/**
 * @author ALazyDogXD
 * @date 2022/6/19 4:14
 * @description 菜单 Controller
 */

@Api(tags = "菜单相关接口")
@RestController
@RequestMapping("menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @ApiOperation("菜单查询")
    @GetMapping("list")
    public ResponseData<List<MenuRoleVO>> getMenus() throws UnsupportedEncodingException {
        String payload = getHeader(TOKEN_PAYLOAD);
        JSONObject jsonObject = JSON.parseObject(URLDecoder.decode(payload, StandardCharsets.UTF_8.name()));
        int userId = jsonObject.getIntValue("userId");

        List<MenuRoleDO> menuRoles = menuService.getMenuRoleByUserId(userId);

        return ResponseData.success(TreeUtil.convert(menuRoles
                .stream()
                .map(menuRole -> (MenuRoleVO) new MenuRoleVO().convert(menuRole))
                .collect(Collectors.toList()), 0));
    }

}
