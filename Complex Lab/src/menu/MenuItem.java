package menu;

import AirlinesContent.Airline;

import java.util.logging.Logger;

public interface MenuItem {
    void execute(Airline content, Logger logger) throws Exception;
}
