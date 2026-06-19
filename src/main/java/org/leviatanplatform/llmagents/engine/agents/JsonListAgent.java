package org.leviatanplatform.llmagents.engine.agents;

import org.leviatanplatform.llmagents.engine.domain.NameAndDescription;

import java.io.IOException;
import java.util.List;

public class JsonListAgent extends AbstractAgent {

    private static final String CONTEXT = "Your responses are ALWAYS a JSON with a list of objects with the field 'name' and the field 'description'. The response must only be the JSON";

    public JsonListAgent() {
        super(CONTEXT);
    }

    public List<NameAndDescription> callAndRetrieveList(String inputText) throws IOException {

        String response = super.call(inputText);

        // FIXME acabar
        return null;
    }
}
