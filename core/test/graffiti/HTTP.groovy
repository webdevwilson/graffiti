package graffiti

import com.google.common.io.ByteStreams
import java.net.HttpURLConnection
import java.net.URL

class HTTP {

    static final String BASE = 'http://localhost:8080'
    
    def get(url) {
        return get(url, [:])
    }
    
    def get(url, params) {
        return request('GET', url, params)
    }
    
    def request(method, path, params) {
        
        def url = new URL(BASE + path)
        HttpURLConnection conn = (HttpURLConnection)url.openConnection()
        
        conn.requestMethod = method
        conn.allowUserInteraction = false
        
        return new String(ByteStreams.toByteArray( conn.inputStream ))
        
    }
}