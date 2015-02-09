package top20music

import org.springframework.dao.DataIntegrityViolationException

class ArtistController {

	def index() {
		def artists = Artist.list();
		[artists : artists]
	}

	def save(){
		def artist = new Artist(params)

		if (artist.validate()) {
			artist.save()
			redirect(view: "index")
			return;
		}

		if (artist.errors.hasFieldErrors("firstName")) {
			println artist.errors.getFieldError("firstName").rejectedValue
			render(view: "index", model: [artistErr: artist, artists: Artist.list()])
		}
	}

	def delete(Long id){
		def artist = Artist.get(id);
		
		if (!artist){
			flash.message = "Unable to delete the artist!"
			redirect(view: "index")
			return
		}

		try{
			artist.delete(flush: true);
			flash.message = "The artist was sucessfully deleted!"
		}
		catch (DataIntegrityViolationException e){
			flash.message = "An error occured on deleting the artist!"
		}
		redirect(view: "index")
	}
	
	def update(Long id){
		Artist artist = Artist.get(id)
		
		if (!artist){
			flash.message = "Unable to update the artist! Artist not found!"
			redirect(view: "index")
			return
		}
		
		artist.properties = params
		if (!artist.save(flush: true)) {
			redirect(view: "index")
			return
		}
		flash.message = "The artist was successfully updated!";
		redirect(controller: "song", action: "index", id: artist.id)
	}
}
