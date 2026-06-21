package org.leviatanplatform.llmagents.engine.llm;

import java.io.IOException;

public class LLMTest {

    public static void main(String[] args) throws IOException {

        LLM llm = new LLM();
        askAndPrintResponse(llm,"what is a black hole?");
        askAndPrintResponse(llm,"create a JSON with a list of interesting topics about physics. The JSON should be a list of objects with the field 'name' and the field 'description'. The response must only be the JSON");

        String excuse1 = "Milord, I swear on my family's honor that I'm not a monster. The villagers have been whispering about me, but I've been living a simple life, tending to my fields and helping with the harvest. I have a steady wife and children, and I'm not a wanderer. If I were to change, would I still be able to sleep through the full moon? And wouldn't my family's livestock be in danger if I were to roam the forest?";
        String excuse2 = "It's just a skin condition. I have a rare case of hypertrichosis, which causes excessive hair growth. I also have a condition called acroosteolysis, which affects the shape of my nails. And my eyes are just sensitive to light. It's nothing more than that. *accidentally lets out a low, growling noise, clears throat* Ah, just a tick";
        askAndPrintResponse(llm,"what is the probability that a werewolf says '" + excuse1 + "'? Answer just with the probability");
        askAndPrintResponse(llm,"what is the probability that a werewolf says '" + excuse2 + "'? Answer just with the probability");
    }

    private static void askAndPrintResponse(LLM llm, String inputText) throws IOException {

        String response = llm.call(inputText);

        System.out.println("#########################################################");
        System.out.println(response);
        System.out.println("#########################################################");
    }
}
