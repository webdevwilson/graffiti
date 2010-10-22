#!/usr/bin/env groovy
import graffiti.*

@Grapes([
    @Grab('com.goodercode:graffiti:1.0-SNAPSHOT'),
    @Grab('mysql:mysql-connector-java:5.1.13')
])
@Resource('jdbc/database')
def getDataSource() {
    datasource = new com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource()
    datasource.user="user"
    datasource.password="password"
    datasource.url = "jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=Cp1251"
}

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
    "the a parameter is '${params['value']}'"
}

@Post('/save')
def save() {
    'saved it'
}

Graffiti.classpath << 'resin.jar'

Graffiti.serve '*.php', 'com.caucho.quercus.servlet.QuercusServlet'

Graffiti.root 'public'
Graffiti.serve '*.css'
Graffiti.serve this

Graffiti.start()