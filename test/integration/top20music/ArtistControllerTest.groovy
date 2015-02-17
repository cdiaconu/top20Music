
package top20music

class ArtistControllerTest extends GroovyTestCase {

	void testIndex() {
		def ac = new ArtistController()
		ac.index()

		assertEquals "/artist/index", ac.modelAndView.viewName
		assertNotNull ac.modelAndView.model.artists
		assertEquals 2, ac.modelAndView.model.artists.size()
	}

	void testSave_artistAlreadyExists(){
		def ac = new ArtistController()

		bindParamsAndSave(ac, 'Prince', 'A');

		assertEquals "The artist Prince already exists!", ac.flash.get("message")
		assertEquals "/artist/index", ac.modelAndView.viewName
		assertNotNull ac.modelAndView.model.artists
		assertEquals 2, ac.modelAndView.model.artists.size()
	}

	void testSave(){
		def ac = new ArtistController()
		bindParamsAndSave(ac, 'A', 'B');

		assertEquals "/artist/index", ac.modelAndView.viewName
		assertNotNull ac.modelAndView.model.artists
		assertEquals 3, ac.modelAndView.model.artists.size()
	}

	void testSave_firstNameOnly(){
		def ac = new ArtistController()
		bindParamsAndSave(ac, 'AName', '');

		assertEquals "/artist/index", ac.modelAndView.viewName
		assertNotNull ac.modelAndView.model.artists
		assertEquals 3, ac.modelAndView.model.artists.size()
	}

	private void bindParamsAndSave(ArtistController ac, String firstName, String lastName){
		ac.params.firstName = firstName
		ac.params.lastName = lastName
		ac.save()
	}

	void testDelete(){
		def ac = new ArtistController()
		ac.params.id = Artist.list().get(0).id
		ac.delete()

		assertEquals "The artist was successfully deleted!",  ac.flash.get("message")
	}

	void testDelete_invalidArtist(){
		def ac = new ArtistController()
		ac.params.id = 100
		ac.delete()

		assertEquals "Unable to delete the artist!",  ac.flash.get("message")
	}
}