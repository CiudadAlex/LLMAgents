package org.leviatanplatform.llmagents.engine.orchestrators;

import org.leviatanplatform.llmagents.engine.agents.*;

import java.io.IOException;
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

    public String execute(String inputText) throws IOException {

        String currentText = inputText;

        for (AbstractAgent agent : listAgents) {
            currentText = agent.call("Tell me with your own words the following text: '" + currentText + "'");
            System.out.println("--------------------");
            System.out.println(currentText);
            System.out.println("--------------------");
        }

        return currentText;
    }
}
