package top20music

import grails.transaction.Transactional

import org.hibernate.Criteria
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.criterion.Order
import org.hibernate.criterion.Projections
import org.hibernate.criterion.Restrictions
import org.hibernate.sql.JoinType
import org.hibernate.transform.Transformers
import org.springframework.beans.factory.annotation.Autowired

import util.DateUtils

@Transactional
class SongService {

	@Autowired
	SessionFactory sessionFactory;

	def getSong(def songId){
		return Song.get(songId)
	}

	def getSongs(def artistId){
		Session session = sessionFactory.getCurrentSession();
		Criteria songCriteria = session.createCriteria(Song.class, "song");
		songCriteria.createAlias('song.artist', 'artist')
		JoinType left = JoinType.LEFT_OUTER_JOIN;
		songCriteria.createAlias('song.vots', 'vote', left)
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
	}

	def vote(songId) {

		def song = Song.get(songId)
		def vote = new Vote(voteNo: 1, voteDate: new Date())
		song.addToVots(vote)

		if (vote.validate()){
			song.save(flush: true, failOnError: true)
		}
	}
}
