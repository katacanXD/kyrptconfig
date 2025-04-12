package net.kyrptonaught.kyrptconfig.config;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class GsonJsonLoader implements JsonLoader {
    private Gson gson;

    public void provideGson(Gson gson) {
        this.gson = gson;
    }

    public Gson getGson() {
        return gson;
    }

    @Override
    public AbstractConfigFile loadFromString(String input, Class<? extends AbstractConfigFile> output) {
        return gson.fromJson(input, output);
    }

    @Override
    public AbstractConfigFile loadFromInputStream(InputStream input, Class<? extends AbstractConfigFile> output) throws Exception {
        try (InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, output);
        }
    }

    @Override
    public String toString(AbstractConfigFile config) {
        return gson.toJson(config);
    }
}
