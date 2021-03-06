/*Charlie Raymond, Colton Gering, CPSC353 MWF 9-10am, Michael Fahy
 * Final project
 * this is the spotify search class
 * this is where all of our spotify implementation is done
 * we found a resource online which aided in the completion 
 * of this part of our project
*/


import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

// This code taken and modified from example from java spotify api wrapper:
// https://github.com/thelinmichael/spotify-web-api-java
//


//spotify class
public class SpotifySearch {

	//The keys required to access the spotify api
	private static final String clientId = "132e797720bb47bd93d4af024d604f23";
    private static final String clientSecret = "38cf022f78804c9da00b20f3c26456ae";

	//making spotify Api
    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    	.setClientId(clientId)
       	.setClientSecret(clientSecret)
       	.build();

	//getting client credentials
    private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
      	.build();

	//sync client credentials
    public static void clientCredentials_Sync() {
        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            // Set access token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());

            System.out.println("Expires in: " + clientCredentials.getExpiresIn());
            System.out.println("Token= " + clientCredentials.getAccessToken());
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }



	//searches tracks based on search string, returns a list of results
    public  Track[] searchTracks_Sync(String q) {
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(q)
                .limit(10)
                .offset(0)
                .build();
        Track[] tracks = null;
        try {
            final Paging<Track> trackPaging = searchTracksRequest.execute();

            System.out.println("Total: " + trackPaging.getTotal());
            tracks = trackPaging.getItems();
            /*
            for (int i=0; i<tracks.length; i++){
                System.out.println("track = " + tracks[i].getName());
                System.out.println("artist = " + tracks[i].getArtists()[0].getName());
                System.out.println("album = "+ tracks[i].getAlbum().getName());
                //System.out.println("")
            }
            */
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return tracks;
    }



}
