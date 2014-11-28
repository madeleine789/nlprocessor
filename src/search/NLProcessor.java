package search;

import com.google.common.collect.Multimap;
import dictionary.Dict;
import dictionary.DictionaryLoader;

import java.io.IOException;
import java.util.*;

/**
 * Created by M on 2014-11-03.
 */
public class NLProcessor
{
    //private static search.NLProcessor NLProcessorInstance = null;

    protected static Map<Dict, Multimap<String,HashSet<String>>> dictionaries = new HashMap<>();


    protected NLProcessor() throws Exception
    {
        //loadDictionaries();
    }

    private void loadDictionaries() throws IOException, InterruptedException {
        DictionaryLoader dictionaryLoader = new DictionaryLoader();
        Thread thread = new Thread(dictionaryLoader);
        thread.start();
        thread.join();
        dictionaries = dictionaryLoader.getDictionaries();

    }

    public Set<String> findWords(String word, WordVariant wordVariant)
    {
        Set<String> result = new HashSet<>();
        SearchEngine searchEngine = new SearchEngine(word, dictionaries, wordVariant);
        if (!searchEngine.startSearch())
            result.add(word);
        else
            result = searchEngine.search(word);

        return result;
    }

    public static void main(String[] args) throws Exception
    {

        NLProcessor nlp = new NLProcessor();
        nlp.loadDictionaries();
        //System.out.println(nlp.findWords("robił", new WordVariant(true, true, false)));
        //System.out.println(nlp.findWords("chłopak", new WordVariant(true, true, false)));

        //Multimap<String,HashSet<String>> d1 = dictionaries.get(Dict.INFLECTIONS);
        //Multimap<String,HashSet<String>> d2 = dictionaries.get(Dict.SYNONYMS);

        //System.out.println(d1.get("robić"));
        //System.out.println(d2.get("blok"));

        //System.out.println(nlp.findWords("robił", new WordVariant(true, true, false)));

    }


}
