package top20music

class TopController {
	def topService;

	def index() {
		def topSongDTOs = topService.getTop5PopularSongs();

		render(view: "top", model: [topSongs: topSongDTOs])
	}
}
