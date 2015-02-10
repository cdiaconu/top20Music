package top20music

class TopController {
	def topService;

	def index() {
		def topSongs = topService.getSongs()
		def topArtists = topService.getArtists()
		def votedWeeks = topService.getVotedWeeks()
		//def topSongsByWeek = topService.getSongsForWeek("2015-02-09 00:00:00.0")

		render(view: "index", model: [topSongs: topSongs, topArtists : topArtists, votedWeeks : votedWeeks])
	}
}
