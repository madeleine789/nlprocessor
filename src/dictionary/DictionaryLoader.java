package dictionary; /**
 * Created by M on 2014-11-21.
 */

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class DictionaryLoader implements Runnable {

    private static String[] files = {"synonyms.txt", "inflections.txt"};    // , "diminutives.txt"};
    protected static Multimap<String,HashSet<String>> dictionary;
    protected static Map<Dict, Multimap<String,HashSet<String>>> dictionaries  = new HashMap<>();


    public DictionaryLoader()
    {
        this.dictionary = ArrayListMultimap.create();
    }


    private Multimap<String,HashSet<String>> loadDictionary(String file) throws Exception {

        long start = System.nanoTime();
        Multimap<String,HashSet<String>> dictionary = ArrayListMultimap.create();

        try {

            List<String> lines = Files.readAllLines(Paths.get("resources", file));

            for(String line : lines)
            {

                if (line.contains(",")){

                    String[] partition = line.split(",\\s");
                    String key = partition[0];

                    HashSet<String> values = new HashSet<>();
                    for (int i = 1; i < partition.length; i++)
                        values.add(partition[i]);

                    dictionary.put(key, values);

                }
                else
                    dictionary.put(line, new HashSet<>());
            }

        } catch (IOException e){
            e.printStackTrace();
        }


        long stop  = System.nanoTime();
        System.out.println(file + "\ttime: " + (stop - start)/1000000 + "m\t size: " + dictionary.size());
        return dictionary;
    }

    private synchronized static Map<Dict, Multimap<String,HashSet<String>>>  loadDictionaries() throws Exception {
        for(String file: files)
        {
            DictionaryLoader dictLoader = new DictionaryLoader();
            Multimap<String,HashSet<String>> dict = dictLoader.loadDictionary(file);
            if (file == "synonyms.txt")
                dictionaries.put(Dict.SYNONYMS , dict);
            else if (file == "inflections.txt")
                dictionaries.put(Dict.INFLECTIONS , dict);
            else if (file == "diminutives.txt")
                dictionaries.put(Dict.DIMINUTIVES , dict);
        }

        return dictionaries;
    }

    public void run()
    {
        try {
            loadDictionaries();

        } catch (Exception e) {}
    }

    public static Map<Dict, Multimap<String,HashSet<String>>> getDictionaries()
    {
        return dictionaries;
    }

    public static String[] getFiles()
    {
        return files;
    }

    public  static void main(String[] args) throws Exception {
        long start = System.nanoTime();
        loadDictionaries();
        long stop  = System.nanoTime();
        System.out.println("TOTAL: " + (stop - start)/1000000 + "ms");
    }
}
