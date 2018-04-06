package cache;

import com.google.gson.Gson;
import com.linkedin.replica.jobs.cache.CacheConnection;
import com.linkedin.replica.jobs.cache.handlers.CacheHandler;
import com.linkedin.replica.jobs.cache.handlers.impl.JedisCacheHandler;
import com.linkedin.replica.jobs.cache.handlers.impl.JobsHandler;
import com.linkedin.replica.jobs.config.Configuration;
import com.linkedin.replica.jobs.models.Job;
import com.linkedin.replica.jobs.models.User;
import com.linkedin.replica.jobs.services.JobService;
import  com.linkedin.replica.jobs.database.DatabaseSeed;
import org.json.simple.parser.ParseException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;
public class JobsCacheHandlerTest {

        private static Gson gson;
        private static JobService jobService;
        private static DatabaseSeed databaseSeed;
        private static Configuration config;
        private static String userId = "0";
        private static HashMap<String, String> args;
        private static JedisCacheHandler jobsHandler;

        @BeforeClass
        public static void setup() throws ClassNotFoundException, SQLException, ParseException, IOException {
            gson = new Gson();
            Configuration.init("src/main/resources/config/app.config", "src/main/resources/config/arango.config",
                    "src/main/resources/config/database.config","src/main/resources/config/commands.config",
                    "src/main/resources/config/controller.config","src/main/resources/config/cache.config");
            config = Configuration.getInstance();
            System.out.println(Integer.parseInt(config.getRedisConfigProp("cache.jobs.index")));
            jobService = new JobService();
            jobsHandler = new JedisCacheHandler();
            args = new HashMap<>();

        }

        @Test
        public void testJobsCache() throws Exception {
            String [] ids = new String[1];
            ids [0] = "1";
            ArrayList<Job> jobs = new ArrayList<Job>();
            Job  job = new Job("1","","", "","Data Wrangling Engineer","Junior", "1","DFKI", "Stuttgart","","");
            jobs.add(job);
            jobsHandler.saveJobListing(ids,jobs);
            Job tempjob =(Job)jobsHandler.getJobListingFromCache("1",Job.class);
            System.out.println(tempjob);
            assertEquals("the two companies must have the same id","1",tempjob.getCompanyID());

        }



        @AfterClass
        public static void teardown() throws IOException, SQLException, ClassNotFoundException {

            CacheConnection.getInstance().destroyRedisPool();
        }



}
