package sample;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvBuilder;

public class Key {

    private static String apiKey;

    public Key() {

    }

    private static String genKey(){
        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();
        apiKey = dotenv.get("API_KEY");
        return apiKey;
    }

    public String getKey(){
        genKey();
        return apiKey;
    }

    }

