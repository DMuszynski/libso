package pl.dmuszynski.libso.service;

import pl.dmuszynski.libso.model.Image;

import java.util.Set;

public interface ImageService {
    Set<Image> saveAll(Set<Image> images);
    byte[] compressBytes(byte[] data);
    byte[] decompressBytes(byte[] data);
}
