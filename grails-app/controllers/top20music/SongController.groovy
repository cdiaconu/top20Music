package top20music

import org.springframework.dao.DataIntegrityViolationException

class SongController {

	def index(int id){
		Artist artistInstance = Artist.get(id);

		if (!artistInstance){
			flash.message = "Unable to find artist!"
		}
		println "${artistInstance}";
		render(view: "index", model: [artist: artistInstance, songs: artistInstance.songs])
	}
	
	def delete(Long id){
		def song = Song.get(id);
		
		if (!song){
			flash.message = "Unable to delete song!"
			redirect(view: "index")
			return
		}

		try{
			song.delete(flush: true);
			flash.message = "The song was sucessfully deleted!"
		}
		catch (DataIntegrityViolationException e){
			flash.message = "An error occured on deleting the song!"
		}
		render(view: "index", model: [artist: song.artist, songs: song.artist.songs])
	}
}
