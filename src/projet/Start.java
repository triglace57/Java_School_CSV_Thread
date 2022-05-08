package projet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Start {

	public static void main(String[] args) {
		List<Chanson> listChanson = new ArrayList<>();
		Path path = Paths.get("src/tracks.csv");
		try (Stream<String> lignes = Files.lines(path))
		{
			lignes.forEach(line ->{
				int j = 0;
				String[] currentData = line.split(",",2);
				String titre = "";
				String rest = "";
				String[] ranking = new String[3];
				String[] temp = currentData[1].split(",");
				if(isNumeric(currentData[0])){
					for(int i=0;i<temp.length-2;i++) {
						if(j==3) {
							rest += temp[i];
						}
						if(!isNumeric(temp[i]) && j==0) {
							titre += temp[i];
						}
						if(isNumeric(temp[i]) && j<3) {
							ranking[j] = temp[i];
							j++;
						}
					}
					ArrayList<String> genre = new ArrayList<>(Arrays.asList(temp[temp.length - 1].split(" ")));
					if(listChanson.size() !=0) {
						if(listChanson.get(listChanson.size()-1).getId() == Integer.parseInt(currentData[0])) {
							listChanson.get(listChanson.size()-1).getGenres().addAll(genre);
						} else
							listChanson.add(new Chanson(Integer.parseInt(currentData[0]),titre,Double.parseDouble(ranking[0]),Double.parseDouble(ranking[1]),Double.parseDouble(ranking[2]),rest,temp[temp.length-2],genre,5));
					} else
						listChanson.add(new Chanson(Integer.parseInt(currentData[0]),titre,Double.parseDouble(ranking[0]),Double.parseDouble(ranking[1]),Double.parseDouble(ranking[2]),rest,temp[temp.length-2],genre,5));
				}
			});
			//System.out.println(listChanson.stream().count());
			Screen screen = new Screen(listChanson);
			screen.start();

		}catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public static boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
