class IndexOf {

    private String subString
    private String string

    static IndexOf indexOf(String subString) {
        new IndexOf(subString: subString)
    }

    static void enhanceClass() {
        String.metaClass.myIndexOf << { indexOf(it).inString delegate as String }
    }

    int inString(String string) {
        this.string = string

        def foundIndex = (0..<string.size()).find { stringIndex ->
            matchAt(stringIndex)
        }

        foundIndex != null ? foundIndex : -1
    }

    private boolean matchAt(stringIndex) {
        (0..<subString.size()).every { subStringIndex ->
            subString[subStringIndex] == string[stringIndex + subStringIndex]
        }
    }

}
