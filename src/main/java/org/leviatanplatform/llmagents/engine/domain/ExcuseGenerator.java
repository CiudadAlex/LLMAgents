package org.leviatanplatform.llmagents.engine.domain;

import org.leviatanplatform.llmagents.engine.agents.AbstractAgent;

import java.io.IOException;

public class ExcuseGenerator {

    private static final String EXCUSE_PROMPT = "you have to convince me in just one sentence that you are not a werewolf. Keep the explanation VERY short";

    private AbstractAgent agent;
    private String excuse;

    public ExcuseGenerator(AbstractAgent agent) throws IOException {
        this.agent = agent;
        askForExcuse();
    }

    public void askForExcuse() throws IOException {
        excuse = agent.call(EXCUSE_PROMPT);
        excuse = excuse.replace("\"", "");
        excuse = excuse.replace("\n", "");
        System.out.println("excuse = " + excuse);
    }

    public String getExcuse() {
        return excuse;
    }
}
