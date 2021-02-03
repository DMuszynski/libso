package pl.dmuszynski.libso.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dmuszynski.libso.model.Image;
import pl.dmuszynski.libso.repository.ImageRepository;
import pl.dmuszynski.libso.service.ImageService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service(value = "imageService")
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Set<Image> saveAll(Set<Image> images) {
        return new HashSet<>(this.imageRepository.saveAll(images));
    }

    // compress the image bytes before storing it in the database
    @Override
    public byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    @Override
    public byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException ioe) {
            ioe.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}
