package org.leviatanplatform.llmagents.engine.agents;

public class WerewolfAgent extends AbstractAgent {

    private static final String CONTEXT = "You are a werewolf. ";

    public WerewolfAgent() {
        super(CONTEXT);
    }

    public WerewolfAgent(String model) {
        super(model, CONTEXT);
    }
}
