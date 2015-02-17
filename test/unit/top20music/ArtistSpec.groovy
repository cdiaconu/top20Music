
package top20music

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Artist)
class ArtistSpec extends Specification {

	def "test_first_name_not_blank"() {
		setup:
		mockForConstraintsTests(Artist)

		when:
		def artist = new Artist(firstName : "", lastName : "A")
		artist.validate()

		then:
		assertTrue artist.errors.hasFieldErrors("firstName")
	}
}
