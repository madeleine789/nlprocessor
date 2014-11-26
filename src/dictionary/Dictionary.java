package dictionary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mms on 2014-11-25.
 */
public class Dictionary extends HashMap<String,HashSet<String>>
{

    public HashSet<String> getWords(String word)
    {
        return this.get(word);
    }

}
