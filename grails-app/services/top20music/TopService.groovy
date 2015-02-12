package top20music
import org.hibernate.criterion.Order
import org.hibernate.transform.Transformers

import org.hibernate.criterion.Order
import org.hibernate.transform.Transformers


import grails.transaction.Transactional

import org.hibernate.Criteria
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.criterion.Order
import org.hibernate.criterion.Projections
import org.hibernate.transform.Transformers
import org.springframework.beans.factory.annotation.Autowired

@Transactional
class TopService {

	@Autowired
	SessionFactory sessionFactory;

	def getTop5PopularArtist(){

		Session session = sessionFactory.getCurrentSession();

		Criteria voteCriteria = session.createCriteria(Vote.class, "vote");
		voteCriteria.createAlias('vote.song', 'song')
		voteCriteria.createAlias('song.artist', 'artist')
		voteCriteria.setProjection(Projections
				.projectionList()
				.add(Projections.alias(Projections.groupProperty("artist.firstName"),
				"artistFirstName"))
				.add(Projections.alias(Projections.groupProperty("artist.lastName"),
				"artistLastName"))
				.add(Projections.alias(Projections.sum("vote.voteNo"),
				"voteNo"))
				)
		voteCriteria.addOrder(Order.desc("voteNo")).setMaxResults(5).setResultTransformer(Transformers.aliasToBean(TopSongDTO.class))
		List<TopSongDTO> topSongDTOs = voteCriteria.list()

		return topSongDTOs
	}

	def getTop5PopularSongs(){

		Session session = sessionFactory.getCurrentSession();

		Criteria songCriteria = session.createCriteria(Song.class, "song");
		songCriteria.createAlias('song.artist', 'artist')
		songCriteria.createAlias('song.vots', 'vote')
		songCriteria.setProjection(Projections
				.projectionList()
				.add(Projections.alias(Projections.groupProperty("song.name"),
				"songName"))
				.add(Projections.alias(Projections.groupProperty("artist.firstName"),
				"artistFirstName"))
				.add(Projections.alias(Projections.groupProperty("artist.lastName"),
				"artistLastName"))
				.add(Projections.alias(Projections.groupProperty("vote.voteNo"),
				"voteNo"))
				.add(Projections.alias(Projections.sum("vote.voteNo"),
				"voteNo"))
				)
		songCriteria.addOrder(Order.desc("vote.voteNo")).setMaxResults(5).setResultTransformer(Transformers.aliasToBean(TopSongDTO.class))
		List<TopSongDTO> topSongDTOs = songCriteria.list()

		return topSongDTOs
	}

	def getAllVotedWeeks(){

		Session session = sessionFactory.getCurrentSession();
		
		Criteria voteCriteria = session.createCriteria(Vote.class, "vote");
		voteCriteria.setProjection(Projections
				.projectionList()
				.add(Projections.alias(Projections.groupProperty("vote.firstDayOfTheWeek"),
				"first_day_of_the_week")))
		
		return voteCriteria.list()
	}
}
