package dictionary;

import com.google.common.collect.Multimap;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by mms on 2015-01-12.
 */
public class SynonymsCorrector {

    protected  Multimap<String,HashSet<String>> synonyms;
    protected  Map<Dict, Multimap<String,HashSet<String>>> dictionaries  = new HashMap<>();
    protected static DictionaryLoader dictionaryLoader = new DictionaryLoader();

    public SynonymsCorrector()
    {
        synchronized (this){
            Thread thread = new Thread(dictionaryLoader);
            thread.start();
        }



    }

    public void getDict(DictionaryLoader dictionaryLoader)
    {
        this.dictionaries = dictionaryLoader.getDictionaries();
        this.synonyms = dictionaries.get(Dict.SYNONYMS);
    }

    public void removeDuplicates()
    {

        System.out.println("BEFORE REMOVAL: " + this.dictionaries.size());

        for (String key: this.synonyms.keySet())
        {
            if (this.synonyms.containsEntry(key, Collections.emptySet()))
            {
                this.synonyms.remove(key, Collections.emptySet());
            }

        }

        System.out.println("AFTER REMOVAL: " + this.dictionaries.size());

    }

    public void symmetrizeSynonyms()
    {
        System.out.println("BEFORE SYMMETRIZATION: " + this.dictionaries.size());
        for (String key: this.synonyms.keySet())
            for (String synonym: (String[]) this.synonyms.get(key).toArray())
            {

            }
        System.out.println("AFTER SYMMETRIZATION: " + this.dictionaries.size());
    }

    public synchronized static void main(String[] args) throws Exception
    {

        SynonymsCorrector synonymsCorrector = new SynonymsCorrector();
        Thread.sleep(10000);
        synonymsCorrector.getDict(dictionaryLoader);
        System.out.println(synonymsCorrector.synonyms.size());
        Thread.sleep(10000);
        synonymsCorrector.removeDuplicates();
        //System.out.println("---------------------------------------------------");
        //synonymsCorrector.symmetrizeSynonyms();
    }




}
