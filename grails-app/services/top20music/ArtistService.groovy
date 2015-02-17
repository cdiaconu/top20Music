package top20music

import grails.transaction.Transactional
import util.CustomException

@Transactional
class ArtistService {

	def list(){
		return Artist.list()
	}

	def getArtist(def artistId){
		return Artist.get(artistId)
	}

	def saveArtist(Artist artist){
		def artists = list();

		for (Artist a : artists){
			if (a.firstName.equals(artist.firstName) && a.lastName.equals(artist.lastName)){
				throw new CustomException("The artist " + artist.firstName + " already exists!")
				return false
			}
		}

		if (artist.validate()) {
			artist.save()
			return true
		}
		return false
	}
}
