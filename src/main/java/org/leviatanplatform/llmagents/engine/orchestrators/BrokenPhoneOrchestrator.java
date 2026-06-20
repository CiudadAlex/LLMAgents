package org.leviatanplatform.llmagents.engine.orchestrators;

import org.leviatanplatform.llmagents.engine.agents.*;

import java.util.List;

public class BrokenPhoneOrchestrator {

    private List<AbstractAgent> listAgents;

    public BrokenPhoneOrchestrator() {
        this.listAgents = List.of(new MathematicianAgent(), new PhylosopherAgent(), new PhysicistAgent(),
                new DumbAgent(), new LittleKidAgent(), new SoftwareEngineerAgent(), new TeacherAgent());
    }

    public BrokenPhoneOrchestrator(String model) {
        this.listAgents = List.of(new MathematicianAgent(model), new PhylosopherAgent(model), new PhysicistAgent(model),
                new DumbAgent(model), new LittleKidAgent(model), new SoftwareEngineerAgent(model), new TeacherAgent(model));
    }

    // FIXME finish

}
