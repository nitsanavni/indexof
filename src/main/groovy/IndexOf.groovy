class IndexOf {

    private String subString
    private List<Matcher> matchers = []

    static IndexOf indexOf(String subString) {
        new IndexOf(subString: subString)
    }

    static void enhanceClass() {
        String.metaClass.myIndexOf << { subString ->
            indexOf(subString).inString(delegate as String)
        }
    }

    int inString(String string) {
        int index = 0
        Matcher matched

        string.any { character ->
            matchers << new Matcher(index)
            assert matchers.size() <= subString.size()
            matched = matchers.find { it.next(character, index) }
            matchers = matchers.findAll { it.stillHope }
            index++
            matched
        }

        matched ? matched.initialIndex : -1
    }

    private class Matcher {

        private final initialIndex
        private boolean stillHope = true

        Matcher(int index) { this.initialIndex = index }

        boolean next(String character, int atIndex) {
            stillHope = stillHope && stillMatchToSubString(character, atIndex)
            boolean match = stillHope && reachedEndOfSubString(atIndex)
            match
        }

        private boolean reachedEndOfSubString(int atIndex) {
            subStringIndex(atIndex) == subString.size() - 1
        }

        private boolean stillMatchToSubString(String character, int atIndex) {
            subString.charAt(subStringIndex(atIndex)) == character as char
        }

        private int subStringIndex(int currentIndex) { currentIndex - initialIndex }

    }

}
