package top20music

import org.springframework.dao.DataIntegrityViolationException

class ArtistController {

	def index() {
		def artists = Artist.list();
		render(view: "index", model: [artists : artists])
	}

	def save(){
		def artist = new Artist()
		bindData(artist, params, [include: ['firstName', 'lastName']])

		def artists = Artist.list();
		for (Artist a : artists){
			if (a.firstName.equals(params.firstName) && a.lastName.equals(params.lastName)){
				flash.message =  "The artist " + params.firstName + " already exists!"
				render(view: "index", model: [artists : Artist.list()])
				return
			}
		}

		if (artist.validate()) {
			artist.save()
			render(view: "index", model: [artists : Artist.list()])
			return;
		}

		if (artist.errors.hasFieldErrors("firstName")) {
			render(view: "index", model: [artistErr: artist, artists : Artist.list()])
		}
	}

	def delete(){
		def artist = Artist.get(params.id);

		if (!artist){
			flash.message = "Unable to delete the artist!"
			redirect(view: "index")
			return
		}

		try{
			artist.delete(flush: true);
			flash.message = "The artist was successfully deleted!"
		}
		catch (DataIntegrityViolationException e){
			flash.message = "An error occured on deleting the artist!"
		}
		redirect(view: "index")
	}

	def update(){
		Artist artist = Artist.get(params.id)

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
