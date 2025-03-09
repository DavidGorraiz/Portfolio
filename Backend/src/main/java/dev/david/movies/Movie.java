package dev.david.movies;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;
import java.util.Objects;

@Document(collection = "movies")
public class Movie {
    @Id
    private ObjectId id;
    private String imdbId;
    private String title;
    private String releaseDate;
    private String trailerLink;
    private String poster;
    private List<String> genres;
    private List<String> backdrops;
    @DocumentReference
    private List<Review> reviewIds;

    public Movie() {
    }

    public Movie(ObjectId id, String imbId, String title, String releaseDate, String trailerLink, String poster, List<String> genres, List<String> backdrops) {
        this.id = id;
        this.imdbId = imbId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.trailerLink = trailerLink;
        this.poster = poster;
        this.genres = genres;
        this.backdrops = backdrops;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<String> backdrops) {
        this.backdrops = backdrops;
    }

    public List<Review> getReviewIds() {
        return reviewIds;
    }

    public void setReviewIds(List<Review> reviewIds) {
        this.reviewIds = reviewIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(imdbId, movie.imdbId) && Objects.equals(title, movie.title) && Objects.equals(releaseDate, movie.releaseDate) && Objects.equals(trailerLink, movie.trailerLink) && Objects.equals(poster, movie.poster) && Objects.equals(genres, movie.genres) && Objects.equals(backdrops, movie.backdrops) && Objects.equals(reviewIds, movie.reviewIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imdbId, title, releaseDate, trailerLink, poster, genres, backdrops, reviewIds);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", imbId='" + imdbId + '\'' +
                ", title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", trailerLink='" + trailerLink + '\'' +
                ", poster='" + poster + '\'' +
                ", genres=" + genres +
                ", backdrops=" + backdrops +
                ", reviewIds=" + reviewIds +
                '}';
    }
}

