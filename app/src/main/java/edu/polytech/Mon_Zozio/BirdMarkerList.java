package edu.polytech.Mon_Zozio;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.List;

public class BirdMarkerList {
    private List<BirdMarker> birdMarkers;

    public BirdMarkerList() {
        birdMarkers = new ArrayList<>();
        // Ajoutez ici vos marqueurs avec les noms d'oiseaux et les coordonnées correspondantes
        birdMarkers.add(new BirdMarker("Merle noir", new GeoPoint(48.8566, 2.3522))); // Paris
        birdMarkers.add(new BirdMarker("Pinson des arbres", new GeoPoint(43.6047, 1.4442))); // Toulouse
        birdMarkers.add(new BirdMarker("Roitelet huppé", new GeoPoint(47.2184, -1.5536))); // Nantes
        birdMarkers.add(new BirdMarker("Chardonneret élégant", new GeoPoint(43.7102, 7.2620))); // Nice
        birdMarkers.add(new BirdMarker("Mésange charbonnière", new GeoPoint(48.1173, -1.6778))); // Rennes
        birdMarkers.add(new BirdMarker("Moineau domestique", new GeoPoint(43.7102, 7.2620))); // Nice
        birdMarkers.add(new BirdMarker("Fauvette à tête noire", new GeoPoint(43.2965, 5.3698))); // Marseille
        birdMarkers.add(new BirdMarker("Tarier des prés", new GeoPoint(47.6375, -2.7776))); // Nantes
        birdMarkers.add(new BirdMarker("Geai des chênes", new GeoPoint(48.8566, 2.3522))); // Paris
        birdMarkers.add(new BirdMarker("Rougegorge familier", new GeoPoint(48.5839, 7.7455))); // Strasbourg
        birdMarkers.add(new BirdMarker("Pie bavarde", new GeoPoint(45.7640, 4.8357))); // Lyon
        birdMarkers.add(new BirdMarker("Hirondelle rustique", new GeoPoint(47.2407, -1.6308))); // Nantes
        birdMarkers.add(new BirdMarker("Alouette lulu", new GeoPoint(47.9022, 1.9040))); // Orléans
        birdMarkers.add(new BirdMarker("Bouvreuil pivoine", new GeoPoint(43.6108, 3.8767))); // Montpellier
        birdMarkers.add(new BirdMarker("Mouette rieuse", new GeoPoint(48.8566, 2.3522))); // Paris
        birdMarkers.add(new BirdMarker("Faucon crécerelle", new GeoPoint(48.8566, 2.3522))); // Paris
        birdMarkers.add(new BirdMarker("Bruant zizi", new GeoPoint(50.6312, 3.0580))); // Lille
        birdMarkers.add(new BirdMarker("Bécasseau variable", new GeoPoint(48.5734, -2.7331))); // Rennes
        birdMarkers.add(new BirdMarker("Rouge-gorge", new GeoPoint(48.8566, 2.3522))); // Paris
        birdMarkers.add(new BirdMarker("Huppe fasciée", new GeoPoint(45.7489, 4.8467))); // Lyon
        birdMarkers.add(new BirdMarker("Mésange bleue", new GeoPoint(43.2965, 5.3698))); // Marseille
        birdMarkers.add(new BirdMarker("Bergeronnette grise", new GeoPoint(48.5839, 7.7455))); // Strasbourg
        birdMarkers.add(new BirdMarker("Corneille noire", new GeoPoint(48.8566, 2.3522))); // Paris
        birdMarkers.add(new BirdMarker("Tourterelle des bois", new GeoPoint(47.9022, 1.9040))); // Orléans
        birdMarkers.add(new BirdMarker("Cigogne blanche", new GeoPoint(48.8566, 2.3522))); // Paris
        birdMarkers.add(new BirdMarker("Buse variable", new GeoPoint(45.7640, 4.8357))); // Lyon
        birdMarkers.add(new BirdMarker("Pie-grièche écorcheur", new GeoPoint(43.6108, 3.8767))); // Montpellier
        birdMarkers.add(new BirdMarker("Merle à plastron", new GeoPoint(48.9558, 1.8542))); // Paris
        birdMarkers.add(new BirdMarker("Tarin des aulnes", new GeoPoint(50.6312, 3.0580))); // Lille
        birdMarkers.add(new BirdMarker("Hirondelle de fenêtre", new GeoPoint(48.8566, 2.3522))); // Paris
        birdMarkers.add(new BirdMarker("Grèbe huppé", new GeoPoint(47.6375, -2.7776))); // Nantes
        birdMarkers.add(new BirdMarker("Pigeon ramier", new GeoPoint(43.7102, 7.2620))); // Nice
        birdMarkers.add(new BirdMarker("Pouillot véloce", new GeoPoint(43.2965, 5.3698))); // Marseille
    }

    public List<BirdMarker> getBirdMarkers() {
        return birdMarkers;
    }
}
