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
        int ret = -1
        int index = 0

        string.any { character ->
            matchers << new Matcher(index)
            assert matchers.size() <= subString.size()
            def matched = matchers.find { it.next(character, index) }
            matchers = matchers.findAll { it.stillHope }
            index++
            ret = matched != null ? matched.index : -1
            ret > -1
        }

        ret
    }

    private class Matcher {

        private final index
        private boolean stillHope = true

        Matcher(int index) { this.index = index }

        boolean next(String character, int atIndex) {
            int subStringIndex = atIndex - index
            stillHope = stillHope &&
                    subString.charAt(subStringIndex) == character as char
            stillHope && subStringIndex == subString.size() - 1
        }

    }

}
