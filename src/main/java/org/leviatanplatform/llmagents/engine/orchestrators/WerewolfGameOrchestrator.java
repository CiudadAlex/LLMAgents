package org.leviatanplatform.llmagents.engine.orchestrators;

import org.leviatanplatform.llmagents.engine.agents.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WerewolfGameOrchestrator {

    private static final String EXCUSE_PROMPT = "you have to convince me that you are not a werewolf. Keep the explanation short";

    private List<AbstractAgent> listAgents;

    public WerewolfGameOrchestrator() {
        this.listAgents = list(new PeasantAgent(), new PeasantAgent(), new WerewolfAgent(),
                new PeasantAgent(), new PeasantAgent(), new WerewolfAgent(), new PeasantAgent(), new PeasantAgent());
    }

    public WerewolfGameOrchestrator(String model) {
        this.listAgents = list(new PeasantAgent(model), new PeasantAgent(model), new WerewolfAgent(model),
                new PeasantAgent(model), new PeasantAgent(model), new WerewolfAgent(model), new PeasantAgent(model), new PeasantAgent(model));
    }

    public static List<AbstractAgent> list(AbstractAgent... arrayAgents) {
        return new ArrayList<>(List.of(arrayAgents));
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

        List<String> listExcuses = generateExcuses(listRemainingAgents);
        log("Excuses Document", listExcuses);

        int[] arrayVotes = new int[listRemainingAgents.size()];

        for (AbstractAgent agent : listRemainingAgents) {

            Integer vote = null;

            if (agent instanceof PeasantAgent peasantAgent) {
                vote = getAgentIndexVotedByPeasant(listExcuses, peasantAgent);

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

    private Integer getAgentIndexVotedByPeasant(List<String> listExcuses, PeasantAgent peasantAgent) throws IOException {

        float max = Float.MIN_VALUE;
        int indexOfMax = -1;

        for (int i = 0; i < listExcuses.size(); i++) {

            String excuse = listExcuses.get(0);
            Float probability = getProbabilityOfWerewolf(excuse, peasantAgent);

            if (probability > max) {
                max = probability;
                indexOfMax = i;
            }
        }

        return indexOfMax;
    }

    private Float getProbabilityOfWerewolf(String excuse, PeasantAgent peasantAgent) {

        String strProbRaw = "";

        while (true) {

            try {

                String prompt = "what is the probability that a werewolf says '" + excuse + "'? Answer just with the probability";
                strProbRaw = peasantAgent.call(prompt);
                Float probability = extractProbability(strProbRaw);

                if (probability == null) {
                    continue;
                }

                log("Probability of agent to be a werewolf: " + probability);
                return probability;

            } catch (Exception e) {
                log("ERROR parsing probability: " + strProbRaw);
                e.printStackTrace();
            }
        }
    }

    private Float extractProbability(String strProbRaw) {

        String strProb = strProbRaw.toLowerCase().trim().replace("zero", "0");
        int lastIndexOfAsterisk = strProb.lastIndexOf("*");

        if (lastIndexOfAsterisk != -1) {
            strProb = strProb.substring(lastIndexOfAsterisk).trim();
        }

        Float probability = extractProbability(strProb, "%");

        if (probability != null) {
            return probability;
        }

        probability = extractProbability(strProb, " ");

        if (probability != null) {
            return probability;
        }

        return null;
    }

    private Float extractProbability(String strProbRaw, String end) {

        int indexOfEnd = strProbRaw.indexOf(end);

        if (indexOfEnd != -1) {
            String strProb = strProbRaw.substring(0, indexOfEnd);
            return Float.parseFloat(strProb);
        }

        return null;
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

    private List<String> generateExcuses(List<AbstractAgent> listRemainingAgents) throws IOException {

        List<String> listExcuses = new ArrayList<>();

        for (AbstractAgent agent : listRemainingAgents) {
            String excuse = agent.call(EXCUSE_PROMPT);
            listExcuses.add(excuse);
        }

        return listExcuses;
    }

    private void log(String title, List<String> texts) {

        String text = String.join("***\n\n", texts);
        log(title, text);
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
