package services;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public interface JSONParserService {
    <T> T read(BufferedReader reader, Class<T> clazz) throws IOException;
    <T> void write(PrintWriter response, T object);
}
