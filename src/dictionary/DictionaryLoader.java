package dictionary; /**
 * Created by M on 2014-11-21.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DictionaryLoader implements Runnable {

    //private File file;
    private static String[] files = {"synonyms.txt", "inflections.txt"};    // , "diminutives.txt"};
    protected static dictionary.Dictionary dictionary;
    protected static Map<Dict, dictionary.Dictionary> dictionaries  = new HashMap<>();

    public DictionaryLoader()
    {
        this.dictionary = new dictionary.Dictionary();
    }

    private static class LineWorker implements Runnable
    {
        private final String line;
        private static dictionary.Dictionary dictionary;

        public LineWorker(String line, dictionary.Dictionary dictionary)
        {
            this.line = line;
            this.dictionary = dictionary;
        }

        @Override
        public void run()
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
    }

    private dictionary.Dictionary loadDictionary(File file) throws IOException {

        long start = System.nanoTime();
        dictionary.Dictionary dictionary = new dictionary.Dictionary();

        ExecutorService service = Executors.newFixedThreadPool(210);      //random 5 to 15 secs for inflections , max 0.6 secs for synonyms
        //ExecutorService service = Executors.newCachedThreadPool();          //always around 6-8 secs for inflections
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file)))
        {
            for (String line; (line = bufferedReader.readLine())!=null;)
            {
                service.execute(new LineWorker(line, dictionary));
            }
        }

        service.shutdown();
        long stop  = System.nanoTime();
        System.out.println(file.getName() + " " + (stop - start)/1000000 + "ms");
        return dictionary;
    }

    private static Map<Dict, dictionary.Dictionary>  loadDictionaries() throws IOException {
        for(String file: files)
        {
            DictionaryLoader dictLoader = new DictionaryLoader();
            dictionary.Dictionary dict = dictLoader.loadDictionary(new File(file));
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
        try
        {
            loadDictionaries();

        } catch (IOException e) {}
    }

    public static Map<Dict, dictionary.Dictionary> getDictionaries()
    {
        return dictionaries;
    }

    public static String[] getFiles()
    {
        return files;
    }

    public  static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        loadDictionaries();
        long stop  = System.nanoTime();
        System.out.println("TOTAL: " + (stop - start)/1000000 + "ms");
    }
}
