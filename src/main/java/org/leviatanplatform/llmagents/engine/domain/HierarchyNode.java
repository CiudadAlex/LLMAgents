package org.leviatanplatform.llmagents.engine.domain;

import java.util.List;

public class HierarchyNode<T> {

    private T node;
    private List<HierarchyNode<T>> children;

    public HierarchyNode(T node, List<HierarchyNode<T>> children) {
        this.node = node;
        this.children = children;
    }

    public T getNode() {
        return node;
    }

    public List<HierarchyNode<T>> getChildren() {
        return children;
    }
}
