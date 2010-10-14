package graffiti

class AppContext {

    static application

    private static requestHolder = new ThreadLocal()
    
    private static responseHolder = new ThreadLocal()

    private static parameterHolder = new ThreadLocal()

    static setup(request, response) {

        requestHolder.set(request)
        responseHolder.set(response)

        // create parameter map
        def parameters = [:]
        request.parameterMap.each { k, v ->
            if( v.length == 1 ) {
                parameters[k] = v[0]
            } else {
                parameters[k] = v
            }
        }
        parameterHolder.set(parameters)
    }

    static cleanup() {
        requestHolder.set(null)
        responseHolder.set(null)
        parameterHolder.set(null)
    }

    static getRequest() {
        requestHolder.get()
    }

    static getResponse() {
        responseHolder.get()
    }

    static getParameters() {
        parameterHolder.get()
    }

}

