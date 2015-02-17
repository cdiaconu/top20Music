package top20music

import grails.transaction.Transactional

import org.grails.datastore.mapping.query.Query.Projection
import org.hibernate.criterion.Order
import org.hibernate.criterion.Projections
import org.hibernate.transform.Transformers

@Transactional
class TopService {


	def getTop5PopularSongs(){

		println "srate"
		def voteCriteria = Vote.createCriteria()
		voteCriteria.createAlias('vote', 'v')
		voteCriteria.createAlias('v.song', 's')
		voteCriteria.createAlias('s.artist', 'a')
		Projection pr = Projections.projectionList()
				.add(Projections.property("voteNo"), "voteNo")
				.add(Projections.property("s.name"), "songName")
				.add(Projections.property("a.firstname"), "artistFirstName")
				.add(Projections.property("a.lastname"), "artistLastName")
		voteCriteria.setProjection(pr)
		voteCriteria.addOrder(Order.desc("voteNo")).setMaxResults(5)
		voteCriteria.resultTransformer(Transformers.aliasToBean(TopSongDTO.class))

		List<TopSongDTO> topSongDTOs = voteCriteria.list;

		println topSongDTOs
		println "songs"

		return topSongDTOs
		
		/*
		select s.name, a.first_name, a.last_name, sum(v.vote_no) as count
		from song s
		 join artist a on a.id = s.artist_id
		 join vote v on s.id = v.song_id
		group by s.name, a.first_name, a.last_name
		order by count desc
		*/
		
		
		/*select a.first_name, a.last_name, sum(v.vote_no) as count
		from song s
		 join artist a on a.id = s.artist_id
		 join vote v on s.id = v.song_id
		group by a.first_name, a.last_name
		order by count desc*/
		
		/*select s.name, a.first_name, a.last_name, sum(v.vote_no) as count
		from song s
		 join artist a on a.id = s.artist_id
		 join vote v on s.id = v.song_id
where v.first_day_of_the_week = '2015-02-09'
		group by s.name, a.first_name, a.last_name
		order by count desc*/
	}
}
