package org.leviatanplatform.llmagents.engine.util;

import java.util.Date;

public class TicToc {

    private Date start;
    private String title;

    public TicToc(String title) {
        this.start = new Date();
        this.title = title;
    }

    public void toc() {
        Date now = new Date();
        long millis = now.getTime() - start.getTime();
        long secs = millis / 1000;
        System.out.println(title + " secs = " + secs);
    }
}
