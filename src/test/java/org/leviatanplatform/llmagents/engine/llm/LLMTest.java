package org.leviatanplatform.llmagents.engine.llm;

import org.leviatanplatform.llmagents.engine.llm.LLM;

import java.io.IOException;

public class LLMTest {

    public static void main(String[] args) throws IOException {

        LLM llm = new LLM();
        askAndPrintResponse(llm,"what is a black hole?");
        askAndPrintResponse(llm,"create a JSON with a list of interesting topics about physics. The JSON should be a list of objects with the field 'name' and the field 'description'. The response must only be the JSON");
    }

    private static void askAndPrintResponse(LLM llm, String inputText) throws IOException {

        String response = llm.call(inputText);

        System.out.println("#########################################################");
        System.out.println(response);
        System.out.println("#########################################################");
    }
}
