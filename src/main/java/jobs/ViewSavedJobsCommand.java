package  jobs;
import database.ArangoHandler;
import database.DatabaseHandler;
import  models.Command;
import models.Job;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ViewSavedJobsCommand extends Command{

    public ViewSavedJobsCommand(HashMap<String, String> args) {
        super(args);
    }

    public LinkedHashMap<String, Object> execute() throws IOException {
        validateArgs(new String[]{"userId"});
        // get notifications from db
        DatabaseHandler noSqlHandler = (DatabaseHandler) new ArangoHandler();

        this.setDbHandler(noSqlHandler);
        List<Job> jobs = dbHandler.getSavedJobs(args.get("userId"));
        LinkedHashMap<String, Object>resutls = new LinkedHashMap<String, Object>();
        resutls.put("response",jobs);
        return resutls;
    }


}
