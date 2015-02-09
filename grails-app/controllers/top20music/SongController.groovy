package top20music

class SongController {

	def index(int id){
		Artist artist = Artist.get(id);

		if (!artist){
			flash.message = "Unable to find artist!"
		}

		render(view: "index", model: [artist: artist])
	}
}
