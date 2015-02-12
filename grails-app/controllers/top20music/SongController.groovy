package top20music

import org.springframework.dao.DataIntegrityViolationException

class SongController {
	def songService

	def index(){
		Artist artistInstance = Artist.get(params.id);

		if (!artistInstance){
			flash.message = "Unable to find artist!"
			redirect(controller: "artist", action: "index")
			return
		}

		render(view: "index", model: [artist: artistInstance, songs: songService.getSongs(params.id)])
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
		def originalSongs = artistInstance.songs.toList();
		def song = new Song(name: params.name)
		artistInstance.addToSongs(song)

		if (song.validate()) {
			artistInstance.save(flush: true, failOnError: true)
			chain(action: "index", id: params.id)
			return;
		}

		if (song.errors.hasFieldErrors("name")) {
			render(view: "index", model: [songErr: song, artist: artistInstance, songs: originalSongs])
		}
	}

	def votePositive(){
		songService.vote(params.id)
		def song = Song.get(params.id)
		redirect(controller: "song", action: "index", id: song.artist.id)
	}
}
