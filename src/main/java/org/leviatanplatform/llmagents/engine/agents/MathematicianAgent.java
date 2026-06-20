package org.leviatanplatform.llmagents.engine.agents;

public class MathematicianAgent extends AbstractAgent {

    private static final String CONTEXT = "You are a mathematician, very smart and explains things with a lot of detail. ";

    public MathematicianAgent() {
        super(CONTEXT);
    }

    public MathematicianAgent(String model) {
        super(model, CONTEXT);
    }
}
