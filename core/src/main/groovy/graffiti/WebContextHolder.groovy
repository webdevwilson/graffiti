package graffiti

/**
 * WebContextHolder is a singleton to allow for dynamic properties (getProperty, setProperty)
 */
class WebContextHolder {

    static application = [:]

    static final instance = new WebContextHolder()

    private static contextHolder = new ThreadLocal()

    def setup(theRequest, theResponse) {

        contextHolder.set([:])

        request = theRequest
        response = theResponse
        
        // create parameter map
        params = [:]
        theRequest.parameterMap.each { k, v ->
            if( v.length == 1 ) {
                params[k] = v[0]
            } else {
                params[k] = v
            }
        }
        
    }

    def cleanup() {
        contextHolder.set(null)
    }

    def getProperty(String name) {
        contextHolder.get()[name]
    }

    void setProperty(String name, value) {
        contextHolder.get()[name] = value
    }

}

