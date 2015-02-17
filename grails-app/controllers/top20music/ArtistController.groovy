package top20music

import util.CustomException

class ArtistController {

	def artistService

	def index() {
		def artists = artistService.list();
		render(view: "index", model: [artists : artists])
	}

	def save(){

		def artist = new Artist()
		bindData(artist, params, [include: ['firstName', 'lastName']])

		try{
			artistService.saveArtist(artist)
		}
		catch (CustomException ex){
			flash.message = ex.getMessage()
		}

		def artists = artistService.list();
		if (artist.errors.hasFieldErrors("firstName")) {
			render(view: "index", model: [artistErr: artist, artists : artists])
		}
		else{
			render(view: "index", model: [artists : artists])
		}
	}

	def delete(){
		def artist = Artist.get(params.id);
		if (artist){
			artist.delete(flush: true);
			flash.message = "The artist was successfully deleted!"
		}
		redirect(view: "index")
	}

	def update(){
		Artist artist = Artist.get(params.id)

		if (artist){
			artist.save()
			flash.message = "The artist was successfully updated!";
			redirect(controller: "song", action: "index", id: artist.id)
			return
		}

		flash.message = "Unable to update the artist! Artist not found!"
		redirect(view: "index")
	}
}
