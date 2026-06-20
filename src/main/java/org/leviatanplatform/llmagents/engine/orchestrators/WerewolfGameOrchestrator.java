package org.leviatanplatform.llmagents.engine.orchestrators;

import org.leviatanplatform.llmagents.engine.agents.*;

import java.io.IOException;
import java.util.List;

public class WerewolfGameOrchestrator {

    private static final String EXCUSE_PROMPT = "you have to convince me that you are not a werewolf";

    private List<AbstractAgent> listAgents;

    public WerewolfGameOrchestrator() {
        this.listAgents = List.of(new PeasantAgent(), new PeasantAgent(), new WerewolfAgent(),
                new PeasantAgent(), new PeasantAgent(), new WerewolfAgent(), new PeasantAgent());
    }

    public WerewolfGameOrchestrator(String model) {
        this.listAgents = List.of(new PeasantAgent(model), new PeasantAgent(model), new WerewolfAgent(model),
                new PeasantAgent(model), new PeasantAgent(model), new WerewolfAgent(model), new PeasantAgent(model));
    }

    // FIXME finish

    private Integer getAgentIndexKilledByPeople(List<AbstractAgent> listRemainingAgents) throws IOException {

        String excusesDocument = generateExcusesDocument(listRemainingAgents);

        for (AbstractAgent agent : listRemainingAgents) {

        }
    }

    private Integer getAgentIndexVotedByPeasant(String excusesDocument, PeasantAgent peasantAgent, List<AbstractAgent> listRemainingAgents) throws IOException {

        String strIndex = peasantAgent.call(excusesDocument + ". Which number of person do you think is a werewolf? You MUST answer only the number");

        try {
            int index = Integer.parseInt(strIndex);
            listRemainingAgents.get(index);
            return index;

        } catch (Exception e) {
            // Nothing to do
        }

        return null;
    }

    private Integer getAgentIndexVotedByWerewolf(WerewolfAgent werewolfAgent, List<AbstractAgent> listRemainingAgents) {

        for (int i = 0; i < listRemainingAgents.size(); i++) {
            AbstractAgent agent = listRemainingAgents.get(i);
            if (agent instanceof PeasantAgent) {
                return i;
            }
        }

        return null;
    }

    private String generateExcusesDocument(List<AbstractAgent> listRemainingAgents) throws IOException {

        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (AbstractAgent agent : listRemainingAgents) {
            String excuse = agent.call(EXCUSE_PROMPT);
            sb.append("The person ").append(count).append(" says:\n").append(excuse).append("\n");
            count++;
        }

        return sb.toString();
    }
}
