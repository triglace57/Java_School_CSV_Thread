package projet;

import java.util.ArrayList;
import java.util.List;

public class Chanson {
	private int id;
	private String titre;
	private String album;
	private String artiste;
	private Double danceability;
	private Double energy;
	private Double loudness;
	private ArrayList<String> genres;
	private int duree;
	
	Chanson(int id, String titre,
			Double danceability, Double energy, Double loudness, String album, String artiste, ArrayList<String> genres,
			int duree){
		this.id = id ;
		this.titre = titre;
		this.album = album;
		this.artiste = artiste;
		this.danceability = danceability;
		this.energy = energy;
		this.loudness = loudness;
		this.genres = genres;
		this.duree = duree;
		
	}
	
	
	public String toString() {
		return id+" Titre : "+titre+" /// Statuts | "+danceability+" | "+energy+" | "+loudness+" /// Album : "+album+" Artiste : "+artiste+" Genre : "+genres.toString();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getArtiste() {
		return artiste;
	}
	public void setArtiste(String artiste) {
		this.artiste = artiste;
	}
	public Double getDanceability() {
		return danceability;
	}
	public void setDanceability(Double danceability) {
		this.danceability = danceability;
	}
	public Double getEnergy() {
		return energy;
	}
	public void setEnergy(Double energy) {
		this.energy = energy;
	}
	public Double getLoudness() {
		return loudness;
	}
	public void setLoudness(Double loudness) {
		this.loudness = loudness;
	}
	public ArrayList<String> getGenres() {
		return genres;
	}
	public void setGenres(ArrayList<String> genres) {
		this.genres = genres;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
}
