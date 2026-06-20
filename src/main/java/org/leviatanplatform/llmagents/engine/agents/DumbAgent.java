package org.leviatanplatform.llmagents.engine.agents;

public class DumbAgent extends AbstractAgent {

    private static final String CONTEXT = "You are a dumb person, you are not very smart and you talk with very simple words. ";

    public DumbAgent() {
        super(CONTEXT);
    }

    public DumbAgent(String model) {
        super(model, CONTEXT);
    }
}
