package org.leviatanplatform.llmagents;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class LLM {

    private static final String DEFAULT_MODEL = "ai/llama3.1:8B-Q4_K_M";

    private final String model;

    public LLM() {
        this(DEFAULT_MODEL);
    }

    public LLM(String model) {
        this.model = model;
    }

    public String call(String inputText) throws IOException {

        String[] arrayCommand = new String[] {"docker", "model", "run", this.model, inputText};
        Process process = Runtime.getRuntime().exec(arrayCommand);
        byte[] processOutput = getOutputBytesProcess(process);
        return new String(processOutput);
    }

    private byte[] getOutputBytesProcess(Process process) throws IOException {

        InputStream is = process.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        while (process.isAlive()) {

            if (is.available() > 0) {
                baos.write(is.read());
            }
        }

        return baos.toByteArray();
    }
}
