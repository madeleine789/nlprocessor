package test;

import finder.LexemeFinder;
import org.junit.Test;
import static org.junit.Assert.*;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by mms on 2014-12-01.
 */
public class LexemeFinderTest
{
    @Test
    public void testSearchForLexemes()
    {
        LexemeFinder lf = new LexemeFinder("kotki");
        Set<String > expected = new HashSet<>(Arrays.asList("kotka", "kotek"));
        Set<String> result = lf.searchForLexemes();
        assertEquals(expected,result);

        lf = new LexemeFinder("asdfghjkl");
        expected = Collections.emptySet();
        result = lf.searchForLexemes();
        assertEquals(expected,result);
    }

    @Test
    public void testStartSearch()
    {
        LexemeFinder lf = new LexemeFinder("kotki");
        boolean expected = false;
        boolean result = lf.searchForLexemes().isEmpty();
        assertEquals(expected, result);

        lf = new LexemeFinder("asdfghjkl");
        expected = true;
        result = lf.searchForLexemes().isEmpty();
        assertEquals(expected,result);
    }
}
