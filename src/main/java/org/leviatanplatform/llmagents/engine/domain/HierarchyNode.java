package org.leviatanplatform.llmagents.engine.domain;

import java.util.List;

public class HierarchyNode<T> {

    private final T node;
    private List<HierarchyNode<T>> children;

    public HierarchyNode(T node) {
        this.node = node;
    }

    public T getNode() {
        return node;
    }

    public List<HierarchyNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<HierarchyNode<T>> children) {
        this.children = children;
    }
}
