package graffiti

import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

@Ignore
class GraffitiTest {

    private static defaultConfig

    @Before
    void setup() {
        defaultConfig = Graffiti.config
    }

    @After
    void reset() {
        Graffiti.config = defaultConfig
    }

    @Test
    void get_registers_mappings() {

        def theBlock = { println 'some block' }
        Graffiti.get('url', theBlock)
        
        def anotherBlock = { println 'another block' }
        Graffiti.get('url', anotherBlock)

        assertThat( Graffiti.config.mappings.size, equalTo( 2 ) )

        assertThat( Graffiti.config.mappings.first.method, equalTo( 'get' ) )
        assertThat( Graffiti.config.mappings.first.url, equalTo( 'url' ) )
        assertThat( Graffiti.config.mappings.first.block, equalTo( theBlock ) )

        assertThat( Graffiti.config.mappings[1].method, equalTo( 'get' ) )
        assertThat( Graffiti.config.mappings[1].url, equalTo( 'url' ) )
        assertThat( Graffiti.config.mappings[1].block, equalTo( anotherBlock ) )
    }
    
    @Test
    void post_registers_mappings() {

        def theBlock = { println 'some block' }
        Graffiti.post('url', theBlock)

        def anotherBlock = { println 'another block' }
        Graffiti.post('url', anotherBlock)

        assertThat( Graffiti.config.mappings.size, equalTo( 2 ) )

        assertThat( Graffiti.config.mappings.first.method, equalTo( 'post' ) )
        assertThat( Graffiti.config.mappings.first.url, equalTo( 'url' ) )
        assertThat( Graffiti.config.mappings.first.block, equalTo( theBlock ) )

        assertThat( Graffiti.config.mappings[1].method, equalTo( 'post' ) )
        assertThat( Graffiti.config.mappings[1].url, equalTo( 'url' ) )
        assertThat( Graffiti.config.mappings[1].block, equalTo( anotherBlock ) )
    }
}

