package edu.skon.trasecman

import spock.lang.Specification


class ResourcesAccessTest extends Specification {

    def setupSpec() {
        if (System.getSecurityManager() == null) {
            System.securityManager = new SecurityManager()
        }
    }

    def "test url connection"() {
        when:
        def url = new URL("http://google.com")

        then:
        url.openConnection()
        assert url.text != null
    }


}
