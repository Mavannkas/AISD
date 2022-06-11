package ludwiniak.wiktor.Lab.L12;

import java.util.*;

public class AutomatonMatcher implements IStringMatcher {
    @Override
    public List<Integer> validShifts(String textToSearch, String patternToFind) {
        Set<Character> alphabet = getAlphabet(textToSearch);
        Map<Integer, Map<Character, Integer>> transitions = buildTransitions(patternToFind, alphabet);
        return findMatches(textToSearch, transitions, patternToFind.length());
    }

    private List<Integer> findMatches(String textToSearch, Map<Integer, Map<Character, Integer>> transitions, int length) {
        List<Integer> matches = new LinkedList<>();
        int currentState = 0;
        for (int i = 0; i < textToSearch.length(); i++) {
            currentState = transitions.get(currentState).get(textToSearch.charAt(i));
            if (currentState == length) {
                matches.add(i - length + 1);
            }
        }
        return matches;
    }

    private Map<Integer, Map<Character, Integer>> buildTransitions(String patternToFind, Set<Character> alphabet) {
        int patternLength = patternToFind.length();
        Map<Integer, Map<Character, Integer>> transitions = getFilledHashMap(patternLength);

        for (int q = 0; q <= patternLength; q++) {
            setStatesForState(q, patternToFind, alphabet, transitions);
        }

        return transitions;
    }

    private void setStatesForState(int q, String patternToFind, Set<Character> alphabet, Map<Integer, Map<Character, Integer>> transitions) {
        int patternLength = patternToFind.length();

        for (char character : alphabet) {
            int k = Math.min(q + 2, patternLength + 1);
            do {
                k--;
            } while (isNotSuffix(patternToFind, character, q, k));
            transitions.get(q).put(character, k);
        }
    }

    private Map<Integer, Map<Character, Integer>> getFilledHashMap(int size) {
        Map<Integer, Map<Character, Integer>> hashMap = new HashMap<>();
        for (int i = 0; i <= size; i++) {
            hashMap.put(i, new HashMap<>());
        }

        return hashMap;
    }

    private boolean isNotSuffix(String patternToFind, char character, int q, int k) {
        String str = patternToFind.substring(0, q) + character;
        String suffix = patternToFind.substring(0, k);
        return !str.endsWith(suffix);
    }

    private Set<Character> getAlphabet(String chars) {
        Set<Character> characterSet = new HashSet<>(chars.length());

        for (char c : chars.toCharArray()) {
            characterSet.add(c);
        }

        return characterSet;
    }
}
