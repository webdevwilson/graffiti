What is it?
====================================================================================================
Graffiti is a lightweight web framework for Groovy inspired by Sinatra


Groovy Start:
====================================================================================================

import graffiti.*

// only required once
@Grab('com.goodercode:graffiti:1.0-SNAPSHOT')
@Get('/hello')
def hello() {
    'Hello World!'
}

// /hello/name?name=You
@Get('/hello/name')
def helloWhomever() {
    "Hello ${parameters[name]}"
}

// a sample post
@Post('/save')
def save() {
    'saved it'
}

// static files served from here
Graffiti.root 'public'

// we also have to setup what static files to serve
Graffiti.serve '*.css'

// required to process annotations
Graffiti.serve this


Running It:
====================================================================================================

It's super easy!

groovy $YOUR_FILE_NAME.groovy


Implicit Variables:
====================================================================================================

application - ServletContext
parameters - map of parameters
request - HttpServletRequest
response - HttpServletResponse
session - HttpSession


That's all for now, not sure if or when I will add more features.  Feel free to fork it and give it a go!

Cheers!