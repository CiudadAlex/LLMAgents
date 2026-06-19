package org.leviatanplatform.llmagents;

public class AgentCaller {
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