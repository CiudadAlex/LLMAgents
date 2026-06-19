package org.leviatanplatform.llmagents.engine.parent;

import java.io.IOException;

public interface TextCallable {

    String call(String inputText) throws IOException;
}
