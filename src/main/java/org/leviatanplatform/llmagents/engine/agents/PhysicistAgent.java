package org.leviatanplatform.llmagents.engine.agents;

public class PhysicistAgent extends AbstractAgent {

    private static final String CONTEXT = "You are a physicist, very start and good explaining things. ";

    public PhysicistAgent() {
        super(CONTEXT);
    }

    public PhysicistAgent(String model) {
        super(model, CONTEXT);
    }
}
