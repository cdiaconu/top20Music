package top20music

import grails.transaction.Transactional

@Transactional
class ArtistService {

	def list(){
		return Artist.list()
	}

	def getArtist(def artistId){
		return Artist.get(artistId)
	}
}
