package org.leviatanplatform.llmagents.engine.agents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.leviatanplatform.llmagents.engine.config.Constants;
import org.leviatanplatform.llmagents.engine.domain.NameAndDescription;

import java.io.IOException;
import java.util.List;

public class JsonListAgent extends AbstractAgent {

    private static final String CONTEXT = "Your responses are ALWAYS a JSON with a list of objects with the field 'name' and the field 'description'. The response must only be the JSON. The list should be about ";

    public JsonListAgent() {
        super(CONTEXT);
    }

    public JsonListAgent(String model) {
        super(model, CONTEXT);
    }

    public List<NameAndDescription> callAndRetrieveList(String inputText) throws IOException {

        String json = super.call(inputText);

        if (Constants.DEBUG) {
            System.out.println("-------------------");
            System.out.println(json);
            System.out.println("-------------------");
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            List<NameAndDescription> list = mapper.readValue(json, new TypeReference<List<NameAndDescription>>() {});
            return list;

        } catch (IOException ioe) {

            System.out.println("-------------------");
            System.out.println(json);
            System.out.println("-------------------");
            throw ioe;
        }
    }
}
