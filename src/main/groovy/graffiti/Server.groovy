package graffiti

class Server {

    def jetty

    def webAppContext

    def dynamicServlets = [:]

    Server(config) {
        
        jetty = new org.mortbay.jetty.Server(config.port as Integer)
        webAppContext = new org.mortbay.jetty.webapp.WebAppContext()

        // session setup
        def sessionManager = new org.mortbay.jetty.servlet.HashSessionManager()
        webAppContext.sessionHandler = new org.mortbay.jetty.servlet.SessionHandler(sessionManager)
        
        jetty.addHandler(webAppContext)

        loadConfig(config)

        start()
    }

    void loadConfig(config) {

        webAppContext.extraClasspath = config.classpath.flatten().join(File.pathSeparator)
        webAppContext.welcomeFiles = config.welcomeFiles.flatten()

        setRoot(config.root)

        config.mappings.each { map it }

        config.resources.each { name, value ->
            resource = new org.mortbay.jetty.plus.naming.Resource(name, value)
        }

//        <New id="DSTest" class="org.mortbay.jetty.plus.naming.Resource">
//    <Arg></Arg>
//    <Arg>jdbc/DSTest</Arg>
//    <Arg>
//     <New class="org.apache.commons.dbcp.BasicDataSource">
//                 <Set name="driverClassName">org.some.Driver</Set>
//                 <Set name="url">jdbc.url</Set>
//                 <Set name="username">jdbc.user</Set>
//                 <Set name="password">jdbc.pass</Set>
//     </New>
//    </Arg>
//   </New>
    }

    void map(mapping) {

        def path = mapping.path

        // handle GET /path { doSomething() } mappings
        if( mapping.method && path && mapping.block ) {
            def servlet = dynamicServlets[path]
            if( !servlet ) {
                servlet = new GraffitiServlet()
                map(['path': path, 'servlet': servlet])
                dynamicServlets[path] = servlet
            }
            servlet[mapping.method] = mapping.block
            return
        }

        // handle /path -> SomeServlet class mappings
        if( mapping.path && mapping.servlet ) {

            def servlet = mapping.servlet
            if( servlet instanceof javax.servlet.Servlet ) {
                servlet = new org.mortbay.jetty.servlet.ServletHolder(servlet)
            }

            println 'adding servlet ' + servlet + ' to ' + path
            webAppContext.addServlet(servlet, path)
            return
        }
        
    }

    def setRoot(String root) {
        webAppContext.resourceBase = root
    }

    void start() {
        started = true
        jetty.start()
    }

    void stop() {
        jetty.stop()
    }
}