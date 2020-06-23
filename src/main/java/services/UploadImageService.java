package services;

import exceptions.InvalidDataException;

import javax.servlet.http.Part;

public interface UploadImageService {
    Boolean isCorrect(Part file) throws InvalidDataException;
}
