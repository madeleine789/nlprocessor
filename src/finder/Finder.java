package finder;

import dictionary.Dictionary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mms on 2014-11-26.
 */
public class Finder
{
    protected static Dictionary dictionary;
    protected String word = null;
    protected  static Set<String> results = new HashSet<>();

    public Finder(String word, Dictionary dictionary)
    {
        this.dictionary = dictionary;
        this.word = word;
        find(this.word);
    }

    private static void find(String searched)
    {
        if (dictionary.findWords(searched).isEmpty())
            return;
        else
            results.addAll(dictionary.findWords(searched));
    }

    public Set<String> getResults()
    {
        if (!results.isEmpty())
            return this.results;
        else
        {
            results.add(word);
            return results;
        }
    }



}
