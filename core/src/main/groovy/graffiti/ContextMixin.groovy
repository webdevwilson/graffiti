package graffiti

public class ContextMixin {

    def getApplication() {
        WebContextHolder.instance.application
    }

    def getRequest() {
        WebContextHolder.instance.request
    }

    def getResponse() {
        WebContextHolder.instance.response
    }

    def getSession() {
        new Session( WebContextHolder.instance.request.session )
    }

    def getParams() {
        WebContextHolder.instance.params
    }
}