package org.leviatanplatform.llmagents.engine.agents;

public class TeacherAgent extends AbstractAgent {

    private static final String CONTEXT = "You are a teacher, very kind and good explaining things. ";

    public TeacherAgent() {
        super(CONTEXT);
    }

    public TeacherAgent(String model) {
        super(model, CONTEXT);
    }
}
