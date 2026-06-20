package org.leviatanplatform.llmagents.engine.orchestrators;

import org.leviatanplatform.llmagents.engine.domain.HierarchyNode;
import org.leviatanplatform.llmagents.engine.domain.NameAndDescription;

import java.io.IOException;

class TopicHierarchyOrchestratorTest {

    public static void main(String[] args) throws IOException {

        TopicHierarchyOrchestrator topicHierarchyOrchestrator = new TopicHierarchyOrchestrator();
        askAndPrintResponse(topicHierarchyOrchestrator,"interesting topics about physics");
    }

    private static void askAndPrintResponse(TopicHierarchyOrchestrator topicHierarchyOrchestrator, String inputText) throws IOException {

        int layers = 3;
        HierarchyNode<NameAndDescription> hierarchyNode = topicHierarchyOrchestrator.getHierarchyConcepts(inputText, layers);

        System.out.println("#########################################################");

        for (HierarchyNode<NameAndDescription> item : hierarchyNode.getChildren()) {

            System.out.println("-----------------------");

            System.out.println(item.getNode());

            for (HierarchyNode<NameAndDescription> subItem : item.getChildren()) {

                System.out.println(subItem.getNode().toString(2));

                for (HierarchyNode<NameAndDescription> subSubItem : subItem.getChildren()) {
                    System.out.println(subSubItem.getNode().toString(4));
                }
            }

            System.out.println("-----------------------");
        }

        System.out.println("#########################################################");
    }
}