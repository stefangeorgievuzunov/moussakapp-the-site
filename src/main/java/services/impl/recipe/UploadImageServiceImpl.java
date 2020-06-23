package services.impl.recipe;

import exceptions.InvalidDataException;
import org.apache.commons.io.FilenameUtils;
import services.UploadImageService;

import javax.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;

public class UploadImageServiceImpl implements UploadImageService {
    private final String[] extensions;

    public UploadImageServiceImpl() {
        extensions = new String[]{"jpg", "jpeg", "png", "gif", "svg"};
    }

    @Override
    public Boolean isCorrect(Part file) throws InvalidDataException {
        String filename = Paths.get(file.getSubmittedFileName()).getFileName().toString();
        File resource = Paths.get(Paths.get(file.getSubmittedFileName()).toString()).toFile();

        if (!FilenameUtils.isExtension(filename, extensions)) {
            throw new InvalidDataException("Избраният от Вас файл е с неподходящ формат.");
        }

        if (resource.length() > 1000000) {
            throw new InvalidDataException("Избраният от Вас файл е прекалено голям.");
        }

        return true;
    }
}
