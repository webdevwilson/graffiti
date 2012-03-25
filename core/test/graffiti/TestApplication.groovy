package graffiti

class TestApplication {
	
    @Get('/status')
    def status() {
                'OK'
    }
    
    def start() {
        Graffiti.serve this
        Graffiti.start()
    }
    
}

