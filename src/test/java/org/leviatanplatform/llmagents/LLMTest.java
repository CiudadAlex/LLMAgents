package org.leviatanplatform.llmagents;

import java.io.IOException;

public class LLMTest {

    public static void main(String[] args) throws IOException {

        LLM llm = new LLM();
        String response = llm.call("what is a black hole?");

        System.out.println("#########################################################");
        System.out.println(response);
    }
}
