package DTO;

import modules.Kategori;

import java.time.LocalDateTime;

public class KursDTO {
    public String navn;
    public LocalDateTime startTid;
    public LocalDateTime sluttTid;
    public String beskrivelse;
    public Kategori kategori;
}
