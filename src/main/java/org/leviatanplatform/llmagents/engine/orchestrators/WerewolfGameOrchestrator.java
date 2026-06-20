package org.leviatanplatform.llmagents.engine.orchestrators;

import org.leviatanplatform.llmagents.engine.agents.*;

import java.io.IOException;
import java.util.List;

public class WerewolfGameOrchestrator {

    private static final String EXCUSE_PROMPT = "you have to convince me that you are not a werewolf. Keep the explanation short";

    private List<AbstractAgent> listAgents;

    public WerewolfGameOrchestrator() {
        this.listAgents = List.of(new PeasantAgent(), new PeasantAgent(), new WerewolfAgent(),
                new PeasantAgent(), new PeasantAgent(), new WerewolfAgent(), new PeasantAgent(), new PeasantAgent());
    }

    public WerewolfGameOrchestrator(String model) {
        this.listAgents = List.of(new PeasantAgent(model), new PeasantAgent(model), new WerewolfAgent(model),
                new PeasantAgent(model), new PeasantAgent(model), new WerewolfAgent(model), new PeasantAgent(model), new PeasantAgent(model));
    }

    public String executeGame() throws IOException {

        boolean peopleOrWerewolves = true;

        while (true) {

            executeTurn(listAgents, peopleOrWerewolves);

            if (isThereAWinner(listAgents)) {
                return listAgents.get(0).getClass().getSimpleName();
            }

            peopleOrWerewolves = !peopleOrWerewolves;
        }
    }

    public void executeTurn(List<AbstractAgent> listRemainingAgents, boolean peopleOrWerewolves) throws IOException {

        Integer indexToKill;

        if (peopleOrWerewolves) {
            indexToKill = getAgentIndexKilledByPeople(listRemainingAgents);
        } else {
            indexToKill = getAgentIndexVotedByWerewolf(listRemainingAgents);
        }

        kill(peopleOrWerewolves, listRemainingAgents, indexToKill);
    }

    private void kill(boolean peopleOrWerewolves, List<AbstractAgent> listRemainingAgents, Integer indexToKill) {

        AbstractAgent killed = listRemainingAgents.remove(indexToKill.intValue());

        String who = peopleOrWerewolves ? "People" : "Werewolves";
        log(who + " kills " + killed.getClass().getSimpleName());
    }

    private boolean isThereAWinner(List<AbstractAgent> listRemainingAgents) {
        return !areThereAnyPeasants(listRemainingAgents) || !areThereAnyWerewolves(listRemainingAgents);
    }

    private boolean areThereAnyPeasants(List<AbstractAgent> listRemainingAgents) {

        for (AbstractAgent agent : listRemainingAgents) {

            if (agent instanceof PeasantAgent) {
                return true;
            }
        }

        return false;
    }

    private boolean areThereAnyWerewolves(List<AbstractAgent> listRemainingAgents) {

        for (AbstractAgent agent : listRemainingAgents) {

            if (agent instanceof WerewolfAgent) {
                return true;
            }
        }

        return false;
    }

    private Integer getAgentIndexKilledByPeople(List<AbstractAgent> listRemainingAgents) throws IOException {

        String excusesDocument = generateExcusesDocument(listRemainingAgents);
        log("Excuses Document", excusesDocument);

        int[] arrayVotes = new int[listRemainingAgents.size()];

        for (AbstractAgent agent : listRemainingAgents) {

            Integer vote = null;

            if (agent instanceof PeasantAgent peasantAgent) {
                vote = getAgentIndexVotedByPeasant(excusesDocument, peasantAgent, listRemainingAgents);

            } else if (agent instanceof WerewolfAgent) {
                vote = getAgentIndexVotedByWerewolf(listRemainingAgents);
            }

            arrayVotes[vote]++;
        }

        return getMaxIndex(arrayVotes);
    }

    private int getMaxIndex(int[] array) {

        int max = Integer.MIN_VALUE;
        int indexOfMax = -1;

        for (int i = 0; i < array.length; i++) {

            int value = array[i];

            if (value > max) {
                max = value;
                indexOfMax = i;
            }
        }

        return indexOfMax;
    }

    private Integer getAgentIndexVotedByPeasant(String excusesDocument, PeasantAgent peasantAgent, List<AbstractAgent> listRemainingAgents) throws IOException {

        while (true) {

            try {

                String strIndex = peasantAgent.call("These are the comments of the people in this town. You have to tell me which number of person do you think is a werewolf?" +
                        " You MUST answer only the number. These are their comments:\n" + excusesDocument);
                log("Index of agent to kill by peasant: " + strIndex);

                int index = Integer.parseInt(strIndex);
                listRemainingAgents.get(index);
                return index;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Integer getAgentIndexVotedByWerewolf(List<AbstractAgent> listRemainingAgents) {

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

    private void log(String title, String text) {

        log("#########################");
        log(title);
        log("-------------");
        log(text);
        log("#########################");
    }

    private void log(String text) {
        System.out.println(text);
    }
}
