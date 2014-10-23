package exercise3;

import common.StringUtils;
import common.html.GazetaHtmlDocument;
import common.html.HtmlDocument;
import java.util.concurrent.Callable;

public class WordCounter implements Callable<Integer> {

    private final String documentUrl;
    private final String wordToCount;

    public WordCounter(String documentUrl, String wordToCount) {
        this.documentUrl = documentUrl;
        this.wordToCount = wordToCount;
    }

    public Integer call() throws Exception {
        HtmlDocument html = new GazetaHtmlDocument(this.documentUrl);
        String content = html.getContent().toLowerCase();
        return StringUtils.countOccurrences(content, this.wordToCount);
    }
}
