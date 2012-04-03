#!/usr/bin/env groovy
@Grab('com.goodercode:graffiti:1.0-SNAPSHOT')
@Grab('commons-httpclient:commons-httpclient:3.1')

import graffiti.*
import org.apache.commons.httpclient.*
import org.apache.commons.httpclient.methods.*


@Get('/blah')
def get() {
    println 'get'
}

@Post('/blah')
def post() {
    println 'post'
}

@Put('/blah')
def put() {
    println 'put'
}

@Delete('/blah')
def delete() {
    println 'delete'
}

Graffiti.serve this
Graffiti.start()

client = new HttpClient();

get = new GetMethod("http://localhost:8080/blah")
post = new PostMethod("http://localhost:8080/blah")
put = new PutMethod("http://localhost:8080/blah")
delete = new DeleteMethod("http://localhost:8080/blah")

client.executeMethod(get)
get.releaseConnection()

client.executeMethod(post)
post.releaseConnection()

client.executeMethod(put)
put.releaseConnection()

client.executeMethod(delete)
delete.releaseConnection()

Graffiti.server.stop()
