package org.leviatanplatform.llmagents.engine.orchestrators;

import org.leviatanplatform.llmagents.engine.agents.JsonListAgent;
import org.leviatanplatform.llmagents.engine.domain.HierarchyNode;
import org.leviatanplatform.llmagents.engine.domain.NameAndDescription;

import java.io.IOException;
import java.util.List;

public class TopicHierarchyOrchestrator {

    private JsonListAgent jsonListAgent;

    public TopicHierarchyOrchestrator() {
        this.jsonListAgent = new JsonListAgent();
    }

    public TopicHierarchyOrchestrator(String model) {
        this.jsonListAgent = new JsonListAgent(model);
    }

    public HierarchyNode<NameAndDescription> getHierarchyConcepts(String concept) throws IOException {

        List<NameAndDescription> listNameAndDescription = jsonListAgent.callAndRetrieveList(concept);
        // FIXME acabar
        return null;
    }
}
