package projet;

import java.util.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Screen {
	
	public final int WIDTH = 450;
	public final int HEIGHT = 300;
	
	List<Chanson> data;
	LinkedList<Chanson> history = new LinkedList<>();
	ArrayList<JLabel> listLabel;
	protected Chanson currentMusic;
	protected Chanson currentMusicThread;
	protected int currentFilter = 1;
	public JFrame frame = new JFrame();
	
	Screen(List<Chanson> listChanson){
		data = listChanson;
	}

	/**
	 * Method start user interface
	 */
	public void start() {
		setListLabel(init(new Random().nextInt(data.size())));
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		SwingUtilities.invokeLater(() -> {
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					if(Integer.parseInt(listLabel.get(7).getText())==0) {
						setMusic(listLabel);
					}else if(!currentMusic.equals(currentMusicThread)) {
						setMusic(listLabel, currentMusicThread);
					}
				}
			}, currentMusic.getDuree()* 1000L, currentMusic.getDuree()* 1000L);
		});
	}
	/**
	 * Music exists in history
	 * @param currentChanson : Music to verify
	 * @return if it exists in history
	 */
	public boolean enableMusic(Chanson currentChanson){
		for (Chanson element : history) {
			if (currentChanson.equals(element))
				return true;
		}
		return false;
	}

	/**
	 * Next music in terms of current filter
	 * @return Chanson, it is not in history
	 */
	protected Chanson nextChanson() {
		// self have enabled to use
		List<Chanson> self = selectFilter();
    	if(self.isEmpty()) {
    		self = data;
    	}
		Chanson randomMusic;
		//Use i if I have not new song in 6 iteration
		int i=0;
		do {
			i++;
			randomMusic = self.get(new Random().nextInt(self.size()));
			if (i==5){
				//setCurrentFilter(1);
				randomMusic = data.get(new Random().nextInt(data.size()));
			}
		}while (enableMusic(randomMusic));
    	return randomMusic;
	}

	/**
	 *  Last music
	 * @return Last music used
	 */
	protected Chanson lastChanson() {
		if(history.size()>1){
			history.removeFirst();
			Chanson last = history.getFirst();
			history.removeFirst();
			return last;
		}
		return null;
	}

	/**
	 * Modify player music thread
	 * @param listLabel : all label to modify
	 */
	protected void setMusic(ArrayList<JLabel> listLabel) {
		SwingUtilities.invokeLater(() -> {
			Chanson currentChanson = nextChanson();
			setCurrentMusic(currentChanson);
			setCurrentMusicThread(currentChanson);
			addHistorySpecial(currentChanson);
			listLabel.get(0).setText(currentChanson.getTitre());
			listLabel.get(1).setText(currentChanson.getAlbum());
			listLabel.get(2).setText(currentChanson.getArtiste());
			listLabel.get(3).setText(currentChanson.getGenres().toString());
			listLabel.get(4).setText(currentChanson.getDanceability().toString());
			listLabel.get(5).setText(currentChanson.getEnergy().toString());
			listLabel.get(6).setText(currentChanson.getLoudness().toString());
			listLabel.get(7).setText(""+currentChanson.getDuree());
		});
	}

	/**
	 * Add last music in history
	 * @param current : last music
	 */
	public void addHistorySpecial(Chanson current) {
		try {
			if(history.size() > 5){
				history.remove(5);
			}
		} catch (IndexOutOfBoundsException ignored){}
		try {
			if(history.get(4)!=null){
				history.set(5,history.get(4));
			}
		} catch (IndexOutOfBoundsException ignored){}
		try {
			if(history.get(3)!=null){
				history.set(4,history.get(3));
			}
		} catch (IndexOutOfBoundsException ignored){}
		try {
			if(history.get(2)!=null){
				history.set(3,history.get(2));
			}
		} catch (IndexOutOfBoundsException ignored){}
		try {
			if(history.get(1)!=null){
				history.set(2,history.get(1));
			}
		} catch (IndexOutOfBoundsException ignored){}
		try {
			if(history.get(0)!=null){
				history.set(1,history.get(0));
			}
		} catch (IndexOutOfBoundsException ignored){}
		history.add(0,current);
	}

	/**
	 * Modify player music thread with selected music
	 * @param listLabel : all label to modify
	 * @param currentChanson : music that it wants to start
	 */
	protected void setMusic(ArrayList<JLabel> listLabel, Chanson currentChanson) {
		setCurrentMusic(currentChanson);
		addHistorySpecial(currentChanson);
		listLabel.get(0).setText(currentChanson.getTitre());
		listLabel.get(1).setText(currentChanson.getAlbum());
		listLabel.get(2).setText(currentChanson.getArtiste());
		listLabel.get(3).setText(currentChanson.getGenres().toString());
		listLabel.get(4).setText(currentChanson.getDanceability().toString());
		listLabel.get(5).setText(currentChanson.getEnergy().toString());
		listLabel.get(6).setText(currentChanson.getLoudness().toString());
		listLabel.get(7).setText(""+currentChanson.getDuree());
	}

	/**
	 * Modify player music thread with selected music in history
	 * To avoid adding music
	 * @param listLabel : all label to modify
	 * @param currentChanson : music that it wants to start in history
	 */
	protected void setMusicHistory(ArrayList<JLabel> listLabel, Chanson currentChanson) {
		System.out.println("yooo "+currentChanson);
		if(currentChanson!=null){
			setCurrentMusic(currentChanson);
			listLabel.get(0).setText(currentChanson.getTitre());
			listLabel.get(1).setText(currentChanson.getAlbum());
			listLabel.get(2).setText(currentChanson.getArtiste());
			listLabel.get(3).setText(currentChanson.getGenres().toString());
			listLabel.get(4).setText(currentChanson.getDanceability().toString());
			listLabel.get(5).setText(currentChanson.getEnergy().toString());
			listLabel.get(6).setText(currentChanson.getLoudness().toString());
			listLabel.get(7).setText(""+currentChanson.getDuree());
		}
	}

	/**
	 * return list about album
	 * @param data : all music
	 * @param current : get music to compare with other music
	 * @return returns list with similar music
	 */
	protected List<Chanson> filterAlbum(List<Chanson> data, Chanson current){
		return data.stream().filter(s->s.getAlbum().equals(current.getAlbum())).toList();
	}
	/**
	 * return list about artiste
	 * @param data : all music
	 * @param current : get music to compare with other music
	 * @return returns list with similar music
	 */
	protected List<Chanson> filterArtiste(List<Chanson> data, Chanson current){
		return data.stream().filter(s->s.getArtiste().equals(current.getArtiste())).toList();
	}
	/**
	 * return list about Danceability
	 * @param data : all music
	 * @param current : get music to compare with other music
	 * @return returns list with similar music
	 */
	protected List<Chanson> filterDanceability(List<Chanson> data, Chanson current){
		return data.stream().filter(s-> s.getDanceability()<current.getDanceability()+0.05 && s.getDanceability()>current.getDanceability()-0.05).toList();
	}
	/**
	 * return list about Energy
	 * @param data : all music
	 * @param current : get music to compare with other music
	 * @return returns list with similar music
	 */
	protected List<Chanson> filterEnergy(List<Chanson> data, Chanson current){
		return data.stream().filter(s-> s.getEnergy()<current.getEnergy()+0.05 && s.getEnergy()>current.getEnergy()-0.05).toList();
	}
	/**
	 * return list about Loudness
	 * @param data : all music
	 * @param current : get music to compare with other music
	 * @return returns list with similar music
	 */
	protected List<Chanson> filterLoudness(List<Chanson> data, Chanson current){
		return data.stream().filter(s-> s.getLoudness().intValue()==current.getLoudness().intValue()).toList();
	}
	/**
	 * return list about Genres
	 * @param data : all music
	 * @param current : get music to compare with other music
	 * @return returns list with similar music
	 */
	protected List<Chanson> filterGenres(List<Chanson> data, Chanson current){
		ArrayList<Chanson> listGenre = new ArrayList<>();
		data.forEach(s ->{
			for(String strCurrent : s.getGenres()) {
				int i=0;
				for(String element : current.getGenres()) {
					if(element.equals(strCurrent)) {
						i++;
					}
					if(i==2) {
						listGenre.add(s);
						break;
					}
				}
			}
		});
		return listGenre.stream().distinct().toList();
	}

	/**
	 * get list of music data
	 * @return data in terms of currentfilter
	 */
	protected List<Chanson> selectFilter(){
		//SwingWorker
		return switch (getCurrentFilter()) {
			case 1 -> filterAlbum(data, getCurrentMusic());
			case 2 -> filterArtiste(data, getCurrentMusic());
			case 3 -> filterDanceability(data, getCurrentMusic());
			case 4 -> filterEnergy(data, getCurrentMusic());
			case 5 -> filterLoudness(data, getCurrentMusic());
			case 6 -> filterGenres(data, getCurrentMusic());
			default -> data;
		};
	}

	/**
	 * initialize user interface
	 * @param currentChansonNb : random music
	 * @return To give label
	 */
	private ArrayList<JLabel> init(int currentChansonNb) {
		ArrayList<JLabel> listLabel = new ArrayList<>();
		Chanson currentChanson = data.get(currentChansonNb);
		setCurrentMusic(currentChanson);
		System.out.println(currentChanson.toString());
		JLabel nullLabel = new JLabel("");
		JLabel cpt = new JLabel(""+currentChanson.getDuree());
		JLabel lbl_title = new JLabel("Titre : ");
		JLabel lbl_title_current = new JLabel(currentChanson.getTitre());
		JLabel lbl_album = new JLabel("Album : ");
		JLabel lbl_album_current = new JLabel(currentChanson.getAlbum());
		JLabel lbl_artiste = new JLabel("Artiste : ");
		JLabel lbl_artiste_current = new JLabel(currentChanson.getArtiste());
		JLabel lbl_genres = new JLabel("Genres : ");
		JLabel lbl_genres_current = new JLabel(""+currentChanson.getGenres());
		JLabel lbl_danceability = new JLabel("Danceability : ");
		JLabel lbl_danceability_current = new JLabel(""+currentChanson.getDanceability());
		JLabel lbl_energy = new JLabel("Energy : ");
		JLabel lbl_energy_current = new JLabel(""+currentChanson.getEnergy());
		JLabel lbl_loudness = new JLabel("Loudness : ");
		JLabel lbl_loudness_current = new JLabel(""+currentChanson.getLoudness());
		JLabel lbl_nb = new JLabel(" Nombre de chanson : "+data.size());
		JButton bt_last = new JButton("Précedent");
		bt_last.addActionListener(e -> {
			Chanson tempMusic = lastChanson();
			if (tempMusic == null) {
				tempMusic = currentMusic;
			}
			setCurrentMusicThread(tempMusic);
			setMusicHistory(listLabel,tempMusic);
		});
		JButton bt_next = new JButton("Suivant");
		bt_next.addActionListener(e -> {
			SwingUtilities.invokeLater(() -> {
				setCurrentMusicThread(nextChanson());
				setMusic(listLabel, currentMusicThread);
			});
		});
		JComboBox<String> filtrerBox = new JComboBox<>(new String[]{"Album", "Artiste", "Danceability", "Energy", "Loudness", "Genres"});
		filtrerBox.addItemListener(e -> {
			int currentBox = radioToInt((String) filtrerBox.getSelectedItem());
			if(currentBox != getCurrentFilter()) {
				setCurrentFilter(currentBox);
			}
		});
		cpt.setBounds(5, 0, 20, 20);
		lbl_nb.setBounds(0, HEIGHT-75, 200, 20);
		lbl_title.setBounds(20, 40, 100, 20);
		lbl_title_current.setBounds(100, 40, WIDTH -20, 20);
		lbl_album.setBounds(20, 60, 100, 20);
		lbl_album_current.setBounds(100, 60, WIDTH -20, 20);
		lbl_artiste.setBounds(20, 80, 100, 20);
		lbl_artiste_current.setBounds(100, 80, WIDTH -20, 20);
		lbl_genres.setBounds(20, 100, 200, 20);
		lbl_genres_current.setBounds(100, 100, WIDTH -20, 20);
		lbl_danceability.setBounds(20, 120, 100, 20);
		lbl_danceability_current.setBounds(100, 120, 100, 20);
		lbl_energy.setBounds(140, 120, 100, 20);
		lbl_energy_current.setBounds(200, 120, 50, 20);
		lbl_loudness.setBounds(240, 120, 100, 20);
		lbl_loudness_current.setBounds(310, 120, 50, 20);
		bt_last.setBounds(WIDTH /2 - 150, 200, 100, 20);
		filtrerBox.setBounds(WIDTH /2-50, 200, 100, 20);
		bt_next.setBounds(WIDTH /2 + 50, 200, 100, 20);
		frame.add(lbl_title);
		frame.add(lbl_title_current);
		listLabel.add(lbl_title_current);
		frame.add(lbl_album);
		frame.add(lbl_album_current);
		listLabel.add(lbl_album_current);
		frame.add(lbl_artiste);
		frame.add(lbl_artiste_current);
		listLabel.add(lbl_artiste_current);
		frame.add(lbl_genres);
		frame.add(lbl_genres_current);
		listLabel.add(lbl_genres_current);
		frame.add(lbl_danceability);
		frame.add(lbl_danceability_current);
		listLabel.add(lbl_danceability_current);
		frame.add(lbl_energy);
		frame.add(lbl_energy_current);
		listLabel.add(lbl_energy_current);
		frame.add(lbl_loudness);
		frame.add(lbl_loudness_current);
		listLabel.add(lbl_loudness_current);
		frame.add(bt_last);
		frame.add(bt_next);
		frame.add(cpt);
		frame.add(filtrerBox);
		listLabel.add(cpt);
		frame.add(lbl_nb);
		frame.add(nullLabel);
		Timer timerCpt = new Timer();
		timerCpt.schedule(new TimerTask() {
		    @Override
		    public void run() {
		    	if(Integer.parseInt(cpt.getText())!=0) {
		    		cpt.setText(""+(Integer.parseInt(cpt.getText())-1));
		    	}
		    }
		}, 1000, 1000);
		return listLabel;
	}

	/**
	 * translate JComboBox data to int
	 * @param selectedItem : data filter
	 * @return information to use in programme
	 */
	protected int radioToInt(String selectedItem) {
		if(selectedItem.equals("Album")) {
			return 1;
		}
		if(selectedItem.equals("Artiste")) {
			return 2;
		}
		if(selectedItem.equals("Danceability")) {
			return 3;		
		}
		if(selectedItem.equals("Energy")) {
			return 4;
		}
		if(selectedItem.equals("Loudness")) {
			return 5;
		}
		if(selectedItem.equals("Genres")) {
			return 6;
		}
		return 0;
	}
	//getter and setter
	public int getCurrentFilter() {
		return currentFilter;
	}
	public void setCurrentFilter(int currentFilter) {
		this.currentFilter = currentFilter;
	}
	public Chanson getCurrentMusic() {
		return currentMusic;
	}
	public void setCurrentMusic(Chanson currentMusic) {
		this.currentMusic = currentMusic;
	}
	public void setListLabel(ArrayList<JLabel> listLabel) {
		this.listLabel = listLabel;
	}
	public void setCurrentMusicThread(Chanson currentMusicThread) {
		this.currentMusicThread = currentMusicThread;
	}
}
