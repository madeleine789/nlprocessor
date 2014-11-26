package finder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by M on 2014-10-26.
 */
public interface IFinder
{
    static String word = null;
    List<String> results = new ArrayList<String>();

    public Set<String> find(String searched);

    public void addToResults(String word);

    public void run();

}
