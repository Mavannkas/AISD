package ludwiniak.wiktor.Lab.L12;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class KMPMatcher implements IStringMatcher {
    @Override
    public List<Integer> validShifts(String textToSearch, String patternToFind) {
        Map<Integer, Integer> prefixes = generatePrefixes(patternToFind);
        return findMatches(textToSearch, patternToFind, prefixes);
    }

    private List<Integer> findMatches(String textToSearch, String patternToFind, Map<Integer, Integer> prefixes) {
        List<Integer> matches = new LinkedList<>();
        int currentState = 0;
        for (int i = 0; i < textToSearch.length(); i++) {
            currentState = goToNextState(currentState, textToSearch.charAt(i), patternToFind, prefixes);

            if (patternToFind.charAt(currentState + 1) == textToSearch.charAt(i)) {
                currentState++;
            }

            if (currentState == patternToFind.length() - 1) {
                matches.add(i - patternToFind.length() + 1);
                currentState = prefixes.get(currentState);
            }
        }

        return matches;
    }

    private int goToNextState(int currentState, char charAt, String patternToFind, Map<Integer, Integer> prefixes) {
        while (currentState > 0 && patternToFind.charAt(currentState + 1) != charAt) {
            currentState = prefixes.get(currentState);
        }
        return currentState;
    }

    private Map<Integer, Integer> generatePrefixes(String patternToFind) {
        Map<Integer, Integer> prefixes = new HashMap<>();
        int k = 0;
        prefixes.put(0, 0);

        for (int q = 1; q < patternToFind.length(); q++) {
            k = findNextK(q, patternToFind, prefixes, k);
            prefixes.put(q, k);
        }

        return prefixes;
    }

    private int findNextK(int q, String patternToFind, Map<Integer, Integer> prefixes, int k) {
        while (k > 0 && patternToFind.charAt(k + 1) != patternToFind.charAt(q)) {
            k = prefixes.get(k);
        }

        if (patternToFind.charAt(k) == patternToFind.charAt(q)) {
            k++;
        }

        return k;
    }
}
