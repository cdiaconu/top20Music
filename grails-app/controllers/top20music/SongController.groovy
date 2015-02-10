package top20music

import org.springframework.dao.DataIntegrityViolationException

class SongController {

	def index(){
		Artist artistInstance = Artist.get(params.id);

		if (!artistInstance){
			flash.message = "Unable to find artist!"
			redirect(controller: "artist", action: "index")
			return
		}

		render(view: "index", model: [artist: artistInstance, songs: artistInstance.songs.toList()])
	}

	def delete(){
		def song = Song.get(params.id);

		if (!song){
			flash.message = "Unable to delete song!"
			redirect(view: "index")
			return
		}

		try{
			song.delete(flush: true);
			flash.message = "The song was successfully deleted!"
		}
		catch (DataIntegrityViolationException e){
			flash.message = "An error occured on deleting the song!"
		}

		redirect(action: "index", id: song.artist.id)
	}

	def save(){
		def artistInstance = Artist.get(params.id)
		def song = new Song(name: params.name)
		artistInstance.addToSongs(song)
		
		if (song.validate()) {
			artistInstance.save(flush: true, failOnError: true)
			chain(action: "index", id: params.id)
			return;
		}

		if (song.errors.hasFieldErrors("name")) {
			//chain(action: "index", id: song.artist.id, model: [songErr: song])
			render(view: "index", model: [songErr: song, artist: artistInstance, songs: Artist.get(params.id).songs.toList()])
		}
	}
}
