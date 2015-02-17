import top20music.Artist
import top20music.Song

class BootStrap {

	def init = { servletContext ->

		if (Artist.count() == 0){

			new Artist(firstName : 'Robbie', lastName : 'Williams')
			.addToSongs(new Song(name : "Lazy Days"))
			.addToSongs(new Song(name : "Candy"))
			.addToSongs(new Song(name : "Gospel"))
			.addToSongs(new Song(name : "Monsoon"))
			.addToSongs(new Song(name : "Eternity")).save(failOnError:true)

			new Artist(firstName : 'Prince', lastName : '')
			.addToSongs(new Song(name : "Shhh"))
			.addToSongs(new Song(name : "Live 4 Love"))
			.addToSongs(new Song(name : "Dolphin"))
			.addToSongs(new Song(name : "Gold"))
			.addToSongs(new Song(name : "Money Don't Matter 2 Night"))
			.addToSongs(new Song(name : "Thunder")).save(failOnError:true)
		}
	}

	def destroy = {
	}
}
