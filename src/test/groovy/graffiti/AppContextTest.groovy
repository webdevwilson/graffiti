package graffiti

import org.junit.Before
import org.junit.Ignore
import org.junit.Test

@Ignore
class AppContextTest extends GroovyTestCase {

    def request, response

    @Before
    def setup() {
        def request = new org.springframework.mock.web.MockHttpServletRequest()
        def response = new org.springframework.mock.web.MockHttpServletResponse()
    }

    @Test
    def setup_builds_the_current_request_context() {
        AppContext.setup(request, response)
        assertEquals( AppContext.request, request )
        assertEquals( AppContext.response, response )
        assertTrue( AppContext.params.size == 0 )
    }


}

