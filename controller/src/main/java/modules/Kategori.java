package modules;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Kategori {
    STRIKKING,
    VEVING,
    SYING;

    // Jackson vil kalle denne metoden når den leser JSON
    @JsonCreator
    public static Kategori fromString(String value) {
        if (value == null) {
            return null;
        }
        try {
            // Gjør teksten om til STORE bokstaver før den slås opp i enumen
            return Kategori.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Ugyldig kategori: " + value +
                    ". Gyldige verdier er: STRIKKING, VEVING, SYING");
        }
    }
}