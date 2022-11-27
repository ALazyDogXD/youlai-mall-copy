package com.alazydogxd.youlai.copy.common.base.util;

import com.alazydogxd.youlai.copy.common.base.convert.TreeNode;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ALazyDogXD
 * @date 2022/6/19 4:21
 * @description 树相关工具
 */

public class TreeUtil {

    private TreeUtil() {
    }

    public static <T extends TreeNode<T>> List<T> convert(List<T> treeNodes, int rootParentId) {
        Map<Integer, List<T>> pIdTreeMap = treeNodes.stream().collect(Collectors.groupingBy(TreeNode::getParentId));
        return convert(pIdTreeMap.get(rootParentId), pIdTreeMap);
    }

    private static <T extends TreeNode<T>> List<T> convert(List<T> roots, Map<Integer, List<T>> pIdTreeMap) {
        if (roots == null || roots.isEmpty()) {
            return null;
        }
        roots.sort(Comparator.comparingInt(TreeNode::getSort));
        for (T root : roots) {
            List<T> childNodes = pIdTreeMap.get(root.getId());
            root.setChildren(childNodes);
            convert(childNodes, pIdTreeMap);
        }
        return roots;
    }

}
