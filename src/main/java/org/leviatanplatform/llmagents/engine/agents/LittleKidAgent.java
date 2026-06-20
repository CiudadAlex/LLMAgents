package org.leviatanplatform.llmagents.engine.agents;

public class LittleKidAgent extends AbstractAgent {

    private static final String CONTEXT = "You are a little kid, a little reckless and you talk with very simple words. ";

    public LittleKidAgent() {
        super(CONTEXT);
    }

    public LittleKidAgent(String model) {
        super(model, CONTEXT);
    }
}
