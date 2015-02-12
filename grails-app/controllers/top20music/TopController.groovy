package top20music

class TopController {
	def topService;

	def index() {
		def topSongs = topService.getTop5PopularSongs()
		def topArtists = topService.getTop5PopularArtist()
		def votedWeeks = topService.getAllVotedWeeks()

		render(view: "index", model: [topSongs: topSongs, topArtists : topArtists, votedWeeks : votedWeeks])
	}
}
