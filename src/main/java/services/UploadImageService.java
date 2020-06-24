package services;

import exceptions.InvalidDataException;

import javax.servlet.http.Part;
import java.io.IOException;

public interface UploadImageService {
     Boolean isCorrect(Part file) throws InvalidDataException, IOException;
}
