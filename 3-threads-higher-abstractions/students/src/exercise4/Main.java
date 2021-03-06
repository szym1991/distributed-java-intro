package exercise4;

import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        String rootUrl = "http://wiadomosci.gazeta.pl/";
        String wordToFound = "sikorski";
        Integer numberOfWords = 0;

        // TODO: Create new ForkJoinPool object
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // TODO: Create new WebCrawlingTask for rootUrl and wordToFound
        WebCrawlingTask webCrawlingTask = new WebCrawlingTask(rootUrl, wordToFound);
        // TODO: Invoke invoke method on ForkJoinPool object passing WebCrawlingTask
        // TODO: Assign result of invoke method to numberOfWords variable
        numberOfWords = forkJoinPool.invoke(webCrawlingTask);
        
        System.out.printf("Number of words '%s': %d", wordToFound, numberOfWords);
    }
}
