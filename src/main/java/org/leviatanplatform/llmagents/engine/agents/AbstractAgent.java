package org.leviatanplatform.llmagents.engine.agents;

import org.leviatanplatform.llmagents.engine.llm.LLM;

public class AbstractAgent {

    private final LLM llm;
    private final String context;


    public AbstractAgent(String context) {
        this(new LLM(), context);
    }

    public AbstractAgent(String model, String context) {
        this(new LLM(model), context);
    }

    private AbstractAgent(LLM llm, String context) {
        this.llm = llm;
        this.context = context;
    }

}
