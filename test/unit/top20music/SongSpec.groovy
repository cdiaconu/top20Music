package top20music

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Song)
class SongSpec extends Specification {

	def "test_song_name_not_blank"() {
		setup:
		mockForConstraintsTests(Song)
		mockDomain(Artist)

		when:
		def song = new Song(name:'', artist: new Artist(firstName: "A"));
		song.validate()

		then:
		assertTrue song.errors.hasFieldErrors("name")
	}

	def "test_song_no_artist"() {
		setup:
		mockForConstraintsTests(Song)

		when:
		def song = new Song(name:'', artist: null);
		song.validate()

		then:
		assertTrue song.errors.hasFieldErrors("artist")
	}
}
