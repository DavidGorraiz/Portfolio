package dev.david.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public List<Movie> allMovies(){
        return movieRepository.findAll();
    }
    public Movie singleMovie(String imdbId){
        return movieRepository.findMovieByImdbId(imdbId).orElse(null);
    }
}
