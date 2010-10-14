package graffiti

class Server {

    def port

    def jetty

    def servlets = [:]

    def servletContext

    Server(port) {
        this.port = port
        jetty = new org.mortbay.jetty.Server(port)
        servletContext = new org.mortbay.jetty.servlet.Context()

        // session setup
        def sessionManager = new org.mortbay.jetty.servlet.HashSessionManager()
        servletContext.sessionHandler = new org.mortbay.jetty.servlet.SessionHandler(sessionManager)
        
        jetty.addHandler(servletContext)
    }

    void map(method, path, block) {

        def servlet = servlets[path]
        if( !servlet ) {
            servlet = new GraffitiServlet()
            servletContext.addServlet( new org.mortbay.jetty.servlet.ServletHolder(servlet), path)
        }
        servlet[method] = block
    }

    void mapStatic(path) {
        servletContext.addServlet( org.mortbay.jetty.servlet.DefaultServlet, path )
    }

    def setRoot(String root) {
        servletContext.resourceBase = root
    }

    void start() {
        jetty.start()
    }

    void stop() {
        jetty.stop()
    }
}