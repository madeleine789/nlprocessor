package search;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by M on 2014-10-25.
 */

public class ResultsCollector
{
    Set<String> finalResults = new HashSet<String>();
    String searchedWord;

    public ResultsCollector(String searched)
    {
        this.searchedWord = searched;
    }

    public void processPartialResults(Set<String> pResults)
    {
        for (String word : pResults)
            finalResults.add(word);
    }

    /*private String sendNoResults()
    {
        return searchedWord;
    }*/

    public Set<String> getFinalResults()
    {
        return finalResults;
    }

    /*public void send()
    {
        if (finalResults.isEmpty())
            sendNoResults();
        else
            sendResults();
    }*/
}
