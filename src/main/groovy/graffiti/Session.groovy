package graffiti

class Session {

    @Delegate
    def session

    Session(session) {
        this.session = session
    }

    def getProperty(String name) {
        getAttribute(name)
    }

    void setProperty(String name, value) {
        setAttribute(name, value)
    }


}

