package org.leviatanplatform.llmagents.engine.orchestrators;

import java.io.IOException;

class WerewolfGameOrchestratorTest {


    public static void main(String[] args) throws IOException {

        WerewolfGameOrchestrator werewolfGameOrchestrator = new WerewolfGameOrchestrator();
        String winner = werewolfGameOrchestrator.executeGame();

        System.out.println("WINNER!!!!! " + winner);
    }

    // FIXME finish
}