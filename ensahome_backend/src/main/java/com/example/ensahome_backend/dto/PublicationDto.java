package com.example.ensahome_backend.dto;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PublicationDto {
    private String id;
    private String typePub;
    private List<String> photos;
    private String designation;
    private String desc;
    private Double prix;

    private String adresee;
    private String proximite;
    private String type;
    private Integer nombrePieces;
    private Float loyer;
    private List<Map<String, String>> commodites;

    // Getters et setters
}
