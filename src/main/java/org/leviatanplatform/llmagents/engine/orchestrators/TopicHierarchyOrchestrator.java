package org.leviatanplatform.llmagents.engine.orchestrators;

import org.leviatanplatform.llmagents.engine.agents.JsonListAgent;
import org.leviatanplatform.llmagents.engine.domain.HierarchyNode;
import org.leviatanplatform.llmagents.engine.domain.NameAndDescription;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TopicHierarchyOrchestrator {

    private JsonListAgent jsonListAgent;

    public TopicHierarchyOrchestrator() {
        this.jsonListAgent = new JsonListAgent();
    }

    public TopicHierarchyOrchestrator(String model) {
        this.jsonListAgent = new JsonListAgent(model);
    }

    public HierarchyNode<NameAndDescription> getHierarchyConcepts(String concept, int layers) throws IOException {

        NameAndDescription nodeRoot = new NameAndDescription(concept, concept);
        HierarchyNode<NameAndDescription> hierarchyRoot = new HierarchyNode<>(nodeRoot);

        fillChildren(hierarchyRoot);

        List<HierarchyNode<NameAndDescription>> currentChildren = hierarchyRoot.getChildren();

        for (int i = 0; i < layers - 1; i++) {

            List<HierarchyNode<NameAndDescription>> nextChildren = new ArrayList<>();

            for (HierarchyNode<NameAndDescription> child : currentChildren) {
                fillChildren(child);
                nextChildren.addAll(child.getChildren());
            }

            currentChildren = nextChildren;
        }

        return hierarchyRoot;
    }

    public void fillChildren(HierarchyNode<NameAndDescription> hierarchyNode) throws IOException {

        List<NameAndDescription> listNameAndDescription = jsonListAgent.callAndRetrieveList(hierarchyNode.getNode().getName());
        List<HierarchyNode<NameAndDescription>> children = listNameAndDescription.stream().map(HierarchyNode::new).toList();
        hierarchyNode.setChildren(children);
    }
}
