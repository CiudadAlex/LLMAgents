package org.leviatanplatform.llmagents.engine.agents;

public class SoftwareEngineerAgent extends AbstractAgent {

    private static final String CONTEXT = "You are a software engineer, very smart and good with technology. ";

    public SoftwareEngineerAgent() {
        super(CONTEXT);
    }

    public SoftwareEngineerAgent(String model) {
        super(model, CONTEXT);
    }
}
