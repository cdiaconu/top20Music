package top20music

import grails.transaction.Transactional
import util.DateUtils

@Transactional
class SongService {

	def vote(songId) {

		def song = Song.get(songId)
		def vote = getVoteInstanceToAdd(songId)
		song.addToVots(vote)

		if (vote.validate()){
			song.save(flush: true, failOnError: true)
		}
	}

	def getVoteInstanceToAdd(songId){
		def vots = Song.get(songId).vots

		Date monday = DateUtils.getMondayThisWeek()
		if (vots.isEmpty()){
			// no votes exist for this song
			return new Vote(voteNo: 1, firstDayOfTheWeek: monday)
		}


		for (Vote vot : vots){
			Date date = vot.firstDayOfTheWeek
			if (DateUtils.areEquals(date, monday)){
				vot.voteNo ++
				return vot
			}
		}

		return new Vote(voteNo: 1, firstDayOfTheWeek: monday)
	}
	
	def getVotesForCurrentWeek(songId){
		def vots = Song.get(songId).vots
		
		Date monday = DateUtils.getMondayThisWeek()
		if (vots.isEmpty()){
			return 0
		}
		
		for (Vote vot : vots){
			Date date = vot.firstDayOfTheWeek
			if (DateUtils.areEquals(date, monday)){
				return vot.voteNo;
			}
		}
		
		return 0;
		
	}
}
