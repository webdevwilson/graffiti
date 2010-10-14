package graffiti

class Session {

    @Delegate
    def session

    Session(session) {
        this.session = session
    }

    def getProperty(String name) {
        session.getAttribute(name)
    }

    void setProperty(String name, value) {
        session.setAttribute(name, value)
    }


}

