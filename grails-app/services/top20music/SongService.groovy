package top20music

import grails.transaction.Transactional

import org.hibernate.Criteria
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.criterion.Order
import org.hibernate.criterion.Projections
import org.hibernate.criterion.Restrictions
import org.hibernate.transform.Transformers
import org.springframework.beans.factory.annotation.Autowired

import util.DateUtils

@Transactional
class SongService {

	@Autowired
	SessionFactory sessionFactory;

	def getSongs(def artistId){
		Session session = sessionFactory.getCurrentSession();
		Criteria songCriteria = session.createCriteria(Song.class, "song");
		songCriteria.createAlias('song.artist', 'artist')
		//JoinType left = JoinType.LEFT_OUTER_JOIN;
		songCriteria.createAlias('song.vots', 'vote')
		songCriteria.setProjection(Projections
				.projectionList()
				.add(Projections.alias(Projections.groupProperty("song.id"),
				"id"))
				.add(Projections.alias(Projections.property("song.name"),
				"songName"))
				.add(Projections.alias(Projections.sum("vote.voteNo"),
				"voteNo"))
				)
		songCriteria.add(Restrictions.eq("artist.id", Long.valueOf(artistId)));
		songCriteria.addOrder(Order.asc("id")).setResultTransformer(Transformers.aliasToBean(TopSongDTO.class))
		List<TopSongDTO> topSongDTOs = songCriteria.list()

		return topSongDTOs;
		/*
		 List<TopSongDTO> userList = Song.createCriteria().list {
		 createAlias('artist', 'artist')
		 createAlias("vots", "vote", JoinType.LEFT_OUTER_JOIN)
		 setProjection(Projections
		 .projectionList()
		 .add(Projections.alias(Projections.groupProperty("song.id"),
		 "id"))
		 .add(Projections.alias(Projections.property("song.name"),
		 "songName"))
		 .add(Projections.alias(Projections.sum("vote.voteNo"),
		 "voteNo"))
		 )
		 add(Restrictions.eq("artist.id", Long.valueOf(artistId)));
		 addOrder(Order.asc("id")).setResultTransformer(Transformers.aliasToBean(TopSongDTO.class))
		 }
		 return userList*/
	}

	def vote(songId) {

		def song = Song.get(songId)
		def vote = getVoteInstance(songId)
		song.addToVots(vote)

		if (vote.validate()){
			song.save(flush: true, failOnError: true)
		}
	}

	def getVoteInstance(songId){
		def vots = Song.get(songId).vots

		Date monday = DateUtils.getMondayThisWeek()

		println "monday:" + monday

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
