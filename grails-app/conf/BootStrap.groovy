import top20music.Artist
import top20music.Song

class BootStrap {

	def init = { servletContext ->

		if (Artist.count() == 0){

			new Artist(firstName : 'Robbie', lastName : 'Williams')
			.addToSongs(new Song(name : "Lazy Days", votes: 0))
			.addToSongs(new Song(name : "Candy", votes: 4))
			.addToSongs(new Song(name : "Gospel", votes: 1))
			.addToSongs(new Song(name : "Monsoon", votes: 3))
			.addToSongs(new Song(name : "Eternity", votes: 2)).save(failOnError:true)

			new Artist(firstName : 'Prince', lastName : 'A')
			.addToSongs(new Song(name : "Lazy Days", votes: 2))
			.addToSongs(new Song(name : "Candy", votes: 3))
			.addToSongs(new Song(name : "Candy Crush", votes: 3))
			.addToSongs(new Song(name : "Gospel", votes: 1))
			.addToSongs(new Song(name : "Monsoon", votes: 0))
			.addToSongs(new Song(name : "Eternity", votes: 3)).save(failOnError:true)
		}
	}
	
	def destroy = {
	}
}
