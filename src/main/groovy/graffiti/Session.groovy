package graffiti

import javax.servlet.http.HttpSession

class Session {

    @Delegate
    def HttpSession session

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

