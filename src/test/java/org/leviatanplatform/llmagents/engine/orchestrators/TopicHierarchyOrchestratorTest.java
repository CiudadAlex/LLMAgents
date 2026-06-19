package org.leviatanplatform.llmagents.engine.orchestrators;

import org.leviatanplatform.llmagents.engine.domain.HierarchyNode;
import org.leviatanplatform.llmagents.engine.domain.NameAndDescription;

import java.io.IOException;

class TopicHierarchyOrchestratorTest {

    public static void main(String[] args) throws IOException {

        TopicHierarchyOrchestrator topicHierarchyOrchestrator = new TopicHierarchyOrchestrator();
        askAndPrintResponse(topicHierarchyOrchestrator,"create a list of interesting topics about physics");
    }

    private static void askAndPrintResponse(TopicHierarchyOrchestrator topicHierarchyOrchestrator, String inputText) throws IOException {

        int layers = 2;
        HierarchyNode<NameAndDescription> hierarchyNode = topicHierarchyOrchestrator.getHierarchyConcepts(inputText, layers);

        System.out.println("#########################################################");

        for (HierarchyNode<NameAndDescription> item : hierarchyNode.getChildren()) {
            System.out.println(item);
        }

        System.out.println("#########################################################");
    }
}