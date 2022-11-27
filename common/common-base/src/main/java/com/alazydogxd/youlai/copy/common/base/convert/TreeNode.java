package com.alazydogxd.youlai.copy.common.base.convert;

import java.util.List;

/**
 * @author ALazyDogXD
 * @date 2022/6/19 4:17
 * @description 树节点
 */

public interface TreeNode<T extends TreeNode<T>> {

    /**
     * 获取 ID
     *
     * @return ID
     */
    int getId();

    /**
     * 获取 P_ID
     *
     * @return P_ID
     */
    int getParentId();

    /**
     * 获取节点顺序
     *
     * @return 节点顺序
     */
    int getSort();

    /**
     * 设置子节点
     *
     * @param children 子节点
     */
    void setChildren(List<T> children);

}
