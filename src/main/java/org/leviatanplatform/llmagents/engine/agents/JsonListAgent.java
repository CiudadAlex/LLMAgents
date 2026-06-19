package org.leviatanplatform.llmagents.engine.agents;

public class JsonListAgent extends AbstractAgent {

    private static final String CONTEXT = "Your responses are ALWAYS a JSON with a list of objects with the field 'name' and the field 'description'. The response must only be the JSON";

    public JsonListAgent() {
        super(CONTEXT);
    }
}
