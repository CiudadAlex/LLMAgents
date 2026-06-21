package org.leviatanplatform.llmagents.engine.orchestrators;

import org.leviatanplatform.llmagents.engine.agents.*;
import org.leviatanplatform.llmagents.engine.config.Constants;
import org.leviatanplatform.llmagents.engine.domain.ExcuseGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WerewolfGameOrchestrator {

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

        List<ExcuseGenerator> listExcuseGenerator = generateExcuseGenerators(listRemainingAgents);
        log("Excuses Document", listExcuseGenerator.stream().map(ExcuseGenerator::getExcuse).toList());

        int[] arrayVotes = new int[listRemainingAgents.size()];

        for (AbstractAgent agent : listRemainingAgents) {

            Integer vote = null;

            if (agent instanceof PeasantAgent peasantAgent) {
                vote = getAgentIndexVotedByPeasant(listExcuseGenerator, peasantAgent);

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

    private Integer getAgentIndexVotedByPeasant(List<ExcuseGenerator> listExcuseGenerator, PeasantAgent peasantAgent) throws IOException {

        float max = Float.NEGATIVE_INFINITY;
        int indexOfMax = -1;

        for (int i = 0; i < listExcuseGenerator.size(); i++) {

            ExcuseGenerator excuseGenerator = listExcuseGenerator.get(i);
            Float probability = getProbabilityOfWerewolf(excuseGenerator, peasantAgent);

            if (probability > max) {
                max = probability;
                indexOfMax = i;
            }
        }

        return indexOfMax;
    }

    private Float getProbabilityOfWerewolf(ExcuseGenerator excuseGenerator, PeasantAgent peasantAgent) {

        String strProbRaw = "";
        int count = -1;

        while (true) {

            count++;

            try {
                String prompt = generatePromptAskForProbabilityOfWerewolf(excuseGenerator, count);

                strProbRaw = peasantAgent.call(prompt);
                Float probability = extractProbability(strProbRaw);

                if (probability == null) {
                    continue;
                }

                log("Probability of agent to be a werewolf: " + probability);
                return probability;

            } catch (Exception e) {

                if (Constants.DEBUG) {
                    log("ERROR parsing probability: " + strProbRaw);
                    e.printStackTrace();
                }
            }
        }
    }

    private String generatePromptAskForProbabilityOfWerewolf(ExcuseGenerator excuseGenerator, int count) throws IOException {

        int mod = count % 3;
        String excuse = excuseGenerator.getExcuse();

        if (mod == 0) {
            return "What is the probability that a werewolf says \" " + excuse + " \" ? Answer just with the probability";

        } else if (mod == 1) {
            return "if someone says: \" " + excuse + " \" , What is the probability that it is a werewolf? Answer just with the probability";

        } else {
            excuseGenerator.askForExcuse();
            return "What is the probability that a bad person says \" " + excuse + " \" ? Answer just with the probability";

        }
    }

    private Float extractProbability(String strProbRaw) {

        String strProb = strProbRaw.toLowerCase().trim().replace("zero", "0");
        int lastIndexOfAsterisk = strProb.lastIndexOf("*");

        if (lastIndexOfAsterisk != -1) {
            strProb = strProb.substring(lastIndexOfAsterisk + 1).trim();
        }

        Float probability = extractProbability(strProb, "%");

        if (probability != null) {
            return probability;
        }

        probability = extractProbability(strProb, " ");

        if (probability != null) {
            return probability;
        }

        // If no probability, it will be interpreted as abstention
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

    private List<ExcuseGenerator> generateExcuseGenerators(List<AbstractAgent> listRemainingAgents) throws IOException {

        List<ExcuseGenerator> listExcuseGenerator = new ArrayList<>();

        for (AbstractAgent agent : listRemainingAgents) {

            ExcuseGenerator excuseGenerator = new ExcuseGenerator(agent);
            listExcuseGenerator.add(excuseGenerator);
        }

        return listExcuseGenerator;
    }

    private void log(String title, List<String> texts) {

        String text = String.join("\n\n", texts);
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
