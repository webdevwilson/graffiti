#!/usr/bin/env groovy
import graffiti.*

@Grapes([
    @Grab('com.goodercode:graffiti:1.0-SNAPSHOT'),
    @Grab('mysql:mysql-connector-java:5.1.13')
])
@DataSource('mydb')
def getDataSource() {
    datasource = new com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource()
    datasource.user="root"
    datasource.password=""
    datasource.url = "jdbc:mysql://localhost:3306/test"
    return datasource
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
//    session.message = 'blah'

    javax.naming.InitialContext ic = new javax.naming.InitialContext();
    javax.sql.DataSource mydb = (javax.sql.DataSource)ic.lookup("java:comp/env/jdbc/mydb");
    println mydb
    "the a parameter is '${params['value']}'"
}

@Post('/save')
def save() {
    'saved it'
}

Graffiti.classpath << 'resin.jar'

Graffiti.serve('*.php', 'com.caucho.quercus.servlet.QuercusServlet') {
    initParameter('database', 'jdbc/mydb')
}

Graffiti.root 'public'
Graffiti.serve '*.css'
Graffiti.serve this

Graffiti.start()