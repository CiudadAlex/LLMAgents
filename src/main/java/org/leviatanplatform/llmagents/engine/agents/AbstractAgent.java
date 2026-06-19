package org.leviatanplatform.llmagents.engine.agents;

import org.leviatanplatform.llmagents.engine.llm.LLM;
import org.leviatanplatform.llmagents.engine.parent.TextCallable;

import java.io.IOException;

public abstract class AbstractAgent implements TextCallable {

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

    @Override
    public String call(String inputText) throws IOException {
        return llm.call(context + " " + inputText);
    }

}
