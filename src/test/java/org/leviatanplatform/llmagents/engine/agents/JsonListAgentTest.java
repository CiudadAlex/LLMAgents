package org.leviatanplatform.llmagents.engine.agents;

import java.io.IOException;

class JsonListAgentTest {

    public static void main(String[] args) throws IOException {

        JsonListAgent jsonListAgent = new JsonListAgent();
        askAndPrintResponse(jsonListAgent,"create a list of interesting topics about physics");
    }

    private static void askAndPrintResponse(JsonListAgent jsonListAgent, String inputText) throws IOException {

        String response = jsonListAgent.call(inputText);

        System.out.println("#########################################################");
        System.out.println(response);
        System.out.println("#########################################################");
    }
}