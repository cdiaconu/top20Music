package top20music

import util.DateUtils;

class TopController {
	def topService;

	def index() {
		println "params: " + params
		def topSongs = topService.getSongs()
		def topArtists = topService.getArtists()
		def votedWeeks = topService.getVotedWeeks()
		
		Date lastMonday = DateUtils.getMondayLastWeek()
		def topSongsByWeek = topService.getSongsForWeek(lastMonday)

		render(view: "index", model: [topSongs: topSongs, topArtists : topArtists, votedWeeks : votedWeeks, lastMonday: lastMonday, topSongsByWeek : topSongsByWeek])
	}
}
