package top20music

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(SongController)
@Mock([Artist])
class SongControllerSpec extends Specification {

	def songMockService;
	def artistMockService;

	def setup() {
		artistMockService = mockFor(ArtistService)
		songMockService = mockFor(SongService)
		controller.artistService = artistMockService.createMock()
		controller.songService = songMockService.createMock()
	}

	void test_index() {
		given:
		Artist artist = new Artist(firstName: "A", lastName: "B")
		Song song = new Song(name:'aSong', artist: artist);

		artistMockService.demand.getArtist(0) { -> return artist}
		songMockService.demand.getSongs() { -> return [song]}

		when:
		controller.index()

		then:
		view == "/song/index"
		model.artist == artist
		model.songs == [song]
	}

	void test_delete(){
		Artist artist = new Artist(firstName: "A", lastName: "B")
		Song song = new Song(name:'aSong', artist: artist);

		songMockService.demand.getSong(_) { -> return song}

		when:
		controller.delete()

		then:
		response.redirectedUrl == "/song/index"
	}
}
