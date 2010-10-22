package graffiti

public class Graffiti {

    static server

    static isStarted = false

    public static final welcomeFiles = []

    public static final classpath = []

    static config = [ 'port': '8080',
                      'root': 'public',
                      'datasources': [:],
                      'classpath': classpath,
                      'servlets': [],
                      'mappings': [],
                      'welcomeFiles': []]
    
    public static serve(obj) {
        obj.metaClass { mixin ContextMixin }
        obj.metaClass.methods.each { method ->
            if( method.class == org.codehaus.groovy.reflection.CachedMethod ) {

                def get = method.cachedMethod.getAnnotation(Get)
                if( get ) {
                    Graffiti.get( get.value(), { method.invoke(obj) } )
                }

                def post = method.cachedMethod.getAnnotation(Post)
                if( post ) {
                    Graffiti.post( post.value(), { method.invoke(obj) } )
                }

                def dataSource = method.cachedMethod.getAnnotation(DataSource)
                if( dataSource ) {
                    config.datasources[dataSource.value()] = method.invoke(obj)
                }
            }
        }

    }

    public static serve(String path, servlet = org.mortbay.jetty.servlet.DefaultServlet, configBlock = null) {
        config.mappings << ['path': path, 'servlet': servlet, 'configBlock': configBlock]
    }
    
    public static root(String path) {
        config.root = path
    }

    public static get(path, block) {
        register('get', path, block)
    }

    public static post(path, block) {
        register('post', path, block)
    }

    public static start() {
        if( !isStarted ) {
            // initialize the server
            server = new Server(config)
            server.start()
        }
    }

    private static register(method, path, block) {
        def mapping = [ 'method': method, 'path': path, 'block': block ]
        if(! isStarted ) {
            config.mappings << mapping
        } else {
            server.map(mapping)
        }
    }
}