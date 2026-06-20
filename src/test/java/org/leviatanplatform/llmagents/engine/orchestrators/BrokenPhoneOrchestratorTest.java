package org.leviatanplatform.llmagents.engine.orchestrators;

import java.io.IOException;

class BrokenPhoneOrchestratorTest {

    public static void main(String[] args) throws IOException {

        BrokenPhoneOrchestrator brokenPhoneOrchestrator = new BrokenPhoneOrchestrator();
        askAndPrintResponse(brokenPhoneOrchestrator,"Our Solar System takes roughly 250 million years to complete a single orbit around the center of the Milky Way. " +
                "The last time Earth was in its current position around the galaxy, dinosaurs had not yet appeared." +
                "An even more remarkable detail: at the center of the Milky Way lies a supermassive black hole called Sagittarius A*, containing about 4 million times the mass of the Sun.");
    }

    private static void askAndPrintResponse(BrokenPhoneOrchestrator brokenPhoneOrchestrator, String inputText) throws IOException {
        String response = brokenPhoneOrchestrator.execute(inputText);
        System.out.println("#########################################################");
        System.out.println(inputText);
        System.out.println("#########################################################");
        System.out.println(response);
        System.out.println("#########################################################");
    }
}