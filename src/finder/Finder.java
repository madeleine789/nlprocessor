package finder;

import com.google.common.collect.Multimap;
import java.util.*;

/**
 * Created by mms on 2014-11-26.
 */

public class Finder
{
    protected static Multimap<String,HashSet<String>> dictionary;
    protected String word;
    protected  static Set<String> results = new HashSet<>();

    public Finder(String word, Multimap<String,HashSet<String>> dictionary)
    {
        this.dictionary = dictionary;
        this.word = word;
        find(this.word);
    }

    private static void find(String searched)
    {
        if(dictionary.containsKey(searched) && dictionary.get(searched) != null)
        {
            Collection<HashSet<String>> found = dictionary.get(searched);
            for(HashSet<String> set : found)
            {
                results.addAll(set);
            }
        }
    }

    public Set<String> getResults()
    {
        if (!results.isEmpty())
            return this.results;
        else
            return Collections.emptySet();
    }



}
