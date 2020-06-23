package services.impl.json;

import com.google.gson.Gson;
import services.JSONParserService;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class JSONParserServiceImpl implements JSONParserService {
    private final Gson gson;

    @Inject
    public JSONParserServiceImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public <T> T read(BufferedReader reader, Class<T> clazz) throws IOException {
        String line;
        StringBuilder builder = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        return gson.fromJson(builder.toString(), clazz);
    }

    @Override
    public <T> void write(PrintWriter response, T object) {
        response.println(gson.toJson(object));
        response.flush();
    }
}
