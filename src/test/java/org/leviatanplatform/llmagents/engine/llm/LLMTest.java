package org.leviatanplatform.llmagents.engine.llm;

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

/*
Example JSON response:

```json
[
  {
    "name": "Quantum Entanglement",
    "description": "A phenomenon where two or more particles become connected in such a way that their properties are correlated, regardless of the distance between them."
  },
  {
    "name": "Wormholes",
    "description": "Theoretical passages through space-time that could potentially connect two distant points in the universe, allowing for faster-than-light travel."
  },
  {
    "name": "Dark Matter",
    "description": "A type of matter that does not emit, absorb, or reflect any electromagnetic radiation, making it invisible to our telescopes, yet its presence can be felt through its gravitational effects."
  },
  {
    "name": "Black Holes",
    "description": "Regions in space where the gravitational pull is so strong that nothing, including light, can escape, creating a boundary called the event horizon."
  },
  {
    "name": "Gravitational Waves",
    "description": "Ripples in the fabric of space-time that are produced by massive cosmic events, such as the collision of two black holes, which were predicted by Einstein's theory of general relativity."
  },
  {
    "name": "Time Dilation",
    "description": "The phenomenon where time appears to pass slower for an observer in motion relative to a stationary observer, due to the effects of special relativity."
  },
  {
    "name": "Quantum Fluctuations",
    "description": "Temporary and random changes in energy that occur at the quantum level, which can create particles and antiparticles out of the vacuum."
  },
  {
    "name": "The Multiverse",
    "description": "A hypothetical concept that proposes the existence of multiple universes beyond our own, each with its own unique set of physical laws and properties."
  },
  {
    "name": "Exotic Matter",
    "description": "A type of matter that has negative energy density, which could potentially be used to create stable wormholes or to power advanced propulsion systems."
  },
  {
    "name": "The Higgs Field",
    "description": "A fundamental field in the universe that gives mass to fundamental particles, which is responsible for the existence of mass in the universe."
  }
]
```
*/
