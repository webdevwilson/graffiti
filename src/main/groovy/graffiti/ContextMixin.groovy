package graffiti

public class ContextMixin {

    def getApplication() {
        return AppContext.application
    }

    def getRequest() {
        return AppContext.getRequest()
    }

    def getResponse() {
        return AppContext.getResponse()
    }

    def getSession() {
        return new Session( AppContext.getRequest().getSession(true) )
    }

    def getParameters() {
        return AppContext.getParameters()
    }
}