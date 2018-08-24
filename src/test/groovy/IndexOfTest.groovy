import spock.lang.Specification

class IndexOfTest extends Specification {
    def "indexOf"() {
        IndexOf.enhanceClass()

        expect:
        string.myIndexOf(subString) == string.indexOf(subString)

        where:
        subString | string
        'a'       | 'a'
        'a'       | 'ba'
        'a'       | 'b'
        'ab'      | 'b'
        'ab'      | 'ab'
        'ab'      | 'aab'
        'aaaab'   | 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaab'
        'aa'      | 'aaa'
        'aaa'     | 'aaa'
    }
}
