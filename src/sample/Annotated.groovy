#!/usr/bin/env groovy
import graffiti.*

@Grab('com.goodercode:graffiti:1.0-SNAPSHOT')
@Get('/')
def root() {
    'this is the root of the app'
}

@Get('/form')
def form() {
    "this is the form and this is the message ${session.message}"
}

@Get('/list')
def list() {
    session.message = 'blah'
    "the a parameter is '${parameters['value']}'"
}

@Post('/save')
def save() {
    'saved it'
}

Graffiti.root 'public'
Graffiti.serve '*.css'
Graffiti.serve this