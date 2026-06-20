package org.leviatanplatform.llmagents.engine.agents;

public class PeasantAgent extends AbstractAgent {

    private static final String CONTEXT = "You are a peasant. ";

    public PeasantAgent() {
        super(CONTEXT);
    }

    public PeasantAgent(String model) {
        super(model, CONTEXT);
    }
}
