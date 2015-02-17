
package top20music

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ArtistController)
@Mock([Artist])
class ArtistControllerSpec extends Specification {

	def artistMockService;

	def setup() {
		artistMockService = mockFor(ArtistService)
		controller.artistService = artistMockService.createMock()
	}

	void test_index() {
		given:
		Artist artist = new Artist(firstName: "A", lastName: "B")
		artistMockService.demand.list() { -> return [artist]}

		when:
		controller.index()

		then:
		view == "/artist/index"
		model.artists == [artist]
	}
	
	void test_save_artist_already_exists(){
		given:
		controller.params.firstName = "A"
		controller.params.lastName = "B"
		Artist artist = new Artist(firstName: "A", lastName: "B")
		artistMockService.demand.list() { -> return [artist]}

		when:
		controller.save()

		then:
		view == "/artist/index"
		model.artists == [artist]
	}
	
	void test_save_success(){
		given:
		controller.params.firstName = "A"
		controller.params.lastName = "B"
		Artist artist = new Artist(firstName: "testA", lastName: "testB")
		artistMockService.demand.list(1..2) { -> return [artist]}

		when:
		controller.save()

		then:
		view == "/artist/index" 
		// validate returning model contains 2 artists
	}
}
