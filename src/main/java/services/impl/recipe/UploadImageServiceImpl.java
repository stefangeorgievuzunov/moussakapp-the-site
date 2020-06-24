package services.impl.recipe;

import exceptions.InvalidDataException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import services.UploadImageService;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class UploadImageServiceImpl implements UploadImageService {
    private final String[] extensions;

    public UploadImageServiceImpl() {
        extensions = new String[]{"jpg", "jpeg", "png", "gif", "svg"};
    }

    public Boolean isCorrect(Part file) throws InvalidDataException {
        String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString();
        File resource = Paths.get(Paths.get(file.getSubmittedFileName()).toString()).toFile();

        System.out.println("FILENAMEE: "+fileName);

        if (!FilenameUtils.isExtension(fileName, extensions)) {
            throw new InvalidDataException("Избраният от Вас файл е с неподходящ формат.");
        }

        if (resource.length() > 1000000) {
            throw new InvalidDataException("Избраният от Вас файл е прекалено голям.");
        }

        return true;
    }
}
