package org.leviatanplatform.llmagents.engine.agents;

import org.leviatanplatform.llmagents.engine.domain.NameAndDescription;

import java.io.IOException;
import java.util.List;

class JsonListAgentTest {

    public static void main(String[] args) throws IOException {

        JsonListAgent jsonListAgent = new JsonListAgent();
        askAndPrintResponse(jsonListAgent,"create a list of interesting topics about physics");
    }

    private static void askAndPrintResponse(JsonListAgent jsonListAgent, String inputText) throws IOException {

        List<NameAndDescription> response = jsonListAgent.callAndRetrieveList(inputText);

        System.out.println("#########################################################");

        for (NameAndDescription item : response) {
            System.out.println(item);
        }

        System.out.println("#########################################################");
    }
}