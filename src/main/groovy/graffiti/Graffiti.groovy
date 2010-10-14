package graffiti

public class Graffiti {

    static server, root, paths = []
    
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
            }
        }
    }

    public static serve(String path) {
        if( !server ) {
            paths << path
        } else {
            server.mapStatic(path)
        }
    }

    public static root(String path) {
        if(! server ) {
            root = path
        } else {
            root = null
            server.root = path
        }
    }

    public static get(url, block) {
        register('get', url, block)
    }

    public static post(url, block) {
        register('post', url, block)
    }

    private static register(method, url, block) {
        if( !server ) {
            init()
        }
        server.map(method, url, block)
    }

    private static init() {
        // initialize the server
        server = new Server(8001)
        server.start()
        if( root ) {
            server.root = root
            root = null
        }
        if( paths.size > 0 ) {
            paths.each { server.mapStatic(it) }
            paths = []
        }
    }

}