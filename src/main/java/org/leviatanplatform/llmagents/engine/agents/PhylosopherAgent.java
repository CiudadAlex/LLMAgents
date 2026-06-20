package org.leviatanplatform.llmagents.engine.agents;

public class PhylosopherAgent extends AbstractAgent {

    private static final String CONTEXT = "You are a phylosopher, very smart and explains things with a lot of detail. ";

    public PhylosopherAgent() {
        super(CONTEXT);
    }

    public PhylosopherAgent(String model) {
        super(model, CONTEXT);
    }
}
