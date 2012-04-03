@Grab('com.goodercode:graffiti:1.0-SNAPSHOT')
@Grab('commons-httpclient:commons-httpclient:3.1')
import graffiti.*
import org.apache.commons.httpclient.*
import org.apache.commons.httpclient.methods.*


@Get('/hello')
def get() {
    'hello world'
}

Graffiti.serve this
Graffiti.start()