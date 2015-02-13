package top20music
import org.hibernate.criterion.Order
import org.hibernate.transform.Transformers

import org.hibernate.criterion.Order
import org.hibernate.transform.Transformers


import grails.transaction.Transactional

import java.util.concurrent.TimeUnit

import org.hibernate.Criteria
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.criterion.Conjunction
import org.hibernate.criterion.Order
import org.hibernate.criterion.Projections
import org.hibernate.criterion.Restrictions
import org.hibernate.transform.Transformers
import org.springframework.beans.factory.annotation.Autowired

@Transactional
class TopService {

	@Autowired
	SessionFactory sessionFactory;

	def getArtists(){

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

	def getSongs(){

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
				.add(Projections.alias(Projections.sum("vote.voteNo"),
				"voteNo"))
				)
		songCriteria.addOrder(Order.desc("voteNo")).setMaxResults(5).setResultTransformer(Transformers.aliasToBean(TopSongDTO.class))
		List<TopSongDTO> topSongDTOs = songCriteria.list()

		return topSongDTOs
	}

	def getVotedWeeks(){
		Session session = sessionFactory.getCurrentSession();

		Criteria voteCriteria = session.createCriteria(Vote.class, "vote");
		voteCriteria.setProjection(Projections
				.projectionList()
				.add(Projections.alias(Projections.groupProperty("vote.firstDayOfTheWeek"),
				"week")))
		voteCriteria.addOrder(Order.desc("week")).setResultTransformer(Transformers.aliasToBean(VoteWeekDto.class))

		return voteCriteria.list()
	}

	def getSongsForWeek(Date monday){
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
				.add(Projections.alias(Projections.sum("vote.voteNo"),
				"voteNo"))
				)

		Date minDate = monday;
		Date maxDate = new Date(minDate.getTime() + TimeUnit.DAYS.toMillis(1));
		Conjunction and = Restrictions.conjunction();
		and.add( Restrictions.ge("vote.firstDayOfTheWeek", minDate) );
		and.add( Restrictions.lt("vote.firstDayOfTheWeek", maxDate) );

		songCriteria.add(and);
		songCriteria.addOrder(Order.desc("voteNo")).setMaxResults(5).setResultTransformer(Transformers.aliasToBean(TopSongDTO.class))
		List<TopSongDTO> topSongDTOs = songCriteria.list()

		return topSongDTOs
	}
}
