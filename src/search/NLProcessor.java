package search;

import dictionary.Dict;
import dictionary.Dictionary;
import dictionary.DictionaryLoader;

import java.io.IOException;
import java.util.*;

/**
 * Created by M on 2014-11-03.
 */
public class NLProcessor
{
    //private static search.NLProcessor NLProcessorInstance = null;

    protected static Map<Dict, Dictionary> dictionaries = new HashMap<>();


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
        SearchEngine searchEngine = new SearchEngine(word, dictionaries);
        if (!searchEngine.startSearch())
            result.add(word);
        else
            result = searchEngine.search(word, wordVariant.searchForInflections(), wordVariant.searchForSynonyms(), wordVariant.searchForDiminutives());

        return result;
    }

    public static void main(String[] args) throws Exception
    {

        NLProcessor nlp = new NLProcessor();
        System.out.println(dictionaries.isEmpty());
        nlp.loadDictionaries();
        //Thread.sleep(10000);
        System.out.println(dictionaries.isEmpty());

        nlp.findWords("dom", new WordVariant(false, false, true));

    }


}
