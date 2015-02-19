package top20music

import java.text.SimpleDateFormat

class TopController {
	def topService;

	def index() {
		def topSongs = topService.getSongs()
		def topArtists = topService.getArtists()

		Date fromDate = new Date();
		Date toDate = new Date();
		def topSongsForPeriod = []
		if (params.fromDate_year){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd")
			String dateString = params.fromDate_year + "-" +  params.fromDate_month + "-" +  params.fromDate_day;
			fromDate = formatter.parse(dateString)
			dateString = params.toDate_year + "-" +  params.toDate_month + "-" +  params.toDate_day;
			toDate = formatter.parse(dateString)
			topSongsForPeriod = topService.getSongsForPeriod(fromDate, toDate)
		}

		render(view: "index", model: [fromDate: fromDate, toDate: toDate, topSongsForPeriod: topSongsForPeriod, topSongs: topSongs, topArtists : topArtists])
	}
}
