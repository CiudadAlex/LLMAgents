package org.leviatanplatform.llmagents;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LLM {

    private final String model;

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

        while (is.available() > 0) {
            baos.write(is.read());
        }

        return baos.toByteArray();
    }
}


/*
"""
Caller for an LLM. Example call:

docker model run ai/llama3.1:8B-Q4_K_M "what is a black hole"
"""


class LLM:

    def __init__(self, model="ai/llama3.1:8B-Q4_K_M"):
        self.model = model

    def execute(self, text):
        # Execute command and capture output
        result = subprocess.run(
            ['docker', 'model', 'run', self.model, text],
            capture_output=True,
            text=True,
            encoding='utf-8'
        )

        return result.stdout
*/